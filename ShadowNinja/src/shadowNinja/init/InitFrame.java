package shadowNinja.init;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitFrame extends JFrame {
	// 设置窗体的基本属性 大小
	/**
	 * 1.1、设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 1.2、创建背景面板MainPanel，实现背景图片功能
	 * 
	 * 2.图片按钮功能
	 */
	// 2.1创建开始按钮 退出按钮 注册按钮
	JLabel start, exit, register;
	private Toolkit tk; // ** 创建更换鼠标图片所需的变量
	private Image image;
	JPanel MainPanel;
	private Cursor cu;

	/**
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException      @param[无参构造方法]
	 */
	public InitFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		start = new JLabel("  ");// 创建对象
		start.setBounds(320, 565, 80, 70);// 确定位置

		start.setEnabled(false);// 使start可点击
		start.setOpaque(false);// 使start组件透明
		this.add(start);// 向画布中添加start组件

		/**
		 * @param [为start组件创建鼠标点击事件]
		 */
		start.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				new LoginFrame(getThis());
			}

			/**
			 * @param[e][鼠标监听事件][抽象方法]
			 */
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标进入
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

		register = new JLabel("  ");
		register.setBounds(110, 555, 80, 70);
		register.setEnabled(false);
		this.add(register);
		register.setOpaque(false);
		/**
		 * @param [为register组件添加监听事件]
		 */
		register.addMouseListener(new MouseListener() {
			/**
			 * @param[e][鼠标监听事件][抽象方法]
			 */
			public void mouseClicked(MouseEvent e) {
				new RegisterFrame();
			}

			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
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

		exit = new JLabel("  ");
		exit.setBounds(880, 565, 80, 70);
		exit.setEnabled(false);
		this.add(exit);
		exit.setOpaque(false);
		/**
		 * @param[为exit组件添加监听事件]
		 */
		exit.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}

			/**
			 * @param[e][抽象方法]
			 */
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
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

		/** 1.实现背景图片及窗体属性 */
		MainPanel panel = new MainPanel();// 创建一个panel对象
		this.add(panel);// 将panel添加到Frame中去

		// 设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮
		this.setSize(1200, 720);// 大小
		this.setLocationRelativeTo(null);// 居中
		this.setUndecorated(true);// 边框隐藏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭
		this.setVisible(true); // 设置为可见
		// 更换鼠标光标为自定义选项
		String url = "src/shadowNinja/init/res/鼠标.png"; // 储存鼠标图片的位置
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = new ImageIcon(url).getImage();
		Cursor cursor = tk.createCustomCursor(image, new Point(20, 10), "norm");
		panel.setCursor(cursor);

		File musicPath = new File("C:\\Users\\23159\\Desktop\\千辛万苦.wav");
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
		Clip clip = AudioSystem.getClip();
		clip.open(audioInput);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new InitFrame();
	}

	/**
	 * 创建背景面板MainPanel，实现背景图片功能
	 * 
	 * @author 23159
	 *
	 */
	class MainPanel extends JPanel {// 创建的MainPanel类，在MainFrame中调用
		Image background;

		/**
		 * @param[构造一个具有背景图片的panel的方法]
		 */
		public MainPanel() {
			try {
				background = ImageIO.read(new File("src/shadowNinja/init/res/主背景.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(background, 0, 0, 1200, 720, null);
		}

	}

	public InitFrame getThis() {
		return this;
	}

}
