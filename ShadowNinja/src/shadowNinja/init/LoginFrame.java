package shadowNinja.init;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author 23159
 * @version 1.0
 * 
 */
public class LoginFrame extends JFrame {
	// 创建输入输出文本框，以及“账号”、“密码”文本
	JLabel userLabel;
	JTextField userField;
	JLabel userLabel2;
	JPasswordField userField2;
	JButton Login, Cancel;
	public static User user;

	/**
	 * @param[无参构造函数]
	 */
	public LoginFrame(InitFrame fr) {
		// 创建文本
		userLabel = new JLabel("用户名");
		// 设置字体
		userLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		// 创建文本
		userLabel2 = new JLabel("密 码");
		// 设置字体
		userLabel2.setFont(new Font("微软雅黑", Font.BOLD, 18));

		// 布局方式：绝对布局
		userLabel.setBounds(380, 40, 100, 30);// x位置，y位置，所占显示空间的大小
		this.add(userLabel);// 将用户名这三个字添加到登录界面上，以下同理
		userLabel2.setBounds(380, 100, 100, 30);
		this.add(userLabel2);

		// 用户名输入框
		userField = new JTextField();
		userField.setBounds(440, 40, 100, 30);
		// 设置输入框凹陷效果
		userField.setBorder(BorderFactory.createLoweredBevelBorder());
		// 设置输入框背景透明
		userField.setOpaque(false);
		this.add(userField);

		userField2 = new JPasswordField();
		userField2.setBounds(440, 100, 100, 30);
		userField2.setBorder(BorderFactory.createLoweredBevelBorder());
		userField2.setOpaque(false);
		this.add(userField2);

//登录按钮
		Login = new JButton("登录");
		Login.setFont(new Font("微软雅黑", Font.BOLD, 18));
		Login.setBorder(BorderFactory.createRaisedBevelBorder());
		Login.setBounds(410, 150, 80, 45);
		Login.setContentAreaFilled(false);
		Login.setBackground(this.getBackground());// 背景色
		Login.setForeground(Color.BLACK);// 前景色
		/**
		 * @param[绑定监听事件]
		 */
		Login.addActionListener(new ActionListener() {
			@SuppressWarnings({ "resource" })
			public void actionPerformed(ActionEvent e) {
				String userName = userField.getText();
				String passWord = new String(userField2.getPassword());
				System.out.println(passWord);

				File file = new File(userName + ".txt");
				if ("".equals(userName) || "".equals("passWord")) {

					JOptionPane.showMessageDialog(null, "用户名 / 密码不能为空，请重新输入！");
				} else if (!file.exists()) {

					JOptionPane.showMessageDialog(null, "该用户未注册，请先注册再进入游戏！");
					dispose();
				} else {

					try {
						BufferedReader userInfomatoin = new BufferedReader(new FileReader(userName + ".txt"));
						String accountNumber = userInfomatoin.readLine();
						String userPassword = userInfomatoin.readLine();
						if (accountNumber.equals(userName) && userPassword.equals(passWord)) {

							user = User.readUserInfomation(accountNumber);
							// 登录成功
							JOptionPane.showMessageDialog(null, "欢迎" + userName + "来到暗影忍者");
							// 跳转到下一界面
							new Huanchongjiemian().Start();

							// 关闭当前界面
							dispose();
							fr.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "用户名 / 密码输入错误，请重新输入！");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

			}

		});
		this.add(Login);

//取消按钮
		Cancel = new JButton("取消");
		Cancel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		Cancel.setContentAreaFilled(false);
		Cancel.setBackground(this.getBackground());// 背景色
		Cancel.setForeground(Color.BLACK);// 前景色
		Cancel.setBorder(BorderFactory.createRaisedBevelBorder());
		Cancel.setBounds(510, 150, 80, 45);
		this.add(Cancel);
		Cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		// 创建背景面板，并添加到窗体上去
		LoginPanel2 panel = new LoginPanel2();
		panel.setLayout(new GridLayout(1, 1));
		// System.out.println("ddd");
		this.add(panel);
		// 设置登录界面的基本属性
		this.setSize(720, 520);
		this.setResizable(false);
		this.setLocationRelativeTo(null);// 位置居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
//	    this.pack();;
	}

	/**
	 * 
	 * @author 23159 @exception[无法找到图片时]
	 */
	class LoginPanel2 extends JPanel {
		// 背景图片变量
		Image background;// ------ctr shift + o 导包

		public LoginPanel2() {// -----alt / 回车 构造方法 在{后双击,显示作用域
			// 读取图片文件，赋值给background变量
			try {// -----虽然不大可能，但也做好吃饭噎死的准备
				background = ImageIO.read(new File("src/shadowNinja/init/res/LOGIN.jpg"));// ----read参数为File类型
			} catch (IOException e) {// -------捕获异常信息
				// 打印异常日志信息
				e.printStackTrace();
			}
		}

		// 绘制方法
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// 绘制背景图片
			g.drawImage(background, 5, 5, 710, 510, null);
		}
	}

}
