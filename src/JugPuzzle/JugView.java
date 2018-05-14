
package JugPuzzle;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * A JTextField. Observes a jug and updates
 * text when the amount of water in a jug changes
 *
 */

public class JugView extends JTextField implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		Jug jug = (Jug)o;
		this.setText(""+jug.getAmount());	
		
	}
	
}


