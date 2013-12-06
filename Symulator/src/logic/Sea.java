package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Sea implements ComponentManager, SymulatorObject {

	private final int x, y;
	private Square[][] squares;
	private List<SquareComponent> squareComponents;

	private ElipseSystem elipseSystem;
	public Sea(int x, int y, int componentListSize){
		this.x = x;
		this.y = y;
		squareComponents = new ArrayList<SquareComponent>(componentListSize);
		reset();
	}
	
	@Override
	public void reset() {
		
	}
	
	public void update(float timeDelta){
		// TODO: create some list of systems
		elipseSystem.update(timeDelta, this);
		for(int x = 0; x < squares.length; x++){
			for(int y = 0; y < squares[x].length; y++){
				for(SquareComponent sc : squareComponents){
					sc.update(this, timeDelta, squares[x][y]);
				}
				squares[x][y].update(this, timeDelta);
			}
		}
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


}
