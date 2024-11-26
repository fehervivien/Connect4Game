package com.connect4.input;
import java.io.InputStream;
import java.util.Scanner;


/*
 * Lehetővé teszi, hogy a felhasználói bemeneteket a konzolon
 * keresztül gyűjtsük össze, és ezáltal interaktívvá tegyük a játékot. 
 * Az osztály feladata a felhasználói élmény javítása azáltal, hogy könnyen kezelhető API-t biztosít a bemenetek beolvasásához.
 */
 
 public class ConsoleInputProvider implements InputProvider {
     private Scanner scanner;
 
     public ConsoleInputProvider(InputStream input) {
         this.scanner = new Scanner(input);
     }
 
     @Override
     public String nextLine() {
         return scanner.nextLine();
     }
 }
