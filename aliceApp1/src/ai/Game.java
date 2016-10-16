package AI;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Chang on 10/15/2016.
 */
public class Game {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean isStarted = true;

        String readColor = sc.nextLine();
        String player = readColor.equals("you are white") ? "white" : "black";

        //white first move
        if(player.equals("white")) {
            List<String> whiteFirstMoves = Board.nextWhiteMoves();
            Board.update(whiteFirstMoves.get(0));
            System.out.println(whiteFirstMoves.get(0));
        }

        while(isStarted){
            String read = sc.nextLine();
            if(read.substring(6, 11).equals("moves")){
                //TODO:first check if the move is legal, if it is, update the board
                Board.update(read);
                if(player.equals("white")){
                    List<String> whiteMoves = Board.nextWhiteMoves();
                    if(whiteMoves.size() == 0){
                        System.out.println("white offers draw");
                        break;
                    }
                    Board.update(whiteMoves.get(0));
//                    for(String s : whiteMoves) System.out.println(s);
                    System.out.println(whiteMoves.get(0));
                }
                else{
                    List<String> blackMoves = Board.nextBlackMoves();
                    if(blackMoves.size() == 0){
                        System.out.println("black offers draw");
                        break;
                    }
                    Board.update(blackMoves.get(0));
                    System.out.println(blackMoves.get(0));
                }
            }
            else isStarted = false;
        }
        sc.close();
    }
}
