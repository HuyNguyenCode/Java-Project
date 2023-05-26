package sceneBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private TextField textfiledAuthor;

    @FXML
    private TextField textfiledCategory;

    @FXML
    private TextField textfiledID;

    @FXML
    private TextField textfiledPrice;

    @FXML
    private TextField textfiledPublisher;

    @FXML
    private TextField textfiledStock;

    @FXML
    private TextField textfiledTitle;

    @FXML
    private TextField textfiledYear;

    public int getTextfiledID() {
        return Integer.parseInt(textfiledID.getText());
    }

    public int getTextfiledYear() {
        return Integer.parseInt(textfiledYear.getText());
    }

    public int getTextfiledStock() {
        return Integer.parseInt(textfiledStock.getText());
    }

    public double getTextfiledPrice() {
        return Double.parseDouble(textfiledPrice.getText());
    }

    public String getTextfiledTitle() {
        return textfiledTitle.getText();
    }

    public String getTextfiledAuthor() {
        return textfiledAuthor.getText();
    }

    public String getTextfiledPublisher() {
        return textfiledPublisher.getText();
    }

    public String getTextfiledCategory() {
        return textfiledCategory.getText();
    }   
}

