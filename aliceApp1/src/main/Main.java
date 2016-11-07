package main;

import java.util.List;
//import java.util.Random;
import java.util.Scanner;

import ai.AliceAI;
import ai.AliceAIFactory;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        boolean isStarted = true;
        AliceAI ai = AliceAIFactory.GetInstance().getAIComponent();
        int moveCount = 0;
        Random rand = new Random();

        String readColor = sc.nextLine();
        String player = readColor.equals("you are white") ? "white" : "black";

        //white first move
        if(player.equals("white")) {
            List<String> whiteFirstMoves = ai.nextWhiteMoves();
            ai.update(whiteFirstMoves.get(3));
            System.out.println(whiteFirstMoves.get(3));
        }

        while(isStarted){
            String read = sc.nextLine();
            if(read.substring(6, 11).equals("moves")) {
            	moveCount++;
                // TODO: what should we do if we find the referee passed an illegal move?
                // check if the move is legal, if it is, update the board
                if(player.equals("white")){
                    List<String> legalMoves = ai.nextBlackMoves();
                    if(!legalMoves.contains(read)){
                        System.out.println("Illegal move!");
                        break;
                    }
                }
                else{
                    List<String> legalMoves = ai.nextWhiteMoves();
                    if(!legalMoves.contains(read)){
                        System.out.println("Illegal move!");
                        break;
                    }
                }
            	ai.update(read);

                // TODO : remove the counter once we have a better AI
                if(moveCount >= 102) {
                    System.out.println(player + " surrenders");
                    break;
                }

                if(player.equals("white")){
                    List<String> whiteMoves = ai.nextWhiteMoves();
                    if(whiteMoves.size() == 0){
                    	System.out.println(player + " surrenders");
                        break;
                    }
                    String theMove = whiteMoves.get(rand.nextInt(whiteMoves.size()));
                    ai.update(theMove);
                    // for(String s : whiteMoves) System.out.println(s);
                    System.out.println(theMove);
                }
                else{
                    List<String> blackMoves = ai.nextBlackMoves();
                    if(blackMoves.size() == 0){
                    	System.out.println(player + " surrenders");
                        break;
                    }
                    String theMove = blackMoves.get(rand.nextInt(blackMoves.size()));
                    ai.update(theMove);
                    System.out.println(theMove);
                }
                

            }
            else isStarted = false;
        }
        sc.close();
	}
}
