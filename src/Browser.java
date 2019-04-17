import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

public class Browser extends User  implements ActionListener,DocumentListener,ListSelectionListener{
	Scanner cin=new Scanner(System.in);
	Dimension dim;
	JFrame AdFrame=new JFrame();
	JButton btn[]={new JButton("下载文件"),new JButton("文件列表"),new JButton("个人中心"),new JButton("注销登录"),
			new JButton("退出")};
	JPanel pnl;
	JPanel pnl2;
	Color LightCyan=new Color(225,255,255);//淡青
	Color Azure=new Color(240,255,255);//蔚蓝
	Color choosed=new Color(135,206,250);//青
	ArrayList nowfileList=new ArrayList();
	JTextField textFileName;
	JList fileList;
	JScrollPane scroll;
	Document userdoc;
	Document filedoc;
	 User user;
	 Doc doc;
	 File cfile;
	 Browser(String name,String password,String role){
		super(name,password,role);	
		AdFrame=new JFrame("尊敬的"+this.getName()+",欢迎进入浏览员界面\n");
		AdFrame.setSize(600, 400);
		AdFrame.setResizable(false);//设置大小不可改变
		AdFrame.setLocationRelativeTo(null);
		pnl=new JPanel();
		pnl.setSize(200, 400);
		pnl.setBackground(LightCyan);
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
		pnl.setLayout(fl);
		dim= new Dimension(200,30);
		for(int i=0;i<5;i++) {
			if(i>=3) dim= new Dimension(90,30);
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
		btn[2].setBackground(choosed);// TODO 自动生成的方法存根
	}
    @SuppressWarnings("resource")

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		for(int i=0;i<5;i++) {
			btn[i].setBackground(Azure);
		}
		((JButton)e.getSource()).setBackground(choosed);
		switch(((JButton)e.getSource()).getText()) {
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
			if(arg0.getSource().equals(fileList))
			{	doc=(Doc) nowfileList.get(fileList.getSelectedIndex());
				downUi(doc);}
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

	
	
}