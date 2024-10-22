package com.connect4;

import java.util.Scanner;

import com.connect4.game.GameRunner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameRunner gameRunner = new GameRunner(scanner);
        gameRunner.runGame();
        scanner.close();
    }
}
