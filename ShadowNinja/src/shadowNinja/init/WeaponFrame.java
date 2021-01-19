
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
import java.awt.image.BufferedImage;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 商店列表信息操作类
 * @author cyx&dzy
 * @version 1.0.0
 * @date 2020-12-29
 * @see User
 * @see LoginFrame
 */
public class WeaponFrame extends JFrame{
	
	//Line separator
	private final static String NEW_LINE = System.getProperty("line.separator");
	
	JLabel Information;
	JTextArea Information1;
	JLabel Money;
	
	//登录的用户信息
	public static User user;
	
	//当前页武器、人物信息
	public String weaponInformation = "";
	
	//左换右换装配武器按钮
	public JButton youhuan;
	public JButton zuohuan;
	
	//购买按钮
	public JButton confirm;

	private static int i=1;
	String[] p;
	String[] names;
	
	
/**
 * 
 * 商店列表信息操作类构造函数
 * @throws UnsupportedAudioFileException
 * @throws IOException
 * @throws LineUnavailableException
 */
public WeaponFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	
	//导入登录用户的数据
	user = LoginFrame.user;
	
	Money = new JLabel(Integer.toString(user.getCoin()));	
	Money.setBounds(825, 14, 150, 40);
	Money.setFont(new Font("微软雅黑",Font.BOLD,30));
	Money.setForeground(Color.cyan );
	this.add(Money);
	
	p = new String[16];
	p[0]=" ";
	for(int j=1;j<=15;j++) {
		p[j]="src/shadowNinja/init/res/"+j+".png";
	}
	System.out.println(i);
	
