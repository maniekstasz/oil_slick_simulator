package login.system;

import logic.core.Sea;
import logic.core.SymulatorObject;

public interface SymulatorSystem extends SymulatorObject{
	public void update(float timeDelta, Sea sea);
}
