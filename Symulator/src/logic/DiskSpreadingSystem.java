package logic;

import static java.lang.Math.*;

public class DiskSpreadingSystem implements SpreadingSystem {

	private float actualDiameter = 0;
	private float actualDerivativeOfDiameter = 0;
	private final TimeSystem timeSystem;

	/* Parametry */
	private float densityOfWater;
	private float densityOfOil;
	private float startVolume;
	private float constant;

	public DiskSpreadingSystem(TimeSystem timeSystem) {
		this.timeSystem = timeSystem;
		/* default value */
		densityOfWater = 800; // [kg/m^3]
		densityOfOil = 1050; // [kg/m^3]
		startVolume = 1000; // [m^3]
		constant = 1.7f;// stochastic.pdf (18)

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

		float time = timeSystem.getTotalRealTime()/60;  //czas w minutach
		if (densityOfWater > densityOfOil) {
			actualDiameter = (float) ((constant * pow(
					(densityOfWater - densityOfOil) / densityOfOil, 1.0 / 3))
					* pow(startVolume, 1. / 3) * pow(time, 0.25));
		
			actualDerivativeOfDiameter=(float) ((constant*0.25 * pow(
					(densityOfWater - densityOfOil) / densityOfOil, 1.0 / 3))
					* pow(startVolume, 1. / 3) * pow(time, -0.75));
		
		
		
		}

	}

	public float getActualDiameter() {
		return actualDiameter;
	}

	public float getActualDerivativeOfDiameter() {
		return actualDerivativeOfDiameter;
	}

}
