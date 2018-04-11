package com.service.setting.email;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSending {
	
	public EmailSending() {
		String host = "smtp.gmail.com";


		Properties props = new Properties();

		// Ha a statikus Transport.send()-et használjuk,
		// meg kell adni melyik hoston keresztül küldjünk
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		// Debug bekapcsolása
		props.put("mail.debug", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailSetting.username, EmailSetting.password);
			}
		});

		try {
			// Transport objektummal történő küldés
			Transport bus = session.getTransport("smtp");

			// Csak egyszer csatlakozik
			// Transport.send() minden küldés után kapcsolatot bont
			// Általában, SMTP-hez nem szükséges felhasználónév és jelszó
			bus.connect();
			// de ha mégis, akkor
			// bus.connect("smtpserver.yourisp.net", "username", "password");

			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(EmailSetting.from));
			InternetAddress[] address = { new InternetAddress(EmailSetting.to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("javás emailküldés");
			msg.setSentDate(new Date());

			setHTMLContent(msg);
			msg.saveChanges();
			bus.sendMessage(msg, address);

			bus.close();

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	// Egyrészes HTML-tartalom beállítása.
		// Bármilyen más típusú adat küldése hasonló
		public static void setHTMLContent(Message msg) throws MessagingException {
		String eszkozAz = "8211000";
		String eszkoz = "Szünetmentes tápegység";
		String gyarto = "ASUS";
			String javitas= "Szeged szervíz";
			String allapot= "";
			String ugyintezo= "Balogh Lajos";
			String technikus= "Lajos Lajos";
			String priorit= "Alap";
			String hivatkozasSzam= "S012323";
			String hibaLerIras= "S012323 sdsdds sd sd dds dsd csdv  c sdc sdc sc,c sdc  clsdc s csd sdc sdkc ksdc sd ks cdsdk  ";
			String valosHiba= "S012323 sdsdds sd sd dds dsd csdv  c sdc sdc sc,c sdc  clsdc s csd sdc sdkc ksdc sd ks cdsdk  ";
			String bejelentes= "2018-01-01";
			String hatarido= "2018-01-02";
			String elkeszult= "2018-01-03";
			String szoftver= "igen";
			
			String date ="2018-02-01";

			String html = "<html><hr />" + 
					"<h1 style=\"text-align: center;\"><span style=\"color: whitesmoke; background-color: #616161;\">PcVipService</span></h1>" + 
					"<hr />" + 
					"<p>&nbsp;</p>" + 
					"<table style=\"height: 48px; margin-left: auto; margin-right: auto; width: 612px;\" bgcolor=\"#61a2b1\">" + 
					"<tbody>" + 
					"<tr>" + 
					"<td style=\"width: 239px; text-align: right; background-color: #616161;\"><span style=\"color: whitesmoke;\">Eszk&ouml;z Azonos&iacute;t&oacute;:</span></td>" + 
					"<td style=\"width: 501px; text-align: left; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"+eszkozAz+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Eszk&ouml;z:</span></td>" + 
					"<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"+eszkoz+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Gy&aacute;rt&oacute;:</span></td>" + 
					"<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"+gyarto+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">Jav&iacute;t&aacute;s helye:</span>&nbsp;</td>" + 
					"<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"+javitas+"</span></td>"+
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">&Aacute;llapot:</span>&nbsp;</td>" + 
					"<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"+allapot+"</span></td>"+
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">&Uuml;gyint&eacute;ző:</span>&nbsp;</td>" + 
					"<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"+ugyintezo+"</span></td>"+
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">Technikus:</span>&nbsp;</td>" + 
					"<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"+technikus+"</span></td>"+
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\"><span style=\"color: whitesmoke;\">Priorit&aacute;s:</span>&nbsp;</td>" + 
					"<td style=\"width: 501px; background-color: #616161;\">&nbsp;<span style=\"color: whitesmoke;\">"+priorit+"</span></td>"+
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hivatkoz&aacute;si sz&aacute;m:</span></td>" + 
					"<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"+hivatkozasSzam+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hiba Le&iacute;r&aacute;s:</span></td>" + 
					"<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"+hibaLerIras+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Val&oacute;s hiba:</span></td>" + 
					"<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"+valosHiba+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Bejelent&eacute;s:</span></td>" + 
					"<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"+bejelentes+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Hat&aacute;ridő:</span></td>" + 
					"<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"+hatarido+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #616161; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Elk&eacute;sz&uuml;lt:</span></td>" + 
					"<td style=\"width: 501px; background-color: #616161;\"><span style=\"color: whitesmoke;\">&nbsp;"+elkeszult+"</span></td>" + 
					"</tr>" + 
					"<tr>" + 
					"<td style=\"width: 239px; background-color: #424242; text-align: right;\">&nbsp;<span style=\"color: whitesmoke;\">Szoftver:</span></td>" + 
					"<td style=\"width: 501px; background-color: #424242;\"><span style=\"color: whitesmoke;\">&nbsp;"+szoftver+"</span></td>" + 
					"</tr>" + 
					"</tbody>" + 
					"</table>"+
					"<p>&nbsp;</p>"+
					"<hr />"+
					"<p><em>Ezt az emailt a PcVIpService k&uuml;ldte "+date+".&nbsp;</em></p>"+
					"<p><em>Erre az emailre ne &iacute;rj vissza!</em></p>"
					+ "</html>";
			// HTMLDataSource egy belső osztály
			msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
		}
}
