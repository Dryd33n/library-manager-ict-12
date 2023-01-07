package app.nvdpl.librarymanager;

import javafx.application.Application;

public class Main extends LibraryManagerApplication{

    public static LibraryInventory libraryInventory = new LibraryInventory();


    public static void main(String[] args) {
        Thread nvdplThread = new Thread(Application::launch);
        nvdplThread.start();
    }
}
