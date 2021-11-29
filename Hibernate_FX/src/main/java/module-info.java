module com.mycompany.Hibernate_FX {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;

    opens com.mycompany.Hibernate_FX to javafx.fxml,
            org.hibernate.orm.core, java.sql;
    opens models;
    exports com.mycompany.Hibernate_FX;
}
