/**
 * Calls the TetrisGameGuiController and starts the game.
 * 
 * Tried Bonus Challenges:
 * give a preview of the next piece
 * indicate when the level has been lost -- when a block is above the top of the board
 * for the GUI version, have each type of piece displayed with a different color
 * have different levels, where the speed of pieces falling increases as the level increases
 * allow the user to restart a level
 * 
 * @author Tseki Lhamo
 * date: 21 March 2017
 */

public class TetrisGameGUIApplication{
	public static void main(String[] args){
		new TetrisGameGUIController();
	}
}