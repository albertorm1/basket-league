module es.alrodmue {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens es.alrodmue to javafx.fxml;
    exports es.alrodmue;
}
