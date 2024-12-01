package com.connect4.input;


/**
 * Az InputProvider *interfész definiálja a bemenetek 
 * beolvasásához szükséges API-t.
 * Az interfész célja, hogy egy általános szerződést 
 * biztosítson a bemenetek kezelésére,
 * amelyet különböző osztályok implementálhatnak.
 */
public interface InputProvider {
    String nextLine();
}