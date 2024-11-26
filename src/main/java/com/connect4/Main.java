package com.connect4;
import com.connect4.input.ConsoleInputProvider;
import com.connect4.game.GameRunner;

public class Main {
    public static void main(String[] args) {
        ConsoleInputProvider inputProvider = new ConsoleInputProvider(System.in);
        //A játék indítása a GameRunner osztályból
        GameRunner gameRunner = new GameRunner(inputProvider);
        gameRunner.runGame();
    }
}