	//调取当前页的武器、人物信息
	switch(i) {
	case 1:
	{
		weaponInformation = "name=\"Dao\"\r\n"
				+ "type=1;\r\n"
				+ "detail=\"清除身前一格的障碍物和敌人\"\r\n"
				+ "rarity=\"B\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 2:
	{
		weaponInformation ="name=\"DaKanDao\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"清除身前两格的障碍物和敌人\"\r\n"
				+ "rarity=\"B\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 3:
	{
		weaponInformation ="name=\"WuShiDao\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"清除身前四格的障碍物和敌人\"\r\n"
				+ "rarity=\"A\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 4:
	{
		weaponInformation ="name=\"LanBaoShiJianYa\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"清除身体为圆心半径4格\r\n的障碍物和敌人\"\r\n"
				+ "rarity=\"S\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 5:
	{
		weaponInformation ="name=\"DaXiaoDuiDao\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"第一次清除身前四格的障碍物\r\n和敌人,第二次清除身前3格\"\r\n"
				+ "rarity=\"S\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 6:
	{
		weaponInformation ="name=\"FeiCuiDaoJu\"\r\n"
				+ "type=1;\r\n"
				+ "detail=\"清除身前与头上两格的\n障碍物和敌人\"\r\n"
				+ "rarity=\"S\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 7:
	{
		weaponInformation ="name=\"DengLiZiBuQiang\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"向前发射一个光弹,清除\n沿途所有障碍物和敌人\"\r\n"
				+ "rarity=\"SR\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 8:
	{
		weaponInformation ="name=\"ZhenDongZhiZhua\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"清除身前与头上三格\n的障碍物和敌人\"\r\n"
				+ "rarity=\"A\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 9:
	{
		weaponInformation ="name=\"QiDongQuan\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"连续两次清除身前一格\n的障碍物和敌人\"\r\n"
				+ "rarity=\"A\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 10:
	{
		weaponInformation ="name=\"HuoYanGunBang\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"发射火焰, 持续三秒, \n清除身前四格的障碍物和敌人\"\r\n"
				+ "rarity=\"SR\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 11:
	{
		weaponInformation ="name=\"LueDuoZhiRen\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"延迟一秒后清除身前七格\n的障碍物和敌人\"\r\n"
				+ "rarity=\"SR\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 12:
	{
		weaponInformation =" name=\"XianXueShouGeZhe\"\r\n"
				+ "type=1\r\n"
				+ "detail=\"清除身前八格的障碍物和敌人\"\r\n"
				+ "rarity=\"SSR\"\r\n"
				+ "currentLevel=1\r\n"
				+ "price=120";
		break;
	}
	case 13:
	{
		weaponInformation ="Person Name = Ninja\r\n"
				+ "price=120";
		break;
	}
	case 14:
	{
		weaponInformation ="Person Name = Panther\r\n"
				+ "price=120";
		break;
	}
	case 15:
	{
		weaponInformation ="Person Name = StickMan\r\n"
				+ "price=120";
		break;
	}	
	}
	System.out.println(weaponInformation);
	

	
	//添加武器、人物信息文本框
	Information1 = new JTextArea();
	Information1.setBounds(730,140,250,190);
	Information1.setText(weaponInformation);
	Information1.setFont(new Font("微软雅黑",Font.BOLD,13));
	Information1.setForeground(Color.black );
	Information1.setOpaque(false); 
	Information1.setEditable(false);
	this.add(Information1);
	
	//System.out.println(i);
	
	//向右转化按钮
	youhuan = new JButton(" ");
	youhuan.setBounds(690,250,50,140);
	youhuan.setContentAreaFilled(false);
	youhuan.setForeground(Color.BLACK);//前景色	
	youhuan.setBorder(null);
	youhuan.setEnabled(false);
	this.add(youhuan);
	youhuan.addMouseListener(new MouseListener(){
/**
  * @param[e][鼠标监听事件][抽象方法]
*/
			public void mouseClicked(MouseEvent e) {
					 if(i<15) {
					    	i++;
					    }
					    else {
					    	i=1;
					    }				 
					 try {
					    	dispose();
							new  WeaponFrame();
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
	
	
	//向左转换按钮
	zuohuan = new JButton(" ");
	zuohuan.setBounds(240,250,50,140);
	zuohuan.setContentAreaFilled(false);
	zuohuan.setForeground(Color.BLACK);//前景色	
	zuohuan.setBorder(null);
	zuohuan.setEnabled(false);
	this.add(zuohuan);
	zuohuan.addMouseListener(new MouseListener(){
/**
  * @param[e][鼠标监听事件][抽象方法]
*/
			public void mouseClicked(MouseEvent e) {
					 if(i>=2) {
					    	i--;
					    }
					    else {
					    	i=15;
					    }					 
					 try {
					    	dispose();
							new  WeaponFrame();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        }
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
		
	
	//购买按钮
	    confirm = new JButton("购买");
	    confirm.setFont(new Font("微软雅黑",Font.BOLD,18));
		confirm.setBounds(750,420,120,50);
		confirm.setFocusPainted(false);		
		confirm.setBackground(Color.darkGray); 
		confirm.setContentAreaFilled(true);
	    confirm.setBorderPainted(true);
	    confirm.setEnabled(false);
		this.add(confirm);
		confirm.addMouseListener(new MouseListener(){
			/**
			  * @param[e][鼠标监听事件][抽象方法]
			*/
		public void mouseClicked(MouseEvent e) {

			boolean flag = false;
			boolean flag1 = true;
			System.out.println(user.getAccountNumber() + NEW_LINE + user.getPassword() + NEW_LINE
					+ user.getWeapon() + NEW_LINE + user.getPerson() + NEW_LINE + user.getCoin()
					+ NEW_LINE + user.getBestScore() + NEW_LINE + user.getNumberOfPasses());
			switch(i) {
			case 1:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "Dao");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 2:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "DaKanDao");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 3:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "WuShiDao");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 4:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "LanBaoShiJianYa");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 5:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "DaXiaoDuiDao");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 6:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "FeiCuiDaoJu");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 7:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "DengLiZiBuQiang");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 8:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "ZhenDongZhiZhua");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 9:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "QiDongQuan");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 10:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "HuoYanGunBang");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 11:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user, "LueDuoZhiRen");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 12:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updateWeaponInformation(user,"XianXueShouGeZhe");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 13:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updatePersonInformation(user, "Ninja");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 14:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updatePersonInformation(user, "Panther");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			case 15:
			{
				try {
					if(user.getCoin() >= 120) {
						flag = user.updatePersonInformation(user, "StickMan");
						if(flag) {
							user.setCoin(user.getCoin() - 120);
							user.updateNewCoinInformation(user, user.getCoin());
						}
					}else {
						flag1 = false;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			
				
			}
			if(flag&&flag1) {
				JOptionPane.showMessageDialog(null, "购买成功！");
				dispose();
				try {
					new StoreFrame();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(flag1 == false) {
				JOptionPane.showMessageDialog(null, "金币不足！");
			}else  {
				JOptionPane.showMessageDialog(null, "该武器/人物已拥有！");
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
back.setBounds(290,80,70,70);
back.setContentAreaFilled(false);
back.setBorderPainted(false);
back.setEnabled(false);
back.setBorder(null);
this.add(back);
back.addMouseListener(new MouseListener(){

	
@Override
public void mousePressed(MouseEvent e) {
	dispose();
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
	panel.setOpaque(true);
 

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
	new WeaponFrame();
}


class MainPanel extends JPanel {//创建的MainPanel类，在MainFrame中调用
	Image background;	
	Image background1;
	Image background2;
	
	
/**
 * @param[构造一个具有背景图片的panel的方法]	
 */
	public MainPanel() {
		try {
			background = ImageIO.read(new File("src/shadowNinja/init/res/beijing.png"));
			background1 = ImageIO.read(new File(p[i]));
			background2=ImageIO.read(new File("src/shadowNinja/init/res/商店界面.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background2, 0, 0,1200,720, null);
		g.drawImage(background, 250, 60,710,510, null);
		g.drawImage(background1, 270, 120,400,300, null);
		}
	
}



}
