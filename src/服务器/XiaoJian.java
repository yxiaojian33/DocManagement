package ·þÎñÆ÷;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class XiaoJian {
	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL="jdbc:sqlserver://localhost:1433;databaseName=XiaoJian";
    String Name="sa";
    String Pwd="542542";
    Connection conn;
    Statement stmt;
    ResultSet rs;
    XiaoJian() throws  Exception{
    	 try {
    	   Class.forName(driverName);
    	   conn=DriverManager.getConnection(dbURL,Name,Pwd);
    	   stmt=conn.createStatement();
    	   }catch(Exception e) {
    		   throw new Exception(e.getMessage());
    	   }
     }
}
