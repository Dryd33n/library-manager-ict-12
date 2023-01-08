package app.nvdpl.librarymanager;

import java.util.AbstractMap;

public class LoginForm extends Accounts{

    public static String login(){
        AbstractMap.SimpleImmutableEntry<String,String> loginKVPair;
        String username, hashedPassword;

        while (true){
            System.out.println("Please Enter Username: ");
            username = WaitFor.waitForString();
            System.out.println("Please Enter Password");
            hashedPassword = Util.sha256(String.valueOf(WaitFor.waitForString()));
            loginKVPair = new AbstractMap.SimpleImmutableEntry<>(username, hashedPassword);

            if(Accounts.isValidLogin(loginKVPair)) {
                System.out.println("An account with this username and password combination does not exist. Please try again.");
                continue;
            }

            break;
        }

        System.out.println("Account logged in successfully");
        return loginKVPair.getKey();
    }

    public static String signup(){
        AbstractMap.SimpleImmutableEntry<String,String> signupKVPair;
        String username, hashedPassword;


        while(true){
            System.out.println("Please pick a username:");
            username = WaitFor.waitForString();
            if(!Accounts.isUniqueAccount(username)){
                System.out.println("An account with this username already exists. Please enter another username");
                continue;
            }
            break;
        }
        System.out.println("\nPlease Enter a password: ");
        hashedPassword = String.valueOf(WaitFor.waitForString());

        signupKVPair = new AbstractMap.SimpleImmutableEntry<>(username, hashedPassword);

        Accounts.addAccount(signupKVPair);
        return signupKVPair.getKey();
    }
}
