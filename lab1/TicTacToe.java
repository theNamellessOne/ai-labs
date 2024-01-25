package lab1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicTacToe {
    private final List<Character> board;
    private char currentPlayer;
    private char winner;

    public TicTacToe() {
        board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            board.add(' ');
        }

        currentPlayer = 'X';
        winner = '\0';
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);

        while (getWinner() == '\0') {
            printBoard();

            if (getCurrentPlayer() == 'X') {
                System.out.print("Enter your move (1-9): ");
                int move = scanner.nextInt() - 1;
                if (move < 0 || move >= 9 || makeMove(move)) {
                    System.out.println("Invalid move. Please enter a valid, unoccupied position.");
                    continue;
                }
            } else {
                int move = getBestMove();
                System.out.println("Computer chooses position " + (move + 1));
                makeMove(move);
            }
        }

        printBoard();
        if (getWinner() == 'T') {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Player " + getWinner() + " wins!");
        }
    }

    private void printBoard() {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(board.get(i) + " | " + board.get(i + 1) + " | " + board.get(i + 2));
        }
    }

    private boolean isWinner(char player) {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
                {0, 4, 8}, {2, 4, 6}              // Diagonals
        };

        for (int[] combination : winningCombinations) {
            if (board.get(combination[0]) == player
                    && board.get(combination[1]) == player
                    && board.get(combination[2]) == player) {
                return true;
            }
        }

        return false;
    }

    List<Integer> getEmptyPositions() {
        return IntStream.range(0, 9)
                .filter(i -> board.get(i) == ' ')
                .boxed()
                .collect(Collectors.toList());
    }

    private boolean isBoardFull() {
        return board.stream().noneMatch(cell -> cell == ' ');
    }

    private boolean makeMove(int position) {
        if (board.get(position) != ' ') {
            return false;
        }

        board.set(position, currentPlayer);
        if (isWinner(currentPlayer)) {
            winner = currentPlayer;
            return true;
        }

        if (isBoardFull()) {
            winner = 'T';
            return true;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return true;
    }

    public int minimax(int depth, int alpha, int beta, boolean humanTurn) {
        if (isWinner('X')) return -1;
        if (isWinner('O')) return 1;
        if (isBoardFull()) return 0;

        List<Integer> emptyPositions = getEmptyPositions();
        if (humanTurn) {
            return maxEval(depth, alpha, beta, emptyPositions);
        } else {
            return minEval(depth, alpha, beta, emptyPositions);
        }
    }

    private int minEval(int depth, int alpha, int beta, List<Integer> emptyPositions) {
        int minEval = Integer.MAX_VALUE;
        for (int i : emptyPositions) {
            board.set(i, 'X');
            int eval = minimax(depth + 1, alpha, beta, true);
            board.set(i, ' ');
            minEval = Math.min(minEval, eval);
            beta = Math.min(beta, eval);
            if (beta <= alpha) {
                return minEval;
            }
        }
        return minEval;
    }

    private int maxEval(int depth, int alpha, int beta, List<Integer> emptyPositions) {
        int maxEval = Integer.MIN_VALUE;
        for (int i : emptyPositions) {
            board.set(i, 'O');
            int eval = minimax(depth + 1, alpha, beta, false);
            board.set(i, ' ');
            maxEval = Math.max(maxEval, eval);
            alpha = Math.max(alpha, eval);
            if (beta <= alpha) {
                return maxEval;
            }
        }
        return maxEval;
    }

    public int getBestMove() {
        int bestMove = -1;
        int bestEval = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (int i : getEmptyPositions()) {
            board.set(i, 'O');
            int eval = minimax(0, alpha, beta, false);
            board.set(i, ' ');
            if (eval > bestEval) {
                bestEval = eval;
                bestMove = i;
            }
            alpha = Math.max(alpha, eval);
        }
        return bestMove;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getWinner() {
        return winner;
    }
}
