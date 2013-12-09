package login.system;

import static java.lang.Math.*;
import logic.core.Sea;


public class DiskSpreadingSystem implements SpreadingSystem {

	private float actualDiameter = 0;
	private float previousDiameter = 0;

	private final TimeSystem timeSystem;

	/* Parametry */
	private float densityOfWater;
	private float densityOfOil;
	private float startVolume;
	private float constant;
	private float startDiameter;

	public DiskSpreadingSystem(TimeSystem timeSystem) {
		this.timeSystem = timeSystem;
		/* default value */
		densityOfWater = 1050; // [kg/m^3]
		densityOfOil = 800; // [kg/m^3]
		startVolume = 1000; // [m^3]
		constant = 20f;// stochastic.pdf (18)  //1.7 
		//startDiameter = (float) (2*112*Math.sqrt(startVolume));  //(30, stochastic.pdf
		startDiameter=20;
	}

	public void setStartDiameter(float startDiameter) {
		this.startDiameter = startDiameter;
	}

	public void setDensityOfWater(float densityOfWater) {
		this.densityOfWater = densityOfWater;
	}

	public void setDensityOfOil(float densityOfOil) {
		this.densityOfOil = densityOfOil;
	}

	public void setStartVolume(float startVolume) {
		this.startVolume = startVolume;
	}

	public void setConstant(float constant) {
		this.constant = constant;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float timeDelta, Sea sea) {

		float time = timeSystem.getTotalTime() / 60.0f; // czas w minutach
		if (densityOfWater > densityOfOil) {
			previousDiameter = actualDiameter;
			actualDiameter = startDiameter
					+ (float) ((constant * pow((densityOfWater - densityOfOil)
							/ densityOfOil, 1.0 / 3))
							* pow(startVolume, 1. / 3) * pow(time, 0.25));

		}
		if(previousDiameter==0.0f){
			previousDiameter=actualDiameter;
		}

	}

	public float getActualDiameter() {
		return actualDiameter;
	}

	public float getPreviousDiameter() {
		return previousDiameter;
	}

}
