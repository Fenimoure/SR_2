package com.objoriprog.sr2.sr_2;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Platform.exit;


public class HelloController {
    @FXML // fx:id="Toggles"
    private ToggleGroup Toggles; // Value injected by FXMLLoader

    @FXML // fx:id="false_toggle"
    private RadioButton false_toggle; // Value injected by FXMLLoader

    @FXML // fx:id="mid_toggle"
    private RadioButton mid_toggle; // Value injected by FXMLLoader

    @FXML // fx:id="true_toggle"
    private RadioButton true_toggle; // Value injected by FXMLLoader

    @FXML // fx:id="all_toggle"
    private RadioButton all_toggle;

    @FXML // fx:id="outTField"
    private TextArea outTField; // Value injected by FXMLLoader

    SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
    @FXML // fx:id="counter"
    private Spinner<Integer> counter = new Spinner<>(spinnerValueFactory); // Value injected by FXMLLoader

    @FXML // fx:id="AboutButton"
    private Button AboutButton; // Value injected by FXMLLoader

    @FXML // fx:id="ExitButton"
    private Button ExitButton; // Value injected by FXMLLoader

    private static String group = "Оберіть зі списку";

    private static int mark = 1;

    private final Tester tester = new Tester();

    @FXML
    void exitProgram() {
        exit();
    }

    @FXML
    void openAboutWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("about_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Про розробника");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    //Saves the value of the "counter" Spinner;
    private int counterValue = 0;

    void setOutput(String id) throws NullPointerException {
        if (counter.isDisabled()) {
            counter.setDisable(false);
            counter.getValueFactory().setValue(counterValue);
        }
//        System.out.println("Counter value: " + counter.getValueFactory().getValue());
        StringBuilder output = new StringBuilder();
        switch (id) {
            case "false_toggle" -> output.append(tester.false_out(counter.getValue()));

            case "mid_toggle" -> {
                if (!counter.isDisabled()) {
                    counter.setDisable(true);
                    counterValue = counter.getValueFactory().getValue();
                    counter.getValueFactory().setValue(1);
                }
                output.append(tester.mid_out());
            }

            case "true_toggle" -> output.append(tester.true_out(counter.getValue()));

            case "all_toggle" -> {
                output.append(tester.false_out(counter.getValue())).append("\n");
                output.append(tester.mid_out()).append("\n");
                output.append(tester.true_out(counter.getValue())).append("\n");
            }

            default -> output.append("Виникла помилка.");
        }
        outTField.setText(output.toString());
    }


    @FXML
    void setTOut(Event event) {
        //JavaFX part
        RadioButton toggle = (RadioButton) event.getSource();
        String id = toggle.getId();
        try {
            setOutput(id);
        } catch (NullPointerException nullPointerException) {
            outTField.setText("Умову не було обрано.");
        }
    }

    @FXML
    void setTOut_onKey(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCharacter());
        if (keyEvent.getCharacter().equals("\n") || keyEvent.getCharacter().equals(" ")) setTOut(keyEvent);
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert AboutButton != null : "fx:id=\"AboutButton\" was not injected: check your FXML file 'main_window.fxml'.";
        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'main_window.fxml'.";
        assert Toggles != null : "fx:id=\"Toggles\" was not injected: check your FXML file 'main_window.fxml'.";
        assert false_toggle != null : "fx:id=\"false_toggle\" was not injected: check your FXML file 'main_window.fxml'.";
        assert mid_toggle != null : "fx:id=\"mid_toggle\" was not injected: check your FXML file 'main_window.fxml'.";
        assert true_toggle != null : "fx:id=\"true_toggle\" was not injected: check your FXML file 'main_window.fxml'.";
        assert all_toggle != null : "fx:id=\"all_toggle\" was not injected: check your FXML file 'main_window.fxml'.";
        assert outTField != null : "fx:id=\"outTField\" was not injected: check your FXML file 'main_window.fxml'.";
        counter.setValueFactory(spinnerValueFactory);

        counter.valueProperty().addListener((obs, oldValue, newValue) -> {
            try {
//                System.out.println("Counter disabled: " + counter.isDisabled());
                if (!counter.isDisabled()) {
                    RadioButton selected = (RadioButton) Toggles.getSelectedToggle();
                    String id = selected.getId();
                    setOutput(id);
                }
            } catch (NullPointerException nullPointerException) {
                outTField.setText("Умову не було обрано.");
            }
        });
    }

    public static String getGroup() {
        return group;
    }

    public static void setGroup(String group) {
        HelloController.group = group;
    }

    public static int getMark() {
        return mark;
    }

    public static void setMark(int mark) {
        HelloController.mark = mark;
    }
}
