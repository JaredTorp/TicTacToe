package TTTPD;

import java.util.Scanner; // import the Scanner class 

//this is the "Driver" program
public class Test {
	
	
	public static void main (String args[]) 
	{
		
		//prepare the game
		String name;
		String type;
		
		
		 Scanner scan = new Scanner(System.in);
		 
		 
		
		
		 System.out.println("Hello player 1 (X), Please enter your name: ");
		 name = scan.nextLine();
		 
		 Player player1 = new Player(name, "Human");
		 Player player2 = new Player(); // this player will be set up on the code below
		 
		 //implent this later this is the computer part of the option
		 
		 
		 boolean validOption = false; //set up a boolean function to test the input
		 
		 while (validOption == false) {
			
			 System.out.println("Play against computer or a human? (C/H)" );
			 type = scan.nextLine();
			 
			 if (type.equals("computer") || type.equals("Computer") || type.equals("C") || type.equals("c"))
			 {
				 //set up the computer
				 player2 = new Player("b0t", "Computer");
				 validOption = true; //flip the bool
			 }
			 else if (type.equals("human") || type.equals("Human") || type.equals("H") || type.equals("h")) {
				 
				 //set up the human player
				 System.out.println("Hello player 2 (O), Please enter your name: ");
				 name = scan.nextLine();
				 player2 = new Player(name, "Human");
				 validOption = true; //flip the bool
			 }
			 else
			 {
				 //invalid entry, try again
				 System.out.println("Entry invalid ");
			 }
			 
			 
		 }
		 
		 
		 //prepare the game
		 Game game = new Game(); 
		
		 
		 boolean playAgain = true; //set a boolean to play multiple games
		 int count = 1; //set the number of times we go through the loop
		 
		 //while play again is not false
		 while (playAgain != false)
			 
		 {
			 	//Start the game
			 	game.startGame(player1, player2);
			 	System.out.println(); //add a space
			 	System.out.println("Game #" + count + ": Start!");
			 	
				//while the game is not complete
				 while(!game.isComplete())
				 {
					 game.getCurrentBoard().printBoard(); //print the board
					 game.getCurrentPlayer().move(); //let the user move
					 
				 }
			 
				 //this is a stupid function but here we go haha
				 if (game.getCurrentBoard().whoIsWinner() == 1) {
					 
					 game.getPlayer1().winsGame();
					 game.getPlayer2().losesGame();
				 }
				 
				 else if (game.getCurrentBoard().whoIsWinner() == -1) {
					 game.getPlayer2().winsGame();
					 game.getPlayer1().losesGame();;
					 
				 }
				 
				 
				 //print the final board
			 game.getCurrentBoard().printBoard();
			 
			 if (game.getWinner() == null)
			 {
				 System.out.println("It's a tie!");
			 }
			 else
			 {
				 System.out.println(game.getWinner().getName() + " wins!");
			 }
			 
			
			 
			 
			 //now do a validation to play again or not
			 System.out.println("Play again? (Y/N)");
			
			 String option = scan.next(); 
			 
			 if (option.equals("y") || option.equals("Y") || option.equals("yes")) {
				 
				 count++; //increment count 
				
				 
			 }
			 else {
				 playAgain = false;
			 }
			 
		 }
		 
		 //after this, print out the wins and losses of both player 1 and player 2
		 System.out.println();
		 System.out.println("You played " + count + " games!");
		 System.out.println("Here are the Stats of both players: ");
		 player1.printStats();
		 player2.printStats();
		
		
		
	}

}
