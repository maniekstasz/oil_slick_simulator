package logic.core;

import java.util.Date;

import login.system.TimeSystem;

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
		stop = true;
	}

	public void loop() {
		// TODO: change for sth more precise
		lastTimeUpdate = new Date().getTime();
		while (stop) {
			final long time = new Date().getTime();
			final long timeDelta = time - lastTimeUpdate;
			lastTimeUpdate = time;
			timeSystem.update((float)timeDelta /1000.0f, sea);
			sea.update(timeSystem.getRealTimeDelta());
			if(timeDelta < 16){
				try {
					Thread.sleep(16-timeDelta);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
