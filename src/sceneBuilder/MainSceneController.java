package sceneBuilder;
import model.Book;
import model.Tool;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import database.ControllDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainSceneController implements Initializable {

    @FXML
    private Label userNameInScene;

    @FXML
    private HBox cardLayout;

    //Btn
    @FXML
    private HBox btnAddBook;
    
    @FXML
    private HBox btnDashboard;

    @FXML
    private HBox btnDelete;
    
    @FXML
    private HBox btnFavorite;
    
    @FXML
    private HBox btnHistory;
    
    @FXML
    private HBox btnPurchase;
    
    @FXML
    private HBox btnUpdate;

    @FXML
    private HBox btnShelves;

    @FXML
    private Label btnTableView;

    @FXML
    private HBox btnBooks;
    
    @FXML
    private HBox btnInvoices;

    @FXML
    private HBox btnStaffs;

    @FXML
    private HBox btnSuppliers;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnbookManage;

    @FXML
    private VBox pnBooksManagement;

    @FXML
    private VBox sideNav;

    @FXML
    private GridPane bookContainer;

    private List<Book> recommendedBooks;

    @FXML
    private GridPane bookGridPane;
    
    @FXML
    private TableView<Book> booksTableView;

    @FXML
    private ScrollPane booksBookstoreView;

    @FXML
    private TableColumn<Book, Integer> colID;

    @FXML
    private TableColumn<Book, Integer> colStock;

    @FXML
    private TableColumn<Book, Integer> colYear;

    @FXML
    private TableColumn<Book, Double> colPrice;

    @FXML
    private TableColumn<Book, String> colAuthor;

    @FXML
    private TableColumn<Book, String> colCategory;

    @FXML
    private TableColumn<Book, String> colPublisher;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private TableColumn<Book, Button> colDetail;

    @FXML
    private StackPane stackPane;

    @FXML
    private TextField searchInput;
    
    ObservableList<Book> books = FXCollections.observableArrayList();   

    private Class<MainSceneController> mainSceneClass = MainSceneController.class;

    public void initialize(URL location, ResourceBundle resources) {

        // String userName = SigninController.user.getFullName();
        // this.userNameInScene.setText(userName);
        recommendedBooks = new ArrayList<>(recommendedBooks());

        try {
            
            //load fxml and controller
            for (int i = 0; i < recommendedBooks.size(); i++) {
                FXMLLoader fxmlLoader = Tool.getFxml(mainSceneClass, "Card");
                HBox carBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recommendedBooks.get(i));
                cardLayout.getChildren().add(carBox);
            }

            books = ControllDB.getListFromBooks();
            addBooktoTable(books);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addBooktoTable( ObservableList<Book> books) {
        colID.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        colStock.setCellValueFactory(new PropertyValueFactory<Book, Integer>("Stock"));
        colYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("Year"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("Price"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Book, String>("Category"));
        colPublisher.setCellValueFactory(new PropertyValueFactory<Book, String>("Publisher"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
        booksTableView.setItems(books);
    }

    private List<Book> recommendedBooks() {
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setTitle("RICH DAD\nPOOR DAD");
        book.setImageSrc("/img/book/rich_dad_poor_dad.jpg");
        book.setAuthor("Robert T.Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setTitle("THE WARREN\nBUFFET WAY");
        book.setImageSrc("/img/book/the_warren_buffet_way.jpg");
        book.setAuthor("Robert G.Hagstorm");
        ls.add(book);

        book = new Book();
        book.setTitle("THE SEVEN HABITS\nOF HIGHLY EFFECTIVE PEOPLE");
        book.setImageSrc("/img/book/the_seven_habits_of_highly_effective_people.jpg");
        book.setAuthor("Stephen R.Covey");
        ls.add(book);
        return ls;
    }

    @FXML
    void handleSwitch(MouseEvent event) throws IOException {
        List<VBox> lsPn = new ArrayList<>();
        lsPn.add(pnBooksManagement); 
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        //Handle event on addbtn
        if (event.getSource() == btnAddBook) {
            pnBooksManagement.setVisible(true);
            //Show dialog to add a new book
            FXMLLoader fxmlLoader = Tool.getFxml(mainSceneClass, "AddBook");
            DialogPane addBookDialogPane = fxmlLoader.load();
            Optional<ButtonType> clickedButton = Tool.showDialogPaneOptional("Add new book", addBookDialogPane);
            AddBookController addBook = fxmlLoader.getController();  


            if (clickedButton.get() == ButtonType.OK) { 
                //Add books to tableview
                //Iterate through tableview to check duplicate title
                boolean isTitleDuplicate = false;
                for (Book book : books) {
                    if(book.getTitle().toLowerCase().equals(addBook.getTextfiledTitle().toLowerCase())) {
                        isTitleDuplicate = true;
                        break; 
                    } 
                }

                if (isTitleDuplicate) {
                    Tool.showAlert(Alert.AlertType.ERROR, "Can't add new book!", "You have entered an existing book title");
                } else {
                    Book book = new Book(
                        -1,
                        addBook.getTextfiledYear(), 
                        addBook.getTextfiledStock(), 
                        addBook.getTextfiledPrice(), 
                        addBook.getTextfiledTitle(),
                        null, 
                        addBook.getTextfiledAuthor(), 
                        addBook.getTextfiledPublisher(), 
                        addBook.getTextfiledCategory()
                    );
                    boolean checkInsert = ControllDB.insertValuesIntoBooks(book);
                    if(checkInsert == true){
                        books.add(ControllDB.getLastestBook());
                    }
                    addBooktoTable(books);
                }
            }
        } else if (event.getSource() == btnExit) {
            Optional<ButtonType> result = Tool.showConfirmAlert("Confirm to exit program !", "Do you want to exit ?");
            if (result.get() == ButtonType.OK) { 
                javafx.application.Platform.exit();
            }
        } else if (event.getSource() == btnInvoices) {
            Tool.loadScene(mainSceneClass,"Invoice", event);
        } else if (event.getSource() == btnStaffs) {
            Tool.loadScene(mainSceneClass,"Staffs", event);
        } else if (event.getSource() == btnSuppliers) {
            Tool.loadScene(mainSceneClass,"Suppliers", event);
        } else if (event.getSource() == btnDashboard) {
            Tool.loadScene(mainSceneClass,"Dashboard", event);
        }
    }

    @FXML
    void handleUpdate(MouseEvent event) throws IOException { 
    
        FXMLLoader fxmlLoader = Tool.getFxml(mainSceneClass, "UpdateBook");
        DialogPane updateBookDialogPane = fxmlLoader.load();
        UpdateBookController updateBook = fxmlLoader.getController();
        updateBook.getTextfiledID().setEditable(false);
        
        Book clickedBook = booksTableView.getSelectionModel().getSelectedItem();
        
        if (event.getSource() == btnUpdate) {
            if(books.isEmpty()) {
                Tool.showAlert(Alert.AlertType.ERROR, "Empty board error!", "Unable to update the information in the table because the table is empty !");
            } else {
                updateBook.setTextfiledID(String.valueOf(clickedBook.getId()));
                updateBook.setTextfiledPrice(String.valueOf(clickedBook.getPrice()));
                updateBook.setTextfiledYear(String.valueOf(clickedBook.getYear()));
                updateBook.setTextfiledStock(String.valueOf(clickedBook.getStock()));
                updateBook.setTextfiledAuthor(clickedBook.getAuthor());
                updateBook.setTextfiledCategory(clickedBook.getCategory());
                updateBook.setTextfiledPublisher(clickedBook.getPublisher());
                updateBook.setTextfiledTitle(clickedBook.getTitle());
               
                Optional<ButtonType> clickedButton = Tool.showDialogPaneOptional("Update book", updateBookDialogPane);

                if(clickedButton.get() == ButtonType.APPLY) { 
                    ObservableList<Book> currentTableData = booksTableView.getItems();
                    int currentID = Integer.parseInt(updateBook.getTextfiledID().getText());
                    for (Book book : currentTableData) {
                        if(book.getId() == currentID) {
                            book.setTitle(updateBook.getTextfiledTitle().getText());
                            book.setAuthor(updateBook.getTextfiledAuthor().getText());
                            book.setCategory(updateBook.getTextfiledCategory().getText());
                            book.setPublisher(updateBook.getTextfiledPublisher().getText());
                            book.setYear(Integer.parseInt(updateBook.getTextfiledYear().getText()));
                            book.setPrice(Double.parseDouble(updateBook.getTextfiledPrice().getText()));
                            book.setStock(Integer.parseInt(updateBook.getTextfiledStock().getText()));
                            ControllDB.updateBooks(book);
                            booksTableView.setItems(currentTableData);
                            booksTableView.refresh();
                            break;
                        }
                        
                    }
                    
                }
            }
        }

        else if (event.getSource() == btnDelete) { 
            if(books.isEmpty()) {
                Tool.showAlert(Alert.AlertType.ERROR, "Empty board error!", "Unable to update the information in the table because the table is empty !");
            } else {
                Optional<ButtonType> result = Tool.showConfirmAlert("Confirm to delete a book !", "Do you want to delete a book ?");
                if (result.get() == ButtonType.OK) { 
                    booksTableView.getItems().removeAll(clickedBook);
                    ControllDB.deleteFromBooks(clickedBook);
                }
            }
        }  
    }       

    @FXML
    void searchBook() {
        FilteredList<Book> filterData = new FilteredList<>(books,e->true);
        searchInput.setOnKeyReleased(e -> {
            searchInput.textProperty().addListener(((observable, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super Book>) cust -> {
                    if (newValue == null) {
                        return true;
                    } 
                    String toLowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(cust.getId()).contains(newValue)) {
                        return true;
                    } else if (cust.getCategory().toLowerCase().contains(toLowerCaseFilter)) {
                        return true;
                    } else if (cust.getPublisher().toLowerCase().contains(toLowerCaseFilter)) {
                        return true;
                    } else if (cust.getTitle().toLowerCase().contains(toLowerCaseFilter)) {
                        return true;
                    } else if (cust.getAuthor().contains(newValue)) {
                        return true;
                    } else if (String.valueOf(cust.getPrice()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(cust.getStock()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(cust.getYear()).contains(newValue)) {
                        return true;
                    } 
                return false;
                });
            }));

            final SortedList<Book> booksSortedList = new SortedList<>(filterData);
            booksSortedList.comparatorProperty().bind(booksTableView.comparatorProperty());
            booksTableView.setItems(booksSortedList);
        });
    }
}
