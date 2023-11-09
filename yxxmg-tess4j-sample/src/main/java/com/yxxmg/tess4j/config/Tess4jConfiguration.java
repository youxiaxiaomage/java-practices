package com.yxxmg.tess4j.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.Tesseract;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/9
 */
@Configuration
public class Tess4jConfiguration {
    @Value("${tess4j.dataPath}")
    private String dataPath;

    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(dataPath);
        tesseract.setLanguage("chi_sim");
        return tesseract;
    }
}
