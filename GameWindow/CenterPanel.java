package GameWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Common.ImageMethods;
import Common.SE;
import GameObject.Deck;
import GameObject.Player;

public class CenterPanel extends JPanel {
	private static final int WIDTH = 1000;  // 画面の幅
    private static final int HEIGHT = 600; // 画面の高さ
	private Deck d;	//山札オブジェクト
	private GameWindow gw;	//ゲームウィンドウオブジェクト
	private JLabel progresText;	//ゲーム進行メッセージ
	
	//各フィールドのゲッター
	public Deck getDeck() {return this.d;}
	public JLabel getPrgresText() {return this.progresText;}
	
	protected CenterPanel(GameWindow gw) {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setLayout(new BorderLayout());
		
		this.d = new Deck();	//山札をインスタンス化
		this.gw = gw;
		
		//ゲーム進行メッセージの設定
		progresText = new JLabel("ラウンドスタート");
		progresText.setPreferredSize(new Dimension(1000,70));
		progresText.setFont(new Font("SansSerif", Font.PLAIN, 30));
		add(progresText,BorderLayout.NORTH);
		
		//余白パネルの設定
		JPanel margin = new JPanel();
		margin.setPreferredSize(new Dimension(1000,70));
		margin.setBackground(new Color(0,102,51));
		add(margin,BorderLayout.CENTER);
		
		//
		JPanel cp = new JPanel();
		cp.setPreferredSize(new Dimension(1000,400));
		cp.setBackground(new Color(0,102,51));
		
		//ドローボタンの設定
		ImageIcon icon = ImageMethods.smallImageIcon(this.d.getImageIcon(),0.15);	//ドローボタンの画像設定
		JButton drawButton = new JButton(icon);
		drawButton.addActionListener(new DrawActionListener());
		drawButton.setContentAreaFilled(true);
		drawButton.setBorderPainted(false);
		drawButton.setPreferredSize(new Dimension(120,180));
		cp.add(drawButton);
		
		//パスボタンの設定
		JButton pathButton = new JButton("パス");
		pathButton.addActionListener(new PathActionListener());
		pathButton.setContentAreaFilled(true);
		pathButton.setPreferredSize(new Dimension(100,50));
		drawButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cp.add(pathButton);
		
		add(cp,BorderLayout.SOUTH);
		
		
	}
	
	//ドローボタンのアクションリスナー
	class DrawActionListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(gw.pcTrun == true) {
				Player p = gw.pcp.getPlayer();
				gw.pcp.getComponent(p.draw(d)).setVisible(true);
				gw.pcTrun = false;	//ドローボタンを押されてもドローしない様にfalseにする
				
				//カードを引く音
				try {
					new SE (new File(SE.seDir + "nc41227.wav"),1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	//パスボタンアクションリスナー
	class PathActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(gw.pcTrun == true) {
				gw.pcPath = true;
				gw.pcTrun = false;
			}
		}
	}
}
