package app.nvdpl.librarymanager;

import java.util.ArrayList;
import java.util.HashMap;

public class Accounts {
    private static HashMap<String,String> accounts = new HashMap<>();

    private static ArrayList<BookUser> usersArrayList = new ArrayList<>();

    protected static  boolean isUniqueAccount(String username){
        return !(accounts.containsKey(username));
    }

    protected static void addAccount(HashMap.Entry<String,String> accountKVPair, BookUser userObject){
        if(isUniqueAccount(accountKVPair.getKey())) accounts.put(accountKVPair.getKey(), accountKVPair.getValue());
        usersArrayList.add(userObject);
    }

    protected static boolean compareAccountValues(HashMap.Entry<String,String> accountKVPair){
        if(isUniqueAccount(accountKVPair.getKey())) return false;

        return accounts.get(accountKVPair.getKey()).equals(accountKVPair.getValue());
    }

    protected static boolean isValidLogin(HashMap.Entry<String,String> accountKVPair){
        if(isUniqueAccount(accountKVPair.getKey())) return false;
        return compareAccountValues(accountKVPair);

    }

    public static BookUser retrieveBookUserFromUsername(String username){
        for (BookUser user: usersArrayList) {
            if(user.username.equals(username)) return user;
        }
        return null;
    }


}