package gui.sea;
import javax.swing.JFrame;

import logic.core.Symulator;

/**
 * Kod napisany na podstawie, kodu udostêpnionego nam na pierwszych laboratoriach przez mgr. in¿. Jakuba Porzyckiego 
 * http://home.agh.edu.pl/~porzycki/doku.php?id=sym:lab1
 */
public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUI gof;

	public Program() {
		setTitle("Symulacja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Symulator symulator = new Symulator();
		symulator.configure(this);
//		gof = new GUI(this);
//		gof.initialize(this.getContentPane());

		this.setSize(1024, 768);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Program();
	}

}
