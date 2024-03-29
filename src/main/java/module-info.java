module com.example.logocompanyfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.logocompanyfx to javafx.fxml;
    exports com.example.logocompanyfx;
}