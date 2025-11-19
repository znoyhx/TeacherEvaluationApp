package com.stu;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/db01?useSSL=false&serverTimezone=Asia/Shanghai";
        String usere = "root";
        String pass = "znoyhx";
        Connection conn = DriverManager.getConnection(url, usere, pass);
        System.out.println("连接成功: " + conn);
        conn.close();
    }
}
