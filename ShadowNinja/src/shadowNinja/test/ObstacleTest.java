package shadowNinja.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shadowNinja.controller.Painter;
import shadowNinja.controller.Table;
import shadowNinja.main.ui.MainFrame;
import shadowNinja.model.Arrow;
import shadowNinja.model.BackGround;
import shadowNinja.model.Bat;
import shadowNinja.model.Bullet;
import shadowNinja.model.Dart;
import shadowNinja.model.Enemy;
import shadowNinja.model.Fire;
import shadowNinja.model.Needle;
import shadowNinja.model.Ninja;
import shadowNinja.model.Obstacle;
import shadowNinja.model.Panther;
import shadowNinja.model.Person;
import shadowNinja.model.Rope;
import shadowNinja.model.Samurai;
import shadowNinja.model.Stickman;
import shadowNinja.model.Weapon;
import shadowNinja.timer.ReschedulableTimerTask;


/**
 * 这个类用来测试障碍物能否正确随机生成
 * @author 唐雷
 *
 */
public class ObstacleTest {
	public static int FPS = 60;
	
	/**
	 * Constructor.
	 * @throws IOException
	 */
	public ObstacleTest() throws IOException {
		// TODO Auto-generated constructor stub
		new MainFrame();
	}
	
	/**
	 * Activate the tset
	 * @param rags
	 * @throws IOException
	 */
	public static void main(String rags[]) throws IOException
	{
		 new ObstacleTest();
	}
	
	class MainPanel extends JPanel {

		private MainFrame mainFrame;
		private Painter painter;
		private Table table;

		private BackGround backGround;
		private BufferedImage imageBuffer = null;
		private Graphics gBuffer = null;

		
		ReschedulableTimerTask gameTimerTask;
		Timer gameTimer = null;

		public void setFPS(int fps) {
			if (fps <= 5 || fps >= 180) {
				return;
			}
			FPS = fps;
		}

		public Painter getPainter() {

			return painter;
		}

		public void setPainter(Painter painter) {

			this.painter = painter;
		}

		public Table getTable() {

			return table;
		}

		public void setTable(Table newTable) {

			this.table = newTable;
		}

		public BackGround getBackGround() {
			return backGround;
		}

		public MainPanel(MainFrame mainFrame) throws IOException {
			this.mainFrame = mainFrame;
			this.table = new Table(2); // personId = 1为火柴人，2为忍者，3为猎豹
			backGround = new BackGround(0, 0, 0);
			painter = new Painter(this.table);
			if (this.imageBuffer == null) {
				this.imageBuffer = new BufferedImage(MainFrame.WINDOW_WIDTH, MainFrame.WINDOW_HEIGHT,
						BufferedImage.TYPE_INT_ARGB);
				this.gBuffer = this.imageBuffer.getGraphics();
			}

			// 初始化主循环定时器
			this.gameTimer = new Timer();

		}

		public void onKeyDown(char keyChar) {
			
		}

		public void onMouseClick() {


		}

		private void pushOneStep() {
			table.stepMovable();
			table.addObs();
			// System.out.println("pushOneStepDone");
		}

		/**
		 * 开始进入一个主循环（开启主定时器）
		 */
		public void initMainLoop() {
			// 创建定时器的执行任务对象（但并未开始执行定时器！）
			gameTimerTask = new ReschedulableTimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					pushOneStep();// 让游戏所有元素变化一下，再重绘
					onDraw();// 重新绘制
				}
			};

