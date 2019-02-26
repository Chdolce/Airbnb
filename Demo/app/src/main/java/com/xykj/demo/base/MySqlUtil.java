package com.xykj.demo.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlUtil {
    //openConnecttion是连接数据库
    public static Connection openConnection(String url, String user, String password){
        Connection connection = null;
        try {
            final String DRIVER_NAME = "com.maysql.jabc.Driver";
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            connection = null;
        }catch (SQLException e){
            connection = null;
        }
        return connection;
    }

    //查询数据库
    public static void query(Connection connection, String sql){
        if(connection == null){
            return;
        }

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet != null && resultSet.first()){
                int idColumnIndex = resultSet.findColumn("id");
                int nameColumnIndex = resultSet.findColumn("name");
                while (!resultSet.isAfterLast()){
                    System.out.println("----------------");
                    System.out.println("id"+resultSet.getString(idColumnIndex));
                    System.out.println("name"+resultSet.getString(nameColumnIndex));
                    resultSet.next();

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
            }catch (SQLException sqle){

            }
        }
    }
    //执行mysql语句的函数
    public static boolean execSQL(Connection connection, String sql){
        boolean execResult = false;
        if(connection == null){
            return execResult;
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
            if(statement != null){
                execResult = statement.execute(sql);
            }
        }catch (SQLException e){
            execResult = false;
        }
        return execResult;
    }

}
