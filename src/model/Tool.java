package model;
import java.util.Optional;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sceneBuilder.SigninController;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public class Tool {
    private static Stage primaryStage;
    public static void loadScene(Class<?> classBeingCalled, String sceneName, MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(classBeingCalled.getResource(sceneName + ".fxml"));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(sceneName + " Management");
        primaryStage.centerOnScreen();
        primaryStage.show(); 
    }

    public static FXMLLoader getFxml(Class<?> classBeingCalled, String fxmlFileName) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(classBeingCalled.getResource(fxmlFileName + ".fxml"));
        return fxmlLoader;
    }

    public static void showAlert(Alert.AlertType alertType, String alertTitle, String alertContentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setContentText(alertContentText);
        alert.showAndWait(); 
    }

    public static Optional<ButtonType> showConfirmAlert(String alertTitle, String alertContentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(alertTitle);
        alert.setContentText(alertContentText);
        return alert.showAndWait();
    }

    public static Optional<ButtonType> showDialogPaneOptional(String dialogTitle, DialogPane dialogPaneName) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPaneName);
        dialog.setTitle(dialogTitle);
        return dialog.showAndWait();
    }

    public static void showDialogPane(String dialogTitle, DialogPane dialogPaneName) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPaneName);
        dialog.setTitle(dialogTitle);
        dialog.showAndWait();
    }

    public static String getUserFullName(){
        User user = SigninController.getUser();
        if(user == null){
            return "______";
        }
        return user.getFullName();
    }
}
