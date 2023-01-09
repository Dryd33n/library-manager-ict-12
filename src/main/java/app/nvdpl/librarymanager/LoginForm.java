/*
This class holds the 2 cli forms to login and signup
For storing the passwords it uses a method in util to convert the
password to a sha256 hash
 */
package app.nvdpl.librarymanager;

import java.util.AbstractMap;

public class LoginForm extends Accounts{

    public static String login(){
        AbstractMap.SimpleImmutableEntry<String,String> loginKVPair;
        String username, hashedPassword;

        while (true){
            WaitFor.waitForString();
            System.out.println("Please Enter Username (Type nothing to go back): ");
            username = WaitFor.waitForString();

            if (username.isEmpty()) return null;//quit if username empty

            System.out.println("Please Enter Password");
            hashedPassword = Util.sha256(String.valueOf(WaitFor.waitForString()));//convert password to hash
            loginKVPair = new AbstractMap.SimpleImmutableEntry<>(username, hashedPassword);//create new kv pair

            if(!Accounts.isValidLogin(loginKVPair)) {
                System.out.println("An account with this username and password combination does not exist. Please try again.");
                continue;
            }

            break;
        }

        System.out.println("Account logged in successfully");

        Main.currentUser = Accounts.retrieveBookUserFromUsername(loginKVPair.getValue());//set current account
        return loginKVPair.getKey();
    }

    public static void signup(){
        AbstractMap.SimpleImmutableEntry<String,String> signupKVPair;
        String username, hashedPassword;


        while(true){
            WaitFor.waitForString();
            System.out.println("Please pick a username:");
            username = WaitFor.waitForString();


            if(!Accounts.isUniqueAccount(username)){
                System.out.println("An account with this username already exists. Please enter another username");
                continue;
            }
            break;
        }
        System.out.println("\nPlease Enter a password: ");
        hashedPassword = Util.sha256(WaitFor.waitForString());

        signupKVPair = new AbstractMap.SimpleImmutableEntry<>(username, hashedPassword);

        Accounts.addAccount(signupKVPair, new BookUser(username));
    }
}
