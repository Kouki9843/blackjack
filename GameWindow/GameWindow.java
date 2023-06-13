package GameWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import Common.SE;

public class GameWindow extends JFrame {
	CenterPanel cp = new CenterPanel(this);	//メッセージと山札パネルオブジェクト
	PlayerPanel pcp = new PlayerPanel("PC",cp.getDeck());	//プレイヤーパネルのオブジェクト
	PlayerPanel npcp = new PlayerPanel("NPC",cp.getDeck());	//NPCパネルのオブジェクト
	Boolean pcTrun = false;	//プレイヤーターンのブール値
	Boolean pcPath = false;	//プレイヤーパスのブール値
	Boolean npcPath = false;//NPCパスのブール値
	
	
	protected GameWindow(String title, int width, int height) {
		super(title);
		
		//ゲームBGMのインスタンス化
		GameBGM gb = new GameBGM();
		gb.start();
		
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowManage(gb));	//ウィンドウを閉じる前の処理
		setSize(width,height);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//プレイヤーパネルの設定
		pcp.setPreferredSize(new Dimension(1000,170));
		pcp.setOpaque(false);
		
		//NPCパネルの設定
		npcp.setPreferredSize(new Dimension(1000,170));
		npcp.setOpaque(false);
		
		//コンスタントペインに追加
		getContentPane().add(cp,BorderLayout.CENTER);
		getContentPane().add(pcp,BorderLayout.SOUTH);
		getContentPane().add(npcp,BorderLayout.NORTH);
		//コンスタントペインの背景色設定
		Color c = new Color(0,102,51);
		getContentPane().setBackground(c);
		
		setVisible(true);
	}
	
	//ゲームBGMのインナークラス
	class GameBGM extends Thread{
		Boolean bgmPlay = false;	//BGMを流し続けるかのブール値
			
		public GameBGM() {bgmPlay = true;}	//インスタンス化と同時にBGMをONにする
			
		public void run() {
			Clip clip = SE.createClip(new File(SE.seDir + "hometown_mixv2.wav"));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			while(bgmPlay == true){	//ウィンドウを閉じるまではBGMを流し続ける
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			clip.close();
		}
	}
	
	//ウィンドウリスナークラス
	class WindowManage extends WindowAdapter{
		private GameBGM gb;
		
		public WindowManage(GameBGM gb) {this.gb = gb;}
		
		//閉じる際にBGMをoffにする
		public void windowClosing(WindowEvent e) {
			gb.bgmPlay = false;
		}
	}
}
