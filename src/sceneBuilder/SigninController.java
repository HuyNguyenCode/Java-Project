package sceneBuilder;
import java.io.IOException;

import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SigninController {

    @FXML
    private TextField auth_input;

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

}
