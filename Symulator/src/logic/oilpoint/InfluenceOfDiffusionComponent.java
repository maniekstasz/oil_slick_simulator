package logic.oilpoint;

import java.util.Random;

import logic.core.PhasedComponentImpl;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;

import static java.lang.Math.*;

public class InfluenceOfDiffusionComponent extends PhasedComponentImpl implements
		OilPointComponent {

	float diffusionCoefficent = 10; // default value, m^2/s, przyk³adowa
											// wartoœæ, trzeba poszukaæ w
											// artykule np. tym
											// "Crude oil dissolution in saline water"
											// (ale nie mam dostêpu)

	private Random random = new Random();

	public InfluenceOfDiffusionComponent() {

		super(Phase.PHISICS.ordinal());
	}

	public InfluenceOfDiffusionComponent(float nonDefaultDiffusionCoefficent) {

		super(Phase.PHISICS.ordinal());

		diffusionCoefficent = nonDefaultDiffusionCoefficent;
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
/*publikacja stochastic.pdf (wzór 10) "A stochastic simulation model of oil spill fate and transport"*/
		float r = random.nextFloat(); 
		float theta = 2 * (float) PI * random.nextFloat();
		float value = (r * (float) Math.sqrt(12 * diffusionCoefficent
				/ timeDelta));
		float xValue = value * (float) cos(theta);
		float yValue = value * (float) sin(theta);
		oilPoint.getVelocity().add(xValue, yValue);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
