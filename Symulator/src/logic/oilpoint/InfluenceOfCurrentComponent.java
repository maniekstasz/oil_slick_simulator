package logic.oilpoint;

import logic.core.PhasedComponentImpl;
import logic.core.Vector2;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;

public class InfluenceOfCurrentComponent extends PhasedComponentImpl implements
		OilPointComponent {
	private float alphaC = 1.1f; // default value

	public InfluenceOfCurrentComponent() {
		super(Phase.PHISICS.ordinal());
	}

	public InfluenceOfCurrentComponent(float nonDefaultalphaC) {
		super(Phase.PHISICS.ordinal());
		alphaC = nonDefaultalphaC;
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		Vector2 velocityOfCurrent = square.getCurrent();
		if (velocityOfCurrent != null) {
			oilPoint.getVelocity().add(alphaC * velocityOfCurrent.x,
					alphaC * velocityOfCurrent.y);
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
