package logic.oilpoint;

import logic.core.PhasedComponentImpl;
import logic.core.Vector2;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;

public class InfluenceOfCurrentComponent extends PhasedComponentImpl implements
		OilPointComponent {
	private float currentParameter;



	public InfluenceOfCurrentComponent(float currentParameter) {
		super(Phase.PHISICS.ordinal());
		this.currentParameter = currentParameter;
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		Vector2 velocityOfCurrent = square.getCurrent();
		if (velocityOfCurrent != null) {
			oilPoint.getVelocity().add(currentParameter * velocityOfCurrent.x,
					currentParameter * velocityOfCurrent.y);
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
