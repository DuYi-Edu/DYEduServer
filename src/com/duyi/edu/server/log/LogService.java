package com.duyi.edu.server.log;

import com.duyi.edu.server.config.ConfigName;
import com.duyi.edu.server.config.ConfigService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LogService {

    private static String logPath;
    private static File logFile;
    private static LinkedBlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    private static final LogThread logThread = new LogThread();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void init() throws IOException {
        logPath = ConfigService.getConfig(ConfigName.LOG_PATH, "dy_edu_server.log");
        logFile = new File(logPath);
        FileOutputStream fileOutputStream = new FileOutputStream(logFile, true);
        fileOutputStream.close();
        logThread.run();
    }

    public void info(String info) {
        try {
            pushLog(logFormat("INFO", info));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void error(String error) {
        try {
            pushLog(logFormat("ERROR", error));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public OutputStream getOutputStream() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(logFile, true);
        return fileOutputStream;
    }

    private String logFormat(String type, String log) {
        StringBuilder logText = new StringBuilder();
        logText.append(dateFormat.format(new Date(System.currentTimeMillis())));
        logText.append(" [");
        logText.append(type);
        logText.append("] ");
        logText.append(log);
        logText.append("\n");
        return logText.toString();
    }

    private void pushLog(String log) throws InterruptedException {
        logQueue.offer(log, 10, TimeUnit.MILLISECONDS);
    }

    private static void flush(int size) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(logFile, true);
        for (int i = 0 ; i < size ; i ++) {
            fileOutputStream.write(logQueue.poll().getBytes());
        }
        fileOutputStream.close();
    }

    static class LogThread implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    if (logQueue.size() > 0) {
                        int size = logQueue.size();
                        flush(size);
                    }
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
        }
    }


}


