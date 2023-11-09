package com.yxxmg.tess4j.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yxxmg.tess4j.service.OcrService;

import net.sourceforge.tess4j.TesseractException;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/9
 */
@RestController
@RequestMapping("/api")
public class OcrController {
    @Resource
    private OcrService ocrService;

    @PostMapping(value = "/recognize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String recognizedImage(@RequestParam("file") MultipartFile multipartFile)
        throws TesseractException, IOException {
        return this.ocrService.recognizeText(multipartFile);
    }

}
