package login.system;

import logic.core.Sea;
import logic.core.Vector2;

public class DifferentalEquationsSpreadingSystem implements SpreadingSystem {
	/* wszedzie czas w sekundach */

	private final TimeSystem timeSystem;

	private final CenterOfMassSystem centerOfMassSystem;

	private float T, W; // wartosci w kazdej iteracji brane z kwadratu
						// zawierajacego srodek ciezkosci
	/****************************************************/
	/* PARAMETRY */
	private float K;
	private float B;
	private float T0;
	private float Tg;
	private float D;
	private float C3;
	private float K1;
	private float C4;
	private float roW; // gestoœæ wody
	private float roC; // orginalna gêstoœæ oleju
	private float st; // wspolczynnik napiecia powierzchniowego

	/************************************************************/

	private float rk4TimeStep=1;
	private RK4 rK4;

	// * wartosci liczone RK4*//
	double[] values = new double[5];
	double[] startValues = new double[5];

	private static final int Fe_EVAPRATION = 0;
	private static final int V_VOLUME = 1;
	private static final int Y_CONT_WATER = 2;
	private static final int A_AREA = 3;
	private static final int MI_VISCOSITY = 4;

	
	// *****************************//

	private float previousDiameter;
	private float diameter;

	public DifferentalEquationsSpreadingSystem(
			CenterOfMassSystem centerOfMassSystem, TimeSystem timeSystem) {

		this.centerOfMassSystem = centerOfMassSystem;
		this.timeSystem = timeSystem;
		Vector2 vectorOfWind = centerOfMassSystem.getWind();
		if (vectorOfWind != null) {
			this.W = vectorOfWind.length();
		} else {
			this.W = 0;
		}
		this.T = centerOfMassSystem.getTemperature();
		rK4 = new RK4(new Function());
	}

	public void setupStartValues(float Fe0, float V0, float Y0, float A0,
			float mi0) {
		values[Fe_EVAPRATION] = Fe0;
		values[V_VOLUME] = V0;
		values[Y_CONT_WATER] = Y0;
		values[A_AREA] = A0;
		values[MI_VISCOSITY] = mi0;
		startValues[Fe_EVAPRATION] = Fe0;
		startValues[V_VOLUME] = V0;
		startValues[Y_CONT_WATER] = Y0;
		startValues[A_AREA] = A0;
		startValues[MI_VISCOSITY] = mi0;

	}

	class Function {
		double V0 =startValues[V_VOLUME];

		private double dFe_dt(double t, double A, double Fe) {
			double result = K * A / V0 * Math.exp(A - B / T * (T0 + Tg * Fe));
			return result;
		}

		private double dV_dt(double t, double dFe_dt, double V) {
			double result = -V0 * dFe_dt - D * V;
			return result;
		}

		private double dY_dt(double t, double Y) {
			double result = 2E-6 * (W + 1) * (W + 1) * (1 - Y / C3);
			return result;

		}

		private double dA_dt(double t, double A, double V) {
			double result = K1 / A * Math.pow(V, 4. / 3);
			return result;
		}

		private double dmi_dt(double mi, double dFe_dt, double Y, double dY_dt) {
			double result = C4 * mi * dFe_dt * (2.5 * mi)
					/ Math.pow((1 - C3 * Y), 2.) * dY_dt;
			return result;

		}

		double[] evaluate(double t, double[] args) {
			double Fe = args[Fe_EVAPRATION];
			double V = args[V_VOLUME];
			double Y = args[Y_CONT_WATER];
			double A = args[A_AREA];
			double mi = args[MI_VISCOSITY];

			double[] results = new double[5];
			results[Fe_EVAPRATION] = dFe_dt(t, A, Fe);
			results[V_VOLUME] = dV_dt(t, results[0], V);
			results[Y_CONT_WATER] = dY_dt(t, Y);
			results[A_AREA] = dA_dt(t, A, V);
			results[MI_VISCOSITY] = dmi_dt(mi, dFe_dt(t, A, Fe), Y, dY_dt(t, Y));

			return results;

		}

	}

	class RK4 {
		Function function;

		double[] k1 = new double[5];
		double[] k2 = new double[5];
		double[] k3 = new double[5];
		double[] k4 = new double[5];
		double[] k = new double[5];

		RK4(Function function) {
			this.function = function;
		}

		void solve(float tStart, float tEnd, float delta) {

			for (int count = 0; count * delta < (tEnd - tStart); count++) {
				float actualTime = tStart + count * delta;
				k1 = multiple(function.evaluate(actualTime, values), delta);
				k2 = multiple(
						function.evaluate(actualTime + delta / 2,
								add(values, multiple(k1, 0.5))), delta);
				k3 = multiple(
						function.evaluate(actualTime + delta / 2,
								add(values, multiple(k2, 0.5))), delta);
				k4 = multiple(
						function.evaluate(actualTime + delta, add(values, k3)),
						delta);
				k = multiple(add(add(k1, k2), add(k3, k4)), 1. / 6);
				values = add(values, k);
			}

		}

		double[] multiple(double[] vector, double a) {
			double[] result = new double[5];
			for (int i = 0; i < vector.length; i++) {
				result[i] = vector[i] * a;
			}
			return vector;
		}

		double[] add(double[] vector, double[] vector2) {
			double[] result = new double[5];
			for (int i = 0; i < vector.length; i++) {
				result[i] = vector[i] + vector2[i];
			}
			return vector;
		}

	}

	@Override
	public void update(float timeDelta, Sea sea) {
		previousDiameter = (float) Math.sqrt(values[3] * 4 / Math.PI);
		Vector2 vectorOfWind = centerOfMassSystem.getWind();
		if (vectorOfWind != null) {
			this.W = vectorOfWind.length();
		} else {
			this.W = 0;
		}

		T = centerOfMassSystem.getTemperature();
		float totalTime = timeSystem.getTotalTime();
		float deltaTime = timeSystem.getTimeDelta();
		float density = (float) (values[Fe_EVAPRATION] * roW + (1 - values[Y_CONT_WATER])
				* (roC + C3 * values[0]));
		float thicknes = (float) (values[V_VOLUME] / values[A_AREA]);
		D = (float) (0.11 * (1 + W) * (1 + W) * 1.0 / (1 + 50
				* Math.pow(values[MI_VISCOSITY], 0.5) * thicknes * st));

		rK4.solve(totalTime, totalTime + deltaTime, rk4TimeStep);
		diameter = (float) Math.sqrt(values[A_AREA] * 4 / Math.PI);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public float getActualDiameter() {
		return diameter;
	}

	@Override
	public float getPreviousDiameter() {
		return previousDiameter;
	}

}
