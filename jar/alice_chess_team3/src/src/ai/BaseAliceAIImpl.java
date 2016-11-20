package ai;

import model.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Our first iteration of the AliceAI api.
 * It's better than just dumbly giving up or picking a random move that can be invalid.
 * It actually calculates all the legal moves that can be made for the current game board.
 * From this List of valid/legal moves, this implementation only returns the 1st
 * move in the list.
 * It does NOT select the smarter option of the bunch. There is no formal intelligence associated to it.
 * 
 * @author krish
 *
 */
public class BaseAliceAIImpl implements AliceAI {
	/**
	 * The actual Board to hold the current state of the game.
	 */
	private Board board = new Board();

	/**
	 * This is not doing any thing as of now. Just returns the same input List of moves. 
	 */
	public List<String> filterMoves(List<String> validMoves) {
		return validMoves;
	}
	
	/**
	 * Returns the next set of white moves that are legal to make but not smarter.
	 */
    public List<String> nextWhiteMoves(){
        List<String> moves = new ArrayList<String>();

        /* Loop through all positions on the first board and find out if a move can be made by the piece */
        for(int i = 0; i < 64; i++){
            switch (board.getBoardA()[i / 8][i % 8]){
                case 'P' : nextMoves_P(1, i, moves, -1);
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

        /* Loop through the second board and find out all possible moves that can be made. */
        for(int i = 0; i < 64; i++){
            switch (board.getBoardB()[i / 8][i % 8]){
                case 'P' : nextMoves_P(2, i, moves, -1);
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
        
        /* Finally return the List of moves. If this is empty, the calling program should give up. */
        return moves;
    }

    /**
     * Returns the next set of black moves that are legal to make but not smarter.
     */
    public List<String> nextBlackMoves(){
        List<String> moves = new ArrayList<String>();

        /* Loop through all positions on the first board and find out if a move can be made by the piece */
        for(int i = 0; i < 64; i++){
            switch (board.getBoardA()[i / 8][i % 8]){
                case 'p' : nextMoves_P(1, i, moves, 1);
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

        /* Loop through the second board and find out all possible moves that can be made. */
        for(int i = 0; i < 64; i++){
            switch (board.getBoardB()[i / 8][i % 8]){
                case 'p' : nextMoves_P(2, i, moves, 1);
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

    private void nextMoves_P(int boardTag, int pos, List<String> moves, int direction){
        int r = pos / 8;
        int c = pos % 8;
        int dir = direction;
        String player = (dir == -1) ? "white" : "black";
        char ini_pos = (dir == -1) ? '2' : '7';
        char ch = (dir == -1) ? 'P' : 'p';

        //pawn can move 2 steps if it is at the initial position
        if(boardTag == 1 && board.getRow()[r] == ini_pos){
            //move by 2 steps
            if(board.getFromBoard(boardTag, r + 2 * dir, c) == ' ' && board.getBoardB()[r + 2 * dir][c] == ' '){
                // temporarily update the board to check if the king is still safe after the move
                board.setBoard(boardTag, r, c, ' ');
                board.setBoard((boardTag * 2) % 3, r + 2 * dir, c, ch);
                if(isKingSafe(player)) {
                    moves.add(player + " moves P from " + boardTag + " " + board.getCol()[c] +
                            board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + 2 * dir]);
                }
                //undo update
                board.setBoard(boardTag, r, c, ch);
                board.setBoard((boardTag * 2) % 3, r + 2 * dir, c, ' ');
            }
        }

        // TODO: promotion not considered yet
        //move forward by 1 step
        if(board.getFromBoard(boardTag, r + dir, c) == ' ' &&
                board.getFromBoard((boardTag * 2) % 3, r + dir, c) == ' '){
            // temporarily update the board to check if the king is still safe after the move
            board.setBoard(boardTag, r, c, ' ');
            board.setBoard((boardTag * 2) % 3, r + dir, c, ch);
            if(isKingSafe(player)) {
                moves.add(player + " moves P from " + boardTag + " " + board.getCol()[c] +
                        board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + dir]);
            }
            //undo update
            board.setBoard(boardTag, r, c, ch);
            board.setBoard((boardTag * 2) % 3, r + dir, c, ' ');
        }
        //catch enemy piece
        try{
            for(int i = -1; i <= 1; i+=2) {
                if (board.getFromBoard(boardTag, r + dir, c + i) != ' ' &&
                        Character.isUpperCase(board.getFromBoard(boardTag, r + dir, c + i)) != player.equals("white")
                        && board.getFromBoard((boardTag * 2) % 3, r + dir, c + i) == ' '){
                    char enemy = board.getFromBoard(boardTag, r + dir, c + i);
                    // temporarily update the board to check if the king is still safe after the move
                    board.setBoard(boardTag, r, c, ' ');
                    board.setBoard(boardTag, r + dir, c + i, ' ');
                    board.setBoard((boardTag * 2) % 3, r + dir, c + i, ch);
                    if (isKingSafe(player)) {
                        moves.add(player + " moves P from " + boardTag + " " + board.getCol()[c] +
                                board.getRow()[r] + " to " + board.getCol()[c + i] + board.getRow()[r + dir]);
                    }
                    //undo update
                    board.setBoard(boardTag, r, c, ch);
                    board.setBoard(boardTag, r + dir, c + i, enemy);
                    board.setBoard((boardTag * 2) % 3, r + dir, c + i, ' ');
                }
            }
        }catch (Exception e){}

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

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? "white" : "black";

        for(List<Integer> list : candidates){
            // check if the target place is within the board
            if(list.get(0) >= 0 && list.get(0) < 8 && list.get(1) >= 0 && list.get(1) < 8){
                // check if the target place is empty or occupied by enemy piece
                if(board.getFromBoard(boardTag, list.get(0), list.get(1)) == ' ' ||
                        Character.isUpperCase(board.getFromBoard(boardTag, list.get(0), list.get(1))) !=
                                Character.isUpperCase(board.getFromBoard(boardTag, r, c))){
                    // check if the corresponding place on the other board is empty
                    if(board.getFromBoard((boardTag * 2) % 3, list.get(0), list.get(1)) == ' '){
                        char ch = player.equals("white") ? 'N' : 'n';
                        char enemy = board.getFromBoard(boardTag, list.get(0), list.get(1));
                        // temporarily update the board to check if the king is still safe
                        board.setBoard(boardTag, r, c, ' ');
                        board.setBoard(boardTag, list.get(0), list.get(1), ' ');
                        board.setBoard((boardTag * 2) % 3, list.get(0), list.get(1), ch);
                        if(isKingSafe(player)) {
                            moves.add(player + " moves N from " + boardTag + " " +
                                    board.getCol()[c] + board.getRow()[r] + " to " +
                                    board.getCol()[list.get(1)] + board.getRow()[list.get(0)]);
                        }
                        // undo the update
                        board.setBoard(boardTag, r, c, ch);
                        board.setBoard(boardTag, list.get(0), list.get(1), enemy);
                        board.setBoard((boardTag * 2) % 3, list.get(0), list.get(1), ' ');
                    }
                }
            }
        }
    }

    private void nextMoves_R(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? "white" : "black";

        // use i and j to indicate the direction of the move, for example (0, 1) refers to moving right
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if((i == 0 && j != 0) || (i != 0 && j == 0)){
                    // steps indicates how far the move is from the original position
                    int steps = 1;
                    try{
                        // keep trying while the path is empty, else check if the piece on the path is an enemy piece
                        while(board.getFromBoard(boardTag, r + i * steps, c + j * steps) == ' '){
                            // if the target position on the opposite board is empty and king is safe after the move
                            // then we can make the move
                            if(board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' '){
                                char ch = player.equals("white") ? 'R' : 'r';
                                // update the board to can check if king is still safe after the rook move
                                board.setBoard(boardTag, r, c, ' ');
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch);
                                if(isKingSafe(player)) {
                                    moves.add(player + " moves R from " + boardTag + " " +
                                            board.getCol()[c] + board.getRow()[r] + " to " +
                                            board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                                }
                                // undo the move and restore the board
                                board.setBoard(boardTag, r, c, ch);
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                            }
                            steps++;
                        }
                        // can catch enemy piece
                        if(Character.isUpperCase(board.getFromBoard(boardTag, r + i * steps, c + j * steps)) !=
                                player.equals("white")){
                            if(board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' '){
                                char ch = player.equals("white") ? 'R' : 'r';
                                char enemy = board.getFromBoard(boardTag, r + i * steps, c + j * steps);
                                // temporarily update the board to can check if king is still safe after the rook move
                                board.setBoard(boardTag, r, c, ' '); // previous rook pos
                                board.setBoard(boardTag, r + i * steps, c + j * steps, ' '); // previous enemy pos
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch); // new rook pos
                                if(isKingSafe(player)) {
                                    moves.add(player + " moves R from " + boardTag + " " +
                                            board.getCol()[c] + board.getRow()[r] + " to " +
                                            board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                                }
                                // undo the update
                                board.setBoard(boardTag, r, c, ch);
                                board.setBoard(boardTag, r + i * steps, c + j * steps, enemy);
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                            }
                        }
                    }
                    catch (Exception e){}
                }
            }
        }
    }


    private void nextMoves_B(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? "white" : "black";

        // use i and j to indicate the direction of the move, for example (1, 1) refers to moving southeast
        for(int i = -1; i <= 1; i+=2){
            for(int j = -1; j <= 1; j+=2){
                // steps indicates how far the move is from the original position
                int steps = 1;
                try{
                    // keep trying while the path is empty, else check if the piece on the path is an enemy piece
                    while(board.getFromBoard(boardTag, r + i * steps, c + j * steps) == ' '){
                        // if the target position on the opposite board is empty and king is safe after the move
                        // then we can make the move
                        if(board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' '){
                            char ch = player.equals("white") ? 'B' : 'b';
                            // update the board to check if king is still safe after the rook move
                            board.setBoard(boardTag, r, c, ' ');
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch);
                            if(isKingSafe(player)) {
                                moves.add(player + " moves B from " + boardTag + " " +
                                        board.getCol()[c] + board.getRow()[r] + " to " +
                                        board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                            }
                            // undo the move and restore the board
                            board.setBoard(boardTag, r, c, ch);
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                        }
                        steps++;
                    }
                    // can catch enemy piece
                    if(Character.isUpperCase(board.getFromBoard(boardTag, r + i * steps, c + j * steps)) !=
                            player.equals("white")){
                        // check if the corresponding position on the other board is empty
                        if(board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' '){
                            char ch = player.equals("white") ? 'B' : 'b';
                            char enemy = board.getFromBoard(boardTag, r + i * steps, c + j * steps);
                            // temporarily update the board to check if king is still safe after the rook move
                            board.setBoard(boardTag, r, c, ' ');
                            board.setBoard(boardTag, r + i * steps, c + j * steps, ' ');
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch);
                            if(isKingSafe(player)) {
                                moves.add(player + " moves B from " + boardTag + " " +
                                        board.getCol()[c] + board.getRow()[r] + " to " +
                                        board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                            }
                            // undo the update
                            board.setBoard(boardTag, r, c, ch);
                            board.setBoard(boardTag, r + i * steps, c + j * steps, enemy);
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                        }
                    }
                }
                catch (Exception e){}
            }
        }
    }

    private void nextMoves_Q(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? "white" : "black";

        // use i and j to indicate the direction of the move, for example (1, 1) refers to moving southeast
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    // steps indicates how far the move is from the original position
                    int steps = 1;
                    try {
                        // keep trying while the path is empty, else check if the piece on the path is an enemy piece
                        while (board.getFromBoard(boardTag, r + i * steps, c + j * steps) == ' ') {
                            // if the target position on the opposite board is empty and king is safe after the move
                            // then we can make the move
                            if (board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' ') {
                                char ch = player.equals("white") ? 'Q' : 'q';
                                // update the board to check if king is still safe after the rook move
                                board.setBoard(boardTag, r, c, ' ');
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch);
                                if (isKingSafe(player)) {
                                    moves.add(player + " moves Q from " + boardTag + " " +
                                            board.getCol()[c] + board.getRow()[r] + " to " +
                                            board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                                }
                                // undo the move and restore the board
                                board.setBoard(boardTag, r, c, ch);
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                            }
                            steps++;
                        }
                        // can catch enemy piece
                        if (Character.isUpperCase(board.getFromBoard(boardTag, r + i * steps, c + j * steps)) !=
                                player.equals("white")) {
                            // check if the corresponding position on the other board is empty
                            if (board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' ') {
                                char ch = player.equals("white") ? 'Q' : 'q';
                                char enemy = board.getFromBoard(boardTag, r + i * steps, c + j * steps);
                                // temporarily update the board to check if king is still safe after the rook move
                                board.setBoard(boardTag, r, c, ' ');
                                board.setBoard(boardTag, r + i * steps, c + j * steps, ' ');
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch);
                                if (isKingSafe(player)) {
                                    moves.add(player + " moves Q from " + boardTag + " " +
                                            board.getCol()[c] + board.getRow()[r] + " to " +
                                            board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                                }
                                // undo the update
                                board.setBoard(boardTag, r, c, ch);
                                board.setBoard(boardTag, r + i * steps, c + j * steps, enemy);
                                board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    private void nextMoves_K(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? "white" : "black";

        // move around by 1 step
        for(int i = 0; i < 9; i++){
            if(i != 4){
                int r1 = r - 1 + i / 3;
                int c1 = c - 1 + i % 3;
                // check if the move is within the board
                if(r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8){
                    // check if the target position is empty or occupied by an enemy piece
                    if(board.getFromBoard(boardTag, r1, c1) == ' ' ||
                            player.equals("white") != Character.isUpperCase(board.getFromBoard(boardTag, r1, c1))){
                        // check if the corresponding position on the other board is empty
                        if(board.getFromBoard((boardTag * 2) % 3, r1, c1) == ' '){
                            char ch = player.equals("white") ? 'K' : 'k';
                            char enemy = board.getFromBoard(boardTag, r1, c1);
                            // Here we need two rounds of checks, first we need to check if the move is legal on the
                            // original board. Then we need to check if the move is also legal after the king was
                            // transferred to the other board

                            board.setBoard(boardTag, r, c, ' ');
                            board.setBoard(boardTag, r1, c1, ch);
                            if(isKingSafe(player)) {
                                // transfer the king to the other board
                                board.setBoard(boardTag, r1, c1, ' ');
                                board.setBoard((boardTag * 2) % 3, r1, c1, ch);
                                if(isKingSafe(player)){
                                    moves.add(player + " moves K from " + boardTag + " " +
                                            board.getCol()[c] + board.getRow()[r] + " to " +
                                            board.getCol()[c1] + board.getRow()[r1]);
                                }
                            }
                            // undo the update
                            board.setBoard(boardTag, r, c, ch);
                            board.setBoard(boardTag, r1, c1, enemy);
                            board.setBoard((boardTag * 2) % 3, r1, c1, ' ');
                        }
                    }
                }
            }
        }
    }

    /**
     * This function returns true if the king is safe in the current board state
     *
     */
    private boolean isKingSafe(String player) {
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
                if (board.getFromBoard(boardTag_K, r_K - 1, c_K - 1) == 'p' ||
                        board.getFromBoard(boardTag_K, r_K - 1, c_K + 1) == 'p') {
                    return false;
                }
            }
            catch (Exception e){}
        }
        else{
            try {
                if (board.getFromBoard(boardTag_k, r_k + 1, c_k - 1) == 'P' ||
                        board.getFromBoard(boardTag_k, r_k + 1, c_k + 1) == 'P') {
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

    /**
     * This is the Update function that simply updates the game state
     * 
     */
    public void update(String s) {
    	// From the input message, find out which board it is, the piece, the row and column
    	//  it has moved from and to.
        int boardTag = s.charAt(19) - '0';
        char ch = s.charAt(12);
        String color = s.substring(0, 5);
        if(color.equals("black")) ch = Character.toLowerCase(ch);
        int preCol = s.charAt(21) - 'a';
        int preRow = 8 - (s.charAt(22) - '0');
        int col = s.charAt(27) - 'a';
        int row = 8 - (s.charAt(28) - '0');

        // Just update the board.
        board.setBoard(boardTag, preRow, preCol, ' ');
        board.setBoard(boardTag, row, col, ' ');
        board.setBoard((boardTag * 2) % 3, row, col, ch);

//        printBoard();
    }

    private void printBoard() {
        for(int i = 0; i < 8; i++){
            for(char c : board.getBoardA()[i]){
                if(c == ' ') {
                    c = '-';
                }
                System.out.print(c + " ");
            }
            System.out.print("  ");
            for(char c : board.getBoardB()[i]){
                if(c == ' ') {
                    c = '-';
                }
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }


    /**
     * This function picks the best move for the current player,
     * where white is the maxPlayer and black the minPlayer
     *
     */
    public String pickBestMove(boolean isMaxPlayer) {
        final int DEPTH = 4;
        return miniMax(DEPTH, isMaxPlayer);
    }

    private String miniMax(int depth, boolean isMaxPlayer){
        String player = isMaxPlayer? "white" : "black";
        String bestMove = player + " surrenders";
        int highValue = Integer.MIN_VALUE;
        int lowValue = Integer.MAX_VALUE;
        int currentValue;

        List<String> nextMoves = isMaxPlayer ? nextWhiteMoves() : nextBlackMoves();

        for(String s : nextMoves){
            int boardTag = s.charAt(19) - '0';
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals("black")) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - 'a';
            int preRow = 8 - (s.charAt(22) - '0');
            int col = s.charAt(27) - 'a';
            int row = 8 - (s.charAt(28) - '0');
            char preChar = board.getFromBoard(boardTag, row, col);
            //update board
            board.setBoard(boardTag, preRow, preCol, ' ');
            board.setBoard(boardTag, row, col, ' ');
            board.setBoard((boardTag * 2) % 3, row, col, ch);

            currentValue = isMaxPlayer ? max(depth - 1) : min(depth - 1);
            if(isMaxPlayer && currentValue > highValue){
                highValue = currentValue;
                bestMove = s;
            }
            else if(!isMaxPlayer && currentValue < lowValue){
                lowValue = currentValue;
                bestMove = s;
            }

            //undoUpdate
            board.setBoard(boardTag, preRow, preCol, ch);
            board.setBoard(boardTag, row, col, preChar);
            board.setBoard((boardTag * 2) % 3, row, col, ' ');

        }
        return bestMove;
    }

    private int max(int depth) {
        if (depth == 0) {
            return evaluate();
        }

        int bestValue = Integer.MIN_VALUE;
        List<String> nextWhiteMoves = nextWhiteMoves();
        if (nextWhiteMoves.size() == 0) return bestValue;
        for (String s : nextWhiteMoves) {
            int boardTag = s.charAt(19) - '0';
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals("black")) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - 'a';
            int preRow = 8 - (s.charAt(22) - '0');
            int col = s.charAt(27) - 'a';
            int row = 8 - (s.charAt(28) - '0');
            char preChar = board.getFromBoard(boardTag, row, col);
            //update board
            board.setBoard(boardTag, preRow, preCol, ' ');
            board.setBoard(boardTag, row, col, ' ');
            board.setBoard((boardTag * 2) % 3, row, col, ch);

            int value = min(depth - 1);
            bestValue = Math.max(bestValue, value);

            //undoUpdate
            board.setBoard(boardTag, preRow, preCol, ch);
            board.setBoard(boardTag, row, col, preChar);
            board.setBoard((boardTag * 2) % 3, row, col, ' ');
        }
        return bestValue;
    }

    private int min(int depth) {
        if (depth == 0) {
            return evaluate();
        }

        int bestValue = Integer.MAX_VALUE;
        List<String> nextBlackMoves = nextBlackMoves();
        if(nextBlackMoves.size() == 0) return bestValue;
        for(String s : nextBlackMoves){
            int boardTag = s.charAt(19) - '0';
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals("black")) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - 'a';
            int preRow = 8 - (s.charAt(22) - '0');
            int col = s.charAt(27) - 'a';
            int row = 8 - (s.charAt(28) - '0');
            char preChar = board.getFromBoard(boardTag, row, col);
            //update board
            board.setBoard(boardTag, preRow, preCol, ' ');
            board.setBoard(boardTag, row, col, ' ');
            board.setBoard((boardTag * 2) % 3, row, col, ch);

            int value = max(depth - 1);
            bestValue = Math.min(bestValue, value);

            //undoUpdate
            board.setBoard(boardTag, preRow, preCol, ch);
            board.setBoard(boardTag, row, col, preChar);
            board.setBoard((boardTag * 2) % 3, row, col, ' ');
        }
        return bestValue;
    }

    /**
     * This function evaluate the current board and return a score which is calculated by considering
     * the pieces, check, move ability, and ...
     *
     */
    private int evaluate(){
        // scores of white and black
        int sumWhite = 0;
        int sumBlack = 0;

        // add the weight of active pieces to score
        for(int i = 0; i < 64; i++){
            switch (board.getBoardA()[i / 8][i % 8]){
                case 'P' : sumWhite += 1;
                    break;
                case 'R' : sumWhite += 5;
                    break;
                case 'N' : sumWhite += 3;
                    break;
                case 'B' : sumWhite += 4;
                    break;
                case 'Q' : sumWhite += 10;
                    break;
                case 'K' : sumWhite += 0;
                    break;
                case 'p' : sumBlack += 1;
                    break;
                case 'r' : sumBlack += 5;
                    break;
                case 'n' : sumBlack += 3;
                    break;
                case 'b' : sumBlack += 4;
                    break;
                case 'q' : sumBlack += 10;
                    break;
                case 'k' : sumBlack += 0;
                    break;
            }
        }

        for(int i = 0; i < 64; i++){
            switch (board.getBoardB()[i / 8][i % 8]){
                case 'P' : sumWhite += 1;
                    break;
                case 'R' : sumWhite += 5;
                    break;
                case 'N' : sumWhite += 3;
                    break;
                case 'B' : sumWhite += 4;
                    break;
                case 'Q' : sumWhite += 10;
                    break;
                case 'K' : sumWhite += 0;
                    break;
                case 'p' : sumBlack += 1;
                    break;
                case 'r' : sumBlack += 5;
                    break;
                case 'n' : sumBlack += 3;
                    break;
                case 'b' : sumBlack += 4;
                    break;
                case 'q' : sumBlack += 10;
                    break;
                case 'k' : sumBlack += 0;
                    break;
            }
        }

        // add points for check
        if(!isKingSafe("white")) sumBlack += 2;
        if(!isKingSafe("black")) sumWhite += 2;

        // add points for move ability
//        if(isMaxPlayer){
//            sumWhite += (20 - nextBlackMoves().size());
//        }
//        if(!isMaxPlayer){
//            sumBlack -= (20 - nextWhiteMoves().size());
//        }

        return sumWhite - sumBlack;
    }
}
