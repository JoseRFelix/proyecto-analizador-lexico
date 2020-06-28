package com.proyectojflex;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private TextArea expressionsTextArea;

    @FXML
    private ListView<String> resultListView;

    public void clearForm() {
        expressionsTextArea.clear();
    }

    public void analyze() {
        ObservableList<String> names = FXCollections.observableArrayList(
                "Julia\nHEY\nHEYY", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
        resultListView.setItems(names);
        // TODO: add logic here
    }
}
