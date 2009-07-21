package org.lunivore.tyburn.asciigrid;


import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Converts an image to a string by looking at the centre
 * of each square and picking up the colour of pixel that's there.
 * 
 * This is only useful for black and white grids, and is typically used
 * for eg: the Game of Life. If the cell is black, it produces
 * an 'X', otherwise it produces an '.', so you end up with a grid that looks
 * like, eg:
 * 
 * ......
 * .XX...
 * .X.X..
 * ...XXX
 * ......
 * 
 * System.getProperty("new.line") is appended after each line, except that
 * there is no new line after the last line. This allows it to work well with
 * JBehave, which sees no new lines after multi-line arguments.
 */
public class ImageToAsciiGridConverter {

	private static final String NL = System.getProperty("line.separator");

	public String convert(BufferedImage image, int scale) {
		StringBuilder builder = new StringBuilder();
		
		int noOfRows = (image.getHeight() / scale);
		int noOfCols = image.getWidth() / scale;

		for (int row = 0; row < noOfRows; row++) {
			for (int col = 0; col < noOfCols; col++) {
				int x = (col * scale) + (scale / 2);
				int y = (row * scale) + (scale / 2);
				int color = image.getRGB(x, y);
				builder.append(getStringRepresentationOf(color));
			}
			if (row < noOfRows - 1) { builder.append(NL); }
		}
		return builder.toString();
	}

	protected char getStringRepresentationOf(int color) {
		return color == Color.BLACK.getRGB() ? 'X' : '.';
	}

}
