package app.nvdpl.librarymanager;

import javafx.application.Application;
import javafx.application.Platform;

public class Main extends LibraryManagerApplication{

    public static LibraryInventory libraryInventory;
    public static BookUser currentUser;



    static volatile boolean isRunning = true;


    public static void main(String[] args) {
        Thread nvdplThread = new Thread(Application::launch);
        nvdplThread.start();
        libraryInventory = new LibraryInventory();

        Printer.title();
        Printer.intro();
        WaitFor.waitForInteraction();

        while(isRunning){
        Printer.clr();
        Printer.signupMenu();
        }

        Printer.exit();
        WaitFor.waitForInteraction();
        Printer.clr();
        exit();
    }

    public static void exit(){
        isRunning = false;
        System.exit(0);
    }

    public static void showWindow() {
        Platform.runLater(LibraryManagerApplication::showWindow);
    }

    public static void hideWindow() {
        Platform.runLater(LibraryManagerApplication::hideWindow);
    }
    
    public static void launchApp(){

    }
}
