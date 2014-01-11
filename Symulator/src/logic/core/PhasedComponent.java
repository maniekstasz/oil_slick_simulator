package logic.core;

import logic.core.PhasedComponentImpl.Phase;

/**
 * @author Szymon Konicki
 *	
 *	G³ówny interfejs wszystkich komponentów. Pomocny w sortowaniu komponentów w zale¿noœci od ich fazy {@link Phase}.
 */
public interface PhasedComponent {


	/**
	 * @return Faza komponentu {@link Phase}
	 */
	public int getPhase();
}
