package logic;

public class OilPoint {
	
	private Vector2 position;
	private Vector2 velocity;
	// Dalsze pola kt�re b�d� modifikowane przez obiekty typu OilPointComponent
	
	
	public Vector2 getPosition() {
		return position;
	}


	public Vector2 getVelocity() {
		return velocity;
	}

	
	public void update(Square square, float timeDelta){
		//Tutaj raczej nic nie powinno. Lepiej u�ywa� OilPointComponent
	}
	
}
