package gui.sea;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.core.MainLoop;
import logic.system.GraphicsSystem;

/**
 * Kod napisany na podstawie, kodu udostêpnionego nam na pierwszych laboratoriach przez mgr. in¿. Jakuba Porzyckiego 
 * http://home.agh.edu.pl/~porzycki/doku.php?id=sym:lab1
 */
public class GUI extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private GraphicsSystem board;
	private JButton start;
	private JButton clear;
	private JSlider pred;
	private JFrame frame;
	private int iterNum = 0;
	private final int maxDelay = 500;
	private final int initDelay = 100;
	private boolean running = false;
	private MainLoop mainLoop;
	public GUI(JFrame jf, GraphicsSystem board, MainLoop mainLoop) {
		frame = jf;
		timer = new Timer(initDelay, this);
		timer.stop();
		this.board = board;
		this.mainLoop = mainLoop;
	}
	
	public void initialize(Container container) {
		container.setLayout(new BorderLayout());
		container.setSize(new Dimension(1024, 768));

		JPanel buttonPanel = new JPanel();

		start = new JButton("Start");
		start.setActionCommand("Start");
		start.setToolTipText("Starts clock");
		start.addActionListener(this);

		clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.setToolTipText("Clears the board");
		clear.addActionListener(this);


		pred = new JSlider();
		pred.setMinimum(0);
		pred.setMaximum(maxDelay);
		pred.setToolTipText("Time speed");
		pred.addChangeListener(this);
		pred.setValue(maxDelay - timer.getDelay());

		buttonPanel.add(start);
		buttonPanel.add(clear);
		buttonPanel.add(pred);

		
		container.add(board, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);
	}



	/**
	 * slider to control simulation speed
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e) {
		timer.setDelay(maxDelay - pred.getValue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			iterNum++;
			frame.setTitle("Symulacja rozprzestrzeniania siê ropy naftowej (" + Integer.toString(iterNum) + " iteration)");
			mainLoop.loop();
		} else {
			String command = e.getActionCommand();
			if (command.equals("Start")) {
				if (!running) {
					timer.start();
					start.setText("Pause");
				} else {
					timer.stop();
					start.setText("Start");
				}
				running = !running;
			}else if(command.equals("clear") &&  !running){
				mainLoop.reset();
			}

		}
		
	}
}
