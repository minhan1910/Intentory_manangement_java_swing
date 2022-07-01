package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Utils {
	public static Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	public static ImageIcon setIcon(ImageIcon rawImageIcon, int w, int h) {
		Image img = rawImageIcon.getImage();
		img = Utils.getScaledImage(img, w, h);
		img = Transparency.makeColorTransparent(img, Color.white);
		ImageIcon newImageIcon = new ImageIcon(img);
		return newImageIcon;
	}
}
