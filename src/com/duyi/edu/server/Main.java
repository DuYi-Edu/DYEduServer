package com.duyi.edu.server;

import com.duyi.edu.server.config.ConfigService;
import com.duyi.edu.server.log.LogService;
import com.duyi.edu.server.socket.SocketService;

import java.io.IOException;

public class Main {

    public static LogService log = new LogService();

    public static void main(String[] args) throws Exception {
        //初始化配置
        ConfigService.init();
        //初始化LOG服务
        Thread logTh = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogService.init();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        logTh.start();
        //初始化服务
        Thread serverTh = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketService.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        serverTh.start();
        log.info("DY Edu Server start ... ");
    }

}
