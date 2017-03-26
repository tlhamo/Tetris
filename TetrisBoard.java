
import java.awt.Color;
import java.awt.Component;
/**
* This is where the board for the game is created. 
* It is the memory class as it remembers a piece's existance.
* This class contains all the methods
* to manipulate the board to add pieces and check if a movement 
* is valid etc. 
* 
* author: Tseki Lhamo
* date: March 9th 2017
*/
import java.lang.Math;

public class TetrisBoard {
	// Board size
	public static final int NUM_ROWS = 18;
	public static final int NUM_COLS = 10;
	// size of a piece's board
	public static final int numCols = 4;
	public static final int numRows = 4;
	// matrix that will handle the true and false values
	public boolean[][] blockMatrix = new boolean[NUM_ROWS][NUM_COLS];
	public boolean[][] previewMatrix = new boolean[numRows][numCols];
	private TetrisPiece currentPiece;
	private TetrisPiece previewPiece;
	private TetrisPiece nextPiece;
	// stores the coordinates of the first piece that appears
	private int[] currentPieceGridPosition = new int[2];
	private int[] previewGridPosition = new int[2];
	private int linesCleared = 0;
	private int tetrisCleared = 0;
	private int counter= 0;
	private Color randomColor;
	private boolean gameOverCheck = false;
	
	//Initializes board and add's new piece 
	public TetrisBoard() {
		initBoard();
		initPreviewBoard();
		addNewPiece();	
		randomColor(); 
		
	}

	// Initializes the grid position of where the tetris piece should start
	public void initCurrentGP() {
		currentPieceGridPosition[0] = 0;
		currentPieceGridPosition[1] = 3;
	}
	
