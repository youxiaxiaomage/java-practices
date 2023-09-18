package com.yxxmg.lombok;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/18
 */
@Data
@Builder
public class Book implements Serializable {
    private String bookId;
    private String bookName;
    /**
     * @Builder 使用后有些默认值无法使用 可以使用@Builder.Default设置
     */
    @Builder.Default
    private Boolean isNew = Boolean.TRUE;
}
