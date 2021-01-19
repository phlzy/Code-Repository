package shadowNinja.main.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * 核心游戏主窗口
 * 
 * @version 2.0.1
 * @date 2020/12/29
 *
 */
public class MainFrame extends JFrame {

	// 窗口大小
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 800;

	// 游戏功能按键定义
	// 旋转
	public static final int UP = 1;
	// 下移
	public static final int DOWN = 2;
	// 左移
	public static final int LEFT = 3;
	// 右移
	public static final int RIGHT = 4;
	// 直接落底
	public static final int TO_BOTTOM = 5;

	// 步长设为5
	public static final int elementStep = 5;

	// 主面板
	private MainPanel mainPanel;
	// 0无尽模式，1有尽模式
	public static int type;
	// 关卡
	public static int term;

	// 构造函数
	public MainFrame(int initType, int initTerm) throws IOException {
		// 模式选择
		type = initType;
		// 关卡
		term = initTerm;

		// 设置窗口数据
		this.setTitle("暗影忍者 v1.0");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.mainPanel = new MainPanel(this);

		// 设定模式
		mainPanel.setType(type);
		mainPanel.setTerm(term);

		// 创建添加游戏主面板到主窗口中
		this.getContentPane().add(mainPanel);
		this.setVisible(true);

		// 读取键盘操作
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				mainPanel.onKeyDown(e.getKeyChar());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// 读取鼠标操作
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON1) {
					// 左键点下
					mainPanel.onMouseClick();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// mainPanel.getTable().getPerson().reset();
		// 进入主循环
		mainPanel.initMainLoop();
	}

	/**
	 * main方法
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		new MainFrame(0, 1); // 随用户选择更改
	}

}
