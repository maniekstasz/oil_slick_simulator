package logic;

import java.util.Date;

public class MainLoop implements SymulatorObject {

	private final TimeSystem timeSystem;
	private final Sea sea;
	private boolean stop;
	private long lastTimeUpdate;

	
	public MainLoop(TimeSystem timeSystem, Sea sea){
		this.timeSystem = timeSystem;
		this.sea = sea;
		reset();
	}
	
	@Override
	public void reset() {
		lastTimeUpdate = 0;
		stop = false;
	}

	public void update() {
		// TODO: change for sth more precise
		while (stop) {
			final long time = new Date().getTime();
			final long timeDelta = time - lastTimeUpdate;
			timeSystem.update(timeDelta, sea);
			sea.update(timeSystem.getRealTimeDelta());
		}
	}

}
