//Checkers programmed by Sebastian Metellus, Kai Coops, and Brody Miller for Advanced Programming in Java

package Checkers;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Checkers
public class Checkers extends JPanel implements ActionListener, MouseListener { 
	public static int width = 720,
			          height = width;
	
	public static final int iTile = width / 8; // sets dimensions for tiles
	
	public static final int tileCount = width/iTile; // tile count
	
	public static int[][] initGameData = new int[tileCount][tileCount];
	
	public static int[][] consGameData = new int[tileCount][tileCount]; 
	
	public static final int emptyTile = 0, redStandard = 1, redKing = 2, grayStandard = 3, grayKing = 4; // sets checker piece type by numbers
	
	public static JFrame frame;
	
	public boolean ongoingGame = true;
	
	public int player = redStandard;
	
	public boolean ongoingPlay = false;
	
	public static int[][] possiblePlays = new int[tileCount][tileCount]; 
	
	public int enteredRow, enteredColumn;
	
	public boolean kill = false;
	
	static BufferedImage crownMinion = null; // King Piece

	public static void main(String[] args){
		try 
		{
			crownMinion = ImageIO.read(new File("crown.png")); // Sets image for King
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		new Checkers();
	}
	
	// Checkers Constructor
	public Checkers() 
	{
		windowFrame(width, height, this);
		Board();
		repaint(); 
	}
	
	// EndGame Function
	public boolean endGame()
	{ 
		return gameOver(0, 0, 0, 0);
	}
	
	// Sets winning condition for game piece configuration
	public boolean gameOver(int col, int row, int red, int white)
	{
		if(consGameData[col][row] == redStandard || consGameData[col][row] == redKing)
			red += 1;
		
		if(consGameData[col][row] == grayStandard || consGameData[col][row] == grayKing)
			white += 1;
		
		if(col == tileCount-1 && row == tileCount-1){
			if(red == 0 || white == 0)
				return true;
			else return false;
		}
		if(col == tileCount-1){
			row += 1;
			col = -1;
		}
		return gameOver(col+1, row, red, white);
	}
	
	// Sets Frame for Checkers Window
	public void windowFrame(int width, int height, Checkers game)
	{ 
		JFrame frame = new JFrame(); // Frame Object
		
		frame.setSize(width, height);
		
		frame.setIconImage(crownMinion);
		
		frame.setBackground(Color.cyan);
		
		frame.setLocationRelativeTo(null);
		
		frame.pack();
		
		Insets insets = frame.getInsets();
		
		int frameLeftBorder = insets.left;
		
		int frameRightBorder = insets.right;
		
		int frameTopBorder = insets.top;
		
		int frameBottomBorder = insets.bottom;
		
		frame.setPreferredSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
		
		frame.setMaximumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
		
		frame.setMinimumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
		
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addMouseListener(this);
		
		frame.requestFocus();
		
		frame.setVisible(true);
		
		frame.add(game);
	}
	// Sets Columns and Rows for Checkers Board
	public void Board(){

				for(int col=0; col < (tileCount); col+=2)
				{
					consGameData[col][5] = redStandard;
					consGameData[col][7] = redStandard;
				}
				for(int col=1; col < (tileCount); col+=2)
					consGameData[col][6] = redStandard;
				
				for(int col=1; col < (tileCount); col+=2)
				{
					consGameData[col][0] = grayStandard;
					consGameData[col][2] = grayStandard;
				}	
				for(int col=0; col < (tileCount); col+=2)
					consGameData[col][1] = grayStandard;
	}
	
	// Sets graphics properties for Circle
	public static void drawCircle(int col, int row, Graphics g, Color color){
		
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		g.setColor(color);
		
		g.fillOval((col*iTile)+2, (row*iTile)+2, iTile-4, iTile-4); // Fills Circle
	}
	
	public void paint(Graphics g)
	{ 
		super.paintComponent(g); // calls main constructor
		
		for(int row = 0; row < tileCount; row++)
		{
			for(int col = 0; col < tileCount; col++)
			{
				if((row%2 == 0 && col%2 == 0) || (row%2 != 0 && col%2 != 0)) // allows for split board pattern
				{ 
					initGameData[col][row] = 0;
					g.setColor(Color.white);
					g.fillRect(col*iTile, row*iTile, iTile, iTile);
				}
				else
				{
					initGameData[col][row] = 1;
					g.setColor(Color.black);
					g.fillRect(col*iTile, row*iTile, iTile, iTile);
				}
				if(checkTeam(col, row) ==  true)
				{			
					g.setColor(Color.black.darker());
					g.fillRect(col*iTile, row*iTile, iTile, iTile);
				}
				if(possiblePlays[col][row] == 1) // tile selection color
				{
					g.setColor(Color.CYAN.darker());
					g.fillRect(col*iTile, row*iTile, iTile, iTile);
				}
				if(consGameData[col][row] == grayStandard)
					drawCircle(col, row, g, Color.gray);
				else if(consGameData[col][row] == grayKing)
				{
					drawCircle(col, row, g, Color.gray);
					g.drawImage(crownMinion, (col*iTile)+6, (row*iTile)+6, iTile-12, iTile-12, null);
				}
				else if(consGameData[col][row] == redStandard)
					drawCircle(col, row, g, Color.red);
				else if(consGameData[col][row] == redKing)
				{
					drawCircle(col, row, g, Color.red);
				g.drawImage(crownMinion, (col*iTile)+6, (row*iTile)+6, iTile-12, iTile-12, null);
				}
			}
		}
		if(endGame() == true)
			showGameOver(g);
	}	
	
	// Game Over Function
	public void showGameOver(Graphics g) { 
		 
		 String msg = "Game Over";
	     
		 Font small = new Font("Helvetica", Font.BOLD, 20);
	     
		 FontMetrics metr = getFontMetrics(small); /  Sets font
	     
	     g.setColor(Color.white);
	     
	     g.setFont(small);
	    
	     g.drawString(msg, (width - metr.stringWidth(msg)) / 2, width / 2);
	}
	
	// Clears board and pauses ongoingPlay
	public void Reset(){
		
		enteredColumn = 0;
		enteredRow = 0;
		ongoingPlay = false;
		kill = false;
		
		for(int row = 0; row < tileCount; row++)
		{
			for(int col = 0; col < tileCount; col++)
			{
				possiblePlays[col][row] = 0; // Clear Board
			}
		}
		repaint();
	}
	
	// Mouse Function and Registers Click Selection
	public void mousePressed(java.awt.event.MouseEvent evt) {
    	int col = (evt.getX()-8) / iTile; 
        int row = (evt.getY()-30) / iTile; 
		
        if(ongoingPlay == false && consGameData[col][row] != 0 || ongoingPlay == true && checkTeam(col, row) == true)
        {
			Reset();
			enteredColumn = col;
			enteredRow = row; 
			checkForPlays(col, row);
		}
		
        else if(ongoingPlay == true && possiblePlays[col][row] == 1)
		{
			movePiece(col, row, enteredColumn, enteredRow);
		}
		
        else if(ongoingPlay == true && possiblePlays[col][row] == 0)
		{
			Reset();
		}
	}
	
	public void changeTurns() // Sets Player Colors
	{
		if(player == redStandard)
			player = grayStandard;
		else player = redStandard;
	}
	
	public void movePiece(int col, int row, int storedCol, int storedRow)
	{
		int x = consGameData[storedCol][storedRow]; // Stores column and row data
		
		consGameData[col][row] = x;
		consGameData[storedCol][storedRow] = emptyTile; // Checks Empty Tile
		kingInspect(col, row);
		
		if(kill == true) // If space is empty, kill piece
			killedPiece(col, row, storedCol, storedRow);
		Reset();
		changeTurns();
	}
	
	public boolean kingCheck(int col, int row) // Determines King Conditions
	{
		if(consGameData[col][row] == redKing || consGameData[col][row] == grayKing){
			return true;
		}
		else return false;
	}
	
	public int enemyCheck(int col, int row) // Checks Enemy
	{
		if(consGameData[col][row] == redStandard || consGameData[col][row] ==  redKing)
			return grayStandard;
		else
			return redStandard;
	}
	
	public void checkMove(int col, int row) // Checks Opponent Move
	{
		int opponent = enemyCheck(col, row);
		int opponentKing = enemyCheck(col, row) + 1;
		
		if(consGameData[col-1][row-1] == opponent || consGameData[col-1][row-1] == opponentKing){
			possiblePlays[col-1][row-1] = 1;
		}
		else if(consGameData[col+1][row-1] == opponent || consGameData[col+1][row-1] == opponentKing){
			possiblePlays[col+1][row-1] = 1;
		}
		else if(consGameData[col-1][row+1] == opponent || consGameData[col-1][row+1] == opponentKing){
			possiblePlays[col-1][row+1] = 1;
		}
		else if(consGameData[col+1][row+1] == opponent || consGameData[col+1][row+1] == opponentKing){
			possiblePlays[col+1][row+1] = 1;
		}
		repaint();
	}
	
	public void kingInspect(int col, int row) // Turns Piece to King if reaches end of board
	{
		if(consGameData[col][row] == redStandard && row == 0)
			consGameData[col][row] = redKing;
		else if(consGameData[col][row] == grayStandard && row == tileCount-1)
			consGameData[col][row] = grayKing;
		else return;
	}
	
	public void killedPiece(int col, int row, int storedCol, int storedRow)
	{
		int pieceRow = -1; 
		int pieceCol = -1;
		if(col > storedCol && row > storedRow){
			pieceRow = row-1;
			pieceCol = col-1;
		}
		if(col > storedCol && row < storedRow){
			pieceRow = row+1;
			pieceCol = col-1;
		}
		if(col < storedCol && row > storedRow){
			pieceRow = row-1;
			pieceCol = col+1;
		}
		if(col < storedCol && row < storedRow){
			pieceRow = row+1;
			pieceCol = col+1;
		}
		consGameData[pieceCol][pieceRow] = emptyTile;
	}
	
	public void checkForPlays(int col, int row) // Clicker Selector Function (Roaming Clicker)
	{
		ongoingPlay = true;
		
		if((checkTeam(col, row) == true)){ 
			if(consGameData[col][row] == redStandard){ 
				goUp(col, row);
			}
			if(consGameData[col][row] == grayStandard){ 
				goDown(col, row);
			}
			if(consGameData[col][row] == redKing || consGameData[col][row] == grayKing){ 
				goUp(col, row);
				goDown(col, row);
			}
		repaint();
		}
	}
	
	public void goUp(int col, int row)
	{
		int rowUp = row-1; // Moves row up one
		
		if(col == 0 && row != 0){ // Checks loop for columns
			for(int i = col; i < col+2; i++){
				if(consGameData[col][row] != 0 && consGameData[i][rowUp] != 0){
					if(jumpCheck(col, row, i, rowUp) == true){
						int jumpCol = checkJumpPosition(col, row, i, rowUp)[0];
						int jumpRow = checkJumpPosition(col, row, i, rowUp)[1];
						possiblePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(initGameData[i][rowUp] == 1 && consGameData[i][rowUp] == 0)
					possiblePlays[i][rowUp] = 1;
			}
		}
		
		else if(col == (tileCount - 1) && row != 0){ 
				if(consGameData[col][row] != 0 && consGameData[col-1][rowUp] != 0){
					if(jumpCheck(col, row, col-1, rowUp) == true){
						int jumpCol = checkJumpPosition(col, row, col-1, rowUp)[0];
						int jumpRow = checkJumpPosition(col, row, col-1, rowUp)[1];
						possiblePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(initGameData[col-1][rowUp] == 1 && consGameData[col-1][rowUp] == 0)
					possiblePlays[col-1][rowUp] = 1;
		}
		
		else if(col != tileCount - 1 && col != 0 && row != 0){
			for(int i = col-1; i <= col+1; i++){
				if(consGameData[col][row] != 0 && consGameData[i][rowUp] != 0){
					if(jumpCheck(col, row, i, rowUp) == true){
						int jumpCol = checkJumpPosition(col, row, i, rowUp)[0];
						int jumpRow = checkJumpPosition(col, row, i, rowUp)[1];
						possiblePlays[jumpCol][jumpRow] = 1;
					}
				}
				
				else if(initGameData[i][rowUp] == 1 && consGameData[i][rowUp] == 0)
					possiblePlays[i][rowUp] = 1;
			}
		}
	}
	
	public void goDown(int col, int row)
	{
		int rowDown = row+1; // Row down by 1 
		
		if(col == 0 && row != tileCount-1){ // Checks columns and takes one from tileCount
				if(consGameData[col][row] != 0 && consGameData[col+1][rowDown] != 0)
				{
					if(jumpCheck(col, row, col+1, rowDown) == true){
						int jumpCol = checkJumpPosition(col, row, col+1, rowDown)[0];
						int jumpRow = checkJumpPosition(col, row, col+1, rowDown)[1];
						possiblePlays[jumpCol][jumpRow] = 1;
					}
				}
				
				else if(initGameData[col+1][rowDown] == 1 && consGameData[col+1][rowDown] == 0)
					possiblePlays[col+1][rowDown] = 1;
		}
		
		else if(col == tileCount - 1 && row != tileCount-1)
		{
				if(consGameData[col][row] != 0 && consGameData[col-1][rowDown] != 0){
					if(jumpCheck(col, row, col-1, rowDown) == true){
						int jumpCol = checkJumpPosition(col, row, col-1, rowDown)[0];
						int jumpRow = checkJumpPosition(col, row, col-1, rowDown)[1];
						possiblePlays[jumpCol][jumpRow] = 1;
					}
				}
				
				else if(initGameData[col-1][rowDown] == 1 && consGameData[col-1][rowDown] == 0)
					possiblePlays[col-1][rowDown] = 1;
		}
		
		else if(col != tileCount-1 && col != 0 && row != tileCount-1){
			for(int i = col-1; i <= col+1; i++){
				if(consGameData[col][row] != 0 && consGameData[i][rowDown] != 0){
					if(jumpCheck(col, row, i, rowDown) == true){
						int jumpCol = checkJumpPosition(col, row, i, rowDown)[0];
						int jumpRow = checkJumpPosition(col, row, i, rowDown)[1];
						possiblePlays[jumpCol][jumpRow] = 1;
					}
				}
				
				else if(initGameData[i][rowDown] == 1 && consGameData[i][rowDown] == 0)
					possiblePlays[i][rowDown] = 1;
			}
		}
	}
	
	public boolean checkTeam(int col, int row) // Checks Error (Only move on opponent pieces)
	{
		if(player == redStandard && (consGameData[col][row] == redStandard || consGameData[col][row] == redKing)) //bottom
			return true;
		if(player == grayStandard && (consGameData[col][row] == grayStandard || consGameData[col][row] == grayKing)) //top
			return true;
		else
			return false;
	}
	
	public boolean checkPosition(int col, int row) // Checks TileCount
	{
		if(row < 0 || row >= tileCount || col < 0 || col >= tileCount)
			return false;
		else return true;
	}
	
	public boolean jumpCheck(int col, int row, int opponentCol, int opponentRow) // Assigns Piece Value after Jump
	{
		if(((consGameData[col][row] == grayStandard || 
			 consGameData[col][row] == grayKing) && 
			(consGameData[opponentCol][opponentRow] == redStandard || 
			 consGameData[opponentCol][opponentRow] == redKing)) || 
			(consGameData[col][row] == redStandard || 
			 consGameData[col][row] == redKing) && 
			(consGameData[opponentCol][opponentRow] == grayStandard || 
			 consGameData[opponentCol][opponentRow] == grayKing)){ 
			
			if(opponentCol == 0 || opponentCol == tileCount-1 || opponentRow == 0 || opponentRow == tileCount-1)
				return false;
			int[] toData = checkJumpPosition(col, row, opponentCol, opponentRow);
		    if(checkPosition(toData[0],toData[1]) == false) //check board out of bounds
		        return false;
		    
		    if(consGameData[toData[0]][toData[1]] == 0){
		    	kill = true;
		    	return true;
		    }
		}
		return false;
	}
	
	public int[] checkJumpPosition(int col, int row, int opponentCol, int opponentRow) // Returns new Jump Position
	{
		if(col > opponentCol && row > opponentRow && consGameData[col-2][row-2] == 0)
			return new int[] {col-2, row-2};
		else if(col > opponentCol && row < opponentRow && consGameData[col-2][row+2] == 0)
			return new int[] {col-2, row+2};
		else if(col < opponentCol && row > opponentRow && consGameData[col+2][row-2] == 0)
			return new int[] {col+2, row-2};
		else
			return new int[] {col+2, row+2};
	}
	

}