package com.yxxmg.mybatisplussample.plugin;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.yxxmg.mybatisplussample.enums.BaseEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.builders.PropertySpecificationBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/3
 */
public class EnumModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<BeanPropertyDefinition> definition = context.getBeanPropertyDefinition();
        if (!definition.isPresent()) {
            return;
        }
        Class<?> fieldClassType = definition.get().getField().getRawType();
        if (BaseEnum.class.isAssignableFrom(fieldClassType)) {
            EnumApiModelProperty annotation =
                AnnotationUtils.findAnnotation(fieldClassType, EnumApiModelProperty.class);
            boolean retCode = true;
            if (annotation != null) {
                retCode = annotation.retCode();
            }
            List<BaseEnum> allEnums = BaseEnum.getAllEnums(fieldClassType);
            if (CollectionUtils.isEmpty(allEnums)) {
                return;
            }
            changeEnumDesc(context, retCode, allEnums);
        }
    }

    private void changeEnumDesc(ModelPropertyContext context, boolean retCode, List<BaseEnum> allEnums) {
        String desc = generateDesc(allEnums, retCode);
        changeSpecPropertyBuilderEnumDesc(context, desc);
        changePropertyBuilderEnumDesc(context, retCode, desc);
    }

    private void changePropertyBuilderEnumDesc(ModelPropertyContext context, boolean retCode, String desc) {
        PropertySpecificationBuilder builder = context.getSpecificationBuilder();
        changeEnumDesc(desc, builder);
        if (retCode) {
            // ModelSpecification
            builder.type(context.getResolver().resolve(Integer.class));
        } else {
            builder.type(context.getResolver().resolve(String.class));
        }
    }

    private void changeEnumDesc(String desc, ModelPropertyBuilder builder) {
        String orginalDesc = "";
        try {
            Field description = ReflectionUtils.findField(ModelPropertyBuilder.class, "description");
            if (description != null) {
                ReflectionUtils.makeAccessible(description);
                Object field = ReflectionUtils.getField(description, builder);
                if (field != null) {
                    orginalDesc = field + "";
                }
            }
        } catch (Exception e) {
            // ignore
        }
        builder.description(orginalDesc + desc);
    }

    private void changeSpecPropertyBuilderEnumDesc(ModelPropertyContext context, String desc) {
        PropertySpecificationBuilder builder = context.getSpecificationBuilder();
        changeEnumDesc(desc, builder);
    }

    private void changeEnumDesc(String desc, PropertySpecificationBuilder builder) {
        String originalDesc = "";
        try {
            Field field = ReflectionUtils.findField(PropertySpecificationBuilder.class, "description");
            if (field != null) {
                ReflectionUtils.makeAccessible(field);
                Object f = ReflectionUtils.getField(field, builder);
                if (f != null) {
                    originalDesc = f + ", ";
                }
            }
        } catch (Exception e) {
            // ignore
        }
        builder.description(originalDesc + desc);
    }

    private String generateDesc(List<BaseEnum> allEnums, boolean retCode) {
        if (retCode) {
            return allEnums.stream().map(baseEnum -> baseEnum.getCode() + ":" + baseEnum.getDesc())
                .collect(Collectors.joining(","));
        }
        return allEnums.stream().map(BaseEnum::getDesc).collect(Collectors.joining(","));
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
