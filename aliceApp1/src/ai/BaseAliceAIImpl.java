package ai;

import model.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseAliceAIImpl implements AliceAI {
	private Board board = new Board();

    public List<String> nextWhiteMoves(){
        List<String> moves = new ArrayList<String>();

        for(int i = 0; i < 64; i++){
            switch (board.getFromBoard(Board.BOARD_A, i / 8, i % 8)){
                case 'P' : nextMoves_P(1, i, moves);
                    break;
                case 'R' : nextMoves_R(1, i, moves);
                    break;
                case 'N' : nextMoves_N(1, i, moves);
                    break;
                case 'B' : nextMoves_B(1, i, moves);
                    break;
                case 'Q' : nextMoves_Q(1, i, moves);
                    break;
                case 'K' : nextMoves_K(1, i, moves);
                    break;
            }
        }

        for(int i = 0; i < 64; i++){
            switch (board.getBoardB()[i / 8][i % 8]){
                case 'P' : nextMoves_P(2, i, moves);
                    break;
                case 'R' : nextMoves_R(2, i, moves);
                    break;
                case 'N' : nextMoves_N(2, i, moves);
                    break;
                case 'B' : nextMoves_B(2, i, moves);
                    break;
                case 'Q' : nextMoves_Q(2, i, moves);
                    break;
                case 'K' : nextMoves_K(2, i, moves);
                    break;
            }
        }
        return moves;
    }

    public List<String> nextBlackMoves(){
        List<String> moves = new ArrayList<String>();

        for(int i = 0; i < 64; i++){
            switch (board.getFromBoard(Board.BOARD_A, i / 8, i % 8)){
                case 'p' : nextMoves_p(1, i, moves);
                    break;
                case 'r' : nextMoves_R(1, i, moves);
                    break;
                case 'n' : nextMoves_N(1, i, moves);
                    break;
                case 'b' : nextMoves_B(1, i, moves);
                    break;
                case 'q' : nextMoves_Q(1, i, moves);
                    break;
                case 'k' : nextMoves_K(1, i, moves);
                    break;
            }
        }

        for(int i = 0; i < 64; i++){
            switch (board.getBoardB()[i / 8][i % 8]){
                case 'p' : nextMoves_p(2, i, moves);
                    break;
                case 'r' : nextMoves_R(2, i, moves);
                    break;
                case 'n' : nextMoves_N(2, i, moves);
                    break;
                case 'b' : nextMoves_B(2, i, moves);
                    break;
                case 'q' : nextMoves_Q(2, i, moves);
                    break;
                case 'k' : nextMoves_K(2, i, moves);
                    break;
            }
        }
        return moves;
    }

    private void nextMoves_P(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            //P is at the initial position
            if(board.getRow()[r] == '2'){
                //move forward by 1 step
                if(board.getFromBoard(Board.BOARD_A, r - 1, c) == ' ' && board.getBoardB()[r - 1][c] == ' '){
                    moves.add("white moves P from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r - 1]);
                }
                //move by 2 steps
                if(board.getFromBoard(Board.BOARD_A, r - 2, c) == ' ' && board.getBoardB()[r - 2][c] == ' '){
                    moves.add("white moves P from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r - 2]);
                }
            }

            else{
                //TODO: en passant
                //TODO: promotion
                //TODO: catch enemy piece

                //move forward by 1 step
                if(board.getFromBoard(Board.BOARD_A, r - 1, c) == ' ' && board.getBoardB()[r - 1][c] == ' '){
                    moves.add("white moves P from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r - 1]);
                }

            }
        }

        else{
            //TODO: en passant
            //TODO: promotion
            //TODO: catch enemy piece

            //move forward by 1 step
            if(board.getBoardB()[r - 1][c] == ' ' && board.getFromBoard(Board.BOARD_A, r - 1, c) == ' '){
                moves.add("white moves P from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r - 1]);
            }
        }


    }

    private void nextMoves_p(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            //p is at the initial position
            if(board.getRow()[r] == '7'){
                //move forward by 1 step
                if(board.getFromBoard(Board.BOARD_A, r + 1, c) == ' ' && board.getBoardB()[r + 1][c] == ' '){
                    moves.add("black moves P from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + 1]);
                }
                //move by 2 steps
                if(board.getFromBoard(Board.BOARD_A, r + 2, c) == ' ' && board.getBoardB()[r + 2][c] == ' '){
                    moves.add("black moves P from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + 2]);
                }
                //TODO: catch enemy piece
            }

            else{
                //TODO: en passant
                //TODO: promotion
                //TODO: catch enemy piece

                //move forward by 1 step
                if(board.getFromBoard(Board.BOARD_A, r + 1, c) == ' ' && board.getBoardB()[r + 1][c] == ' '){
                    moves.add("black moves P from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + 1]);
                }

            }
        }

        else{
            //TODO: promotion
            //TODO: catch enemy piece

            //move forward by 1 step
            if(board.getBoardB()[r + 1][c] == ' ' && board.getFromBoard(Board.BOARD_A, r + 1, c) == ' '){
                moves.add("black moves P from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + 1]);
            }
        }


    }

    private void nextMoves_R(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            //TODO: castling

            String player = Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c)) ? "white" : "black";
            //move left
            for(int i = c - 1; i >= 0; i--){
                if(board.getFromBoard(Board.BOARD_A, r, i) == ' '){
                    if(board.getBoardB()[r][i] == ' '){
                        moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[r]);
                    }
                }
                else if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, i))){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[r]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[r]);
                        }
                        break;
                    }
                }
            }
            //move right
            for(int i = c + 1; i < 8; i++){
                if(board.getFromBoard(Board.BOARD_A, r, i) == ' '){
                    if(board.getBoardB()[r][i] == ' '){
                        moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[r]);
                    }
                }
                else if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, i))){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[r]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[r]);
                        }
                        break;
                    }
                }
            }
            //move up
            for(int i = r - 1; i >= 0; i--){
                if(board.getFromBoard(Board.BOARD_A, i, c) == ' '){
                    if(board.getBoardB()[i][c] == ' '){
                        moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                    }
                }
                else if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, i, c))){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
            }
            //move down
            for(int i = r + 1; i < 8; i++){
                if(board.getFromBoard(Board.BOARD_A, i, c) == ' '){
                    if(board.getBoardB()[i][c] == ' '){
                        moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                    }
                }
                else if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, i, c))){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getBoardB()[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
            }
        }

        else{
            String player = Character.isUpperCase(board.getBoardB()[r][c]) ? "white" : "black";
            //move left
            for(int i = c - 1; i >= 0; i--){
                if(board.getBoardB()[r][i] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, r, i) == ' '){
                        moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[i]);
                    }
                }
                else if(Character.isUpperCase(board.getBoardB()[r][i])){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, r, i) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, r, i) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[i]);
                        }
                        break;
                    }
                }
            }
            //move right
            for(int i = c + 1; i < 8; i++){
                if(board.getBoardB()[r][i] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, r, i) == ' '){
                        moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[i]);
                    }
                }
                else if(Character.isUpperCase(board.getBoardB()[r][i])){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, r, i) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, r, i) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[i] + board.getRow()[i]);
                        }
                        break;
                    }
                }
            }
            //move up
            for(int i = r - 1; i >= 0; i--){
                if(board.getBoardB()[i][c] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, i, c) == ' '){
                        moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                    }
                }
                else if(Character.isUpperCase(board.getBoardB()[r][i])){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, i, c) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, i, c) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
            }
            //move down
            for(int i = r + 1; i < 8; i++){
                if(board.getBoardB()[i][c] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, i, c) == ' '){
                        moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                    }
                }
                else if(Character.isUpperCase(board.getBoardB()[r][i])){
                    //can not pass the same board.getCol()or piece
                    if(player.equals("white")) break;
                        //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, i, c) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same board.getCol()or piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(board.getFromBoard(Board.BOARD_A, i, c) == ' ') {
                            moves.add(player + " moves R from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[i]);
                        }
                        break;
                    }
                }
            }
        }

    }

    private void nextMoves_N(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        List<List<Integer>> candidates = new ArrayList<List<Integer>>();
        candidates.add(Arrays.asList(r + 1, c + 2));
        candidates.add(Arrays.asList(r + 2, c + 1));
        candidates.add(Arrays.asList(r + 2, c - 1));
        candidates.add(Arrays.asList(r + 1, c - 2));
        candidates.add(Arrays.asList(r - 1, c - 2));
        candidates.add(Arrays.asList(r - 2, c - 1));
        candidates.add(Arrays.asList(r - 2, c + 1));
        candidates.add(Arrays.asList(r - 1, c + 2));

        if(boardTag == 1){
            String player = Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c)) ? "white" : "black";

            for(List<Integer> list : candidates){
                // check if the target place is within the board
                if(list.get(0) >= 0 && list.get(0) < 8 && list.get(1) >= 0 && list.get(1) < 8){
                    // check if the target place is empty or occupied by enemy piece
                    if(board.getFromBoard(Board.BOARD_A, list.get(0), list.get(1)) == ' ' ||
                            Character.isUpperCase(board.getFromBoard(Board.BOARD_A, list.get(0), list.get(1))) != Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c))){
                        // check if the corresponding place on the other board is empty
                        if(board.getBoardB()[list.get(0)][list.get(1)] == ' '){
                            moves.add(player + " moves N from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[list.get(1)] + board.getRow()[list.get(0)]);
                        }
                    }
                }
            }

        }

        else{
            String player = Character.isUpperCase(board.getBoardB()[r][c]) ? "white" : "black";

            for(List<Integer> list : candidates){
                // check if the target place is within the board
                if(list.get(0) >= 0 && list.get(0) < 8 && list.get(1) >= 0 && list.get(1) < 8){
                    // check if the target place is empty or occupied by enemy piece
                    if(board.getBoardB()[list.get(0)][list.get(1)] == ' ' ||
                            Character.isUpperCase(board.getBoardB()[list.get(0)][list.get(1)]) != Character.isUpperCase(board.getBoardB()[r][c])){
                        // check if the corresponding place on the other board is empty
                        if(board.getFromBoard(Board.BOARD_A, list.get(0), list.get(1)) == ' '){
                            moves.add(player + " moves N from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[list.get(1)] + board.getRow()[list.get(0)]);
                        }
                    }
                }
            }
        }
    }

    private void nextMoves_B(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            String player = Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c)) ? "white" : "black";

            // move southeast
            for(int i = r + 1, j = c + 1; i < 8 && j < 8; i++, j++){
                if(board.getFromBoard(Board.BOARD_A, i, j) == ' '){
                    if(board.getBoardB()[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, i, j)) != Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c))){
                        if(board.getBoardB()[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

            // move southwest
            for(int i = r + 1, j = c - 1; i < 8 && j >= 0; i++, j--){
                if(board.getFromBoard(Board.BOARD_A, i, j) == ' '){
                    if(board.getBoardB()[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, i, j)) != Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c))){
                        if(board.getBoardB()[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

            // move northwest
            for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--){
                if(board.getFromBoard(Board.BOARD_A, i, j) == ' '){
                    if(board.getBoardB()[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, i, j)) != Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c))){
                        if(board.getBoardB()[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

            // move northeast
            for(int i = r - 1, j = c + 1; i >= 0 && j < 8; i--, j++){
                if(board.getFromBoard(Board.BOARD_A, i, j) == ' '){
                    if(board.getBoardB()[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, i, j)) != Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c))){
                        if(board.getBoardB()[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

        }

        else{
            String player = Character.isUpperCase(board.getBoardB()[r][c]) ? "white" : "black";

            // move southeast
            for(int i = r + 1, j = c + 1; i < 8 && j < 8; i++, j++){
                if(board.getBoardB()[i][j] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                        moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getBoardB()[i][j]) != Character.isUpperCase(board.getBoardB()[r][c])){
                        if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                            moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

            // move southwest
            for(int i = r + 1, j = c - 1; i < 8 && j >= 0; i++, j--){
                if(board.getBoardB()[i][j] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                        moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getBoardB()[i][j]) != Character.isUpperCase(board.getBoardB()[r][c])){
                        if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                            moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

            // move northwest
            for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--){
                if(board.getBoardB()[i][j] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                        moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getBoardB()[i][j]) != Character.isUpperCase(board.getBoardB()[r][c])){
                        if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                            moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

            // move northeast
            for(int i = r - 1, j = c + 1; i >= 0 && j < 8; i--, j++){
                if(board.getBoardB()[i][j] == ' '){
                    if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                        moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(board.getBoardB()[i][j]) != Character.isUpperCase(board.getBoardB()[r][c])){
                        if(board.getFromBoard(Board.BOARD_A, i, j) == ' ') {
                            moves.add(player + " moves B from 2 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[j] + board.getRow()[i]);
                        }
                    }
                    break;
                }
            }

        }
    }

    private void nextMoves_Q(int boardTag, int pos, List<String> moves){
        List<String> Q_moves = new ArrayList<String>();
        nextMoves_R(boardTag, pos, Q_moves);
        nextMoves_B(boardTag, pos, Q_moves);
        for(int i = 0; i < Q_moves.size(); i++){
            String s = Q_moves.get(i);
            String pre = s.substring(0, 12);
            String post = s.substring(13);
            String newStr = pre + 'Q' + post;
            moves.add(newStr);
        }

    }

    private void nextMoves_K(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            String player = Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c)) ? "white" : "black";
            //TODO: castling

            // move around by 1 step
            for(int i = 0; i < 9; i++){
                if(i != 4){
                    int r1 = r - 1 + i / 3;
                    int c1 = c - 1 + i % 3;
                    if(r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8){
                        if(board.getFromBoard(Board.BOARD_A, r1, c1) == ' '){
                            if(board.getBoardB()[r1][c1] == ' '){
                                moves.add(player + " moves K from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c1] + board.getRow()[r1]);
                            }
                        }
                        else{
                            if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r1, c1)) != Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c))){
                                if(board.getBoardB()[r1][c1] == ' '){
                                    moves.add(player + " moves K from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c1] + board.getRow()[r1]);
                                }
                            }
                        }
                    }
                }
            }
        }

        else{
            String player = Character.isUpperCase(board.getBoardB()[r][c]) ? "white" : "black";

            // move around by 1 step
            for(int i = 0; i < 9; i++){
                if(i != 4){
                    int r1 = r - 1 + i / 3;
                    int c1 = c - 1 + i % 3;
                    if(r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8){
                        if(board.getBoardB()[r1][c1] == ' '){
                            if(board.getFromBoard(Board.BOARD_A, r1, c1) == ' '){
                                moves.add(player + " moves K from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c1] + board.getRow()[r1]);
                            }
                        }
                        else{
                            if(Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r1, c1)) != Character.isUpperCase(board.getFromBoard(Board.BOARD_A, r, c))){
                                if(board.getFromBoard(Board.BOARD_A, r1, c1) == ' '){
                                    moves.add(player + " moves K from 1 " + board.getCol()[c] + board.getRow()[r] + " to " + board.getCol()[c1] + board.getRow()[r1]);
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public void update(String s) {
    	
        char boardTag = s.charAt(19);
        int preCol = s.charAt(21) - 'a';
        int preRow = 8 - (s.charAt(22) - '0');
        int col = s.charAt(27) - 'a';
        int row = 8 - (s.charAt(28) - '0');

        if(boardTag == '1'){
            board.getBoardB()[row][col] = board.getFromBoard(Board.BOARD_A, preRow, preCol);
            board.getBoardA()[preRow][preCol] = ' ';
        }
        else{
        	board.getBoardA()[row][col] = board.getBoardB()[preRow][preCol];
            board.getBoardB()[preRow][preCol] = ' ';
        }


    }
}
