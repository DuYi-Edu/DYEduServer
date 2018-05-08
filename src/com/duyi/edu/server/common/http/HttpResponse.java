package com.duyi.edu.server.common.http;

import java.io.OutputStream;

public class HttpResponse {

    private String protocol = "HTTP/1.1";
    private String code;
    private String status;
    private String server = "DuYi-Edu Server/1.0";
    private String contentType;

    private OutputStream outputStream;

    public HttpResponse (OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
