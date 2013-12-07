package logic.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.Position;

import logic.oilpoint.OilPointChangeSquareComponent;
import logic.square.Square;
import logic.square.SquareComponent;
import login.system.OilPointSquareSystem;
import login.system.SpillSystem;
import login.system.SpreadingSystem;

public class Sea implements ComponentManager, SymulatorObject {

	private final int x, y;
	private Square[][] squares;
	private List<SquareComponent> squareComponents;

	private  SpreadingSystem spreadingSystem;
	private OilPointSquareSystem oilPointSquareSystem;
	private SpillSystem spillSystem;
	public Sea(int x, int y){
		this.x = x;
		this.y = y;
		squares = new Square[x][y];
		for(x = 0; x < squares.length; x++){
			for(y = 0; y < squares[x].length; y++){
				squares[x][y] = new Square(new Vector2(x,y));
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
		
	}
	
	public void update(float timeDelta){
		// TODO: create some list of systems
		spreadingSystem.update(timeDelta, this);
		spillSystem.update(timeDelta, this);
		for(int x = 0; x < squares.length; x++){
			for(int y = 0; y < squares[x].length; y++){
				for(SquareComponent sc : squareComponents){
					sc.update(this, timeDelta, squares[x][y]);
				}
				squares[x][y].update(this, timeDelta);
			}
		}
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



}
