package com.yly.func;

import java.io.FileOutputStream;

import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.yly.issue.Issueapp;
import com.yly.ls.Lsinfo;
import com.yly.stor.Stoappinfo;

public class pdfMaker {
    static Font font;
    static {
        try {
            font = new Font(BaseFont.createFont("/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
            font.setSize(5f);
        } catch (Exception e) {}
    }
	public pdfMaker(){
		super();
	}
	public static void printPdf(Stoappinfo app)throws Exception{
        Document doc = new Document();
        doc.setPageSize(PageSize.A5);
        PdfWriter pw = PdfWriter.getInstance(doc, new FileOutputStream("e:/"+app.getFormNo()+".pdf"));
        doc.open();
        PdfContentByte cb = pw.getDirectContent();

        /*               ux 
         *   |-----------|uy
         *   |           |
         *   |           | 595
         * ly|-----------|
         *   lx   420
         */
        //
        addText(170, 385, 210, 405, cb, app.getCurrDate());
        //
        addText(360, 385, 410, 405, cb, app.getFormNo());
        //
        addText(10, 330, 110, 350, cb,"√‹‘øø®" );
        addText(110, 330, 210, 350, cb,SingleDicMap.getDicItemVal(SingleDic.PROD_ID, app.getProdId()));
        addText(250, 330, 270, 350, cb, app.getProdId().equals("4")?"øÈ":"’≈");
        addText(210, 330, 250, 350, cb,String.valueOf(app.getPurchaseAmt()));
        addText(270, 330, 310, 350, cb, String.valueOf(app.getUnitPrice()));
        addText(270, 330, 350, 350, cb, app.getRemarks());

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
