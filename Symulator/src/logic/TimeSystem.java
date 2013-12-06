package logic;

public class TimeSystem implements SymulatorSystem {

	public TimeSystem(float iNITIAL_TIME_COEFFICIENT) {
		INITIAL_TIME_COEFFICIENT = iNITIAL_TIME_COEFFICIENT;
		reset();
	}

	private final float INITIAL_TIME_COEFFICIENT;
	private float realTimeDelta;
	private float totalRealTime = 0;
	private float timeCoefficient = 1.0f;
	
	public void update(float timeDelta, Sea sea){
		realTimeDelta = timeDelta * timeCoefficient;
		totalRealTime += realTimeDelta;
	}

	@Override
	public void reset() {
		totalRealTime = 0;
		realTimeDelta = 0;
		timeCoefficient = INITIAL_TIME_COEFFICIENT;
	}

	public float getRealTimeDelta() {
		return realTimeDelta;
	}

	public void setRealTimeDelta(float realTimeDelta) {
		this.realTimeDelta = realTimeDelta;
	}

	public float getTotalRealTime() {
		return totalRealTime;
	}

	public void setTotalRealTime(float totalRealTime) {
		this.totalRealTime = totalRealTime;
	}

	public float getTimeCoefficient() {
		return timeCoefficient;
	}

	public void setTimeCoefficient(float timeCoefficient) {
		this.timeCoefficient = timeCoefficient;
	}
}
