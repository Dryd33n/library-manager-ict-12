module app.nvdok.librarymanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.nvdok.librarymanager to javafx.fxml;
    exports app.nvdok.librarymanager;
}