package logic;

import java.util.ArrayList;
import java.util.List;

public class Square implements ComponentManager, SymulatorObject {
	private Vector2 position;
	
	private List<OilPoint> oilPoints;
	private List<OilPointComponent> oilPointComponents;
	
	
	public Square(Vector2 position, int componentsSize) {
		this.position = position;
		oilPointComponents = new ArrayList<OilPointComponent>(componentsSize);
	}
	
	@Override
	public void reset() {
		
		
	}
	
	public void update(Sea sea, float timeDelta){
		for(OilPoint oilPoint: oilPoints){
			for(OilPointComponent oc: oilPointComponents){
				oc.update(this, timeDelta, oilPoint);
			}	
			oilPoint.update(this, timeDelta);
		}
	}
	
	//TODO
	public boolean inSquare(Vector2 point){
		return false;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	// Dodajemy komponenty tylko t¹ metod¹ bêd¹ one wtedy odpowiednio posortowane
	@Override
	public void addComponent(PhasedComponent component) {
		int i = 0;
		for(PhasedComponent sc: oilPointComponents){
			if(sc.getPhase() >= component.getPhase()){
				break;
			}
			i++;
		}
		oilPointComponents.add(i, (OilPointComponent) component);
	}


}
