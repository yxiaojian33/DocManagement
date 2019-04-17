package 客户端;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import 文件操作.FileOpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
public class Operator extends User implements Serializable,ActionListener,DocumentListener,ListSelectionListener{
	Dimension dim;
	JFrame AdFrame=new JFrame();
	JButton btn[]={new JButton("上传文件"),new JButton("下载文件"),new JButton("文件列表"),new JButton("个人中心"),new JButton("注销登录"),
			new JButton("退出")};
	JPanel pnl;
	JPanel pnl2;
	Color LightCyan=new Color(225,255,255);//淡青
	Color Azure=new Color(240,255,255);//蔚蓝
	Color choosed=new Color(135,206,250);//青
	ArrayList nowfileList=new ArrayList();
	JTextField textFileName;
	JTable fileList;
	JScrollPane scroll;
	Document userdoc;
	Document filedoc;
	 User user;
	 Doc doc;
	 File cfile;
	public Operator(String name,String password,String role){
		super(name,password,role);	
		AdFrame=new JFrame("尊敬的"+this.getName()+",欢迎进入录入员界面\n");
		AdFrame.setSize(600, 400);
		AdFrame.setResizable(false);//设置大小不可改变
		AdFrame.setLocationRelativeTo(null);
		pnl=new JPanel();
		pnl.setSize(200, 400);
		pnl.setBackground(LightCyan);
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
		pnl.setLayout(fl);
		dim= new Dimension(200,30);
		for(int i=0;i<6;i++) {
			if(i>=4) dim= new Dimension(90,30);
			btn[i].setPreferredSize(dim);
			btn[i].setBackground(Azure);
			btn[i].addActionListener(this);
			pnl.add(btn[i]);
		}
		AdFrame.add(pnl);
		pnl2=new JPanel();
		pnl2.setSize(370, 400);
		pnl2.setBackground(Azure);
		AdFrame.add(pnl2);//pnl2需要多次重绘
	}

	@Override
	public void showMenu() {
		AdFrame.setVisible(true);
		changePwdUi();
		btn[3].setBackground(choosed);
	}
    @SuppressWarnings("resource")

	private void shareFile()  {
		// TODO 自动生成的方法存根
    	cfile=null;
    	pnl2.removeAll();
    	FlowLayout fl = new FlowLayout(FlowLayout.RIGHT, 0, 0);
    	pnl2.setLayout(fl);
    	JLabel lab[]= {new JLabel("设置文件名"),new JLabel("文件描述"),new JLabel("文件id"),new JLabel("已选文件"),
    			new JLabel("")
    			};
    	JTextField text[]=new JTextField[3];
    	dim=new Dimension(370,30);
    	for(int i=0;i<5;i++) {
    		lab[i].setPreferredSize(dim);
    		pnl2.add(lab[i]);
    		if(i<3) {
    			text[i]=new JTextField();
    			text[i].setPreferredSize(dim);
    			pnl2.add(text[i]);
    		}
    	}
    	JPanel choosefile=new JPanel();
    	choosefile.setBackground(Azure);
    	dim=new Dimension(370,40);
    	choosefile.setPreferredSize(dim);
    	JButton choosebtn=new JButton("选择文件");
    	JButton sure=new JButton("上传");
    	choosefile.add(choosebtn);
    	choosefile.add(sure);
    	pnl2.add(choosefile);
    	JPanel noticepnl=new JPanel();
		noticepnl.setBackground(Azure);
		noticepnl.setPreferredSize(new Dimension(370,40));
		JLabel notice = new JLabel();
		noticepnl.add(notice);
		pnl2.add(noticepnl);
		choosebtn.addActionListener(new ActionListener(){
			@Override
				public void actionPerformed(ActionEvent arg0) {
				cfile=null;
				lab[4].setText("");
				JFileChooser f=new JFileChooser();
				f.setPreferredSize(new Dimension(370,320));
				f.showOpenDialog(null);
				if(f.isFileSelectionEnabled())
			     cfile=f.getSelectedFile();
				if(cfile!=null) lab[4].setText(cfile.getPath());
				}		
			});
		sure.addActionListener(new ActionListener(){
			@Override
				public void actionPerformed(ActionEvent arg0) {
				notice.setForeground(Color.RED);
				String docname;
				String docdescrib;
				String docid;
				if(cfile==null) notice.setText("请选择文件:");
				else { 
					docid=text[2].getText();
					if(docid.equals("")) notice.setText("请设置文件id:");
					else {
						try {
							if(DataProcessing.searchDoc(docid)!=(null)) {
								notice.setText("该id已经存在，请重新设置:");
							}
							else {	
							docname=cfile.getName();
							 docdescrib="暂无描述";
							 if(!text[0].getText().equals(""))
								 docname=text[0].getText();
							 if(!text[1].getText().equals(""))
								 docdescrib=text[1].getText();
							 Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
							 if(new FileOpt().copy(cfile.getPath(), docname,0)&&DataProcessing.insertDoc(docid, getName(), timestamp, docdescrib,docname))
	
								 {
								  notice.setForeground(Color.BLUE);
								 notice.setText("上传成功");
								 
								}
							 else {
								 notice.setForeground(Color.RED);
								 notice.setText("上传失败");
								 
							 }
							}
						} catch (SQLException e) {
							// TODO 自动生成的 catch 块
							notice.setText(e.getMessage());
						}
				}	
				}

			}
				
			});
		AdFrame.repaint();
		AdFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		for(int i=0;i<6;i++) {
			btn[i].setBackground(Azure);
		}
		((JButton)e.getSource()).setBackground(choosed);
		switch(((JButton)e.getSource()).getText()) {
		case "上传文件":
			shareFile();
			break;
		case "文件列表":
			try {
				nowfileList=GetNowFilelist(true,null);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
			}
			DisplayFileList();
			AdFrame.repaint();
			AdFrame.setVisible(true);
			break;
		case "下载文件":
			nowfileList=null;
			try {
				nowfileList=GetNowFilelist(true,null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
			}
			DisplayFileList();
			textFileName = new JTextField();
		    dim = new Dimension(370,30);
		    textFileName.setPreferredSize(dim);
			pnl2.add(textFileName);
			AdFrame.add(pnl2);
			AdFrame.repaint();
			AdFrame.setVisible(true);
			AdFrame.repaint();
			AdFrame.setVisible(true);
			filedoc=textFileName.getDocument();
			filedoc.addDocumentListener(this);
			fileList.getSelectionModel().addListSelectionListener(this);
			break;
		case "个人中心":
			changePwdUi();
				break;
		case "退出":
		System.exit(0);
		break;
		case "注销登录":
			AdFrame.dispose();
			Login returnLogin=new Login();
			break;
	}
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		if(arg0.getDocument().equals(filedoc))
			UpdateFileList();
		
	}
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getDocument().equals(filedoc))
			UpdateFileList();
	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO 自动生成的方法存根
	
