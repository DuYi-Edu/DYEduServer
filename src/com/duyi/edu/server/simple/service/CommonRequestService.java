package com.duyi.edu.server.simple.service;

import com.duyi.edu.server.common.config.ConfigName;
import com.duyi.edu.server.common.config.ConfigService;
import com.duyi.edu.server.common.http.HttpRequest;
import com.duyi.edu.server.common.http.HttpResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CommonRequestService {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");

    public static void action(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        writeFile(httpRequest.getPath(), httpResponse);
    }

    private static void writeResponseHead (HttpResponse httpResponse) throws IOException {
        httpResponse.getOutputStream().write(httpResponse.getProtocol().getBytes());
        httpResponse.getOutputStream().write((" " + httpResponse.getCode()).getBytes());
        httpResponse.getOutputStream().write((" " + httpResponse.getStatus() + "\n").getBytes());
        httpResponse.getOutputStream().write(("Server: " + httpResponse.getServer() + "\n").getBytes());
        httpResponse.getOutputStream().write(("Date: " + dateFormat.format(new Date()) + "\n").getBytes());
        httpResponse.getOutputStream().write("\n".getBytes());
    }

    private static void writeFile(String path, HttpResponse httpResponse) throws IOException {
        if ("/".equals(path)) {
            String defaultPage = ConfigService.getConfig(ConfigName.DEFAULT_PAGE);
            if (defaultPage != null) {
                path = "/" + defaultPage;
            }
        }
        File file = new File(ConfigService.getConfig(ConfigName.BASE_PATH, "./views") + path);
        if (file.isFile()) {
            httpResponse.setCode("200");
            httpResponse.setStatus("OK");
            writeResponseHead(httpResponse);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[64];
            for ( ;; ) {
                int k = fileInputStream.read(bytes);
                httpResponse.getOutputStream().write(Arrays.copyOf(bytes, k));
                if (k < 64) {
                    break;
                }
            }
        } else {
            httpResponse.setCode("404");
            httpResponse.setStatus("Not Found");
            writeResponseHead(httpResponse);
            String page404 = ConfigService.getConfig(ConfigName.PAGE_404);
            if (page404 != null) {
                FileInputStream fileInputStream = new FileInputStream(new File(ConfigService.getConfig(ConfigName.BASE_PATH, "./views") + "/" + page404));
                byte[] bytes = new byte[64];
                for ( ;; ) {
                    int k = fileInputStream.read(bytes);
                    httpResponse.getOutputStream().write(Arrays.copyOf(bytes, k));
                    if (k < 64) {
                        break;
                    }
                }
            }
        }
    }
}
