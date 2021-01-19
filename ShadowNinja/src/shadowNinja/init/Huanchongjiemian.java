package shadowNinja.init;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * 
 * @author Huey
 * @date 2020-11-18
 * 缓存加载界面:背景图片、进度条
 * 动态加载过程。（线程）
 * 
 */
public class Huanchongjiemian extends JFrame implements Runnable{
	Image background;
	//进度条
	JProgressBar jdt;
	
	//创建一个线程并启动
	public void Start(){
		Huanchongjiemian frame = new Huanchongjiemian();
		Thread t = new Thread(frame);//t代表线程
		//启动线程
	t.start();
	dispose();
	}
	
	
	public Huanchongjiemian() {
	    try {
			background= ImageIO.read(new File("src/shadowNinja/init/res/缓冲图片.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdt = new JProgressBar();
		jdt.setStringPainted(true);//加载以字符串形式呈现出来。0%
		jdt.setBackground(Color.ORANGE);
		this.add(BorderLayout.SOUTH,jdt);		
		//大小 568 * 340
		this.setSize(720,539);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setUndecorated(false);
		this.setIconImage(new ImageIcon("src/shadowNinja/init/res/缓冲图片.jpg").getImage());	
		this.setVisible(true);	
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		//绘制背景图片
		g.drawImage(background, 5, 5,710,510, null);
	}
	

	@Override
	public void run() {
		//启动线程后，线程具体执行的内容
		int [] values = {0,1,3,10,23,32,40,47,55,66,76,86,89,95,99,99,99,100};
		for(int i=0; i<values.length; i++){//循环遍历赋值
			jdt.setValue(values[i]);
			//线程休眠
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//200毫秒
		}
		new dengluhoujiemian();
		dispose();
	}
	
}