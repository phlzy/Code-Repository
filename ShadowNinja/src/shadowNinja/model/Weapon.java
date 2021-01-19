package shadowNinja.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import shadowNinja.main.ui.MainFrame;
/**
 * 武器类，拥有武器的各项属性
 * @author 唐雷
 *
 */
public class Weapon {
	//武器名称
    private String name;  
    //武器类型，0 for defence, 1 for attack
    private int type;                           
    //细节描述
    private String detail;    
    //稀有度
    private String rarity;        
    //当前等级
    private int currentLevel;                   
    //武器的时间配置
    private WeaponTimeConfig weaponTimeConfig;  
    //武器的效果配置
    private WeaponEffect weaponEffect;         

    //可以使用技能的图片
    private BufferedImage yes;   
    //冷却还没过的图片
    private BufferedImage no;   
    //设置显示cd的位置与右下角两边各自的像素距离
    private static int position=200;            

    
    /**
     * Default constructor.
     */
    public Weapon()
    {
    	
    }

    /**
     * Constructor with params.
     * @param weaponID 1-12
     */
    public Weapon(int weaponID){
    	
    	//加载武器cd图片1
        BufferedImage origin=null;
        //加载图片
        try {
            origin = ImageIO.read(Weapon.class.getResource("/weapons/cd_yes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
        float scaleY = 50 / (float) origin.getWidth();
        float scaleX = 50 / (float) origin.getHeight();

        // 拉伸各个颜色的小格子图片
        yes = createScaledImg(origin, scaleX, scaleY);

        //加载武器cd图片2
        // 加载图片
        try {
            origin = ImageIO.read(Weapon.class.getResource("/weapons/cd_no.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 计算每一小格图片的拉伸比例（要求每一个小格子原始图片尺寸完全一样！）
        scaleY = 50 / (float) origin.getWidth();
        scaleX = 50 / (float) origin.getHeight();

        // 拉伸各个颜色的小格子图片
        no = createScaledImg(origin, scaleX, scaleY);
    	//选择武器
        switch (weaponID){
            case 1:
            {
                name="Dao";
                type=1;
                detail="清除身前一格的障碍物和敌人";
                rarity="B";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 5000);
                ArrayList<Character> Dao_C = new ArrayList<Character>();
                Dao_C.add('d');
                ArrayList<Integer> Dao_I = new ArrayList<Integer>();
                Dao_I.add(3*40);
                weaponEffect=new WeaponEffect(Dao_C, Dao_I, 
                                              1, false, false, false);
                break;
        }
            case 2:
            {
                name="DaKanDao";
                type=1;
                detail="清除身前两格的障碍物和敌人";
                rarity="B";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 5500);
                ArrayList<Character> DaKanDao_C = new ArrayList<Character>();
                DaKanDao_C.add('d');
                ArrayList<Integer> DaKanDao_I = new ArrayList<Integer>();
                DaKanDao_I.add(4*40);
                weaponEffect=new WeaponEffect(DaKanDao_C, DaKanDao_I, 
                                              1, false, false, false);
                break;
            }
            case 3:
            {
                name="WuShiDao";
                type=1;
                detail="清除身前四格的障碍物和敌人";
                rarity="A";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 5500);
                ArrayList<Character> WuShiDao_C = new ArrayList<Character>();
                WuShiDao_C.add('d');
                ArrayList<Integer> WuShiDao_I = new ArrayList<Integer>();
                WuShiDao_I.add(5*40);
                weaponEffect=new WeaponEffect(WuShiDao_C, WuShiDao_I, 
                                              1, false, false, false);
                break;
            }
            case 4:
            {
                name="LanBaoShiJianYa";
                type=1;
                detail="清除身体为圆心半径4格的障碍物和敌人";
                rarity="S";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 6000);
                ArrayList<Character> LanBaoShiJianYa_C = new ArrayList<Character>();
                LanBaoShiJianYa_C.add('s');
                ArrayList<Integer> LanBaoShiJianYa_I = new ArrayList<Integer>();
                LanBaoShiJianYa_I.add(4*40);
                weaponEffect=new WeaponEffect(LanBaoShiJianYa_C, LanBaoShiJianYa_I, 
                                              1, false, false, false);
                break;
            }
            case 5:
            {
                name="DaXiaoDuiDao";
                type=1;
                detail="第一次清除身前四格的障碍物和敌人, 第二次清除身前3格";
                rarity="S";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 6000);
                ArrayList<Character> DaXiaoDuiDao_C = new ArrayList<Character>();
                DaXiaoDuiDao_C.add('d','d');
                ArrayList<Integer> DaXiaoDuiDao_I = new ArrayList<Integer>();
                DaXiaoDuiDao_I.add(4*40,3*40);
                weaponEffect=new WeaponEffect(DaXiaoDuiDao_C, DaXiaoDuiDao_I, 
                                              2, false, false, false);
                break;
            }
            case 6:
            {
                name="FeiCuiDaoJu";
                type=1;
                detail="清除身前与头上两格的障碍物和敌人";
                rarity="S";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 5300);
                ArrayList<Character> FeiCuiDaoJu_C = new ArrayList<Character>();
                FeiCuiDaoJu_C.add('e');
                ArrayList<Integer> FeiCuiDaoJu_I = new ArrayList<Integer>();
                FeiCuiDaoJu_I.add(4*40);
                weaponEffect=new WeaponEffect(FeiCuiDaoJu_C, FeiCuiDaoJu_I, 
                                              1, false, false, false);
                break;
            }
            case 7:
            {
                name="DengLiZiBuQiang";
                type=1;
                detail="向前发射一个光弹, 清除沿途所有障碍物和敌人";
                rarity="SR";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 7000);
                ArrayList<Character> DengLiZiBuQiang_C = new ArrayList<Character>();
                DengLiZiBuQiang_C.add('d');
                ArrayList<Integer> DengLiZiBuQiang_I = new ArrayList<Integer>();
                DengLiZiBuQiang_I.add(-1);
                weaponEffect=new WeaponEffect(DengLiZiBuQiang_C, DengLiZiBuQiang_I, 
                                              1, false, true, false);
                break;
            }
            case 8:
            {
                name="ZhenDongZhiZhua";
                type=1;
                detail="清除身前与头上三格的障碍物和敌人";
                rarity="A";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 5500);
                ArrayList<Character> ZhenDongZhiZhua_C = new ArrayList<Character>();
                ZhenDongZhiZhua_C.add('e');
                ArrayList<Integer> ZhenDongZhiZhua_I = new ArrayList<Integer>();
                ZhenDongZhiZhua_I.add(3*40);
                weaponEffect=new WeaponEffect(ZhenDongZhiZhua_C, ZhenDongZhiZhua_I, 
                                              1, false, false, false);
                break;
            }
            case 9:
            {
                name="QiDongQuan";
                type=1;
                detail="连续两次清除身前一格的障碍物和敌人";
                rarity="A";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 5000);
                ArrayList<Character> QiDongQuan_C = new ArrayList<Character>();
                QiDongQuan_C.add('d','d');
                ArrayList<Integer> QiDongQuan_I = new ArrayList<Integer>();
                QiDongQuan_I.add(3*40,3*40);
                weaponEffect=new WeaponEffect(QiDongQuan_C, QiDongQuan_I, 
                                              2, true, false, false);
                break;
            }
            case 10:
            {
                name="HuoYanGunBang";
                type=1;
                detail="发射火焰, 持续三秒, 清除身前四格的障碍物和敌人";
                rarity="SR";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 1, 7000);  // 释放+冷却 = cd
                ArrayList<Character> HuoYanGunBang_C = new ArrayList<Character>();
                HuoYanGunBang_C.add('d');
                ArrayList<Integer> HuoYanGunBang_I = new ArrayList<Integer>();
                HuoYanGunBang_I.add(4*40);
                weaponEffect=new WeaponEffect(HuoYanGunBang_C, HuoYanGunBang_I, 
                                              1, false, false, true);
                break;
            }
            case 11:
            {
                name="LueDuoZhiRen";
                type=1;
                detail="延迟一秒后清除身前七格的障碍物和敌人";
                rarity="SR";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(1000, 0, 5500);
                ArrayList<Character> LueDuoZhiRen_C = new ArrayList<Character>();
                LueDuoZhiRen_C.add('d');
                ArrayList<Integer> LueDuoZhiRen_I = new ArrayList<Integer>();
                LueDuoZhiRen_I.add(7*40);
                weaponEffect=new WeaponEffect(LueDuoZhiRen_C, LueDuoZhiRen_I, 
                                              1, false, false, false);
                break;
            }
            case 12:
            {
                name="XianXueShouGeZhe";
                type=1;
                detail="清除身前八格的障碍物和敌人";
                rarity="SSR";
                currentLevel=1;
                weaponTimeConfig=new WeaponTimeConfig(0, 0, 6000);
                ArrayList<Character> XianXueShouGeZhe_C = new ArrayList<Character>();
                XianXueShouGeZhe_C.add('d');
                ArrayList<Integer> XianXueShouGeZhe_I = new ArrayList<Integer>();
                XianXueShouGeZhe_I.add(8*40);
                weaponEffect=new WeaponEffect(XianXueShouGeZhe_C, XianXueShouGeZhe_I, 
                                              1, false, false, false);
                break;
            }
            default:
                break;
        }
    }

    /**
     * Update the cdStamp.
     */
    public void updateCdStamp(){
    	//System.out.println("update!!!!!!!!\n"+System.currentTimeMillis()+"\n");
        weaponTimeConfig.setStamp(System.currentTimeMillis());
    }

    /**
     * Get name.
     * @return Name(String).
     */
    public String getName(){
        return name;
    }

    /**
     * Get type.
     * @return Type(int).
     */
    public int getType(){
        return type;
    }

    /**
     * Get detail.
     * @return Detail(String).
     */
    public String getDetail(){
        return detail;
    }

    /**
     * Get rarity.
     * @return Rarity(String).
     */
    public String getRarity(){
        return rarity;
    }

    /**
     * Get currentLevel.
     * @return CurrentLevel(int).
     */
    public int getCurrentLevel(){
        return currentLevel;
    }

    /**
     * Get cd, 0 minimum.
     * @return Rest cd time.
     */
    public long getCd(){
        long cd=System.currentTimeMillis()-weaponTimeConfig.getLastStamp();
        if(cd>=weaponTimeConfig.getCd())
        {
        	//System.out.println("---------------------\n"+cd+"\n"+weaponTimeConfig.getLastStamp());
            return 0;
        }
        else {
            return weaponTimeConfig.getCd()-cd;
        }
    }

    /**
     * Get ifSuccessive.
     * @return ifSuccessive(boolean).
     */
    public boolean getIfSuccessive(){
        return weaponEffect.getIfSuccessive();
    }

    /**
     * Get stageNum.
     * @return stageNum(int).
     */
    public int getStageNum(){
        return weaponEffect.getStageNum();
    }

    /**
     * Get direction of specified stage.
     *
     * @param index The stage index
     * @return The direction(char).
     */
    public char getDirection(int index){
        return weaponEffect.getDirectionByIndex(index);
    }

    /**
     * Get distance of specified stage.
     *
     * @param index The stage index
     * @return The distance(int).
     */
    public int getDistance(int index){
        return weaponEffect.getDistanceByIndex(index);
    }
    /**
     * get the effect of the chosen weapon
     * @return weaponEffect
     */
    public WeaponEffect getWeaponEffect()
    {
    	return weaponEffect;
    }
    
    /**
     * 显示cd
     * @param g2d
     */
    public void drawCd(Graphics2D g2d){
        long currentStamp=System.currentTimeMillis();
        long restTime;
        if((restTime=(currentStamp-weaponTimeConfig.getLastStamp()))<=weaponTimeConfig.getCd())
        {
            g2d.drawImage(no, MainFrame.WINDOW_WIDTH-position, MainFrame.WINDOW_HEIGHT-position, no.getWidth(), no.getHeight(), null);
            g2d.setFont(new Font("Chiller",Font.PLAIN,25));
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawString(Long.toString((weaponTimeConfig.getCd()-restTime)/1000), MainFrame.WINDOW_WIDTH-position+17, MainFrame.WINDOW_HEIGHT-position+30);
        }else {
            g2d.drawImage(yes, MainFrame.WINDOW_WIDTH-position, MainFrame.WINDOW_HEIGHT-position, yes.getWidth(), yes.getHeight(), null);
        }
    }
/**
 * creat the image
 * @param originImg
 * @param scaleWidth
 * @param scaleHeight
 * @return
 */
    private BufferedImage createScaledImg(BufferedImage originImg, float scaleWidth, float scaleHeight){
        //计算出要拉伸的图片目标宽、高
        int scaledWidth = (int)(originImg.getWidth()*scaleWidth);
        int scaledHeight = (int)(originImg.getHeight()*scaleHeight);

        //创建一个拉伸后的照片对象，准备通过原始照片拉伸它
        BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, originImg.getType());

        //将原始照片，按照缩放比率，绘制到目标拉伸照片对象中
        Graphics g = newImage.getGraphics();
        g.drawImage(originImg, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return newImage;
    }
}