package edu.bsu.cs222.Exceutor;

import edu.bsu.cs222.Wikipedia.Wikipedia;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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

    public static void main(String[] args){

        launch(args);
    }


    private Button OKButton = new Button("OK");
    private TextField emailField = new TextField();
    private TextField queryField = new TextField();
    private Label outputField = new Label();

    @Override
    public void start(Stage UserField) throws Exception {
        configure(UserField);
        configureOKButton();
    }

    private void configure(Stage UserField) {
        UserField.setTitle("Wikipedia Research");
        UserField.setScene(new Scene(createRoot()));
        UserField.sizeToScene();
        emailField.setMinWidth(500);
        outputField.setMinSize(500,600);
        UserField.show();

    }



    private void configureOKButton() {
        OKButton.setOnAction(event -> {
            try {
                getQueryInformation();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void getQueryInformation() throws Exception {
        disableUI();
        Wikipedia wikiPage = new Wikipedia();
        String informationToPrint = wikiPage.queryInformation(emailField.getText(), queryField.getText());
        outputField.setText(informationToPrint);
        enableUI();
    }

    private Parent createRoot() {
        VBox root = new VBox();
        root.getChildren().addAll(new Label("Search Term: "), queryField, OKButton, new Label("Output: "), outputField);
        return root;
    }


    private void enableUI() {
        queryField.setEditable(true);
        emailField.setEditable(true);
        OKButton.setDisable(false);
    }

    private void disableUI() {
        queryField.setEditable(false);
        emailField.setEditable(false);
        OKButton.setDisable(true);
    }
}
