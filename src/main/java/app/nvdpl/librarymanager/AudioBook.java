/*
Audio Book Class:

This class is used for the base of the app, for the 3 main types of items in the
inventory, this class provides functionality for audiobooks in the application
 */

package app.nvdpl.librarymanager;

import org.json.JSONObject;



public class AudioBook extends Book {

    private final int length;
    public final String title;
    public AudioBook(JSONObject audiobookObject){
        super(audiobookObject);
        length = super.pages * 3;
        this.title = super.title;
    }

    public AudioBook(String author, String country, String language, String title, int pages, int year, String URI){
        super(author,country,language,title,pages,year,URI);
        length = super.pages * 3;
        this.title = title;
    }

    public Boolean checkSearchTerm(String searchQuery){//returns true if this book should be shown for a given search term
        return title.toLowerCase().contains(searchQuery.toLowerCase());
    }

    public String[] getAudiobookInfo(){

        return new String[]{
                title,
                "by " + author,
                "This book, " + title + "is written by author "+ author +"was released in "+year+". The transcription is in "+language+" and is "+length+" min long.",
                "Author: " + author +"\n"+
                        "Year: " + year + "\n"+
                        "Country: " + country + "\n" +
                        "Speaking Language:  "+language+"\n"+
                        "Time: "+ length +" min",
                uri.toString()};
    }



    @Override
    public String toString(){
        return title+" "+length+"min";
    }
}
