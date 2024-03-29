package com.example.logocompanyfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController  implements Initializable {
    @FXML
    private Label welcomeLabel;
    @FXML
private Button buttonLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    ConnectDB.changeScene(event,null,"login","scene.fxml");
                }catch(Exception e ){
                    System.out.println(e);
                }

            }
        });
    }
    public void displayName(String userName) {
        try {
            welcomeLabel.setText(STR."Welcome \{userName}");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
