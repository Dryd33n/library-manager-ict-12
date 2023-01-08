package app.nvdpl.librarymanager;

import javafx.application.Application;

import java.io.Console;

public class Main extends LibraryManagerApplication{

    public static LibraryInventory libraryInventory;
    public static BookUser myInventory = new BookUser();
    public static Console console;

    private static volatile boolean isRunning = true;


    public static void main(String[] args) {
        System.out.println("start");
        console = System.console();
        libraryInventory = new LibraryInventory();

        Thread nvdplThread = new Thread(Application::launch);
        nvdplThread.start();

        LoginForm.signup();
        LoginForm.login();

        while(isRunning){

        }

    }

    public static void exit(){
        System.exit(0);
    }
}
