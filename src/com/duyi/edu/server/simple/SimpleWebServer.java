package com.duyi.edu.server.simple;

import com.duyi.edu.server.common.DYWebService;
import com.duyi.edu.server.common.http.HttpRequest;
import com.duyi.edu.server.common.http.HttpResponse;
import com.duyi.edu.server.common.log.LogService;
import com.duyi.edu.server.simple.service.CommonRequestService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class SimpleWebServer implements DYWebService {

    private static LogService log = new LogService();

    @Override
    public void init() {

    }

    @Override
    public void action(HttpRequest httpRequest, HttpResponse httpResponse) {
        try {
            CommonRequestService.action(httpRequest, httpResponse);
        } catch (IOException e) {
            try {
                e.printStackTrace(new PrintStream(log.getOutputStream(), true));
            } catch (FileNotFoundException e1) {
            }
        }
    }

}
