module com.mycompany.navegadorfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    opens com.mycompany.navegadorfx.controller to javafx.fxml;
    exports com.mycompany.navegadorfx;
    exports com.mycompany.navegadorfx.controller;
    exports com.mycompany.navegadorfx.model;
    exports com.mycompany.navegadorfx.view;
}
