package com.yly.sample;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfMaker {
    static Font font;
    static {
        try {
            font = new Font(BaseFont.createFont("/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
            font.setSize(5f);
        } catch (Exception e) {}
    }
    public static void main(String[] args) throws Exception {
        Document doc = new Document();
        doc.setPageSize(PageSize.A5);
//        Rectangle r = new Rectangle(240f, 139.5f);
//        doc.setPageSize(r);
        PdfWriter pw = PdfWriter.getInstance(doc, new FileOutputStream("e:/test.pdf"));
        doc.open();
        PdfContentByte cb = pw.getDirectContent();

        /*               ux 
         *   |-----------|uy
         *   |           |
         *   |           | 595
         * ly|-----------|
         *   lx   420
         */
        //鏃ユ湡
        addText(170, 385, 210, 405, cb, "15  2  20");
        //鍗曞彿
        addText(360, 385, 410, 405, cb, "M212");
        //鏄庣粏
        addText(10, 330, 110, 350, cb, "xx卡");
        addText(110, 330, 210, 350, cb, "从xxx采购");
        addText(210, 330, 250, 350, cb, "模块");
        addText(250, 330, 270, 350, cb, "块");
        addText(270, 330, 310, 350, cb, "8000");

        addText(10, 280, 110, 300, cb, "xx卡");
        addText(110, 280, 210, 300, cb, "从xxx采购");
        addText(210, 280, 250, 300, cb, "模块");
        addText(250, 280, 270, 300, cb, "块");
        addText(270, 280, 310, 300, cb, "500");

        doc.close();
        pw.close();
    }

    private static void addText(int lx, int ly, int ux, int uy, PdfContentByte cb, String s) throws Exception {
        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(lx, ly, ux, uy, 5, Element.ALIGN_LEFT);
        
        ct.addText(new Phrase(s, font));
        ct.go();
    }
}
