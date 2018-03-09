package com.service.setting.export;

import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatingPdf {

	public void creating(String clientName, String clientZipCode, String clientSettlement, String clientAddress,
			String clientPhone, String clientNumber, String deviceNumber, String deviceSalesBuying,
			String deviceAddDate, String deviceEndDate, String deviceName, String deviceManufacturer,
			String devicePassword, String deviceAccesssory, String deviceInjury, String deviceErrorDescription,
			String deviceDataRecovery) {
		Document document = new Document(PageSize.A4);
		Font font1 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
		Font font2b = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Font font3 = new Font(Font.FontFamily.COURIER, 7, Font.BOLD);
		try {
			
			
			String s = "F:/Teszt/";
			PdfWriter.getInstance(document, new FileOutputStream(s + "zz" + ".pdf"));
			document.open();

			for (int i = 0; i < 2; i++) {

				Paragraph p1 = new Paragraph("ÁTVÉTELI  ELISMERVÉNY", font1);
				p1.setAlignment(Element.ALIGN_CENTER);
				document.add(p1);

				PdfPTable table = new PdfPTable(2);
				table.setWidthPercentage(100);
				PdfPCell customerLblCell = new PdfPCell();
				Phrase ph1 = new Phrase("Ügyfél:", font2);
				customerLblCell.addElement(ph1);
				Phrase ph2 = new Phrase(clientName, font2b);
				customerLblCell.addElement(ph2);
				Phrase ph3 = new Phrase(clientZipCode+" " + clientSettlement+"," + clientAddress, font2b);
				customerLblCell.addElement(ph3);
				Phrase ph4 = new Phrase(clientPhone, font2b);
				customerLblCell.addElement(ph4);
				Phrase ph5 = new Phrase("Azonosító: " + clientNumber, font2b);
				customerLblCell.addElement(ph5);
				table.addCell(customerLblCell);

				PdfPCell balanceLblCell = new PdfPCell();
				Phrase ph1a = new Phrase("Írisz Holding Kft.", font2);
				balanceLblCell.addElement(ph1a);
				Phrase ph1b = new Phrase("Székhely: 1044, Budapest, Óradna utca 5.", font3);
				balanceLblCell.addElement(ph1b);
				Phrase ph1c = new Phrase("Telephely: 6722, Szeged, Tisza Lajos krt. 47. +36-30/640-5949", font3);
				balanceLblCell.addElement(ph1c);
				Phrase ph1d = new Phrase("Telephely: 6230, Soltvadkert, Kossuth u. 1-3. +36-78/482-497", font3);
				balanceLblCell.addElement(ph1d);
				Phrase ph1f = new Phrase("www.iriszholding.hu", font3);
				balanceLblCell.addElement(ph1f);
				table.addCell(balanceLblCell);
				table.completeRow();
				table.setSpacingBefore(10);
				document.add(table);

				PdfPTable table1 = new PdfPTable(4);
				table1.setWidthPercentage(100);
				PdfPCell cell1 = new PdfPCell();
				Phrase ph2a = new Phrase("Eszköz azonosító", font2);
				Phrase ph2b = new Phrase(deviceNumber, font2b);
				cell1.addElement(ph2a);
				cell1.addElement(ph2b);
				table1.addCell(cell1);

				PdfPCell cell2 = new PdfPCell();
				Phrase ph3a = new Phrase("Vásárlás dátuma", font2);
				Phrase ph3b = new Phrase(deviceSalesBuying, font2b);
				cell2.addElement(ph3a);
				cell2.addElement(ph3b);
				table1.addCell(cell2);

				PdfPCell cell3 = new PdfPCell();
				Phrase ph4a = new Phrase("Felvétel dátuma", font2);
				Phrase ph4b = new Phrase(deviceAddDate, font2b);
				cell3.addElement(ph4a);
				cell3.addElement(ph4b);
				table1.addCell(cell3);

				PdfPCell cell4 = new PdfPCell();
				Phrase ph5a = new Phrase("Vállalás dátuma", font2);
				Phrase ph5b = new Phrase(deviceEndDate, font2b);
				cell4.addElement(ph5a);
				cell4.addElement(ph5b);
				table1.addCell(cell4);
				document.add(table1);

				PdfPTable table2 = new PdfPTable(new float[] { 4, 25 });
				table2.setWidthPercentage(100);

				PdfPCell cell5 = new PdfPCell();
				Phrase ph6a = new Phrase("Eszköz típusa: ", font2);
				cell5.addElement(ph6a);
				Phrase ph7a = new Phrase("Gyártó: ", font2);
				cell5.addElement(ph7a);
				Phrase ph8a = new Phrase("Jelszó: ", font2);
				cell5.addElement(ph8a);
				Phrase ph9a = new Phrase("Tartozékok: ", font2);
				cell5.addElement(ph9a);
				Phrase ph10a = new Phrase("Sérülés: ", font2);
				cell5.addElement(ph10a);
				Phrase ph12a = new Phrase("Adatmentés: ", font2);
				cell5.addElement(ph12a);
				table2.addCell(cell5);

				PdfPCell cell6 = new PdfPCell();
				Phrase ph6b = new Phrase(deviceName, font2);
				cell6.addElement(ph6b);
				Phrase ph7b = new Phrase(deviceManufacturer, font2);
				cell6.addElement(ph7b);
				Phrase ph8b = new Phrase(devicePassword, font2);
				cell6.addElement(ph8b);
				Phrase ph9b = new Phrase(deviceAccesssory, font2);
				cell6.addElement(ph9b);
				Phrase ph10b = new Phrase(deviceInjury, font2);
				cell6.addElement(ph10b);

				Phrase ph12b = new Phrase(deviceDataRecovery, font2);
				cell6.addElement(ph12b);
				table2.addCell(cell6);

				PdfPCell cell7 = new PdfPCell();
				Phrase ph11a = new Phrase("Hiba leírás: ", font2);
				cell7.addElement(ph11a);
				table2.addCell(cell7);

				PdfPCell cell8 = new PdfPCell();
				Phrase ph11b = new Phrase(deviceErrorDescription, font2);
				cell8.addElement(ph11b);
				table2.addCell(cell8);

				document.add(table2);
				document.add(new Paragraph("\n\n"));

				PdfPTable table0 = new PdfPTable(3);
				PdfPCell cell = new PdfPCell(new Paragraph("         Írisz Holding Kft.", font3));
				cell.setBorderWidthTop(2f);
				cell.setBorderWidthRight(0f);
				cell.setBorderWidthBottom(0f);
				cell.setBorderWidthLeft(0f);
				cell.setNoWrap(false);
				PdfPCell cell11 = new PdfPCell(new Paragraph("          Ügyfél aláírása", font3));
				cell11.setBorderWidthTop(2f);
				cell11.setBorderWidthRight(0f);
				cell11.setBorderWidthBottom(0f);
				cell11.setBorderWidthLeft(0f);
				cell11.setNoWrap(false);
				PdfPCell cell21 = new PdfPCell();
				cell21.setBorderWidthTop(0f);
				cell21.setBorderWidthRight(0f);
				cell21.setBorderWidthBottom(0f);
				cell21.setBorderWidthLeft(0f);
				cell21.setNoWrap(false);
				table0.addCell(cell);
				table0.addCell(cell21);
				table0.addCell(cell11);
				document.add(table0);

				Chunk chunk1 = new Chunk(
						"Ezt az elismervényt kérjük őrizze meg, csak ennek ellenében áll módunkban a javított készüléket kiadni!"
								+ "\n A merevlemezen lévő adatok tartalmáért, adatvesztésekért az Ügyfél vállalja a felelősséget! "
								+ "Kérjük mindig készítsen biztonsági másolatot adatairól. "
								+ "Az elkészült és el nem szállított készülékek után, az értesítést követő 10 napon túl nettó 200 Ft/nap "
								+ "tárolási díjat számítunk fel. A 180 (azaz egyszáznyolcvan) napot meghaladó átvételi késedelem esetén, a szerviz "
								+ "jogosult az eszköz megsemmisítésére. ",
						font3);
				Phrase phrase3 = new Phrase();
				phrase3.add(chunk1);
				Paragraph para1 = new Paragraph();
				para1.add(phrase3);
				para1.setAlignment(Element.ALIGN_CENTER);
				document.add(para1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			document.close();

		}
	}

}
