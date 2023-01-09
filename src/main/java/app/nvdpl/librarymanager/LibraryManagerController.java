/*
Controller Class

This class is directly accessed from the Javafx Fxml file
these functions are run directly from the application itself
as a result of an event in the app. These functions are mostly
for updating the user interface, most of the functions rely
on other classes and functions unfortunately due to the lack
of javafx's ability to provide paramters to the function since
the default parameter supplied is the event itself some of these
functions are repetetive since I could not use paramters
 */

package app.nvdpl.librarymanager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryManagerController extends Application {

    public HBox bookmovieaudiobookHbox;
    public Button mainMenuBookButton,mainMenuMovieButton,mainMenuAudioBookButton, mainMenuAccountButton;//Main menu buttons
    public VBox accountInfoVbox;
    public VBox bookSearchVbox, movieSearchVbox, audiobookSearchVbox;//Boxes for search panels
    public VBox bookInfoVbox, movieInfoVbox,audiobookInfoVbox;//Boxes for info panels

    public ListView<String> bookSearchResultListView,audiobookSearchResultListView,movieSearchResultListView;//Search Result List Views
    public ToggleGroup BookSearchReadGroup, BookSearchBorrowedGroup,MovieSearchWatchedGroup,MovieSearchBorrowedGrouop,AudiobookListenedGroup, AudiobookBorrowedGroup; //search toggle groups
    public TextField movieSearchField,audioBookSearchFIeld, bookSearchInputField;//Main Search fields

    //Book Info Elements
    public Label bookTitle,bookAuthor,bookDescription;
    public Button bookWebsiteButton;
    public TextArea bookInfoTextArea;

    //Movie Info Elements
    public Label movieTitle,movieActors, moviePlot;
    public TextArea movieInfoTextArea;

    //AudioBook Info Elements
    public Label audiobookTitle,audiobookAuthor,audiobookDescription;
    public TextArea audioBookInfo;

    //Account Info Elements
    public Label accountUsername;
    public TextArea accountInfo1, accountInfo2;
    public Button bookReadButton,bookBorrowedButton, bookUnreadButton, bookReturnButton, movieWatchedButton,movieBorrowButton,movieUnwatchedButton,MovieReturnButton,audiobookHeardButton,audiobookBorrowButton,audioBookUnheardButton,audioBookReturnButton;


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

        if(Main.currentUser.isBorrowed(book)){
            bookBorrowedButton.setDisable(true);
            bookReturnButton.setDisable(false);
        }else{
            bookBorrowedButton.setDisable(false);
            bookReturnButton.setDisable(true);
        }

        if(Main.currentUser.isRead(book)){
            bookReadButton.setDisable(true);
            bookUnreadButton.setDisable(false);
        }else{
            bookReadButton.setDisable(false);
            bookUnreadButton.setDisable(true);
        }


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

        if(Main.currentUser.isBorrowed(movie)){
            movieBorrowButton.setDisable(true);
            MovieReturnButton.setDisable(false);
        }else{
            movieBorrowButton.setDisable(false);
            MovieReturnButton.setDisable(true);
        }

        if(Main.currentUser.isRead(movie)){
            movieWatchedButton.setDisable(true);
            movieUnwatchedButton.setDisable(false);
        }else{
            movieWatchedButton.setDisable(false);
            movieUnwatchedButton.setDisable(true);
        }

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

        if(Main.currentUser.isBorrowed(audioBook)){
            audiobookBorrowButton.setDisable(true);
            audioBookReturnButton.setDisable(false);
        }else{
            audiobookBorrowButton.setDisable(false);
            audioBookReturnButton.setDisable(true);
        }

        if(Main.currentUser.isRead(audioBook)) {
            audiobookHeardButton.setDisable(true);
            audioBookUnheardButton.setDisable(false);
        }else{
            audiobookHeardButton.setDisable(false);
            audioBookUnheardButton.setDisable(true);
        }

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

        updateBookInfoPanel();
    }

    public void returnBook(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);

        Main.currentUser.borrowItem(book, false);

        updateBookInfoPanel();
    }

    public void borrowMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.borrowItem(movie, true);

        updateMovieInfoPanel();
    }

    public void returnMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.borrowItem(movie, false);

        updateMovieInfoPanel();
    }

    public void borrowAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.borrowItem(audioBook, true);

        updateAudioBookInfoPanel();
    }

    public void returnAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.borrowItem(audioBook, false);

        updateAudioBookInfoPanel();
    }

    public void readBook(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);

        Main.currentUser.markItemAsRead(book, true);
        updateBookInfoPanel();
    }

    public void unreadBook(){
        int index = bookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > bookSearchResultListView.getItems().size()) return;

        Book book = Main.libraryInventory.getSearchResultBookFromIndex(index);

        Main.currentUser.markItemAsRead(book, false);
        updateBookInfoPanel();
    }


    public void readMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.markItemAsRead(movie, true);
        updateMovieInfoPanel();
    }

    public void unreadMovie(){
        int index = movieSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > movieSearchResultListView.getItems().size()) return;

        Movie movie = Main.libraryInventory.getSearchResultMovieFromIndex(index);

        Main.currentUser.markItemAsRead(movie, false);
        updateMovieInfoPanel();
    }

    public void readAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.markItemAsRead(audioBook, true);
        updateAudioBookInfoPanel();
    }

    public void unreadAudioBook(){
        int index = audiobookSearchResultListView.getSelectionModel().getSelectedIndex();

        if(index < 0 || index > audiobookSearchResultListView.getItems().size()) return;

        AudioBook audioBook = Main.libraryInventory.getSearchResultAudioBookFromIndex(index);

        Main.currentUser.markItemAsRead(audioBook, false);
        updateAudioBookInfoPanel();
    }

    public void updateUserScreen(){
        String[] content = Main.currentUser.getUserData();

        accountUsername.setText(content[0]);
        accountInfo1.setText(content[1]);
        accountInfo2.setText(content[2]);
    }

    public void logOut(){
        Main.hideWindow();
    }




    @Override
    public void start(Stage stage){

    }
}