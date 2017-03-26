
/**
 * Handles the appearance of the game 
 * @author Tseki Lhamo
 * Date: 9th March 2016
 */
public class TetrisBoardTextView{

	private TetrisBoard board; 
	public String tempString; 
	/**
	 * Constructor for this class
	 * @param b is the board passed from the textController
	 */
	public TetrisBoardTextView(TetrisBoard b){
		board = b;
	}
	
	/**
	 * prints the Xs to represent blocks that are true in the blockMatrix and a piece array
	 * @return x or black space depending on true and false blocks
	 */
	public String getBoardString(){
		// Prompt the user for input
		System.out.println( "Please enter a move (l,r,d,z,x) or type Quit to end.\n"); 
		System.out.println("Number of lines cleared:" + (board.getLinesCleared()));
		System.out.println("Number of Tetrises cleared:" + (board.getTetrisCleared()));
	 tempString="==========\n";
	for (int i=0; i< board.NUM_ROWS; i++){	
		for (int j=0; j< board.NUM_COLS; j++){
			//if true, print X
			if (board.blockMatrix[i][j]){
				tempString+="X";
			} else if(board.blockMatrix[i][j] == false){
					//checks through current pieces grid for a true value and will print an X, else print a space 
					if(i <= board.getCurrentPieceGridPosition()[0] + 3 && i >= board.getCurrentPieceGridPosition()[0]){					
						if(j <= board.getCurrentPieceGridPosition()[1]+3 && j >= board.getCurrentPieceGridPosition()[1]){						
							if(board != null){
								if (board.getCurrentPiece().isFilled(board.getCurrentPiece().getPieceRotation(), i-board.getCurrentPieceGridPosition()[0], j-board.getCurrentPieceGridPosition()[1])){						
								tempString+="X";
							}else{
								tempString+=" "; 
								}
							}
					}else{
						tempString+=" "; 
					}
			}else{
				tempString+=" "; 					
				}
					
			}
		}	tempString+="\n";
	}
		tempString+="==========";return tempString;
	}

}


