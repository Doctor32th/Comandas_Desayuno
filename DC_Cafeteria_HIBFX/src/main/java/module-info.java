module com.mycompany.dc_cafeteria_hibfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires jasperreports;
    requires javafx.web;
    

    opens com.mycompany.dc_cafeteria_hibfx to javafx.fxml, org.hibernate.orm.core, java.sql, javafx.swing;
    opens models;
    exports com.mycompany.dc_cafeteria_hibfx;
}
