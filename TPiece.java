
/**
* This is the class for the T shaped tetris piece 
* 
*/

public class TPiece extends TetrisPiece{
	
	public TPiece(){
		TetrisPiece.filledSquares = new boolean[][][]{{
		{true, true, true, false}, 
		{false, true, false, false}, 
		{false, false, false, false}, 
		{false, false, false, false}}, 
		
		{{false, true, false, false}, 
		{true, true, false, false}, 
		{false, true, false, false}, 
		{false, false, false, false}},
		
		{{false, true, false, false}, 
		{true, true, true, false}, 
		{false, false, false, false}, 
		{false, false, false, false }},
			
			
		{{true, false, false, false}, 
		{true, true, false, false}, 
		{true, false, false, false}, 
		{false, false, false, false}}}; 

	}
	/**
	 * Overrides the method from parent class. Returns 
	 * number of false blocks on the piece's grid on the right side
	 * depending on it's rotation
	 * @param rot rotation of piece 
	 */
	public int getRowWhiteSpace(int rot){	
		if (rot == 0 || rot == 2){
			rowWhiteSpace= 1;		
		}else if(rot == 1  || rot == 3){
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
			colWhiteSpace = 2;
		}else if(rot == 1 || rot == 3){
			colWhiteSpace = 1;
		}
		return colWhiteSpace;
	}
}