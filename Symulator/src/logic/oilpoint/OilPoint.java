package logic.oilpoint;

import logic.core.Vector2;
import logic.square.Square;

public class OilPoint {
	
	private Vector2 position;
	private Vector2 velocity = new Vector2(0.0f, 0.0f);
	// Dalsze pola które bêd¹ modifikowane przez obiekty typu OilPointComponent
	
	
	
	public OilPoint(Vector2 position) {
		this.position = position;
	}


	public Vector2 getPosition() {
		return position;
	}


	public Vector2 getVelocity() {
		return velocity;
	}

	
	public void update(Square square, float timeDelta){
		//Tutaj raczej nic nie powinno. Lepiej u¿ywaæ OilPointComponent
	}
	
}
