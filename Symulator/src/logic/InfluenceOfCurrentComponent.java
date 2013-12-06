package logic;

import logic.PhasedComponentImpl.Phase;

public class InfluenceOfCurrentComponent extends PhasedComponentImpl implements
OilPointComponent {
	private  float alphaC = 1.1f; //default value

	public InfluenceOfCurrentComponent(int phase) {
		super(Phase.PHISICS.ordinal());
	}
	public InfluenceOfCurrentComponent(int phase,float nonDefaultalphaC) {
		super(Phase.PHISICS.ordinal());
		alphaC=nonDefaultalphaC;
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		Vector2 velocityOfCurrent = square.getCurrent();
		oilPoint.getVelocity().add(alphaC*velocityOfCurrent.x,alphaC*velocityOfCurrent.y);
		
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
