package GameObject;

public class PlayerCharacter implements Player {
	private String Name;	//プレイヤー名
	private Hand hand;		//手札オブジェクト
	
	//各フィールドのゲッター
	public String getName() {return this.Name;}
	public Hand getHand() {return this.hand;}
	
	//山札からカードを引き、引いたトランプ番号を戻す
	public int draw(Deck d) {
		int elements = d.getCards().size();
		int element = new java.util.Random().nextInt(elements) + 1;	//トランプのコンスタントペイン番号が1～52までのため +1
		Card c = d.getCards().get(element);
		this.hand.getCards().add(c);	//乱数で出たトランプ番号のカードを手札リストに加える
		d.removeCards(c);				//乱数で出たトランプ番号のカードを山札リストから取り除く
		this.hand.handTotal();			//手札の合計値を更新
		
		System.out.println(this.hand.getTotal());
		
		return c.getComponentNum();
	}
	
	//コンストラクタ
	public PlayerCharacter(){
		this.Name = "プレイヤー1";
	}
	public PlayerCharacter(Deck d) {
		this();
		this.hand = new Hand();
	}
	public PlayerCharacter(String name,Deck d) {
		this.Name = name;
		this.hand = new Hand();
	}
}
