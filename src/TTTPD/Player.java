package TTTPD;

import java.util.Scanner;

public class Player {

	public String name;
	public int wins;
	public int losses;
	//public GameTree tree;		//save this for the computer part, i dont think it needs to be a private variable
	public String Type;
	public Game currentGame;
	public int mark; 
	
	
	
	public int getMark() {
		return mark;
	}


	public void setMark(int mark) {
		this.mark = mark;
	}


	public Game getCurrentGame() {
		return currentGame;
	}


	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}


	public Player() {
		
		this.name = "";
		this.wins = 0;
		this.losses = 0;
		this.Type = "";
		
		
	}
	
	
	public Player(String name, String type) {
		
		this.name = name;
		this.wins = 0;
		this.losses = 0;
		this.Type = type;
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	
	public void winsGame(){
		
		//sets the winner of the game to this class
		currentGame.setWinner(this);
		wins = wins + 1;
	}
	
	public void losesGame() {
		
		losses = losses + 1;
	}
	



	public void move() {
		
		//if human, get keyboard input
		if(this.Type.equals("Human")) {
			
			boolean moveOK = false;
			 
			 while(!moveOK)
			{
				 
			 
				 System.out.println(name + ", Please enter a space (Row,Column)");
				 Scanner scan = new Scanner(System.in);
				 
				 String position = scan.next(); 
				 String parts[] = position.split(","); 
				 
				 int x = Integer.parseInt(parts[0]); 
				 int y = Integer.parseInt(parts[1]); 
			
			
			 	moveOK = this.getCurrentGame().move(x-1, y-1);
				 if(!moveOK)
				 {
					 System.out.println("ERROR: invalid response, try again");
					 
					 
				 }
			 
			}
			
			
		}
		else {
			//if computer, do something else
			//i think that we will have to call the evaluate function in board to see where to move
			
			
			//i think you need to evaluate the board and then call the game function to move
			
			//this creates the Tree and calls the create tree function in the GameTree code
			
			GameTree currentTree = new GameTree();
			currentTree.createTree(this.getCurrentGame().getCurrentBoard(), this.getMark());
			
			//evaluate the tree
			currentTree.evaluateTree();
			
			//this code finds the next move
			int x = currentTree.getNextMoveX(); 
			int y = currentTree.getNextMoveY(); 
			
			System.out.println("Computer Move " + (x+1) + "," + (y+1));
			boolean moveOK = this.getCurrentGame().move(x, y);
			 if(!moveOK)
			 {
				 System.out.println("ERROR: invalid response, try again");
				 
				 
			 }
			
			
			
			
			
			
			//ALGORITHM
			//things he needs to do
			//computer wants to build a game tree, starting with the current gameboard
			//then, evauate the board code
			//then, we call move for the game code
			//player needs a method to build the game tree
	
			
			//TREE
			//build a gametree class that has a head node and children
			//every node has a list of children
			//take my currents nodes board, and if a position is empty, create a child node and tell it to make its children
			//ask if the game is done 
			//add a deep clone for the Board class
			
			//find the next move
			//keep track of the moves in the node
			
			
		
		}
		 
		 
		
		
		 
		
	}


	public void printStats() {
	 System.out.println("Stats for " + getName() + ": \n" + getWins() + " Wins \n" + getLosses() +" losses");
		
	}
	
}
