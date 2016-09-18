package com.yly.pdf;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.config.SysConfig;
import com.eis.util.DateUtil;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Element;
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
		Document document = new Document();  
		File file = new File(SysConfig.getProperty("pdf.download"));
		if(!file.exists()){
			file.mkdirs();
		}
        PdfWriter.getInstance(document, new FileOutputStream(new File(SysConfig.getProperty("pdf.download")+File.separator+f.getFormNo()+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,String.valueOf(f.getOperationType()))+".pdf")));
        BaseFont bfChinese = BaseFont.createFont(SysConfig.getProperty("font.download")+File.separator+"SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED); 
        Font titlefont=new Font(bfChinese,12,Font.BOLD);
        Font textfont=new Font(bfChinese,8,com.lowagie.text.Font.COURIER);
        Font textRedfont=new Font(bfChinese,8,Font.COURIER,Color.RED);
    	Font keyfont=new Font(bfChinese,10,Font.BOLD);
    	document.open();  

        //Seperate Page controller
        int recordPerPage=5;
        int fullPageRequired=stoList.size()/recordPerPage;   //满页数
        int remainPage=stoList.size()%recordPerPage>1?1:0;   //剩余页数
        int totalPage=fullPageRequired+remainPage;           //总页数 
        if(stoList.size()<recordPerPage){
        	totalPage=1;
        }
        
        
	    String opertypeDes="";
	    String dateDes="";
	    String ABC="";
	    if(f.getOperationType()<20 ||f.getOperationType()==61){
	    	opertypeDes="入库单";
	    	dateDes="入库";
	    	ABC="A";
	    }else if(f.getOperationType()==92){
	    	opertypeDes=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(f.getOperationType()));
	    	dateDes="冲回";
	    	ABC="B";
	    }else {
	    	opertypeDes=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(f.getOperationType()));
	    	dateDes="出库";
	    	ABC="C";
	    }
          
	    if("A".equals(ABC)||"B".equals(ABC)){
	    	getAForm(stoList, f, document, titlefont, textfont, textRedfont,keyfont,
      				recordPerPage, totalPage, opertypeDes, dateDes);
	    }else{
        	  getCForm(stoList, f, document, titlefont, textfont,textRedfont, keyfont,
      				recordPerPage, totalPage, opertypeDes, dateDes);
        }
      
    	document.close(); 
    }
	public static void getCForm(List<Stoappinfo> stoList, StoAppInfoForm f,
			Document document, Font titlefont, Font textfont,Font textRedfont,  Font keyfont,
			int recordPerPage, int totalPage, String opertypeDes, String dateDes)
			throws DocumentException, BadElementException,
			MalformedURLException, IOException {

		for(int j=0;j<totalPage;j++){

			
            document.newPage();

		    //create title image
		    Image jpeg=Image.getInstance("E:\\logo.png");
		    jpeg.setAlignment(Image.ALIGN_LEFT);
		    jpeg.scaleAbsolute(60, 30);
		    document.add(jpeg);
		

		    String title="密钥卡系统产品"+opertypeDes;
		    Paragraph head1 = new Paragraph(printBlank(30)+title,titlefont);
		    document.add(head1);
		    
		    String date=dateDes+"日期:"+DateUtil.formatDate(f.getCurrDate());
		    String formNo=dateDes+"单号:"+f.getFormNo();
		    
		    Paragraph head2 = new Paragraph(printBlank(10)+date+printBlank(30)+formNo,keyfont);
		    document.add(head2);		    
		    Chunk ctitle=new Chunk(printBlank(300),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.UNDERLINE));		
		    document.add(ctitle);
		    
			Table table = new Table(33);
		    table.setWidth(100);  
			table.setBorderWidth(1);
			table.setBorderColor(new Color(0, 0, 255));
			table.setPadding(5);
			
		    Table tHeader=new Table(2);
		    float[] widthsHeader={2f,3f};
		    tHeader.setWidths(widthsHeader);
		    tHeader.setWidth(100);
		    tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		    
			Cell cell  = new Cell(new Paragraph("名称",keyfont));
			cell.setColspan(6);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
		    cell.setBorderColor(new Color(255, 0, 0));
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("规格",keyfont));
			cell.setColspan(5);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);
		
			cell  = new Cell(new Paragraph("单位",keyfont));
			cell.setColspan(2);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("数量",keyfont));
			cell.setColspan(4);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);    	
		
			cell  = new Cell(new Paragraph("单价",keyfont));
			cell.setColspan(3);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);        
			
			
			cell  = new Cell(new Paragraph("金额",keyfont));
			cell.setColspan(6);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);           	
			
			
			cell  = new Cell(new Paragraph("用途或原因",keyfont));
			cell.setColspan(7);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);      	
    	
			
			int max=0;
			if(stoList.size()<=recordPerPage){
				max=stoList.size();
			}else{
				if((j+1)<totalPage){
					max=(j+1)*recordPerPage;
				}else {
					max=stoList.size();
				}
			}

    	for(int i=recordPerPage*j;i<max;i++){
    		Stoappinfo sto=stoList.get(i);
    		Font fixfont=null;
    		if(sto.getOperationType()==61||sto.getOperationType()==92){
    			fixfont=textRedfont;
    		}else{
    			fixfont=textfont;
    		}
        	cell  = new Cell(new Paragraph("密钥卡",fixfont));
        	cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);
        	
        	cell  = new Cell(new Paragraph(SingleDicMap.getDicItemVal(SingleDic.PROD_ID, sto.getProdId()),fixfont));
        	cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);

        	cell  = new Cell(new Paragraph(sto.getProdId().equals("4")?"块":"张",fixfont));
        	cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);
        	
        	cell  = new Cell(new Paragraph(String.valueOf(sto.getPurchaseAmt()),fixfont));
        	cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);    	

        	cell  = new Cell(new Paragraph(String.valueOf(sto.getUnitPrice()),fixfont));
        	cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);        
        	
        	
        	cell  = new Cell(new Paragraph(String.valueOf(sto.getRsvd().trim()),fixfont));
        	cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);           	
        	
        	if(f.getOperationType()==31){
        	cell  = new Cell(new Paragraph(ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, sto.getManufacId())+SingleDicMap.getDicItemVal(SingleDic.REPORT_TYPE,String.valueOf(sto.getOperationType())),fixfont));
        	}else{
            cell  = new Cell(new Paragraph(ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, sto.getManufacId())+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,String.valueOf(sto.getOperationType())),fixfont));
        	}
        	cell.setColspan(7);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);      	

     	}

    	 document.add(table);
     	 Paragraph foot11 = new Paragraph(printBlank(10)+"制单人"+"___________________"+printBlank(30)+"领物人"+"_____________________",keyfont);
         document.add(foot11);
         
         j++;
         Paragraph foot12 = new Paragraph(printBlank(10)+"页码: "+j+" / "+totalPage+printBlank(40)+"Printed from Secretkey System.版本: 1.0",keyfont);
         document.add(foot12);
         j--;
         
         HeaderFooter footer11=new HeaderFooter(foot11, true);
         footer11.setAlignment(HeaderFooter.ALIGN_BOTTOM);
         HeaderFooter footer12=new HeaderFooter(foot12, true);
         footer12.setAlignment(HeaderFooter.ALIGN_BOTTOM);
        }
	}
	public static void getAForm(List<Stoappinfo> stoList, StoAppInfoForm f,
			Document document, Font titlefont, Font textfont,Font textRedfont,  Font keyfont,
			int recordPerPage, int totalPage, String opertypeDes, String dateDes)
			throws DocumentException, BadElementException,
			MalformedURLException, IOException {
		for(int j=0;j<totalPage;j++){
            document.newPage();
            
		    //create title image
		    Image jpeg=Image.getInstance("E:\\logo.png");
		    jpeg.setAlignment(Image.ALIGN_LEFT);
		    jpeg.scaleAbsolute(60, 30);
		    document.add(jpeg);
		
		    String title="密钥卡系统产品"+opertypeDes;
		    Paragraph head1 = new Paragraph(printBlank(30)+title,titlefont);
		    document.add(head1);
		    
		    String date=dateDes+"日期:"+DateUtil.formatDate(f.getCurrDate());
		    String formNo=dateDes+"单号:"+f.getFormNo();
		    
		    Paragraph head2 = new Paragraph(printBlank(10)+date+printBlank(30)+formNo,keyfont);
		    document.add(head2);		    
		    Chunk ctitle=new Chunk(printBlank(300),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.UNDERLINE));		
		    document.add(ctitle);
		    
			Table table = new Table(33);
		    table.setWidth(100);  
			table.setBorderWidth(1);
			table.setBorderColor(new Color(0, 0, 255));
			table.setPadding(5);
			
		    Table tHeader=new Table(2);
		    float[] widthsHeader={2f,3f};
		    tHeader.setWidths(widthsHeader);
		    tHeader.setWidth(100);
		    tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		    
			Cell cell  = new Cell(new Paragraph("名称",keyfont));
			cell.setColspan(6);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("来源",keyfont));
			cell.setColspan(7);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);
		
			cell  = new Cell(new Paragraph("规格",keyfont));
			cell.setColspan(5);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("单位",keyfont));
			cell.setColspan(2);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);    	
		
			cell  = new Cell(new Paragraph("数量",keyfont));
			cell.setColspan(4);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);        
			
			
			cell  = new Cell(new Paragraph("单价",keyfont));
			cell.setColspan(3);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);           	
			
			
			cell  = new Cell(new Paragraph("金额",keyfont));
			cell.setColspan(6);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
			table.addCell(cell);      	
    	
			
			int max=0;
			if(stoList.size()<=recordPerPage){
				max=stoList.size();
			}else{
				if((j+1)<totalPage){
					max=(j+1)*recordPerPage;
				}else {
					max=stoList.size();
				}
			}
			Font fixfont=null;	 
			for(int i=recordPerPage*j;i<max;i++){
	    		Stoappinfo sto=stoList.get(i);
	    		if(f.getOperationType()<20){
	    			fixfont=textfont;
	    		}else{
	    			fixfont=textRedfont;
	    		}
        	cell  = new Cell(new Paragraph("密钥卡--"+SingleDicMap.getDicItemVal(SingleDic.PROD_ID, sto.getProdId()),fixfont));
        	cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
            cell.setBorderColor(new Color(255, 0, 0));
        	table.addCell(cell);
        	
        	cell  = new Cell(new Paragraph(ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, sto.getManufacId())+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,String.valueOf(sto.getOperationType())),fixfont));
        	cell.setColspan(7);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);

        	cell  = new Cell(new Paragraph(SingleDicMap.getDicItemVal(SingleDic.COMM_RATE,sto.getPhiTypeId()),fixfont));
        	cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);
        	
        	cell  = new Cell(new Paragraph(sto.getProdId().equals("4")?"块":"张",fixfont));
        	cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);    	

        	cell  = new Cell(new Paragraph(String.valueOf(sto.getPurchaseAmt()),fixfont));
        	cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);        
        	
        	
        	cell  = new Cell(new Paragraph(String.valueOf(sto.getUnitPrice()),fixfont));
        	cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);           	
        	
        	
            cell  = new Cell(new Paragraph(String.valueOf(sto.getRsvd().trim()),fixfont));
        	cell.setColspan(6);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
        	table.addCell(cell);      	
        	
     	}
        
    	 document.add(table);
     	 Paragraph foot11 = new Paragraph(printBlank(10)+"制单人"+"___________________"+printBlank(30)+"交物人"+"_____________________",keyfont);
         document.add(foot11);
         
         j++;
         Paragraph foot12 = new Paragraph(printBlank(10)+"页码: "+j+" / "+totalPage+printBlank(40)+"Printed from Secretkey System.版本: 1.0",keyfont);
         document.add(foot12);
         j--;
         
         HeaderFooter footer11=new HeaderFooter(foot11, true);
         footer11.setAlignment(HeaderFooter.ALIGN_BOTTOM);
         HeaderFooter footer12=new HeaderFooter(foot12, true);
         footer12.setAlignment(HeaderFooter.ALIGN_BOTTOM);
        }
	}
    public static String leftPad(String str, int i) {
        int addSpaceNo = i-str.length();
        String space = ""; 
        for (int k=0; k<addSpaceNo; k++){
                space= " "+space;
        };
        String result =space + str ;
        return result;
     }
    public static String printBlank(int tmp){
        String space="";
        for(int m=0;m<tmp;m++){
            space=space+" ";
        }
        return space;
  }

}
