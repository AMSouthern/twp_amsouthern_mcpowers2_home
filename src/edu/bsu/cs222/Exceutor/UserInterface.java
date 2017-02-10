package edu.bsu.cs222.Exceutor;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

import static edu.bsu.cs222.XML.Retrieving.connectToWikipedia;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class deals with all of the information that goes to and from the User.
 *
 * Thank you lmkelley and xwang! Looking at your UI was extremely helpful.
 */
public class UserInterface extends Application {
    
    private Button OKButton = new Button("OK");
    private TextField inputField = new TextField();
    private Label outputField = new Label();

    public static void main(String[] args){

        launch(args);
    }


    @Override
    public void start(Stage UserField) throws Exception {
        configure(UserField);
        configureOKButton();
    }

    private void configureOKButton() {
        OKButton.setOnAction(event -> {
            try {
                connectToWikipedia(" ", (inputField.getText())).toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void configure(Stage UserField) {
        UserField.setTitle("Wikipedia Research");
        UserField.setScene(new Scene(createRoot()));
        UserField.sizeToScene();
        inputField.setMinWidth(500);

    }

    private Parent createRoot() {
        VBox root = new VBox();
        root.getChildren().addAll(new Label("Search Term: "), inputField, OKButton, new Label("Output: "), outputField);
        return root;
    }

    private void retrieveQueryInformation(){
        disableUI();

        //

        enableUI();

    }

    private void enableUI() {
        inputField.setEditable(true);
        OKButton.setDisable(false);
    }

    private void disableUI() {
        inputField.setEditable(false);
        OKButton.setDisable(true);
    }
}
