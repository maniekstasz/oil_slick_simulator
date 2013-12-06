package logic;

public interface OilPointComponent extends PhasedComponent, SymulatorObject{
	void update(Square square, float timeDelta, OilPoint oilPoint);
	
}
