package logic.oilpoint;

import logic.core.SymulatorObject;
import logic.core.Vector2;
import logic.square.Square;

/**
 * Klasa reprezentuj¹ca cz¹stkê ropy. 
 * 
 * @author Szymon Konicki
 *
 */
public class OilPoint implements SymulatorObject {
	
	private Vector2 position;
	private Vector2 velocity = new Vector2(0.0f, 0.0f);
	private Long id;
	private boolean remove = false;
	// Dalsze pola które bêd¹ modifikowane przez obiekty typu OilPointComponent
	
	
	
	public OilPoint(Vector2 position, Long id) {
		this.id = id;
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}


	public boolean isRemove() {
		return remove;
	}


	public void setRemove(boolean remove) {
		this.remove = remove;
	}


	
}
