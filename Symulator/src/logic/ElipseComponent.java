package logic;

public class ElipseComponent extends PhasedComponentImpl implements
OilPointComponent {

	private final ElipseSystem elipseSystem;
	public ElipseComponent(ElipseSystem elipseSystem) {
		//TODO: tutaj trzeba ustawi� poprawn� jesli ta jest z�a :)
		super(PhasedComponentImpl.Phase.PHISICS.ordinal());
		this.elipseSystem = elipseSystem;
	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		// TODO Auto-generated method stub
		
	}

}
