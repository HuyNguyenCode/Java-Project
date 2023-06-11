package sceneBuilder;
import java.io.IOException;

import database.ControllUsers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Tool;
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

    private Class<SigninController> signinClass = SigninController.class;

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
    void switchScene(MouseEvent event) throws IOException { 
        Tool.loadScene(signinClass, "Signin", event);
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        if(event.getSource() == btn_auth) {
            if (agreeCheckbox.isSelected()) {
             // Handle the event after ticking the checkbox and clicking "Sign up" here
                String fullName = fullname_signup.getText();
                String email = email_signup.getText();
                String password = password_signup.getText();
                User temp = new User(fullName,email,password);
                boolean checkSignUp = ControllUsers.insertValuesIntoUsers(temp);
                if(checkSignUp == false){ 
                    Tool.showAlert(Alert.AlertType.WARNING, 
                    "SignUp Fail!",
                    "Your account already exists!");
                } else{
                    Tool.showAlert(Alert.AlertType.INFORMATION,
                    "SignUp Success!", 
                    "You can signin with new account!");
                    Tool.loadScene(signinClass, "Signin", event);
                }
            } else {
                Tool.showAlert(Alert.AlertType.WARNING,
                "Warning Agree on term!",
                "Please indicate that you have read and agree to the 'Terms of service'!");
            }
        }

        else if(event.getSource() == btn_google) {
            // Handle event after clicking "Sign up with Google" button here
            // Tool.loadScene(signinClass, "Webview", event);
            // Desktop desktop = Desktop.getDesktop();
            // desktop.browse(java.net.URI.create("https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost/src/webhandle/LoginGoogleHandler&response_type=code&client_id=1006621371158-1p5a6ulo2ct6m68isapqanff7o0ur7su.apps.googleusercontent.com&approval_prompt=force"));
        }

        else if(event.getSource() == termsOfService_text) {
            // Handle event after clicking "term of service"

        }
    }

}
