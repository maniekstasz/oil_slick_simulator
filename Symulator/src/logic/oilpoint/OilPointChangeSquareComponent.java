package logic.oilpoint;

import logic.core.PhasedComponentImpl;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;
import logic.square.SquareComponent;
import logic.system.OilPointSquareSystem;


/**
 * Komponent pomocny w przenoszeniu {@link OilPoint pomiêdzy {@link Square}s 
 * @author Szymon Konicki
 *
 */
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
		if(!oilPoint.isRemove())
			oilPointSquareSystem.addOilPoint(oilPoint);
	}

}
