package shadowNinja.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 可移动物体抽象父类，控制物体的移动和生存周期（毫秒数）
 * @author Administrator
 *
 */
public abstract class Movable {
	//x坐标
	float posX;
	//y坐标
	float posY;
	//生命周期单位为毫秒，0代表永生
	private int lifeCircle;
	//当前物体已经“度过”的生命毫秒数
	private int lifeDuration;
	/**
	 * 构造函数
	 * @param posX
	 * @param posY
	 * @param lifeCircle
	 */
	public Movable(float posX, float posY, int lifeCircle) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.lifeCircle = lifeCircle;
		this.lifeDuration = 0;//所有物体，一出生其已经过的生命周期都是0
	}
	/**
	 * 得到x坐标
	 * @return posX
	 */
	public float getPosX() {
		return posX;
	}
/**
 * 得到y坐标
 * @return posY
 */
	public float getPosY() {
		return posY;
	}



	/**
	 * 工具方法，通过一个原始图片对象，按照指定的宽、高缩放比率，拉伸它并返回拉伸后的图片对象
	 * @param originImg 原始图片
	 * @param scaleX 宽缩放比率
	 * @param scaleY 高缩放比率
	 * @return 返回拉伸后的目标照片对象
	 */
	public BufferedImage createScaledImg(BufferedImage originImg, float scaleX, float scaleY){
		//计算出要拉伸的图片目标宽、高
		int scaledWidth = (int)(originImg.getWidth()*scaleX);
		int scaledHeight = (int)(originImg.getHeight()*scaleY);
		
		//创建一个拉伸后的照片对象，准备通过原始照片拉伸它
		BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, originImg.getType());
		
		//将原始照片，按照缩放比率，绘制到目标拉伸照片对象中
		Graphics g = newImage.getGraphics();
		g.drawImage(originImg, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return newImage;
	}
	
	/**
	 * 抽象方法，子类实例必须能够把自己能够绘制出来
	 * @param g2d 绘制对象（画笔）
	 */
	public abstract void drawSelf(Graphics2D g2d);

	/**
	 * 给定一个绝对目标坐标，改变当前物体的坐标
	 * @param destX 目的X坐标
	 * @param destY
	 */
	public void changePositionAbsolute(float destX, float destY){
		this.posX = destX;
		this.posY = destY;
	}
	
	/**
	 * 通过一个偏移坐标（差值），改变当前物体的坐标
	 * @param deltaX
	 * @param deltaY
	 */
	public void changePositionRelative(float deltaX, float deltaY){
		this.posX += deltaX;
		this.posY += deltaY;
	}
	
	/**
	 * 让当前物体生命度过一段时间（毫秒数）
	 * @param lifePassed 度过的时间（毫秒）
	 * @return 当前生命是否该终结了；true表示生命周期已到；false表示生命周期未到
	 */
	public boolean lifePass(float lifePassed){
		if(lifeCircle == 0){
			//若当前物体为“永生”的，则直接返回false（计算生命周期无意义）
			return false;
		}
		
		//当前已度过的生命周期，加上本次度过的生命周期
		this.lifeDuration += lifePassed;
		
		//返回生命周期是否已经完结
		return lifeDuration >= lifeCircle;
	}
	
	/**
	 * 获知当前生命周期是否已到，该终结了
	 * @return true表示生命周期已到；false表示生命周期未到
	 */
	public boolean lifeEnded(){
		if(lifeCircle == 0){
			//若当前物体为“永生”的，则直接返回false（计算生命周期无意义）
			return false;
		}
		
		//返回生命周期是否已经完结
		return lifeDuration >= lifeCircle;
	}
/**
 * 设置x坐标
 * @param posX
 */
	public void setPosX(float posX) {
		this.posX = posX;
	}
/**
 * 设置y坐标
 * @param posY
 */
	public void setPosY(float posY) {
		this.posY = posY;
	}
/**
 * 设置生命周期
 * @param lifeCircle
 */
	public void setLifeCircle(int lifeCircle) {
		this.lifeCircle = lifeCircle;
	}
/**
 * 设置度过的时间
 * @param lifeDuration
 */
	public void setLifeDuration(int lifeDuration) {
		this.lifeDuration = lifeDuration;
	}
	
	
}

