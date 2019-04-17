
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Document;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
public class Administrator extends User implements ActionListener,DocumentListener,ListSelectionListener {
	Dimension dim;
	JFrame AdFrame=new JFrame();
	JButton btn[]={new JButton("修改用户信息"),new JButton("注册用户"),new JButton("用户列表"
			),new JButton("下载文件"),new JButton("文件列表"),new JButton("个人中心"),new JButton("注销登录"),
			new JButton("退出")};
	JPanel pnl;
	JPanel pnl2;
	Color LightCyan=new Color(225,255,255);//淡青
	Color Azure=new Color(240,255,255);//蔚蓝
	Color choosed=new Color(135,206,250);//青
	ArrayList nowuserList=new ArrayList();
	ArrayList nowfileList=new ArrayList();
	JTextField textuserName;
	JTextField textFileName;
	JList userList;
	JList fileList;
	JScrollPane scroll;
	Document userdoc;
	Document filedoc;
	User user;
	Doc doc;
Administrator(String name,String password,String role){
		super(name,password,role);
		AdFrame=new JFrame("尊敬的"+this.getName()+",欢迎进入管理员界面\n");
		AdFrame.setSize(600, 400);
		AdFrame.setResizable(false);//设置大小不可改变
		AdFrame.setLocationRelativeTo(null);
		pnl=new JPanel();
		pnl.setSize(200, 400);
		pnl.setBackground(LightCyan);
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
		pnl.setLayout(fl);
		dim= new Dimension(200,30);
		for(int i=0;i<8;i++) {
			if(i>=6) dim= new Dimension(90,30);
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
	btn[5].setBackground(choosed);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO 自动生成的方法存根
	for(int i=0;i<8;i++) {
		btn[i].setBackground(Azure);
	}
	((JButton)e.getSource()).setBackground(choosed);
	switch(((JButton)e.getSource()).getText()) {
	case "修改用户信息":
		 ReNew();
		break;
	case "注册用户":
		 addUserUi();
		break;
	case "用户列表":
		GetnowUserList(true);
		DisplayUserList();
		AdFrame.repaint();
		AdFrame.setVisible(true);
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
		fileList.addListSelectionListener(this);
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
void ReNew(){
	GetnowUserList(true);
	DisplayUserList();
    textuserName = new JTextField();
    dim = new Dimension(370,30);
	textuserName.setPreferredSize(dim);
	pnl2.add(textuserName);
	AdFrame.add(pnl2);
	AdFrame.repaint();
	AdFrame.setVisible(true);
   userdoc=textuserName.getDocument();
	userdoc.addDocumentListener(this);
	userList.addListSelectionListener(this);
}
@Override
public void changedUpdate(DocumentEvent arg0) {
	if(arg0.getDocument().equals(userdoc))
	UpdateuserList();
	if(arg0.getDocument().equals(filedoc))
		UpdateFileList();
}
@Override
public void insertUpdate(DocumentEvent arg0) {
	// TODO 自动生成的方法存根
	if(arg0.getDocument().equals(userdoc))
		UpdateuserList();
	if(arg0.getDocument().equals(filedoc))
		UpdateFileList();
}
@Override
public void removeUpdate(DocumentEvent arg0) {
	// TODO 自动生成的方法存根
	if(arg0.getDocument().equals(userdoc))
		UpdateuserList();
	if(arg0.getDocument().equals(filedoc))
		UpdateFileList();
}
@Override
public void valueChanged(ListSelectionEvent arg0) {
	if(arg0.getValueIsAdjusting()) {//一次点击事件
	// TODO 自动生成的方法存根
		if(arg0.getSource().equals(userList))
		changeUserInfoUi();
		if(arg0.getSource().equals(fileList))
		{doc=(Doc) nowfileList.get(fileList.getSelectedIndex());
			downUi(doc);}
	}
	
}
void UpdateuserList() {
	GetnowUserList(false);
	 DefaultListModel lmodel=(DefaultListModel) userList.getModel();
	 lmodel.removeAllElements();
	 for(int i=0;i<nowuserList.size();i++) {
		    lmodel.addElement(ClasstoString.ToString((User) nowuserList.get(i)));
		    }
}
void UpdateFileList() {
	nowfileList=new ArrayList();
	try {
		nowfileList=GetNowFilelist(false,textFileName.getText());
	} catch (SQLException e) {
	}
	 DefaultListModel lmodel=(DefaultListModel) fileList.getModel();
	 lmodel.removeAllElements();
	 for(int i=0;i<nowfileList.size();i++)
		    lmodel.addElement(ClasstoString.ToString((Doc) nowfileList.get(i)));
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


private void addUserUi() {
	// TODO 自动生成的方法存根
	pnl2.removeAll();
	FlowLayout fl = new FlowLayout(FlowLayout.RIGHT, 10, 10);
	pnl2.setLayout(fl);
	JPanel newUser=new JPanel();
	JPanel newpwd=new JPanel();
	newUser.setPreferredSize(new Dimension(370,40));
	newUser.setBackground(Azure);
	newpwd.setBackground(Azure);
	newpwd.setPreferredSize(new Dimension(370,40));
	JLabel labName = new JLabel("新用户名：");
	newUser.add(labName);
	JTextField textName = new JTextField();
    dim = new Dimension(250,30);
	textName.setPreferredSize(dim);
	newUser.add(textName);
	JLabel labpassword= new JLabel("设置密码：");
	JTextField textword=new JTextField();
	textword.setPreferredSize(dim);
	newpwd.add(labpassword);
	newpwd.add(textword);
	pnl2.add(newUser);
	pnl2.add(newpwd);
	JPanel chooseRole=new JPanel();
	chooseRole.setPreferredSize(new Dimension(370,40));
	chooseRole.setBackground(Azure);
	JLabel labchoose = new JLabel("选择职称：");
	chooseRole.add(labchoose);
	JRadioButton box1=new JRadioButton("录入员");//设置单选
	JRadioButton box2=new JRadioButton("浏览员");
	JRadioButton box3=new JRadioButton("管理员");
	box1.setBackground(Azure);
	box2.setBackground(Azure);
	box3.setBackground(Azure);
	ButtonGroup bg=new ButtonGroup();
	bg.add(box1);
	bg.add(box2);
	bg.add(box3);
	box1.setSelected(true);//默认的选中状态
	chooseRole.add(box1);
	chooseRole.add(box2);
	if(getName().equals("小剑")) chooseRole.add(box3);
	pnl2.add(chooseRole);
	JPanel surebtn=new JPanel();
	surebtn.setPreferredSize(new Dimension(370,40));
	surebtn.setBackground(Azure);
	JButton sure=new JButton("确定注册");
	surebtn.add(sure);
	pnl2.add(surebtn);
	JPanel noticepnl=new JPanel();
	noticepnl.setBackground(Azure);
	noticepnl.setPreferredSize(new Dimension(370,40));
	JLabel notice1 = new JLabel();
	noticepnl.add(notice1);
	pnl2.add(noticepnl);
	AdFrame.repaint();
	AdFrame.setVisible(true);
	sure.addActionListener(new ActionListener(){
		@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
			User newuser=null;
			String mname=textName.getText();
			String mpwd=textword.getText();
			String mrole=null;
			if(box1.isSelected()) mrole="operator";
			else if(box2.isSelected()) mrole="browser";
			else mrole="administrator";
			if(mname.equals("")) {
				notice1.setForeground(Color.RED);
				notice1.setText("请输入用户名！");
			}
			else if(mpwd.equals(""))
			{
				notice1.setForeground(Color.RED);
				notice1.setText("请设置密码！");
			}
			else {
			try {
				if(DataProcessing.insertUser(mname,mpwd, mrole))
				{ notice1.setForeground(Color.blue);
					notice1.setText("注册成功");}
				else {
					notice1.setForeground(Color.RED);
					notice1.setText("注册失败,该用户名已存在！");}
			} catch (SQLException e) {
				notice1.setForeground(Color.RED);
				notice1.setText(e.getMessage());
			}
			}	
			}	
		});
}
private void changeUserInfoUi() {
	// TODO 自动生成的方法存根
	 String strname=null;
	  user=null;
	 DefaultListModel lmodel=(DefaultListModel) userList.getModel();
	 try {
	 user=(User) nowuserList.get(userList.getSelectedIndex());
}catch(Exception e) {
	 }
	 if(user!=null) {
	 JFrame changePwdFrame=new JFrame();
	 changePwdFrame.setSize(250,280);
	 changePwdFrame.setLocationRelativeTo(null);
	 changePwdFrame.getContentPane().setBackground(Azure);
	 changePwdFrame.setResizable(false);
	 FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);//10像素间距
	 changePwdFrame.setLayout(fl);
	 JLabel notice=new JLabel("管理员"+this.getName()+"您正在为"+user.getName()+"修改密码:");
	 changePwdFrame.add(notice);
	JLabel labnewword= new JLabel("新密码：");
	 changePwdFrame.add(labnewword);
	 JTextField textnewword=new JTextField();
	     dim = new Dimension(150,30);
		textnewword.setPreferredSize(dim);
		changePwdFrame.add(textnewword);
		JRadioButton box1=new JRadioButton("录入员");//设置单选
		JRadioButton box2=new JRadioButton("浏览员");
		JRadioButton box3=new JRadioButton("管理员");
		box1.setBackground(Azure);
		box2.setBackground(Azure);
		box3.setBackground(Azure);
		ButtonGroup bg=new ButtonGroup();
		bg.add(box1);
		bg.add(box2);
		bg.add(box3);
		box1.setSelected(true);//默认的选中状态
		changePwdFrame.add(box1);
		changePwdFrame.add(box2);
		if(getName().equals("小剑"))
			changePwdFrame.add(box3);
		JPanel panbtn=new JPanel();
		panbtn.setPreferredSize(new Dimension(250,40));
		panbtn.setBackground(Azure);
		JButton sure=new JButton("确定修改");
		JButton exit=new JButton("退出");
		panbtn.add(sure);
		panbtn.add(exit);
		JPanel delpan=new JPanel();
		delpan.setPreferredSize(new Dimension(250,40));
		delpan.setBackground(Azure);
		JButton del=new JButton("注销用户");
		delpan.add(del);
		changePwdFrame.add(panbtn);
		changePwdFrame.add(delpan);
		JLabel notice1 = new JLabel("");
		changePwdFrame.add(notice1);
		changePwdFrame.setVisible(true);
		sure.addActionListener(new ActionListener(){
		@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				String changePwd=textnewword.getText();
				if(changePwd.equals("")) changePwd=user.getPassword();
				String changeRole="";
				if(box1.isSelected()) changeRole="operator";
				else if(box2.isSelected()) changeRole="browser";
				else changeRole="administrator";
			 try {
				 if(user.getRole().equals(getRole())) {
					 notice1.setForeground(Color.RED);
					 notice1.setText("权限不足");}
				 else if(DataProcessing.updateUser(user.getName(), changePwd, changeRole)){
					 notice1.setForeground(Color.blue);
					notice1.setText("修改成功");
				 }
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				notice1.setForeground(Color.RED);
				notice1.setText(e.getMessage());
			}
			}		
		});
       exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				changePwdFrame.dispose();
				btn[0].setBackground(choosed);
				 ReNew();
			}});	
       del.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			try {
				 if(user.getRole().equals(getRole())) {
					 notice1.setForeground(Color.RED);
					 notice1.setText("权限不足");}
				 else if(DataProcessing.deleteUser(user.getName()))
					{notice1.setForeground(Color.blue);
				notice1.setText("注销成功");}
				else {
					notice1.setForeground(Color.RED);
					notice1.setText("注销失败");}
			} catch (SQLException e) {
				notice1.setForeground(Color.RED);
				notice1.setText(e.getMessage());
			}
		}});
	}
}
private void GetnowUserList(boolean choose) {//1表示所有，0表示实时
	// TODO 自动生成的方法存根
   nowuserList=new ArrayList<User>();
   if(choose) nowuserList=DataProcessing.Alluser();
		else {
			for(int i=0;i<DataProcessing.Alluser().size();i++)
			if(DataProcessing.Alluser().get(i).getName().contains(textuserName.getText()))
				nowuserList.add(DataProcessing.Alluser().get(i));
		}
	}

