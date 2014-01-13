package gui.sea;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logic.core.Symulator;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUI gof;

	public Program() {
		setTitle("Symulacja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Symulator symulator = new Symulator();
		File file = input();
		if (file == null) {
			JOptionPane
					.showMessageDialog(this,
							"Nie podano pliku z parametrami. Zostan� u�yte warto�ci domy�lne.");
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
