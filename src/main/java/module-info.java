module app.nvdpl.librarymanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens app.nvdpl.librarymanager to javafx.fxml;
    exports app.nvdpl.librarymanager;
}