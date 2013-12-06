package logic.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import logic.oilpoint.InfluenceOfCurrentComponent;
import logic.oilpoint.InfluenceOfDiffusionComponent;
import logic.oilpoint.InfluenceOfWindComponent;
import logic.oilpoint.MovementComponent;
import logic.oilpoint.OilPoint;
import logic.oilpoint.OilPointChangeSquareComponent;
import logic.oilpoint.SpreadingComponent;
import logic.square.NextRoundOilPointsComponent;
import logic.square.Square;
import login.system.DifferentalEquationsSpreadingSystem;
import login.system.DiskSpreadingSystem;
import login.system.OilPointSquareSystem;
import login.system.TimeSystem;

public class Symulator {
	
	public static void main(String args[]){
		Symulator symulator = new Symulator();
		symulator.configure();
		symulator.mainLoop.loop();
		
	}
	private MainLoop mainLoop;
	
	
	public void configure(){
		int x = 100, y = 100;
		
		Sea sea = new Sea(x, y);
		
		
		// systems
		TimeSystem timeSystem = new TimeSystem(1);
		OilPointSquareSystem oilPointSquareSystem = new OilPointSquareSystem(x, y);
//		DifferentalEquationsSpreadingSystem differentalEquationsSpreadingSystem = new DifferentalEquationsSpreadingSystem(timeSystem);
		DiskSpreadingSystem diskSpreadingSystem = new DiskSpreadingSystem(timeSystem);
		
		//przemyœleæ dodawanie systemów
		mainLoop = new MainLoop(timeSystem, sea);
		sea.setOilPointSquareSystem(oilPointSquareSystem);
		sea.setSpreadingSystem(diskSpreadingSystem);
		// square components
		NextRoundOilPointsComponent nextRoundOilPointsComponent = new NextRoundOilPointsComponent(oilPointSquareSystem);
		sea.addComponent(nextRoundOilPointsComponent);
		
		//oilPoint components
		InfluenceOfCurrentComponent influenceOfCurrentComponent = new InfluenceOfCurrentComponent();
		InfluenceOfDiffusionComponent influenceOfDiffusionComponent = new InfluenceOfDiffusionComponent();
		InfluenceOfWindComponent influenceOfWindComponent = new InfluenceOfWindComponent();
		MovementComponent movementComponent = new MovementComponent();
		OilPointChangeSquareComponent oilPointChangeSquareComponent = new OilPointChangeSquareComponent(oilPointSquareSystem);
		SpreadingComponent spreadingComponent = new SpreadingComponent(diskSpreadingSystem);
		Square[][] squares = sea.getSquares();
		// do przemyœlenia czy dodawaæ do ka¿dego squara
		
		for(int i = 0; i < x; i++){
			for(int j = 0; j< y; j++){
				squares[i][j].addComponent(movementComponent);
				squares[i][j].addComponent(oilPointChangeSquareComponent);
//				squares[i][j].addComponent(influenceOfCurrentComponent);
				squares[i][j].addComponent(influenceOfWindComponent);
//				squares[i][j].addComponent(influenceOfDiffusionComponent);
//				squares[i][j].addComponent(spreadingComponent);
			}
		}
		
//		squares[0][0].setWind(new Vector2(0.5f,0.0f));
//		oilPoints.add(new OilPoint(new Vector2(0.5f, 0.5f)));
//		oilPointSquareSystem.addOilPoint(oilPoints.get(0));
	}
}
