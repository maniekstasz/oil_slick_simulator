package logic;

public class InfluenceOfWindComponent extends PhasedComponentImpl implements
		OilPointComponent {

	private  float alphaW = 0.03f;  //default value  

	public InfluenceOfWindComponent(int phase) {
		super(Phase.PHISICS.ordinal());

	}
	
	public InfluenceOfWindComponent(int phase, float nonDefaultalphaW) {
		super(Phase.PHISICS.ordinal());
		alphaW=nonDefaultalphaW;

	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		Vector2 velocityOfWind = square.wind;
		float speedOfWind = velocityOfWind.length();
		float theta = 0; // publikacja oil.pdf (wzór 15)
		if (speedOfWind < 25) {
			theta = (float) (40 - 8 * Math.sqrt(speedOfWind));
		}

		TranformationMatrixD tranformationMatrixD = new TranformationMatrixD(
				theta);
		
		
		
		float xValue =alphaW
				* (velocityOfWind.x * tranformationMatrixD.matrix[0][0] + velocityOfWind.y
						* tranformationMatrixD.matrix[0][1]);
		
		float yValue= alphaW
				* (velocityOfWind.x * tranformationMatrixD.matrix[1][0] + velocityOfWind.y
						* tranformationMatrixD.matrix[1][1]);
		
		oilPoint.getVelocity().add(xValue,yValue);

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

}
