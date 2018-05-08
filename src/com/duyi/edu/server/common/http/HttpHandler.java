package com.duyi.edu.server.common.http;

import com.sun.tools.javac.util.Pair;

public class HttpHandler {

    public static HttpRequest analysis(String request) {
        System.out.println(request);
        HttpRequest result = new HttpRequest();
        String[] squares = request.split("\n\n");
        String head = squares[0];
        analysisHead(result, head);
        return result;
    }

    private static HttpRequest analysisHead(HttpRequest httpRequest, String head) {
        String[] lines = head.split("\n");

        String fstLine = lines[0];
        String[] fstLineContent = fstLine.split(" ");
        String[] paths = fstLineContent[1].split("\\?");
        HttpRequest.build(httpRequest, "type", fstLineContent[0]);
        HttpRequest.build(httpRequest, "path", paths[0]);
        HttpRequest.build(httpRequest, "protocol", fstLineContent[2]);
        HttpRequest.build(httpRequest, "params", paths.length == 2 ? paths[1] : "");

        for (int i = 1 ; i < lines.length ; i ++) {
            String temp = lines[i];
            Pair<String, String> kv = getKV(temp);
            if (kv == null) {
                continue;
            }
            HttpRequest.build(httpRequest, kv.fst, kv.snd);
        }

        return httpRequest;
    }

    private static Pair<String, String> getKV(String line) {
        int index = line.indexOf(":");
        if (index < 0) {
            return null;
        }
        String key = line.substring(0, index);
        String value = line.substring(index + 1, line.length());
        key = key.trim().toLowerCase();
        value = value.trim();
        return Pair.of(key, value);
    }

}
