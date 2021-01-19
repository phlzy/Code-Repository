package shadowNinja.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import shadowNinja.controller.Table;
import shadowNinja.main.ui.MainFrame;

/**
 * 飞镖障碍物类，继承Obstacle
 * 
 * @author 唐雷
 *
 */
public class Dart extends Obstacle {
	// 飞镖速度
	int DART_SPEED = 15;
	// 飞镖宽度
	int WIDTH = 50;
	// 飞镖高度
	int HEIGHT = 50;

	/**
	 * 构造函数
	 * 
	 * @param table
	 */
	public Dart(Table table) {
		super(table);

		this.loadImg();

		// 导入图片
		try {
			image = ImageIO.read(Needle.class.getResource("/obstacles/dart.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (Obstacle.table == null)
			this.table = table;
		this.x = MainFrame.WINDOW_WIDTH - WIDTH;
		int tmp = MainFrame.WINDOW_HEIGHT - HEIGHT;
		// this.y = MainFrame.WINDOW_HEIGHT-ENEMY_HEIGHT;
		// this.y = table.getRope().getY();
		this.y = random.nextInt() % tmp + 1;

	}

	/**
	 * 测试用构造函数
	 * 
	 * @param table
	 */
	public Dart(shadowNinja.test.ObstacleTest.Table table) {
		// TODO Auto-generated constructor stub
		super(table);
		this.loadImg();

		// 导入图片
		try {
			image = ImageIO.read(Needle.class.getResource("/obstacles/dart.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		this.x = MainFrame.WINDOW_WIDTH - WIDTH;
		int tmp = MainFrame.WINDOW_HEIGHT - HEIGHT;
		// this.y = MainFrame.WINDOW_HEIGHT-ENEMY_HEIGHT;
		// this.y = table.getRope().getY();
		this.y = random.nextInt() % tmp + 1;
	}

	public Dart(shadowNinja.test.CollapseTest.Table table) {
		// TODO Auto-generated constructor stub
		super(table);
		this.loadImg();

		// 导入图片
		try {
			image = ImageIO.read(Needle.class.getResource("/obstacles/dart.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		this.x = MainFrame.WINDOW_WIDTH - WIDTH;
		int tmp = MainFrame.WINDOW_HEIGHT - HEIGHT;
		// this.y = MainFrame.WINDOW_HEIGHT-ENEMY_HEIGHT;
		// this.y = table.getRope().getY();
		this.y = random.nextInt() % tmp + 1;
	}

	/**
	 * 载入图片
	 */
	public void loadImg() {
		BufferedImage origin_plane = null;
		// 加载图片
		try {
			origin_plane = ImageIO.read(Bullet.class.getResource("/obstacles/dart.png"));
			// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
			float scaleX = 50 / (float) origin_plane.getWidth();
			float scaleY = 50 / (float) origin_plane.getHeight();

			// 拉伸各个颜色的小格子图片
			image = createScaledImg(origin_plane, scaleX, scaleY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 令飞镖移动
	 */
	public void step() {

		x -= DART_SPEED;
	}

	/**
	 * 得到飞镖宽度
	 * 
	 * @return
	 */
	public int getWIDtH() {
		return WIDTH;
	}

	/**
	 * 得到飞镖高度
	 */
	public int getHEIGHT() {
		return HEIGHT;
	}

	/**
	 * 复写绘图方法
	 */
	public void drawSelf(Graphics2D g2d) {
		if (image == null) {
			System.out.println("no image!");
			return;
		}

		// 图片中心位置作为当前物体坐标点，所以需要向左上方偏移半个图片尺寸
		g2d.drawImage(image, (int) (x - this.WIDTH / 2.0f), (int) (y - this.HEIGHT / 2.0f), this.WIDTH, this.HEIGHT,
				null);
	}
}
