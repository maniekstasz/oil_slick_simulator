package logic.core;

import logic.core.PhasedComponentImpl.Phase;

/**
 * @author Szymon Konicki
 *	
 *	Główny interfejs wszystkich komponentów. Pomocny w sortowaniu komponentów w zależności od ich fazy {@link Phase}.
 */
public interface PhasedComponent {


	/**
	 * @return Faza komponentu {@link Phase}
	 */
	public int getPhase();
}
