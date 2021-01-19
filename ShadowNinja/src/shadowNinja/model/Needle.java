package shadowNinja.model;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import shadowNinja.controller.Table;
import shadowNinja.main.ui.MainFrame;

/**
 * 地刺障碍物，继承Obstacle
 * 
 * @author 唐雷
 *
 */
public class Needle extends Obstacle {
	/**
	 * 构造函数
	 * 
	 * @param table
	 */
	public Needle(Table table) {
		super(table);
		Random rd = new Random();
		int pt = rd.nextInt();
		loadImg();
		// 导入图片
		try {
			if (pt % 2 == 0) {
				image = ImageIO.read(Needle.class.getResource("/obstacles/needle.png"));
				x = MainFrame.WINDOW_WIDTH - 100;
				y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250;
			} else {
				image = ImageIO.read(Needle.class.getResource("/obstacles/needle2.png"));
				x = MainFrame.WINDOW_WIDTH - 100;
				y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250 + Obstacle.HEIGHT;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		// 设置地刺的宽高
		setWIDTH(100);
		setHEIGHT(100);
	}

	/**
	 * 测试用构造函数
	 * 
	 * @param table
	 */
	public Needle(shadowNinja.test.ObstacleTest.Table table) {
		// TODO Auto-generated constructor stub

		super(table);
		Random rd = new Random();
		int pt = rd.nextInt();
		loadImg();
		// 导入图片
		try {
			if (pt % 3 == 0) {
				image = ImageIO.read(Needle.class.getResource("/obstacles/needle.png"));
				x = MainFrame.WINDOW_WIDTH - 100;
				y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250;
			} else {
				image = ImageIO.read(Needle.class.getResource("/obstacles/needle2.png"));
				x = MainFrame.WINDOW_WIDTH - 100;
				y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250 + Obstacle.HEIGHT;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		// 设置地刺的位置

		// 设置地刺的宽高
		setWIDTH(100);
		setHEIGHT(100);
	}

	public Needle(shadowNinja.test.CollapseTest.Table table) {
		// TODO Auto-generated constructor stub

		super(table);
		Random rd = new Random();
		int pt = rd.nextInt();
		loadImg();
		// 导入图片
		try {
			if (pt % 3 == 0) {
				image = ImageIO.read(Needle.class.getResource("/obstacles/needle.png"));
				x = MainFrame.WINDOW_WIDTH - 100;
				y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250;
			} else {
				image = ImageIO.read(Needle.class.getResource("/obstacles/needle2.png"));
				x = MainFrame.WINDOW_WIDTH - 100;
				y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250 + Obstacle.HEIGHT;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		// 设置地刺的位置

		// 设置地刺的宽高
		setWIDTH(100);
		setHEIGHT(100);
	}
}
