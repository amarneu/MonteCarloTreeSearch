package edu.neu.coe.info6205.mcts.connectFour;

import java.util.Random;
public class Game {
    private Board board;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player activePlayer;
    private Random rng = new Random();

    public Game() {
        initializeGame();
    }

    private void initializeGame() {
        board = new Board();
        firstPlayer = new Player('A');
        secondPlayer = new Player('O');
        chooseStartingPlayer();
    }

    private void chooseStartingPlayer() {
        activePlayer = rng.nextBoolean() ? firstPlayer : secondPlayer;
        System.out.println("Player " + activePlayer.getToken() + " starts the game");
    }

    public void start() {
        boolean isGameOver = false;
        while (!isGameOver) {
            isGameOver = takeTurn();
        }
    }

    boolean takeTurn() {
        board.displayBoard();
        int columnChoice = rng.nextInt(7); // Change this if the number of columns changes
        if (!board.placeToken(columnChoice, activePlayer.getToken())) {
            System.out.println("Column " + columnChoice + " is full, try again.");
            return false;
        }

        System.out.println("Player " + activePlayer.getToken() + " placed a token in column " + columnChoice);

        if (board.checkVictory(activePlayer.getToken())) {
            board.displayBoard();
            System.out.println("Player " + activePlayer.getToken() + " wins!");
            return true;
        } else if (board.isGridFull()) {
            board.displayBoard();
            System.out.println("The game is a draw!");
            return true;
        }

        switchActivePlayer();
        return false;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Random getRng() {
        return rng;
    }

    public void setRng(Random rng) {
        this.rng = rng;
    }

    void switchActivePlayer() {
        activePlayer = (activePlayer == firstPlayer) ? secondPlayer : firstPlayer;
    }
    public Board getBoard() {
        return board;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

}