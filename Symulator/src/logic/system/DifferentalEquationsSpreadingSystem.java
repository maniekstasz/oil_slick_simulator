package logic.system;

import logic.core.Sea;
import logic.core.Vector2;

public class DifferentalEquationsSpreadingSystem implements SpreadingSystem {
	/* wszedzie czas w sekundach */

	private final TimeSystem timeSystem;

	private final CenterOfMassSystem centerOfMassSystem;

	private float T; // wartosci w kazdej iteracji brane z kwadratu //
						// zawierajacego srodek ciezkosci
	private float W;
	private float D = 0;
	private float prevoiusVolume;

	/****************************************************/
	/* PARAMETRY */
	private float K = 1;// ?
	private float B = 100;// ?
	private float ANonArea = 1;
	private float T0 = 301;
	private float Tg = 500;
	private float C3 = 0.7f;
	private float K1 = 1f; // ?
	private float C4 = 10;
	private float densityOfWater = 1.020f; // gestoœæ wody
	private float densityOfOil = 0.832f; // orginalna gêstoœæ oleju
	private float st = 1; // wspolczynnik napiecia powierzchniowego
	private float rk4TimeStep = 0.1f;

	/************************************************************/

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
		this.rK4 = new RK4(new Function());
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

	public void setupParameters(float K, float B, float ANonArea, float T0,
			float Tg, float C3, float K1, float C4, float densityOfWater,
			float densityOfOil, float st, float rk4TimeStep) {

		this.K = K;
		this.B = B;
		this.ANonArea = ANonArea;
		this.T0 = T0;
		this.Tg = Tg;
		this.C3 = C3;
		this.K1 = K1; // ?
		this.C4 = C4;
		this.densityOfWater = densityOfWater / 1000; // gestoœæ wody [tu w
														// g/cm^3]
		this.densityOfOil = densityOfOil / 1000; // orginalna gêstoœæ oleju
		this.st = st; // wspolczynnik napiecia powierzchniowego
		this.rk4TimeStep = rk4TimeStep;
	}

	class Function {

		private double dFe_dt(double t, double Fe) {
			double V0 = startValues[V_VOLUME];

			double result = K * ANonArea / V0
					* Math.exp(ANonArea - B / T * (T0 + Tg * Fe));

			return result;
		}

		private double dV_dt(double t, double dFe_dt, double V) {
			double V0 = startValues[V_VOLUME];

			double result = -V0 * dFe_dt - D / 3600 * V;

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
			results[Fe_EVAPRATION] = dFe_dt(t, Fe);
			results[V_VOLUME] = dV_dt(t, results[0], V);
			results[Y_CONT_WATER] = dY_dt(t, Y);
			results[A_AREA] = dA_dt(t, A, V);
			results[MI_VISCOSITY] = dmi_dt(mi, dFe_dt(t, Fe), Y, dY_dt(t, Y));

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
			float actualTime = tStart;
			while (actualTime < tEnd) {
				float thicknes = (float) (values[V_VOLUME] / values[A_AREA]);
				D = (float) (0.11 * (1 + W) * (1 + W) * 1.0 / (1 + 50
						* Math.pow(values[MI_VISCOSITY], 0.5) * thicknes * st));
				k1 = function.evaluate(actualTime, values);
				k2 = function.evaluate(actualTime + delta / 2,
						add(values, multiple(delta / 2, k1)));
				k3 = function.evaluate(actualTime + delta / 2,
						add(values, multiple(delta / 2, k2)));
				k4 = function.evaluate(actualTime + delta,
						add(values, multiple(delta, k3)));
				k = multiple(1. / 6, add(k1, k2, k2, k3, k3, k4)); // trzeba
																	// pomnioazyc
				values = add(values, multiple(delta, k));

				// values =
				// add(values,multiple(delta,function.evaluate(actualTime,
				// values)));// EULER
				actualTime += delta;
			}

		}

		double[] multiple(double a, double[] vector) {
			double[] result = new double[5];
			for (int i = 0; i < vector.length; i++) {
				result[i] = vector[i] * a;
			}
			return result;
		}

		double[] add(double[]... vectors) {

			double[] result = new double[5];
			for (double[] vector : vectors) {
				for (int i = 0; i < vector.length; i++) {
					result[i] += vector[i];
				}
			}
			return result;
		}

	}

	@Override
	public void update(float timeDelta, Sea sea) {
		this.previousDiameter = (float) Math.sqrt(values[A_AREA] * 4 / Math.PI);
		this.prevoiusVolume = (float) values[V_VOLUME];

		Vector2 vectorOfWind = centerOfMassSystem.getWind();
		if (vectorOfWind != null) {
			this.W = vectorOfWind.length();
		} else {
			this.W = 0;
		}

		T = centerOfMassSystem.getTemperature();
		float totalTime = timeSystem.getTotalTime();
		float deltaTime = timeSystem.getTimeDelta();
		float density = (float) (values[Fe_EVAPRATION] * densityOfWater + (1 - values[Y_CONT_WATER])
				* (densityOfOil + C3 * values[Fe_EVAPRATION]));// ??

		rK4.solve(totalTime, totalTime + deltaTime, rk4TimeStep);
		diameter = (float) Math.sqrt(values[A_AREA] * 4 / Math.PI);
//		System.out.println("D " + D);
//		System.out.println("Fe " + values[0] + "   V " + values[1] + "  Y "
//				+ values[2] + " " + values[3] + "   mi " + values[4]);

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

	public float getRatioOfVolume() {
		return (float) (values[V_VOLUME] / prevoiusVolume);
	}

}
