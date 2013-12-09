package logic.oilpoint;

import logic.core.PhasedComponentImpl;
import logic.core.Vector2;
import logic.square.Square;
import login.system.CenterOfMassSystem;
import login.system.SpreadingSystem;

public class SpreadingComponent extends PhasedComponentImpl implements
		OilPointComponent {

	private final SpreadingSystem spreadingSystem;
	private final CenterOfMassSystem centerOfMassSystem;

	public SpreadingComponent(SpreadingSystem spreadingSystem,
			CenterOfMassSystem centerOfMassSystem) {
		super(PhasedComponentImpl.Phase.PHISICS.ordinal());
		this.spreadingSystem = spreadingSystem;
		this.centerOfMassSystem = centerOfMassSystem;

	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		float diameter = spreadingSystem.getActualDiameter();
		float previousDiameter = spreadingSystem.getPreviousDiameter();
		Vector2 centerOfMass = centerOfMassSystem.getCenterOfMass();
		Vector2 position = oilPoint.getPosition();
		float xPos = position.x - centerOfMass.x;
		float yPos = position.y - centerOfMass.y;
		Vector2 relativePosition = new Vector2(xPos, yPos);

		float r = relativePosition.getR();

		float theta = relativePosition.getTheta();

		float rn = diameter / previousDiameter * r;

		Vector2 velocity = Vector2.createFromPolar(rn-r, theta);

		velocity.divide(timeDelta);
		oilPoint.getVelocity().add(velocity);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
