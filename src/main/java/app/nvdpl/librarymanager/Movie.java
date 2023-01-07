package app.nvdpl.librarymanager;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Movie{

    public final String title;
    private final String year, runtime, director, actors, plot;
    private final ArrayList<String> genres;
    private final URI uri;

    /*
            "id": 1,
            "title": "Beetlejuice",
            "year": "1988",
            "runtime": "92",
            "genres": [
                "Comedy",
                "Fantasy"
            ],
            "director": "Tim Burton",
            "actors": "Alec Baldwin, Geena Davis, Annie McEnroe, Maurice Page",
            "plot": "A couple of recently deceased ghosts contract the services of a \"bio-exorcist\" in order to remove the obnoxious new owners of their house.",
            "posterUrl": "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwODE3MDE0MV5BMl5BanBnXkFtZTgwNTk1MjI4MzE@._V1_SX300.jpg"
     */

    public Movie(JSONObject movieObject){
        title = movieObject.getString("title");
        year = movieObject.getString("year");
        runtime = movieObject.getString("runtime");
        director = movieObject.getString("director");
        actors = movieObject.getString("actors");
        plot = movieObject.getString("plot");
        genres = Util.jsonArrayToArrayList(movieObject.getJSONArray("genres"));

        try {
            uri = new URI(movieObject.getString("posterUrl"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public Movie(String title, String year, String runtime, String director, String actors, String plot,ArrayList<String> genres, String uri) throws URISyntaxException {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.genres = genres;
        this.uri = new URI(uri);
    }

    public Boolean checkSearchTerm(String searchQuery){
        if(title.toLowerCase().contains(searchQuery.toLowerCase())) return true;
        else return false;
    }

    @Override
    public String toString(){
        return title + " (" + year + ")";
    }
}
