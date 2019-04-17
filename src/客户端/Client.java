package 客户端;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client implements Runnable{  
	boolean run = false;// 线程执行的标志
	int baifen= 0;
		JFrame barFrame=new JFrame();
		JPanel pnl=new JPanel();
		JProgressBar bar=new JProgressBar();
		Color color[]= {new Color(252,63,0),new Color(252,126,0),new Color(252,23,166),new Color(252,189,0),new Color(252,252,0)
				,new Color(189,252,0),new Color(126,252,0),new Color(23,252,109),new Color(0,252,252),new Color(0,126,252)};
  
   private FileInputStream fis;
   private ObjectOutputStream output; 
   private ObjectInputStream input; 
   private String message = ""; 
   private String chatServer;
   private Socket client; 
   public Client( String host )
   {
      chatServer = host;
   } 
   public void runClient() 
   {
      try 
      {
         connectToServer(); 
         getStreams();
      } 
      catch ( EOFException eofException ) 
      {
      } 
      catch ( IOException ioException ) 
      {
      } 
    
   } 
   private void connectToServer() throws IOException
   {      
      client = new Socket( InetAddress.getByName( chatServer ), 6666  );
   } 

   private void getStreams() throws IOException
   {
      output = new ObjectOutputStream( client.getOutputStream() );      
      output.flush();
      input = new ObjectInputStream( client.getInputStream() );
   }
   private void closeConnection() 
   {
      try 
      {
         output.close();
         input.close(); 
         client.close(); 
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
   }
  public void sendData(Object object)
   {
      try
      {
  
         output.writeObject(object);
        
         output.flush();
      } 
      catch ( IOException ioException )
      {
    	  System.out.println(ioException.toString());
      }
   } 
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
public Doc searchDoc(String iD) throws ClassNotFoundException, IOException {
	// TODO Auto-generated method stub
	sendData("Search_Doc");
	sendData(iD);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	return ( Doc ) input.readObject(); 
}
public boolean insertDoc(String iD, String creator, Timestamp timestamp, String description, String filename) throws ClassNotFoundException, IOException {
	sendData("Insert_Doc");
	sendData(new Doc(iD,creator,timestamp,description,filename));
	return( boolean ) input.readObject(); 
}
public User searchUser(String name) throws ClassNotFoundException, IOException {
	// TODO Auto-generated method stub
	sendData("Search_User");
	sendData(name);
	return ( User ) input.readObject(); 
}
public User searchUser(String name, String password) throws ClassNotFoundException, IOException {
	sendData("Search_User with password");
	sendData(name);
	sendData(password);
	return ( User ) input.readObject(); 
}
public boolean updateUser(String name, String password, String role) throws ClassNotFoundException, IOException {
	sendData("Update_User");
	sendData(name);
	sendData(password);
	sendData(role);
	// TODO Auto-generated method stub
   return( boolean ) input.readObject(); 
}
public boolean insertUser(String name, String password, String role) throws ClassNotFoundException, IOException {
	// TODO Auto-generated method stub
	sendData("Insert_User");
	sendData(name);
	sendData(password);
	sendData(role);
   return( boolean ) input.readObject(); 
}
public boolean deleteUser(String name) throws ClassNotFoundException, IOException {
	// TODO Auto-generated method stub
	sendData("Delete_User");
	sendData(name);
   return( boolean ) input.readObject(); 
}
public ArrayList<User> Alluser() throws ClassNotFoundException, IOException {
	// TODO Auto-generated method stub
	sendData("Get_User");
   return(ArrayList<User> ) input.readObject(); 
}
public ArrayList<Doc> GetNowFilelist(boolean choose, String text) throws ClassNotFoundException, IOException {
	sendData("Get_File");
	sendData(choose);
	sendData(text);
	ArrayList<Doc> alldoc=(ArrayList<Doc> ) input.readObject();
	ArrayList<Doc> array=new ArrayList<Doc>();
	if(choose) array=alldoc;
	else 
		for(int i=0;i<alldoc.size();i++) {
		if(alldoc.get(i).getID().contains(text))
			array.add(alldoc.get(i));
	}
	return array;
	// TODO Auto-generated method stub
}
public boolean Up_File(String filepath, String name) throws IOException {
	// TODO Auto-generated method stub
	sendData("Up_File");
	sendData(name);
	 Thread t=new Thread(this);//多线程实现
		t.start();
		
	try {
        try {
            File file =new File(filepath);
            fis =new FileInputStream(file);
            output.writeUTF(file.getName());
            output.flush();
            output.writeLong(file.length());
            output.flush();
             run=true;
            //传输文件
            byte[] sendBytes =new byte[1024];
            int length =0,all=0;
            while((length = fis.read(sendBytes,0, sendBytes.length)) >0){
                all+=length;
            	output.write(sendBytes,0, length);
            	output.flush();
            	baifen=(int) (all*1.0*100/file.length());
            	
            }
            run=false;
            return true;
        }catch (Exception e) {
        	run=false;
        	return false;
        }finally{
            if(fis !=null)
                fis.close();
        }
    }catch (Exception e) {
    	run=false;
    	return false;
    }
	
}
public boolean Down_File(String name) throws IOException {
	// TODO Auto-generated method stub
	sendData("Down_File");
	sendData(name);
	baifen=0;
	Thread t=new Thread(this);//多线程实现
	t.start();
	long fileLength = input.readLong();
	FileOutputStream  fos =new FileOutputStream(new File("./Download./" + name));                
    byte[] sendBytes =new byte[1024];
    int transLen =0;
    run=true;
    while(true){
        int read =0;
        read = input.read(sendBytes);
        if(read == -1)
            break;
        transLen += read;
        fos.write(sendBytes,0, read);
        fos.flush();
        baifen=(int) ( transLen*100/fileLength);
        if(baifen==100) break;
    }
    run=false;
	return true;
}
public boolean Change_icon(String filepath, String name) {
	// TODO Auto-generated method stub
	sendData("Change_icon");
	sendData(name);
	return false;
}
@Override
public void run() {
	// TODO 自动生成的方法存根
	barFrame.setUndecorated(true);
	barFrame.setResizable(false);
	barFrame.setSize(300,40);
	Dimension size=bar.getSize();
	Rectangle rect=new Rectangle(0,0,size.width,size.height);
	bar.setStringPainted(true);
	bar.setBackground(new Color(240,255,255));
	pnl.add(bar);
	barFrame.setLocationRelativeTo(null);
	barFrame.setContentPane(pnl);
	String path=null;
	String newPath;
	barFrame.setVisible(true);
	while (true) {
		if (run) {
			size=bar.getSize();
		    rect=new Rectangle(0,0,size.width,size.height);
			bar.setValue(baifen);
			bar.paintImmediately(rect);
			if (baifen >= 100) {
				run = false;
				barFrame.setVisible(false);
			}
			else
				bar.setForeground(color[baifen/10]);
		}
		else 
		barFrame.setVisible(false);
            try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			barFrame.setVisible(false);
		}

	}
}
	
  
}