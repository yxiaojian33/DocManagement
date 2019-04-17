package 文件操作;
import 客户端.ClientTest;

import java.io.IOException;
import java.sql.*;
public class FileOpt {  
 public boolean copy(String filepath,String name,int i){//0，上传，下载。1更换头像
	 try {
		if(i==0) {
			if(!name.equals("")) {
			
				return ClientTest.application.Up_File(filepath,name);
			
		}
		else 		return ClientTest.application.Down_File(filepath);
		}
		else 
		return ClientTest.application.Change_icon(filepath,name);
	 } catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
		

}  

