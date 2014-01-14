package gui.sea;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logic.core.Symulator;

/**
 * Kod napisany na podstawie, kodu udostêpnionego nam na pierwszych laboratoriach przez mgr. in¿. Jakuba Porzyckiego 
 * http://home.agh.edu.pl/~porzycki/doku.php?id=sym:lab1
 */
public class Program extends JFrame {

	private static final long serialVersionUID = 1L;

	public Program() {
		setTitle("Symulacja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Symulator symulator = new Symulator();
		File file = input();
		if (file == null) {
			JOptionPane
					.showMessageDialog(this,
							"Nie podano pliku z parametrami. Zostan¹ u¿yte wartoœci domyœlne.");
			symulator.configure(this);
		} else {
			JOptionPane.showMessageDialog(this, "Wybrano plik z parametrami: "
					+ file.getAbsolutePath());
			symulator.configure(this, file);
		}
		// gof = new GUI(this);
		// gof.initialize(this.getContentPane());

		this.setSize(1024, 768);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Program();
	}

	private File input() {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();

		} else {
			return null;
		}

	}
}
