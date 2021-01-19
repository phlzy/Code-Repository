package shadowNinja.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Painter;

import shadowNinja.controller.Table;

/**
 * 火焰类
 * @author 唐雷
 *
 */
public class Fire {
	//火焰的x坐标
	private int x;
	//火焰的y坐标
	private int y;
	//传入table来获取当前位置
	private Table table;
	//火焰的生命周期
	private float lifeTime;
	//子弹最终宽度
	public static final int FIRE_WIDTH = 240;
	//子弹最终高度
	public static final int FIRE_HEIGHT = 72;
	//拉伸后的子弹照片，保存起来
	private BufferedImage fireImg;
	
	/**
	 * 加载火焰图片，并按指定尺寸拉伸
	 */
	private void loadImg(){
		BufferedImage origin_plane = null;
		//加载图片
		try {
			origin_plane = ImageIO.read(Fire.class.getResource("/weapons/fire.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		float scaleX = FIRE_WIDTH / (float)origin_plane.getWidth();
		float scaleY = FIRE_HEIGHT / (float)origin_plane.getHeight();
		
		//拉伸各个颜色的小格子图片
		fireImg = createScaledImg(origin_plane, scaleX, scaleY);
	}
	/**
	 * 工具方法，通过一个原始图片对象，按照指定的宽、高缩放比率，拉伸它并返回拉伸后的图片对象
	 * @param originImg 原始图片
	 * @param scaleX 宽缩放比率
	 * @param scaleY 高缩放比率
	 * @return 返回拉伸后的目标照片对象
	 */
	public BufferedImage createScaledImg(BufferedImage originImg, float scaleX, float scaleY){
		//计算出要拉伸的图片目标宽、高
		int scaledWidth = (int)(originImg.getWidth()*scaleX);
		int scaledHeight = (int)(originImg.getHeight()*scaleY);
		
		//创建一个拉伸后的照片对象，准备通过原始照片拉伸它
		BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, originImg.getType());
		
		//将原始照片，按照缩放比率，绘制到目标拉伸照片对象中
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
		if (fireImg == null) {
			return;
		}

		// 跟随人物移动
		g2d.drawImage(fireImg, (table.getPerson().getX() + Person.WIDTH),(table.getPerson().getY() + Person.HEIGHT/2 - Fire.FIRE_HEIGHT/2), FIRE_WIDTH,
				FIRE_HEIGHT, null);
	}
	
	/**
	 * 返回中心高度位置，便于碰撞检测
	 * @return
	 */
	public int core()
	{
		return this.y + FIRE_HEIGHT/2;
	}
	/**
	 * 构造方法
	 * @param lifeTime
	 * @param table
	 */
	public Fire(float lifeTime,Table table)
	{
		this.lifeTime = lifeTime;
		this.table = table;
		setX();
		setY();
		loadImg();
	}
	/**
	 * 获取x坐标
	 * @return
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * 获取y坐标
	 * @return
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * 获取生命周期
	 * @return
	 */
	public float getLifeTime()
	{
		return lifeTime;
	}
	/**
	 * 设置x坐标
	 */
	public void setX()
	{
		this.x = table.getPerson().getX() + Person.WIDTH;
	}
	/**
	 * 设置y坐标
	 */
	public void setY()
	{
		this.y = (table.getPerson().getY() + Person.HEIGHT/2 - Fire.FIRE_HEIGHT/2);
	}
	/**
	 * 计时器
	 * @param passTime
	 */
	public void remainLifeTime(float passTime)
	{
		this.lifeTime -= passTime;
	}
	/**
	 * 是否应该删除
	 * @return boolean
	 */
	public boolean shouldEnd()
	{
		if(this.lifeTime > 0) return false;
		else return true;
	}
}
