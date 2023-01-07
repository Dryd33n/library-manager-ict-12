package app.nvdpl.librarymanager;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

public class Book{

    public final String title;
    protected final String author, country, language;
    protected final int pages, year;
    protected final URI uri;

    /*
     "author": "Chinua Achebe",
    "country": "Nigeria",
    "imageLink": "images/things-fall-apart.jpg",
    "language": "English",
    "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
    "pages": 209,
    "title": "Things Fall Apart",
    "year": 1958
     */

    public Book(JSONObject bookObject){
        author = bookObject.getString("author");
        country = bookObject.getString("country");
        language = bookObject.getString("language");
        title = bookObject.getString("title");
        pages = bookObject.getInt("pages");
        year = bookObject.getInt("year");

        try {
            uri = new URI(URLDecoder.decode(bookObject.getString("link"), "UTF-8"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Book(String author, String country, String language, String title, int pages, int year, String URI){
        this.author = author;
        this.country = country;
        this.language = language;
        this.title = title;
        this.pages = pages;
        this.year = year;
        try {
            this.uri = new URI(URI);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean checkSearchTerm(String searchQuery){
        if(title.toLowerCase().contains(searchQuery.toLowerCase())) return true;
        else return false;
    }

    @Override
    public String toString(){
        return title + " - " + author;
    }
}
