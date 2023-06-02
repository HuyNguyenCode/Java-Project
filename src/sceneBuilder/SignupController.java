package sceneBuilder;

import java.io.IOException;
import database.ControllDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class SignupController {


    @FXML
    private CheckBox agreeCheckbox;

    @FXML
    private TextField email_signup;

    @FXML
    private TextField fullname_signup;

    @FXML
    private PasswordField password_signup;

    @FXML
    private Label auth_switch_btn;

    @FXML
    private Button btn_auth;

    @FXML
    private Button btn_google;

    @FXML
    private VBox btn_input;

    @FXML
    private AnchorPane signup_scene;

    @FXML
    private Label termsOfService_text;
    private Stage primaryStage;

    @FXML
    void switchScene(MouseEvent event) throws IOException { 
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene signInScene = new Scene(root);
        primaryStage.setScene(signInScene);
        primaryStage.setTitle("Sign In");
        primaryStage.show();
    }


    void setPassword(PasswordField password_signup) {
        this.password_signup = password_signup;
    }

    void setFullname(TextField fullname_signup) {
        this.fullname_signup = fullname_signup;
    }

    String getFullname() {
        return fullname_signup.getText();
    }

    void setEmail(TextField email_signup) {
        this.email_signup = email_signup;
    }

    String getPassword() {
        return password_signup.getText();
    }

    String getEmail() {
        return email_signup.getText();
    }

    @FXML
    void handleClicks(MouseEvent event) {
        if(event.getSource() == btn_auth) {
            if (agreeCheckbox.isSelected()) {
             // Handle the event after ticking the checkbox and clicking "Sign up" here
                String fullName = fullname_signup.getText();
                String email = email_signup.getText();
                String password = password_signup.getText();
                User temp = new User(fullName,email,password);
                boolean checkSignUp = ControllDB.insertValuesIntoUsers(temp);
                if(checkSignUp == false){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("SignUp Fail!");
                    alert.setContentText("Your account already exists!");
                    alert.showAndWait(); 
                } else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SignUp Success!");
                    alert.setContentText("You can signin with new account!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Agree on term!");
                alert.setContentText("Please indicate that you have read and agree to the 'Terms of service'!");
                alert.showAndWait(); 
            }
        }

        else if(event.getSource() == btn_google) {
            // Handle event after clicking "Sign up with Google" button here
            
        }

        else if(event.getSource() == termsOfService_text) {
            // Handle event after clicking "term of service"

        }
    }

}
