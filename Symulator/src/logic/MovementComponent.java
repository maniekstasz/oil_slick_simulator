package logic;

public class MovementComponent extends PhasedComponentImpl implements OilPointComponent {
	
	// To jest tylko przyk³ad componentu, tutaj zaczyna siê fizyka wiêc Maciek popraw jeœli jest Ÿle :)
	
	public MovementComponent(int phase) {
		super(Phase.POST_PHISICS.ordinal());
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		Vector2 velocity = oilPoint.getVelocity();
		float offsetX = velocity.x * timeDelta;
		float offsetY = velocity.y * timeDelta;
		oilPoint.getPosition().add(offsetX, offsetY);
		
	}

}
