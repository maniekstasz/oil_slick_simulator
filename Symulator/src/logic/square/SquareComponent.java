package logic.square;

import logic.core.PhasedComponent;
import logic.core.Sea;
import logic.core.SymulatorObject;

/**
 * @author Szymon Konicki
 *
 */
public interface SquareComponent extends PhasedComponent, SymulatorObject {
	void update(Sea sea, float timeDelta, Square square);
}
