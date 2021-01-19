package shadowNinja.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import shadowNinja.main.ui.MainFrame;

/**
 * Rope is an attribute of Map.
 *
 * @author 梁芮槐-2019302789
 * @see Map
 */
public class Rope {
	private BufferedImage src; 									// 读到缓冲区的原图像
	public static String srcPath = "/imgs/maps/rope5.png"; 		// 文件路径
	private int height; 										// 图像高度
	private int width; 											// 图像宽度
	private Point leftUp = new Point(); 						// 图像原点
	private int WINDOW_WIDTH = MainFrame.WINDOW_WIDTH;			// 游戏窗口的宽度
	private int WINDOW_HEIGHT = MainFrame.WINDOW_HEIGHT;		// 游戏窗口的高度

	/**
	 * Constructor with file path.
	 * 
	 * @param initSrc Initial file path.
	 * @throws IOException
	 */
	public Rope() throws IOException {
		// src = ImageIO.read(new FileInputStream(srcPath));
		src = ImageIO.read(Rope.class.getResource("/maps/rope5.png"));
		BufferedImage origin_plane = null;
		// 加载图片
		try {
			origin_plane = ImageIO.read(Bullet.class.getResource("/maps/rope5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		float scaleX = WINDOW_WIDTH / (float) origin_plane.getWidth();
		float scaleY = (float) 1.3333;

		// 拉伸各个颜色的小格子图片
		//image = createScaledImg(origin_plane, scaleX, scaleY);
		src = createScaledImg(origin_plane, scaleX, scaleY);
		
		height = src.getHeight();
		width = src.getWidth();
	}

	/**
	 * Set the origin of the Rope coordinate.
	 * 
	 * @param initLeftUp Initial origin coordinate.
	 */
	public void setLeftUp(Point initLeftUp) {
		leftUp = initLeftUp;
	}

//	/**
//	 * 设置游戏窗口大小
//	 */
//	public void setWindow(int width, int height){
//		WINDOW_WIDTH=width;
//		WINDOW_HEIGHT=height;
//	}

	/**
	 * Get the origin coordinate.
	 * 
	 * @return leftUp(Point).
	 */
	public Point getLeftUp() {
		return leftUp;
	}

	/**
	 * Get the y of rope(up)
	 * 
	 * @return
	 */
	public int getY() {
		return (int) leftUp.getY();
	}

	/**
	 * Get height.
	 * 
	 * @return height(int).
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get width.
	 * 
	 * @return width(int).
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the source image.
	 * 
	 * @return src(Image).
	 */
	public Image getSrc() {
		return src;
	}

	/**
	 * Get the image file path.
	 * 
	 * @return srcPath(String).
	 */
	public String getSrcPath() {
		return srcPath;
	}

	private BufferedImage createScaledImg(BufferedImage originImg, float scaleX, float scaleY){
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
	* 画出绳子
	 */
	public void drawSelf(Graphics2D g2d){
		if(src==null)
			return;

		if(leftUp==null)
			return;

		g2d.drawImage(src, leftUp.x, leftUp.y, null);
	}

	/**
	* 画出地面
 	*/
	public void fillLand(Graphics2D g2d){
		if(src==null)
			return;

		if(leftUp==null)
			return;

		for(int i=1;i<=((800-leftUp.y)/20-1);i++)
		{
			g2d.drawImage(src, leftUp.x, leftUp.y+i*src.getHeight(), null);
		}
	}
}
