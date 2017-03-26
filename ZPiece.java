
/**
* This is the class for the Z shaped tetris piece that extends TetrisPiece 
*/


public class ZPiece extends TetrisPiece{
	
	public ZPiece(){
		TetrisPiece.filledSquares = new boolean[][][]{{
			{false, true, false, false}, 
			{true, true, false, false}, 
			{true, false, false, false}, 
			{false, false, false, false}}, 
			
			{{true, true, false, false}, 
			{false, true, true, false}, 
			{false, false, false, false}, 
			{false, false, false, false}},
			
			{{false, true, false, false}, 
			{true, true, false, false}, 
			{true, false, false, false}, 
			{false, false, false, false }},
				
				
			{{true, true, false, false}, 
			{false, true, true, false}, 
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
		if (rot == 1 || rot == 3){
			rowWhiteSpace= 1;
			
		}else if(rot == 0  || rot == 2){
			rowWhiteSpace=2;
		}
		return rowWhiteSpace;
		
	}
	
	/**	
	 * Overrides the method from parent class. Same idea
	 *	with getRowWhiteSpace but evaluates the column
	 */	
	public int getColWhiteSpace(int rot){
		if(rot == 0 || rot == 2){
			colWhiteSpace = 1;
		}else if(rot == 1 || rot == 3){
			colWhiteSpace = 2;
		}
		return colWhiteSpace;
	}

}