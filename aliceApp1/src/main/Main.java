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
            if(read.substring(6, 11).equals("moves")) {
                // check if the move is legal, if it is, update the board
                if(player.equals(Constants.PLAYER_WHITE)){
                    List<String> legalMoves = ai.nextBlackMoves();
                    if(!legalMoves.contains(read)){
                        //System.out.println("Illegal move!");
                        break;
                    }
                }
                else{
                    List<String> legalMoves = ai.nextWhiteMoves();
                    if(!legalMoves.contains(read)){
                        //System.out.println("Illegal move!");
                        break;
                    }
                }
            	ai.update(read);

            	// check if the move made player to surrender, if it is, update the board
                if(player.equals(Constants.PLAYER_WHITE)){
                    List<String> whiteMoves = ai.nextWhiteMoves();
                    if(whiteMoves.size() == 0){
                    	System.out.println(player + " surrenders");
                        break;
                    }
                    String bestMove = ai.pickBestMove(true);
                    System.out.println(bestMove);
                    ai.update(bestMove);
                }
                else{
                    List<String> blackMoves = ai.nextBlackMoves();
                    if(blackMoves.size() == 0){
                    	System.out.println(player + " surrenders");
                        break;
                    }
                    String bestMove = ai.pickBestMove(false);
                    System.out.println(bestMove);
                    ai.update(bestMove);
                }
            } 
            else if(read.substring(6, 11).equals("offers")){
            	// Accept the draw offered by opponent player
                System.out.println(player + " accepts draw");
            }
            else if(read.substring(6, 13).equals("declines")){
            	// If opponent player declines the draw, then surrender
                System.out.println(player + " surrenders");
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
