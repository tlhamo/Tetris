/**
* Controls the calls to moved down, left,right and rotate movements
* Author: Tseki Lhamo
* Date: 9th March 2016
*/


public class TetrisGame{
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int CW = 4;
	public static final int CCW = 5;
	private int numLines;
	private int numTetrises;
	private TetrisBoard tetrisBoard;

	//creates a new board 
	public TetrisGame(){
		tetrisBoard = new TetrisBoard();
	}
	
	//Calls the specific move corresponding to the key that was pressed
	public void attemptMove(int moveType){
		if(moveType == 1){
			tetrisBoard.canMoveLeft();		
		}else if(moveType == 2){
			tetrisBoard.canMoveRight();
		}else if(moveType == 3){
			tetrisBoard.canMoveDown();						
		}else if(moveType == 4){
			tetrisBoard.canRotateCW();			
		}else if(moveType == 5){
		    tetrisBoard.canRotateCCW();			
		}
	}
		

	public TetrisBoard getTetrisBoard(){
		return tetrisBoard;
	}
	
	
}