package GameObject;

public class NonPlayerCharacter extends PlayerCharacter {
	private int firstCardContentPaneNum;
	
	//フィールドのゲッター
	public int getFirstCardContentPaneNum() {return this.firstCardContentPaneNum;}
	
	//手札の合計値が18より大きくなるまでドローする
	public Boolean judgmentPath() {
		if(this.getHand().getBurst() == true) {
			return true;
		}else if(this.getHand().getTotal() >= 18) {
			return true;
		}
		return false;
	}
	
	public int firstDraw(Deck d) {
		firstCardContentPaneNum = draw(d);
		return 0;
	}
	
	//コンストラクタ
	public NonPlayerCharacter() {}
	public NonPlayerCharacter(Deck d) {
		super("NPC",d);
	}
	public NonPlayerCharacter(String name,Deck d) {
		super(name,d);
	}
}
