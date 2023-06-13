package GameObject;

import javax.swing.ImageIcon;

import Common.ImageMethods;

public class Deck extends SetCard {
	private ImageIcon icon;	//山札の画像
	
	//各フィールドのゲッター
	public ImageIcon getImageIcon() {return this.icon;}
	
	//48枚のトランプを格納するコンストラクタ
	//画像URLがPC環境に依存しない様に改善する必要あり
	public Deck() {
		int componentNum = 1;	//トランプのコンテンツペイン番号は 1 から始まるため
		icon = new ImageIcon(ImageMethods.imgDir + "img_trump/back.png");
		String[] strings = {"spade","club","dia","heart"};
		for(String s: strings) {
			for(int i=1;i<=13;i++) {
				String fileName = s + "-" + i + ".png";
				ImageIcon icon = new ImageIcon(ImageMethods.imgDir + "img_trump/" + fileName);
				Card c = new Card(s,i,icon,componentNum);
				addCards(c);
				componentNum++;
			}
		}
	}
}