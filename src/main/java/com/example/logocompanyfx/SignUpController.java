package com.example.logocompanyfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField firstNameController;
    @FXML
    private TextField lastNameController;
    @FXML
    private TextField signupUserName;
    @FXML
    private TextField signupPassword;
    @FXML
    private Button ButtonSignUP;
    @FXML
    private Button button_login;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //on login button clicked
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectDB.changeScene(event,null,"log in","scene.fxml");

            }
        });
        //on sign up button clicked
        ButtonSignUP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!signupUserName.getText().trim().isEmpty() && !signupPassword.getText().trim().isEmpty()
                        && !firstNameController.getText().trim().isEmpty() && !lastNameController.getText().trim().isEmpty()) {
                    ConnectDB.signUpUser(event, firstNameController.getText(), lastNameController.getText(), signupUserName.getText(), signupPassword.getText());
                } else {
                    System.out.println("please fill in all required fields");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please fill in all required fields");
                    alert.show();
                }

            }
        });

    }
}
