package GameObject;

public interface Player {
	//プレイヤー名のゲッター
	public String getName();
	//手札オブジェクトのゲッター
	public Hand getHand();
	//山札から引くメソッド
	public int draw(Deck d);
}
