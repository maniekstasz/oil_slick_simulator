package logic.core;

import java.util.Date;

import login.system.TimeSystem;

public class MainLoop implements SymulatorObject {

	private final TimeSystem timeSystem;
	private final Sea sea;
	private boolean stop;
	private long lastTimeUpdate;

	public MainLoop(TimeSystem timeSystem, Sea sea) {
		this.timeSystem = timeSystem;
		this.sea = sea;
		reset();
	}

	@Override
	public void reset() {
		lastTimeUpdate = 0;
		stop = true;
	}

	public void loop() {
		// TODO: change for sth more precise
		
		while (stop) {

			timeSystem.update(timeSystem.getTimeDelta(), sea);
			sea.update(timeSystem.getTimeDelta());

		}
	}

}
