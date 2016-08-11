package com.yly.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Header;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

public class PdfMaker {

    
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
        
        //Create Document Instance
        Document document=new Document();
        
        //add Chinese font
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        
        //Font headfont=new Font(bfChinese,10,Font.BOLD);
        Font keyfont=new Font(bfChinese,8,Font.BOLD);
        Font textfont=new Font(bfChinese,8,Font.NORMAL);
        Font titlefont=new Font(bfChinese,10,Font.HELVETICA);
        
        //Create Writer associated with document
        PdfWriter.getInstance(document, new FileOutputStream(new File("E:\\POReceiveReport.pdf")));
        
        document.open();
        
        //Seperate Page controller
        int recordPerPage=10;
        int fullPageRequired=ponum.size()/recordPerPage;
        int remainPage=ponum.size()%recordPerPage>1?1:0;
        int totalPage=fullPageRequired+remainPage;
        
        for(int j=0;j<totalPage;j++){
            document.newPage();
            
            //create page number
            String pageNo=leftPad("ҳ��: "+(j+1)+" / "+totalPage,615);
            Paragraph pageNumber=new Paragraph(pageNo, keyfont) ;
            document.add(pageNumber);
            
            //create title image
            Image jpeg=Image.getInstance("E:\\logo.png");
            jpeg.setAlignment(Image.ALIGN_LEFT);
            jpeg.scaleAbsolute(60, 30);
            document.add(jpeg);
            
            String title="��Կ��ϵͳ��Ʒ���ⵥ";
            Chunk ctitle=new Chunk(title,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.UNDERLINE));
            document.add(ctitle);
             
            //header information
            Table tHeader=new Table(2);
            float[] widthsHeader={2f,3f};
            tHeader.setWidths(widthsHeader);
            tHeader.setWidth(100);
            tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            String compAdd="��Դ�и��¼�����������ҵ�����66��";
            String company="������죨��Դ�����޹�˾";
            String vendor="V006";
            String vendorName="��ɽ��¬��������޹�˾";
            String ccn="FHH";
            String mas_loc="FHH";
            String delivery_note="20130718001";
            String receive_date="20130718";
            String dept="H11";
            String asn="0123456789";
            
            
            Cell c1Header=new Cell(new Paragraph("��ַ��"+compAdd,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new Cell(new Paragraph("��Ӧ�̣�"+vendor,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new Cell(new Paragraph("��˾��"+company,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new Cell(new Paragraph("��Ӧ�̹�����"+vendorName,keyfont));
            tHeader.addCell(c1Header);
            c1Header = new Cell(new Paragraph("CCN:   "+ccn+"    Master Loc:   "+mas_loc,keyfont));
            tHeader.addCell(c1Header);
            c1Header = new Cell(new Paragraph("�ͻ����: "+delivery_note+"                             �ͻ�����: "+receive_date,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new Cell(new Paragraph("Dept��"+dept,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new Cell(new Paragraph("ASN#��"+asn,keyfont));
            tHeader.addCell(c1Header);
            document.add(tHeader);
            
            //record header field
            Table t=new Table(5);
            float[] widths={1.5f,1f,1f,1.5f,1f};
            t.setWidths(widths);
            t.setWidth(100);
            t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Cell c1 = new Cell(new Paragraph("PO#",keyfont));
            t.addCell(c1);
            c1 = new Cell(new Paragraph("Line",keyfont));
            t.addCell(c1);
            c1 = new Cell(new Paragraph("Part#",keyfont));
            t.addCell(c1);
            c1 = new Cell(new Paragraph("Description",keyfont));
            t.addCell(c1); 
            c1 = new Cell(new Paragraph("Origin",keyfont));
            t.addCell(c1); 
            
            //calculate the real records within a page ,to calculate the last record number of every page
            int maxRecordInPage= j+1 ==totalPage ? (remainPage==0?recordPerPage:(ponum.size()%recordPerPage)):recordPerPage;
            
            for(int i=j*recordPerPage;i<((j*recordPerPage)+maxRecordInPage);i++){
                Cell c2=new Cell(new Paragraph(ponum.get(i), textfont));
                t.addCell(c2);
                c2=new Cell(new Paragraph(line.get(i), textfont));
                t.addCell(c2);
                c2=new Cell(new Paragraph(part.get(i), textfont));
                t.addCell(c2);
                c2=new Cell(new Paragraph(description.get(i), textfont));
                t.addCell(c2);
                c2=new Cell(new Paragraph(origin.get(i), textfont));
                t.addCell(c2);
            }
            document.add(t);
            
            if(j+1==totalPage){

                Paragraph foot11 = new Paragraph("�ļ�ֻ��  Foster ��؛��"+printBlank(150)+"__________________________",keyfont);
                document.add(foot11);
                Paragraph foot12 = new Paragraph("Printed from Foster supplier portal"+printBlank(134)+company+printBlank(40)+"�汾: 1.0",keyfont);
                document.add(foot12);
                
                HeaderFooter footer11=new HeaderFooter(foot11, true);
                footer11.setAlignment(HeaderFooter.ALIGN_BOTTOM);
                HeaderFooter footer12=new HeaderFooter(foot12, true);
                footer12.setAlignment(HeaderFooter.ALIGN_BOTTOM);
            }
        }
        document.close();
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
    
    public static void add(List<String> list,int num){
        for(int i=0;i<num;i++){
            list.add("test"+i);
        }
    }
    
    public static String printBlank(int tmp){
          String space="";
          for(int m=0;m<tmp;m++){
              space=space+" ";
          }
          return space;
    }

}