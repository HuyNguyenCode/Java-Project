package sceneBuilder;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
            Scene invoiceScene = new Scene(root);
            primaryStage.setScene(invoiceScene);
            primaryStage.setTitle("Book Store Management");
            primaryStage.show();

        } catch (IOException e) {}
    }
    
    public static void main(String[] args) {
        launch(args);   
    }
}