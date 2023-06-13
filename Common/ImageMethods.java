package Common;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageMethods {
	public static final String imgDir = System.getProperty("user.dir") + "/src/images/";
	
	//引数に指定したImageIconを指定した幅率で縮小し、縮小したImageIconを戻す
	public static ImageIcon smallImageIcon(ImageIcon icon,double widthRate) {
		Image smallImg = icon.getImage().getScaledInstance((int)(icon.getIconWidth()*widthRate),-1,Image.SCALE_SMOOTH);
		return new ImageIcon(smallImg);
	}
}
