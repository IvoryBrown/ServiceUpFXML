package com.service.setting.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

public class HTMLDataSource implements DataSource {
	private String html;

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
}