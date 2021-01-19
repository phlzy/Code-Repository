package shadowNinja.test;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import shadowNinja.init.InitFrame;

/**
 * This class implements a test for frame change.
 * @author Aidan Lew
 *
 */
public class FrameChangeTest {
	
	public FrameChangeTest() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// TODO Auto-generated constructor stub
		new InitFrame();
	}
	
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
	 new FrameChangeTest();	
	}
}
