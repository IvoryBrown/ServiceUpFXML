package com.service.setting.export;



import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.service.device.Device;

import javafx.collections.ObservableList;

public class PdfGeneration {

	 public void pdfGeneration(String fileName, String sg) {
	        Document document = new Document();
	        try {
	            //Céges logó
	        	String s ="F:/Teszt/";
	            PdfWriter.getInstance(document, new FileOutputStream(s+fileName + ".pdf"));
	            document.open();
	            Image image1 = Image.getInstance(getClass().getResource("/com/image/irisz.png"));
	            image1.scaleToFit(200, 170);
	            image1.setAbsolutePosition(180f, 730f);
	            document.add(image1);
	            
	            //Sortörések
	            document.add(new Paragraph("\n\n\n\n\n\n\n"));

	            //Táblázat
	            float[] columnWidths = {3, 4, 2};
	            PdfPTable table = new PdfPTable(columnWidths);
	            table.setWidthPercentage(100);
	            PdfPCell cell = new PdfPCell(new Phrase("KontaktLista"));
	            cell.setBackgroundColor(GrayColor.GRAYWHITE);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setColspan(4);
	            table.addCell(cell);
	            
	            table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
	            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell("Sorszám");
	            table.addCell("Vezetéknév");
	            table.setHeaderRows(1);
	            
	            table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
	            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            
	             table.addCell("55"+sg);
	            System.out.println("PDf " + sg);
	            
	            document.add(table);
	            
	 
	            //Aláírás
	            Chunk signature = new Chunk("\n\n Generálva  alkalmazás segítségével.");
	            Paragraph base = new Paragraph(signature);
	            document.add(base);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        document.close();
	    }


}
