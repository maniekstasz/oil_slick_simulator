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
 * @author Szymon Konicki System odpowiedzialny za rysowanie obrazowanie
 *         aktualnego stanu symulacji
 */
public class GraphicsSystem extends JComponent implements SymulatorSystem {

	private Square squares[][];
	private int graphicsSquareSize = 10;
	private float maxThickness = 20;

	private float volumeOfOilPoint;
	private int areaOfSquare;

	@Override
	public void reset() {
		this.repaint();

	}

	public GraphicsSystem(int squareSize, int graphicsSquareSize,
			float startVolume, float numberOfOilPoints, float maxThickness) {
		this.graphicsSquareSize = graphicsSquareSize;
		
		this.areaOfSquare = squareSize * squareSize;
		this.maxThickness = maxThickness;
		this.volumeOfOilPoint=startVolume/numberOfOilPoints;
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
		drawNetting(g, graphicsSquareSize);
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
					final float mass = squares[x][y].getMass();
					
					float volumePerSquare=mass*volumeOfOilPoint;
					float thickness=volumePerSquare/areaOfSquare;
					//System.out.println(thickness);
					float color=thickness/maxThickness>=1?1.0f:thickness/maxThickness;
					g.setColor(new Color(0, 0, 0, color));
					g.fillRect((x * graphicsSquareSize) + 1, (y * graphicsSquareSize) + 1, (graphicsSquareSize - 1),
							(graphicsSquareSize - 1));
				}
			}
		}

	}

	// public void setMaxMass(float maxThickness) {
	// this.maxThickness = maxThickness;
	// }

	public int getMaxSquaresWidth() {
		return (int) Math.floor(this.getWidth() / graphicsSquareSize);
	}

	public int getMaxSquaresHeight() {
		return (int) Math.floor(this.getHeight() / graphicsSquareSize);
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	// public void setSquareSize(int size) {
	// this.graphicSquareSize = size;
	// }
}
