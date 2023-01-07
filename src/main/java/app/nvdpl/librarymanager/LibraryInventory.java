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

    public ObservableList<String> getBookObservableList(String searchTerm){
        ArrayList<Book> resultList = new ArrayList<>();

        for (int i = 0; i < bookInventory.size(); i++) {
            if(bookInventory.get(i).checkSearchTerm(searchTerm)){
                resultList.add(bookInventory.get(i));
            }
        }

        return Util.arrayListToStringifiedObservableList(resultList);
    }

    public ObservableList<String> getMovieObservableList(){
        return Util.arrayListToStringifiedObservableList(movieInventory);
    }

    public ObservableList<String> getMovieObservableList(String searchTerm){
        ArrayList<Movie> resultList = new ArrayList<>();

        for (int i = 0; i < movieInventory.size(); i++) {
            if(movieInventory.get(i).checkSearchTerm(searchTerm)){
                resultList.add(movieInventory.get(i));
            }
        }

        return Util.arrayListToStringifiedObservableList(resultList);
    }

    public ObservableList<String> getAudioBookObservableList(){
        return Util.arrayListToStringifiedObservableList(audiobookInventory);
    }

    public ObservableList<String> getAudioBookObservableList(String searchTerm){
        ArrayList<AudioBook> resultList = new ArrayList<>();

        for (int i = 0; i < audiobookInventory.size(); i++) {
            if(audiobookInventory.get(i).checkSearchTerm(searchTerm)){
                resultList.add(audiobookInventory.get(i));
            }
        }

        return Util.arrayListToStringifiedObservableList(resultList);
    }




}
