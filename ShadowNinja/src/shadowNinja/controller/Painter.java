package shadowNinja.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import shadowNinja.main.ui.MainFrame;
import shadowNinja.model.Bullet;
import shadowNinja.model.Enemy;
import shadowNinja.model.Fire;
import shadowNinja.model.Obstacle;
import shadowNinja.model.Rope;
import shadowNinja.model.Weapon;

/**
 * 此类为界面的“绘画类”，负责将游戏数据绘制到界面
 * 
 * @author 唐雷
 *
 */
public class Painter {
	// 定义每个格子的具体大小，像素数
	// 每一个格子的大小（像素数）
	static int ELEMENT_SIZE = 0;
	// 游戏桌面开始的格子数（偏移量）
	static final int TABLE_X = 1;
	static final int TABLE_Y = 5;
	// 绘图类，先使用直接方式，存一下我们的业务核心类Table
	private Table table;
	// 背景图片
	BufferedImage img_background;
	// 创建武器对象，仅供显示cd使用
	private Weapon weapon;

	/**
	 * 构造函数
	 * 
	 * @param table
	 * @param initWeapon
	 */
	public Painter(Table table, Weapon initWeapon) {
		this.table = table;
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

	/**
	 * 内部方法，用于初始化图片元素
	 * 
	 * @throws IOException
	 */
	private void loadImages() throws IOException {
		// 第一步：根据屏幕大小计算格子尺寸
		// 先确定界面需要的各种尺寸
		// int size1 = MainFrame.WINDOW_WIDTH / (Table.TABLE_WIDTH + TABLE_X + 4 + 2);
		// int size2 = MainFrame.WINDOW_HEIGHT / (Table.TABLE_HEIGHT + TABLE_Y + 2);

		// 选择较小的尺寸，作为每一个的大小（像素数）
		// ELEMENT_SIZE = size1 < size2 ? size1 : size2;

		// 第二步：加载原始图片
		// 从/res/imgs目录，加载所有原始照片（未拉伸）
		BufferedImage bg1 = ImageIO.read(Painter.class.getResource("/maps/" + MainFrame.term + ".png"));

		// 第三步：通过原始图片，创建出拉伸后的最终使用的图片，并给上面的属性赋值
		// 1.根据窗口大小，拉伸背景图
		// 计算窗口背景图宽高比例
		float scaleX = MainFrame.WINDOW_WIDTH / (float) bg1.getWidth();
		float scaleY = MainFrame.WINDOW_HEIGHT / (float) bg1.getHeight();

		// 创建拉伸后的背景图片
		img_background = createScaledImg(bg1, scaleX, scaleY);
	}

	/**
	 * 内部方法，绘制背景
	 */
	private void drawBackground(Graphics2D g2d) {

		table.getBackground().drawSelf(g2d);
		// System.out.println("draw background!");
	}

	/**
	 * 绘制人物
	 */
	private void drawPerson(Graphics2D g2d) {
		if (table.getPerson().isUnderRope()) {
			table.getPerson().paintPersondown(g2d);
		} else {
			table.getPerson().paintPersonup(g2d);
		}
	}

	/**
	 * 绘制所有子弹
	 */
	private void drawBullet(Graphics2D g2d) {

		synchronized (table) {

			List<Bullet> bullets = table.getBullets();

			// System.out.println("bullets" + bullets.size());
			for (Bullet b : bullets) {
				b.drawSelf(g2d);
			}
		}
	}

	/**
	 * 绘制所有障碍物
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

	/**
	 * 绘制所有敌人
	 */
	private void drawEnemy(Graphics2D g2d) {

		synchronized (table) {
			List<Enemy> enemies = table.getEnemies();
			// System.out.println("enemies" + enemies.size());
			for (Enemy enemy : enemies) {
				enemy.drawSelf(g2d);
			}
		}
	}

	/**
	 * 绘制所有火焰
	 */
	private void drawFire(Graphics2D g2d) {
		List<Fire> fires = table.getFire();

		synchronized (table) {
			for (Fire fire : fires) {
				fire.drawSelf(g2d);

			}
		}
	}

	/**
	 * 绘制所有绳子
	 */
	private void drawRope(Graphics2D g2d) {
		synchronized (table) {
			Rope rope = table.getRope();
			rope.drawSelf(g2d);

		}
	}

	/**
	 * 显示分数
	 * 
	 * @param g2d
	 */
	private void drawScore(Graphics2D g2d) {
		table.getPerson().paintScore(g2d);
	}

	/**
	 * 显示进度条
	 * 
	 * @param g2d
	 */
	private void drawBar(Graphics2D g2d) {
		table.getPerson().paintBar(g2d);
	}

	/**
	 * 显示武器冷却时间
	 */
	private void drawCd(Graphics2D g2d) {
		synchronized (table) {
			weapon.drawCd(g2d);

		}
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

		// 绘制障碍物
		drawObs(g2d);

		// 绘制出所有子弹
		drawBullet(g2d);

		// 绘制敌人
		drawEnemy(g2d);

		// 绘制绳子
		drawRope(g2d);

		// 绘制火焰
		drawFire(g2d);

		// 绘制人物
		drawPerson(g2d);

		// 显示分数
		drawScore(g2d);

		// 如果不是无尽模式，显示进度条
		if (table.getIfBar()) {
			drawBar(g2d);
		}

		// 显示cd
		drawCd(g2d);
	}
}
