package logic.square;

import java.util.List;

import logic.core.PhasedComponentImpl;
import logic.core.Sea;
import logic.core.Vector2;
import logic.core.PhasedComponentImpl.Phase;
import logic.oilpoint.OilPoint;
import login.system.OilPointSquareSystem;

public class NextRoundOilPointsComponent extends PhasedComponentImpl implements SquareComponent {

	
	private final OilPointSquareSystem oilPointSquareSystem;
	public NextRoundOilPointsComponent(OilPointSquareSystem oilPointSquareSystem) {
		super(Phase.PRE_PHISICS.ordinal());
		this.oilPointSquareSystem = oilPointSquareSystem;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Sea sea, float timeDelta, Square square) {
		final Vector2 position = square.getPosition();
		List<OilPoint> nextRoundOilPoits = oilPointSquareSystem.getNextRoundOilPoints((int)position.x, (int)position.y);
		square.setOilPoints(nextRoundOilPoits);
	}

}
