package app.nvdpl.librarymanager;

import java.util.HashMap;
import java.util.Map;

public class BookUser {
    Map<String, Book> ReadBookMap, BorrowedBookMap;
    Map<String, Movie> ReadMovieMap, BorrowedMovieMap;
    Map<String, AudioBook> ReadAudioBookMap, BorrowedAudioBookMap;

    public BookUser(){
        ReadBookMap = new HashMap<>();
        BorrowedBookMap = new HashMap<>();
        ReadMovieMap = new HashMap<>();
        BorrowedMovieMap = new HashMap<>();
        ReadAudioBookMap = new HashMap<>();
        BorrowedAudioBookMap = new HashMap<>();
    }

}
