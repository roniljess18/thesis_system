module dev.rjm {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive core.fx;

    requires transitive core.db;
    requires core.util;
    requires javafx.graphics;
    requires java.sql.rowset;
    requires javafx.base;
    requires atlantafx.base;

    opens dev.rjm to javafx.fxml;
    opens dev.rjm.app to javafx.fxml;

    exports dev.rjm;
    exports dev.rjm.app;
}
