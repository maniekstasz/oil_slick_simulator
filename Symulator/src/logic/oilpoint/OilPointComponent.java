package logic.oilpoint;

import logic.core.PhasedComponent;
import logic.core.SymulatorObject;
import logic.square.Square;

/**
 * 
 * @author Szymon Konicki
 *
 */
public interface OilPointComponent extends PhasedComponent, SymulatorObject{
	void update(Square square, float timeDelta, OilPoint oilPoint);
	
}
