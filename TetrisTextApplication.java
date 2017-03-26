//TetrisController implements ActionListener for movement of pieces falling WRONG???
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.lang.IllegalArgumentException;

public class TetrisTextApplication {

	private TetrisGame game;
	private TetrisBoardTextView view;


	//Constructor contains calls the readInput method to begin game
	public TetrisTextApplication(){	
		game = new TetrisGame();
		view = new TetrisBoardTextView(game.getTetrisBoard());
		System.out.println(view.getBoardString());
		//begin game
		readInput();
	}

	//method for when the user types quit or help
	public void readInput(){
		// Buffered readers are used to read text input from the command line
		BufferedReader in = 
			new BufferedReader( new InputStreamReader( System.in ) );

		
		
		//Used to hold what the user inputs
		String line;

		// Bufferedreaders require a try/catch block, to prevent exceptions from crashing
		// your program
		try 
		{
			// Try to read a line
			// This function potentially throws an IOException
			line = in.readLine(); 
			

			// Loop until the user types "quit"
			while ( !line.toLowerCase().equals( "quit" )  ) {
				try{ //if the user types either l,r,d,z,x
					if(line.equals("l")| line.equals("r") | line.equals("d") | line.equals("z") | line.equals("x")){
						moveEntered(line);
					}
					else{
						System.out.println("Error: Letters must be l,r, d, z or x");
					}
				}
				catch (IllegalArgumentException e){
					System.out.println("Please enter a valid letter.");
				}
			line = in.readLine();
			}
			
	}

catch( IOException ioenfe){
	System.out.println("Error: IOException");
	}
}


	public void refreshDisplay(){
		System.out.println(view.getBoardString());
		
	}

	//called from the readInput method and decides what to do when either keys, l,r,d,z,x are pressed
	//l : left, r : right, d: down, z: rot CCW, x: rot CW
	public void moveEntered(String move){
		if(move.equals("l")){
			game.attemptMove(game.LEFT);
			refreshDisplay();
		} else if(move.equals("r")){
			game.attemptMove(game.RIGHT);
			refreshDisplay();
		} else if(move.equals("d")){
			game.attemptMove(game.DOWN);
			refreshDisplay();
		} else if(move.equals("z")){
			game.attemptMove(game.CW);
			refreshDisplay();
		} else if(move.equals("x")){
			game.attemptMove(game.CCW);
			refreshDisplay();
		}


	}

	public static void main (String[] args){
		new TetrisTextApplication();
		
	}
}
