package TTTPD;

public class Board {
	
	
	
	
	//this board is a 2D array of strings, with its only contents being [ ], [X], or [O]
	public int[][] board;
	
	
	
	//Score?
	
	//Move?
	
	public Board(){
		
		//initialize the board
		board = new int[3][3];
	
		for(int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				board[i][j] = 0;
			}
		}
	}
	
	//determine the winner
	//is board full or is there a winner
	//the board will be full when there are no 0's
	//X will win if one of the rows add up to 3
	//O will win if one of the rows add up to -3
	public boolean isComplete(){
		
		//check the board for X win
		if(board[0][0] + board[0][1] + board [0][2] == 3 || 
			board[1][0] + board[1][1] + board [1][2] == 3 ||
			board[2][0] + board[2][1] + board [2][2] == 3 ||
			board[0][0] + board[1][0] + board [2][0] == 3 ||
			board[0][1] + board[1][1] + board [2][1] == 3 ||
			board[0][2] + board[1][2] + board [2][2] == 3 ||
			board[0][0] + board[1][1] + board [2][2] == 3 ||
			board[0][2] + board[1][1] + board [2][0] == 3) 
		{
		
			return true;
			
		}
		
		//checks the board for a O win
		else if(board[0][0] + board[0][1] + board [0][2] == -3 || 
				board[1][0] + board[1][1] + board [1][2] == -3 ||
				board[2][0] + board[2][1] + board [2][2] == -3 ||
				board[0][0] + board[1][0] + board [2][0] == -3 ||
				board[0][1] + board[1][1] + board [2][1] == -3 ||
				board[0][2] + board[1][2] + board [2][2] == -3 ||
				board[0][0] + board[1][1] + board [2][2] == -3 ||
				board[0][2] + board[1][1] + board [2][0] == -3) 
			{
			
			return true;
			
			}
		
		else if (isFull())
		{
			
			return true;
		}
		else {
			return false;
		}
		
		
			
		
		
		
		
		
	}
	
	//i am not sure if we need this function. will report back later
	//returns null or one of the players
	public int whoIsWinner() {
		
		int eval = 0; 
		
		//check the board for X win
		if(board[0][0] + board[0][1] + board [0][2] == 3 || 
			board[1][0] + board[1][1] + board [1][2] == 3 ||
			board[2][0] + board[2][1] + board [2][2] == 3 ||
			board[0][0] + board[1][0] + board [2][0] == 3 ||
			board[0][1] + board[1][1] + board [2][1] == 3 ||
			board[0][2] + board[1][2] + board [2][2] == 3 ||
			board[0][0] + board[1][1] + board [2][2] == 3 ||
			board[0][2] + board[1][1] + board [2][0] == 3) 
		{
			
			eval = 1;
			
			
		}
		
		//checks the board for a O win
		else if(board[0][0] + board[0][1] + board [0][2] == -3 || 
				board[1][0] + board[1][1] + board [1][2] == -3 ||
				board[2][0] + board[2][1] + board [2][2] == -3 ||
				board[0][0] + board[1][0] + board [2][0] == -3 ||
				board[0][1] + board[1][1] + board [2][1] == -3 ||
				board[0][2] + board[1][2] + board [2][2] == -3 ||
				board[0][0] + board[1][1] + board [2][2] == -3 ||
				board[0][2] + board[1][1] + board [2][0] == -3) 
			{
			
			eval = -1;
			
			}
		
		else if (isFull())
		{
			
			eval = 0;
			
			
		}
		else
		{
			System.out.println("Fatal Error in the WhoIsWInner() Function");
		}
		
		return eval;
		
		
		
	
		
		
	}
	
	public boolean isFull() {
		
		//im going to scan the entire 2d array, if there are no zeros, then i want to return true, meaning there is no more space and it is a tie.
		for(int i = 0; i < 3; i++)
		{
			
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] == 0)
				{
					return false; //will return false because then there is an empty space
				}
				
					
			}
			
		}
		
		return true; //this means there are no more spaces
		
	}
	
	public boolean isPosOpen(int x, int y) {
		
		//bounds checking
		if(x >= 0 && x <= 2 && y >= 0 && y <= 2)
		{
			if (board[x][y] == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else 
		{
			return false;
		}
		
	
	
}
		
	
	//this, apparently, is the tricky part of the code
	//This is the computer part
	public void evaluate() {
		
		
	}
	
	public void updateBoard(int x, int y, Player player) {
		
		board[x][y] = player.getMark();
	}
	
	public void printBoard()
	{
		for(int i = 0; i < 3; i++)
		{
			
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] == -1)
				{
					System.out.print("[O]");
				}
				else if (board[i][j] == 0)
				{
					System.out.print("[ ]");
				}
				else 
				{
					System.out.print("[X]");
				}
					
			}
			System.out.print("\n");
		}
	}
	
	public Board clone(){
		
		Board boardCopy = new Board();
		int[][] board2 = new int[3][3];
		
		
		for(int i = 0; i < 3; i++)
		{
			
			for (int j = 0; j < 3; j++)
			{
				board2[i][j] = this.getBoard()[i][j]; //copy whatever is in board into the boardCopy
				
					
			}
			
		}
		
		boardCopy.setBoard(board2);
		
		return boardCopy;
		
		
		
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	
	

}
