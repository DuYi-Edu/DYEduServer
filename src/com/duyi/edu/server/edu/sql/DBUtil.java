package com.duyi.edu.server.edu.sql;

import com.duyi.edu.server.common.config.ConfigName;
import com.duyi.edu.server.common.config.ConfigService;
import com.duyi.edu.server.common.log.LogService;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static LogService log = new LogService();

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url;
    private static String usrName;
    private static String password;

    private static void init() {

        String host = ConfigService.getConfig(ConfigName.DB_HOST);
        String port = ConfigService.getConfig(ConfigName.DB_PORT);
        String dbName = ConfigService.getConfig(ConfigName.DB_NAME);
        String user = ConfigService.getConfig(ConfigName.DB_USER);
        String pwd = ConfigService.getConfig(ConfigName.DB_PWD);

        url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        usrName = user;
        password = pwd;

    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usrName, password);
        } catch (Exception e) {
            try {
                e.printStackTrace(new PrintStream(log.getOutputStream(), true));
            } catch (FileNotFoundException e1) {
            }
        }
        return conn;
    }

}
