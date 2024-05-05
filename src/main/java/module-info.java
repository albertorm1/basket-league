module es.alrodmue {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens es.alrodmue to javafx.fxml;
    exports es.alrodmue;
}
