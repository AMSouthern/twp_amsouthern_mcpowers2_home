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

import static javafx.application.Platform.exit;


/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class deals with all of the information that goes to and from the User.
 */
public class UserInterface extends Application {

    public static void main(String[] args){

        launch(args);
    }


    private Button OKButton = new Button("OK");
    private Button CancelButton = new Button("Cancel");
    private TextField emailField = new TextField();
    private TextField queryField = new TextField();
    private Label outputField = new Label();

    @Override
    public void start(Stage UserField) throws Exception {
        configure(UserField);
        configureOKButton();
        configureCancelButton();
    }

    private void configure(Stage UserField) {
        UserField.setTitle("Wikipedia Research");
        UserField.setScene(new Scene(createRoot()));
        UserField.sizeToScene();
        emailField.setMinWidth(500);
        queryField.setMinWidth(500);
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


    private void configureCancelButton() {
        CancelButton.setOnAction(event -> {
            try {
                exit();
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
        root.getChildren().addAll(new Label("Email Address: "), emailField, new Label("Search Term: "), queryField, OKButton, CancelButton, new Label("Information: "), outputField);
        return root;
    }


    private void enableUI() {
        queryField.setEditable(true);
        emailField.setEditable(true);
        OKButton.setDisable(false);
        CancelButton.setDisable(false);
    }

    private void disableUI() {
        queryField.setEditable(false);
        emailField.setEditable(false);
        OKButton.setDisable(true);
        CancelButton.setDisable(true);
    }
}
