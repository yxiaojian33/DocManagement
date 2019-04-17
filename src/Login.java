
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
public class Login implements ActionListener{
	Color LightYellow=new Color(255,255,224);//浅黄色
	Color LightGreen=new Color(144,238,144);//浅绿色
	JFrame frame;
	User user;
	Dimension dim;//设置控件大小
	ImageIcon icon;
	JLabel labIcon;
	JLabel labName;
	JTextField textName;
	JLabel labpassword;
	JPasswordField textword;
	JPanel pnl;
	JButton btn[]= { new JButton("登录"),new JButton("退出")};
	JLabel notice;
	String name,pwd;
	Login() {
	    frame = new JFrame();
		frame.setTitle("小剑档案系统");
		frame.setSize(400, 320);
		Toolkit tool=frame.getToolkit();
		Image im=tool.getImage(".\\drawable\\bg.png");//修改窗口图标
		frame.setIconImage(im);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(LightYellow);//设置自带容器的颜色
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);//10像素间距
		// 流式布局
		frame.setLayout(fl);
	    icon = new ImageIcon(".\\drawable\\bg.png");
		labIcon = new JLabel(icon);
	    dim = new Dimension(400,100);//设宽一点不然会乱序
		labIcon.setPreferredSize(dim);
		frame.add(labIcon);
	    labName = new JLabel("账号：");
		frame.add(labName);
        textName = new JTextField();
	    dim = new Dimension(300,30);
		textName.setPreferredSize(dim);
		frame.add(textName);
	    labpassword= new JLabel("密码：");
		frame.add(labpassword);
		textword=new JPasswordField();
		textword.setPreferredSize(dim);
		frame.add(textword);
	    pnl=new JPanel();
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 100, 0);
		pnl.setLayout(f2);
		pnl.setBackground(LightYellow);
		for(int i=0;i<2;i++) {
            dim= new Dimension(60,30);
			btn[i].setPreferredSize(dim);
			btn[i].addActionListener(this);
			btn[i].setBackground(LightGreen);
			pnl.add(btn[i]);
		}
		frame.add(pnl);
		notice = new JLabel("");
		dim = new Dimension(500,30);
		notice.setSize(dim);
		frame.add(notice);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		switch(((JButton)e.getSource()).getText()) {
		case "退出":
		System.exit(0);
		break;
		case "登录":
	      login();
			break;
	}
	}
	private void login() {
		// TODO 自动生成的方法存根
		name=textName.getText();
	      pwd=textword.getText();
			try {
				user=null;
			user=DataProcessing.searchUser(name);
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				notice.setForeground(Color.RED);
				notice.setText(e1.getMessage());
			}
			if(user==null) {
				notice.setForeground(Color.RED);
				notice.setText("用户不存在");
			}
			else if(!pwd.equals(user.getPassword())) {
				notice.setForeground(Color.RED);//设置颜色
				notice.setText("账号和密码不匹配");		
			}
			else {
				notice.setForeground(Color.BLUE);//设置颜色
				notice.setText("登录成功");
				frame.dispose();
				user.showMenu();
			}
			}
	}


