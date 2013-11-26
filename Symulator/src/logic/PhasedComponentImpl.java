package logic;


// Klasa pomocna w sortowaniu componentów. Musz¹ byæ one posortowane, ¿eby np. pozycja OilPoint nie zmieni³a siê wczesniej
// ni¿ bd obliczona jego prêdkoœæ.
public abstract class  PhasedComponentImpl implements PhasedComponent{
	
	private final int phase;
	
	public PhasedComponentImpl(int phase){
		this.phase = phase;
	}
	
	public enum Phase{
		PRE_PHISICS,
		PHISICS,
		POST_PHISICS,
	}
	
	public int getPhase(){
		return phase;
	}
	
}
