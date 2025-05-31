package TicTacToe;

import java.util.*;

public class TicTacToe {
	public static final int size=3;
	public static final char PLAYER_X='X';
	public static final char PLAYER_O='O';
	private char board[][];
	
	private char currentPlayer;
	
	private Scanner scan;
	
	public TicTacToe() {
		board=new char[size][size];
		int boxNumber=0;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				board[i][j]=(char)('0'+boxNumber++);
			}
		}
		// current player starts with X
		currentPlayer=PLAYER_X;
		scan=new Scanner(System.in);
	}
	// Method to check which player won
	public boolean checkWinner(char player) {
		//horizontal
		for(int i=0;i<size;i++) {
			if(board[i][0]==player && board[i][1]==player && board[i][2]==player)
				return true;
		}
		//vertical
		for(int j=0;j<size;j++) {
			if(board[0][j]==player && board[1][j]==player && board[2][j]==player)
				return true;
		}
		//diagonals
		if(board[0][0]==player && board[1][1]==player && board[2][2]==player)
			return true;
		if(board[2][0]==player && board[1][1]==player && board[0][2]==player)
			return true;
		
		return false;
	}
	// Board is full , when there is no digit left on the board
	public boolean isBoardFull() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(Character.isDigit(board[i][j]))
					return false;
			}
		}
		return true;
	}
	/*
	 * Method to check if the game is over
	 */
	public boolean isGameOver() {
		return checkWinner(PLAYER_X) || checkWinner(PLAYER_O) || isBoardFull();
	}
	public int makeMove() {
		// making a move is asking the user to enter a box number,
		// where the current player will place their marker
		System.out.print("Player "+currentPlayer+" Turn. Choose a Box number : ");
		int boxNumber=scan.nextInt();
		System.out.println();
		
		// validate box number
		if(boxNumber<0 || boxNumber>8) {
			System.out.println("Input Box number is Invalid! Try Again");
			return makeMove();
		}
		// check if the input box number is empty
		int i=boxNumber/size;
		int j=boxNumber%size;
		if(board[i][j]==PLAYER_X || board[i][j]==PLAYER_O) {
			System.out.println("Input box is already occupied! Try Again");
			return makeMove();
		}
		
		//return the box number
		return boxNumber;
	}
	/*
	 * Method to play the Game 
	 */
	public void play() {
		// we play as long as the game is not over
		while(!isGameOver()) {
			// print the board
			print();
			System.out.println();
			
			// ask the current player to make a move
			int boxNumber=makeMove();
			// place the marker for the current player here
			int i=boxNumber/size;
			int j=boxNumber%size;
			board[i][j]=currentPlayer;
			
			// switch the player
			currentPlayer = currentPlayer==PLAYER_X ? PLAYER_O : PLAYER_X;
		}
		// FINALLY PRINT THE WINNER
		if(checkWinner(PLAYER_X))
			System.out.println("****Player X won****");
		else if(checkWinner(PLAYER_O))
			System.out.println("$$$$Player O won$$$$");
		else 
			System.out.println("@#@#Game drawn!@#@#");
	}
	public void print() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.printf(" %s ",""+board[i][j]);
				if(j<size-1)System.out.print("|");
			}
			System.out.println();
			if(i<size-1)System.out.println("-----------");
		}
	}
}
