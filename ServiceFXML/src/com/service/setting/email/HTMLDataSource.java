package com.service.setting.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;

import javax.activation.DataSource;

public class HTMLDataSource implements DataSource {
	private String html;
	protected static String deviceNumber;
	private static String deviceName;
	private static String deviceManufacturer;
	private static String deviceRepairLocation;
	private static String deviceStatus;
	private static String deviceNewMachine;
	private static String deviceAdministrator;
	private static String deviceTechnicalPerson;
	private static String devicePriorit;
	private static String deviceReferences;
	private static String deviceErrorDescription;
	private static String deviceErrorCorrection;
	private static String deviceDeliveryDate;
	private static String deviceAddDate;
	private static String deviceEndDate;
	private static String deviceCompletedDate;
	private static String deviceSoftver;
	private static String deviceClientName;
	private static String deviceCompanyName;
	private static String deviceStatusz;
	private static String deviceComment;
	private static String deviceOperatingSystem;

	public HTMLDataSource() {

	}

	public HTMLDataSource(String htmlString) {
		html = htmlString;
	}

	public void setHTMLCompom(String deviceCompanyName, String deviceClientName, String deviceNumber, String deviceName,
			String deviceManufacturer, String deviceRepairLocation, String deviceStatus, String deviceNewMachine,
			String deviceAdministrator, String deviceTechnicalPerson, String devicePriorit, String deviceReferences,
			String deviceStatusz, String deviceErrorDescription, String deviceErrorCorrection, String deviceComment,
			String deviceDeliveryDate, String deviceAddDate, String deviceEndDate, String deviceCompletedDate,
			String deviceSoftver, String deviceOperatingSystem) {

		HTMLDataSource.deviceCompanyName = deviceCompanyName;
		HTMLDataSource.deviceClientName = deviceClientName;
		HTMLDataSource.deviceNumber = deviceNumber;
		HTMLDataSource.deviceName = deviceName;
		HTMLDataSource.deviceManufacturer = deviceManufacturer;
		HTMLDataSource.deviceRepairLocation = deviceRepairLocation;
		HTMLDataSource.deviceStatus = deviceStatus;
		HTMLDataSource.deviceNewMachine = deviceNewMachine;
		HTMLDataSource.deviceAdministrator = deviceAdministrator;
		HTMLDataSource.deviceTechnicalPerson = deviceTechnicalPerson;
		HTMLDataSource.devicePriorit = devicePriorit;
		HTMLDataSource.deviceReferences = deviceReferences;
		HTMLDataSource.deviceStatusz = deviceStatusz;
		HTMLDataSource.deviceErrorDescription = deviceErrorDescription;
		HTMLDataSource.deviceErrorCorrection = deviceErrorCorrection;
		HTMLDataSource.deviceComment = deviceComment;
		HTMLDataSource.deviceDeliveryDate = deviceDeliveryDate;
		HTMLDataSource.deviceAddDate = deviceAddDate;
		HTMLDataSource.deviceEndDate = deviceEndDate;
		HTMLDataSource.deviceCompletedDate = deviceCompletedDate;
		HTMLDataSource.deviceSoftver = deviceSoftver;
		HTMLDataSource.deviceOperatingSystem = deviceOperatingSystem;

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

		String html = "<html><hr />"
				+ "<h1 style=\"text-align: center;\"><span style=\"color: whitesmoke; background-color: #616161;\">PcVipService</span></h1>"
				+ "<hr />" + "<p>&nbsp;</p>"
				+ "<table style=\"height: 48px; margin-left: auto; margin-right: auto; width: 612px;\" bgcolor=\"#61a2b1\">"
				+ "<tbody>" + "<tr>"
				+ "<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">&Uuml;gyf&eacute;l:</span></td>"
				+ "<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceClientName + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">C&eacute;g:</span></td>"
				+ "<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceCompanyName + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">Eszk&ouml;z Azonos&iacute;t&oacute;:</span></td>"
				+ "<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceNumber + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Eszk&ouml;z:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceName + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Gy&aacute;rt&oacute;:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceManufacturer + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\"><span style=\"color: whitesmoke;\">Jav&iacute;t&aacute;s helye:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #424242;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ deviceRepairLocation + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">&Aacute;llapot:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ deviceStatus + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\"><span style=\"color: whitesmoke;\">&Uacute;j g&eacute;p:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #424242;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ deviceNewMachine + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">&Uuml;gyint&eacute;ző:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ deviceAdministrator + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\"><span style=\"color: whitesmoke;\">Technikus:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #424242;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ deviceTechnicalPerson + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">Priorit&aacute;s:</span>&nbsp;</td>"
				+ "<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"
				+ devicePriorit + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hivatkoz&aacute;si sz&aacute;m:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceReferences + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">St&aacute;tusz:</span></td>"
				+ "<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceStatusz + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hiba Le&iacute;r&aacute;s:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceErrorDescription + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Val&oacute;s hiba:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceErrorCorrection + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">Eszk&ouml;zről megjegyz&eacute;s:</span></td>"
				+ "<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceComment + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Kisz&aacute;ll&aacute;s:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceDeliveryDate + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Bejelent&eacute;s:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceAddDate + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hat&aacute;ridő:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceEndDate + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Elk&eacute;sz&uuml;lt:</span></td>"
				+ "<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceCompletedDate + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Szoftver:</span></td>"
				+ "<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceSoftver + "</span></td>" + "</tr>" + "<tr>"
				+ "<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">Oper&aacute;ci&oacute;s rendszer:</span></td>"
				+ "<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"
				+ deviceOperatingSystem + "</span></td>" + "</tr>" + "</tbody>" + "</table>" + "<p>&nbsp;</p>"
				+ "<hr />" + "<p><em>Ezt az emailt a PcVIpService k&uuml;ldte " + LocalDateTime.now()
				+ ".&nbsp;</em></p>" + "<p><em>Erre az emailre ne &iacute;rj vissza!</em></p>" + "</html>";
		return html;

	}

}