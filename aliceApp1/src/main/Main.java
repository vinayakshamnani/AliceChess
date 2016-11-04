package main;

import java.util.List;
import java.util.Scanner;

import ai.AliceAI;
import ai.AliceAIFactory;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        boolean isStarted = true;
        AliceAI ai = AliceAIFactory.GetInstance().getAIComponent();

        String readColor = sc.nextLine();
        String player = readColor.equals("you are white") ? "white" : "black";

        //white first move
        if(player.equals("white")) {
            List<String> whiteFirstMoves = ai.nextWhiteMoves();
            ai.update(whiteFirstMoves.get(0));
            System.out.println(whiteFirstMoves.get(0));
        }

        while(isStarted){
            String read = sc.nextLine();
            if(read.substring(6, 11).equals("moves")){
                //TODO:first check if the move is legal, if it is, update the board
            	ai.update(read);
                if(player.equals("white")){
                    List<String> whiteMoves = ai.nextWhiteMoves();
                    if(whiteMoves.size() == 0){
                        System.out.println("white offers draw");
                        break;
                    }
                    ai.update(whiteMoves.get(0));
//                    for(String s : whiteMoves) System.out.println(s);
                    System.out.println(whiteMoves.get(0));
                }
                else{
                    List<String> blackMoves = ai.nextBlackMoves();
                    if(blackMoves.size() == 0){
                        System.out.println("black offers draw");
                        break;
                    }
                    ai.update(blackMoves.get(0));
                    System.out.println(blackMoves.get(0));
                }
            }
            else isStarted = false;
        }
        sc.close();
	}

}
