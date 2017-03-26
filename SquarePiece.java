

/**
* This is the class for the Square shaped tetris piece that extends TetrisPiece 
*/



public class SquarePiece extends TetrisPiece{
	
	public SquarePiece(){
		TetrisPiece.filledSquares = new boolean[][][]{{
			{true, true, false, false}, 
			{true, true, false, false}, 
			{false, false, false, false}, 
			{false, false, false, false}}, 
			
			{{true, true, false, false}, 
			{true, true, false, false}, 
			{false, false, false, false}, 
			{false, false, false, false}},
			
			{{true, true, false, false}, 
			{true, true, false, false}, 
			{false, false, false, false}, 
			{false, false, false, false }},
				
				
			{{true, true, false, false}, 
			{true, true, false, false}, 
			{false, false, false, false}, 
			{false, false, false, false}}}; 
	}

	/**
	 * Overrides the method from parent class. Returns 
	 * number of false blocks on the piece's grid on the right side
	 * depending on it's rotation
	 * @param rot rotation of piece 
	 */

	public int getRowWhiteSpace(int rot){
		
		return rowWhiteSpace = 2;
		
	}

	/**	
	 * Overrides the method from parent class. Same idea
	 *	with getRowWhiteSpace but evaluates the column
	 */	
	public int getColWhiteSpace(int rot){
		
		return colWhiteSpace = 2;
		
	}
}