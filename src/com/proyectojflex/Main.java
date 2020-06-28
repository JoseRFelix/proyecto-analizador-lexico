package com.proyectojflex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jflex.exceptions.SilentExit;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String[] flexPath = {System.getProperty("user.dir") + "/src/com/proyectojflex/Lexer.flex"};
        generateAnalyzer(flexPath);

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Analizador Léxico");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void generateAnalyzer(String[] path) {
        try {
            jflex.Main.generate(path);
        } catch(SilentExit e) {
            System.out.println("Error generating lexer.");
        }
    }
}
