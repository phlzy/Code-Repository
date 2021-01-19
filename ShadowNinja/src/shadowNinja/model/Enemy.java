package shadowNinja.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import shadowNinja.controller.Table;
import shadowNinja.main.ui.MainFrame;

/**
 * 敌人基类
 * 
 * @author 唐雷
 *
 */
public class Enemy {
	// 敌人的图片
	protected Image image;
	protected Image images[];

	// 敌人的横纵坐标
	protected int x;
	protected int y;

	// 敌人大小
	public static int ENEMY_WIDTH = 50;
	public static int ENEMY_HEIGHT = 50;

	// 敌人向玩家运动的速度
	private int speed_X = 5;
	private int speed_Y = 10;

	// 伤害
	private static long damage = 50;

	// 随机对象
	Random random = new Random();
	private BufferedImage enemyImg;

	// table的索引
	private static Table table;

	/**
	 * 构造方法
	 * 
	 * @param table
	 */
	public Enemy(Table table) {
		// 后期改为随机生成的数字
		// 后期改为随机生成的数字
		if (Enemy.table == null)
			this.table = table;
		this.x = MainFrame.WINDOW_WIDTH - ENEMY_WIDTH;
		int tmp = MainFrame.WINDOW_HEIGHT - ENEMY_HEIGHT;
		this.y = random.nextInt() % tmp + 1; // 随机位置生成敌人
	}

	/**
	 * 测试用构造方法
	 * 
	 * @param table2
	 */
	public Enemy(shadowNinja.test.EnemyTest.Table table2) {
		// TODO Auto-generated constructor stub
		if (Enemy.table == null)
			this.table = table;
		this.x = MainFrame.WINDOW_WIDTH - ENEMY_WIDTH;
		int tmp = MainFrame.WINDOW_HEIGHT - ENEMY_HEIGHT;
		this.y = random.nextInt() % tmp + 1; // 随机位置生成敌人
	}

	public Enemy(shadowNinja.test.CollapseTest.Table table2) {
		// TODO Auto-generated constructor stub
		if (Enemy.table == null)
			this.table = table;
		this.x = MainFrame.WINDOW_WIDTH - ENEMY_WIDTH;
		int tmp = MainFrame.WINDOW_HEIGHT - ENEMY_HEIGHT;
		this.y = random.nextInt() % tmp + 1; // 随机位置生成敌人
	}

	/**
	 * 返回中心高度位置，便于碰撞检测
	 * 
	 * @return
	 */
	public int core() {
		return this.y + ENEMY_HEIGHT / 2;
	}

	/**
	 * 判断是否有撞击发生
	 * 
	 * @return
	 */
	public boolean isCollapse() {
		for (Bullet bullet : table.getBullets()) {
			System.out.println("bulletCore:" + bullet.core());
			System.out.println("EnemyCore:" + this.core());
			if (bullet.getX() + Bullet.BULLET_WIDTH >= this.getX()
					&& Math.abs(bullet.core() - this.core()) < Math.max(ENEMY_HEIGHT / 2, Bullet.BULLET_HEIGHT / 2)) {
				// System.out.println("bullet pang!");
				// table.getBullets().remove(bullet);
				return true;
			}
		}

		for (Fire fire : table.getFire()) {
			// System.out.println("bulletCore:"+bullet.core());
			// System.out.println("ObstacleCore:"+this.core());
			if (fire.getX() + Fire.FIRE_WIDTH >= this.getX()
					&& Math.abs(fire.core() - this.core()) < Math.max(Fire.FIRE_HEIGHT / 2, Enemy.ENEMY_HEIGHT / 2)) {
				System.out.println("Fire pang!");
				return true;
			}
		}

		Person ps = table.getPerson();
		if (ps.getX() + Person.WIDTH >= this.getX()
				&& Math.abs(ps.core() - this.core()) < Math.max(ENEMY_HEIGHT / 2, Person.HEIGHT / 2)) {
			// System.out.println("person pang!");
			damage += ps.getScore() / 10000;
			ps.loseHealth(damage);
			return true;
		}
		return false;
	}

