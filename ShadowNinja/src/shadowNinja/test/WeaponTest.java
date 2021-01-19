package shadowNinja.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shadowNinja.model.BackGround;
import shadowNinja.model.Bullet;
import shadowNinja.model.Enemy;
import shadowNinja.model.Fire;
import shadowNinja.model.Obstacle;
import shadowNinja.model.Person;
import shadowNinja.model.Stickman;
import shadowNinja.model.Weapon;
import shadowNinja.timer.ReschedulableTimerTask;

public class WeaponTest {

	public static int FPS = 60;

	/**
	 * Constructor.
	 * 
	 * @throws IOException
	 */
	public WeaponTest() throws IOException {
		// TODO Auto-generated constructor stub
		new MainFrame();
	}

	/**
	 * Activate the tset
	 * 
	 * @param rags
	 * @throws IOException
	 */
	public static void main(String rags[]) throws IOException {
		new WeaponTest();
	}

	class MainPanel extends JPanel {
		private MainFrame mainFrame;
		private Painter painter;
		private shadowNinja.controller.Table table;
		private Weapon weapon;

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

		public shadowNinja.controller.Table getTable() {

			return table;
		}

		public void setTable(shadowNinja.controller.Table newTable) {

			this.table = newTable;
		}

		public BackGround getBackGround() {
			return backGround;
		}

