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
import java.util.StringTokenizer;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 背包列表信息操作类
 * @author cyx
 * @version 1.0.0
 * @date 2020-12-29
 * @see User
 * @see LoginFrame
 */
public class PackbackObsFrame extends JFrame{
	
	//登录的用户信息
	public static User user;
	
	//当前佩戴的武器和人物
	public static int currentWeapon = 1;
	public static int currentPerson = 15;
	
	//当前页武器、人物信息
	public String weaponInformation = "";
	
	//左换右换装配武器按钮
	public JButton youhuan;
	public JButton zuohuan;
	
	//装配按钮
	public JButton confirm;
	
	//武器、人物信息显示框
	JTextArea Information1;
	
	private int k=0;
	private static int i = 1 ;
	//背包中元素数量
	
	//private static int num = PackbackFrame.numPerson + PackbackFrame.numWeapon;
	String[] p;
	int[] f;
	String[] names;
	
	
/**
 * 背包列表信息操作类构造函数
 * 
 * @throws UnsupportedAudioFileException
 * @throws IOException
 * @throws LineUnavailableException
 */
public PackbackObsFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	
	user = User.readUserInfomation(LoginFrame.user.getAccountNumber());
	//System.out.println(user.getPersonNumber(user));
	System.out.println(user.getWeapon()+ "1");
	System.out.println(PackbackFrame.numPerson + "??");
	System.out.println(PackbackFrame.numWeapon + "?");
	int num = PackbackFrame.numPerson + PackbackFrame.numWeapon;
	System.out.println(num);
	p = new String[num + 1];
	f = new int[num + 1];
	f[0]=0;
	p[0]=" ";
	//boolean flag;
	
	int j = 0;
		StringTokenizer st = new StringTokenizer(user.getWeapon(), " ");
		while (st.hasMoreTokens()) {
			
			j++;
			String w = st.nextToken();
			if(w.equals("Dao")) {
				p[j]="src/shadowNinja/init/res/1.png";
				f[j]=1;
			}else if(w.equals("DaKanDao")) {
				p[j]="src/shadowNinja/init/res/2.png";
				f[j]=2;
			}else if(w.equals("WuShiDao")) {
				p[j]="src/shadowNinja/init/res/3.png";
				f[j]=3;
			}else if(w.equals("LanBaoShiJianYa")) {
				p[j]="src/shadowNinja/init/res/4.png";
				f[j]=4;
			}else if(w.equals("DaXiaoDuiDao")) {
				p[j]="src/shadowNinja/init/res/5.png";
				f[j]=5;
			}else if(w.equals("FeiCuiDaoJu")) {
				p[j]="src/shadowNinja/init/res/6.png";
				f[j]=6;
			}else if(w.equals("DengLiZiBuQiang")) {
				p[j]="src/shadowNinja/init/res/7.png";
				f[j]=7;
			}else if(w.equals("ZhenDongZhiZhua")) {
				p[j]="src/shadowNinja/init/res/8.png";
				f[j]=8;
			}else if(w.equals("QiDongQuan")) {
				p[j]="src/shadowNinja/init/res/9.png";
				f[j]=9;
			}else if(w.equals("HuoYanGunBang")) {
				p[j]="src/shadowNinja/init/res/10.png";
				f[j]=10;
			}else if(w.equals("LueDuoZhiRen")) {
				p[j]="src/shadowNinja/init/res/11.png";
				f[j]=11;
			}else if(w.equals("XianXueShouGeZhe")) {
				p[j]="src/shadowNinja/init/res/12.png";
				f[j]=12;
			}else {
				System.out.println("  111  ");
			}
			
	}
		
		//System.out.println(user.getPerson()+ "12");
		StringTokenizer st1 = new StringTokenizer(user.getPerson(), " ");
		while (st1.hasMoreTokens()) {
			j++;
			String w = st1.nextToken();
			//System.out.println(w+".");
			if(w.equals("Ninja")) {  //1 忍者
				p[j]="src/shadowNinja/init/res/13.png";
				f[j]=13;
			}else if(w.equals("Panther")) {  //2  猎豹
				p[j]="src/shadowNinja/init/res/14.png";
				f[j]=14;
			}else if(w.equals("StickMan")) {  //3 火柴人
				p[j]="src/shadowNinja/init/res/15.png";
				f[j]=15;
			}else {
				System.out.println("!!");
			}
	}
	for(int p=1;p<= num;p++) {
		System.out.println(f[p]);
	}

