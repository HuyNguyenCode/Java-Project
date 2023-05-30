package sceneBuilder;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ForgotPasswordController {

    @FXML
    private Label auth_switch_btn;
    
    @FXML
    private Text btnSignin;

    @FXML
    private Label btnSignup;

    @FXML
    private Button btn_auth;

    @FXML
    private TextField email_forgotPassword;

    void setEmail(TextField email_forgotPassword) {
        this.email_forgotPassword = email_forgotPassword;
    }

    String getEmail() {
        return email_forgotPassword.getText();
    }


    @FXML
    void handleClicks(MouseEvent event) {
        if (event.getSource() == btn_auth) {
            System.out.println(getEmail());
        }
    }

    @FXML
    void switchScene(MouseEvent event) throws IOException {
        if (event.getSource() == btnSignin) {
            Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene signupScene = new Scene(root);
            primaryStage.setScene(signupScene);
            primaryStage.setTitle("Signin");
            primaryStage.show(); 
        }

        if (event.getSource() == btnSignup) {
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene signupScene = new Scene(root);
            primaryStage.setScene(signupScene);
            primaryStage.setTitle("Signup");
            primaryStage.show(); 
        }
    }

}
