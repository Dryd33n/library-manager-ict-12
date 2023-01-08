package app.nvdpl.librarymanager;

import javafx.application.Application;

import java.io.Console;

public class Main extends LibraryManagerApplication{

    public static LibraryInventory libraryInventory;
    public static Console console;
    public static BookUser currentUser;



    private static volatile boolean isRunning = true;


    public static void main(String[] args) {
        System.out.println("start");
        console = System.console();
        libraryInventory = new LibraryInventory();





        Printer.title();
        Printer.intro();
        WaitFor.waitForInteraction();

        while(isRunning){
        Printer.clr();
        Printer.signupMenu();
        }





    }

    public static void exit(){
        isRunning = false;
        System.exit(0);
    }
    
    public static void launchApp(){
        Thread nvdplThread = new Thread(Application::launch);
        nvdplThread.start();
    }
}
