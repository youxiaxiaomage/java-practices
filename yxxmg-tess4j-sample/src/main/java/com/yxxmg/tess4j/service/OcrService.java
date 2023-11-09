package com.yxxmg.tess4j.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/9
 */
@Service
@RequiredArgsConstructor
public class OcrService {
    private final Tesseract tesseract;

    public String recognizeText(MultipartFile multipartFile) throws IOException, TesseractException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
        return this.tesseract.doOCR(bufferedImage);
    }
}
