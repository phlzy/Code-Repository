package shadowNinja.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import shadowNinja.main.ui.MainFrame;
import shadowNinja.main.ui.MainPanel;

/**
 * 人物基类
 * 
 * @author 唐雷
 *
 */
public abstract class Person {

	// current image (0-7 up, 8-15 down)
	protected Image imageup;
	protected Image imagedown;

	// all images
	protected Image[] imagesup;
	protected Image[] imagesdown;

	// Person's data
	public static final int WIDTH = 120;
	public static final int HEIGHT = 120;
	public static final int initX = 90;
	public static final int MAX_HEALTH = 100;

	// velocity
	public static final double MAX_VELOCITY = 1100;
	public static final double MAX_E = MAX_VELOCITY * MAX_VELOCITY / 2.0;

	// jump status
	public static final int BALANCE = 0;
	public static final int RISE = 1;
	public static final int DROP = -1;
	protected int jumpcnt, status;
	protected double dt, v0;

	// constants
	public static final double eps = 0.1;
	public static final double g = 2500;
	public static final int floor = MainFrame.WINDOW_HEIGHT - HEIGHT - 286;

	// initial place
	protected int x, y;

	// index of image
	int index;

	// 玩家血量
	private double health;
	private BufferedImage healthImg;
	private BufferedImage lostImg;
	private static int healthImgHeight = 10;

	// 进度条
	private BufferedImage labelImg;
	private BufferedImage barImg;

	// player's score
	private long score;
	private long startTime;
	private long endTime;

	// running distance
	private int distance;

	// player's total score
	private int totalScore;

	// up or down
	private boolean underRope;

