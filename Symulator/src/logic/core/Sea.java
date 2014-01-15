package logic.core;

import java.util.ArrayList;
import java.util.List;

import logic.square.Square;
import logic.square.SquareComponent;
import logic.system.CenterOfMassSystem;
import logic.system.GraphicsSystem;
import logic.system.OilPointSquareSystem;
import logic.system.SpillSystem;
import logic.system.SpreadingSystem;
import logic.system.SymulatorSystem;

/**
 * 
 *
 * G³ówny obiekt symulacji. Jest on managerem ${@link SquareComponent}
 * 
 * @author Szymon Konicki
 */
public class Sea implements ComponentManager, SymulatorObject {

	private final int x, y;
	private Square[][] squares;
	private List<SquareComponent> squareComponents;

	private  SpreadingSystem spreadingSystem;
	private OilPointSquareSystem oilPointSquareSystem;
	private SpillSystem spillSystem;
	private CenterOfMassSystem centerOfMassSystem;
	private GraphicsSystem graphicsSystem;
	public CenterOfMassSystem getCenterOfMassSystem() {
		return centerOfMassSystem;
	}

	public Sea(int x, int y, Vector2 current, Vector2 wind){
		
		this.x = x;
		this.y = y;
		squares = new Square[x][y];
		for(x = 0; x < squares.length; x++){
			for(y = 0; y < squares[x].length; y++){
				squares[x][y] = new Square(new Vector2(x,y), current,wind,273);
			}
		}
		squareComponents = new ArrayList<SquareComponent>();
	}
	
//	public Sea(int x, int y, int componentListSize){
//		this(x,y);
//		squareComponents = new ArrayList<SquareComponent>(componentListSize);
//		reset();
//	}

	
	@Override
	public void reset() {
		spreadingSystem.reset();
		spillSystem.reset();
		centerOfMassSystem.reset();
		for(int x = 0; x < squares.length; x++){
			for(int y = 0; y < squares[x].length; y++){
				for(SquareComponent sc : squareComponents){
					sc.reset();
				}
				squares[x][y].reset();
			}
		}
		graphicsSystem.reset();
		oilPointSquareSystem.reset();
		
	}
	
	
	/**
	 * Funkcja odpowiedzialna za uaktualnianie obiektu, wszystkich podleg³ych mu {@link SymulatorSystem} oraz dodanych {@link SquareComponent}
	 * 
	 * @param timeDelta przeskalowany czas jaki up³yn¹³ od poprzedniego wywo³ania pêtli
	 */
	public void update(float timeDelta){
		// TODO: create some list of systems
	
		spillSystem.update(timeDelta, this);
		spreadingSystem.update(timeDelta, this);
		centerOfMassSystem.update(timeDelta, this);
		
		for(int x = 0; x < squares.length; x++){
			for(int y = 0; y < squares[x].length; y++){
				for(SquareComponent sc : squareComponents){
					sc.update(this, timeDelta, squares[x][y]);
				}
				squares[x][y].update(this, timeDelta);
			}
		}
		graphicsSystem.update(timeDelta, this);
		oilPointSquareSystem.update(timeDelta, this);
	}

	@Override
	public void addComponent(PhasedComponent component) {
		int i = 0;
		for(PhasedComponent sc: squareComponents){
			if(sc.getPhase() >= component.getPhase()){
				break;
			}
			i++;
		}
		squareComponents.add(i, (SquareComponent) component);
	}

	public Square[][] getSquares() {
		return squares;
	}

	public SpreadingSystem getSpreadingSystem() {
		return spreadingSystem;
	}

	public void setSpreadingSystem(SpreadingSystem spreadingSystem) {
		this.spreadingSystem = spreadingSystem;
	}

	public OilPointSquareSystem getOilPointSquareSystem() {
		return oilPointSquareSystem;
	}

	public void setOilPointSquareSystem(OilPointSquareSystem oilPointSquareSystem) {
		this.oilPointSquareSystem = oilPointSquareSystem;
	}

	public void setSpillSystem(SpillSystem spillSystem) {
		this.spillSystem = spillSystem;
	}

	public void setCenterOfMassSystem(CenterOfMassSystem centerOfMassSystem) {
		this.centerOfMassSystem=centerOfMassSystem;
		
	}

	public void setGraphicsSystem(GraphicsSystem graphicsSystem) {
		this.graphicsSystem = graphicsSystem;
	}



}
