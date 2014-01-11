package logic.core;

/**
 * Po³o¿enia, prêdkoœci oraz si³y dzia³aj¹ce na obiekty s¹ zapisywane jako dwuwymiarowy wektor. 
 * Klasa udostêpnia metody wykonuj¹ce przekszta³cenia na wektorach
 * 
 * @author Szymon Konicki
 *
 */
public final class Vector2 {
	public float x;
	public float y;

	public static final Vector2 ZERO = new Vector2(0, 0);

	public Vector2() {
		super();
	}

	public Vector2(float xValue, float yValue) {
		set(xValue, yValue);
	}

	public Vector2(Vector2 other) {
		set(other);
	}

	public final void add(Vector2 other) {
		x += other.x;
		y += other.y;
	}

	public final void add(float otherX, float otherY) {
		x += otherX;
		y += otherY;
	}

	public final void subtract(Vector2 other) {
		x -= other.x;
		y -= other.y;
	}

	public final void multiply(float magnitude) {
		x *= magnitude;
		y *= magnitude;
	}

	public final void multiply(Vector2 other) {
		x *= other.x;
		y *= other.y;
	}

	public final void divide(float magnitude) {
		if (magnitude != 0.0f) {
			x /= magnitude;
			y /= magnitude;
		}
	}

	public final void set(Vector2 other) {
		x = other.x;
		y = other.y;
	}

	public final void set(float xValue, float yValue) {
		x = xValue;
		y = yValue;
	}

	public final float dot(Vector2 other) {
		return (x * other.x) + (y * other.y);
	}

	public final float length() {
		return (float) Math.sqrt(length2());
	}

	public final float length2() {
		return (x * x) + (y * y);
	}

	public final float distance2(Vector2 other) {
		float dx = x - other.x;
		float dy = y - other.y;
		return (dx * dx) + (dy * dy);
	}

	public final float normalize() {
		final float magnitude = length();

		if (magnitude != 0.0f) {
			x /= magnitude;
			y /= magnitude;
		}

		return magnitude;
	}

	public final void zero() {
		set(0.0f, 0.0f);
	}

	public final void flipHorizontal(float aboutWidth) {
		x = (aboutWidth - x);
	}

	public final void flipVertical(float aboutHeight) {
		y = (aboutHeight - y);
	}

	public float getR() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public static Vector2 createFromPolar(float r, float theta) {
		float x = (float) ((float) r * Math.cos(theta));
		float y = (float) ((float) r * Math.sin(theta));
		return new Vector2(x, y);
	}

	public float getTheta() {
		float r = this.getR();
		float theta = 0;
		if (r != 0.f) {
			theta = (float) Math.acos(x / r);
			if (y < 0) {
				theta = -theta;
			}
		}
		return theta;
	}
}
