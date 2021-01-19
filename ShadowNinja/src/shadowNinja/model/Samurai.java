package shadowNinja.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import shadowNinja.controller.Table;

/**
 * 武士敌人，继承Enemy
 * 
 * @author 唐雷
 *
 */
public class Samurai extends Enemy {
	/**
	 * 构造函数
	 * 
	 * @param table
	 */
	public Samurai(Table table) {
		super(table);
		loadImg();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 测试用构造函数
	 * 
	 * @param table
	 */
	public Samurai(shadowNinja.test.EnemyTest.Table table) {
		// TODO Auto-generated constructor stub
		super(table);
		loadImg();
	}

	public Samurai(shadowNinja.test.CollapseTest.Table table) {
		// TODO Auto-generated constructor stub
		super(table);
		loadImg();
	}

	/**
	 * 加载图片
	 */
	private void loadImg() {
		BufferedImage origin_plane = null;
		// 加载图片
		try {
			origin_plane = ImageIO.read(Samurai.class.getResource("/enemys/samurai.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
		float scaleX = ENEMY_WIDTH / (float) origin_plane.getWidth();
		float scaleY = ENEMY_HEIGHT / (float) origin_plane.getHeight();

		// 拉伸各个颜色的小格子图片
		image = createScaledImg(origin_plane, scaleX, scaleY);
	}
}
