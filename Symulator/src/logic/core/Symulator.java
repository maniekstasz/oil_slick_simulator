package logic.core;

import java.io.IOException;

import logic.oilpoint.FileOutputComponent;
import logic.oilpoint.InfluenceOfCurrentComponent;
import logic.oilpoint.InfluenceOfDiffusionComponent;
import logic.oilpoint.InfluenceOfWindComponent;
import logic.oilpoint.MovementComponent;
import logic.oilpoint.OilPointChangeSquareComponent;
import logic.oilpoint.SpreadingComponent;
import logic.square.NextRoundOilPointsComponent;
import logic.square.Square;
import logic.system.CenterOfMassSystem;
import logic.system.DifferentalEquationsSpreadingSystem;
import logic.system.DiskSpreadingSystem;
import logic.system.OilPointSquareSystem;
import logic.system.SpillSystem;
import logic.system.TimeSystem;

/**
 * Klasa odpowiedzialna za konfiguracjê symulacji
 * 
 * @author Szymon Konicki
 *
 */
public class Symulator {

	public static void main(String args[]) {
		final Symulator symulator = new Symulator();
		symulator.configure();
		Thread stopping = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("Zatrzymaæ?");
					System.in.read();
					symulator.mainLoop.setStop(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		stopping.start();
		symulator.mainLoop.loop();

	}

	private MainLoop mainLoop;

	/**
	 * W tej metodzie ustawiane s¹ wszystkie parametry symulacji
	 */
	public void configure() {

		// Okreœl powierzchnie morza
		int x = 1000, y = 1000;

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
//		DiskSpreadingSystem diskSpreadingSystem = new DiskSpreadingSystem(
//				timeSystem);
		DifferentalEquationsSpreadingSystem differentalEquationsSpreadingSystem = new DifferentalEquationsSpreadingSystem(
				centerOfMassSystem, timeSystem);
		differentalEquationsSpreadingSystem.setupStartValues(0, 1000, 0, 300, 1);

		SpillSystem spillSystem = new SpillSystem(oilPointSquareSystem);

		// przemyœleæ dodawanie systemów
		mainLoop = new MainLoop(timeSystem, sea);
		sea.setCenterOfMassSystem(centerOfMassSystem);
		sea.setOilPointSquareSystem(oilPointSquareSystem);
		sea.setSpreadingSystem(differentalEquationsSpreadingSystem);
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
		FileOutputComponent fileOutputComponent = new FileOutputComponent(
				timeSystem);
		SpreadingComponent spreadingComponent = new SpreadingComponent(
				differentalEquationsSpreadingSystem, centerOfMassSystem);

		spillSystem.addOilSpill(50000, 50000, 2, 10000000, 20f);

		Square[][] squares = sea.getSquares();
		// do przemyœlenia czy dodawaæ do ka¿dego squara

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				squares[i][j].addComponent(movementComponent);
				squares[i][j].addComponent(oilPointChangeSquareComponent);
				// squares[i][j].addComponent(influenceOfCurrentComponent);
				// squares[i][j].addComponent(influenceOfWindComponent);
				squares[i][j].addComponent(influenceOfDiffusionComponent);
				squares[i][j].addComponent(fileOutputComponent);
				squares[i][j].addComponent(spreadingComponent);
			}
		}

		// zdefiniuj w którym kwadracie ma pojawiaæ siê ropa i ile oilpunktów ma
		// siê pojawiaæ na sekunde

		// OilPoint op = new OilPoint(new Vector2(0.0f, 0.0f));
		// op.getVelocity().add(new Vector2(2.0f, 0.0f));
		// squares[0][0].setOilPoints(new
		// ArrayList<OilPoint>(Arrays.asList(op)));
		// squares[0][0].setWind(new Vector2(0.5f,0.0f));
		// oilPoints.add(new OilPoint(new Vector2(0.5f, 0.5f)));
		// oilPointSquareSystem.addOilPoint(op);
	}
}
