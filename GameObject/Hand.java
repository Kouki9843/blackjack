package GameObject;

public class Hand extends SetCard {
	private int total;	//手札の合計値
	private Boolean burst = false;	//手札がバーストしたかのブール値
	
	//各フィールドのゲッター
	public int getTotal() {return this.total;}
	public Boolean getBurst() {return this.burst;}
	
	//手札の合計値を計算するメソッド
	public void handTotal() {
		this.total = 0;
		int aceCount = 0;
		for(Card c : this.getCards()) {
			int number = c.getNumber();
			if(number == 1) {
				aceCount++;
			}else if(number >= 11){
				this.total += 10;
			}else {
				this.total += number;
			}
		}
		
		if(aceCount > 0) {
			if(this.total <= 10) {
				switch(aceCount) {
					case 1:	this.total += 11;
							break;
					case 2:	if(this.total+12 > 21) {
								this.total += 2;
							}else {
								this.total += 12;
							}
							break;
					case 3:	if(this.total+13 > 21) {
								this.total += 3;
							}else {
								this.total += 13;
							}
							break;
					case 4:	if(this.total+14 > 21) {
								this.total += 4;
							}else {
								this.total += 14;
							}
							break;
				}
			}else {
				this.total += aceCount;
			}
		}
		
		if(this.total > 21) {this.burst = true;}
	}
}