	// Initializes the board for the start of the game all the spaces will be
	// false
	public void initBoard() {
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				blockMatrix[i][j] = false;
			}
		}
	}
	
	public void initPreviewBoard(){
		for (int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				previewMatrix[i][j] = false;
			}
		}
	}

	// Adds a new piece using a random number
	public void addNewPiece() {
		initCurrentGP();
		randomColor();
		addPreviewPiece();
		int max = 7;
		int min = 1;
		
		
		
		// generating a random number to randomize piece selection
		

		if(counter ==0){
			int randPiece = (int) (Math.random() * max + min);
			System.out.println(randPiece);
		if (randPiece == 1) {
			currentPiece = new LPiece();
			
		} else if (randPiece == 2) {
			currentPiece = new ReversedLPiece();
			
		} else if (randPiece == 3) {
			currentPiece = new SquarePiece();
			
		} else if (randPiece == 4) {
			currentPiece = new TPiece();
			
		} else if (randPiece == 5) {
			currentPiece = new ZPiece();
			
		} else if (randPiece == 6) {
			currentPiece = new ReversedZPiece();
			
		} else {
			currentPiece = new StickPiece();
			
		}counter = 1;
	}
		else{
			
			currentPiece = getPreviewPiece();
		}
	}
	
	//Adds preview piece which will be set to the current Piece
	public void addPreviewPiece(){
		int max = 7;
		int min = 1;
		int randPiece = (int) (Math.random() * max + min);
		System.out.println(randPiece);
		if (randPiece == 1) {
			previewPiece = new LPiece();
			
		} else if (randPiece == 2) {
			previewPiece = new ReversedLPiece();
			
		} else if (randPiece == 3) {
			previewPiece = new SquarePiece();
			
		} else if (randPiece == 4) {
			previewPiece = new TPiece();
			
		} else if (randPiece == 5) {
			previewPiece = new ZPiece();
			
		} else if (randPiece == 6) {
			previewPiece = new ReversedZPiece();
		
		} else {
			previewPiece = new StickPiece();	
		}
		
	}
	
	public TetrisPiece getPreviewPiece(){
		return previewPiece;
	}

	// landPiece checks through the piece's grid for true values whose
	// coordinates are then set to the block matrix
	public void landPiece() {
		int linesRemoved = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (currentPiece.isFilled(currentPiece.getPieceRotation(), i, j)) {
					// making the block matrix true at points corresponding with
					// the tetris piece's position
					blockMatrix[getCurrentPieceGridPosition()[0] + i][getCurrentPieceGridPosition()[1] + j] = true;

					for (int row = 0; row < NUM_ROWS; row++) {
						if (fullLine(row)) {
							removeLine(row);
							linesRemoved++;
							if (linesRemoved == 4) {
								tetrisCleared++;
							}
						}
					}
				}
			}
		}
		
		addNewPiece();
	}

	public boolean canMoveRight() {
		// if the current piece hasn't touched the right boundary left move
		// right happen
		if (validMove(currentPiece, currentPiece.getPieceRotation(), getCurrentPieceGridPosition()[0],
				getCurrentPieceGridPosition()[1] + 1)) {
			currentPieceGridPosition[1] = currentPieceGridPosition[1] + 1;
		}
		return false;
	}

	// checks to see if moving left is possible
	public boolean canMoveLeft() {
		if (validMove(currentPiece, currentPiece.getPieceRotation(), getCurrentPieceGridPosition()[0],
				getCurrentPieceGridPosition()[1] - 1)) {
			currentPieceGridPosition[1] = currentPieceGridPosition[1] - 1;
		}
		return false;
	}

	// method to move down, if landPiece is called, the moveDown will be false
	// and the add new piece will be called again through TetrisGame
	public boolean canMoveDown() {
		if (validMove(currentPiece, currentPiece.getPieceRotation(), getCurrentPieceGridPosition()[0] + 1,
				getCurrentPieceGridPosition()[1])) {
			currentPieceGridPosition[0] = currentPieceGridPosition[0] + 1;
			return true;
		} else {
			landPiece();
			return false;
		}
	}

	// Checks if a move is valid
	public boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		boolean check = true;
		// if detect out of bounds is true and collision is true, valid move is
		// false
		if (detectOutOfBounds(piece, rot, gridRow, gridCol) || detectCollision(piece, rot, gridRow, gridCol)) {
			check = false;
		}
		return check;
	}

	// Checks if a piece has collided with another piece
	public boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		boolean check = false;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (piece.isFilled(rot, i, j) && blockMatrix[gridRow + i][gridCol + j]) {
					check = true;
				}
			}
		}
		return check;
	}

	// Checks the bounds to prevent the piece from falling off the edge
	public boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		boolean check = false;
		//gives the right side boundary depending on piece's white spaces 
		int rightBound = NUM_COLS - numCols + piece.getRowWhiteSpace(piece.getPieceRotation());
		int rowBound = NUM_ROWS - numRows + piece.getColWhiteSpace(piece.getPieceRotation());
		if (gridCol < 0 || gridCol > rightBound || gridRow > rowBound) {
			check = true;
		}
		return check;
	}

	// Rotates the piece clockWise
	public boolean canRotateCW() {
		int rotationBoundary = getCurrentPieceGridPosition()[1]
				+ (currentPiece.getRowWhiteSpace(currentPiece.getPieceRotation()));
		if (rotationBoundary < NUM_COLS) {
			currentPiece.rotateCW();
		}
		return true;
	}

	// Rotates the piece counter clock wise
	public boolean canRotateCCW() {
		int rotationBoundary = getCurrentPieceGridPosition()[1]
				+ (currentPiece.getRowWhiteSpace(currentPiece.getPieceRotation()));
		if (rotationBoundary < NUM_COLS) {
			currentPiece.rotateCCW();
		}
		return true;
	}

	// Checks to see if an entire row is true
	// If all the blocks in the row are true lines cleared will be incremented
	public boolean fullLine(int row) {
		// Stores the number of blocks that are true
		int trueBlocks = 0;
		for (int i = 0; i < NUM_COLS; i++) {
			if (blockMatrix[row][i]) {
				trueBlocks = trueBlocks + 1;
				if (trueBlocks == 10) {
					linesCleared++;
					return true;
				}
			}
		}
		return false;
	}

	// Removes row when the entire row is true
	// and shifts the true blocks above that row down.
	public void removeLine(int row) {
		for (int j = 0; j < NUM_COLS; j++) {
			blockMatrix[row][j] = false;

		}
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < NUM_COLS; j++) {
				blockMatrix[i][j] = blockMatrix[i - 1][j];
			}
		}
	}

	// Returns the number of line cleared
	public int getLinesCleared() {
		return linesCleared;
	}

	// Returns the number of tetrises cleared
	public int getTetrisCleared() {
		return tetrisCleared;
	}

	// checks which places on the board that are blocked and returns those
	// boolean values
	public boolean[][] getBlockMatrix() {
		return blockMatrix;
	}

	// Returns the current piece that gets manipulated
	public TetrisPiece getCurrentPiece() {
		return currentPiece;
	}

	// Returns the position of the current piece
	public int[] getCurrentPieceGridPosition() {
		return currentPieceGridPosition;
	}
	
	//Creates random color for tetris pieces
	public void randomColor(){
		int max = 255;
		int min = 0;
		int randR = (int) (Math.random() * max + min);
		int randG = (int) (Math.random() * max + min);
		int randB = (int) (Math.random() * max + min);
		randomColor = new Color(randR, randG, randB);		
	}
	
	public Color getRandomColor(){
		return randomColor;
	}
	
	//checks if a piece is landed outside of the top of the grid
	public boolean gameOver(){
		for(int j = 0; j < NUM_COLS; j++){
		if(blockMatrix[2][j]){
			gameOverCheck = true;
			return gameOverCheck;
			}
		}return gameOverCheck;
	}
	
	public int getNumCols() {
		return numCols;
	}

	public int getNumRows() {
		return numRows;
	}

}