package TTTPD;

public class Game {
	
	//i just did player 1 and 2 because there are usually 2 players in TTT
	Player player1;
	Player player2;
	Board currentBoard;
	Player currentPlayer;
	Player winner;
	
	
	
	
	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}


	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	

	public Board getCurrentBoard() {
		return currentBoard;
	}





	public void setCurrentBoard(Board currentBoardPosition) {
		this.currentBoard = currentBoardPosition;
	}





	public Player getCurrentPlayer() {
		return currentPlayer;
	}





	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
	
	public void startGame(Player player1, Player player2) {
		
		// this is how we start the game using actual human players, we may (i dont think so) have to 
		// add code to set this up if the player plays against a computer
		
		
		//set up a new board
			currentBoard = new Board();
		
			//Set up player 1 to be Xs
			setPlayer1(player1);
			player1.setMark(1);
			
			//Set up player 2 to be Os
			setPlayer2(player2);
			player2.setMark(-1);
			
			//Set the current player to be player 1, later in this program I would like to randomize the starting player for fairness
			setCurrentPlayer(player1);
			
			//set the current games to this
			player1.setCurrentGame(this);
			player2.setCurrentGame(this);
			
		
	}
	
	public boolean isComplete() {
		
		
		return currentBoard.isComplete();
		
	}
	
	public boolean move(int x, int y) {
		
		if(currentBoard.isPosOpen(x, y))
		{
			currentBoard.updateBoard(x, y, currentPlayer);
			switchPlayer();
			
			return true;
			
		}
		
		else if(x < 0 || x > 2 || y < 0 || y > 2) 
		{
			return false;
		
		}
		else 
		{
			return false;
			
		}
		
		
	}

	private void switchPlayer() {
	
		//check to see if the current player is player 1 (Player 1 will always be the Xs, for now)
		if (currentPlayer.getMark() == 1)
		{
			currentPlayer = player2;
		}
		else 
		{
			currentPlayer = player1;
		}
		
	}

}
