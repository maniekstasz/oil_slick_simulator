package logic.core;

import gui.sea.GUI;
import gui.sea.Program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.swing.JOptionPane;

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
import logic.system.GraphicsSystem;
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

	private MainLoop mainLoop;

	/**
	 * W tej metodzie ustawiane s¹ wszystkie parametry symulacji
	 */
	public void configure(Program program) {

		// Okreœl powierzchnie morza
		int x = 100, y = 100;

		Sea sea = new Sea(x, y);

		// systems
		TimeSystem timeSystem = new TimeSystem(15 * 60);

		// Ostatni parametr to rozmiar kwadratu w metrach
		OilPointSquareSystem oilPointSquareSystem = new OilPointSquareSystem(x,
				y, 1000);
		// DifferentalEquationsSpreadingSystem
		// differentalEquationsSpreadingSystem = new
		// DifferentalEquationsSpreadingSystem(timeSystem);
		CenterOfMassSystem centerOfMassSystem = new CenterOfMassSystem(
				oilPointSquareSystem);
		// DiskSpreadingSystem diskSpreadingSystem = new DiskSpreadingSystem(
		// timeSystem);
		DifferentalEquationsSpreadingSystem differentalEquationsSpreadingSystem = new DifferentalEquationsSpreadingSystem(
				centerOfMassSystem, timeSystem);
		differentalEquationsSpreadingSystem
				.setupStartValues(0, 1000, 0, 300, 1);

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

		spillSystem.addOilSpill(50000, 35000, 1000, 10, 20f);

		Square[][] squares = sea.getSquares();
		// do przemyœlenia czy dodawaæ do ka¿dego squara

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				squares[i][j].addComponent(movementComponent);
				squares[i][j].addComponent(oilPointChangeSquareComponent);
				// squares[i][j].addComponent(influenceOfCurrentComponent);
				// squares[i][j].addComponent(influenceOfWindComponent);
				squares[i][j].addComponent(influenceOfDiffusionComponent);
				// squares[i][j].addComponent(fileOutputComponent);
				squares[i][j].addComponent(spreadingComponent);
			}
		}
		GraphicsSystem graphicsSystem = new GraphicsSystem();
		sea.setGraphicsSystem(graphicsSystem);
		graphicsSystem.setSquares(squares);
		GUI gui = new GUI(program, graphicsSystem, mainLoop);
		gui.initialize(program.getContentPane());

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

	public void configure(Program program, File inputFile) {

		HashMap<Par, Float> map = createMapOfParameters(program, inputFile);
		int x = (int) (map.get(Par.x) == null ? Par.x.defaultValue : map
				.get(Par.x));
		int y = (int) (map.get(Par.y) == null ? Par.y.defaultValue : map
				.get(Par.y));
		float timeDelta = map.get(Par.timeDelta) == null ? Par.timeDelta.defaultValue
				: map.get(Par.timeDelta);
		float squareDimension = map.get(Par.squareDimension) == null ? Par.squareDimension.defaultValue
				: map.get(Par.squareDimension);
		float xOfSpill = (map.get(Par.xOfSpill) == null ? Par.xOfSpill.defaultValue
				: map.get(Par.xOfSpill));
		float yOfSpill = (map.get(Par.yOfSpill) == null ? Par.yOfSpill.defaultValue
				: map.get(Par.yOfSpill));
		int numberOfOilPoints = (int) (map.get(Par.numberOfOilPoints) == null ? Par.numberOfOilPoints.defaultValue
				: map.get(Par.numberOfOilPoints));
		float spillAmount = (map.get(Par.spillAmount) == null ? Par.spillAmount.defaultValue
				: map.get(Par.spillAmount));
		float diameter = (map.get(Par.diameter) == null ? Par.diameter.defaultValue
				: map.get(Par.diameter));

		Sea sea = new Sea(x, y);

		TimeSystem timeSystem = new TimeSystem(timeDelta);

		OilPointSquareSystem oilPointSquareSystem = new OilPointSquareSystem(x,
				y, squareDimension);
		CenterOfMassSystem centerOfMassSystem = new CenterOfMassSystem(
				oilPointSquareSystem);

		float startVolume = (map.get(Par.startVolume) == null ? Par.startVolume.defaultValue
				: map.get(Par.startVolume));
		float densityOfWater = (map.get(Par.densityOfWater) == null ? Par.densityOfWater.defaultValue
				: map.get(Par.densityOfWater));
		float densityOfOil = (map.get(Par.densityOfOil) == null ? Par.densityOfOil.defaultValue
				: map.get(Par.densityOfOil));
		float diffusionCoefficent = (map.get(Par.diffusionCoefficent) == null ? Par.diffusionCoefficent.defaultValue
				: map.get(Par.diffusionCoefficent));

		DiskSpreadingSystem diskSpreadingSystem = new DiskSpreadingSystem(
				timeSystem, densityOfWater, densityOfOil, startVolume, diameter);
		/* ta sama srednica co w spill */
		SpillSystem spillSystem = new SpillSystem(oilPointSquareSystem);
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
		InfluenceOfDiffusionComponent influenceOfDiffusionComponent = new InfluenceOfDiffusionComponent(
				diffusionCoefficent);
		InfluenceOfWindComponent influenceOfWindComponent = new InfluenceOfWindComponent();
		MovementComponent movementComponent = new MovementComponent();
		OilPointChangeSquareComponent oilPointChangeSquareComponent = new OilPointChangeSquareComponent(
				oilPointSquareSystem);
		FileOutputComponent fileOutputComponent = new FileOutputComponent(
				timeSystem);
		// SpreadingComponent spreadingComponent = new SpreadingComponent(
		// differentalEquationsSpreadingSystem, centerOfMassSystem);
		SpreadingComponent spreadingComponent = new SpreadingComponent(
				diskSpreadingSystem, centerOfMassSystem);
		spillSystem.addOilSpill(xOfSpill, yOfSpill, numberOfOilPoints,
				spillAmount, diameter);

		Square[][] squares = sea.getSquares();
		// do przemyœlenia czy dodawaæ do ka¿dego squara

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				squares[i][j].addComponent(movementComponent);
				squares[i][j].addComponent(oilPointChangeSquareComponent);
				// squares[i][j].addComponent(influenceOfCurrentComponent);
				// squares[i][j].addComponent(influenceOfWindComponent);
				squares[i][j].addComponent(influenceOfDiffusionComponent);
				// squares[i][j].addComponent(fileOutputComponent);
				squares[i][j].addComponent(spreadingComponent);
			}
		}
		GraphicsSystem graphicsSystem = new GraphicsSystem();
		sea.setGraphicsSystem(graphicsSystem);
		graphicsSystem.setSquares(squares);
		GUI gui = new GUI(program, graphicsSystem, mainLoop);
		gui.initialize(program.getContentPane());

		System.out.println(map);

	}
	
	
	

	private HashMap<Par, Float> createMapOfParameters(Program program,
			File inputfile) {
		HashMap<Par, Float> map = new HashMap<>();
		int counter = 0;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(inputfile));

			String line;
			while ((line = reader.readLine()) != null) {
				counter++;
				line = deleteComment(line);
				if (!line.equals("")) {
					line = line.replace("\t", " ");
					String[] tab = line.trim().split(" +"); 
					try { // white char
						map.put(Par.valueOf(tab[0]), new Float(tab[1]));
					} catch (Exception e) {
						throw new Exception(new Integer(counter).toString());
					}
				}
			}

			reader.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					program,
					"B³¹d w pliku " + inputfile.getName() + " w linii: "
							+ e.getMessage() + ". Program zostanie zamkniêty.",
					"Inane error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		return map;
	}

	private String deleteComment(String line) { // w inpucie "!" dziala jak "//"
												// w javie
		int index = line.indexOf("!");
		if (index > -1) {
			line = line.substring(0, index);
		}
		return line;

	}

}


