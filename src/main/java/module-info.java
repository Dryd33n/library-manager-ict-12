module app.nvdok.librarymanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.nvdpl.librarymanager to javafx.fxml;
    exports app.nvdpl.librarymanager;
}