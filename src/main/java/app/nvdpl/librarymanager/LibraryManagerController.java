package app.nvdpl.librarymanager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LibraryManagerController {

    public Button mainMenuBookButton;
    public Button mainMenuMovieButton;
    public Button mainMenuAudioBookButton;
    public VBox bookSearchVbox;
    public VBox movieSearchVbox;
    public VBox audiobookSearchVbox;
    public VBox bookInfoVbox;
    public VBox movieInfoVbox;
    public VBox audiobookInfoVbox;

    public ListView<String> bookSearchResultListView;
    public ListView<String> audiobookSearchResultListView;
    public ListView<String> movieSearchResultListView;
    public ToggleGroup BookSearchReadGroup;
    public ToggleGroup BookSearchBorrowedGroup;
    public ToggleGroup MovieSearchWatchedGroup;
    public ToggleGroup MovieSearchBorrowedGrouop;
    public ToggleGroup AudiobookListenedGroup;
    public ToggleGroup AudiobookBorrowedGroup;
    public TextField movieSearchField;
    public TextField audioBookSearchFIeld;

    public TextField bookSearchInputField;

    @FXML
    public void initialize() {
        bookSearchResultListView.getItems().setAll(Main.libraryInventory.getBookObservableList());
        movieSearchResultListView.getItems().setAll(Main.libraryInventory.getMovieObservableList());
        audiobookSearchResultListView.getItems().setAll(Main.libraryInventory.getAudioBookObservableList());

        changeToBookScreen();
    }

    public void changeToBookScreen(){
        bookSearchVbox.setVisible(true);
        bookInfoVbox.setVisible(true);
        mainMenuBookButton.getStyleClass().add("main-menu-button-selected");

        movieSearchVbox.setVisible(false);
        movieInfoVbox.setVisible(false);
        mainMenuMovieButton.getStyleClass().removeAll("main-menu-button-selected");
        audiobookSearchVbox.setVisible(false);
        audiobookInfoVbox.setVisible(false);
        mainMenuAudioBookButton.getStyleClass().removeAll("main-menu-button-selected");
    }

    public void changeToMovieScreen(){
        movieSearchVbox.setVisible(true);
        movieInfoVbox.setVisible(true);
        mainMenuMovieButton.getStyleClass().add("main-menu-button-selected");

        bookSearchVbox.setVisible(false);
        bookInfoVbox.setVisible(false);
        mainMenuBookButton.getStyleClass().removeAll("main-menu-button-selected");
        audiobookSearchVbox.setVisible(false);
        audiobookInfoVbox.setVisible(false);
        mainMenuAudioBookButton.getStyleClass().removeAll("main-menu-button-selected");
    }

    public void changeToAudiobookScreen(){
        audiobookSearchVbox.setVisible(true);
        audiobookInfoVbox.setVisible(true);
        mainMenuAudioBookButton.getStyleClass().add("main-menu-button-selected");

        bookSearchVbox.setVisible(false);
        bookInfoVbox.setVisible(false);
        mainMenuBookButton.getStyleClass().removeAll("main-menu-button-selected");
        movieSearchVbox.setVisible(false);
        movieInfoVbox.setVisible(false);
        mainMenuMovieButton.getStyleClass().removeAll("main-menu-button-selected");
    }

    public void refreshBookSearchBarQuery(){
        String input = bookSearchInputField.getText();
        Boolean searchReadOnly = Util.boolifyRadioButtonString(((RadioButton) BookSearchReadGroup.getSelectedToggle()).getText());
        Boolean searchBorrowedOnly = Util.boolifyRadioButtonString(((RadioButton) BookSearchBorrowedGroup.getSelectedToggle()).getText());

        bookSearchResultListView.getItems().setAll(Main.libraryInventory.getBookObservableList(input, searchReadOnly, searchBorrowedOnly));
    }

    public void refreshMovieSearchBarQuery(){
        String input = movieSearchField.getText();
        Boolean searchReadOnly = Util.boolifyRadioButtonString(((RadioButton) MovieSearchWatchedGroup.getSelectedToggle()).getText());
        Boolean searchBorrowedOnly = Util.boolifyRadioButtonString(((RadioButton) MovieSearchBorrowedGrouop.getSelectedToggle()).getText());

        movieSearchResultListView.getItems().setAll(Main.libraryInventory.getMovieObservableList(input, searchReadOnly, searchBorrowedOnly));
    }

    public void refreshAudioSearchBarQuery(){
        String input = audioBookSearchFIeld.getText();
        Boolean searchReadOnly = Util.boolifyRadioButtonString(((RadioButton) AudiobookListenedGroup.getSelectedToggle()).getText());
        Boolean searchBorrowedOnly = Util.boolifyRadioButtonString(((RadioButton) AudiobookBorrowedGroup.getSelectedToggle()).getText());

        audiobookSearchResultListView.getItems().setAll(Main.libraryInventory.getAudioBookObservableList(input, searchReadOnly, searchBorrowedOnly));
    }



}