package sceneBuilder;
import java.io.IOException;

import database.ControllDB;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SigninController {

    @FXML
    private TextField email_signin;

    @FXML
    private PasswordField password_signin;

    @FXML
    private Label btnForgetPassword;

    @FXML
    private Label auth_switch_btn;

    @FXML
    private Button btn_auth;

    @FXML
    private Button btn_google;

    private Stage primaryStage;

    @FXML
    void switchScene(MouseEvent event) throws IOException { 
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene signupScene = new Scene(root);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.show(); 
    }

    void setPassword(PasswordField password_signin) {
        this.password_signin = password_signin;
    }

    void setEmail(TextField email_signin) {
        this.email_signin = email_signin;
    }

    String getPassword() {
        return password_signin.getText();
    }

    String getEmail() {
        return email_signin.getText();
    }

    @FXML
    void handleSignin(MouseEvent event) {
        if(event.getSource() == btn_auth) {
            // Handle event after clicking "Sign in" button here
            String email = email_signin.getText();
            String password = password_signin.getText();
            String p = ControllDB.getPasswordFromDB(email);
            if(email.isEmpty() || password.isEmpty()) return;
            if(p.equals("-1")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("SignIn Fail!");
                alert.setContentText("Your account not exists!");
                alert.showAndWait(); 
            }
            else if(password.equals(p)){
                // App run
                //
                //
                System.out.println("Login Success!");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Login Fail!");
                alert.setContentText("Your password Wrong!");
                alert.showAndWait(); 
            }
        }

        if(event.getSource() == btn_google) {
            // Handle event after clicking "Sign in with Google" button here
        }
    }

    @FXML
    void handleForgetPassword(MouseEvent event) throws IOException {
        if(event.getSource() == btnForgetPassword) {
            // Handle event after clicking "Forget Password" button here
            
        }
    }


}
