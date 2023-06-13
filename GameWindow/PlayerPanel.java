package GameWindow;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Common.ImageMethods;
import GameObject.Deck;
import GameObject.NonPlayerCharacter;
import GameObject.Player;
import GameObject.PlayerCharacter;

public class PlayerPanel extends JPanel {
	private static final int WIDTH = 1000;  // 画面の幅
    private static final int HEIGHT = 200; // 画面の高さ
	private Player p;	//プレイヤーオブジェクト
	
	//フィールドのゲッター
	public Player getPlayer() {return this.p;}
	
	protected PlayerPanel(String playerType, Deck d) {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		//パネルに裏側手札を非表示で追加
		ImageIcon backIcon = new ImageIcon(ImageMethods.imgDir + "img_trump/back.png");
		backIcon = ImageMethods.smallImageIcon(backIcon, 0.13);
		JLabel backJLabel = new JLabel(backIcon);
		backJLabel.setVisible(false);
		add(backJLabel);
		
		//パネルにすべてカードを非表示で追加
		String[] strings = {"spade","club","dia","heart"};
		for(String s: strings) {
			for(int i=1;i<=13;i++) {
				String fileName = s + "-" + i + ".png";
				ImageIcon icon = new ImageIcon(ImageMethods.imgDir + "img_trump/" + fileName);
				icon = ImageMethods.smallImageIcon(icon, 0.15);
				JLabel jlabel = new JLabel(icon);
				jlabel.setVisible(false);
				add(jlabel);
			}
		}
		
		//プレイヤーかNPCの判定をし、初期手札として2枚引く
		if(playerType.equals("PC")) {
			p = new PlayerCharacter(d);
			getComponent(p.draw(d)).setVisible(true);	//ドローしたCardを手札リストに加え、パネル内のカードを表示する
			getComponent(p.draw(d)).setVisible(true);
		}else {
			p = new NonPlayerCharacter(d);
			NonPlayerCharacter npc =(NonPlayerCharacter)p;
			getComponent(npc.firstDraw(d)).setVisible(true);
			getComponent(npc.draw(d)).setVisible(true);
		}
		
	}
}
