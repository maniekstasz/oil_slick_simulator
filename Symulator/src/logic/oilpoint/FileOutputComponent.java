package logic.oilpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import logic.core.PhasedComponentImpl;
import logic.square.Square;
import login.system.TimeSystem;

public class FileOutputComponent extends PhasedComponentImpl implements
		OilPointComponent {

	private final TimeSystem timeSystem;
	private final String folder = String.valueOf(new Date().getTime());
	public FileOutputComponent(TimeSystem timeSystem) {
		super(Phase.OUTPUT.ordinal());
		this.timeSystem = timeSystem;
		new File("uruchomienia/" + folder).mkdirs();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Square square, float timeDelta, OilPoint oilPoint) {
		Long id = oilPoint.getId();
		PrintWriter pw = null;
		File log = new File("uruchomienia/"+ folder + "/" + id.toString() + ".txt");
		try {
			
			if (!log.exists()) {
				log.createNewFile();
			}
			pw = new PrintWriter(new FileWriter(log, true));
			pw.println(oilPoint.getPosition().x + " "
					+ oilPoint.getPosition().y + " " + timeSystem.getTotalTime());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null)
				pw.close();
		}

	}

}
