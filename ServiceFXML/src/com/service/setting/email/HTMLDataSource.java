package com.service.setting.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;

import javax.activation.DataSource;

public class HTMLDataSource implements DataSource {
	private String html;
	private static String deviceNumber;
	private static String deviceName;
	private static String deviceManufacturer;
	private static String deviceRepairLocation;
	private static String deviceStatusz;
	private static String deviceNewMachine;
	private static String deviceAdministrator;
	private static String deviceTechnicalPerson;
	private static String devicePriorit;
	private static String deviceReferences;
	private static String deviceErrorDescription;
	private static String deviceErrorCorrection;
	private static String deviceAddDate;
	private static String deviceCompletedDate;
	private static String deviceSoftver;
	
	public HTMLDataSource(String deviceNumber, String deviceName, String deviceManufacturer) {
		this.deviceNumber = deviceNumber;
		this.deviceName = deviceName;
		this.deviceManufacturer = deviceManufacturer;
	}
	public HTMLDataSource(String htmlString) {
		html = htmlString;
	}

	public InputStream getInputStream() throws IOException {
		if (html == null)
			throw new IOException("Null HTML");
		return new ByteArrayInputStream(html.getBytes());
	}

	public OutputStream getOutputStream() throws IOException {
		throw new IOException("This DataHandler cannot write HTML");
	}

	public String getContentType() {
		return "text/html";
	}

	public String getName() {
		return "JAF text/html dataSource to send e-mail only";
	}

	public static String getHTMLContent() {

		String eszkozAz = "8211000";
		String eszkoz = "Szünetmentes tápegység";
		String gyarto = "ASUS";
		String javitas = "Szeged szervíz";
		String allapot = "";
		String ujgep = "igen";
		String ugyintezo = "Balogh Lajos";
		String technikus = "Lajos Lajos";
		String priorit = "Alap";
		String hivatkozasSzam = "S012323";
		String hibaLerIras = "S012323 sdsdds sd sd dds dsd csdv  c sdc sdc sc,c sdc  clsdc s csd sdc sdkc ksdc sd ks cdsdk  ";
		String valosHiba = "S012323 sdsdds sd sd dds dsd csdv  c sdc sdc sc,c sdc  clsdc s csd sdc sdkc ksdc sd ks cdsdk  ";
		String bejelentes = "2018-01-01";
		String hatarido = "2018-01-02";
		String elkeszult = "2018-01-03";
		String szoftver = "igen";

		String html = "<html><hr />"
				+ "<h1 style=\"text-align: center;\"><span style=\"color: whitesmoke; background-color: #616161;\">PcVipService</span></h1>"
				+ "<hr />" + "<p>&nbsp;</p>"
				+ "<table style=\"height: 48px; margin-left: auto; margin-right: auto; width: 612px;\" bgcolor=\"#61a2b1\">"
				+ "<tbody>" + "<tr>"
				+ "<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">Eszk&ouml;z Azonos&iacute;t&oacute;:</span></td>"
				+ "<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceNumber + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Eszk&ouml;z:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ eszkoz + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Gy&aacute;rt&oacute;:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ gyarto + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">Jav&iacute;t&aacute;s helye:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ javitas + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">&Aacute;llapot:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ allapot + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">&Uacute;j g&eacute;p:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ ujgep + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">&Uuml;gyint&eacute;ző:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ ugyintezo + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">Technikus:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ technikus + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">Priorit&aacute;s:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ priorit + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hivatkoz&aacute;si sz&aacute;m:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ hivatkozasSzam + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hiba Le&iacute;r&aacute;s:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ hibaLerIras + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Val&oacute;s hiba:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ valosHiba + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Bejelent&eacute;s:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ bejelentes + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hat&aacute;ridő:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ hatarido + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Elk&eacute;sz&uuml;lt:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ elkeszult + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Szoftver:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ szoftver + "</span></td>" + "</tr>" + "</tbody>" + "</table>" + "<p>&nbsp;</p>" + "<hr />"
				+ "<p><em>Ezt az emailt a PcVIpService k&uuml;ldte " + LocalDateTime.now() + ".&nbsp;</em></p>"
				+ "<p><em>Erre az emailre ne &iacute;rj vissza!</em></p>" + "</html>";
		return html;

	}

}