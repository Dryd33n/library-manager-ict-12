package app.nvdpl.librarymanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class Util {
    private Util(){}

    
    public static JSONArray pathStringToJsonArray(String pathString){
        Path path = Path.of(pathString);
        String contents;

        try {
            contents = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(contents);//remove

        return new JSONArray(contents);
    }

    public static ArrayList<String> jsonArrayToArrayList(JSONArray jsonArray){
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.getString(i));
        }

        return result;
    }

    public static ObservableList<String> arrayListToStringifiedObservableList(ArrayList<?> objectArrayList){
        ObservableList<String> result = FXCollections.observableArrayList();

        for (Object object : objectArrayList) {
            result.add(object.toString());
        }

        return result;
    }


}