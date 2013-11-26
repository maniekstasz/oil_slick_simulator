package logic;

public class MovementComponent extends PhasedComponentImpl implements OilPointComponent {
	
	// To jest tylko przyk�ad componentu, tutaj zaczyna si� fizyka wi�c Maciek popraw je�li jest �le :)
	
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