		if(arg0.getDocument().equals(filedoc))
			UpdateFileList();
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(arg0.getValueIsAdjusting()) {//一次点击事件
		// TODO 自动生成的方法存根
			if(arg0.getSource().equals(fileList.getSelectionModel()))
				{doc=(Doc) nowfileList.get(fileList.getSelectedRow());
				downUi(doc);}
			}
		}
	void UpdateFileList() {
		nowfileList=new ArrayList();
		try {
			nowfileList=GetNowFilelist(false,textFileName.getText());
		} catch (SQLException e) {
		}
		 DefaultTableModel lmodel=(DefaultTableModel) fileList.getModel();
		 lmodel.getDataVector().clear();
		 for(int i=0;i<nowfileList.size();i++)
			 lmodel.addRow(((Doc) nowfileList.get(i)).DocObject());
		 fileList.repaint();
	}

	private void changePwdUi() {
		// TODO 自动生成的方法存根
		pnl2.removeAll();
		FlowLayout fl = new FlowLayout(FlowLayout.RIGHT, 10, 10);
		pnl2.setLayout(fl);
		ImageIcon icon = new ImageIcon(".\\PersonIcon\\"+this.getName()+".png");
		JLabel	labIcon = new JLabel(icon);
		    dim = new Dimension(370,100);//设宽一点不然会乱序
			labIcon.setPreferredSize(dim);
			pnl2.add(labIcon);
		JPanel mUser=new JPanel();
		mUser.setPreferredSize(new Dimension(370,30));
		mUser.setBackground(Azure);
		JLabel labName = new JLabel("用户名："+this.getName());
		mUser.add(labName);
		JPanel mRole=new JPanel();
		mRole.setPreferredSize(new Dimension(370,30));
		mRole.setBackground(Azure);
		JLabel labrole = new JLabel("职称："+this.getRole());
		mRole.add(labrole);
		pnl2.add(mUser);
		pnl2.add(mRole);
		JTextField textpword=new JTextField();
		textpword.setPreferredSize(new Dimension(370,30));
		pnl2.add(textpword);
		JPanel pwdpnl=new JPanel();
		pwdpnl.setPreferredSize(new Dimension(370,40));
		pwdpnl.setBackground(Azure);
		JButton pwdbtn=new JButton("修改密码");
		pwdpnl.add( pwdbtn);
		pnl2.add(pwdpnl);
		JPanel noticepnl=new JPanel();
		noticepnl.setBackground(Azure);
		noticepnl.setPreferredSize(new Dimension(370,40));
		JLabel notice1 = new JLabel();
		noticepnl.add(notice1);
		pnl2.add(noticepnl);
		AdFrame.repaint();
		AdFrame.setVisible(true);
		labIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根	
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				ChangeIcon();
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}});
		pwdbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				String npwd=textpword.getText();
				if(npwd.equals(""))
				{
					notice1.setForeground(Color.RED);
					notice1.setText("请设置密码！");
				} else
					try {
						if(changeSelfInfo(npwd)) {
							notice1.setForeground(Color.BLUE);
							notice1.setText("修改成功");
						}
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						notice1.setForeground(Color.RED);
						notice1.setText(e.getMessage());
					}
			}});
	}
	private void DisplayFileList() {
		// TODO 自动生成的方法存根
		AdFrame.remove(pnl2);
	    pnl2=new JPanel();
		pnl2.setBackground(Azure);
		pnl2.setSize(370, 400);
		String[] title= {"文件id","上传者","文件描述","文件名"};
		DefaultTableModel model=new  DefaultTableModel(title,0);
		   for(int i=0;i<nowfileList.size();i++) {
			   model.addRow(((Doc) nowfileList.get(i)).DocObject());
		    }
	    fileList=new JTable(model);
	    fileList.validate();
		fileList.setPreferredSize(new Dimension(370,300));
	    scroll=new JScrollPane(fileList);
		pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 10));
			scroll.setPreferredSize(new Dimension(370,270));
		pnl2.add(scroll);
		AdFrame.add(pnl2);
		AdFrame.repaint();
	}
	}


