package logic.core;

enum Par {
	x(100), y(100), timeDelta(900), squareDimension(1000), xOfSpill(50000), yOfSpill(
			50000), numberOfOilPoints(1000), spillAmount(10), diameter(2000), densityOfWater(
			1020), densityOfOil(800), startVolume(1000), diffusionCoefficent(10);



	final float defaultValue;

	Par(float defaultValue) {
		this.defaultValue = defaultValue;
	}

}