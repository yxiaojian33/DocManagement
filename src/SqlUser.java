
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

public class SqlUser extends XiaoJian{
    public SqlUser() throws  Exception{super();  }
    boolean put(String key,User user){
       	try {
           stmt=conn.createStatement();
           String sql ;
    	 if (!containsKey(key)) sql = "insert into dbo.XiaoJianTab values(?,?,?,?) ";  
    	 else {sql="update dbo.XiaoJianTab set nameid = ?,name= ?,password=?,role=? where nameid='"+key+"'";
    	 
    	 System.out.println("执行命令update dbo.XiaoJianTab set password='"+user.getPassword()+"'where nameid='"+key+"'");
    	 }
    	   PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
    	   pst.setString(1, key);  
    	   pst.setString(2, user.getName()); 
    	   pst.setString(3, user.getPassword());
    	   pst.setString(4, user.getRole());
    	   pst.executeUpdate();  
    	   pst.close();  
       	}catch(Exception e) {
       		return false;
       	}
       	return true;
    }
    public boolean containsKey(String namekey){//对数据库进行数据查询
        try {
            rs=stmt.executeQuery("select *from XiaoJianTab where nameid='"+namekey+"'");
           if(rs.next()) {
            return true;}
           else return false;
        } catch (SQLException e) {
            return false;
        }
        
    }
	public void remove(String namekey) {
		// TODO 自动生成的方法存根
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
		}
		String sql = "delete from XiaoJianTab where nameid='"+namekey+"'";  
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
		}
	}
	public User get(String namekey) {
		containsKey(namekey);
		String name = null;
		String pwd = null;
		String role = null;
		try {
			name=rs.getString("name");
			pwd=rs.getString("password");
			role=rs.getString("role");
		}catch(Exception e) {
		}
		if (role.equalsIgnoreCase("administrator"))
		return new Administrator(name,pwd,role);
		else if (role.equalsIgnoreCase("operator"))
			return new Operator(name,pwd,role);
		else
			return new Browser(name,pwd,role);
		
	}
	public ArrayList<User> getUser() {
		ArrayList<User> user=new ArrayList<User>();
		 try {
		   rs = stmt.executeQuery("select name,password,role from XiaoJianTab");
		   while(rs.next()){
			  String name=rs.getString("name");
			  String pwd=rs.getString("password");
			  String role=rs.getString("role");
			  if (role.equalsIgnoreCase("administrator"))
					user.add( new Administrator(name,pwd,role));
					else if (role.equalsIgnoreCase("operator"))
						user.add( new Operator(name,pwd,role));
					else
						user.add(new Browser(name,pwd,role));
		       }
		   }catch (SQLException e) {
					// TODO 自动生成的 catch 块
				}
		return user;}
}
