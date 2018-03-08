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

public class PdfGeneration {

	public void pdfGeneration(String fileName, String clientName, String clientAddress, String clientPhone,
			String clientEmail, String clientNumber, String deviceName, String deviceNumber, String deviceManufacturer,
			String deviceSalesBuying, String deviceAddDate, String deviceEndDate, String devicePassword,
			String deviceAccesssory, String deviceInjury, String deviceErrorDescription, String deviceDataRecovery,
			String deviceAdministrator) {
		Document document = new Document(PageSize.A4);
		try {
			String s = "F:/Teszt/";
			PdfWriter.getInstance(document, new FileOutputStream(s + fileName + ".pdf"));
			document.open();
			Font font1 = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
			Font font2 = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
			Font font2b = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);
			Font font3 = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
			Font font3b = new Font(Font.FontFamily.COURIER, 13, Font.BOLDITALIC);

			Chunk reportTitle = new Chunk("\nÁTVÉTELI ELISMERVÉNY", font1);
			Chunk companyTitle = new Chunk("Írisz Holding Kft.", font2);
			Chunk companyAddressTitle = new Chunk("\nSzékhely: 1044, Budapest, Óradna utca 5.", font3);
			Chunk companySitesTitle = new Chunk("\nTelephely: 6722, Szeged, Tisza Lajos krt. 47.", font3);
			Chunk companyContactTitle = new Chunk(
					"\nWeb: www.iriszholding.hu, E-mail: uzletszeged@iriszholding.hu \n Tel.: +36-30/640-5949\n", font3);
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
			table.setWidthPercentage(100);
			table.setSpacingBefore(0f);
			table.setSpacingAfter(0f);
			Chunk report = new Chunk("                         Ügyfél Adatai \n", font3b);
			Chunk reportClientName = new Chunk("           Ügyfél neve: ", font2);
			Chunk reportSetClientName = new Chunk(clientName, font2b);
			Chunk reportClientAddress = new Chunk("\n\n                      Címe: ", font2);
			Chunk reportSetClientAddress = new Chunk(clientAddress, font2b);
			Chunk reportClientPhone = new Chunk("\n\n         Telefonszám: ", font2);
			Chunk reportSetClientPhone = new Chunk(clientPhone, font2b);
			Chunk reportClientEmail = new Chunk("\n\n                     E-mail: ", font2);
			Chunk reportSetClientEmail = new Chunk(clientEmail, font2b);
			Chunk reportClientNumber = new Chunk("\n\n             Azonosító: ", font2);
			Chunk reportSetClientNumber = new Chunk(clientNumber, font2b);

			Phrase phrase1 = new Phrase();
			phrase1.add(report);
			phrase1.add(reportClientName);
			phrase1.add(reportSetClientName);
			phrase1.add(reportClientAddress);
			phrase1.add(reportSetClientAddress);
			phrase1.add(reportClientPhone);
			phrase1.add(reportSetClientPhone);
			phrase1.add(reportClientEmail);
			phrase1.add(reportSetClientEmail);
			phrase1.add(reportClientNumber);
			phrase1.add(reportSetClientNumber);
			table.addCell(phrase1);
			document.add(table);

			PdfPTable table1 = new PdfPTable(1);
			table1.setWidthPercentage(100);
			table1.setSpacingBefore(0f);
			table1.setSpacingAfter(0f);

			Chunk report2 = new Chunk("                        Munkalap Adatai \n", font3b);
			Chunk reportDeviceName = new Chunk("      Eszköz típusa: ", font2);
			Chunk reportSetDeviceName = new Chunk(deviceName, font2b);
			Chunk reportDeviceNumber = new Chunk("\n\nEszköz azonosító: ", font2);
			Chunk reportSetDeviceNumber = new Chunk(deviceNumber, font2b);
			Chunk reportDeviceManufacturer = new Chunk("\n\n                  Gyártó: ", font2);
			Chunk reportSetDeviceManufacturer = new Chunk(deviceManufacturer, font2b);
			Chunk reportDeviceSalesBuying = new Chunk("\n\n Vásárlási dátum: ", font2);
			Chunk reportSetDeviceSalesBuying = null;
			if (deviceSalesBuying != null) {
				reportSetDeviceSalesBuying = new Chunk(deviceSalesBuying, font2b);
			}
			Chunk reportDeviceAddDate = new Chunk("\n\n   Felvétel dátum: ", font2);
			Chunk reportSetDeviceAddDate = new Chunk(deviceAddDate, font2b);
			Chunk reportDeviceEndDate = new Chunk("\n\n  Vállalási dátum: ", font2);
			Chunk reportSetDeviceEndDate = new Chunk(deviceEndDate, font2b);
			Chunk reportDevicePassword = new Chunk("\n\n                   Jelszó: ", font2);
			Chunk reportSetDevicePassword = new Chunk(devicePassword, font2b);
			Chunk reportDeviceAccesssory = new Chunk("\n\n           Tartozékok: ", font2);
			Chunk reportSetDeviceAccesssory = new Chunk(deviceAccesssory, font2b);
			Chunk reportDeviceInjury = new Chunk("\n\n                Sérülés: ", font2);
			Chunk reportSetDeviceInjury = new Chunk(deviceInjury, font2b);
			Chunk reportDeviceErrorDescription = new Chunk("\n\n           Hiba leírás: ", font2);
			Chunk reportSetDeviceErrorDescription = new Chunk(deviceErrorDescription, font2b);
			Chunk reportDeviceDataRecovery = new Chunk("\n\n       Adatmentés: ", font2);
			Chunk reportSetDeviceDataRecovery = new Chunk(deviceDataRecovery, font2b);
			Chunk reportDeviceAdministrator = new Chunk("\n\n             Ügyintéző: ", font2);
			Chunk reportSetDeviceAdministrator = new Chunk(deviceAdministrator, font2b);

			Phrase phrase2 = new Phrase();
			phrase2.add(report2);
			phrase2.add(reportDeviceName);
			phrase2.add(reportSetDeviceName);
			phrase2.add(reportDeviceNumber);
			phrase2.add(reportSetDeviceNumber);
			phrase2.add(reportDeviceManufacturer);
			phrase2.add(reportSetDeviceManufacturer);
			phrase2.add(reportDeviceSalesBuying);
			phrase2.add(reportSetDeviceSalesBuying);
			phrase2.add(reportDeviceAddDate);
			phrase2.add(reportSetDeviceAddDate);
			phrase2.add(reportDeviceEndDate);
			phrase2.add(reportSetDeviceEndDate);
			phrase2.add(reportDevicePassword);
			phrase2.add(reportSetDevicePassword);
			phrase2.add(reportDeviceAccesssory);
			phrase2.add(reportSetDeviceAccesssory);
			phrase2.add(reportDeviceInjury);
			phrase2.add(reportSetDeviceInjury);
			phrase2.add(reportDeviceErrorDescription);
			phrase2.add(reportSetDeviceErrorDescription);
			phrase2.add(reportDeviceDataRecovery);
			phrase2.add(reportSetDeviceDataRecovery);
			phrase2.add(reportDeviceAdministrator);
			phrase2.add(reportSetDeviceAdministrator);
			table1.addCell(phrase2);
			document.add(table1);

			document.add(new Paragraph("\n\n\n"));

			PdfPTable table0 = new PdfPTable(3);
			PdfPCell cell = new PdfPCell(new Paragraph("         Írisz Holding Kft."));
			cell.setBorderWidthTop(2f);
			cell.setBorderWidthRight(0f);
			cell.setBorderWidthBottom(0f);
			cell.setBorderWidthLeft(0f);
			cell.setNoWrap(false);
			PdfPCell cell1 = new PdfPCell(new Paragraph("          Ügyfél aláírása"));
			cell1.setBorderWidthTop(2f);
			cell1.setBorderWidthRight(0f);
			cell1.setBorderWidthBottom(0f);
			cell1.setBorderWidthLeft(0f);
			cell1.setNoWrap(false);
			PdfPCell cell2 = new PdfPCell();
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthBottom(0f);
			cell2.setBorderWidthLeft(0f);
			cell2.setNoWrap(false);
			table0.addCell(cell);
			table0.addCell(cell2);
			table0.addCell(cell1);
			document.add(table0);

			Chunk chunk1 = new Chunk(
					"Ezt az elismervényt kérjük őrizze meg, \n csak ennek ellenében áll módunkban a javított készüléket kiadni!"
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

			// Aláírás
			Chunk signature = new Chunk("\n Generálva  alkalmazás segítségével.");
			Paragraph base = new Paragraph(signature);
			document.add(base);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {

            document.close();

        }
	}

}
