package logic.oilpoint;

import logic.core.PhasedComponentImpl;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;
import login.system.OilPointSquareSystem;

public class OilPointChangeSquareComponent extends PhasedComponentImpl implements OilPointComponent{

	private final OilPointSquareSystem oilPointSquareSystem;
	
	public OilPointChangeSquareComponent(OilPointSquareSystem oilPointSquareSystem){
		super(Phase.POST_MOVEMENT.ordinal());
		this.oilPointSquareSystem = oilPointSquareSystem;
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		oilPointSquareSystem.addOilPoint(oilPoint);
	}

}
