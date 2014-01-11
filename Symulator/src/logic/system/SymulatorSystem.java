package logic.system;

import logic.core.Sea;
import logic.core.SymulatorObject;

/**
 * @author Szymon Konicki
 *
 */
public interface SymulatorSystem extends SymulatorObject{
	public void update(float timeDelta, Sea sea);
}
