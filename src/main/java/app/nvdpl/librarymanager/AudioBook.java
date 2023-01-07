package app.nvdpl.librarymanager;

import org.json.JSONObject;

public class AudioBook extends Book {

    private int length;
    public AudioBook(JSONObject audiobookObject){
        super(audiobookObject);
        length = super.pages * 3;
    }

    public AudioBook(String author, String country, String language, String title, int pages, int year, String URI){
        super(author,country,language,title,pages,year,URI);
        length = super.pages * 3;
    }

    public Boolean checkSearchTerm(String searchQuery){
        if(title.toLowerCase().contains(searchQuery.toLowerCase())) return true;
        else return false;
    }



    @Override
    public String toString(){
        return title+" "+length+"min";
    }
}
