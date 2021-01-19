package shadowNinja.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import shadowNinja.main.ui.MainFrame;

/**
 * 背景图
 * 
 * @author 唐雷
 *
 */
public class BackGround extends Movable {

	/**
	 * 构造函数
	 * 
	 * @param posX
	 * @param posY
	 * @param lifeCircle
	 */
	public BackGround(float posX, float posY, int lifeCircle) {
		super(posX, posY, lifeCircle);
		// TODO Auto-generated constructor stub
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		} // 加载子弹图片
	}

	// 定义每个格子的具体大小，像素数
	// 每一个格子的大小（像素数）
	public static int ELEMENT_SIZE = 0;
	// 游戏桌面开始的格子数（偏移量）
	public static final int TABLE_X = 1;
	public static final int TABLE_Y = 5;
	// 背景图片
	public BufferedImage img_background;

	/**
	 * 内部方法，用于初始化图片元素
	 * 
	 * @throws IOException
	 */
	private void loadImages() throws IOException {
		// 第一步：根据屏幕大小计算格子尺寸
		// 先确定界面需要的各种尺寸
		int size1 = MainFrame.WINDOW_WIDTH;
		int size2 = MainFrame.WINDOW_HEIGHT;

		// 选择较小的尺寸，作为每一个的大小（像素数）
		ELEMENT_SIZE = size1 < size2 ? size1 : size2;

		// 第二步：加载原始图片
		// 从/res/imgs目录，加载所有原始照片（未拉伸）
		BufferedImage origin_background = ImageIO
				.read(BackGround.class.getResource("/maps/" + MainFrame.term + ".png"));

		// 第三步：通过原始图片，创建出拉伸后的最终使用的图片，并给上面的属性赋值
		// 1.根据窗口大小，拉伸背景图
		// 计算窗口背景图宽高比例
		float scaleY = MainFrame.WINDOW_HEIGHT / (float) origin_background.getHeight();

		// 创建拉伸后的背景图片
		img_background = createScaledImg(origin_background, scaleY, scaleY);
	}

	// 构造方法

	/**
	 * 绘图
	 */
	@Override
	public void drawSelf(Graphics2D g2d) {
		g2d.drawImage(img_background, (int) posX, 0, img_background.getWidth(), img_background.getHeight(), null);
		g2d.drawImage(img_background, (int) (posX + img_background.getWidth()), 0, img_background.getWidth(),
				img_background.getHeight(), null);
		// System.out.println("posX=" + posX);
		// System.out.println("img_background.getWidth()=" + img_background.getWidth());
		posX -= 15;
		if (posX <= -img_background.getWidth()) {
			// System.out.println("refresh");
			posX = 0;
		}
	}
}
