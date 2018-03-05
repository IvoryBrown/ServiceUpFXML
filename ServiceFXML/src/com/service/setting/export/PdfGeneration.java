package com.service.setting.export;

import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGeneration {

	public void pdfGeneration(String fileName, String clientName,String clientAddress, String clientPhone, String clientEmail) {
		Document document = new Document(PageSize.A4);
		try {
			String s = "F:/Teszt/";
			PdfWriter.getInstance(document, new FileOutputStream(s + fileName + ".pdf"));
			document.open();
			Font font1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
			Font font2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
			Font font2b = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
			Font font3 = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);

			Chunk reportTitle = new Chunk("\nÁTVÉTELI ELISMERVÉNY", font1);
			Chunk companyTitle = new Chunk("\nÍrisz Holding Kft.", font2);
			Chunk companyAddressTitle = new Chunk("\nSzékhely: 1044, Budapest Óradna utca 5.", font3);
			Chunk companySitesTitle = new Chunk("\nTelephely: 6722, Szeged Tisza L. krt. 47", font3);
			Chunk companyContactTitle = new Chunk(
					"\nWeb: www.iriszholding.hu, E-mail: uzletszeged@iriszholding.hu \n Tel: +36-30/640-5949\n", font3);
			Phrase phrase = new Phrase();
			phrase.add(companyTitle);
			phrase.add(companyAddressTitle);
			phrase.add(companySitesTitle);
			phrase.add(companyContactTitle);
			phrase.add(reportTitle);

			Paragraph para = new Paragraph();
			para.add(phrase);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);

			document.add(new Paragraph("\n"));

			PdfPTable table = new PdfPTable(1);
			table.setTotalWidth(60);
			Chunk reportClientName = new Chunk("Megrendelő neve: " , font2);
			Chunk reportSetClientName = new Chunk( clientName , font2b);
			Chunk reportClientAddress = new Chunk("\n\n                  Címe: ", font2);
			Chunk reportSetClientAddress = new Chunk(clientAddress, font2b);
			Chunk reportClientPhone = new Chunk("\n\n     Telefonszám: ", font2);
			Chunk reportSetClientPhone = new Chunk(clientPhone, font2b);
			Chunk reportClientEmail = new Chunk("  Email:", font2);
			Chunk reportSetClientEmail = new Chunk(clientEmail, font2b);
			Phrase phrase1 = new Phrase();
			phrase1.add(reportClientName);
			phrase1.add(reportSetClientName);
			phrase1.add(reportClientAddress);
			phrase1.add(reportSetClientAddress);
			phrase1.add(reportClientPhone);
			phrase1.add(reportSetClientPhone);
			phrase1.add(reportClientEmail);
			phrase1.add(reportSetClientEmail);
			table.addCell(phrase1);

			PdfPCell cell = new PdfPCell(new Paragraph("Text Text Text Text Text "));
			// cell.setBorder(Rectangle.NO_BORDER); keret nincs
			cell.setNoWrap(false);
			table.addCell(cell);

			document.add(table);

			// Aláírás
			Chunk signature = new Chunk("\n\n Generálva  alkalmazás segítségével.");
			Paragraph base = new Paragraph(signature);
			document.add(base);

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}

}
