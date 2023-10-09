package com.yxxmg.itext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.collections.CollectionUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/9
 */
public final class ItextPdfUtil {
    public static void addWaterMark(String source, String target, String waterContent) {
        try {
            // 水印的高和宽
            int waterMarkHeight = 30;
            int watermarkWeight = 60;

            // 水印间隔距离
            int waterMarkInterval = 100;

            // 读取PDF文件流
            PdfReader pdfReader = new PdfReader(source);
            // 创建PDF文件的模板，可以对模板的内容修改，重新生成新PDF文件
            PdfStamper pdfStamper = new PdfStamper(pdfReader, Files.newOutputStream(Paths.get(target)));

            // 设置水印字体
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);

            // 设置PDF内容的Graphic State 图形状态
            PdfGState pdfGraPhicState = new PdfGState();
            // 填充透明度
            pdfGraPhicState.setFillOpacity(0.2f);
            // 轮廓不透明度
            pdfGraPhicState.setStrokeOpacity(0.4f);

            // PDF页数
            int pdfPageNum = pdfReader.getNumberOfPages() + 1;

            // PDF文件内容字节
            PdfContentByte pdfContent;

            // PDF页面矩形区域
            Rectangle pageRectangle;

            for (int i = 1; i < pdfPageNum; i++) {
                // 获取当前页面矩形区域
                pageRectangle = pdfReader.getPageSizeWithRotation(i);
                // 获取当前页内容，getOverContent表示之后会在页面内容的上方加水印
                pdfContent = pdfStamper.getOverContent(i);

                // 获取当前页内容，getOverContent表示之后会在页面内容的下方加水印
                // pdfContent = pdfStamper.getUnderContent(i);

                pdfContent.saveState();
                // 设置水印透明度
                pdfContent.setGState(pdfGraPhicState);

                // 开启写入文本
                pdfContent.beginText();
                // 设置字体
                pdfContent.setFontAndSize(baseFont, 20);

                // 在高度和宽度维度每隔waterMarkInterval距离添加一个水印
                for (int height = waterMarkHeight; height < pageRectangle.getHeight();
                    height = height + waterMarkInterval) {
                    for (int width = watermarkWeight; width < pageRectangle.getWidth() + watermarkWeight;
                        width = width + waterMarkInterval) {
                        // 添加水印文字并旋转30度角
                        pdfContent.showTextAligned(Element.ALIGN_LEFT, waterContent, width - watermarkWeight,
                            height - waterMarkHeight, 30);
                    }
                }
                // 停止写入文本
                pdfContent.endText();
            }
            pdfStamper.close();
            pdfReader.close();
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasWaterMark(String filePath) throws IOException {
        PdfReader pdfReader = new PdfReader(filePath);
        AcroFields acroFields = pdfReader.getAcroFields();
        return CollectionUtils.isNotEmpty(acroFields.getSignatureNames());
    }
}
