package com.duyi.edu.server.common;

import com.duyi.edu.server.common.http.HttpRequest;
import com.duyi.edu.server.common.http.HttpResponse;

public interface DYWebService {

    void init();

    void action(HttpRequest httpRequest, HttpResponse httpResponse);

}
