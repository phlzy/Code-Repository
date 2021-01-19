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
 * 金币类
 * @author 唐雷
 *
 */
public class Coin extends Obstacle{
/**
 * 构造函数
 * @param table
 */
	public Coin(Table table) {
		super(table);
		
		images = new Image[6];
		
		//导入图片
		try {
			images[0] = ImageIO.read(new File("res/images/things/money1.png"));
			images[1]= ImageIO.read(new File("res/images/things/money2.png"));
			images[2]= ImageIO.read(new File("res/images/things/money3.png"));
			images[3]= ImageIO.read(new File("res/images/things/money4.png"));
			images[4]= ImageIO.read(new File("res/images/things/money5.png"));
			images[5]= ImageIO.read(new File("res/images/things/money6.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//随机金币图片
		image= images[random.nextInt(6)];
		
		//设置金币的位置
		x = MainFrame.WIDTH + 2500;
		y = random.nextInt(800);
		
		//设置金币的宽高
		setWIDTH(50);
		setHEIGHT(50);
	}
}
