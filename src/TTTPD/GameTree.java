package TTTPD;

import java.util.ArrayList;

public class GameTree<E> {
	
	//Nested Class
	private class GameTreeNode {
		
//Attributes for the GameTreeNode class---------------------------------------------------------------------------------------------------------------------
		private GameTreeNode parent; //every node has a parent
		private ArrayList<GameTreeNode> children; //this is a list of children that each node may or may not have
		private Board board; //each node has a board element
		private int lastMoveX;
		private int lastMoveY; 
		private int lastMark;
		private int eval; //this stores as the "min" or "max" number once we evaluate the current board
		
		
		
		
		
		public GameTreeNode() {
			children = new ArrayList<GameTreeNode>();
		}
		
		public GameTreeNode getParent() {
			return parent;
		}

		public void setParent(GameTreeNode parent) {
			this.parent = parent;
		}

		public ArrayList<GameTreeNode> getChildren() {
			return children;
		}

		public void setChildren(ArrayList<GameTreeNode> children) {
			this.children = children;
		}

		public Board getNodeBoard() {
			return board;
		}

		public void setNodeBoard(Board nodeBoard) {
			this.board = nodeBoard;
		}

		

		public int getLastMoveX() {
			return lastMoveX;
		}

		public void setLastMoveX(int lastMoveX) {
			this.lastMoveX = lastMoveX;
		}

		public int getLastMoveY() {
			return lastMoveY;
		}

		public void setLastMoveY(int lastMoveY) {
			this.lastMoveY = lastMoveY;
		}

		public int getLastMark() {
			return lastMark;
		}

		public void setLastMark(int lastMark) {
			this.lastMark = lastMark;
		}
		
		
		public int getEval() {
			return eval;
		}

		public void setEval(int eval) {
			this.eval = eval;
		}

		public void addChild(GameTreeNode node) {
			
			this.children.add(node);
			
		}
		
		
		//Services for the GameTreeNode class
					
		//i think this evaluates the what this specific board has and returns a 1, 0, or -1
		public int evaluate(int playersMark) {
			
			
			
			int nodesEval;
			
			if (board.isComplete()){
			
				if(board.whoIsWinner() == playersMark) {
					this.setEval(1);
				}
				else if (board.whoIsWinner() == -playersMark) {
					this.setEval(-1);
				}
				else {
					this.setEval(0);
				}
			}
		
			else {
				//i think we want to go through our children and ask them for min and max?
				if (lastMark == playersMark){
					this.setEval(2);
					
					
					for (GameTreeNode node: children)
					{
						nodesEval = node.evaluate(playersMark);
						if (nodesEval <= this.getEval()) {
							this.setEval(nodesEval);
						}
						 
					}
					
				}
				else {
					
					this.setEval(-2);
					
					
					for (GameTreeNode node: children)
					{
						nodesEval = node.evaluate(playersMark);
						if (nodesEval >= this.getEval()) {
							this.setEval(nodesEval);
						}
						 
					}
					
					
				}
				
			}
			
			return this.getEval();
		
			
			//else, we want to ask our children for their numbers
		
			
		}
		//this will return an integer for the next move for X
		public int getNextMoveX() {
			return lastMoveX;
		}
		//this will return an integer for the next move for Y
		public int getNextMoveY() {
			return lastMoveY;
		}

		public int getDepth(GameTreeNode node) {
			
			if (node.getParent() == null)
			{
				return 0;
			}
			else {
				return 1 + getDepth(node.getParent());
			}
		}
		
		//this function creates children if neccesary
		public void createChildren(int myMark) {
			
			for(int i = 0; i < 3; i++)
			{
				
				for (int j = 0; j < 3; j++)
				{
					/*if(getDepth(this)==1) {
						
						System.out.println(i +"," + j + "=" + this.getNodeBoard().getBoard()[i][j]);
					}*/
						
					if(this.getNodeBoard().getBoard()[i][j] == 0)
					{
						GameTreeNode node = new GameTreeNode();
						node.setNodeBoard(this.getNodeBoard().clone()); //set the roots board
						node.getNodeBoard().getBoard()[i][j] = myMark;
						node.setParent(this);
						node.setLastMark(myMark);
						
						node.setLastMoveX(i);
						node.setLastMoveY(j);
						
						this.addChild(node);
						
						
						
						
						//this will call if we need to create more children
						int newMark;
						if(!node.getNodeBoard().isFull() && !node.getNodeBoard().isComplete())
						{
							if(myMark == 1)
							{
								newMark = -1;
							}
							else
							{
								newMark = 1;
							}
							
							node.createChildren(newMark);
						}
						
							
					}
					
						
				}
				
			}
			
		}
		
	
		
	}
	//end of the node nested class------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	//Attributes for the GameTree
	private GameTreeNode root;
	private int myMark;
	
	
	public GameTreeNode getRoot() {
		return root;
	}
	public void setRoot(GameTreeNode root) {
		this.root = root;
	}
	public int getMyMark() {
		return myMark;
	}
	public void setMyMark(int myMark) {
		this.myMark = myMark;
	}
	
	
	
	//Services for the GameTree
	
	//this is where we create our tree, i think we set the currentBoard as
	// the root and then call a function to create the children
	public void createTree(Board currentBoard, int mark) {
		
		
		
		root = new GameTreeNode(); //initialize the root
		root.setNodeBoard(currentBoard); //set the roots board
		root.setParent(null);
		this.setMyMark(mark); //set the mark 
		
		root.createChildren(this.getMyMark());
		
		
		
	}
	
	//hmmmmm think about this one for a second
	//this one evaluates the entire tree, while the one in GameTreeNode doesnt?
	public void evaluateTree() {
		
		 root.evaluate(myMark);	
		
		
	}
	
	public int getNextMoveX() {
		
		GameTreeNode nextMoveNode = null;
		
		int max = -2;
		
		
		for (GameTreeNode node: root.getChildren())
		{
			if (node.getEval() >= max) {
				nextMoveNode = node;
				max = node.getEval();
				
			}
			 
		}
		
		return nextMoveNode.getLastMoveX();
		
	}
	
	public int getNextMoveY() {
		
		GameTreeNode nextMoveNode = null;
		
		int max = -2;
		
		
		for (GameTreeNode node: root.getChildren())
		{
			if (node.getEval() >= max) {
				nextMoveNode = node;
				max = node.getEval();
				
			}
			 
		}
		
		return nextMoveNode.getLastMoveY();
		
	}
	
	
	
		
	
	
	
	
	
}


