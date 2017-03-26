import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays GUI components and controls keylisteners for Tetris. Also sets up
 * the timer for the movement of Tetris pieces
 * 
 * Author: Tseki Lhamo Date: 9th March 2016
 */
public class TetrisGameGUIController extends JPanel implements KeyListener, ActionListener {
	private static int DEFAULT_DROP_RATE = 1000;
	private TetrisGame game;
	private TetrisBoardGUIView view;
	private JLabel linesLabel;
	private JLabel tetrisesLabel;
	private JLabel instructions;
	private JPanel northPanel;
	private JPanel eastPanel;
	private JPanel tetrisPanel;
	private JFrame tetrisFrame;
	private JComboBox difficulty; 
	private Timer gameTimer;
	private JButton newGameButton;

	public TetrisGameGUIController() {
		super(new BorderLayout());
		tetrisFrame = new JFrame("TETRIS");
		tetrisFrame.setSize(500, 800);
		tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tetrisFrame.setLayout(new BorderLayout());
		game = new TetrisGame();
		northPanel = new JPanel();
		eastPanel = new JPanel();
		tetrisPanel = new JPanel();
		instructions = new JLabel(
				"Please use the arrow keys to move left, right and down and use the x and z keys to rotate.");
		linesLabel = new JLabel();
		tetrisesLabel = new JLabel();
		newGameButton = new JButton("New Game");
		 newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 newGameButton.addActionListener(this);
		// boxlayout structures the labels vertically
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		northPanel.add(instructions, BorderLayout.NORTH);
		northPanel.add(linesLabel, BorderLayout.NORTH);
		northPanel.add(tetrisesLabel, BorderLayout.NORTH);
		northPanel.add(newGameButton, BorderLayout.NORTH);
		
		//adding items or levels in the combobox
		difficulty = new JComboBox();  
        difficulty.addItem("Easy"); 
        difficulty.addItem("Medium");
        difficulty.addItem("Hard");
        northPanel.add(difficulty);
        difficulty.addActionListener(this);
		//adding panels to tetrisFrame
		tetrisFrame.add(northPanel, BorderLayout.NORTH);
		tetrisFrame.add(tetrisPanel, BorderLayout.WEST);

		tetrisFrame.setVisible(true);
		tetrisFrame.setFocusable(true);
		tetrisFrame.addKeyListener(this);
		createView();
		createScore();
		setUpTimer();
	}

	// Timer for the movement of the tetris pieces
	public void setUpTimer() {
		gameTimer = new Timer(DEFAULT_DROP_RATE, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.attemptMove(3);
				view.repaint();
				isGameOver();
			}
		});
		gameTimer.start();
	}

	// Displays the tetris board
	public void createView() {
		view = new TetrisBoardGUIView(game.getTetrisBoard());
		view.setPreferredSize(new Dimension(getFrameSize()));
		tetrisPanel.add(view, BorderLayout.CENTER);
	}

	// Displays the update score
	public void createScore() {
		linesLabel = new JLabel("Lines cleared: "
				+ game.getTetrisBoard().getLinesCleared());
		tetrisesLabel = new JLabel("Tetrises cleared: "
				+ game.getTetrisBoard().getTetrisCleared());
		northPanel.add(linesLabel);
		northPanel.add(tetrisesLabel);
	}
	
	//Communicates that the game is over by displaying gameover
	//stops the pieces from falling 
	public void isGameOver(){		
		if (game.getTetrisBoard().gameOver()){
			JLabel gameOver = new JLabel("Game Over");
			eastPanel.add(gameOver);
			tetrisFrame.add(eastPanel);
			gameTimer.stop();
		}
	}
	
	
	// Refreshes display each time a key is pressed r lines are cleared
	public void refreshDisplay() {
		view.repaint();
		linesLabel.setText("Lines cleared: "
				+ game.getTetrisBoard().getLinesCleared());
		tetrisesLabel.setText("Tetrises cleared: "
				+ game.getTetrisBoard().getTetrisCleared());

	}

	// Controls the movements of tetris pieces
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_DOWN) {
			game.attemptMove(3);
			refreshDisplay();
		} else if (key == KeyEvent.VK_RIGHT) {
			game.attemptMove(2);
			refreshDisplay();
		} else if (key == KeyEvent.VK_LEFT) {
			game.attemptMove(1);
			refreshDisplay();
		} else if (key == KeyEvent.VK_X) {
			game.attemptMove(5);
			refreshDisplay();
		} else if (key == KeyEvent.VK_Z) {
			game.attemptMove(4);
			refreshDisplay();
		}
		
	}

	// Returns the Dimension of frame
	public Dimension getFrameSize() {
		Dimension width = tetrisFrame.getContentPane().getSize();
		return width;
	}

	//Controls the different levels for the game by changing the drop rate
	//Also controls the new game button which starts the game again
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == difficulty ){
			if(difficulty.getSelectedItem() == "Easy"){
				
				DEFAULT_DROP_RATE = 1000;
				setUpTimer();		
			}if(difficulty.getSelectedItem() == "Medium"){
				
				DEFAULT_DROP_RATE = 800;
				setUpTimer();
			}if(difficulty.getSelectedItem() == "Hard"){
				
				DEFAULT_DROP_RATE = 400;
				setUpTimer();		
			}
	}	//Starting the game again when button is pressed
		else if(e.getSource() == newGameButton){
            new TetrisGameGUIController();
        }
	
}
	
	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}
}