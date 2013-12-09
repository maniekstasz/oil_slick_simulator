package login.system;

import logic.core.Sea;

public class TimeSystem implements SymulatorSystem {

	public TimeSystem(float timeDelta) {

		reset();
		this.timeDelta=timeDelta;
	}

	private float timeDelta;
	private long totalTime = 0;

	public void update(float timeDelta, Sea sea) {

		totalTime += timeDelta;
	}

	@Override
	public void reset() {
		totalTime = 0;
	}

	public float getTimeDelta() {
		return timeDelta;
	}

	public long getTotalTime() {
		return totalTime;
	}

}
