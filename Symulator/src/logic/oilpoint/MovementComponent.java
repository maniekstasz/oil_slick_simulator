package logic.oilpoint;

import logic.core.PhasedComponentImpl;
import logic.core.Vector2;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;

public class MovementComponent extends PhasedComponentImpl implements OilPointComponent {
	

	public MovementComponent() {
		super(Phase.POST_PHISICS.ordinal());
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		Vector2 velocity = oilPoint.getVelocity();
		float offsetX = velocity.x * timeDelta;
		float offsetY = velocity.y * timeDelta;
		oilPoint.getPosition().add(offsetX, offsetY);
		oilPoint.getVelocity().zero();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
