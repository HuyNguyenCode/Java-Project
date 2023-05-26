package sceneBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateBookController {

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

    public TextField getTextfiledAuthor() {
        return textfiledAuthor;
    }

    public void setTextfiledAuthor(String inputAuthor) {
        this.textfiledAuthor.setText(inputAuthor);
    }

    public TextField getTextfiledCategory() {
        return textfiledCategory;
    }

    public void setTextfiledCategory(String inputCategory) {
        this.textfiledCategory.setText(inputCategory);
    }

    public TextField getTextfiledID() {
        return textfiledID;
    }

    public void setTextfiledID(String inputID) {
        this.textfiledID.setText(inputID);
    }

    public TextField getTextfiledPrice() {
        return textfiledPrice;
    }

    public void setTextfiledPrice(String inputPrice) {
        this.textfiledPrice.setText(inputPrice);
    }

    public TextField getTextfiledPublisher() {
        return textfiledPublisher;
    }

    public void setTextfiledPublisher(String inputPublisher) {
        this.textfiledPublisher.setText(inputPublisher);
    }

    public TextField getTextfiledStock() {
        return textfiledStock;
    }

    public void setTextfiledStock(String inputStock) {
        this.textfiledStock.setText(inputStock);
    }

    public TextField getTextfiledTitle() {
        return textfiledTitle;
    }

    public void setTextfiledTitle(String inputTitle) {
        this.textfiledTitle.setText(inputTitle);
    }

    public TextField getTextfiledYear() {
        return textfiledYear;
    }

    public void setTextfiledYear(String inputYear) {
        this.textfiledYear.setText(inputYear);
    }

    
}


