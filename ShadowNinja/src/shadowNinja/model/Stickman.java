package shadowNinja.model;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 火柴人，继承Person
 * @author 唐雷
 *
 */
public class Stickman extends Person {

	public Stickman() throws IOException {
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
		// for (int i = 0; i < imagesup.length / 2; i++) {
		for (int i = 0; i < imagesup.length; i++) {
			try {
				// imagesup[i] = ImageIO.read(new File("/run/StickMan/" + (i + 1) + ".png"));
				imagesup[i] = ImageIO.read(Stickman.class.getResource("/run/StickMan/" + (i+1) + ".png"));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		for (int i = 0; i < imagesup.length; ++i) {
			try {
				imagesdown[i] = ImageIO.read(Ninja.class.getResource("/run/StickMan/" + (i + 2 + 8) + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	}