			startMainLoop();// 开始定时器
		}

		public void resumeGame() {
			// TODO Auto-generated method stub

		}

		public void startMainLoop() {
			// 按FPS值，开启一个定时器
			gameTimer.schedule(gameTimerTask, 0, (int) (1.0f / FPS * 1000));
		}

		public void onDraw() {
			// 命令绘图类对象，把所有内容绘制到图形缓冲中
			painter.drawAll((Graphics2D) gBuffer);

			// 把缓冲中绘制的内容绘制到主界面中
			Graphics src = this.getGraphics();// 获取当前主面板自身的绘图对象
			src.drawImage(imageBuffer, 0, 0, null);// 通过这个绘图对象，把缓冲区图像的内容，一次性“复制”到主面板的可视区域
//			src.drawImage(imageBuffer, table.getBackground().img_background.getWidth(), 0, null);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

			onDraw();
		}
	}

	
	class MainFrame extends JFrame {
		public static final int WINDOW_WIDTH = 1200;// 1200
		public static final int WINDOW_HEIGHT = 800;// 800

		private MainPanel mainPanel;

		public MainFrame() throws IOException {
			this.setTitle("背景图移动测试");
			this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.mainPanel = new MainPanel(this);

			// 创建添加游戏主面板到主窗口中
			this.getContentPane().add(mainPanel);
			this.setVisible(true);

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



			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					if (e.getButton() == MouseEvent.BUTTON1) {
						mainPanel.onMouseClick();// 左键点下
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
			mainPanel.initMainLoop();
		}

	}


	
	
	public class Table
	{
			private ArrayList<Obstacle> Obses = new ArrayList<Obstacle>();
			private ArrayList<Obstacle> removeObses = new ArrayList<Obstacle>();
			private BackGround background;
			Random random;

			public Table(int personId) throws IOException// 构造函数
			{	
				background = new BackGround(0, 0, 0);
				random = new Random();
			}

			public BackGround getBackground() {
				return background;
			}
			

			

			
			/**
			 * Update all data,except people
			 */
			public void stepMovable()// 移动一次
			{
				synchronized ("check") {

					obsMove();

				}
			}

			private void obsMove() {
				// TODO Auto-generated method stub
					for (Obstacle obs : Obses) {
						if (obs.outofBounds()) {
							removeObses.add(obs);
						} else {
							obs.step();
						}
					}
					for (Obstacle removeObs : removeObses) {
						Obses.remove(removeObs);
					}
					removeObses = new ArrayList<Obstacle>();

			}
			private void addObs()
			{
				synchronized (this) {
					int temp = random.nextInt();
					if (temp % 97 == 0)
						Obses.add(new Arrow(this));
					else if (temp % 101 == 0)
						Obses.add(new Needle(this));
					else if (temp % 83 == 0)
						Obses.add(new Dart(this));
					
				}
			}
			public List<Obstacle> getObses() {
				// TODO Auto-generated method stub
				return Obses;
			}


	}
	
	
	class Painter {

		// 绘图类，先使用直接方式，存一下我们的业务核心类Table
		private Table table;
		// 背景图片
		BufferedImage img_background;
		

		/**
		 * Constructor
		 * @param table
		 * @param initWeapon
		 */
		public Painter(Table table) {
			this.table = table;

			try {
				loadImages();// 绘制类一创建就加载图片
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("图片加载错误，请检查图片是否存在！");
			}
		}

		/**
		 * 内部方法，通过一个原始图片对象，按照指定的宽、高缩放比率，拉伸它并返回拉伸后的图片对象
		 * 
		 * @param originImg 原始图片
		 * @param scaleX    宽缩放比率
		 * @param scaleY    高缩放比率
		 * @return 返回拉伸后的目标照片对象
		 */
		private BufferedImage createScaledImg(BufferedImage originImg, float scaleX, float scaleY) {
			// 计算出要拉伸的图片目标宽、高
			int scaledWidth = (int) (originImg.getWidth() * scaleX);
			int scaledHeight = (int) (originImg.getHeight() * scaleY);

			// 创建一个拉伸后的照片对象，准备通过原始照片拉伸它
			BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, originImg.getType());

			// 将原始照片，按照缩放比率，绘制到目标拉伸照片对象中
			Graphics g = newImage.getGraphics();
			g.drawImage(originImg, 0, 0, scaledWidth, scaledHeight, null);
			g.dispose();
			return newImage;
		}

		/**
		 * 内部方法，用于初始化图片元素
		 * 
		 * @throws IOException
		 */
		private void loadImages() throws IOException {
			
			// 从/res/imgs目录，加载所有原始照片（未拉伸）
			BufferedImage origin_background = ImageIO.read(Painter.class.getResource("/maps/3.png"));

			// 第三步：通过原始图片，创建出拉伸后的最终使用的图片，并给上面的属性赋值
			// 1.根据窗口大小，拉伸背景图
			// 计算窗口背景图宽高比例
			float scaleX = MainFrame.WINDOW_WIDTH / (float) origin_background.getWidth();
			float scaleY = MainFrame.WINDOW_HEIGHT / (float) origin_background.getHeight();

			// 创建拉伸后的背景图片
			img_background = createScaledImg(origin_background, scaleX, scaleY);
		}



		/**
		 * 内部方法，绘制背景
		 */
		private void drawObs(Graphics2D g2d) {
			synchronized (table) {

				List<Obstacle> obstacles = table.getObses();
				// System.out.println("obstacles:"+obstacles.size());
				for (Obstacle obstacle : obstacles) {
					obstacle.drawSelf(g2d);
				}

			}
		}
		private void drawBackground(Graphics2D g2d) {

			table.getBackground().drawSelf(g2d);
		}

		
		/**
		 * 当前绘图对象，通过传入的“Graphics2D”画笔对象，把所有界面内容画到缓冲区中
		 * 
		 * @param g2d
		 */
		public void drawAll(Graphics2D g2d) {
			// 按顺序画出界面的各个部分
					// 先画出背景
			drawBackground(g2d);
					drawObs(g2d);
					// 绘制障碍物
					
		}


	}

}



	
	

