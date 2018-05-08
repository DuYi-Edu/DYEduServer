package com.duyi.edu.server.common;

import com.duyi.edu.server.common.config.ConfigName;
import com.duyi.edu.server.common.config.ConfigService;
import com.duyi.edu.server.container.ContainerWebService;
import com.duyi.edu.server.edu.EduWebService;
import com.duyi.edu.server.simple.SimpleWebServer;

public class WebServerPattern {

    public volatile static DYWebService webService;

    private static DYWebService getWebService (PatternEnum patternEnum) {
        if (webService == null) {
            synchronized (WebServerPattern.class) {
                if (webService == null) {
                    switch (patternEnum) {
                        case SIMPLE_WEB_SERVER : webService = new SimpleWebServer(); break;
                        case EDU_WEB_SERVER : webService = new EduWebService(); break;
                        case WEB_CONTAINER : webService = new ContainerWebService(); break;
                        default : {
                            webService = new SimpleWebServer(); break;
                        }
                    }
                }
                webService.init();
            }
        }
        return webService;
    }

    public static DYWebService getWebServer() {
        String pattern = ConfigService.getConfig(ConfigName.PATTERN, "SimpleWebServer");
        PatternEnum patternEnum = PatternEnum.findPatternEnum(pattern);
        return getWebService(patternEnum);
    }

    public enum PatternEnum {
        SIMPLE_WEB_SERVER("SimpleWebServer"), EDU_WEB_SERVER("EduWebServer"), WEB_CONTAINER("WebContainer");

        private String value;

        PatternEnum(String value) {
            this.value = value;
        }

        public static PatternEnum findPatternEnum(String value) {
            switch (value) {
                case "SimpleWebServer" : return SIMPLE_WEB_SERVER;
                case "EduWebServer" : return EDU_WEB_SERVER;
                case "WebContainer" : return WEB_CONTAINER;
                default : return SIMPLE_WEB_SERVER;
            }
        }
    }

}
