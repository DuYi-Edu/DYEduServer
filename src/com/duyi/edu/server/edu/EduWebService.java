package com.duyi.edu.server.edu;

import com.duyi.edu.server.common.DYWebService;
import com.duyi.edu.server.common.http.HttpRequest;
import com.duyi.edu.server.common.http.HttpResponse;
import com.duyi.edu.server.common.log.LogService;
import com.duyi.edu.server.edu.sql.DBUtil;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EduWebService implements DYWebService {

    LogService log = new LogService();

    @Override
    public void init() {

    }

    @Override
    public void action(HttpRequest httpRequest, HttpResponse httpResponse) {
        Connection connection = DBUtil.getConn();
        try {
            Statement statement = connection.createStatement();
            String sql = httpRequest.getParams().get("sql");
            if ("query".equals(httpRequest.getParams().get("method"))) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    System.out.println(resultSet.getRow());
                }
            } else {
                statement.execute(sql);
            }
        } catch (Exception e) {
            try {
                e.printStackTrace(new PrintWriter(log.getOutputStream(), true));
            } catch (FileNotFoundException e1) {
            }
        }
    }

}