	/**
	 * 构造函数
	 */
	public Person() {
		init();
		imageup = imagesup[0];
		imagedown = imagesdown[0];
		x = initX;
		y = floor;
		health = 100;
		// 加载血条
		BufferedImage origin = null;
		// 加载图片
		try {
			origin = ImageIO.read(Person.class.getResource("/maps/healthImg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		float scaleY = WIDTH / (float) origin.getWidth();
		float scaleX = healthImgHeight / (float) origin.getHeight();

		// 拉伸各个颜色的小格子图片
		healthImg = createScaledImg(origin, scaleX, scaleY);

		// 加载掉血条
		origin = null;
		// 加载图片
		try {
			origin = ImageIO.read(Person.class.getResource("/maps/rope5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		scaleY = WIDTH / (float) origin.getWidth();
		scaleX = healthImgHeight / (float) origin.getHeight();
		lostImg = createScaledImg(origin, scaleX, scaleY);

		// 加载进度条标签
		origin = null;
		// 加载图片
		try {
			origin = ImageIO.read(Person.class.getResource("/maps/label.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		scaleY = 20 / (float) origin.getWidth();
		scaleX = 20 / (float) origin.getHeight();
		labelImg = createScaledImg(origin, scaleX, scaleY);

		// 加载进度条
		origin = null;
		// 加载图片
		try {
			origin = ImageIO.read(Person.class.getResource("/maps/bar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		scaleX = (MainFrame.WINDOW_WIDTH / 5) / (float) origin.getWidth();
		scaleY = 7 / (float) origin.getHeight();
		barImg = createScaledImg(origin, scaleX, scaleY);

		index = 0;
		score = 0;
		distance = 0;
		totalScore = 0;
		jumpcnt = 0;
		dt = 1.0 / (double) MainPanel.FPS;
		v0 = 0.0;
		status = BALANCE;
		underRope = false;
	}

	/**
	 * 抽象方法
	 */
	public abstract void init();

	/**
	 * jump on rope
	 */
	public void jumpup() {
		if (underRope) {
			y = floor;
			v0 = 0;
			jumpcnt = 0;
			status = BALANCE;
			underRope = false;
		} else if (jumpcnt < 2) {
			if (v0 <= eps) {
				v0 = MAX_VELOCITY;
			} else {
				double E = v0 * v0 / 2.0 + MAX_E;
				v0 = Math.sqrt(E * 2.0);
			}
			// v0 = v0 <= 0 ? MAX_VELOCITY : v0 + MAX_VELOCITY;
			status = RISE;
			jumpcnt++;
		}
	}

	public void reset() {
//		init();
//		imageup = imagesup[0];
//		imagedown = imagesdown[0];
//		x = initX;
//		y = floor;
//		health = 100;
//		// 加载血条
//		BufferedImage origin = null;
//		// 加载图片
//		try {
//			origin = ImageIO.read(Person.class.getResource("/maps/healthImg.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
//		float scaleY = WIDTH / (float) origin.getWidth();
//		float scaleX = healthImgHeight / (float) origin.getHeight();
//
//		// 拉伸各个颜色的小格子图片
//		healthImg = createScaledImg(origin, scaleX, scaleY);
//
//		// 加载掉血条
//		origin = null;
//		// 加载图片
//		try {
//			origin = ImageIO.read(Person.class.getResource("/maps/rope5.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
//		scaleY = WIDTH / (float) origin.getWidth();
//		scaleX = healthImgHeight / (float) origin.getHeight();
//		lostImg = createScaledImg(origin, scaleX, scaleY);
//
//		// 加载进度条标签
//		origin = null;
//		// 加载图片
//		try {
//			origin = ImageIO.read(Person.class.getResource("/maps/label.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
//		scaleY = 20 / (float) origin.getWidth();
//		scaleX = 20 / (float) origin.getHeight();
//		labelImg = createScaledImg(origin, scaleX, scaleY);
//
//		// 加载进度条
//		origin = null;
//		// 加载图片
//		try {
//			origin = ImageIO.read(Person.class.getResource("/maps/bar.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
//		scaleX = (MainFrame.WINDOW_WIDTH / 5) / (float) origin.getWidth();
//		scaleY = 7 / (float) origin.getHeight();
//		barImg = createScaledImg(origin, scaleX, scaleY);
//
//		index = 0;
//		score = 0;
//		distance = 0;
//		totalScore = 0;
//		jumpcnt = 0;
//		dt = 1.0 / (double) MainPanel.FPS;
//		v0 = 0.0;
//		status = BALANCE;
//		underRope = false;
	}

	/**
	 * jump under rope
	 */
	public void jumpdown() {
		y += 25;
		if (y >= floor + 1) {
			y = floor + 1;
		}
	}

	/**
	 * 玩家在绳子上自由下落方法
	 */
	public void dropup() {

		y += 5;
		if (y >= floor + 1) {
			y = floor + 1;
		}
	}

	/**
	 * drop to land
	 */
	public void dropdown() {
		if (underRope) {
			return;
		}
		if (status == RISE) {
			double dy = v0 * dt - g * dt * dt / 2.0;
			if (dy > eps && v0 > eps) {
				y -= (int) Math.ceil(dy);
				v0 -= g * dt;
			} else {
				status = DROP;
				v0 = 0.0;
			}
		} else if (status == DROP) {
			double dy = v0 * dt - g * dt * dt / 2.0;
			if (y - (int) Math.floor(dy) >= floor) {
				status = BALANCE;
				v0 = 0.0;
				y = floor;
			} else {
				v0 -= g * dt;
				y -= (int) Math.floor(dy);
			}
		} else {
			y = floor;
			v0 = 0.0;
			jumpcnt = 0;
		}
	}

	/**
	 * 拉伸图片
	 * 
	 * @param originImg
	 * @param scaleWidth
	 * @param scaleHeight
	 * @return
	 */
	private BufferedImage createScaledImg(BufferedImage originImg, float scaleWidth, float scaleHeight) {
		// 计算出要拉伸的图片目标宽、高
		int scaledWidth = (int) (originImg.getWidth() * scaleWidth);
		int scaledHeight = (int) (originImg.getHeight() * scaleHeight);

		// 创建一个拉伸后的照片对象，准备通过原始照片拉伸它
		BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, originImg.getType());

		// 将原始照片，按照缩放比率，绘制到目标拉伸照片对象中
		Graphics g = newImage.getGraphics();
		g.drawImage(originImg, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return newImage;
	}

	/**
	 * 人物翻下绳子
	 */
	public void flip() {
		if (!underRope) {
			underRope = true;
			jumpcnt = 0;
			v0 = 0.0;
			y = floor + HEIGHT;
			status = BALANCE;
		}
	}

	/**
	 * running on rope
	 */
	public void stepup() {

		imageup = imagesup[index++ / 4 % imagesup.length];

		if (index > 4 * imagesup.length) {
			index -= 4 * imagesup.length;
		}
		distance += 2;
	}

	/**
	 * running under rope
	 */
	public void stepdown() {

		imagedown = imagesdown[index++ / 4 % imagesdown.length];
		if (index > 4 * imagesdown.length) {
			index -= 4 * imagesdown.length;
		}
		distance += 2;
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
	 * restore player's health
	 */
	public void restoreHealth() {

		health += 0.05;
		// full health
		if (health + eps >= (double) 100) {
			health = MAX_HEALTH;
		}
	}

	/**
	 * player loses health
	 */
	public void loseHealth(long damage) {

		health -= damage;
	}

	/**
	 * dead?
	 */
	public boolean lost() {
		if (health <= eps) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 玩家向上翻绳坐标改变的方法
	 */
	public void overturnup() {

		if (y == floor + 1) {// 若玩家在绳子下方

			y = floor - 1;

		}
	}

	/**
	 * 玩家向下翻绳坐标改变的方法
	 */
	public void overturndown() {

		if (y == floor - 1) {// 若玩家在绳子上方

			y = floor - 1;

		}
	}

	/**
	 * 开始游戏时调用这个开始计分
	 */
	public void startGame() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * 结束游戏时调用这个终止计分
	 */
	public void endGame() {
		endTime = System.currentTimeMillis();
	}

	/**
	 * 绘制玩家正向的方法
	 * 
	 * @param g Graphics类
	 */
	public void paintPersonup(Graphics g) {
		g.drawImage(imageup, x, y, WIDTH, HEIGHT, null);
		if (health <= 0)
			health = 0;
		if (health < 100) {
			// System.out.println("血条"+health+"------------");
			float scale = (float) health / 100;
			g.drawImage(healthImg, x, y - healthImgHeight + 10, (int) (scale * WIDTH), healthImgHeight, null);
			if (health == 0) {
				g.drawImage(lostImg, x, y - healthImgHeight + 10, WIDTH, healthImgHeight, null);
				return;
			}
			g.drawImage(lostImg, (int) (x + scale * WIDTH), y - healthImgHeight + 10, WIDTH - (int) (scale * WIDTH),
					healthImgHeight, null);
		}
	}

	/**
	 * 绘制玩家反向的方法
	 * 
	 * @param g Graphics类
	 */
	public void paintPersondown(Graphics g) {
		g.drawImage(imagedown, x, y, WIDTH, HEIGHT, null);
		if (health <= 0)
			health = 0;
		if (health < 100) {
			// System.out.println("血条"+health+"------------");
			float scale = (float) health / 100;
			g.drawImage(healthImg, x, y + healthImgHeight + 10 + HEIGHT, (int) (scale * WIDTH), healthImgHeight, null);
			if (health == 0) {
				g.drawImage(lostImg, x, y + healthImgHeight + 10 + HEIGHT, WIDTH, healthImgHeight, null);
				return;
			}
			g.drawImage(lostImg, (int) (x + scale * WIDTH), y + healthImgHeight + 10 + HEIGHT,
					WIDTH - (int) (scale * WIDTH), healthImgHeight, null);
		}
	}

	/**
	 * 显示分数
	 * 
	 * @param g2d
	 */
	public void paintScore(Graphics2D g2d) {
		endTime = System.currentTimeMillis();
		score = endTime - startTime;
		// System.out.println("得分 "+score+"----------");
		g2d.setFont(new Font("Chiller", Font.PLAIN, 35));
		g2d.setColor(new Color(192, 192, 192));
		// System.out.println("painting score--------");
		g2d.drawString(Long.toString(score), 0, 27);
	}

	/**
	 * 显示进度条
	 */
	public void paintBar(Graphics2D g2d) {
		long currentStamp = System.currentTimeMillis();
		float scale = (float) (currentStamp - startTime) / 60000;
		int location = (int) (scale * barImg.getWidth());

		// System.out.println("painting bar!!!!!!!!");
		g2d.drawImage(barImg, 4 * MainFrame.WINDOW_WIDTH / 5 - 30, 27, barImg.getWidth(), barImg.getHeight(), null);
		if (location >= barImg.getWidth()) {
			g2d.drawImage(labelImg, 4 * MainFrame.WINDOW_WIDTH / 5 - 30 - labelImg.getWidth() / 2 + barImg.getWidth(),
					27 - labelImg.getHeight() / 2, labelImg.getWidth(), labelImg.getHeight(), null);
			return;
		}
		g2d.drawImage(labelImg, 4 * MainFrame.WINDOW_WIDTH / 5 - 30 - labelImg.getWidth() / 2 + location,
				27 - labelImg.getHeight() / 2, labelImg.getWidth(), labelImg.getHeight(), null);
	}

	/**
	 * 死了就返回true
	 * 
	 * @return
	 */
	public boolean isDead() {
		if (health <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断玩家是否越界的方法
	 * 
	 * @return
	 */
	public boolean outOfBounds() {
		return this.x >= MainFrame.WINDOW_WIDTH || this.x <= -WIDTH;
	}

	/**
	 * 判断是否在绳子下面
	 * 
	 * @return
	 */
	public boolean isUnderRope() {
		return underRope;
	}

	// 以下为各属性的set,get方法
	/**
	 * 返回向上的当前人物图片
	 * 
	 * @return
	 */
	public Image getImageup() {
		return imageup;
	}

	/**
	 * 设置向上的当前人物图片
	 * 
	 * @param imageup
	 */
	public void setImageup(Image imageup) {
		this.imageup = imageup;
	}

	/**
	 * 返回向下的当前人物图片
	 * 
	 * @return
	 */
	public Image getImagedown() {
		return imagedown;
	}

	/**
	 * 设置向下的当前人物图片
	 * 
	 * @param imagedown
	 */
	public void setImagedown(Image imagedown) {
		this.imagedown = imagedown;
	}

	/**
	 * 返回向上的人物图片数组
	 * 
	 * @return
	 */
	public Image[] getImagesup() {
		return imagesup;
	}

	/**
	 * 设置向上的人物图片数组
	 * 
	 * @param imagesup
	 */
	public void setImagesup(Image[] imagesup) {
		this.imagesup = imagesup;
	}

	/**
	 * 返回向下的人物图片数组
	 * 
	 * @return
	 */
	public Image[] getImagesdown() {
		return imagesdown;
	}

	/**
	 * 设置向下的人物图片数组
	 * 
	 * @param imagesdown
	 */
	public void setImagesdown(Image[] imagesdown) {
		this.imagesdown = imagesdown;
	}

	/**
	 * 返回人物横坐标
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * 设置人物横坐标
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 返回人物纵坐标
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * 设置人物纵坐标
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 返回人物宽度
	 * 
	 * @return
	 */
	public static int getWidth() {
		return WIDTH;
	}

	/**
	 * 返回人物高度
	 * 
	 * @return
	 */
	public static int getHeight() {
		return HEIGHT;
	}

	/**
	 * 返回index
	 * 
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 设置index
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 返回人物分数
	 * 
	 * @return
	 */
	public long getScore() {
		return score;
	}

	/**
	 * 设置人物分数
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 返回跑过距离
	 * 
	 * @return
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * 设置跑过距离
	 * 
	 * @param distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * 返回总分
	 * 
	 * @return
	 */
	public int getTotalScore() {
		return (int) (score * 10 + distance * 0.6);
	}

	/**
	 * 设置总分
	 * 
	 * @param totalScore
	 */
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * 返回生命
	 * 
	 * @return
	 */
	public int getHealth() {
		return (int) health;
	}

	/**
	 * 设置生命
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
}
