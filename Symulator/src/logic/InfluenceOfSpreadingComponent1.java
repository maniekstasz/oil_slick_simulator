package logic;

import logic.PhasedComponentImpl.Phase;

public class InfluenceOfSpreadingComponent1 extends PhasedComponentImpl implements
OilPointComponent {
	
	

	public InfluenceOfSpreadingComponent1(int phase) {
		super(Phase.PHISICS.ordinal());
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
