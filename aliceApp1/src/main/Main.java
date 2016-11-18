package main;

import java.util.List;
//import java.util.Random;
import java.util.Scanner;

import ai.AliceAI;
import ai.AliceAIFactory;

import java.util.Random;

import util.MoveFilter;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        boolean isStarted = true;
        AliceAI ai = AliceAIFactory.GetInstance().getAIComponent();
//        int moveCount = 0;
//        Random rand = new Random();


        String readColor = sc.nextLine();
        String player = readColor.equals("you are white") ? "white" : "black";

        //white first move
        if(player.equals("white")) {
            List<String> whiteFirstMoves = ai.nextWhiteMoves();
            ai.update(whiteFirstMoves.get(4));
            System.out.println(whiteFirstMoves.get(4));
        }

        while(isStarted){
            String read = sc.nextLine();
            if(read.substring(6, 11).equals("moves")) {
//            	moveCount++;
                // TODO: what should we do if we find the referee passed an illegal move?
                // check if the move is legal, if it is, update the board
                if(player.equals("white")){
                    List<String> legalMoves = ai.nextBlackMoves();
                    if(!MoveFilter.ContainsInListIgnoringCase(legalMoves, read)){
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

//                // TODO : remove the counter once we have a better AI
//                if(moveCount >= 102) {
//                    System.out.println(player + " surrenders");
//                    break;
//                }

                if(player.equals("white")){
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
                System.out.println(player + " accepts draw");
            }
            else if(read.substring(6, 13).equals("declines")){
                System.out.println(player + " surrenders");
            }
            else isStarted = false;
        }
        sc.close();
	}
}
