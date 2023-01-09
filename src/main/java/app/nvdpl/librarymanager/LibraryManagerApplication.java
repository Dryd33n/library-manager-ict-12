package app.nvdpl.librarymanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryManagerApplication extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagerApplication.class.getResource("library.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        this.stage = stage;
        stage.setResizable(false);
        stage.setTitle("North Vancouver District Public Library Inventory Manager");
        stage.setScene(scene);

    }

    public static void showWindow(){
        stage.show();
    }

    public static void hideWindow() {
        stage.hide();
    }

    public static void main(String[] args) {
        launch();
    }


}