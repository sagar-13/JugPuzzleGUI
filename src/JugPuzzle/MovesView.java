package JugPuzzle;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * We extend JLabel as a view on a JugPuzzle. 
 * This JLabel updates only when a move is 
 * made.
 *
 */

public class MovesView extends JLabel implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		JugPuzzle jp = (JugPuzzle)o;
		this.setText("Moves: " + jp.getMoves());	
	}
	
}



