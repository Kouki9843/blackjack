package HomeWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Common.ImageMethods;
import GameWindow.GameStart;

public class HomeWindow extends JFrame {
	public HomeWindow(String title, int width, int height) {
		super(title);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//コンスタントペインをBorderLayoutとして設定
		Container contentPane = getContentPane();
		Color c = new Color(0,102,51);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(c);
		
		//タイトルロゴを設定
		ImageIcon icon = new ImageIcon(ImageMethods.imgDir + "blackjack_logo.png");
		JLabel logo = new JLabel(icon);
		
		//ゲーム開始ボタンの設定
		GameStart gsal = new GameStart();
		JButton gameStart = new JButton("ゲーム開始");
		gameStart.setFont(new Font("SansSerif", Font.PLAIN, 25));
		gameStart.setBorderPainted(false);
		gameStart.setBorder(new EmptyBorder(0,0,0,0));
		gameStart.addActionListener(gsal);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.add(gameStart);
		
		//真ん中にタイトルとボタンを配置
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(logo,BorderLayout.CENTER);
		centerPanel.add(buttonPanel,BorderLayout.SOUTH);
		
		//余白パネルを設定
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(100,600));
		westPanel.setOpaque(false);
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(100,600));
		JPanel southPanel = new JPanel();
		eastPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(600,100));
		southPanel.setOpaque(false);
		
		//各パネルをコンスタントペインに追加
		contentPane.add(westPanel,BorderLayout.WEST);
		contentPane.add(eastPanel,BorderLayout.EAST);
		contentPane.add(southPanel,BorderLayout.SOUTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
	}
}
