package logic;

public interface OilPointComponent extends PhasedComponent{
	void update(Square square, float timeDelta, OilPoint oilPoint);
	
}
