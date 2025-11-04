package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import java.io.*;
import org.owasp.encoder.Encode;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] sanitizedBody;

    public XSSRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        String body = getBody(request);
        String cleaned = sanitizeJsonBody(body);
        this.sanitizedBody = cleaned.getBytes(StandardCharsets.UTF_8);
    }

    private String getBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

    private String sanitizeJsonBody(String body) {
        if (body == null || body.isBlank()) return body;
        // Sanitiza contenido HTML dentro del JSON usando Encode.forXmlContent
        return Encode.forXmlContent(body);
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.sanitizedBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}
