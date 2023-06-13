package GameObject;

import java.util.ArrayList;

public abstract class SetCard{
	//Cardリストオブジェクト
	private ArrayList<Card> cards;
	
	//各フィールドのゲッター
	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	//Cardオブジェクトをリストに追加
	public void addCards(Card c) {
		this.cards.add(c);
	}
	//Cardオブジェクトをリストから削除
	public void removeCards(Card c) {
		int n = cards.indexOf(c);
		cards.remove(n);
	}
	
	//コンストラクタ
	protected SetCard() {
		cards = new ArrayList<Card>();
	}
}
