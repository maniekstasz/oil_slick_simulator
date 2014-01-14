package logic.oilpoint;

import java.util.Random;

import logic.core.PhasedComponentImpl;
import logic.core.PhasedComponentImpl.Phase;
import logic.square.Square;
import logic.system.DifferentalEquationsSpreadingSystem;

public class RemoveComponent extends PhasedComponentImpl implements
		OilPointComponent {
	private DifferentalEquationsSpreadingSystem differentalEquationsSpreadingSystem;
	private Random random = new Random();

	public RemoveComponent(
			DifferentalEquationsSpreadingSystem differentalEquationsSpreadingSystem) {
		super(Phase.PHISICS.ordinal());

		this.differentalEquationsSpreadingSystem = differentalEquationsSpreadingSystem;

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		float ratioOfVolume = differentalEquationsSpreadingSystem
				.getRatioOfVolume();
		float removeProbability = 1 - ratioOfVolume;
		float r = random.nextFloat();
		if (r < removeProbability) {
			oilPoint = null; // trzeba zupelnie usunac ten oilPoint
		}
	}

}
