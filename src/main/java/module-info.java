module es.gui.passwordgenerator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens es.gui.passwordgenerator to javafx.fxml;
    exports es.gui.passwordgenerator;
}