package logic.core;


// Klasa pomocna w sortowaniu component�w. Musz� by� one posortowane, �eby np. pozycja OilPoint nie zmieni�a si� wczesniej
// ni� bd obliczona jego pr�dko��.
public abstract class  PhasedComponentImpl implements PhasedComponent{
	
	private final int phase;
	
	public PhasedComponentImpl(int phase){
		this.phase = phase;
	}
	
	public enum Phase{
		PRE_PHISICS,
		PHISICS,
		POST_PHISICS,
		POST_MOVEMENT,
		OUTPUT,
	}
	
	public int getPhase(){
		return phase;
	}
	
}
