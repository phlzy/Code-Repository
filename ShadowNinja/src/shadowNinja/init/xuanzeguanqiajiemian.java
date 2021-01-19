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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import shadowNinja.main.ui.MainFrame;
/**
 * @author 23159
 * @version 1.0
 */

public class xuanzeguanqiajiemian extends JFrame{
	private JButton first,second,third,forth,fifth,back;
	
/**
 * @param[无参构造方法]
 */
  public xuanzeguanqiajiemian() {
	  
	first=new JButton(" ");
	first.setFont(new Font("微软雅黑",Font.BOLD,8));
	first.setBorder(BorderFactory.createRaisedBevelBorder()); 
    first.setBounds(137,300,70,300);
	first.setEnabled(false);
	first.setContentAreaFilled(false); 
	first.setBorderPainted(false);
    this.add(first);
    first.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			
			dispose();
			try {
				new MainFrame(1,1);
			} catch (IOException e1) {
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
    
    second=new JButton(" ");
	second.setFont(new Font("微软雅黑",Font.BOLD,18));
	second.setBorder(BorderFactory.createRaisedBevelBorder()); 
	second.setBounds(330,300,70,300);
	second.setEnabled(false);
	second.setContentAreaFilled(false); 
	second.setBorderPainted(false);
	this.add(second);
	second.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			dispose();
			try {
				new MainFrame(1,2);
			} catch (IOException e1) {
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
    
	third=new JButton(" ");
	third.setFont(new Font("微软雅黑",Font.BOLD,18));
	third.setBounds(570,300,70,300);
	third.setEnabled(false);
	third.setContentAreaFilled(false); 
	third.setBorderPainted(false);
	this.add(third);
	third.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			dispose();
			try {
				new MainFrame(1,3);
			} catch (IOException e1) {
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
    
	forth=new JButton(" ");
	forth.setFont(new Font("微软雅黑",Font.BOLD,18));
	forth.setBorder(BorderFactory.createRaisedBevelBorder()); 
	forth.setBounds(790,300,70,300);
	forth.setEnabled(false);
	forth.setContentAreaFilled(false); 
	forth.setBorderPainted(false);
	this.add(forth);
	forth.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			dispose();
			try {
				new MainFrame(1,4);
			} catch (IOException e1) {
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
	  
	fifth=new JButton(" ");
	fifth.setFont(new Font("微软雅黑",Font.BOLD,18));
	fifth.setBorder(BorderFactory.createRaisedBevelBorder()); 
	fifth.setBounds(1000,300,70,300);
	fifth.setEnabled(false);
	fifth.setContentAreaFilled(false); 
	fifth.setBorderPainted(false);
	this.add(fifth);
	fifth.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			dispose();
			try {
				new MainFrame(1,5);
			} catch (IOException e1) {
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
	back=new JButton();
	back.setBorder(BorderFactory.createRaisedBevelBorder()); 
	back.setBounds(1100,600,75,75);
	back.setContentAreaFilled(false);
	back.setBorderPainted(false);
	back.setEnabled(false);
	this.add(back);
	back.addMouseListener(new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			dispose();
			new dengluhoujiemian();
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
	  
	  
	  
	  
			MainPanel panel = new MainPanel();
			this.add(panel);
			
			//设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 logo图标
			this.setSize(1200,720);//大小
			this.setLocationRelativeTo(null);//居中
			this.setUndecorated(false);//边框隐藏
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭
			this.setVisible(true);	
			String url = "src/shadowNinja/init/res/鼠标.png"; //储存鼠标图片的位置
			Toolkit tk = Toolkit.getDefaultToolkit(); 
			Image image = new ImageIcon(url).getImage(); 
			Cursor cursor = tk.createCustomCursor(image, new Point(20, 10), "norm"); 
			panel.setCursor(cursor);
		}
		 
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new xuanzeguanqiajiemian();
	}
	
		
		//2、创建背景面板MainPanel，实现背景图片功能
		class MainPanel extends JPanel {//创建的MainPanel类，在MainFrame中调用
		Image background;
		public MainPanel() {
			try {
				background = ImageIO.read(new File("src/shadowNinja/init/res/选关背景图.png"));
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
	
	
	
