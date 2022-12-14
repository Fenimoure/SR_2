package com.objoriprog.sr2.sr_2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class AboutWindowController {

    @FXML
    private Button close_button;

    @FXML
    void close_window() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        HelloController.setGroup(group_selector.getValue());
        HelloController.setMark(mark_spinner.getValue());
        stage.close();
    }

    @FXML
    private ChoiceBox<String> group_selector;

    @FXML
    private Spinner<Integer> mark_spinner;

    private final SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);

    @FXML
    private Label version_label;

    @FXML
    void initialize() {
        assert close_button != null : "fx:id=\"close_button\" was not injected: check your FXML file 'about_window.fxml'.";
        assert group_selector != null : "fx:id=\"group_selector\" was not injected: check your FXML file 'about_window.fxml'.";
        assert mark_spinner != null : "fx:id=\"mark_spinner\" was not injected: check your FXML file 'about_window.fxml'.";
        assert version_label != null : "fx:id=\"version_label\" was not injected: check your FXML file 'about_window.fxml'.";
        version_label.setText("Дата та час здачі " + java.time.LocalDate.now() + " " + java.time.LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        mark_spinner.setValueFactory(spinnerValueFactory);
        mark_spinner.getValueFactory().setValue(HelloController.getMark());
        group_selector.getItems().addAll("КН-21", "КН-22 (найкращі)", "Анд-21");
        group_selector.setValue(HelloController.getGroup());
    }

}
