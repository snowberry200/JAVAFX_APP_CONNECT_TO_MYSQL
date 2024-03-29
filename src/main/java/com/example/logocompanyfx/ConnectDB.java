package com.example.logocompanyfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

public class ConnectDB {

    static Parent root;

    //NOTE THE PURPOSE OF THIS CLASS IS TO LET...
    // THESE FUNCTIONS  TO ASSIST IN PUTTING PARAMETERS  FOR THE ARGUMENTS  title,username,and fxml files into corresponding Controllers


    public static void changeScene(ActionEvent event, String username, String title, String fxmlFile) {

        //if the username is not empty then that means the user has logged in so the scene will be changed to welcomeScene.fxml
        if (username != null) {

            try {
                FXMLLoader loader = new FXMLLoader(ConnectDB.class.getResource(fxmlFile));

                root = loader.load();
                LoggedInController controller = loader.getController();
                controller.displayName(username);

            } catch (Exception e) {
                System.out.println(e);
            }
            //else if the username is null this but scene is being changed, this mean the user is changing scene to the signup page (signUp.fxml)
            //therefore there is no need to create a new fxml loader object with controller  functions .
        } else {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(ConnectDB.class.getResource(fxmlFile)));
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        //in changing scene no matter the condition the stage and scene must be set and shown therefore
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();


    }

    //user signup
    public static void signUpUser(ActionEvent event, String firstname, String lastname, String username, String password) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckIfUserExist = null;

        //if a user want to sign up he needs to establish a connection with the dataBase so ...
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ NAME  SCHEMA", "USERNAME TO YOUR DB", "PASSWORD TO DB");
            psCheckIfUserExist = connection.prepareStatement("SELECT * FROM //NAME OF SQLTABLE // WHERE username = ?");
            psCheckIfUserExist.setString(1, username);
            resultSet = psCheckIfUserExist.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("user already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you can not use this username");
                alert.show();

            } else {
                psInsert = connection.prepareStatement("INSERT INTO user_account(firstname,lastname,username,password) VALUES(?,?,?,?)");
                psInsert.setString(1, firstname);
                psInsert.setString(2, lastname);
                psInsert.setString(3, username);
                psInsert.setString(4, password);
                psInsert.executeUpdate();

                changeScene(event, username, "welcome", "welcomeScene.fxml");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (psCheckIfUserExist != null) {
                try {
                    psCheckIfUserExist.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }

    }


    //logging in LOGIC
    public static void loginUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //connection to the database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ user", "root", "Nagus200$");

            //retrieving the password from the dataBase and compare it to what the user has provided if it corresponds with the stored username
            preparedStatement = connection.prepareStatement("SELECT  password FROM  user_account WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            //if result is not found in the dataBase,
            if (!resultSet.isBeforeFirst()) {
                System.out.println("user not found in database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("provided credentials are incorrect");
                alert.show();

                //if user dose exist, then compare passwords
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, username, "welcome", "welcomeScene.fxml");
                    } else {
                        System.out.println("password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }

                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }


}



