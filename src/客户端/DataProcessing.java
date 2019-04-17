package 客户端;

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

	public static Doc searchDoc(String ID) throws SQLException {
		  
		  try {
			return ClientTest.application.searchDoc(ID);
		} catch (ClassNotFoundException | IOException e) {
			return null;
		}	
	}
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
			try {
				return ClientTest.application.insertDoc(ID, creator,timestamp,description,  filename);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				return false;
			}
	} 
	
	public static User searchUser(String name) throws SQLException{

		try {
			return ClientTest.application.searchUser( name);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static User searchUser(String name, String password) throws SQLException {
		
		try {
			return ClientTest.application.searchUser( name,password);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{
		try {
			System.out.println("更新");
			return ClientTest.application.updateUser( name,password,role);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{
		try {
			return ClientTest.application.insertUser( name,password,role);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public static boolean deleteUser(String name) throws SQLException{
		
		try {
			return ClientTest.application.deleteUser( name);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}	
                 
public static ArrayList<User> Alluser() {
	try {
		return ClientTest.application.Alluser();
	} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		return null;
	}
	}
	
}
