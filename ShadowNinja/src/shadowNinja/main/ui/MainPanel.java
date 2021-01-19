package shadowNinja.main.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import shadowNinja.controller.Painter;
import shadowNinja.controller.Table;
import shadowNinja.init.PackbackObsFrame;
import shadowNinja.model.BackGround;
import shadowNinja.model.Weapon;
//import shadowNinja.game.control.Painter;
import shadowNinja.timer.ReschedulableTimerTask;

/**
 * 游戏主面板
 * 
 * @version 2.0.1
 * @date 2020/12/29
 *
 */
public class MainPanel extends JPanel {

	// 主窗口的引用
	private MainFrame mainFrame;
	// 绘制类
	private Painter painter;
	// 游戏主业务类
	private Table table;
	// 武器
	private Weapon weapon;
	// 背景
	private BackGround backGround;
	// 缓冲的图像对象
	private BufferedImage imageBuffer = null;
	// 在缓冲对象中绘图
	private Graphics gBuffer = null;
	// 帧率
	public static int FPS = 60;
	// 定时器执行的任务
	ReschedulableTimerTask gameTimerTask;
	// 定时器
	Timer gameTimer = null;

	// 0无尽模式，1有尽模式
	private int type;
	// 关卡
	private int term;

	/**
	 * 设置帧率
	 * 
	 * @param fps
	 */
	public void setFPS(int fps) {
		if (fps <= 5 || fps >= 180) {
			return;
		}
		FPS = fps;
	}

	/**
	 * 获取绘画类
	 * 
	 * @return
	 */
	public Painter getPainter() {

		return painter;
	}

	/**
	 * 设置绘画类
	 * 
	 * @param painter
	 */
	public void setPainter(Painter painter) {

		this.painter = painter;
	}

	/**
	 * 获取主业务类
	 * 
	 * @return
	 */
	public shadowNinja.controller.Table getTable() {

		return table;
	}

	/**
	 * 设置主业务类
	 * 
	 * @param newTable
	 */
	public void setTable(Table newTable) {

		this.table = newTable;
	}

	/**
	 * 获取背景
	 * 
	 * @return
	 */
	public BackGround getBackGround() {
		return backGround;
	}

	/**
	 * 构造函数
	 * 
	 * @param mainFrame
	 * @throws IOException
	 */
	public MainPanel(MainFrame mainFrame) throws IOException {
		// 输入weaponId
		this.weapon = new Weapon(PackbackObsFrame.currentWeapon);
		this.mainFrame = mainFrame;
		// personId = 3为火柴人，1为忍者，2为猎豹
		this.table = new Table(PackbackObsFrame.curPerson(), mainFrame);

		backGround = new BackGround(0, 0, 0);
		painter = new Painter(this.table, this.weapon);
		if (this.imageBuffer == null) {
			this.imageBuffer = new BufferedImage(MainFrame.WINDOW_WIDTH, MainFrame.WINDOW_HEIGHT,
					BufferedImage.TYPE_INT_ARGB);
			this.gBuffer = this.imageBuffer.getGraphics();
		}

		// 初始化主循环定时器
		this.gameTimer = new Timer();

	}

	/**
	 * 响应按下键盘对应按键的事件
	 * 
	 * @param keyChar
	 */
	public void onKeyDown(char keyChar) {
		switch (keyChar) {
		case 'w':
		case 'W':
			// 跳
			table.personJump();
			break;
		case 's':
		case 'S':
			// 下翻
			table.personFlip();
			break;
		case ' ':
			// 使用武器
			table.useWeapon(this.weapon, this.table);
			break;
		}
	}

	/**
	 * 响应鼠标按下的方法
	 */
	public void onMouseClick() {
		// 暂无

	}

	/**
	 * 向后推移一帧
	 * 
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	private void pushOneStep() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// 随机生成敌人
		table.addEnemy();
		// 随机生成障碍
		table.addObs();
		// 让游戏所有元素变化一下
		table.stepMovable();

		// System.out.println(table.getPerson().getHealth());
	}

	/**
	 * 开始进入一个主循环（开启主定时器）
	 */
	public void initMainLoop() {
		// 创建定时器的执行任务对象
		gameTimerTask = new ReschedulableTimerTask() {
			@Override
			public void run() {
				// 让游戏所有元素变化一下
				try {
					pushOneStep();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 重新绘制
				onDraw();
			}
		};
		// 开始定时器
		startMainLoop();
	}

	/**
	 * 开启主定时器，进入主绘制循环
	 */
	public void startMainLoop() {
		// 按FPS值开启一个定时器
		gameTimer.schedule(gameTimerTask, 0, (int) (1.0f / FPS * 1000));
	}

	/**
	 * 使用带双缓冲的方法绘制
	 */
	public void onDraw() {
		// 命令绘图类对象把所有内容绘制到图形缓冲中
		painter.drawAll((Graphics2D) gBuffer);

		// 把缓冲中绘制的内容绘制到主界面中
		// 获取当前主面板自身的绘图对象
		Graphics src = this.getGraphics();
		// 通过这个绘图对象把缓冲区图像的内容一次性复制到主面板的可视区域
		src.drawImage(imageBuffer, 0, 0, null);
	}

	/**
	 * 绘制
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		onDraw();
	}

	// 针对模式和关数的set和get
	public void setType(int initType) {
		type = initType;
		if (type == 1)
			table.setIfBar(true);
		else {
			table.setIfBar(false);
		}
	}

	/**
	 * 设置选择的关卡
	 * 
	 * @param initTerm
	 */
	public void setTerm(int initTerm) {
		term = initTerm;
	}

	public int getType() {
		return type;
	}

	public int getTerm() {
		return term;
	}
}
