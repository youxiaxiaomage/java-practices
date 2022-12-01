package com.yxxmg.mybatisplussample.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : OCR响应
 * @since : 2022/11/7
 */
@Data
public class OcrResponse implements Serializable {
    private static final long serialVersionUID = 6692172050101897450L;
    @JSONField(name = "log_id")
    private long logId;
    @JSONField(name = "words_result_num")
    private int wordsResultNum;

    @JSONField(name = "words_result")
    private List<Words> wordsResult;

}