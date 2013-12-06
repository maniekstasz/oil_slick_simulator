package logic;

public class ElipseSystem implements SymulatorSystem{

	
	private final TimeSystem timeSystem;
	
	public ElipseSystem(TimeSystem timeSystem){
		this.timeSystem = timeSystem;
	}
	
	
	
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void update(float timeDelta, Sea sea) {
		
		// TODO 
		timeSystem.getTotalRealTime();
		
	}

}
