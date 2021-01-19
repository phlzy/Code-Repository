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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shadowNinja.main.ui.MainFrame;

public class dengluhoujiemian extends JFrame {

	// 设置窗体的基本属性 大小
	/**
	 * 1.1、设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 logo图标 1.2、创建背景面板MainPanel，实现背景图片功能
	 * 
	 * 2.图片按钮功能
	 */
	// 2.1创建开始按钮 帮助按钮 离开按钮 组件
	JLabel wujinmoshi, xuanguanmoshi, packback, store;
	private Toolkit tk;
	private Image image;
	JPanel MainPanel;
	private Cursor cu;

	/**
	 * @param[无参构造函数]
	 */
	public dengluhoujiemian() {
		// 2.2
		wujinmoshi = new JLabel("  ");
		wujinmoshi.setBounds(110, 527, 80, 90);
		wujinmoshi.setEnabled(false);
		this.add(wujinmoshi);
		wujinmoshi.setOpaque(false);
		wujinmoshi.addMouseListener(new MouseListener() {
			/**
			 * @param[e][鼠标监听事件][pattern添加监听事件]
			 */
			// 无尽模式
			public void mouseClicked(MouseEvent e) {
				// new Frame(0);
				try {
					new MainFrame(0, 1);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});

		xuanguanmoshi = new JLabel("  ");
		xuanguanmoshi.setBounds(320, 527, 80, 90);
		xuanguanmoshi.setEnabled(false);
		xuanguanmoshi.setOpaque(false);
		this.add(xuanguanmoshi);
		/**
		 * @param[e][监听事件][为xuanguanmoshi组件添加监听事件]
		 */
		xuanguanmoshi.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				new xuanzeguanqiajiemian();
				dispose();
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

		packback = new JLabel(" ");
		packback.setBounds(1070, 527, 80, 90);
		packback.setEnabled(false);
		this.add(packback);
		packback.setOpaque(false);
		/**
		 * @param[e][监听事件][为packback组件添加监听事件]
		 */
		packback.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// 创建背包界面

				try {
					new PackbackFrame();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
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

		store = new JLabel("  ");
		store.setBounds(880, 527, 80, 90);
		store.setEnabled(false);
		this.add(store);
		store.setOpaque(false);
		/**
		 * @param[e][监听事件][为backpack组件添加监听事件]
		 */
		store.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// 创建商店界面
				try {
					new StoreFrame();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
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

		JButton back = new JButton();
		back.setBorder(BorderFactory.createRaisedBevelBorder());
		back.setBounds(20, 30, 90, 80);
		back.setContentAreaFilled(false);
		back.setEnabled(false);
		back.setBorderPainted(false);
		this.add(back);
		back.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				try {
					new InitFrame();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		/** 1.实现背景图片及窗体属性 */
		MainPanel panel = new MainPanel();
		this.add(panel);

		// 设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 logo图标
		this.setSize(1200, 720);// 大小
		this.setLocationRelativeTo(null);// 居中
		this.setUndecorated(true);// 边框隐藏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭
		this.setVisible(true);
		String url = "src/shadowNinja/init/res/鼠标.png"; // 储存鼠标图片的位置
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = new ImageIcon(url).getImage();
		Cursor cursor = tk.createCustomCursor(image, new Point(20, 10), "norm");
		panel.setCursor(cursor);
	}

	/**
	 * 
	 * @author 23159
	 * @version 1.0 @exception[当图片无法找到时抛出异常]
	 */
	class MainPanel extends JPanel {// 创建的MainPanel类，在MainFrame中调用
		Image background;

		public MainPanel() {
			try {
				background = ImageIO.read(new File("src/shadowNinja/init/res/选关界面.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * @param[g][继承的Graphics类][用于将背景图片画在JFrame上去]
		 */
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(background, 0, 0, 1200, 720, null);
		}

	}

}