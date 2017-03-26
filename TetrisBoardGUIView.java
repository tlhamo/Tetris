/**
 * Paints graphics to the screen for tetris pieces and preview pieces
 * 
 * Author: Tseki Lhamo
 * Date: 21th March 2016
 */
//author: Tseki Lhamo
//date: 21 March 2017
import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JComponent;

public class TetrisBoardGUIView extends JComponent {

	public TetrisBoard board;

	public TetrisBoardGUIView(TetrisBoard b) {
		super();
		board = b;
		repaint();
	}

	// painting a yellow block on a particular grid position depnding on true
	// and false values
	public void paint(Graphics g) {
		paintBoardOutline(g, computeBlockSize());
		paintPreview(g);
		for (int i = 0; i < board.NUM_ROWS; i++) {
			for (int j = 0; j < board.NUM_COLS; j++) {
				if (board.blockMatrix[i][j]) {
					paintBlock(g, i, j, computeBlockSize());
				} else if (board.blockMatrix[i][j] == false) {
					if (i <= board.getCurrentPieceGridPosition()[0] + 3
							&& i >= board.getCurrentPieceGridPosition()[0]) {
						if (j <= board.getCurrentPieceGridPosition()[1] + 3
								&& j >= board.getCurrentPieceGridPosition()[1]) {
							if (board != null) {
								if (board
										.getCurrentPiece()
										.isFilled(
												board.getCurrentPiece()
														.getPieceRotation(),
												i
														- board.getCurrentPieceGridPosition()[0],
												j
														- board.getCurrentPieceGridPosition()[1])) {
									paintBlock(g, i, j, computeBlockSize());
								}
							}
						}
					}
				}
			}
		}
	}

	// painting the board outline and the preview box outline
	public void paintBoardOutline(Graphics g, int blockSize) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, blockSize * 10, blockSize * 18);
		g.drawRect(blockSize * 10, 0, blockSize * 4, blockSize * 4);
	}

	// painting the preview piece
	public void paintPreview(Graphics g) {
		for (int i = 0; i < board.numRows; i++) {
			for (int j = 0; j < board.numCols; j++) {
				if (board.previewMatrix[i][j]) {
					paintPreviewBlock(g, i, j, computeBlockSize());
				}
				if (board.getPreviewPiece().isFilled(1, i, j)) {
					paintPreviewBlock(g, i, j, computeBlockSize());
					
				}
			}
		}
	}

	// painting a yellow block for tetris piece
	public void paintBlock(Graphics g, int row, int col, int blockSize) {
		g.setColor(board.getRandomColor());
		g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
		g.setColor(Color.BLACK);
		g.drawRect(col * blockSize, row * blockSize, blockSize, blockSize);
	}

	// painting a yellow block for preview piece
	public void paintPreviewBlock(Graphics g, int row, int col, int blockSize) {
		g.setColor(Color.YELLOW);
		g.fillRect(col * blockSize + blockSize * 10, row * blockSize,
				blockSize, blockSize);
		g.setColor(Color.BLACK);
		g.drawRect(col * blockSize + blockSize * 10, row * blockSize,
				blockSize, blockSize);

	}

	// computing block size for each piece
	public int computeBlockSize() {
		return (getWidth() / board.NUM_ROWS);
	}
	

}