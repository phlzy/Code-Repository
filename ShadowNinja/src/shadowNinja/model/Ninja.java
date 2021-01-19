package shadowNinja.model;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 忍者类，继承Person
 * @author 唐雷
 *
 */
public class Ninja extends Person {
//图片张数
	public static final int IMAGE_CNT = 8;
/**
 * 构造函数
 * @throws IOException
 */
	public Ninja() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * 抽象方法的实现
	 */
	public void init() {

		imagesup = new Image[8];
		imagesdown = new Image[8];

		for (int i = 0; i < IMAGE_CNT; ++i) {
			try {
				imagesup[i] = ImageIO.read(Ninja.class.getResource("/run/Ninja/" + (i + 1) + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < IMAGE_CNT; ++i) {
			try {
				imagesdown[i] = ImageIO.read(Ninja.class.getResource("/run/Ninja/" + (i + 2 + 8) + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
