package logic;

public interface SquareComponent extends PhasedComponent {
	void update(Sea sea, float timeDelta, Square square);
}
