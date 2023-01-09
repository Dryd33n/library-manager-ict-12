/*
BookUser Class:

This class is used to hold information about each user in the application
This class holds information about what books are being borrowed and which books
have been read
 */

package app.nvdpl.librarymanager;

import java.util.HashMap;

public class BookUser{
    String username;
    protected HashMap<String, Book> ReadBookMap, BorrowedBookMap;
    protected HashMap<String, Movie> ReadMovieMap, BorrowedMovieMap;
    protected HashMap<String, AudioBook> ReadAudioBookMap, BorrowedAudioBookMap;

    public BookUser(String username){
        ReadBookMap = new HashMap<>();
        BorrowedBookMap = new HashMap<>();
        ReadMovieMap = new HashMap<>();
        BorrowedMovieMap = new HashMap<>();
        ReadAudioBookMap = new HashMap<>();
        BorrowedAudioBookMap = new HashMap<>();

        this.username = username;
    }

    public boolean borrowItem(Book bookToRent, Boolean borrowing){

        if(borrowing) BorrowedBookMap.put(bookToRent.title, bookToRent);
        else BorrowedBookMap.remove(bookToRent.title, bookToRent);
        return true;
    }

    public boolean borrowItem(Movie movieToRent, Boolean borrowing){

        if(borrowing) BorrowedMovieMap.put(movieToRent.title, movieToRent);
        else BorrowedMovieMap.remove(movieToRent.title, movieToRent);
        return true;
    }

    public boolean borrowItem(AudioBook audiobookToRent, Boolean borrowing){

        if(borrowing) BorrowedAudioBookMap.put(audiobookToRent.title, audiobookToRent);
        else BorrowedAudioBookMap.remove(audiobookToRent.title, audiobookToRent);
        return true;
    }

    public boolean markItemAsRead(Book readBook, Boolean read){
        if(!read && !ReadBookMap.containsKey(readBook.title)) return false;

        if(read) ReadBookMap.put(readBook.title, readBook);
        else ReadBookMap.remove(readBook.title);

        return true;
    }

    public boolean markItemAsRead(Movie watchedMovie, Boolean read){
        if(!read && !ReadMovieMap.containsKey(watchedMovie.title)) return false;

        if(read) ReadMovieMap.put(watchedMovie.title, watchedMovie);
        else ReadMovieMap.remove(watchedMovie.title);

        return true;
    }

    public boolean markItemAsRead(AudioBook heardBook, Boolean read){
        if(!read && !ReadAudioBookMap.containsKey(heardBook.title)) return false;

        if(read) ReadAudioBookMap.put(heardBook.title, heardBook);
        else ReadAudioBookMap.remove(heardBook.title);

        return true;
    }

    public boolean isBorrowed(Book book){
        return BorrowedBookMap.containsKey(book.title);
    }

    public boolean isBorrowed(Movie movie){
        return BorrowedMovieMap.containsKey(movie.title);
    }

    public boolean isBorrowed(AudioBook audiobook){
        return BorrowedAudioBookMap.containsKey(audiobook.title);
    }

    public boolean isRead(Book book){
        return ReadBookMap.containsKey(book.title);
    }

    public boolean isRead(Movie movie){
        return ReadMovieMap.containsKey(movie.title);
    }

    public boolean isRead(AudioBook audiobook){
        return ReadAudioBookMap.containsKey(audiobook.title);
    }

    public String[] getUserData(){
        return new String[]{
                "Username: "+username,
                "Books Read: " +ReadBookMap.size()+"\n\n"+
                "Movies Watched: "+ReadMovieMap.size()+"\n\n"+
                "Audiobooks Listened: "+ReadAudioBookMap.size()+"\n\n",
                "Books Borrowing: "+BorrowedBookMap.size()+"\n\n"+
                "Movies Borrowing: "+BorrowedMovieMap.size()+"\n\n"+
                "Audiobooks Borrowing: "+BorrowedAudioBookMap.size()+"\n\n"
        };
    }

    public int getNumOfBorrowedItems(){
        return BorrowedBookMap.size() + BorrowedMovieMap.size() + BorrowedAudioBookMap.size();
    }

}
