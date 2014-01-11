package logic.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.core.Sea;
import logic.core.Vector2;
import logic.oilpoint.OilPoint;

public class SpillSystem implements SymulatorSystem {

	private final OilPointSquareSystem oilPointSquareSystem;
	private List<OilSpill> oilSpills = new ArrayList<SpillSystem.OilSpill>();
	private long autoIncrementId = 1;
	public List<OilSpill> getOilSpills() {
		return oilSpills;
	}

	public SpillSystem(OilPointSquareSystem oilPointSquareSystem) {
		this.oilPointSquareSystem = oilPointSquareSystem;

	}

	public void addOilSpill(float x, float y, int number, float spillAmount, float diameter) {
		oilSpills.add(new OilSpill(x, y, number, spillAmount, diameter));
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float timeDelta, Sea sea) {
		for (OilSpill os : oilSpills) {
			List<OilPoint> oilPoints = os.update(timeDelta);
			if (oilPoints.size() > 0) {
				oilPointSquareSystem.addAll(oilPoints);
			}
		}
	}

	public class OilSpill {
		private final float x, y;
		private float spillAmount; // oil pieces per second
		private int number = 0;
		private int actualNumber = 0;
		private float lastSpill = 0;
		private Random random = new Random();
		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		private float diameter=20;
		public OilSpill(float x, float y, int number, float spillAmount, float diameter) {
			this.x = x;
			this.y = y;
			this.spillAmount = spillAmount;
			this.number = number;
			this.diameter=diameter;
		}

		public List<OilPoint> update(float timeDelta) {
			List<OilPoint> oilPoints = new ArrayList<OilPoint>(0);
			if (actualNumber < number) {
				lastSpill += timeDelta;
				int oilPiecesAmount = (int) (lastSpill * spillAmount);
				int capacity = 0;
				if (actualNumber+oilPiecesAmount < number) {
					capacity = oilPiecesAmount;
					oilPoints = new ArrayList<OilPoint>(capacity);

				} else {
					capacity = number-actualNumber;
					oilPoints = new ArrayList<OilPoint>(capacity);
				}
				
				actualNumber+=capacity;
				for (int i = 0; i < capacity; i++) {
					float theta=(float) (random.nextFloat()*2*Math.PI);
					float r=diameter/2*random.nextFloat();
					float x = (float) (this.x+r*Math.sin(theta));
					float y = (float) (this.y+r*Math.cos(theta));
					oilPoints.add(new OilPoint(new Vector2(x, y),autoIncrementId++));
				}
				if (oilPiecesAmount > 0) {
					lastSpill = 0;
				}

			}
			return oilPoints;
		}

	}

}
