package GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Common.SE;
import GameObject.NonPlayerCharacter;
import GameObject.Player;

public class GameStart implements ActionListener {
	private GameWindow gw; //GameWindowオブジェクト

	@Override
	public void actionPerformed(ActionEvent e) {new gameWindow().start();} //HomeWindowのゲーム開始ボタンを押した時のアクションリスナー
	
	//GameWindowフレームをスレッドで起動
	class gameWindow extends Thread{
		public void run() {
			gw = new GameWindow("ニューゲーム",1000,1000);
			new Round().start();
		}
	}
	
	//ゲームの進行をスレッドで起動
	class Round extends Thread {
		Player pc = gw.pcp.getPlayer();		//プレイヤー
		Player npc = gw.npcp.getPlayer();	//NPC
		
		public synchronized void run(){
			try {
				progression();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//実際のゲーム進行のコードが長いためメソッドでまとめた
		void progression() throws InterruptedException{
			
			//「ラウンドスタート」と表示中
			Thread.sleep(2000);
			
			//プレイヤーとNPCがどちらもパスをするまでドローするかどうかを繰り返す
			while(gw.pcPath == false || gw.npcPath == false) {
				
				// ここからプレイヤーのターン
				if(gw.pcPath == false) {
					gw.cp.getPrgresText().setText("あなたのターンです");
					
					//ドローボタン機能をONにする
					gw.pcTrun = true;
					//ドローかパスをするまでゲーム進行メソッドをスリーブする
					while(gw.pcTrun == true) {Thread.sleep(1);}
					
					//プレイヤーがバーストした時点でゲーム終了
					if(pc.getHand().getBurst() == true) {
						gw.cp.getPrgresText().setText("あなたはバーストしました");
						
						//「あなたはバーストしました」と表示中
						Thread.sleep(3000);
						break;
					}
				}
				//ここまでプレイヤーのターン
				
				// NPCのターン 
				if(npc.getHand().getBurst() == false || gw.npcPath == false) {
					gw.cp.getPrgresText().setText("相手のターンです");
					
					//「相手のターンです」と表示中
					Thread.sleep(3000);
					
					NonPlayerCharacter p = (NonPlayerCharacter)npc;
					
					//パスをする場合はtrue、しない場合はfalseを返す
					gw.npcPath = p.judgmentPath();
					if(gw.npcPath == false) {
						gw.npcp.getComponent(p.draw(gw.cp.getDeck())).setVisible(true);	//引いたカードの手札パネルで表示
						new SE (new File(SE.seDir + "nc41227.wav"),1000);	//カードを引く音
					}else {
						gw.cp.getPrgresText().setText("相手はパスしました");
						
						//「相手はパスしました」と表示中
						Thread.sleep(2000);
					}
				}
				//ここまでNPCのターン
			}
			
			gw.cp.getPrgresText().setText("ラウンド終了");
			
			//「ラウンド終了」と表示中
			Thread.sleep(2000);
			
			result();
		}
		
		//手札の合計値で勝敗を出すメソッド
		void result() {
			//バーストのブール値
			Boolean pcBurst = pc.getHand().getBurst();
			Boolean npcBurst = npc.getHand().getBurst();
			//手札の合計値
			int pcTotal = pc.getHand().getTotal();
			int npcTotal = npc.getHand().getTotal();
			
			//NPCの裏側手札を非表示に最初に引いたカードを表示する
			NonPlayerCharacter p = (NonPlayerCharacter)npc;
			gw.npcp.getComponent(0).setVisible(false);
			gw.npcp.getComponent(p.getFirstCardContentPaneNum()).setVisible(true);
			
			if(pcBurst == true) { //プレイヤーがバーストしたらプレイヤーの負け
				gw.cp.getPrgresText().setText("YOU LOSE");
			}else if(pcBurst == false && npcBurst == true) { //NPCだけがバーストしたらプレイヤーの勝ち
				gw.cp.getPrgresText().setText("YOU WIN");
			}else if(pcBurst == false && npcBurst == false) { //どちらもバーストしていない場合は手札の合計値で勝敗を決める
				if(pcTotal > npcTotal) { //プレイヤーの合計値の方が大きい場合はプレイヤーの勝ち
					gw.cp.getPrgresText().setText("YOU WIN");
				}else if(pcTotal < npcTotal) { //プレイヤーの合計値の方が小さい場合はプレイヤーの負け
					gw.cp.getPrgresText().setText("YOU LOSE");
				}else if(pcTotal == npcTotal) { //合計値がどちらも同じ場合は引き分け
					gw.cp.getPrgresText().setText("DRAW");
				}
			}
		}
	}
}
