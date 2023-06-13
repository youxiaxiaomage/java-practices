package com.yxxmg.javers;

import cn.hutool.core.lang.Assert;
import com.yxxmg.javers.annotation.Column;
import com.yxxmg.javers.comparator.CustomerLabelComparator;
import com.yxxmg.javers.comparator.CustomerTypeComparator;
import com.yxxmg.javers.comparator.DealerComparator;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.javers.common.reflection.ReflectionUtil;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ListChange;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/9
 */
public class JaversUtil<T> {
    private static final Javers JAVERS =
        JaversBuilder.javers().registerValue(CustomerLabel.class, new CustomerLabelComparator())
            .registerValue(Dealer.class, new DealerComparator())
            .registerValue(CustomerTypeEnum.class, new CustomerTypeComparator()).build();

    @SneakyThrows
    public static <T> Changes diff(@NonNull T before, @NonNull T after) {
        Diff compare = JAVERS.compare(before, after);
        Changes changes = compare.getChanges();
        if (CollectionUtils.isNotEmpty(changes)) {
            for (Change change : changes) {
                Optional<Object> affectedObject = change.getAffectedObject();
                if (affectedObject.isPresent()) {
                    if (change instanceof NewObject) {
                        // 新增
                        NewObject newObj = (NewObject)change;
                        System.out.println("新增=>" + newObj);
                    } else if (change instanceof ObjectRemoved) {
                        // 元素删除
                        ObjectRemoved objRemoved = (ObjectRemoved)change;
                        System.out.println("删除=>" + objRemoved);
                    } else if (change instanceof ValueChange) {
                        // 值变化
                        ValueChange valueChange = (ValueChange)change;
                        String propertyName = valueChange.getPropertyName();
                        Field field = getField(propertyName, change.getAffectedObject().get().getClass());
                        Assert.notNull(field);
                        Column annotation = field.getAnnotation(Column.class);
                        System.out.println(field.getType());
                        field.setAccessible(true);

                        System.out.println(annotation.name() + ": " + str(valueChange.getLeft()) + "修改=> "
                            + str(valueChange.getRight()));
                    } else if (change instanceof ListChange) {
                        // 集合变化
                        ListChange listChange = (ListChange)change;
                        System.out.println(listChange.getLeft());
                        System.out.println(listChange.getRight());
                        System.out.println("集合变化=>" + listChange);
                    }
                }
            }
        }
        return null;
    }

    private static String str(Object obj) {
        if (obj instanceof CustomerTypeEnum) {
            return ((CustomerTypeEnum)obj).getMsg();
        } else if (obj instanceof Dealer) {
            return ((Dealer)obj).getDearName();
        }
        return null;
    }

    private static List<Field> getFields(Class clazz) {
        List<Field> annotationFieldList = Arrays.stream(clazz.getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(Column.class)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(annotationFieldList)) {
            return Collections.emptyList();
        }
        return annotationFieldList;
    }

    private static Field getField(String fieldName, Class clazz) {
        List<Field> fields = getFields(clazz);
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }
        return fields.stream().filter(field -> fieldName.equals(field.getName())).findFirst().orElse(null);
    }

    public static <T> Changes diff(List<T> before, List<T> after, Class<T> clazz) {
        Diff diff = JAVERS.compareCollections(before, after, clazz);
        return diff.getChanges();
    }
}
