module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires selenium.chrome.driver;
    requires selenium.api;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
}