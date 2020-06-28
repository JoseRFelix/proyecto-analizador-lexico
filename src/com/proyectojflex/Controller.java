package com.proyectojflex;

import com.sun.javafx.fxml.expression.Expression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private TextArea expressionsTextArea;

    @FXML
    private ListView<String> resultListView;

    public void clearForm() {
        expressionsTextArea.clear();
    }

    public void analyze() {
        String rawExpressions = expressionsTextArea.getText().replaceAll("\n", System.getProperty("line.separator"));
        String[] expressions = rawExpressions.split("\n");

        List<String> results = new ArrayList();

        for (int i = 0; i < expressions.length; ++i) {
            String currentExpression = expressions[i];

            StringReader toAnalyze = new StringReader(currentExpression);
            Lexer lexer = new Lexer(toAnalyze);

            try {
                String tokens = "";
                String previousToken = "";

                while (!lexer.yyatEOF()) {
                    Tokens token = lexer.yylex();

                    if (token == null) {
                        break;
                    }

                    if (token.name() == previousToken && (token.name() != "DIGITO" || token.name() == "PARENTESIS")) {
                        String[] oldTokens = tokens.split("\n");
                        String[] newTokens = Arrays.copyOf(oldTokens, oldTokens.length-1);

                        tokens = joinArray(newTokens);
                        tokens += "ERROR\n";
                        continue;
                    }

                    tokens += token.name() + "\n";
                    previousToken = token.name();
                }

                if (tokens.contains("ERROR")) {
                    tokens += "INVALIDO" + "\n";
                } else {
                    tokens += "VALIDO" + "\n";
                }

                results.add(tokens);
            } catch (IOException e) {
            }
        }

        ObservableList<String> listViewItems = FXCollections.observableArrayList(results);
        resultListView.setItems(listViewItems);
    }

    String joinArray(String[] array) {
        String joined = "";

        for(int i = 0; i < array.length; i++) {
            joined += array[i] + "\n";
        }

        return joined;
    }
}
