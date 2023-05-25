package sceneBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Book;

public class BookController {
    
    @FXML
    private Label authorName;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookTitle;

    @FXML
    private VBox bookItem;

    @FXML
    public void setData(Book book) {
        Image image = new Image(getClass().getResourceAsStream(book.getImageSrc()));
        bookImage.setImage(image);
        bookTitle.setText(book.getTitle());
        authorName.setText(book.getAuthor());
    }

    @FXML
    public void optionMenu(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addToYourShelves = new MenuItem("Add to Your Shelves");
        MenuItem addToFavorite = new MenuItem("Add to Favorite");
        MenuItem addToCart = new MenuItem("Add to Cart");
        contextMenu.getItems().setAll(addToYourShelves, addToFavorite, addToCart);
        contextMenu.show(bookItem, event.getScreenX(), event.getScreenY());        
    }
}
