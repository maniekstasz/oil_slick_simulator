package logic.core;

import logic.system.TimeSystem;

/**
 * 
 * Gó³wna pêtla symulacji
 * @author Szymon Konicki
 */
public class MainLoop implements SymulatorObject {

	private final TimeSystem timeSystem;
	private final Sea sea;
	private boolean stop;

	public MainLoop(TimeSystem timeSystem, Sea sea) {
		this.timeSystem = timeSystem;
		this.sea = sea;
	}

	@Override
	public void reset() {
//		stop = false;
		timeSystem.reset();
		sea.reset();
	}

	
	/**
	 * Metoda pobiera przeskalowany czas z {@link TimeSystem} i aktualizuje stan morza
	 */
	public void loop() {
//		while (!stop) {
			
			sea.update(timeSystem.getTimeDelta());
			timeSystem.update(timeSystem.getTimeDelta(), sea);

//		}
	}

	public boolean isStop() {
		synchronized (this) {
			return stop;
		}
	}

	public void setStop(boolean stop) {
		synchronized (this) {
			this.stop = stop;
		}
	}

}
