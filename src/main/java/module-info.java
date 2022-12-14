module com.objoriprog.sr2.sr_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.objoriprog.sr2.sr_2 to javafx.fxml;
    exports com.objoriprog.sr2.sr_2;
}