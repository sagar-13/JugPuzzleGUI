package JugPuzzle;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
* A JLabel. Used for informing user of what move happened, 
* congratulating user upon winning, and informing when
* moves have been reset. Observes a JugPuzzle and updates
* based on various things being sent to it.
*
*/

public class JugPuzzleView extends JLabel implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		JugPuzzle jp = (JugPuzzle)o;
		this.setText("" + arg);	
	}
	
}


