module es.alrodmue {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens es.alrodmue.view to javafx.fxml;
    opens es.alrodmue.model.players to javafx.base;
    opens es.alrodmue.model.matches to javafx.base;
    exports es.alrodmue;
}
