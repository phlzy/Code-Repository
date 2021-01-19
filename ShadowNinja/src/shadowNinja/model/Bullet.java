package shadowNinja.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import shadowNinja.controller.Table;
import shadowNinja.main.ui.MainFrame;
/**
 * 子弹类
 * @author 唐雷
 *
 */
public class Bullet {
	// 子弹的x坐标
	private int x;
	// 子弹的y坐标
	private int y;
	// 子弹最终速度
	private static final int BULLET_SPEED = 10;
	// 子弹最终宽度
	public static final int BULLET_WIDTH = 200;
	// 子弹最终高度
	public static final int BULLET_HEIGHT = 120;
	// 拉伸后的子弹照片，保存起来
	private BufferedImage bulletImg;

	//table的索引
		private static Table table;
		/**
		 * 构造函数
		 * @param x
		 * @param y
		 * @param table
		 */
		public Bullet(float x, float y,Table table)
		{
			this.x = (int) x;
			this.y = (int) y;
			if(Bullet.table==null)this.table = table;
			loadImg();
		}
		
	/**
	 * 加载子弹图片，并按指定尺寸拉伸
	 */
	private void loadImg() {
		BufferedImage origin_plane = null;
		// 加载图片
		try {
			origin_plane = ImageIO.read(Bullet.class.getResource("/weapons/bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		float scaleX = BULLET_WIDTH / (float) origin_plane.getWidth();
		float scaleY = BULLET_HEIGHT / (float) origin_plane.getHeight();

		// 拉伸各个颜色的小格子图片
		bulletImg = createScaledImg(origin_plane, scaleX, scaleY);
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
 * 绘图方法
 * @param g2d
 */
	public void drawSelf(Graphics2D g2d) {
		if (bulletImg == null) {
			return;
		}

		// 图片中心位置作为当前物体坐标点，所以需要向左上方偏移半个图片尺寸
		g2d.drawImage(bulletImg, (int) (x - BULLET_WIDTH / 2.0f), (int) (y - BULLET_HEIGHT / 2.0f), BULLET_WIDTH,
				BULLET_HEIGHT, null);
	}

	
/**
 * 得到x坐标
 * @return
 */
	public float getX()
	{
		return x;
	}
/**
 * 得到y坐标
 * @return
 */
	public float getY()
	{
		return y;
	}
	
	/**
	 * 返回中心高度位置，便于碰撞检测
	 * @return
	 */
	public int core()
	{
		return (int)this.y + BULLET_HEIGHT/2;
	}
	
	
	/**
	 * 	判断是否有撞击发生, 该方法有问题
	 * @return
	 */
	public boolean isCollapse()
	{
		for(Obstacle obs:table.getObses())
		{
			System.out.println("bulletCore:"+this.core());
			System.out.println("ObstacleCore:"+obs.core());
			if(this.getX()+Bullet.BULLET_WIDTH>=obs.getX() && Math.abs(this.core()-obs.core())<Math.max(BULLET_HEIGHT/2, Obstacle.HEIGHT/2))
			{
				System.out.println("pang obstacle!");
				return true;
			}
		}
		
		for(Enemy em:table.getEnemies())
		{
			System.out.println("bulletCore:"+this.core());
			System.out.println("EnemyCore:"+em.core());
			if(this.getX()+Bullet.BULLET_WIDTH>=em.getX() && Math.abs(this.core()-em.core())<Math.max(BULLET_HEIGHT/2, Enemy.ENEMY_HEIGHT/2))
			{
				System.out.println("pang enemy!");
				return true;
			}
		}
		
		return false;
		
	}
	
/**
 * 令子弹飞行
 */
	public void move()
	{
		this.x += BULLET_SPEED;
	}
/**
 * 判断是否越界
 * @return
 */
	public boolean isOutOfBound()
	{
		if (this.x >= MainFrame.WINDOW_WIDTH) {
			return true;
		} else
			return false;
	}
}
