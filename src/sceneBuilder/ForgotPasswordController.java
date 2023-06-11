package sceneBuilder;
import java.io.IOException;
import database.ControllUsers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Tool;

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

    private Class<ForgotPasswordController> forgotPasswordClass = ForgotPasswordController.class;


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
            String password = ControllUsers.getPasswordFromDB(getEmail());
            if(password.equals("-1")){
                Tool.showAlert(Alert.AlertType.INFORMATION, "Get Password Fail!", "Your account not exists!");
            } else if (password != null){
                Tool.showAlert(Alert.AlertType.INFORMATION, "Get Password Success!", "Your Password: " + password);
            }
        }
    }

    @FXML
    void switchScene(MouseEvent event) throws IOException {
        if (event.getSource() == btnSignin) {
            Tool.loadScene(forgotPasswordClass, "Signin", event);
        }

        else if (event.getSource() == btnSignup) {
            Tool.loadScene(forgotPasswordClass, "Signup", event);
        }
    }

}
