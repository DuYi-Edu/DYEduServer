package com.duyi.edu.server.socket;

import com.duyi.edu.server.config.ConfigName;
import com.duyi.edu.server.config.ConfigService;
import com.duyi.edu.server.http.HttpHandler;
import com.duyi.edu.server.http.HttpRequest;
import com.duyi.edu.server.http.HttpResponse;
import com.duyi.edu.server.log.LogService;
import com.duyi.edu.server.service.CommonRequestService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by changlifeng on 2018/1/17.
 */
public class SocketService {

    private static ServerSocket s;
    private static LogService log = new LogService();

    private static ExecutorService executorService;

    public static void start() throws IOException {

        int corePoolSize = Integer.parseInt(ConfigService.getConfig(ConfigName.CORE_THREAD_SIZE));
        int maximunPoolSize = Integer.parseInt(ConfigService.getConfig(ConfigName.MAX_THREAD_SIZE));
        int capacity = Integer.parseInt(ConfigService.getConfig(ConfigName.WAIT_QUEUE_SIZE));

        executorService = new ThreadPoolExecutor(corePoolSize, maximunPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(capacity),
                new ThreadPoolExecutor.DiscardPolicy());

        Socket socket = null;
        try {
            s = new ServerSocket(Integer.parseInt(ConfigService.getConfig(ConfigName.PORT)));
            while (true) {
                socket = s.accept();
                executorService.execute(new ServerThread(socket));
            }
        } catch (IOException e) {
            log.error(e.toString());
        }

    }
}

class ServerThread extends Thread {

    private static LogService log = new LogService();
    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String request = getRequest(socket);
            HttpRequest httpRequest = HttpHandler.analysis(request);
            HttpResponse httpResponse = new HttpResponse(socket.getOutputStream());
            CommonRequestService.action(httpRequest, httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                socket.getInputStream().close();
//            } catch (IOException e) {
//                try {
//                    e.printStackTrace(new PrintStream(log.getOutputStream(), true));
//                } catch (FileNotFoundException e1) {
//                }
//            }
            try {
                if (!socket.isOutputShutdown()) {
                    socket.getOutputStream().close();
                }
            } catch (IOException e) {
                try {
                    e.printStackTrace(new PrintStream(log.getOutputStream(), true));
                } catch (FileNotFoundException e1) {
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                try {
                    e.printStackTrace(new PrintStream(log.getOutputStream(), true));
                } catch (FileNotFoundException e1) {
                }
            }
        }
    }

    private String getRequest(Socket socket) throws IOException {
        StringBuilder result = new StringBuilder();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[64];
        while (true) {
            int k = inputStream.read(bytes);
            if (k < bytes.length) {
                break;
            }
            result.append(new String(bytes));
        }
        return result.toString();
    }
}
