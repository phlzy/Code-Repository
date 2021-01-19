package shadowNinja.model;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import shadowNinja.controller.Table;
import shadowNinja.main.ui.MainFrame;
/**
 * 蜘蛛障碍物，继承Obstacle
 * @author 唐雷
 *
 */
public class Spider extends Obstacle{
/**
 * 构造方法
 * @param table
 */
	public Spider(Table table) {
		super(table);
		
		//导入图片
		try {
			image = ImageIO.read(new File("res/images/obstacles/spider.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//设置蜘蛛的位置
		x = MainFrame.WIDTH + 1500;
		y = random.nextInt(800);
		
		//设置蜘蛛的宽高
		setWIDTH(150);
		setHEIGHT(150);
	}
}
