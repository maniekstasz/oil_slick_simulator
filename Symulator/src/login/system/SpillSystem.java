package login.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.core.Sea;
import logic.core.Vector2;
import logic.oilpoint.OilPoint;

public class SpillSystem implements SymulatorSystem {

	private final OilPointSquareSystem oilPointSquareSystem;
	private List<OilSpill> oilSpills = new ArrayList<SpillSystem.OilSpill>();
	public SpillSystem(OilPointSquareSystem oilPointSquareSystem) {
		this.oilPointSquareSystem = oilPointSquareSystem;

	}

	
	public void addOilSpill(int x, int y, float spillAmount){
		oilSpills.add(new OilSpill(x, y, spillAmount));
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float timeDelta, Sea sea) {
		for(OilSpill os : oilSpills){
			List<OilPoint> oilPoints = os.update(timeDelta);
			if(oilPoints.size() > 0){
				oilPointSquareSystem.addAll(oilPoints);
			}
		}
	}

	private class OilSpill {
		public final int x, y;
		public float spillAmount; // oil pieces per second
		private float lastSpill = 0;
		private Random random = new Random();
		public OilSpill(int x, int y, float spillAmount){
			this.x = x;
			this.y = y;
			this.spillAmount = spillAmount;
		}
		
		public List<OilPoint> update(float timeDelta){
			lastSpill += timeDelta;
			int oilPiecesAmount = (int) (lastSpill*spillAmount);
			List<OilPoint> oilPoints = new ArrayList<OilPoint>(oilPiecesAmount);
			for(int i =0; i < oilPiecesAmount; i++ ){
				float x = random.nextFloat() * oilPointSquareSystem.getSquareDimension() + (oilPointSquareSystem.getSquareDimension()*this.x);
				float y = random.nextFloat() * oilPointSquareSystem.getSquareDimension()+ (oilPointSquareSystem.getSquareDimension()*this.y);	
				oilPoints.add(new OilPoint(new Vector2(x, y)));
			}
			if(oilPiecesAmount > 0){
				lastSpill = 0;
			}
			return oilPoints;
		}
		

	}

}
