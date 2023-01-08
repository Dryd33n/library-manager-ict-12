package app.nvdpl.librarymanager;

import java.util.HashMap;
import java.util.Map;

public class BookUser{
    String username;
    protected HashMap<String, Book> ReadBookMap, BorrowedBookMap;
    protected HashMap<String, Movie> ReadMovieMap, BorrowedMovieMap;
    protected Map<String, AudioBook> ReadAudioBookMap, BorrowedAudioBookMap;

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
        if(getNumOfBorrowedItems() >= 10) return false;

        if(borrowing) BorrowedBookMap.put(bookToRent.title, bookToRent);
        else BorrowedBookMap.remove(bookToRent.title);
        return true;
    }

    public boolean borrowItem(Movie movieToRent, Boolean borrowing){
        if(getNumOfBorrowedItems() >= 10) return false;

        if(borrowing) BorrowedMovieMap.put(movieToRent.title, movieToRent);
        else BorrowedBookMap.remove(movieToRent.title);
        return true;
    }

    public boolean borrowItem(AudioBook audiobookToRent, Boolean borrowing){
        if(getNumOfBorrowedItems() >= 10) return false;

        if(borrowing) BorrowedAudioBookMap.put(audiobookToRent.title, audiobookToRent);
        else BorrowedBookMap.remove(audiobookToRent.title);
        return true;
    }

    public boolean markItemAsRead(Book readBook, Boolean read){
        if(!read || !ReadBookMap.containsKey(readBook.title)) return false;

        if(read) ReadBookMap.put(readBook.title, readBook);
        else ReadBookMap.remove(readBook.title);

        return true;
    }

    public boolean markItemAsRead(Movie watchedMovie, Boolean read){
        if(!read || !ReadMovieMap.containsKey(watchedMovie.title)) return false;

        if(read) ReadMovieMap.put(watchedMovie.title, watchedMovie);
        else ReadMovieMap.remove(watchedMovie.title);

        return true;
    }

    public boolean markItemAsRead(AudioBook heardBook, Boolean read){
        if(!read || !ReadAudioBookMap.containsKey(heardBook.title)) return false;

        if(read) ReadAudioBookMap.put(heardBook.title, heardBook);
        else ReadAudioBookMap.remove(heardBook.title);

        return true;
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
