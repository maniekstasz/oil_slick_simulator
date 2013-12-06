package logic;

public class SpreadingComponent extends PhasedComponentImpl implements
		OilPointComponent {

	private final SpreadingSystem spreadingSystem;

	public SpreadingComponent(SpreadingSystem spreadingSystem) {
		super(PhasedComponentImpl.Phase.PHISICS.ordinal());
		this.spreadingSystem = spreadingSystem;
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		float diameter = spreadingSystem.getActualDiameter();
		float derivativeOfDiameter = spreadingSystem.getActualDerivativeOfDiameter();
		Vector2 positionOfOilPoint = oilPoint.getPosition();
		float xPos = positionOfOilPoint.x;
		float yPos = positionOfOilPoint.y;
		float s = derivativeOfDiameter / (diameter * diameter * diameter)
				* (xPos * xPos + yPos * yPos); // (22) i (24) w stochastic.pdf
		float xVel = s * xPos;
		float yVel = s * yPos;
		oilPoint.getVelocity().add(xVel, yVel);

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