		//调取当前页的武器、人物信息
		switch(f[i]) {
		case 1:
		{
			weaponInformation = "name=\"Dao\"\r\n"
					+ "type=1;\r\n"
					+ "detail=\"清除身前一格的障碍物和敌人\"\r\n"
					+ "rarity=\"B\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 2:
		{
			weaponInformation ="name=\"DaKanDao\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"清除身前两格的障碍物和敌人\"\r\n"
					+ "rarity=\"B\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 3:
		{
			weaponInformation ="name=\"WuShiDao\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"清除身前四格的障碍物和敌人\"\r\n"
					+ "rarity=\"A\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 4:
		{
			weaponInformation ="name=\"LanBaoShiJianYa\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"清除身体为圆心半径4格\r\n的障碍物和敌人\"\r\n"
					+ "rarity=\"S\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 5:
		{
			weaponInformation ="name=\"DaXiaoDuiDao\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"第一次清除身前四格的障碍物\r\n和敌人,第二次清除身前3格\"\r\n"
					+ "rarity=\"S\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 6:
		{
			weaponInformation ="name=\"FeiCuiDaoJu\"\r\n"
					+ "type=1;\r\n"
					+ "detail=\"清除身前与头上两格的\n障碍物和敌人\"\r\n"
					+ "rarity=\"S\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 7:
		{
			weaponInformation ="name=\"DengLiZiBuQiang\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"向前发射一个光弹,清除\n沿途所有障碍物和敌人\"\r\n"
					+ "rarity=\"SR\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 8:
		{
			weaponInformation ="name=\"ZhenDongZhiZhua\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"清除身前与头上三格\n的障碍物和敌人\"\r\n"
					+ "rarity=\"A\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 9:
		{
			weaponInformation ="name=\"QiDongQuan\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"连续两次清除身前一格\n的障碍物和敌人\"\r\n"
					+ "rarity=\"A\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 10:
		{
			weaponInformation ="name=\"HuoYanGunBang\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"发射火焰, 持续三秒, \n清除身前四格的障碍物和敌人\"\r\n"
					+ "rarity=\"SR\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 11:
		{
			weaponInformation ="name=\"LueDuoZhiRen\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"延迟一秒后清除身前七格\n的障碍物和敌人\"\r\n"
					+ "rarity=\"SR\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 12:
		{
			weaponInformation =" name=\"XianXueShouGeZhe\"\r\n"
					+ "type=1\r\n"
					+ "detail=\"清除身前八格的障碍物和敌人\"\r\n"
					+ "rarity=\"SSR\"\r\n"
					+ "currentLevel=1";
			break;
		}
		case 13:
		{
			weaponInformation ="Person Name = Ninja";
			break;
		}
		case 14:
		{
			weaponInformation ="Person Name = Panther";
			break;
		}
		case 15:
		{
			weaponInformation ="Person Name = StickMan";
			break;
		}	
		}
		
		//添加文本框到界面
		Information1 = new JTextArea();
		Information1.setBounds(730,140,250,190);
		Information1.setText(weaponInformation);
		Information1.setFont(new Font("微软雅黑",Font.BOLD,13));
		Information1.setForeground(Color.black );
		Information1.setOpaque(false); 
		Information1.setEditable(false);
		this.add(Information1);
		
//	System.out.println(i);
		
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
							 if(i< num) {
							    	i++;
							    }
							    else {
							    	i=1;
							    }	
							 try {
							    	dispose();
									new  PackbackObsFrame();
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
							    	i=num;
							    }			 
							 try {
							    	dispose();
									new PackbackObsFrame();
									k=i;
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
	//穿戴按钮
	   confirm = new JButton("装备");
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

					if(f[i]<=12) {
						    		currentWeapon = f[i];
						    	}else
						    	{
						    		currentPerson = f[i];
						    	}
					JOptionPane.showMessageDialog(null, "穿戴成功！");
						    	System.out.println("current weapon = " + currentWeapon);
						    	System.out.println("current person = " + currentPerson);
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



	 
				
	     
	     JButton back=new JButton();
			 	back.setBorder(BorderFactory.createRaisedBevelBorder()); 
			 	back.setBounds(290,80,70,70);
			 	back.setContentAreaFilled(false);
			 	back.setBorderPainted(false);
			 	back.setBorder(null);
			 	back.setEnabled(false);
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
	//System.out.println(num);
	new PackbackObsFrame();
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
			System.out.println("bag :"+i);
			System.out.println(p[i]);
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
	public static int curPerson()
	{
		return currentPerson-12;
	}
	
}




