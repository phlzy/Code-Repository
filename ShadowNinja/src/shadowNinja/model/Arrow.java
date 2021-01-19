package shadowNinja.model;

import shadowNinja.controller.Table;
import shadowNinja.main.ui.MainFrame;

/**
 * 箭型障碍物，继承Obstacle类
 * 
 * @author 唐雷
 *
 */
public class Arrow extends Obstacle {
	/**
	 * 构造函数
	 * 
	 * @param table
	 */
	public Arrow(Table table) {
		super(table);
		// 加载图片
		loadImg();
		// 设置箭的位置
		x = MainFrame.WINDOW_WIDTH - 100;
		y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250;// MainFrame.WINDOW_HEIGHT/2 - Obstacle.getHEIGHT();
	}

	/**
	 * 测试用构造函数
	 * 
	 * @param table
	 */
	public Arrow(shadowNinja.test.ObstacleTest.Table table) {
		// TODO Auto-generated constructor stub
		super(table);
		// 加载图片
		loadImg();
		// 设置箭的位置
		x = MainFrame.WINDOW_WIDTH - 100;
		y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250;// MainFrame.WINDOW_HEIGHT/2 - Obstacle.getHEIGHT();
	}

	public Arrow(shadowNinja.test.CollapseTest.Table table) {
		// TODO Auto-generated constructor stub
		super(table);
		// 加载图片
		loadImg();
		// 设置箭的位置
		x = MainFrame.WINDOW_WIDTH - 100;
		y = MainFrame.WINDOW_HEIGHT - HEIGHT - 250;// MainFrame.WINDOW_HEIGHT/2 - Obstacle.getHEIGHT();
	}

}