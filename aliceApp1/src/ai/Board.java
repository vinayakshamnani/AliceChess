package AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Created by Chang on 10/14/2016.
 */
public class Board {

    private static char[] row = {'8', '7', '6', '5', '4', '3', '2', '1'};
    private static char[] col = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    private static char[][] boardA = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
    };

    private static char[][] boardB = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };

    public static List<String> nextWhiteMoves(){
        List<String> moves = new ArrayList<>();

        for(int i = 0; i < 64; i++){
            switch (boardA[i / 8][i % 8]){
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
            switch (boardB[i / 8][i % 8]){
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

    public static List<String> nextBlackMoves(){
        List<String> moves = new ArrayList<>();

        for(int i = 0; i < 64; i++){
            switch (boardA[i / 8][i % 8]){
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
            switch (boardB[i / 8][i % 8]){
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

    private static void nextMoves_P(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            //P is at the initial position
            if(row[r] == '2'){
                //move forward by 1 step
                if(boardA[r - 1][c] == ' ' && boardB[r - 1][c] == ' '){
                    moves.add("white moves P from 1 " + col[c] + row[r] + " to " + col[c] + row[r - 1]);
                }
                //move by 2 steps
                if(boardA[r - 2][c] == ' ' && boardB[r - 2][c] == ' '){
                    moves.add("white moves P from 1 " + col[c] + row[r] + " to " + col[c] + row[r - 2]);
                }
            }

            else{
                //TODO: en passant
                //TODO: promotion
                //TODO: catch enemy piece

                //move forward by 1 step
                if(boardA[r - 1][c] == ' ' && boardB[r - 1][c] == ' '){
                    moves.add("white moves P from 1 " + col[c] + row[r] + " to " + col[c] + row[r - 1]);
                }

            }
        }

        else{
            //TODO: en passant
            //TODO: promotion
            //TODO: catch enemy piece

            //move forward by 1 step
            if(boardB[r - 1][c] == ' ' && boardA[r - 1][c] == ' '){
                moves.add("white moves P from 2 " + col[c] + row[r] + " to " + col[c] + row[r - 1]);
            }
        }


    }

    private static void nextMoves_p(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            //p is at the initial position
            if(row[r] == '7'){
                //move forward by 1 step
                if(boardA[r + 1][c] == ' ' && boardB[r + 1][c] == ' '){
                    moves.add("black moves P from 1 " + col[c] + row[r] + " to " + col[c] + row[r + 1]);
                }
                //move by 2 steps
                if(boardA[r + 2][c] == ' ' && boardB[r + 2][c] == ' '){
                    moves.add("black moves P from 1 " + col[c] + row[r] + " to " + col[c] + row[r + 2]);
                }
                //TODO: catch enemy piece
            }

            else{
                //TODO: en passant
                //TODO: promotion
                //TODO: catch enemy piece

                //move forward by 1 step
                if(boardA[r + 1][c] == ' ' && boardB[r + 1][c] == ' '){
                    moves.add("black moves P from 1 " + col[c] + row[r] + " to " + col[c] + row[r + 1]);
                }

            }
        }

        else{
            //TODO: promotion
            //TODO: catch enemy piece

            //move forward by 1 step
            if(boardB[r + 1][c] == ' ' && boardA[r + 1][c] == ' '){
                moves.add("black moves P from 2 " + col[c] + row[r] + " to " + col[c] + row[r + 1]);
            }
        }


    }

    private static void nextMoves_R(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            //TODO: castling

            String player = Character.isUpperCase(boardA[r][c]) ? "white" : "black";
            //move left
            for(int i = c - 1; i >= 0; i--){
                if(boardA[r][i] == ' '){
                    if(boardB[r][i] == ' '){
                        moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[i] + row[r]);
                    }
                }
                else if(Character.isUpperCase(boardA[r][i])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[i] + row[r]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[i] + row[r]);
                        }
                        break;
                    }
                }
            }
            //move right
            for(int i = c + 1; i < 8; i++){
                if(boardA[r][i] == ' '){
                    if(boardB[r][i] == ' '){
                        moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[i] + row[r]);
                    }
                }
                else if(Character.isUpperCase(boardA[r][i])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[i] + row[r]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[r][i] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[i] + row[r]);
                        }
                        break;
                    }
                }
            }
            //move up
            for(int i = r - 1; i >= 0; i--){
                if(boardA[i][c] == ' '){
                    if(boardB[i][c] == ' '){
                        moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[c] + row[i]);
                    }
                }
                else if(Character.isUpperCase(boardA[i][c])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
            }
            //move down
            for(int i = r + 1; i < 8; i++){
                if(boardA[i][c] == ' '){
                    if(boardB[i][c] == ' '){
                        moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[c] + row[i]);
                    }
                }
                else if(Character.isUpperCase(boardA[i][c])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardB[i][c] == ' ') {
                            moves.add(player + " moves R from 1 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
            }
        }

        else{
            String player = Character.isUpperCase(boardB[r][c]) ? "white" : "black";
            //move left
            for(int i = c - 1; i >= 0; i--){
                if(boardB[r][i] == ' '){
                    if(boardA[r][i] == ' '){
                        moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[i] + row[i]);
                    }
                }
                else if(Character.isUpperCase(boardB[r][i])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(boardA[r][i] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[i] + row[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardA[r][i] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[i] + row[i]);
                        }
                        break;
                    }
                }
            }
            //move right
            for(int i = c + 1; i < 8; i++){
                if(boardB[r][i] == ' '){
                    if(boardA[r][i] == ' '){
                        moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[i] + row[i]);
                    }
                }
                else if(Character.isUpperCase(boardB[r][i])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(boardA[r][i] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[i] + row[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardA[r][i] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[i] + row[i]);
                        }
                        break;
                    }
                }
            }
            //move up
            for(int i = r - 1; i >= 0; i--){
                if(boardB[i][c] == ' '){
                    if(boardA[i][c] == ' '){
                        moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[c] + row[i]);
                    }
                }
                else if(Character.isUpperCase(boardB[r][i])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                    //can catch the enemy piece
                    else{
                        if(boardA[i][c] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardA[i][c] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
            }
            //move down
            for(int i = r + 1; i < 8; i++){
                if(boardB[i][c] == ' '){
                    if(boardA[i][c] == ' '){
                        moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[c] + row[i]);
                    }
                }
                else if(Character.isUpperCase(boardB[r][i])){
                    //can not pass the same color piece
                    if(player.equals("white")) break;
                        //can catch the enemy piece
                    else{
                        if(boardA[i][c] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
                else{
                    //can not pass the same color piece
                    if(player.equals("black")) break;
                    //can catch the enemy piece
                    else{
                        if(boardA[i][c] == ' ') {
                            moves.add(player + " moves R from 2 " + col[c] + row[r] + " to " + col[c] + row[i]);
                        }
                        break;
                    }
                }
            }
        }

    }

    private static void nextMoves_N(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        List<List<Integer>> candidates = new ArrayList<>();
        candidates.add(Arrays.asList(r + 1, c + 2));
        candidates.add(Arrays.asList(r + 2, c + 1));
        candidates.add(Arrays.asList(r + 2, c - 1));
        candidates.add(Arrays.asList(r + 1, c - 2));
        candidates.add(Arrays.asList(r - 1, c - 2));
        candidates.add(Arrays.asList(r - 2, c - 1));
        candidates.add(Arrays.asList(r - 2, c + 1));
        candidates.add(Arrays.asList(r - 1, c + 2));

        if(boardTag == 1){
            String player = Character.isUpperCase(boardA[r][c]) ? "white" : "black";

            for(List<Integer> list : candidates){
                // check if the target place is within the board
                if(list.get(0) >= 0 && list.get(0) < 8 && list.get(1) >= 0 && list.get(1) < 8){
                    // check if the target place is empty or occupied by enemy piece
                    if(boardA[list.get(0)][list.get(1)] == ' ' ||
                            Character.isUpperCase(boardA[list.get(0)][list.get(1)]) != Character.isUpperCase(boardA[r][c])){
                        // check if the corresponding place on the other board is empty
                        if(boardB[list.get(0)][list.get(1)] == ' '){
                            moves.add(player + " moves N from 1 " + col[c] + row[r] + " to " + col[list.get(1)] + row[list.get(0)]);
                        }
                    }
                }
            }

        }

        else{
            String player = Character.isUpperCase(boardB[r][c]) ? "white" : "black";

            for(List<Integer> list : candidates){
                // check if the target place is within the board
                if(list.get(0) >= 0 && list.get(0) < 8 && list.get(1) >= 0 && list.get(1) < 8){
                    // check if the target place is empty or occupied by enemy piece
                    if(boardB[list.get(0)][list.get(1)] == ' ' ||
                            Character.isUpperCase(boardB[list.get(0)][list.get(1)]) != Character.isUpperCase(boardB[r][c])){
                        // check if the corresponding place on the other board is empty
                        if(boardA[list.get(0)][list.get(1)] == ' '){
                            moves.add(player + " moves N from 2 " + col[c] + row[r] + " to " + col[list.get(1)] + row[list.get(0)]);
                        }
                    }
                }
            }
        }
    }

    private static void nextMoves_B(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            String player = Character.isUpperCase(boardA[r][c]) ? "white" : "black";

            // move southeast
            for(int i = r + 1, j = c + 1; i < 8 && j < 8; i++, j++){
                if(boardA[i][j] == ' '){
                    if(boardB[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardA[i][j]) != Character.isUpperCase(boardA[r][c])){
                        if(boardB[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

            // move southwest
            for(int i = r + 1, j = c - 1; i < 8 && j >= 0; i++, j--){
                if(boardA[i][j] == ' '){
                    if(boardB[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardA[i][j]) != Character.isUpperCase(boardA[r][c])){
                        if(boardB[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

            // move northwest
            for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--){
                if(boardA[i][j] == ' '){
                    if(boardB[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardA[i][j]) != Character.isUpperCase(boardA[r][c])){
                        if(boardB[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

            // move northeast
            for(int i = r - 1, j = c + 1; i >= 0 && j < 8; i--, j++){
                if(boardA[i][j] == ' '){
                    if(boardB[i][j] == ' ') {
                        moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardA[i][j]) != Character.isUpperCase(boardA[r][c])){
                        if(boardB[i][j] == ' ') {
                            moves.add(player + " moves B from 1 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

        }

        else{
            String player = Character.isUpperCase(boardB[r][c]) ? "white" : "black";

            // move southeast
            for(int i = r + 1, j = c + 1; i < 8 && j < 8; i++, j++){
                if(boardB[i][j] == ' '){
                    if(boardA[i][j] == ' ') {
                        moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardB[i][j]) != Character.isUpperCase(boardB[r][c])){
                        if(boardA[i][j] == ' ') {
                            moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

            // move southwest
            for(int i = r + 1, j = c - 1; i < 8 && j >= 0; i++, j--){
                if(boardB[i][j] == ' '){
                    if(boardA[i][j] == ' ') {
                        moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardB[i][j]) != Character.isUpperCase(boardB[r][c])){
                        if(boardA[i][j] == ' ') {
                            moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

            // move northwest
            for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--){
                if(boardB[i][j] == ' '){
                    if(boardA[i][j] == ' ') {
                        moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardB[i][j]) != Character.isUpperCase(boardB[r][c])){
                        if(boardA[i][j] == ' ') {
                            moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

            // move northeast
            for(int i = r - 1, j = c + 1; i >= 0 && j < 8; i--, j++){
                if(boardB[i][j] == ' '){
                    if(boardA[i][j] == ' ') {
                        moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                    }
                }
                else{
                    if(Character.isUpperCase(boardB[i][j]) != Character.isUpperCase(boardB[r][c])){
                        if(boardA[i][j] == ' ') {
                            moves.add(player + " moves B from 2 " + col[c] + row[r] + " to " + col[j] + row[i]);
                        }
                    }
                    break;
                }
            }

        }
    }

    private static void nextMoves_Q(int boardTag, int pos, List<String> moves){
        List<String> Q_moves = new ArrayList<>();
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

    private static void nextMoves_K(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;

        if(boardTag == 1){
            String player = Character.isUpperCase(boardA[r][c]) ? "white" : "black";
            //TODO: castling

            // move around by 1 step
            for(int i = 0; i < 9; i++){
                if(i != 4){
                    int r1 = r - 1 + i / 3;
                    int c1 = c - 1 + i % 3;
                    if(r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8){
                        if(boardA[r1][c1] == ' '){
                            if(boardB[r1][c1] == ' '){
                                moves.add(player + " moves K from 1 " + col[c] + row[r] + " to " + col[c1] + row[r1]);
                            }
                        }
                        else{
                            if(Character.isUpperCase(boardA[r1][c1]) != Character.isUpperCase(boardA[r][c])){
                                if(boardB[r1][c1] == ' '){
                                    moves.add(player + " moves K from 1 " + col[c] + row[r] + " to " + col[c1] + row[r1]);
                                }
                            }
                        }
                    }
                }
            }
        }

        else{
            String player = Character.isUpperCase(boardB[r][c]) ? "white" : "black";

            // move around by 1 step
            for(int i = 0; i < 9; i++){
                if(i != 4){
                    int r1 = r - 1 + i / 3;
                    int c1 = c - 1 + i % 3;
                    if(r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8){
                        if(boardB[r1][c1] == ' '){
                            if(boardA[r1][c1] == ' '){
                                moves.add(player + " moves K from 1 " + col[c] + row[r] + " to " + col[c1] + row[r1]);
                            }
                        }
                        else{
                            if(Character.isUpperCase(boardA[r1][c1]) != Character.isUpperCase(boardA[r][c])){
                                if(boardA[r1][c1] == ' '){
                                    moves.add(player + " moves K from 1 " + col[c] + row[r] + " to " + col[c1] + row[r1]);
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public static void update(String s){
//        String player = s.substring(0, 5);
//        char piece = s.charAt(12);
        char boardTag = s.charAt(19);
        int preCol = s.charAt(21) - 'a';
        int preRow = 8 - (s.charAt(22) - '0');
        int col = s.charAt(27) - 'a';
        int row = 8 - (s.charAt(28) - '0');

        if(boardTag == '1'){
            boardB[row][col] = boardA[preRow][preCol];
            boardA[preRow][preCol] = ' ';
        }
        else{
            boardA[row][col] = boardB[preRow][preCol];
            boardB[preRow][preCol] = ' ';
        }

        //print the board for debug
//        for(char[] cs : boardA){
//            for(char c : cs){
//                System.out.print(c + " ");
//            }
//            System.out.println();
//        }
//        for(char[] cs : boardB){
//            for(char c : cs){
//                System.out.print(c + " ");
//            }
//            System.out.println();
//        }

    }
}
