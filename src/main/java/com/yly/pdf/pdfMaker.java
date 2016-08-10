package com.yly.pdf;

import java.io.FileOutputStream;
import java.util.List;

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
import com.yly.stor.StoAppInfoForm;
import com.yly.stor.Stoappinfo;

public class pdfMaker {
    static Font font;
    static {
        try {
            font = new Font(BaseFont.createFont("/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
            font.setSize(9f);
        } catch (Exception e) {}
    }
	public pdfMaker(){
		super();
	}
	public static void printPdf(List<Stoappinfo> stoList,StoAppInfoForm f)throws Exception{
        Document doc = new Document();
        doc.setPageSize(PageSize.A5);
        PdfWriter pw = PdfWriter.getInstance(doc, new FileOutputStream("e:/"+f.getFormNo()+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,String.valueOf(f.getOperationType()))+".pdf"));
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
        if(f.getOperationType()<20 ||f.getOperationType()==92){
        	 addText(170, 385, 250, 405, cb, f.getCurrDate().substring(0,9));
             addText(300, 385, 410, 405, cb, f.getFormNo());
             int i=1;
         	for(Stoappinfo sto:stoList){
                 addText(10, getColumn(i,2), 80,  getColumn(i,4), cb,"ÃÜÔ¿¿¨--"+SingleDicMap.getDicItemVal(SingleDic.PROD_ID, sto.getProdId()));
                 addText(90,  getColumn(i,2), 150, getColumn(i,4), cb, ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, sto.getManufacId())+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,String.valueOf(sto.getOperationType())));
                 addText(160,  getColumn(i,2), 230, getColumn(i,4), cb,SingleDicMap.getDicItemVal(SingleDic.COMM_RATE,sto.getPhiTypeId()));
                 addText(230,  getColumn(i,2), 240, getColumn(i,4), cb, sto.getProdId().equals("4")?"¿é":"ÕÅ");
                 addText(240,  getColumn(i,2), 260, getColumn(i,4), cb, String.valueOf(sto.getPurchaseAmt()));
                 addText(270,  getColumn(i,2), 300, getColumn(i,4), cb, String.valueOf(sto.getUnitPrice()));
                 addText(310,  getColumn(i,2), 390, getColumn(i,4), cb, String.valueOf(sto.getRsvd().trim()));
                 i++;
                 if(i>4){
                 	doc.newPage();
                 	i=1;
                 }
         	}
        }else{
        	 addText(170, 385, 250, 405, cb, f.getCurrDate().substring(0,9));
             addText(300, 385, 410, 405, cb, f.getFormNo());
             int i=1;
         	for(Stoappinfo sto:stoList){
                 addText(10, getColumn(i,2), 50,  getColumn(i,4), cb,"ÃÜÔ¿¿¨" );
                 addText(60,  getColumn(i,2), 120, getColumn(i,4), cb,SingleDicMap.getDicItemVal(SingleDic.PROD_ID, sto.getProdId()));
                 addText(130,  getColumn(i,2), 150, getColumn(i,4), cb, sto.getProdId().equals("4")?"¿é":"ÕÅ");
                 addText(160,  getColumn(i,2), 210, getColumn(i,4), cb,String.valueOf(sto.getPurchaseAmt()));
                 addText(220,  getColumn(i,2), 250, getColumn(i,4), cb, String.valueOf(sto.getUnitPrice()));
                 addText(260,  getColumn(i,2), 320, getColumn(i,4), cb, String.valueOf(sto.getRsvd().trim()));
             	if(f.getOperationType()==31){
                     addText(330,  getColumn(i,2), 390, getColumn(i,4), cb, ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, sto.getManufacId())+SingleDicMap.getDicItemVal(SingleDic.REPORT_TYPE,String.valueOf(sto.getOperationType())));
             	}else{
                     addText(330,  getColumn(i,2), 390, getColumn(i,4), cb, ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, sto.getManufacId())+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,String.valueOf(sto.getOperationType())));

             	}
                 
                 i++;
                 if(i>4){
                 	doc.newPage();
                 	i=1;
                 }
         	}
        }
        
           
        

        doc.close();
        pw.close();
    }

    private static void addText(int lx, int ly, int ux, int uy, PdfContentByte cb, String s) throws Exception {
        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(lx, ly, ux, uy, 10, Element.ALIGN_LEFT);
        
        ct.addText(new Phrase(s, font));
        ct.go();
    }
    private static int getColumn(int i,int j) throws Exception {
    	int col=0;
		if(j==2){
			col=330;
		}else if(j==4){
			col=350;
		}
		if(i>1){
			col=col-50*(i-1);
		}
		return col;
    }
}
