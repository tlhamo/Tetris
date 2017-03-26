import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;

//author: Tseki Lhamo
//date: 21 March 2017

public abstract class TetrisPiece {

	public static boolean[][][] filledSquares;
	private int pieceRotation = 0;

	public static int rowWhiteSpace;
	public static int colWhiteSpace;

	public TetrisPiece() {

	}

	/**
	 * Rotates the piece clock wise
	 * 
	 */
	public void rotateCW() {
		if (pieceRotation == 3) {
			pieceRotation = 0;
		} else {
			pieceRotation++;
		}

	}

	/**
	 * Rotates the piece counter clock wise
	 */
	public void rotateCCW() {
		if (pieceRotation == 0) {
			pieceRotation = 3;
		} else {
			pieceRotation--;
		}

	}
	/**
	 * 
	 * @return piece's rotation value
	 */
	public int getPieceRotation() {

		return pieceRotation;
	}
	
	/**
	 * 
	 * @param rot rotation value of piece 
	 * @return  the white space depending on the piece
	 */
	public int getRowWhiteSpace(int rot) {

		return rowWhiteSpace;
	}
	/**
	 * 
	 * @param rot rotation value of piece 
	 * @return the white space depending on the piece
	 */
	public int getColWhiteSpace(int rot) {
		return colWhiteSpace;
	}

	/**
	 * @return whether a specific block in the piece's grid is true
	 * @param rot  rotation value
	 * @param row  row value of piece
	 * @param col  column value of piece
	 */
	public boolean isFilled(int rot, int row, int col) {

		return filledSquares[rot][row][col];

	}
}