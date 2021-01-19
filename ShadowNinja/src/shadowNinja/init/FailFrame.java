package shadowNinja.init;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class FailFrame extends JFrame   {
	//设置窗体的基本属性	大小
	/**
	 *  1.1、设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮
	 1.2、创建背景面板MainPanel，实现背景图片功能

	 2.图片按钮功能
	 */
	//2.1创建开始按钮 退出按钮 注册按钮
	JLabel confirm;
	private Toolkit tk;  //** 创建更换鼠标图片所需的变量
	private Image image;
	JPanel MainPanel;
	private Cursor cu;    //

	JLabel userField;

	/**
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * @param[无参构造方法]
	 */
	public FailFrame(String outputString) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		confirm = new JLabel("");//创建对象
		confirm.setBounds(1000,465,100,100);//确定位置
		confirm.setEnabled(false);//使start可点击
		confirm.setOpaque(false);//使start组件透明
		this.add(confirm);//向画布中添加start组件

/**
 * @param  [为start组件创建鼠标点击事件]
 */
		confirm.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				dispose();
				new dengluhoujiemian();
			}
			/**
			 * @param[e][鼠标监听事件][抽象方法]
			 */
			public void mouseEntered(MouseEvent e) {
				//处理鼠标进入
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});


		userField = new JLabel(outputString);
		userField.setBounds(1070, 380, 120, 30);
		userField.setFont(new Font("微软雅黑",Font.BOLD,28));
		//设置输入框背景透明
		userField.setOpaque(false);
		userField.setBorder(BorderFactory.createEmptyBorder());
		this.add(userField);



		/**1.实现背景图片及窗体属性*/
		MainPanel panel = new MainPanel();//创建一个panel对象
		this.add(panel);//将panel添加到Frame中去

		//设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮
		this.setSize(1200,720);//大小
		this.setLocationRelativeTo(null);//居中
		this.setUndecorated(true);//边框隐藏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭
		this.setVisible(true);	//设置为可见
		//更换鼠标光标为自定义选项
		String url = "src/shadowNinja/init/res/鼠标.png"; //储存鼠标图片的位置 src/shadowNinja/init/res/鼠标.png
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = new ImageIcon(url).getImage();
		Cursor cursor = tk.createCustomCursor(image, new Point(20, 10), "norm");
		panel.setCursor(cursor);


	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new FailFrame("score");
	}

	/**
	 * 创建背景面板MainPanel，实现背景图片功能
	 * @author 23159
	 *
	 */
	class MainPanel extends JPanel {//创建的MainPanel类，在MainFrame中调用
		Image background;
		/**
		 * @param[构造一个具有背景图片的panel的方法]
		 */
		public MainPanel() {
			try {
				background = ImageIO.read(new File("src/shadowNinja/init/res/Fail.png"));//src/shadowNinja/init/res/Success.png
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(background, 0, 0,1200,720, null);
		}

	}
	

}
