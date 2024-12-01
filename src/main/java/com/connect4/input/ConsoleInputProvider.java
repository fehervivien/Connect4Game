package com.connect4.input;
import java.io.InputStream;
import java.util.Scanner;


/*
 * Az osztály lehetővé teszi, hogy a felhasználói bemeneteket 
 * a konzolon keresztül gyűjtse össze, és ezáltal interaktívvá
 * tegye a játékot. 
 * Az osztály feladata a felhasználói élmény javítása azáltal, 
 * hogy könnyen kezelhető API-t biztosít a bemenetek beolvasásához.
 */
 
 public class ConsoleInputProvider implements InputProvider {
     private Scanner scanner;
 
     /*
     * Konstruktor: Inicializálja a Scanner objektumot a 
     * megadott InputStream segítségével.
     * @param input Az InputStream, amelyből a bemeneteket 
     * olvassuk.
     */
     public ConsoleInputProvider(InputStream input) {
         this.scanner = new Scanner(input);
     }
 
    /*
     * Bemenet olvasása a konzolról.
     * @return A felhasználó által beírt sor.
     * Könnyen kezelhető API-t biztosít a bemenetek
     * beolvasásához.
    */
     @Override
     public String nextLine() {
         return scanner.nextLine();
     }
 }
