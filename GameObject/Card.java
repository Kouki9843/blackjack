package GameObject;

import javax.swing.ImageIcon;

public class Card {
	private String pictrue;		//絵柄
	private int number;			//カード番号
	private ImageIcon icon;		//カード画像
	private int componentNum;	//トランプ番号、この番号で手札のカードを表示する
	
	//各フィールドのゲッター
	public String getPictrue() {return this.pictrue;}
	public int getNumber() {return this.number;}
	public ImageIcon getImageIcon() {return this.icon;}
	public int getComponentNum() {return this.componentNum;}
	
	//絵柄とカード番号が一致するかどうかでequalsメソッドをオーバーライド
	public boolean equals(Object o) {
		if(o==this)return true;
		if(o==null)return false;
		if(o instanceof Card){
			Card c=(Card)o;
			if(!this.pictrue.equals(c.getPictrue()) || this.number != c.getNumber())return false;
		}
		return true;
	}
	
	//コンストラクタ
	protected Card() {}
	protected Card(String pictrue,int number,ImageIcon icon, int componentNum){
		this.pictrue=pictrue;
		this.number=number;
		this.icon=icon;
		this.componentNum = componentNum;
	}
}
