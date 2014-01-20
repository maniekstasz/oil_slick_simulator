package logic.core;

enum Par {
	x(100),  //ilosc kwadratow w poziomie		
	y(100), //ilosc kwadratow w pionie
	timeDelta(900),  //krok czasowy [s]
	squareDimension(1000), // dlugosc kwadratu [m]
	xOfSpill(50000), //polozenie  wyplywu ropy na x[m]
	yOfSpill(50000), ////polozenie  wyplywu ropy na y[m]
	numberOfOilPoints(1000), //liczba punktow ropy
	spillAmount(10), //szybkosc wyplywu ropy [pkt/s]
	diameter(2000),  //srednica poczatkowa plamy ropy [m]
	densityOfWater(1024),  // gestosc wody [kg/m^3]
	densityOfOil(804),   // gestosc oleju [kg/m^3]
	startVolume(1000),   // poczatkowa objetosc plamy [m^3]
	diffusionCoefficent(10),  //wspolczynnik dyfuzji  [m^2/s] (art 1.)
	
	T(308),  // temperatura wody [K]
	/*stale do klasy DifferentialEquationSpreadinSystem*/ 
	viscosity(2.16f), // lepkosc [cSt]
	rk4TimeStep(0.1f),   //krok czasowy w metodzie rk4
	st(20),  //wsp napiecia powierzchniowego [mNm^-1]
	Tg(259),
	T0(496),
	K(1),
	B(100),
	ANonArea(10),
	C3(0.7f),  //pewne
	K1(150f), //pewne
	C4(10.0f), 
   /***************************************/
	/*stale do pr¹du i aiatru*/
	currentX(0f),
	currentY(0f),
	windX(0f),
	windY(0f),
	currentParameter(1.1f),
	windParameter(0.03f),
	
	
	// grafika
	graphicsSquareSize(10),
	maxThickness(50);


	final float defaultValue;

	Par(float defaultValue) {
		this.defaultValue = defaultValue;
	}

}




/* publikacje*****************************
[1] A stochastic simulation model of oil spill fate
and transport
A. H. Al-Rabeh, H. M. Cekirge, and N. Gunay


[2] Modelling the Fate of Oil Sills at Sea.
A. H. Al-Rabeh, H. M. Cekirge, and N. Gunay
*/