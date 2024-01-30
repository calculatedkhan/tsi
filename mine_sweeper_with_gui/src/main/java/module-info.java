module com.example.mine_sweeper_with_gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.mine_sweeper_with_gui to javafx.fxml;
    exports com.example.mine_sweeper_with_gui;
}