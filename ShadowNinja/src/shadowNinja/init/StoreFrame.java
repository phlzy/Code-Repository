package shadowNinja.init;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * 商店总体界面类
 * @author cyx&dzy
 * @version 1.0.0
 * @date 2020-12-29
 * @see User
 * @see LoginFrame
 */
public class StoreFrame extends JFrame {
	
	
	//登录的用户信息
	public static User user;
		
	//菜单按钮
	JButton CaiDan;
	JLabel Money;

	/**
	 * 
	 * 商店总体界面类构造函数
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public StoreFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//导入登录用户的数据
		user = LoginFrame.user;
		
		Money = new JLabel(Integer.toString(user.getCoin()));	
		Money.setBounds(825, 14, 150, 40);
		Money.setFont(new Font("微软雅黑",Font.BOLD,30));
		Money.setForeground(Color.cyan );
		this.add(Money);
		
		
		
		CaiDan = new JButton("   ");
		CaiDan.setFont(new Font("微软雅黑",Font.BOLD,10));
		CaiDan.setBounds(90,70,100,50);
		CaiDan.setFocusPainted(false);		
		CaiDan.setContentAreaFilled(false);
	    CaiDan.setBorderPainted(false);
	    CaiDan.setEnabled(false);
		this.add(CaiDan);
		CaiDan.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				try {
					new WeaponFrame();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			});
	     
	     
JButton back=new JButton();
	back.setBorder(BorderFactory.createRaisedBevelBorder()); 
	back.setBounds(20,20,70,70);
	back.setContentAreaFilled(false);
	back.setBorderPainted(false);
	back.setEnabled(false);
	this.add(back);
	back.addMouseListener(new MouseListener(){	
	@Override
	public void mousePressed(MouseEvent e) {
		dispose();
		new dengluhoujiemian();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			
	}
	});
	 
		
		MainPanel panel = new MainPanel();//创建一个panel对象
		this.add(panel);//将panel添加到Frame中去
		
		//设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 
		this.setSize(1200,720);//大小
		this.setLocationRelativeTo(null);//居中
		this.setUndecorated(true);//边框隐藏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭
		this.setVisible(true);	//设置为可见
		//更换鼠标光标为自定义选项
		String url = "src/shadowNinja/init/res/鼠标.png"; //储存鼠标图片的位置
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Image image = new ImageIcon(url).getImage(); 
		Cursor cursor = tk.createCustomCursor(image, new Point(20, 10), "norm"); 
		panel.setCursor(cursor);
		

	}
	
	
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new StoreFrame();
	}
	
	/**
	 * 创建的MainPanel类，在MainFrame中调用
	 * @author cyx
	 *
	 */
	class MainPanel extends JPanel {//创建的MainPanel类，在MainFrame中调用
		Image background;	
	/**
	 * @param[构造一个具有背景图片的panel的方法]	
	 */
		public MainPanel() {
			try {
				background = ImageIO.read(new File("src/shadowNinja/init/res/商店界面.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(background, 0, 0,1200,720, null);
			}
			
	}
}
