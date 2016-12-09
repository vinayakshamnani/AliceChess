package main;

import java.util.List;
import java.util.Scanner;

import ai.AliceAI;
import ai.AliceAIFactory;
import util.Constants;

/**
 * Main class for running the program
 * 
 * @author Ajay
 *
 */
public class Main {

	/**
	 * main method for running the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// scanner instance for reading the inputs from referee 
		Scanner sc = new Scanner(System.in);
        boolean isStarted = true;
        
        // instance of AI for deciding the move
        AliceAI ai = AliceAIFactory.GetInstance().getAIComponent();

        String readColor = sc.nextLine();
        
        // read the player color
        String player = readColor.equals("you are white") ? Constants.PLAYER_WHITE : Constants.PLAYER_BLACK;

        //white first move
        if(player.equals(Constants.PLAYER_WHITE)) {
            String bestMove = ai.pickBestMove(true);
            System.out.println(bestMove);
            ai.update(bestMove);
        }

        // read incoming move and play next move until game closes
        while(isStarted){
            String read = sc.nextLine();
            if(read.substring(6, 11).equals(Constants.MOVES)) {
            	ai.update(read);

            	// Choose the best move for the current player, update the board; Surrender if there is no further moves
                if(player.equals(Constants.PLAYER_WHITE)){
                    List<String> whiteMoves = ai.nextWhiteMoves();
                    if(whiteMoves.size() == 0){
                    	System.out.println(player + Constants.SURRENDERS);
                        break;
                    }
                    String bestMove = ai.pickBestMove(true);
                    System.out.println(bestMove);
                    ai.update(bestMove);
                }
                else{
                    List<String> blackMoves = ai.nextBlackMoves();
                    if(blackMoves.size() == 0){
                    	System.out.println(player + Constants.SURRENDERS);
                        break;
                    }
                    String bestMove = ai.pickBestMove(false);
                    System.out.println(bestMove);
                    ai.update(bestMove);
                }
            } 
            else if(read.substring(6, 11).equals(Constants.OFFERS)){
            	// Accept the draw offered by opponent player
                System.out.println(player + Constants.ACCEPTS_DRAW);
            }
            else if(read.substring(6, 13).equals(Constants.DECLINES_DRAW)){
            	// If opponent player declines the draw, then surrender
                System.out.println(player + Constants.SURRENDERS);
            }
            else {
            	// Stop the game
            	isStarted = false;
            }
        }
        
        // close the scanner
        sc.close();
	}
}
