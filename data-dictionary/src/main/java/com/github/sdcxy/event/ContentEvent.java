package com.github.sdcxy.event;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @ClassName ContentEvent
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/18 14:22
 **/
public class ContentEvent extends PdfPageEventHelper {

    @Override
    public void onStartPage(PdfWriter pdfWriter, Document document) {
        super.onStartPage(pdfWriter, document);
    }

    @Override
    public void onChapter(PdfWriter pdfWriter, Document document, float v, Paragraph paragraph) {
        super.onChapter(pdfWriter, document, v, paragraph);
    }

    @Override
    public void onSection(PdfWriter pdfWriter, Document document, float v, int i, Paragraph paragraph) {
        super.onSection(pdfWriter, document, v, i, paragraph);
    }
}
