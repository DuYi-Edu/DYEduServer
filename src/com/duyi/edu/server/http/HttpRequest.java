package com.duyi.edu.server.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String type;
    private String protocol;
    private String url;
    private String path;
    private Map<String, String> params;
    private String host;
    private String connection;
    private String cacheControl;
    private String userAgent;
    private String accept;
    private String accpetEncoding;
    private String accpetLanguage;
    private String cookie;
    private String referer;
    private String pragma;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAccpetEncoding() {
        return accpetEncoding;
    }

    public void setAccpetEncoding(String accpetEncoding) {
        this.accpetEncoding = accpetEncoding;
    }

    public String getAccpetLanguage() {
        return accpetLanguage;
    }

    public void setAccpetLanguage(String accpetLanguage) {
        this.accpetLanguage = accpetLanguage;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getPragma() {
        return pragma;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "type='" + type + '\'' +
                ", protocol='" + protocol + '\'' +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", params=" + params +
                ", host='" + host + '\'' +
                ", connection='" + connection + '\'' +
                ", cacheControl='" + cacheControl + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", accept='" + accept + '\'' +
                ", accpetEncoding='" + accpetEncoding + '\'' +
                ", accpetLanguage='" + accpetLanguage + '\'' +
                ", cookie='" + cookie + '\'' +
                ", referer='" + referer + '\'' +
                ", pragma='" + pragma + '\'' +
                '}';
    }

    public static void build(HttpRequest httpRequest, String key, String value) {
        switch (key) {
            case "type" : httpRequest.setType(value);break;
            case "protocol" : httpRequest.setProtocol(value);break;
            case "url" : httpRequest.setUrl(value);break;
            case "path" : httpRequest.setPath(value);break;
            case "params" : buildParams(value);break;
            case "host" : httpRequest.setHost(value);break;
            case "connection" : httpRequest.setConnection(value);break;
            case "cacheControl" : httpRequest.setCacheControl(value);break;
            case "userAgent" : httpRequest.setUserAgent(value);break;
            case "accept" : httpRequest.setAccept(value);break;
            case "accpetEncoding" : httpRequest.setAccpetEncoding(value);break;
            case "accpetLanguage" : httpRequest.setAccpetLanguage(value);break;
            case "cookie" : httpRequest.setCookie(value);break;
            case "referer" : httpRequest.setReferer(value);break;
            case "pragma" : httpRequest.setPragma(value);break;
            default:{
                break;
            }
        }
    }

    private static Map<String, String> buildParams(String params) {
        Map<String, String> result = new HashMap<>();
        String[] paramSet = params.split("&");
        for (String s : paramSet) {
            String[] kv = s.split("&");
            if (kv.length != 2) {
                continue;
            }
            result.put(kv[0], kv[1]);
        }
        return result;
    }

}
