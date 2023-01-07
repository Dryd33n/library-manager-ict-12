package app.nvdpl.librarymanager;

import javafx.collections.ObservableList;
import org.json.JSONArray;

import java.util.ArrayList;

public class LibraryInventory {

    private ArrayList<Book> bookInventory;
    private ArrayList<Movie> movieInventory;
    private ArrayList<AudioBook> audiobookInventory;


    public LibraryInventory(){
        String bookPath = "src/main/resources/app/nvdpl/librarymanager/books.json";
        String moviePath = "src/main/resources/app/nvdpl/librarymanager/movies.json";
        String audiobookPath = "src/main/resources/app/nvdpl/librarymanager/audiobooks.json";

        bookInventory = processBookJsonArray(Util.pathStringToJsonArray(bookPath));
        movieInventory= processMovieJsonArray(Util.pathStringToJsonArray(moviePath));
        audiobookInventory = processAudioBookJsonArray(Util.pathStringToJsonArray(audiobookPath));
    }

    private ArrayList<Book> processBookJsonArray(JSONArray bookJsonArray){
        ArrayList<Book> result = new ArrayList<>();

        for (int i = 0; i < bookJsonArray.length(); i++) {
            result.add(new Book(bookJsonArray.getJSONObject(i)));
        }

        return result;
    }

    private ArrayList<Movie> processMovieJsonArray(JSONArray movieJsonArray){
        ArrayList<Movie> result = new ArrayList<>();

        for (int i = 0; i < movieJsonArray.length(); i++) {
            result.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return result;
    }

    private ArrayList<AudioBook> processAudioBookJsonArray(JSONArray audio){
        ArrayList<AudioBook> result = new ArrayList<>();

        for (int i = 0; i < audio.length(); i++) {
            result.add(new AudioBook(audio.getJSONObject(i)));
        }

        return result;
    }

    public ObservableList<String> getBookObservableList(){
        return Util.arrayListToStringifiedObservableList(bookInventory);
    }

    public ObservableList<String> getBookObservableList(String searchTerm, Boolean seenToggle, Boolean borrowedToggle){
        ArrayList<Book> resultList = new ArrayList<>();
        ArrayList<Book> resultList2 = new ArrayList<>();


        if(!searchTerm.isEmpty()){
            for (Book book : bookInventory) {
                if(book.title.toLowerCase().contains(searchTerm.toLowerCase()))  resultList.add(book);
            }
        }else{
            resultList = bookInventory;
        }

        if(seenToggle != null) {
            for (Book book: resultList) {
                if(Main.myInventory.ReadBookMap.containsKey(book.title)) {
                    if(seenToggle) resultList2.add(book);
                }else{
                    if(!seenToggle) resultList2.add(book);
                }
            }

            resultList = resultList2;
            resultList2 = new ArrayList<>();
        }

        if(borrowedToggle != null) {
            for (Book book: resultList) {
                if(Main.myInventory.BorrowedBookMap.containsKey(book.title)) {
                    if(borrowedToggle) resultList2.add(book);
                }else{
                    if(!borrowedToggle) resultList2.add(book);
                }
            }

            resultList = resultList2;
        }

        return Util.arrayListToStringifiedObservableList(resultList);
    }

    public ObservableList<String> getMovieObservableList(){
        return Util.arrayListToStringifiedObservableList(movieInventory);
    }

    public ObservableList<String> getMovieObservableList(String searchTerm, Boolean seenToggle, Boolean borrowedToggle){
        ArrayList<Movie> resultList = new ArrayList<>();
        ArrayList<Movie> resultList2 = new ArrayList<>();


        if(!searchTerm.isEmpty()){
            for (Movie movie : movieInventory) {
                if(movie.title.toLowerCase().contains(searchTerm.toLowerCase()))  resultList.add(movie);
            }
        }else{
            resultList = movieInventory;
        }

        if(seenToggle != null) {
            for (Movie movie: resultList) {
                if(Main.myInventory.ReadMovieMap.containsKey(movie.title)) {
                    if(seenToggle) resultList2.add(movie);
                }else{
                    if(!seenToggle) resultList2.add(movie);
                }
            }

            resultList = resultList2;
            resultList2 = new ArrayList<>();
        }

        if(borrowedToggle != null) {
            for (Movie movie: resultList) {
                if(Main.myInventory.BorrowedMovieMap.containsKey(movie.title)) {
                    if(borrowedToggle) resultList2.add(movie);
                }else{
                    if(!borrowedToggle) resultList2.add(movie);
                }
            }

            resultList = resultList2;
        }

        return Util.arrayListToStringifiedObservableList(resultList);
    }


    public ObservableList<String> getAudioBookObservableList(){
        return Util.arrayListToStringifiedObservableList(audiobookInventory);
    }

    public ObservableList<String> getAudioBookObservableList(String searchTerm, Boolean seenToggle, Boolean borrowedToggle){
        ArrayList<AudioBook> resultList = new ArrayList<>();
        ArrayList<AudioBook> resultList2 = new ArrayList<>();


        if(!searchTerm.isEmpty()){
            for (AudioBook audioBook : audiobookInventory) {
                if(audioBook.title.toLowerCase().contains(searchTerm.toLowerCase()))  resultList.add(audioBook);
            }
        }else{
            resultList = audiobookInventory;
        }

        if(seenToggle != null) {
            for (AudioBook audioBook: resultList) {
                if(Main.myInventory.ReadAudioBookMap.containsKey(audioBook.title)) {
                    if(seenToggle) resultList2.add(audioBook);
                }else{
                    if(!seenToggle) resultList2.add(audioBook);
                }
            }

            resultList = resultList2;
            resultList2 = new ArrayList<>();
        }

        if(borrowedToggle != null) {
            for (AudioBook audioBook: resultList) {
                if(Main.myInventory.BorrowedAudioBookMap.containsKey(audioBook.title)) {
                    if(borrowedToggle) resultList2.add(audioBook);
                }else{
                    if(!borrowedToggle) resultList2.add(audioBook);
                }
            }

            resultList = resultList2;
        }

        return Util.arrayListToStringifiedObservableList(resultList);
    }







}
