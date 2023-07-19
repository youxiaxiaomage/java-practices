package com.yxxmg.mybatisplussample.domain;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class OcrResponseTest {
    private static final String RESPONSE =
            "{\n" + "    \"log_id\": 1390582998516105216,\n" + "    \"words_result_num\": 2\n" + "    \"words_result\": [\n"
                    + "        {\n" + "            \"words\": \" OCR\"\n" + "        },\n" + "        {\n"
                    + "            \"words\": \"百度通用文字识别高精度版\"\n" + "        }\n" + "    ]  \n" + "}";
    private static final String ERROR_RSP =
            "{\n" + "  \"error_code\": 110,\n" + "  \"error_msg\": \"Access token invalid or no longer valid\"\n" + "}";

    @Test
    public void test() {
        OcrResponse ocrResponse = JSONObject.parseObject(RESPONSE, OcrResponse.class);
        System.out.println(ocrResponse);
    }

    @Test
    public void test1() {
        OcrResponse ocrResponse = JSONObject.parseObject(ERROR_RSP, OcrResponse.class);
        System.out.println(ocrResponse);
    }
}