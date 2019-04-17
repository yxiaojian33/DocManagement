package 服务器;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;

import 客户端.Administrator;
import 客户端.Browser;
import 客户端.Doc;
import 客户端.Operator;
import 客户端.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;

public  class DataProcessing {

	private static boolean connectToDB=false;
	public static SqlDoc docs;
	static SqlUser users;
	static {
 try {
 users=new SqlUser();
 docs=new SqlDoc();
} catch (Exception e) {
	// TODO 自动生成的 catch 块
	e.printStackTrace();
}	
	}
	public static Doc searchDoc(String ID) throws SQLException {
		if (docs.containsKey(ID)) {
			Doc temp =docs.get(ID);
			return temp;
		}
		return null;
	}
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		Doc doc;		
	
		if (docs.containsKey(ID))
			return false;
		else{
			doc = new Doc(ID,creator,timestamp,description,filename);
			if(docs.put(ID, doc))
			return true;
			else return false;
		}
	} 
	
	public static User searchUser(String name) throws SQLException{

		
		if (users.containsKey(name)) {
			return users.get(name);	
			
		}
		return null;
	}
	
	public static User searchUser(String name, String password) throws SQLException {
		
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{
		User user;
		
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{
		User user;
		
		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}
	}
	
	public static boolean deleteUser(String name) throws SQLException{
		
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
	}	
            
	public static void disconnectFromDB() {
		if ( connectToDB ){
	
			try{

			}finally{                                            
				connectToDB = false;              
			}                             
		} 
   }           
public static ArrayList<User> Alluser() {
	return users.getUser();}
	
}
