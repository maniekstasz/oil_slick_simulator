package logic;

public interface SquareComponent extends PhasedComponent, SymulatorObject {
	void update(Sea sea, float timeDelta, Square square);
}
