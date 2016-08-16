package com.yly.sample;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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
import com.yly.stor.StoAppInfoForm;


public class pdfMakerTest {
    public static void main(String[] args) throws Exception, DocumentException {
        List<String> ponum=new ArrayList<String>();
        add(ponum, 26);
        List<String> line=new ArrayList<String>();
        add(line, 26);
        List<String> part=new ArrayList<String>();
        add(part, 26);
        List<String> description=new ArrayList<String>();
        add(description, 26);
        List<String> origin=new ArrayList<String>();
        add(origin, 26);
    
        StoAppInfoForm f=new StoAppInfoForm();
        f.setOperationType((long)31);
    	Document document = new Document();  
    	PdfWriter.getInstance(document, new FileOutputStream("E:\\Helloworld1.PDF"));
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titlefont=new Font(bfChinese,12,Font.BOLD);
        Font textfont=new Font(bfChinese,8,Font.COURIER);
        Font textRedfont=new Font(bfChinese,8,Font.COURIER,Color.RED);
    	Font keyfont=new Font(bfChinese,10,Font.BOLD);
    	document.open();  

        //Seperate Page controller
        int recordPerPage=10;
        int fullPageRequired=ponum.size()/recordPerPage;   //满页数
        int remainPage=ponum.size()%recordPerPage>1?1:0;   //剩余页数
        int totalPage=fullPageRequired+remainPage;           //总页数 
        
	    String opertypeDes="";
	    String dateDes="";
	    String ABC="";
	    if(f.getOperationType()<20 ||f.getOperationType()==61){
	    	opertypeDes="入库单";
	    	dateDes="入库";
	    	ABC="A";
	    }else if(f.getOperationType()==92){
	    	opertypeDes="冲回单";
	    	dateDes="冲回";
	    	ABC="B";
	    }else {
	    	opertypeDes="出库单";
	    	dateDes="出库";
	    	ABC="C";
	    }
          
	    if("A".equals(ABC)||"B".equals(ABC)){
	    	getCForm(ponum, f, document, titlefont, textfont, textRedfont,keyfont,
      				recordPerPage, totalPage, opertypeDes, dateDes);
	    }else{
        	  getCForm(ponum, f, document, titlefont, textfont,textRedfont, keyfont,
      				recordPerPage, totalPage, opertypeDes, dateDes);
        }
      
    	document.close(); 
    }
	public static void getCForm(List<String> ponum, StoAppInfoForm f,
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
		    Paragraph head1 = new Paragraph(printBlank(80)+title,titlefont);
		    document.add(head1);
		    
		    String date=dateDes+"日期:"+DateUtil.formatDate(f.getCurrDate());
		    String formNo=dateDes+"单号:"+f.getFormNo();
		    
		    Paragraph head2 = new Paragraph(printBlank(20)+date+printBlank(140)+formNo,keyfont);
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
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
		    cell.setBorderColor(new Color(255, 0, 0));
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("规格",keyfont));
			cell.setColspan(5);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);
		
			cell  = new Cell(new Paragraph("单位",keyfont));
			cell.setColspan(2);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("数量",keyfont));
			cell.setColspan(4);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);    	
		
			cell  = new Cell(new Paragraph("单价",keyfont));
			cell.setColspan(3);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);        
			
			
			cell  = new Cell(new Paragraph("金额",keyfont));
			cell.setColspan(6);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);           	
			
			
			cell  = new Cell(new Paragraph("用途或原因",keyfont));
			cell.setColspan(7);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);      	
    	
			
			int max=0;
			if(ponum.size()<=recordPerPage){
				max=ponum.size();
			}else{
				if((j+1)<totalPage){
					max=(j+1)*10;
				}else {
					max=ponum.size();
				}
			}
			 
    	for(int i=10*j;i<max;i++){

    		Font fixfont=null;
    		
    		fixfont=textfont;
    		
        	cell  = new Cell(new Paragraph("密钥卡",fixfont));
        	cell.setColspan(6);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
            cell.setBorderColor(new Color(255, 0, 0));
        	table.addCell(cell);
        	
        	cell=new Cell(new Paragraph(ponum.get(i), textfont));
        	cell.setColspan(5);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);

        	cell=new Cell(new Paragraph(ponum.get(i), textfont));
        	cell.setColspan(2);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);
        	
        	cell=new Cell(new Paragraph(ponum.get(i), textfont));
        	cell.setColspan(4);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);    	

        	cell=new Cell(new Paragraph(ponum.get(i), textfont));
        	cell.setColspan(3);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);        
        	
        	
        	cell=new Cell(new Paragraph(ponum.get(i), textfont));
        	cell.setColspan(6);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);           	
        	
        	if(f.getOperationType()==31){
        		cell=new Cell(new Paragraph(ponum.get(i), textfont));
        	}else{
        		cell=new Cell(new Paragraph(ponum.get(i), textfont));        	}
        	cell.setColspan(7);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);      	
        	
     	}

        
    	document.add(table);
      	 Paragraph foot11 = new Paragraph(printBlank(20)+"制单人"+"___________________"+printBlank(50)+"领物人"+"_____________________",keyfont);
         document.add(foot11);
         
         j++;
         Paragraph foot12 = new Paragraph(printBlank(20)+"页码: "+j+" / "+totalPage+printBlank(100)+"Printed from Secretkey System.版本: 1.0",keyfont);
         document.add(foot12);
         j--;
         
         HeaderFooter footer11=new HeaderFooter(foot11, true);
         footer11.setAlignment(HeaderFooter.ALIGN_BOTTOM);
         HeaderFooter footer12=new HeaderFooter(foot12, true);
         footer12.setAlignment(HeaderFooter.ALIGN_BOTTOM);
        }

		
	}
	public static void getAForm(List<String> ponum, StoAppInfoForm f,
			Document document, Font titlefont, Font textfont,Font textRedfont,  Font keyfont,
			int recordPerPage, int totalPage, String opertypeDes, String dateDes)
			throws DocumentException, BadElementException,
			MalformedURLException, IOException {
		for(int j=0;j<totalPage;j++){
            document.newPage();
    	
		    //create page number
		    String pageNo=leftPad("页码: "+j+1+" / "+totalPage,615);
		    Paragraph pageNumber=new Paragraph(pageNo, keyfont) ;
		    document.add(pageNumber);
		
		    //create title image
		    Image jpeg=Image.getInstance("E:\\logo.png");
		    jpeg.setAlignment(Image.ALIGN_LEFT);
		    jpeg.scaleAbsolute(60, 30);
		    document.add(jpeg);
		

		    String title="密钥卡系统产品"+opertypeDes;
		    Paragraph head1 = new Paragraph(printBlank(80)+title,titlefont);
		    document.add(head1);
		    
		    String date=dateDes+"日期:"+DateUtil.formatDate(f.getCurrDate());
		    String formNo=dateDes+"单号:"+f.getFormNo();
		    
		    Paragraph head2 = new Paragraph(printBlank(20)+date+printBlank(120)+formNo,keyfont);
		    document.add(head2);		    
		    Chunk ctitle=new Chunk(printBlank(320),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.UNDERLINE));		
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
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
		    cell.setBorderColor(new Color(255, 0, 0));
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("来源",keyfont));
			cell.setColspan(7);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);
		
			cell  = new Cell(new Paragraph("规格",keyfont));
			cell.setColspan(5);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);
			
			cell  = new Cell(new Paragraph("单位",keyfont));
			cell.setColspan(2);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);    	
		
			cell  = new Cell(new Paragraph("数量",keyfont));
			cell.setColspan(4);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);        
			
			
			cell  = new Cell(new Paragraph("单价",keyfont));
			cell.setColspan(3);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);           	
			
			
			cell  = new Cell(new Paragraph("金额",keyfont));
			cell.setColspan(6);
		    cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
		    cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
			table.addCell(cell);      	
    	
			
			int max=0;
			if(ponum.size()<=recordPerPage){
				max=ponum.size();
			}else{
				if((j+1)<totalPage){
					max=(j+1)*10;
				}else {
					max=ponum.size();
				}
			}
			Font fixfont=null;	 
			for(int i=10*j;i<max;i++){
	    		if(f.getOperationType()==62){
	    			fixfont=textfont;
	    		}else{
	    			fixfont=textRedfont;
	    		}
	    	cell=new Cell(new Paragraph(ponum.get(i), fixfont)); 
	    	cell.setColspan(6);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
            cell.setBorderColor(new Color(255, 0, 0));
        	table.addCell(cell);
        	
	    	cell=new Cell(new Paragraph(ponum.get(i), fixfont)); 
        	cell.setColspan(7);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);

	    	cell=new Cell(new Paragraph(ponum.get(i), fixfont)); 
        	cell.setColspan(5);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);
        	
	    	cell=new Cell(new Paragraph(ponum.get(i), fixfont)); 
        	cell.setColspan(2);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);    	

	    	cell=new Cell(new Paragraph(ponum.get(i), fixfont)); 
        	cell.setColspan(4);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);        
        	
        	
	    	cell=new Cell(new Paragraph(ponum.get(i), fixfont)); 
        	cell.setColspan(3);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);           	
        	
        	
	    	cell=new Cell(new Paragraph(ponum.get(i), fixfont)); 
        	cell.setColspan(6);
            cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);  
            cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);  
        	table.addCell(cell);      	
        	
     	}

    	 Paragraph foot11 = new Paragraph(printBlank(50)+"制单人"+"__________________________"+printBlank(100)+"交物人"+"_____________________",keyfont);
         document.add(foot11);
         Paragraph foot12 = new Paragraph("Printed from Secretkey System."+printBlank(100)+"版本: 1.0",keyfont);
         document.add(foot12);
         
         HeaderFooter footer11=new HeaderFooter(foot11, true);
         footer11.setAlignment(HeaderFooter.ALIGN_BOTTOM);
         HeaderFooter footer12=new HeaderFooter(foot12, true);
         footer12.setAlignment(HeaderFooter.ALIGN_BOTTOM);
   
        
    	document.add(table);
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
    public static void add(List<String> list,int num){
        for(int i=0;i<num;i++){
            list.add("test"+i);
        }
    }
}