	/**
	 * 加载图片
	 */
	private void loadImg() {
		BufferedImage origin_plane = null;
		// 加载图片
		try {
			origin_plane = ImageIO.read(Enemy.class.getResource("/enemys/samurai.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		float scaleX = ENEMY_WIDTH / (float) origin_plane.getWidth();
		float scaleY = ENEMY_HEIGHT / (float) origin_plane.getHeight();

		// 拉伸各个颜色的小格子图片
		image = createScaledImg(origin_plane, scaleX, scaleY);
	}

	/**
	 * 工具方法，通过一个原始图片对象，按照指定的宽、高缩放比率，拉伸它并返回拉伸后的图片对象
	 * 
	 * @param originImg 原始图片
	 * @param scaleX    宽缩放比率
	 * @param scaleY    高缩放比率
	 * @return 返回拉伸后的目标照片对象
	 */
	public BufferedImage createScaledImg(BufferedImage originImg, float scaleX, float scaleY) {
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
	 * 障碍物向玩家移动的方法
	 */
	public void step() {

		x -= speed_X;
	}

	/**
	 * 绘制自己
	 */
	public void drawSelf(Graphics2D g2d) {
		if (image == null) {
			System.out.println("no image!");
			return;
		}

		// 图片中心位置作为当前物体坐标点，所以需要向左上方偏移半个图片尺寸
		g2d.drawImage(image, (int) (x - Enemy.ENEMY_WIDTH / 2.0f), (int) (y - ENEMY_HEIGHT / 2.0f), Enemy.ENEMY_WIDTH,
				ENEMY_HEIGHT, null);
	}

	/**
	 * 判断障碍物是否越界的方法
	 * 
	 * @return
	 */
	public boolean isOutOfBound() {
		// return this.x <= -WIDTH;
		if (x <= 0) {
			System.out.println("enemy out!");
			return true;
		}
		return false;

	}

	/**
	 * 绘制障碍物的方法
	 * 
	 * @param g Graphics类
	 */
	public void paintEnemy(Graphics g) {
		g.drawImage(image, x, y, ENEMY_WIDTH, ENEMY_HEIGHT, null);
	}

	/**
	 * 判断障碍物是否越界的方法
	 * 
	 * @return boolean
	 */
	public boolean outofBounds() {
		return this.x <= -ENEMY_WIDTH;
	}

	/**
	 * 判断y方向是否越界
	 * 
	 * @return boolean
	 */
	public boolean outofBoundsY() {
		return (this.y <= 0 || this.y >= MainFrame.WINDOW_HEIGHT);
	}

	/**
	 * 得到图片
	 * 
	 * @return image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * 设置图片
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * 得到图片数组
	 * 
	 * @return
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * 设置图片数组
	 * 
	 * @param images
	 */
	public void setImages(Image[] images) {
		this.images = images;
	}

	/**
	 * 得到x坐标
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * 设置x坐标
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 沿x移动
	 */
	public void moveX() {
		this.x -= speed_X;
	}

	/**
	 * 沿y移动
	 */
	public void moveY() {
		if (!outofBoundsY()) {
			this.y += speed_Y;
		} else {
			speed_Y = -speed_Y;
			this.y += speed_Y;
		}
	}

	/**
	 * x,y,一起移动
	 */
	public void move() {
		moveX();
		moveY();
	}

	/**
	 * 得到y坐标
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * 设置y坐标
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 得到宽度
	 * 
	 * @return ENEMY_WIDTH
	 */
	public static int getWIDTH() {
		return ENEMY_WIDTH;
	}

	/**
	 * 得到高度
	 * 
	 * @return ENEMY_HEIGHT
	 */
	public static int getHEIGHT() {
		return ENEMY_HEIGHT;
	}

	/**
	 * 设置宽度
	 * 
	 * @param wIDTH
	 */
	public static void setWIDTH(int wIDTH) {
		ENEMY_WIDTH = wIDTH;
	}

	/**
	 * 设置高度
	 * 
	 * @param hEIGHT
	 */
	public static void setHEIGHT(int hEIGHT) {
		ENEMY_HEIGHT = hEIGHT;
	}

	/**
	 * 得到x方向的速度
	 * 
	 * @return
	 */
	public int getSpeedX() {
		return speed_X;
	}

	/**
	 * 得到y方向的
	 * 
	 * @return
	 */
	public int getSpeedY() {
		return speed_Y;
	}

	/**
	 * 设置x方向的速度
	 * 
	 * @param speed
	 */
	public void setSpeedX(int speed) {
		speed_X = speed;
	}

	/**
	 * 设置y方向的速度
	 * 
	 * @param speed
	 */
	public void setSpeedY(int speed) {
		speed_Y = speed;
	}
}
