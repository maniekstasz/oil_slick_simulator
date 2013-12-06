package login.system;

import java.util.ArrayList;
import java.util.List;

import logic.core.Sea;
import logic.core.Vector2;
import logic.oilpoint.OilPoint;

public class OilPointSquareSystem implements SymulatorSystem {
	

	private List<OilPoint>[][] nextRoundSquares;
	
	public OilPointSquareSystem(int x, int y){
		nextRoundSquares = new ArrayList[x][y];
		for(int i = 0; i < nextRoundSquares.length; i++){
			for(int j = 0; j < nextRoundSquares[i].length; j++){
				nextRoundSquares[i][j] = new ArrayList<OilPoint>();
			}
		}
	}
	
	
	public void addOilPoint(OilPoint oilPoint){
		final Vector2 position = oilPoint.getPosition();
		final int x = (int) position.x;
		final int y = (int) position.y;
		nextRoundSquares[x][y].add(oilPoint);
	}
	
	public List<OilPoint> getNextRoundOilPoints(int x, int y){
		return nextRoundSquares[x][y];
	}
	
	@Override
	public void reset() {
		for(int x = 0; x < nextRoundSquares.length; x++){
			for(int y = 0; y < nextRoundSquares[x].length; y++){
				nextRoundSquares[x][y].clear();
			}
		}
	}





	@Override
	public void update(float timeDelta, Sea sea) {
		for(int x = 0; x < nextRoundSquares.length; x++){
			for(int y = 0; y < nextRoundSquares[x].length; y++){
				nextRoundSquares[x][y].clear();
			}
		}
		
	}

}
