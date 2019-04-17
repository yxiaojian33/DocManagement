import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.sql.*;
public class FileOpt implements Runnable{  
	boolean run = false;// 线程执行的标志
	int baifen= 0;
	    String upPath="./DocLib";
	    String downPath="./Download";
		JFrame barFrame=new JFrame();
		JPanel pnl=new JPanel();
		JProgressBar bar=new JProgressBar();
		Color color[]= {new Color(252,63,0),new Color(252,126,0),new Color(252,23,166),new Color(252,189,0),new Color(252,252,0)
				,new Color(189,252,0),new Color(126,252,0),new Color(23,252,109),new Color(0,252,252),new Color(0,126,252)};
		
		
 boolean copy(String filepath,String name,int i){//0，上传，下载。1更换头像
	 Thread t=new Thread(this);//多线程实现
		t.start();
	    run=true;
		String path=null;
		String newPath;
		if(i==0) {if(!name.equals("")) {
			path=upPath;
			newPath=path+"/"+name;
		}
		else { path=downPath;
		newPath=path+"/"+new File(filepath).getName();
		}
		}
		else newPath="./PersonIcon/"+name;
		try {  
				int bytesum = 0;  
				int byteread = 0;  
				File oldfile = new File(filepath);  
				if (oldfile.exists()) { //文件存在时  
					InputStream inStream = new FileInputStream(filepath); //读入原文件  
					FileOutputStream fs = new FileOutputStream(newPath);  
					byte[] buffer = new byte[14440000];  
					while ( (byteread = inStream.read(buffer)) != -1) {  
						bytesum += byteread; //字节数 文件大小  
						fs.write(buffer, 0, byteread);  
						baifen=(int) (bytesum*1.0*100/oldfile.length());
					}  
					inStream.close();  
					fs.close(); 
					
				} 
				run=false;
				barFrame.setVisible(false);
				return true;
			}  
			catch (Exception e) {  
				run=false;
				return false;
			}
			
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
				if (baifen == 100) {
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

