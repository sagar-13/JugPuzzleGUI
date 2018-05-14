
package JugPuzzle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A class used for controlling the GUI. Contains the main method that
 * runs the GUI. It also implements an ActionListener which handles every
 * possible button press from the GUI. 
 * @author Sagar Suri
 *
 */
public class JugPuzzleGUIController implements ActionListener {
	
	int index; // index used for the move method
	JugPuzzle jugPuzzle; // The game
	JFrame frame; //frame, need this here to make dialog boxes	
	
	/**
	 * Constructor. Creates an instance of the JugPuzzleGUIController.
	 * @param jugPuzzle jp - game
	 * @param int i - index of the jug that is being selected
	 * @param JFrame frame - frame passed in used for dialog box
	 */
	public JugPuzzleGUIController(JugPuzzle jp, int i, JFrame frame) {
		this.jugPuzzle = jp;
		this.index = i;
		this.frame = frame;
		}
	
	// these static variables store data between button clicks
	static boolean fromJugSelected = false; // Whether click has occurred
	static int fromIndex; // Index of the "from" jug
	static JButton blurButton; // to blur button that is selected
	
	/**
	 * The action performed when a button that uses
	 * JugPuzzleGUIController as an ActionListener is pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		// Quit Button Pressed
		if (e.getActionCommand()=="Quit")
			System.exit(0);

		// New Game Button Pressed
		if (e.getActionCommand()=="New Game"){
			resetGame();
			blurButton.setEnabled(true);
			return;	
		}
		
		// Halts game if solved
		if (this.jugPuzzle.getIsPuzzleSolved()){
			return;
			// Note: only noticeable if you exit dialog box
			// using 'x' button after game won. 
			// (Other options are New Game or Quit)
		}
		
		// Remaining condition is making a move:
		if (!fromJugSelected){
			fromJugSelected = true; // first jug selected
			fromIndex = this.index; // Store index for spill.
			blurButton = (JButton) e.getSource();
			// Disable the button so user sees which jug they selected
			blurButton.setEnabled(false);	
			
		} else {
			//user has selected 2 buttons, now we should be spilling:
			this.jugPuzzle.move(fromIndex, this.index);
			fromJugSelected = false; //reset 
			blurButton.setEnabled(true); // re-enable for new selection
			
			// Dialog box for when user solves the game
			if (this.jugPuzzle.getIsPuzzleSolved()){
				Object[] options = {"New Game","Quit"};
				int result = 
						JOptionPane.showOptionDialog(frame, 
						"Would you like to start a new game or quit?",
						"Congratulations, You Won!",
						 JOptionPane.YES_NO_OPTION, 
						 JOptionPane.QUESTION_MESSAGE,
						 null, options, options[1]);
				// If the user selects New Game
				if (result == JOptionPane.YES_OPTION)
					resetGame();
				// If the user selects Quit
				if (result == JOptionPane.NO_OPTION)
					System.exit(0);	
			}
		}	
	}
	
	/**
	 * Helper method for actionPerformed. Resets the current
	 * JugPuzzle and sets everything as if it was a new game.
	 */
	public void resetGame(){
		// Empty All Jugs
		for (int i=0; i<3; i++){
			int amount = this.jugPuzzle.getJugs()[i].getAmount();
			this.jugPuzzle.getJugs()[i].remove(amount);	
		}
		// Set up new game
		this.jugPuzzle.getJugs()[0].add(8);
		this.jugPuzzle.setMoves(0);
	}
	
	
	/**
	 * main method of JugPuzzleGUIController. This is responsible
	 * for creating the actual GUI (Not designing)
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JugPuzzleGUI.createAndShowGUI();
			}
		});
	}

	
}
