package app.nvdpl.librarymanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Map;


public class Util {
    private Util(){}

    
    public static JSONArray pathStringToJsonArray(String pathString){//converts the path of a json file to the jsonarray within
        Path path = Path.of(pathString);
        String contents;

        try {
            contents = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new JSONArray(contents);
    }

    public static String sha256(final String base) {//converts a string to its sha256 hash equivalent
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static ArrayList<String> jsonArrayToArrayList(JSONArray jsonArray){//converts a json array to arraylist
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.getString(i));
        }

        return result;
    }

    public static Boolean boolifyRadioButtonString(String buttonString){//used for converting values from radio buttons to booleans
        Map<String, Boolean> stringToBoolMap = Map.of(
                "Read",true,
                "Unread",false,
                "Watched", true,
                "Unwatched", false,
                "Listened", true,
                "Unlistened",false,
                "Borrowing", true,
                "Not Borrowing",false);

        return stringToBoolMap.get(buttonString);
    }



    public static ObservableList<String> arrayListToStringifiedObservableList(ArrayList<?> objectArrayList){//takes an array list and converts it to its stringified equivalent in an observable list.
        ObservableList<String> result = FXCollections.observableArrayList();

        for (Object object : objectArrayList) {
            result.add(object.toString());
        }

        return result;
    }


}
