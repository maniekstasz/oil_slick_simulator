package login.system;

import java.util.ArrayList;
import java.util.List;

import logic.core.Sea;
import logic.core.Vector2;
import logic.oilpoint.OilPoint;
import logic.square.Square;

public class CenterOfMassSystem implements SymulatorSystem {

	Vector2 centerOfMass=null;
	private float temperature;
	private Vector2 wind;  // wind and current in the square which it's located center of mass
	private Vector2 current;
	private float squareDimension;
	
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public CenterOfMassSystem(OilPointSquareSystem oilPointSquareSystem) {
		this.squareDimension=oilPointSquareSystem.getSquareDimension();
	}

	@Override
	public void update(float timeDelta, Sea sea) {
		OilPointSquareSystem opss=sea.getOilPointSquareSystem();
		List<OilPoint>[][] lists=opss.getNextRoundSquares();
		float x=0;
		float y=0;
		int amount=0;
		for(int i=0;i<lists.length;i++){
			for(int j=0;j<lists[i].length;j++){
				
				 for(OilPoint op:lists[i][j]){
					 Vector2 actualPos=op.getPosition();
					 x+=actualPos.x;
					 y+=actualPos.y;
					 amount++;
				 }
			}
		}
		x=x/amount;
		y=y/amount;
		centerOfMass=new Vector2(x,y);
		System.out.println("center: "+centerOfMass.x+" "+centerOfMass.y);
		
		
		Square square=sea.getSquares()[(int)(x/squareDimension)][(int)(y/squareDimension)];   // square with center of mass
		temperature=square.getTemperature();
		wind=square.getWind();
		current=square.getCurrent();
	}

	public float getTemperature() {
		return temperature;
	}

	public Vector2 getWind() {
		return wind;
	}

	public Vector2 getCurrent() {
		return current;
	}

	public Vector2 getCenterOfMass() {
		return centerOfMass;
	}

}
