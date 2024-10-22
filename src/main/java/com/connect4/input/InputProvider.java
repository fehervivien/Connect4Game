package com.connect4.input;

/*
 * Segít elválasztani a bemenet kezelésének logikáját
 * a többi játéknak kapcsolódó kódtól, lehetővé téve, 
 * hogy később más bemeneti forrásokat is könnyen integráljunk 
 * (pl. fájlokból, hálózaton keresztül).
 * pl. GameRunner osztályban van használatban
 */

public interface InputProvider {
    String nextLine();

    int nextInt();
}