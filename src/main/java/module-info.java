module org.example.assignment3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.assignment3 to javafx.fxml;
    exports org.example.assignment3;
}