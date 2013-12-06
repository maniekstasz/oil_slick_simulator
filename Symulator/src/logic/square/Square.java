package logic.square;

import java.util.ArrayList;
import java.util.List;

import logic.core.ComponentManager;
import logic.core.PhasedComponent;
import logic.core.Sea;
import logic.core.SymulatorObject;
import logic.core.Vector2;
import logic.oilpoint.OilPoint;
import logic.oilpoint.OilPointComponent;


public class Square implements ComponentManager, SymulatorObject {
	private Vector2 position;

	private List<OilPoint> oilPoints;
	private List<OilPointComponent> oilPointComponents;
	private List<OilPoint> nextRoundOilPoints;
	
	private Vector2 wind; // m/s
	private Vector2 current; // m/s

	public Vector2 getWind() {
		return wind;
	}

	public Vector2 getCurrent() {
		return current;
	}

	public Square(Vector2 position, int componentsSize) {
		this.position = position;
		oilPointComponents = new ArrayList<OilPointComponent>(componentsSize);
		oilPoints = new ArrayList<OilPoint>();
	}
	public Square(Vector2 position) {
		this.position = position;
		oilPointComponents = new ArrayList<OilPointComponent>();
		oilPoints = new ArrayList<OilPoint>();
	}

	@Override
	public void reset() {

	}

	public void update(Sea sea, float timeDelta) {
		for (OilPoint oilPoint : oilPoints) {
			for (OilPointComponent oc : oilPointComponents) {
				oc.update(this, timeDelta, oilPoint);
			}
			oilPoint.update(this, timeDelta);
		}
	}


	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	// Dodajemy komponenty tylko t¹ metod¹ bêd¹ one wtedy odpowiednio
	// posortowane
	@Override
	public void addComponent(PhasedComponent component) {
		int i = 0;
		for (PhasedComponent sc : oilPointComponents) {
			if (sc.getPhase() >= component.getPhase()) {
				break;
			}
			i++;
		}
		oilPointComponents.add(i, (OilPointComponent) component);
	}

	public List<OilPoint> getOilPoints() {
		return oilPoints;
	}

	public void setOilPoints(List<OilPoint> oilPoints) {
		this.oilPoints = oilPoints;
	}

	public void setWind(Vector2 wind) {
		this.wind = wind;
	}

	public void setCurrent(Vector2 current) {
		this.current = current;
	}

}
