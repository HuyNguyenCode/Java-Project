package sceneBuilder;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private TextField auth_input;

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

}
