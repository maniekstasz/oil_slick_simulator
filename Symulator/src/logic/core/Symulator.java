package logic.core;

import logic.oilpoint.InfluenceOfCurrentComponent;
import logic.oilpoint.InfluenceOfDiffusionComponent;
import logic.oilpoint.InfluenceOfWindComponent;
import logic.oilpoint.MovementComponent;
import logic.oilpoint.OilPointChangeSquareComponent;
import logic.oilpoint.SpreadingComponent;
import logic.square.NextRoundOilPointsComponent;
import logic.square.Square;
import login.system.CenterOfMassSystem;
import login.system.DifferentalEquationsSpreadingSystem;
import login.system.DiskSpreadingSystem;
import login.system.OilPointSquareSystem;
import login.system.SpillSystem;
import login.system.TimeSystem;

public class Symulator {

	public static void main(String args[]) {
		Symulator symulator = new Symulator();
		symulator.configure();
		symulator.mainLoop.loop();

	}

	private MainLoop mainLoop;

	public void configure() {

		// Okre�l powierzchnie morza
		int x = 10, y = 10;

		Sea sea = new Sea(x, y);

		// systems
		TimeSystem timeSystem = new TimeSystem(15 * 60);

		// Ostatni parametr to rozmiar kwadratu w metrach
		OilPointSquareSystem oilPointSquareSystem = new OilPointSquareSystem(x,
				y, 100);
		// DifferentalEquationsSpreadingSystem
		// differentalEquationsSpreadingSystem = new
		// DifferentalEquationsSpreadingSystem(timeSystem);
		CenterOfMassSystem centerOfMassSystem = new CenterOfMassSystem(
				oilPointSquareSystem);
		//DiskSpreadingSystem diskSpreadingSystem = new DiskSpreadingSystem(
			//	timeSystem);
		
		DiskSpreadingSystem diskSpreadingSystem = new DiskSpreadingSystem(timeSystem);
		
		SpillSystem spillSystem = new SpillSystem(oilPointSquareSystem);
		
		// przemy�le� dodawanie system�w
		mainLoop = new MainLoop(timeSystem, sea);
		sea.setCenterOfMassSystem(centerOfMassSystem);
		sea.setOilPointSquareSystem(oilPointSquareSystem);
		sea.setSpreadingSystem(diskSpreadingSystem);
		sea.setSpillSystem(spillSystem);
		// square components
		NextRoundOilPointsComponent nextRoundOilPointsComponent = new NextRoundOilPointsComponent(
				oilPointSquareSystem);
		sea.addComponent(nextRoundOilPointsComponent);

		// oilPoint components
		InfluenceOfCurrentComponent influenceOfCurrentComponent = new InfluenceOfCurrentComponent();
		InfluenceOfDiffusionComponent influenceOfDiffusionComponent = new InfluenceOfDiffusionComponent();
		InfluenceOfWindComponent influenceOfWindComponent = new InfluenceOfWindComponent();
		MovementComponent movementComponent = new MovementComponent();
		OilPointChangeSquareComponent oilPointChangeSquareComponent = new OilPointChangeSquareComponent(
				oilPointSquareSystem);
		SpreadingComponent spreadingComponent = new SpreadingComponent(
				diskSpreadingSystem, centerOfMassSystem);
		
		spillSystem.addOilSpill(500, 500, 5, 10000000, 20f);
		
		Square[][] squares = sea.getSquares();
		// do przemy�lenia czy dodawa� do ka�dego squara

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				squares[i][j].addComponent(movementComponent);
				squares[i][j].addComponent(oilPointChangeSquareComponent);
				// squares[i][j].addComponent(influenceOfCurrentComponent);
				// squares[i][j].addComponent(influenceOfWindComponent);
				 squares[i][j].addComponent(influenceOfDiffusionComponent);
				squares[i][j].addComponent(spreadingComponent);
			}
		}

		// zdefiniuj w kt�rym kwadracie ma pojawia� si� ropa i ile oilpunkt�w ma
		// si� pojawia� na sekunde

		// OilPoint op = new OilPoint(new Vector2(0.0f, 0.0f));
		// op.getVelocity().add(new Vector2(2.0f, 0.0f));
		// squares[0][0].setOilPoints(new
		// ArrayList<OilPoint>(Arrays.asList(op)));
		// squares[0][0].setWind(new Vector2(0.5f,0.0f));
		// oilPoints.add(new OilPoint(new Vector2(0.5f, 0.5f)));
		// oilPointSquareSystem.addOilPoint(op);
	}
}
