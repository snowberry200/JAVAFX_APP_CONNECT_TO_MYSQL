package com.example.logocompanyfx;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView logoController;
    @FXML
    private TextField userNameController;
    @FXML
    private TextField passwordController;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(logoController);
        scale.setDuration(Duration.seconds(2));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setAutoReverse(true);
        scale.setByX(2);
        scale.setByY(2);
        scale.play();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectDB.loginUser(event, userNameController.getText(), passwordController.getText());

            }
        });

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectDB.changeScene(event,null,"sign up","signUp.fxml");

            }
        });

    }

//        String username = userNameController.getText();
//        validatorController.setText("You tried to login");
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomeScene.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//
//            userNameController.getText();
//            LoggedInController controller = loader.getController();
//            controller.displayName(username);
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }












//        if (!userNameController.getText().isBlank() && !passwordController.getText().isBlank()) {
//           // validateLogin();
//        } else {
//            validatorController.setText("please enter username and password");
//        }

    }


//    public void onCancel(ActionEvent event) {
//        Stage stage = (Stage) cancelController.getScene().getWindow();
//        stage.close();
//    }

//    public void validateLogin() {
//        ConnectDB connectNow= new ConnectDB();
//        Connection connectDB = connectNow.getConnection() ;
//        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = ' " + userNameController.getText() + "'  AND password = '"+ passwordController.getText() + "' AND ' ";
//   try{
//       Statement statement = ConnectDB.createStatement();
//       ResultSet querryResult = statement.executeQuery(verifyLogin);
//       while (querryResult.next()){
//           if(querryResult.getInt(1)==1){
//               validatorController.setText("Congrats!");
//
//           }else{
//               validatorController.setText("invalid login please try again");
//           }
//       }
  // }catch(Exception e){
     //  System.out.println(e);
 //  }
   // }

