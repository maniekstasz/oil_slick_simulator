package logic.oilpoint;

import logic.core.PhasedComponentImpl;
import logic.core.Vector2;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;

public class InfluenceOfWindComponent extends PhasedComponentImpl implements
		OilPointComponent {

	private float windParameter;



	public InfluenceOfWindComponent(float windParameter) {
		super(Phase.PHISICS.ordinal());
		this.windParameter = windParameter;

	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {

		Vector2 velocityOfWind = square.getWind();
		if (velocityOfWind != null) {
			float speedOfWind = velocityOfWind.length();
			float theta = 0; // publikacja oil.pdf (wz�r 15)
			if (speedOfWind < 25) {
				theta = (float) (40 - 8 * Math.sqrt(speedOfWind));
			}

			TranformationMatrixD tranformationMatrixD = new TranformationMatrixD(
					theta);

			float xValue = windParameter
					* (velocityOfWind.x * tranformationMatrixD.matrix[0][0] + velocityOfWind.y
							* tranformationMatrixD.matrix[0][1]);

			float yValue = windParameter
					* (velocityOfWind.x * tranformationMatrixD.matrix[1][0] + velocityOfWind.y
							* tranformationMatrixD.matrix[1][1]);

			oilPoint.getVelocity().add(xValue, yValue);
		}
	}

	private class TranformationMatrixD {
		float[][] matrix = new float[2][2];

		public TranformationMatrixD(float theta) {
			float radTheta = (theta / 360 * 2 * (float) Math.PI);
			matrix[0][0] = (float) Math.cos(radTheta);
			matrix[0][1] = (float) Math.sin(radTheta);
			matrix[1][0] = (float) (-Math.sin(radTheta));
			matrix[1][1] = (float) Math.cos(radTheta);
		}

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
