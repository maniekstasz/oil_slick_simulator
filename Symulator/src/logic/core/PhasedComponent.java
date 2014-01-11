package logic.core;

import logic.core.PhasedComponentImpl.Phase;

/**
 * @author Szymon Konicki
 *	
 *	G��wny interfejs wszystkich komponent�w. Pomocny w sortowaniu komponent�w w zale�no�ci od ich fazy {@link Phase}.
 */
public interface PhasedComponent {


	/**
	 * @return Faza komponentu {@link Phase}
	 */
	public int getPhase();
}
