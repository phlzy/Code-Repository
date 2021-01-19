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
 * 障碍物基类
 * 
 * @author 唐雷
 *
 */
public abstract class Obstacle {

	// 障碍物的图片
	protected Image image;
	protected Image images[];

	// 障碍物的横纵坐标
	protected int x;
	protected int y;

	// 障碍物大小
	public static int WIDTH = 100;
	public static int HEIGHT = 100;

	// 伤害
	private static long damage = 50;

	// table的索引
	public static Table table;

	// 障碍物向玩家运动的速度 = 玩家的当前速度
	private static final int speed = 5;

	// 随机对象
	Random random = new Random();

	/**
	 * 构造方法
	 * 
	 * @param table
	 */
	public Obstacle(Table table) {
		// 索引加入
		if (Obstacle.table == null)
			this.table = table;

		x = MainFrame.WINDOW_WIDTH;
		y = MainFrame.WINDOW_HEIGHT / 2;
		// loadImg();
	}

	public Obstacle(shadowNinja.test.ObstacleTest.Table table2) {
		// TODO Auto-generated constructor stub
		if (Obstacle.table == null)
			this.table = table;

		x = MainFrame.WINDOW_WIDTH;
		y = MainFrame.WINDOW_HEIGHT / 2;
	}

	public Obstacle(shadowNinja.test.CollapseTest.Table table2) {
		// TODO Auto-generated constructor stub
		if (Obstacle.table == null)
			this.table = table;

		x = MainFrame.WINDOW_WIDTH;
		y = MainFrame.WINDOW_HEIGHT / 2;
	}

	/**
	 * 障碍物向玩家移动的方法
	 */
	public void step() {

		x -= speed;
	}

	/**
	 * 绘制障碍物的方法
	 * 
	 * @param g Graphics类
	 */
	public void paintObstacle(Graphics g) {
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}

	public void drawSelf(Graphics2D g2d) {
		if (image == null) {
			System.out.println("no image!");
			return;
		}
		g2d.drawImage(image, (int) (x - Obstacle.WIDTH / 2.0f), (int) (y - Obstacle.HEIGHT / 2.0f), Obstacle.WIDTH,
				Obstacle.HEIGHT, null);

	}

	/**
	 * 返回中心高度位置，便于碰撞检测
	 * 
	 * @return
	 */
	public int core() {
		return this.y + HEIGHT / 2;
	}

	/**
	 * 判断是否有撞击发生
	 * 
	 * @return
	 */
	public boolean isCollapse() {

		for (Bullet bullet : table.getBullets()) {
			// System.out.println("bulletCore:" + bullet.core());
			// System.out.println("ObstacleCore:" + this.core());
			if (bullet.getX() + Bullet.BULLET_WIDTH >= this.getX()
					&& Math.abs(bullet.core() - this.core()) < Math.max(HEIGHT / 2, Bullet.BULLET_HEIGHT / 2)) {
				// System.out.println("bullet pang!");
				// table.getBullets().remove(bullet);
				return true;
			}
		}

		for (Fire fire : table.getFire()) {
			// System.out.println("bulletCore:"+bullet.core());
			// System.out.println("ObstacleCore:"+this.core());
			if (fire.getX() + Fire.FIRE_WIDTH >= this.getX()
					&& Math.abs(fire.core() - this.core()) < Math.max(Fire.FIRE_HEIGHT / 2, HEIGHT / 2)) {
				System.out.println("Fire pang!");
				return true;
			}
		}
		Person ps = table.getPerson();
		if (ps.getX() + Person.WIDTH >= this.getX()
				&& Math.abs(ps.core() - this.core()) < Math.max(HEIGHT / 2, Person.HEIGHT / 2)) {
			// System.out.println("person pang!");
			damage += ps.getScore() / 10000;
			ps.loseHealth(damage);
			return true;
		}
		return false;

	}

	/**
	 * 判断障碍物是否越界的方法
	 * 
	 * @return
	 */
	public boolean outofBounds() {
		// return this.x <= -WIDTH;
		if (x <= 0) {
			// System.out.println("obs ouyut!");
			return true;
		}
		return false;

	}

	/**
	 * 加载图片
	 */
	public void loadImg() {
		BufferedImage origin_plane = null;
		// 加载图片
		try {
			origin_plane = ImageIO.read(Obstacle.class.getResource("/obstacles/arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		float scaleX = getWIDTH() / (float) origin_plane.getWidth();
		float scaleY = getHEIGHT() / (float) origin_plane.getHeight();

		// 拉伸各个颜色的小格子图片
		image = createScaledImg(origin_plane, scaleX, scaleY);
	}

	/**
	 * 拉伸图片
	 * 
	 * @param originImg
	 * @param scaleX
	 * @param scaleY
	 * @return
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
	 * 得到图片
	 * 
	 * @return
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
	 * 得到图片组
	 * 
	 * @return
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * 设置图片组
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
	 * 得到y坐标
	 * 
	 * @return
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
	 * @return
	 */
	public int getWIDTH() {
		return WIDTH;
	}

	/**
	 * 得到高度
	 * 
	 * @return
	 */
	public int getHEIGHT() {
		return HEIGHT;
	}

	/**
	 * 设置宽度
	 * 
	 * @param wIDTH
	 */
	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	/**
	 * 设置宽度
	 * 
	 * @param hEIGHT
	 */
	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

}
