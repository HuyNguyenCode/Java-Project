package sceneBuilder;
import java.io.IOException;

import database.ControllDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            //handle Forgot password
            String password = ControllDB.getPasswordFromDB(getEmail());
            if(password.equals("-1")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Get Password Fail!");
                alert.setContentText("Your account not exists!");
                alert.showAndWait(); 
            }
            else if (password != null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Get Password Success!");
                alert.setContentText("Your Password: " + password);
                alert.showAndWait();
            }
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

        else if (event.getSource() == btnSignup) {
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene signupScene = new Scene(root);
            primaryStage.setScene(signupScene);
            primaryStage.setTitle("Signup");
            primaryStage.show(); 
        }
    }

}
