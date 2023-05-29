package sceneBuilder;
import java.io.IOException;


import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            System.out.println(getEmail());
            System.out.println(getPassword());
        }

        if(event.getSource() == btn_google) {
            // Handle event after clicking "Sign in with Google" button here
            
        }
    }

    @FXML
    void handleForgetPassword(MouseEvent event) {
        if(event.getSource() == btnForgetPassword) {
            // Handle event after clicking "Forget Password" button here
        }
    }


}
