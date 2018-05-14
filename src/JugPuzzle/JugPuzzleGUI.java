package JugPuzzle;

import javax.swing.*;
import java.awt.*;

/**
 * A class which contains the GUI for the JugPuzzle game.
 * @author Sagar Suri
 *
 */
public class JugPuzzleGUI {
	
	/**
	 * This method is responsible for designing the GUI. 
	 * It links the model to views which allow elements
	 * on the GUI to update automatically when the state
	 * of the game is altered.
	 */
	public static void createAndShowGUI() {
		
		// Model
		JugPuzzle jp = new JugPuzzle();
					
		//Views for elements on the GUI:
		
			//JLabel for what move happened:
		JugPuzzleView view = new JugPuzzleView();
		view.setText("Make a move!");
		
			// JLabel - move counter:
		MovesView moves = new MovesView();
		moves.setText("Moves: ");
		
			// 3 TextFields for jug water amounts:
		JugView Jug0 = new JugView();
		JugView Jug1 = new JugView();
		JugView Jug2 = new JugView();
		
		//Hook up the model to the views
		jp.addObserver(view);
		jp.addObserver(moves);
		jp.getJugs()[0].addObserver(Jug0);
		jp.getJugs()[1].addObserver(Jug1);
		jp.getJugs()[2].addObserver(Jug2);
		
		// Create the GUI
		JFrame frame = new JFrame("Jug Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(204, 233, 239));
		
		// Modify components for use
			// User shouldn't edit textFields.
		Jug0.setEditable(false);
		Jug1.setEditable(false);
		Jug2.setEditable(false);
			// Center Text
		Jug0.setHorizontalAlignment(JTextField.CENTER);
		Jug1.setHorizontalAlignment(JTextField.CENTER);
		Jug2.setHorizontalAlignment(JTextField.CENTER);
			//Set Initial Amounts:
		Jug0.setText("" + jp.getJugs()[0].getAmount());
		Jug1.setText("" + jp.getJugs()[1].getAmount());
		Jug2.setText("" + jp.getJugs()[2].getAmount());
		
		// Layout components in frame
		frame.getContentPane().setLayout(new GridLayout(3, 3));
		
		// Create the buttons
		JButton but0, but1, but2, quit, newGame; 
		but0 = new JButton("Jug0: " + jp.getJugs()[0].getCapacity());
		but1 = new JButton("Jug1: " + jp.getJugs()[1].getCapacity());
		but2 = new JButton("Jug2: " + jp.getJugs()[2].getCapacity());
		quit = new JButton("Quit");
		newGame = new JButton("New Game");
	
		// add everything to the contentPane
		frame.add(Jug0);
		frame.add(Jug1);
		frame.add(Jug2);
		frame.add(but0);
		frame.add(but1);
		frame.add(but2);
		frame.add(view);
		
		JPanel movesPanel = new JPanel();	
		movesPanel.add(moves);
		frame.add(movesPanel);
		movesPanel.setBackground(new Color(204, 233, 239));;
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(quit);
		buttonPanel.add(newGame);
		frame.add(buttonPanel);
		buttonPanel.setBackground(new Color(204, 233, 239));
	
		// Create button press event handlers
		JugPuzzleGUIController j0 = new JugPuzzleGUIController(jp, 0, frame);
		JugPuzzleGUIController j1 = new JugPuzzleGUIController(jp, 1, frame);
		JugPuzzleGUIController j2 = new JugPuzzleGUIController(jp, 2, frame);
	
		JugPuzzleGUIController quitListen =
				new JugPuzzleGUIController(jp, 3, frame);
		JugPuzzleGUIController newGameListen = 
				new JugPuzzleGUIController(jp, 3, frame);
		
		// Tell the buttons who they should call when they are pressed
		but0.addActionListener(j0);
		but1.addActionListener(j1);
		but2.addActionListener(j2);
		quit.addActionListener(quitListen);
		newGame.addActionListener(newGameListen);

		// tell the frame to pack in all the components
		frame.pack();
		frame.setVisible(true);
		
		//Notify Observers
		jp.notifyObservers();
		jp.getJugs()[0].notifyObservers();
		jp.getJugs()[1].notifyObservers();
		jp.getJugs()[2].notifyObservers();
		
	}
	}

