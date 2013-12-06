package login.system;

import logic.core.Sea;

public class DifferentalEquationsSpreadingSystem implements SpreadingSystem {
	/* wszedzie czas w sekundach */

	private final TimeSystem timeSystem;

	/*
	 * parametry bêd¹ 'rêcznie' zmieniane w trakcie trwania symulacji na
	 * podstawie wartoœci kwadratów w których jest ropa. W sensie œrednia z
	 * wybranych kwadratów.
	 */
	private float wind;
	private float temperature;
	/***********************************************************/
	
	private float area;
	private float previousArea;
	private float evaporation;
	private float previousEvaporation;
	private float viscosity;
	private float previousViscosity;
	private float volume;
	private float previousVolume;
	private float contentOfWater;
	private float previouscontentOfWater;

	private float actualArea;

	public DifferentalEquationsSpreadingSystem(TimeSystem timeSystem) {
		this.timeSystem = timeSystem;
	}

	@Override
	public void update(float timeDelta, Sea sea) {
		changeTemperature(sea);
		changeWind(sea);

		calculateArea(timeDelta);
		calculateEvaporation(timeDelta);
		calculateViscosity(timeDelta);
		calculateVolume(timeDelta);
		calculateContentOfWater(timeDelta);

	}

	private void calculateContentOfWater(float timeDelta) {
		// TODO Auto-generated method stub

	}

	private void calculateVolume(float timeDelta) {
		// TODO Auto-generated method stub

	}

	private void calculateViscosity(float timeDelta) {
		// TODO Auto-generated method stub

	}

	private void calculateEvaporation(float timeDelta) {
		// TODO Auto-generated method stub

	}

	private void calculateArea(float timeDelta) {
		// TODO Auto-generated method stub

	}

	private void changeWind(Sea sea) {
		// TODO Auto-generated method stub

	}

	private void changeTemperature(Sea sea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public float getActualDiameter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getActualDerivativeOfDiameter() {
		// TODO Auto-generated method stub
		return 0;
	}

}
