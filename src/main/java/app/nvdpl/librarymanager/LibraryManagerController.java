package app.nvdpl.librarymanager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryManagerController extends Application {

    public Button mainMenuBookButton;
    public Button mainMenuMovieButton;
    public Button mainMenuAudioBookButton;
    public VBox accountInfoVbox;
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
    public Label bookTitle;
    public Label bookAuthor;
    public Label bookDescription;
    public Button bookWebsiteButton;
    public TextArea bookInfoTextArea;
    public Label movieTitle;
    public Label movieActors;
    public Label moviePlot;
    public TextArea movieInfoTextArea;
    public Label audiobookTitle;
    public Label audiobookAuthor;
    public Label audiobookDescription;
    public TextArea audioBookInfo;
    public Button mainMenuAccountButton;
    public Label accountUsername;
    public TextArea accountInfo1;
    public TextArea accountInfo2;
    public HBox bookmovieaudiobookHbox;


    @FXML
    public void initialize() {
        bookSearchResultListView.getItems().setAll(Main.libraryInventory.getBookObservableList());
        movieSearchResultListView.getItems().setAll(Main.libraryInventory.getMovieObservableList());
        audiobookSearchResultListView.getItems().setAll(Main.libraryInventory.getAudioBookObservableList());

        changeToBookScreen();
    }

    public void hideAllScreens(){
        accountInfoVbox.setVisible(false);
        mainMenuAccountButton.getStyleClass().removeAll("main-menu-button-selected");
        movieSearchVbox.setVisible(false);
        movieInfoVbox.setVisible(false);
        mainMenuMovieButton.getStyleClass().removeAll("main-menu-button-selected");
        audiobookSearchVbox.setVisible(false);
        audiobookInfoVbox.setVisible(false);
        mainMenuAudioBookButton.getStyleClass().removeAll("main-menu-button-selected");
        bookSearchVbox.setVisible(false);
        bookInfoVbox.setVisible(false);
        mainMenuBookButton.getStyleClass().removeAll("main-menu-button-selected");
        bookmovieaudiobookHbox.setVisible(false);
    }

    public void changeToBookScreen(){
        hideAllScreens();

        bookSearchVbox.setVisible(true);
        bookInfoVbox.setVisible(true);
        mainMenuBookButton.getStyleClass().add("main-menu-button-selected");
        bookmovieaudiobookHbox.setVisible(true);
    }

    public void changeToMovieScreen(){
        hideAllScreens();

        movieSearchVbox.setVisible(true);
        movieInfoVbox.setVisible(true);
        mainMenuMovieButton.getStyleClass().add("main-menu-button-selected");
        bookmovieaudiobookHbox.setVisible(true);
    }

    public void changeToAudiobookScreen(){
        hideAllScreens();

        audiobookSearchVbox.setVisible(true);
        audiobookInfoVbox.setVisible(true);
        mainMenuAudioBookButton.getStyleClass().add("main-menu-button-selected");
        bookmovieaudiobookHbox.setVisible(true);
    }

    public void changeToAccountInfoScreen(){
        hideAllScreens();
        updateUserScreen();

        accountInfoVbox.setVisible(true);
        mainMenuAccountButton.getStyleClass().add("main-menu-button-selected");
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

    public void updateBookInfoPanel(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);
        String[] content = book.getBookInfo();

        bookTitle.setText(content[0]);
        bookAuthor.setText(content[1]);
        bookDescription.setText(content[2]);
        bookInfoTextArea.setText(content[3]);
    }

    public void bookWebsiteButton(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);
        String[] content = book.getBookInfo();

        getHostServices().showDocument(content[4]);
    }

    public void updateMovieInfoPanel(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);
        String[] content = movie.getMovieInfo();

        movieTitle.setText(content[0]);
        movieActors.setText(content[1]);
        moviePlot.setText(content[2]);
        movieInfoTextArea.setText(content[3]);
    }

    public void updateAudioBookInfoPanel(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);
        String[] content = audioBook.getAudiobookInfo();

        audiobookTitle.setText(content[0]);
        audiobookAuthor.setText(content[1]);
        audiobookDescription.setText(content[2]);
        audioBookInfo.setText(content[3]);
    }

    public void audiobookWebsiteButton(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);
        String[] content = book.getBookInfo();

        getHostServices().showDocument(content[4]);
    }

    public void borrowBook(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);

        Main.currentUser.borrowItem(book, true);
    }

    public void returnBook(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);

        Main.currentUser.borrowItem(book, false);
    }

    public void borrowMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.borrowItem(movie, true);
    }

    public void returnMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.borrowItem(movie, false);
    }

    public void borrowAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.borrowItem(audioBook, true);
    }

    public void returnAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.borrowItem(audioBook, false);
    }

    public void readBook(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);

        Main.currentUser.markItemAsRead(book, true);
    }

    public void unreadBook(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);

        Main.currentUser.markItemAsRead(book, false);
    }


    public void readMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.markItemAsRead(movie, true);
    }

    public void unreadMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.markItemAsRead(movie, false);
    }

    public void readAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.markItemAsRead(audioBook, true);
    }

    public void unreadAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.markItemAsRead(audioBook, false);
    }

    public void updateUserScreen(){
        String[] content = Main.currentUser.getUserData();

        accountUsername.setText(content[0]);
        accountInfo1.setText(content[1]);
        accountInfo2.setText(content[2]);
    }




    @Override
    public void start(Stage stage){

    }
}