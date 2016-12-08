package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Board;

public abstract class BaseChessPiece implements IChessPiece {
	Board board;
	
	protected char name;

	public BaseChessPiece(Board board) {
		this.board = board;
	}
	
	public char getPieceName() {
		return name;
	}
	
	public boolean isKingSafe(String player) {
        // get the position of the king
        int boardTag_K = 0, pos_K = 0, boardTag_k = 0, pos_k = 0;
        for(int i = 0; i < 64; i++){
            if(board.getBoardA()[i / 8][i % 8] == 'K'){
                boardTag_K = 1;
                pos_K = i;
            }
            if(board.getBoardB()[i / 8][i % 8] == 'K'){
                boardTag_K = 2;
                pos_K = i;
            }
            if(board.getBoardA()[i / 8][i % 8] == 'k'){
                boardTag_k = 1;
                pos_k = i;
            }
            if(board.getBoardB()[i / 8][i % 8] == 'k'){
                boardTag_k = 2;
                pos_k = i;
            }
        }
        int r_K = pos_K / 8;
        int c_K = pos_K % 8;
        int r_k = pos_k / 8;
        int c_k = pos_k % 8;

        // check if king is under attack by an enemy pawn
        if(player.equals("white")){
            try {
                if (board.getFromBoard(boardTag_K, r_K - 1, c_K - 1) == 'p') {
                    return false;
                }
            }
            catch (Exception e){}
            try {
                if (board.getFromBoard(boardTag_K, r_K - 1, c_K + 1) == 'p') {
                    return false;
                }
            }
            catch (Exception e){}
        }
        else{
            try {
                if (board.getFromBoard(boardTag_k, r_k + 1, c_k - 1) == 'P') {
                    return false;
                }
            }
            catch (Exception e){}
            try {
                if (board.getFromBoard(boardTag_k, r_k + 1, c_k + 1) == 'P') {
                    return false;
                }
            }
            catch (Exception e){}
        }

        // check knight
        List<List<Integer>> candidates = new ArrayList<List<Integer>>();
        if(player.equals("white")) {
            candidates.add(Arrays.asList(r_K + 1, c_K + 2));
            candidates.add(Arrays.asList(r_K + 2, c_K + 1));
            candidates.add(Arrays.asList(r_K + 2, c_K - 1));
            candidates.add(Arrays.asList(r_K + 1, c_K - 2));
            candidates.add(Arrays.asList(r_K - 1, c_K - 2));
            candidates.add(Arrays.asList(r_K - 2, c_K - 1));
            candidates.add(Arrays.asList(r_K - 2, c_K + 1));
            candidates.add(Arrays.asList(r_K - 1, c_K + 2));
            for (List<Integer> list : candidates) {
                try {
                    if (board.getFromBoard(boardTag_K, list.get(0), list.get(1)) == 'n') return false;
                }
                catch (Exception e) {}
            }
        }
        else{
            candidates.add(Arrays.asList(r_k + 1, c_k + 2));
            candidates.add(Arrays.asList(r_k + 2, c_k + 1));
            candidates.add(Arrays.asList(r_k + 2, c_k - 1));
            candidates.add(Arrays.asList(r_k + 1, c_k - 2));
            candidates.add(Arrays.asList(r_k - 1, c_k - 2));
            candidates.add(Arrays.asList(r_k - 2, c_k - 1));
            candidates.add(Arrays.asList(r_k - 2, c_k + 1));
            candidates.add(Arrays.asList(r_k - 1, c_k + 2));
            for (List<Integer> list : candidates) {
                try {
                    if (board.getFromBoard(boardTag_k, list.get(0), list.get(1)) == 'N') return false;
                }
                catch (Exception e) {}
            }
        }

        // check bishop or queen
        if(player.equals("white")) {
            // use i and j to indicate the direction of the move, for example (1, 1) refers to moving southeast
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    // steps indicates how far the move is from the original position
                    int steps = 1;
                    try {
                        // keep trying while the path is empty, else check if the piece on the path is an enemy piece
                        while (board.getFromBoard(boardTag_K, r_K + i * steps, c_K + j * steps) == ' ') {
                            steps++;
                        }
                        if (board.getFromBoard(boardTag_K, r_K + i * steps, c_K + j * steps) == 'b' ||
                                board.getFromBoard(boardTag_K, r_K + i * steps, c_K + j * steps) == 'q') {
                            return false;
                        }
                    }
                    catch (Exception e) {}
                }
            }
        }
        else{
            // use i and j to indicate the direction of the move, for example (1, 1) refers to moving southeast
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    // steps indicates how far the move is from the original position
                    int steps = 1;
                    try {
                        // keep trying while the path is empty, else check if the piece on the path is an enemy piece
                        while (board.getFromBoard(boardTag_k, r_k + i * steps, c_k + j * steps) == ' ') {
                            steps++;
                        }
                        if (board.getFromBoard(boardTag_k, r_k + i * steps, c_k + j * steps) == 'B' ||
                                board.getFromBoard(boardTag_k, r_k + i * steps, c_k + j * steps) == 'Q') {
                            return false;
                        }
                    }
                    catch (Exception e) {}
                }
            }
        }

        // check rook or queen
        if(player.equals("white")){
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    if((i == 0 && j != 0) || (i != 0 && j == 0)){
                        // steps indicates how far the move is from the original position
                        int steps = 1;
                        try{
                            // keep trying while the path is empty, else check if the piece on the path is an enemy
                            // piece
                            while(board.getFromBoard(boardTag_K, r_K + i * steps, c_K + j * steps) == ' '){
                                steps++;
                            }
                            if(board.getFromBoard(boardTag_K, r_K + i * steps, c_K + j * steps) == 'r' ||
                                    board.getFromBoard(boardTag_K, r_K + i * steps, c_K + j * steps) == 'q'){
                                return false;
                            }
                        }
                        catch (Exception e){}
                    }
                }
            }
        }
        else{
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    if((i == 0 && j != 0) || (i != 0 && j == 0)){
                        // steps indicates how far the move is from the original position
                        int steps = 1;
                        try{
                            // keep trying while the path is empty, else check if the piece on the path is an enemy
                            // piece
                            while(board.getFromBoard(boardTag_k, r_k + i * steps, c_k + j * steps) == ' '){
                                steps++;
                            }
                            if(board.getFromBoard(boardTag_k, r_k + i * steps, c_k + j * steps) == 'R' ||
                                    board.getFromBoard(boardTag_k, r_k + i * steps, c_k + j * steps) == 'Q'){
                                return false;
                            }
                        }
                        catch (Exception e){}
                    }
                }
            }
        }

        // check king
        if(player.equals("white")){
            for(int i = 0; i < 9; i++) {
                if (i != 4) {
                    int r1 = r_K - 1 + i / 3;
                    int c1 = c_K - 1 + i % 3;
                    // check if the position is within the board
                    if (r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8) {
                        if (board.getFromBoard(boardTag_K, r1, c1) == 'k') {
                            return false;
                        }
                    }
                }
            }
        }
        else{
            for(int i = 0; i < 9; i++) {
                if (i != 4) {
                    int r1 = r_k - 1 + i / 3;
                    int c1 = c_k - 1 + i % 3;
                    // check if the position is within the board
                    if (r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8) {
                        if (board.getFromBoard(boardTag_k, r1, c1) == 'K') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

	
}
