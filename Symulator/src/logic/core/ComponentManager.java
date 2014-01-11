package logic.core;

import logic.core.PhasedComponentImpl.Phase;

/**
 * Manager {@link PhasedComponent} 
 * 
 * @author Szymon Konicki
 */
public interface ComponentManager {
	
	/**
	 * Metoda powinna dodawa� podany {@link PhasedComponent} w okre�lone miejsce posortowanej wg. {@link Phase}  listy. 
	 * 
	 * @param component {@link PhasedComponent}
	 */
	void addComponent(PhasedComponent component);

}
