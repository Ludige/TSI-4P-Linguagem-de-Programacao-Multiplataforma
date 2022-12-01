module com.mycompany.calculadorafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.calculadorafx to javafx.fxml;
    exports com.mycompany.calculadorafx;
}