private void DisplayUserList() {
	// TODO 自动生成的方法存根
	AdFrame.remove(pnl2);
    pnl2=new JPanel();
	pnl2.setBackground(Azure);
	pnl2.setSize(370, 400);
    userList=new JList();
    DefaultListModel model=new  DefaultListModel();
    userList.setModel(model);
   for(int i=0;i<nowuserList.size();i++) {
    model.addElement(ClasstoString.ToString((User) nowuserList.get(i)));
    }
	userList.setPreferredSize(new Dimension(370,300));
    scroll=new JScrollPane(userList);
	pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 10));
		JLabel labName = new JLabel("     用户名                                 职称");
		labName.setPreferredSize(new Dimension(370,20));
		scroll.setPreferredSize(new Dimension(370,270));
	pnl2.add(labName);
	pnl2.add(scroll);
	AdFrame.add(pnl2);
}
private void DisplayFileList() {
	// TODO 自动生成的方法存根
	AdFrame.remove(pnl2);
    pnl2=new JPanel();
	pnl2.setBackground(Azure);
	pnl2.setSize(370, 400);
    fileList=new JList();
    DefaultListModel model=new  DefaultListModel();
    fileList.setModel(model);
    for(int i=0;i<nowfileList.size();i++) {
        model.addElement(ClasstoString.ToString((Doc) nowfileList.get(i)));
        }
	fileList.setPreferredSize(new Dimension(370,300));
    scroll=new JScrollPane(fileList);
	pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 10));
		JLabel labName = new JLabel("文件id       上传者           描述                        文件名");
		labName.setPreferredSize(new Dimension(370,20));
		scroll.setPreferredSize(new Dimension(370,270));
	pnl2.add(labName);
	pnl2.add(scroll);
	AdFrame.add(pnl2);
	AdFrame.repaint();
}
}
