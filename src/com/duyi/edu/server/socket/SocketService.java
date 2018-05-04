package com.duyi.edu.server.socket;

import com.duyi.edu.server.config.ConfigService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by changlifeng on 2018/1/17.
 */
public class SocketService {

    private static ServerSocket s;

    public static void start() throws IOException {

        Socket socket = null;
        try {
            s = new ServerSocket(Integer.parseInt(ConfigService.getConfig("port")));
            while (true) {
                socket = s.accept();
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ServerThread extends Thread {

    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader is = new InputStreamReader(socket.getInputStream());
            PrintStream out;
            out = new PrintStream(socket.getOutputStream());

            Map<String, String> request = analyzeRequest(socket);

            action(request, out);

            is.close();
            out.flush();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void action(Map<String, String> request, PrintStream out) throws Exception {
        String refer = request.get("referer");
        normalResp(out, "./test.html");
    }

    private static void normalResp(PrintStream out, String path) throws Exception {

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type:text/html;charset:utf-8");
        out.println();

        StringBuilder page = ServerThread.getPage(path);
        out.println(page.toString());
    }

    private static void errorResp(PrintStream out) throws Exception {

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type:text/html;charset:utf-8");
        out.println();

        out.println("<html><body>Not Found</body</html>");

    }

    private Map<String, String> analyzeRequest(Socket socket) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            String temp = br.readLine();
            if (temp == null || "".equals(temp)) {
                break;
            }
            if (!temp.contains(":")) {
                continue;
            }
            int p = temp.indexOf(":");
            System.out.println(temp);
            result.put(temp.trim().substring(0, p).trim().toLowerCase(), temp.trim().substring(p + 1, temp.length()).trim());
            System.out.println(temp.trim().substring(0, p).trim().toLowerCase() + ":" + temp.trim().substring(p + 1, temp.length()).trim());
        }

        return result;
    }

    private static StringBuilder getPage(String path) throws Exception {
        StringBuilder result = new StringBuilder();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (true) {
            String temp = br.readLine();
            if (temp == null || "".equals(temp)) {
                break;
            }
            result.append(temp);
            result.append("\r\n");
        }
        br.close();
        return result;
    }
}