		public MainPanel(MainFrame mainFrame) throws IOException {
			this.weapon = new Weapon(10); // 输入weaponId
			this.mainFrame = mainFrame;
			this.table = new shadowNinja.controller.Table(2, this.mainFrame); // personId = 1为火柴人，2为忍者，3为猎豹
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

		public void onKeyDown(char keyChar) {
			switch (keyChar) {
			case 'w':
			case 'W':
				table.personJump();
				break;
			case 's':
			case 'S':
				// table.personDown();
				table.personFlip();
				break;
			case ' ':

				// table.addBullet();
				table.useWeapon(this.weapon, this.table);
				break;
			}
		}

		public void onMouseClick() {
			// Attack?
			// table.getPerson().

		}

		private void pushOneStep() {
			table.addEnemy();
			table.addObs();
			try {
				table.stepMovable();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public class MainFrame extends JFrame {
		public static final int WINDOW_WIDTH = 1200;// 1200
		public static final int WINDOW_HEIGHT = 800;// 800
		// 游戏功能按键定义
		public static final int UP = 1;// 旋转
		public static final int DOWN = 2;// 下移

		public static final int LEFT = 3;// 左移
		public static final int RIGHT = 4;// 右移
		public static final int TO_BOTTOM = 5;// 直接落底

		public static final int elementStep = 5; // 暂时步长设为5

		// MainPanel mainPanel;
		private MainPanel mainPanel;

		public MainFrame() throws IOException {
			this.setTitle("武器使用和武器冷却测试");
			this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.mainPanel = new MainPanel(this);
			// mainPanel.action();

			this.getContentPane().add(mainPanel);// 创建添加游戏主面板到主窗口中
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

			mainPanel.initMainLoop();

		}
	}

	class Table {
		ArrayList<Bullet> bullets;// 子弹的集合
		ArrayList<Bullet> removeBullets;// 删除子弹的集合
		ArrayList<Fire> fire;// 火焰的集合
		ArrayList<Fire> removeFire;// 删除火焰的集合
		ArrayList<Obstacle> obses;// 障碍物的集合
		ArrayList<Obstacle> removeObses;// 删除障碍物的集合
		ArrayList<Enemy> enemies;// 敌人的集合
		ArrayList<Enemy> removeEnemies;// 删除敌人的集合
		private Person person;// 人物
		Random random;

		private BackGround background;

		int weaponId;// 获取武器

		public Table(int personId) throws IOException// 构造函数
		{
			random = new Random();
			bullets = new ArrayList<Bullet>();
			removeBullets = new ArrayList<Bullet>();

			fire = new ArrayList<Fire>();
			removeFire = new ArrayList<Fire>();

			obses = new ArrayList<Obstacle>();
			removeObses = new ArrayList<Obstacle>();
			enemies = new ArrayList<Enemy>();
			removeEnemies = new ArrayList<Enemy>();

			background = new BackGround(0, 0, 0);

			switch (personId) {
			case 1:
				person = new Stickman();
				person.startGame();
				break;
			}
		}

		public BackGround getBackground() {
			return background;
		}

		public int getPersonX() {// 得到人物的x坐标
			// TODO Auto-generated method stub
			return person.getX();
		}

		public int getPersonY() {// 得到人物的y坐标
			// TODO Auto-generated method stub
			return person.getY();
		}

		public Person getPerson() {// 得到人物
			// TODO Auto-generated method stub
			return person;
		}

		public synchronized ArrayList<Bullet> getBullets()// 得到子弹
		{
			return bullets;
		}

		public synchronized ArrayList<Fire> getFire()// 得到火焰
		{
			return fire;
		}

		public synchronized ArrayList<Obstacle> getObses()// 得到障碍物
		{
			return obses;
		}

		public synchronized ArrayList<Enemy> getEnemies()// 得到敌人
		{
			return enemies;
		}

		public void fireWork()// 火焰的移动与删除
		{
			for (Fire each : fire) {
				if (each.shouldEnd()) {
					removeFire.add(each);
				} else {
					each.setX(); // 跟着人
					each.setY();
					each.remainLifeTime(1.0f / FPS * 1000);
				}
			}
			for (Fire removeEach : removeFire) {
				fire.remove(removeEach);
			}
		}

		public synchronized void bulletFly()// 子弹的移动与删除
		{
			for (Bullet bullet : bullets) {
				if (bullet.isOutOfBound()) {
					removeBullets.add(bullet);
				} else
					bullet.move(); // 自己飞
			}
			for (Bullet removeBullet : removeBullets) {
				bullets.remove(removeBullet);
			}
			removeBullets = new ArrayList<Bullet>();

		}

		/**
		 * obstacle move
		 */
		public synchronized void obsMove()// 障碍物的移动与删除
		{
			for (Obstacle obs : obses) {
				if (obs.outofBounds() || obs.isCollapse()) {
					removeObses.add(obs);
				} else {
					obs.step();
					// obs.moveX();
					// obs.setY(rope.getY() - obs.getHEIGHT());
				}
			}
			for (Obstacle removeObs : removeObses) {
				obses.remove(removeObs);
			}
			removeObses = new ArrayList<Obstacle>();

		}

		/**
		 * 地面跳起
		 * 
		 * @param y
		 */
		public void personJump() {
			person.jumpup();
		}

		/**
		 * 自由下落
		 */
		public void personDropdown() {
			person.dropup();
		}

		/**
		 * 快速下落
		 */
		public void personDown() {
			person.jumpdown();
		}

		/**
		 * 上翻
		 */
		public void personCrossUp() {
			person.overturnup();
		}

		/**
		 * 下翻
		 */
		public void personCrossDown() {
			person.overturndown();
		}

		/**
		 * Update all data,except people
		 */
		public void stepMovable()// 移动一次
		{
			synchronized ("check") {

				if (person.isUnderRope()) {
					person.stepdown();
				} else {
					person.stepup();
					person.dropdown();
				}
				bulletFly();
				fireWork();
				obsMove();

			}
		}

		/**
		 * 使用武器
		 * 
		 * @param weaponName
		 */

		public void useWeapon(Weapon weapon, shadowNinja.controller.Table table) {

			double cd = weapon.getCd();// 获取是否可使用
			if (cd == 0) {
				int index = weapon.getStageNum();// 获取使用次数
				if (weapon.getWeaponEffect().getIsBullet())// 判断是否为射出子弹的武器
				{

				} else if (weapon.getWeaponEffect().getIsFire())// 判断是否为喷出火焰的武器
				{
					table.fire.add(new Fire(1 * 1000, table));

				} else {
					for (int i = 0; i < index; i++) {
						switch (weapon.getDirection(i))// 获取释放方向
						{
						case 'w':
							// 删除障碍物
							for (Obstacle obs : table.obses) {
								if (((person.getX() <= obs.getX()) && ((person.getX() + Person.WIDTH) >= obs.getX()))
										|| ((person.getX() <= (obs.getX() + obs.WIDTH))
												&& ((person.getX() + Person.WIDTH) >= (obs.getX() + obs.WIDTH))))// 判断是否可能发生碰撞
								{
									if (((person.getY() <= obs.getY())
											&& ((person.getY() + Person.HEIGHT + weapon.getDistance(i)) >= obs.getY()))
											|| ((person.getY() <= (obs.getY() + obs.HEIGHT))
													&& ((person.getY() + Person.HEIGHT
															+ weapon.getDistance(i)) >= (obs.getY() + obs.HEIGHT))))// 判断是否发生碰撞
									{
										removeObses.add(obs);// 删除发生碰撞的障碍物

									}
								}
							}
							break;
						case 'd':
							// 删除障碍物
							for (Obstacle obs : obses) {
								if (((person.getY() <= obs.getY()) && ((person.getY() + Person.HEIGHT) >= obs.getY()))
										|| ((person.getY() <= (obs.getY() + obs.HEIGHT))
												&& ((person.getY() + Person.HEIGHT) >= (obs.getY() + obs.HEIGHT))))// 判断是否可能发生碰撞
								{
									if (((person.getX() <= obs.getX())
											&& ((person.getX() + Person.WIDTH + weapon.getDistance(i)) >= obs.getX()))
											|| ((person.getX() <= (obs.getX() + obs.WIDTH))
													&& ((person.getX() + Person.WIDTH
															+ weapon.getDistance(i)) >= (obs.getX() + obs.WIDTH))))// 判断是否发生碰撞
									{
										removeObses.add(obs);// 删除发生碰撞的障碍物
									}
								}
							}
							break;
						case 's':
							for (Obstacle obs : table.obses) {
								if (!(((obs.getX() + Obstacle.WIDTH) < (person.getX() - weapon.getDistance(i)))
										|| ((obs.getX()) > (person.getX() + person.WIDTH + weapon.getDistance(i)))
										|| ((obs.getY() + obs.HEIGHT) < (person.getY() - weapon.getDistance(i)))
										|| ((obs.getY()) > (person.getY() + person.HEIGHT + weapon.getDistance(i)))))// 判断人周围weapon.getDistance范围内的方形区域是否有障碍物
								{
									removeObses.add(obs);// 删除发生碰撞的障碍物
								}
							}
							break;
						}
					}
				}
				weapon.updateCdStamp();// 等待Weapon类完成
			} else
				System.out.println("The weapon is not ready.");
		}

		public void personFlip() {
			person.flip();
		}
	}

	class Painter {
		// 定义每个格子的具体大小，像素数
		int ELEMENT_SIZE = 0;// 每一个格子的大小（像素数）
		static final int TABLE_X = 1;// 游戏桌面开始的格子数（偏移量）
		static final int TABLE_Y = 5;

		private shadowNinja.controller.Table table;// 绘图类，先使用直接方式，存一下我们的业务核心类Table
		// 定义7种颜色格子图片对象，作为属性保存起来
		BufferedImage img_background;// 背景图片

		private Weapon weapon; // 仅供显示cd使用

		public Painter(shadowNinja.controller.Table table2, Weapon initWeapon) {
			this.table = table2;
			weapon = initWeapon;

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

		private void loadImages() throws IOException {
			BufferedImage origin_background = ImageIO.read(Painter.class.getResource("/maps/3.png"));
			float scaleX = MainFrame.WINDOW_WIDTH / (float) origin_background.getWidth();
			float scaleY = MainFrame.WINDOW_HEIGHT / (float) origin_background.getHeight();

			// 创建拉伸后的背景图片
			img_background = createScaledImg(origin_background, scaleX, scaleY);
		}

		private void drawBackground(Graphics2D g2d) {

			table.getBackground().drawSelf(g2d);
		}

		private void drawPerson(Graphics2D g2d) {
			if (table.getPerson().isUnderRope()) {
				table.getPerson().paintPersondown(g2d);
			} else {
				table.getPerson().paintPersonup(g2d);
			}
		}

		private void drawBullet(Graphics2D g2d) {

			synchronized (table) {

				List<shadowNinja.model.Bullet> bullets = table.getBullets();

				// System.out.println("bullets" + bullets.size());
				for (shadowNinja.model.Bullet b : bullets) {
					b.drawSelf(g2d);
				}
			}
		}

		private void drawObs(Graphics2D g2d) {
			synchronized (table) {

				List<Obstacle> obstacles = table.getObses();
				// System.out.println("obstacles:"+obstacles.size());
				for (Obstacle obstacle : obstacles) {
					obstacle.drawSelf(g2d);
				}

			}
		}

		private void drawFire(Graphics2D g2d) {
			List<Fire> fires = table.getFire();

			synchronized (table) {
				for (Fire fire : fires) {
					fire.drawSelf(g2d);

				}
			}
		}

		private void drawCd(Graphics2D g2d) {
			synchronized (table) {
				weapon.drawCd(g2d);

			}
		}

		public void drawAll(Graphics2D g2d) {
			// 按顺序画出界面的各个部分
			// 先画出背景
			drawBackground(g2d);

			// 绘制障碍物
			drawObs(g2d);

			// 绘制出所有子弹
			drawBullet(g2d);
			// 绘制火焰
			drawFire(g2d);

			// 绘制人物
			drawPerson(g2d);

			// 显示cd
			drawCd(g2d);
		}
	}
}
