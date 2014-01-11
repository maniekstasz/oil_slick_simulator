package logic.system;

import java.util.ArrayList;
import java.util.List;

import logic.core.Sea;
import logic.core.Vector2;
import logic.oilpoint.OilPoint;
import logic.square.Square;

/**
 * System odpowiedzialny za przenoszenie {@link OilPoint}s pomi�dzy {@link Square}s
 * 
 * @author Szymon Konicki
 *
 */
public class OilPointSquareSystem implements SymulatorSystem {

	public List<OilPoint>[][] getNextRoundSquares() {
		return nextRoundSquares;
	}

	private List<OilPoint>[][] nextRoundSquares;
	private final float squareDimension; // w metrach

	public OilPointSquareSystem(int x, int y, float squareDimension) {
		nextRoundSquares = new ArrayList[x][y];
		for (int i = 0; i < nextRoundSquares.length; i++) {
			for (int j = 0; j < nextRoundSquares[i].length; j++) {
				nextRoundSquares[i][j] = new ArrayList<OilPoint>();
			}
		}
		this.squareDimension = squareDimension;
	}

	/**
	 * Metoda dodaj�ca {@link OilPoint} do listy, kt�ra w nast�pnej iteracji zostanie przekazana do {@link Square}
	 * @param oilPoint cz�stka ropy, kt�r� trzeba przenie��
	 */
	public void addOilPoint(OilPoint oilPoint) {
		Vector2 position = oilPoint.getPosition();
		float scaledX = position.x / squareDimension;
		float scaledY = position.y / squareDimension;
		int posX = (int) scaledX;
		int posY = (int) scaledY;
		
		nextRoundSquares[posX][posY].add(oilPoint);
	}
	
	public void addAll(List<OilPoint> oilPoints) {
		for(OilPoint op: oilPoints){
			addOilPoint(op);
		}
	}

	
	/**
	 * 
	 * 
	 * @param x wsp�rz�dna X {@link Square}
	 * @param y wsp�rz�dna Y {@link Square}
	 * @return List� {@link OilPoint}s, dla {@link Square} o podanych wsp�rz�dnych
	 */
	public List<OilPoint> getNextRoundOilPoints(int x, int y) {
		List<OilPoint> next = new ArrayList<OilPoint>(nextRoundSquares[x][y]);
		nextRoundSquares[x][y].clear();
		return next;
	}

	@Override
	public void reset() {
		for (int x = 0; x < nextRoundSquares.length; x++) {
			for (int y = 0; y < nextRoundSquares[x].length; y++) {
				nextRoundSquares[x][y].clear();
			}
		}
	}

	@Override
	public void update(float timeDelta, Sea sea) {
//		for (int x = 0; x < nextRoundSquares.length; x++) {
//			for (int y = 0; y < nextRoundSquares[x].length; y++) {
//				nextRoundSquares[x][y].clear();
//			}
//		}

	}

	public float getSquareDimension() {
		return squareDimension;
	}

}
