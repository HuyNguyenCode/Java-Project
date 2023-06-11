package sceneBuilder;
import java.io.IOException;
import database.ControlUsers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Tool;
import model.User;

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

    private static User user;

    private Class<SigninController> signinClass = SigninController.class;

    @FXML
    void switchScene(MouseEvent event) throws IOException {
        Tool.loadScene(signinClass, "Signup", event);
    }

    public static User getUser(){
        return user;
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
    void handleSignin(MouseEvent event) throws IOException {
        if(event.getSource() == btn_auth) {
            // Handle event after clicking "Sign in" button here

            String email = email_signin.getText();
            String password = password_signin.getText();
            String p = ControlUsers.getPasswordFromDB(email);
            if(email.isEmpty() || password.isEmpty()) return;
            if(p.equals("-1")){
                Tool.showAlert(Alert.AlertType.WARNING,
                "SignIn Fail!", 
                "Your account not exists!");
            }
            else if(password.equals(p)){
                System.out.println("Login Success!");
                user = ControlUsers.getUserFromDB(email);
                Tool.loadScene(signinClass, "Dashboard", event);
            }
            else{
                Tool.showAlert(Alert.AlertType.WARNING, 
                "Login Fail!", 
                "Your password Wrong!");
            }
        }

        else if(event.getSource() == btn_google) {
            // Handle event after clicking "Sign in with Google" button here
        }
    }

    @FXML
    void handleForgetPassword(MouseEvent event) throws IOException {
        if(event.getSource() == btnForgetPassword) {
            // Handle event after clicking "Forget Password" button here
            Tool.loadScene(signinClass, "ForgotPassword", event);
        }
    }


}
