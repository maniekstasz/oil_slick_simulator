package logic.system;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import logic.core.Sea;
import logic.square.Square;

/**
 * @author Szymon Konicki
 * System odpowiedzialny za rysowanie obrazowanie aktualnego stanu symulacji
 */
public class GraphicsSystem extends JComponent implements SymulatorSystem {

	private Square squares[][];
	private int squareSize = 10;
	private float maxMass = 20;


	@Override
	public void reset() {
		this.repaint();

	}

	@Override
	public void update(float timeDelta, Sea sea) {
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		g.setColor(Color.GRAY);
		drawNetting(g, squareSize);
	}

	private void drawNetting(Graphics g, int gridSpace) {
		Insets insets = getInsets();
		int firstX = insets.left;
		int firstY = insets.top;
		int lastX = this.getWidth() - insets.right;
		int lastY = this.getHeight() - insets.bottom;

		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}
		for (x = 0; x < squares.length; ++x) {
			for (y = 0; y < squares[x].length; ++y) {
				if (squares[x][y].getMass() != 0) {
					final float mass = squares[x][y].getMass() > maxMass ? maxMass
							: squares[x][y].getMass();
					g.setColor(new Color(0, 0, 0, (mass / maxMass)));
					g.fillRect((x * squareSize) + 1, (y * squareSize) + 1, (squareSize - 1),
							(squareSize - 1));
				}
			}
		}

	}

	public void setMaxMass(float maxMass) {
		this.maxMass = maxMass;
	}

	public int getMaxSquaresWidth() {
		return (int) Math.floor(this.getWidth() / squareSize);
	}

	public int getMaxSquaresHeight() {
		return (int) Math.floor(this.getHeight() / squareSize);
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public void setSquareSize(int size) {
		this.squareSize = size;
	}
}
