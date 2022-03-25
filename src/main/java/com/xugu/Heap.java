package com.xugu;

import com.xugu.permission.LoadPermission;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class Heap {

    private static Connection conn;
    private static LoadPermission loadPermission = new LoadPermission();
    public static void main(String[] args) {

        System.out.println("xms:"+Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("xmx:"+Runtime.getRuntime().maxMemory()/1024/1024);
        try {
            Thread.sleep(100000000);
        }catch (Exception e){

        }
        try {
        Class.forName("com.xugu.cloudjdbc.Driver");
        Properties info = new Properties();
        info.put("user", "SYSDBA");
        info.put("password", "SYSDBA");
        conn = DriverManager.getConnection("jdbc:xugu://192.168.2.139:5138/SYSTEM",info);

        ResultSet st=conn.createStatement().executeQuery("select 1");
        while (st.next()){
            System.out.println(st.getString(1));
        }
            String sqlPermission = loadPermission.getDatabasePermission(conn, "SYSDBA");
            System.out.println(sqlPermission);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
