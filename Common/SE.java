package Common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SE {
	public static final String seDir = System.getProperty("user.dir") + "/src/SE/";
	
	//インスタンス化と同時にサウンドエフェクトが出るコンストラクタ
	public SE(File path, int time )throws InterruptedException{
		Clip clip = createClip(path);
		clip.start();
		Thread.sleep(time);
		clip.close();
	}
	
	//引数のWAVファイルをopenした状態でClipクラスで戻す
	public static Clip createClip(File path) {
		try(AudioInputStream ais = AudioSystem.getAudioInputStream(path)){
			AudioFormat af = ais.getFormat();
			DataLine.Info dataLine = new DataLine.Info(Clip.class,af);
			Clip c = (Clip)AudioSystem.getLine(dataLine);
			
			c.open(ais);
			
			return c;
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
	}
}
