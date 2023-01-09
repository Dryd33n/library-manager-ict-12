/*
Book Class:

This class is used for the base of the app, for the 3 main types of items in the
inventory, this class provides functionality for books in the application
*/

package app.nvdpl.librarymanager;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
            uri = new URI(URLDecoder.decode(bookObject.getString("link"), StandardCharsets.UTF_8));
        } catch (URISyntaxException e) {
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
        return title.toLowerCase().contains(searchQuery.toLowerCase());
    }

    public String[] getBookInfo(){
        String[] bookInfo = {
                title,
                "by " + author,
                "Released in " + year + " in " + country + ", " + title + " is a " + pages + " page " + language + " book written by author " + author,
                    "Author: " + author +"\n"+
                    "Year: " + year + "\n"+
                    "Country: " + country + "\n" +
                    "Book Language:  "+language+"\n"+
                    "Pages: "+ pages,
                uri.toString()};

        return bookInfo;
    }

    @Override
    public String toString(){
        return title + " - " + author;
    }
}
