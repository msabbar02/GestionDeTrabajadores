module com.example.trabajoindividual_mohamed_sabbar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.trabajoindividual_mohamed_sabbar to javafx.fxml;
    exports com.example.trabajoindividual_mohamed_sabbar;
}