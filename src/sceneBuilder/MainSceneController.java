package sceneBuilder;
import model.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
import javafx.stage.Stage;

public class MainSceneController implements Initializable {

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
    private VBox pnDashboard;

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
    private StackPane stackPane;

    @FXML
    private TextField searchInput;

    @FXML
    private BarChart<String, Double> priceChart;

    @FXML
    private StackedAreaChart<Integer, Integer> stockChart;
    
    @FXML
    private PieChart categoryChart;

    private Stage primaryStage;

    
    ObservableList<Book> books = FXCollections.observableArrayList();   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recommendedBooks = new ArrayList<>(recommendedBooks());

        try {
            
            //load fxml and controller
            for (int i = 0; i < recommendedBooks.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                HBox carBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recommendedBooks.get(i));
                cardLayout.getChildren().add(carBox);
            }            

            // BarChart - priceChart
            XYChart.Series<String, Double> chart = new XYChart.Series<>();
            chart.setName("Product sold");
            chart.getData().add(new XYChart.Data<String, Double>("PA", 2000.0));
            chart.getData().add(new XYChart.Data<String, Double>("PB", 3000.0));
            chart.getData().add(new XYChart.Data<String, Double>("PC", 1500.0));
            chart.getData().add(new XYChart.Data<String, Double>("PD", 1000.0));
            priceChart.getData().add(chart);
            

            // PieChart - categoryChart
            ObservableList <PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("PA", 40),
                new PieChart.Data("PB", 20),
                new PieChart.Data("PC", 18),
                new PieChart.Data("PD", 70),
                new PieChart.Data("PE", 10)
            );
            categoryChart.setClockwise(true);
            categoryChart.setLabelLineLength(50);
            categoryChart.setLabelsVisible(true);
            categoryChart.setStartAngle(180);
            categoryChart.getData().addAll(pieChartData);
            categoryChart.setLegendSide(Side.LEFT);
            
            // StackedChart - stockChart
            XYChart.Series<Integer, Integer> stackedChart1 = new XYChart.Series<>();
            stackedChart1.setName("2012");
            stackedChart1.getData().add(new XYChart.Data<>(12, 25));
            stackedChart1.getData().add(new XYChart.Data<>(15, 18));
            stackedChart1.getData().add(new XYChart.Data<>(18, 15));
            stackedChart1.getData().add(new XYChart.Data<>(24, 19));
            stackedChart1.getData().add(new XYChart.Data<>(22, 21));

            XYChart.Series<Integer, Integer> stackedChart2 = new XYChart.Series<>();
            stackedChart2.setName("2011");
            stackedChart2.getData().add(new XYChart.Data<>(16, 12));
            stackedChart2.getData().add(new XYChart.Data<>(17, 26));
            stackedChart2.getData().add(new XYChart.Data<>(11, 21));
            stackedChart2.getData().add(new XYChart.Data<>(12, 29));
            stackedChart2.getData().add(new XYChart.Data<>(15, 23));

            stockChart.getData().addAll(stackedChart1, stackedChart2);




        } catch (Exception e) {
            e.printStackTrace();
        }
       
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

    void removeStyleClass() {
        List<HBox> ls = new ArrayList<>();
        ls.add(btnDashboard);
        ls.add(btnDashboard);
        ls.add(btnFavorite);
        ls.add(btnHistory);
        ls.add(btnPurchase);
        ls.add(btnUpdate);
        ls.add(btnShelves);

        for (HBox hBox : ls) {
            hBox.getStyleClass().remove("selected");
        }
    }


    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        
        //Switch between tableview and bookstores view
        // ObservableList<Node> childs = stackPane.getChildren();
        // if (event.getSource() == btnTableView) {
        //     if (childs.size() > 1) {
        //         Node topNode = childs.get(childs.size()-1);
        //         topNode.toBack();
        //      }
        // }

        //Handle event on addbtn
        if (event.getSource() == btnAddBook) {

            //Show dialog to add a new book
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddBook.fxml"));
            DialogPane addBookDialogPane = fxmlLoader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addBookDialogPane);
            dialog.setTitle("Add new book");
            dialog.showAndWait();

      
            AddBookController addBook = fxmlLoader.getController();  


            Optional<ButtonType> clickedButton = dialog.showAndWait();

            if (clickedButton.get() == ButtonType.OK) { 
                //Adding Confirmation 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm to add a new book !");
                alert.setContentText("Do you want to add a new book ?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    //Add books to tableview

                    //Iterate through tableview to check duplicate id
                    boolean isTitleDuplicate = false;
                    for (Book book : books) {
                        if(book.getTitle().toLowerCase().equals(addBook.getTextfiledTitle().toLowerCase())) {
                            isTitleDuplicate = true;
                            break; 
                        } 
                    }

                    if (isTitleDuplicate) {
                        Alert alertError = new Alert(Alert.AlertType.ERROR);
                        alertError.setTitle("Can't add new book!");
                        alertError.setContentText("You have entered an existing ID");
                        alertError.showAndWait();
                    } else {
                        books.add(new Book(
                            addBook.getTextfiledID(), 
                            addBook.getTextfiledYear(), 
                            addBook.getTextfiledStock(), 
                            addBook.getTextfiledPrice(), 
                            addBook.getTextfiledTitle(),
                            null, 
                            addBook.getTextfiledAuthor(), 
                            addBook.getTextfiledPublisher(), 
                            addBook.getTextfiledCategory()
                        ));

                        books.add(new Book(345, 2022, 2, 238000, "RICH DAD POOR DAD", null, "Robert T.Kiyosaki", "Plata Publishing", "Personal finance"));
                        books.add(new Book(641, 2021, 0, 120000, "THE WARREN BUFFET WAY", null, "Robert G.Hagstorm", "Alpha Books", "Business/investing"));
                        books.add(new Book(721, 2018, 5, 159690, "ATOMIC HABITS", null, "James Clear", "Avery", "Self-help"));
                        books.add(new Book(653, 2020, 10, 196000, "THE SEVEN HABITS OF HIGHLY EFFECTIVE PEOPLE", null, "Stephen R.Covey", "Simon & Schuster", "Business/Self-help"));
                        books.add(new Book(925, 2022, 0, 330600, "AWAKEN THE GIANT WITHIN", null, "Tony Robbins", "Efinito", "Self-help"));
        
                        
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
                }
            }
        }

        if (event.getSource() == btnExit) {
            javafx.application.Platform.exit();
        }

        if (event.getSource() == btnInvoices) {
            Parent root = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene invoiceScene = new Scene(root);
            primaryStage.setScene(invoiceScene);
            primaryStage.setTitle("Invoices Management");
            primaryStage.show(); 
        }

        if (event.getSource() == btnDashboard) {
            // btnDashboard.getStyleClass().add("selected");
            pnBooksManagement.setVisible(false);
            pnDashboard.setVisible(true);
        } else if (event.getSource() == btnbookManage) {
            pnBooksManagement.setVisible(true);
            pnDashboard.setVisible(false);
        }
    }

    @FXML
    void handleUpdate(MouseEvent event) throws IOException { 
    
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("UpdateBook.fxml"));

        DialogPane updateBookDialogPane = fxmlLoader.load();
        UpdateBookController updateBook = fxmlLoader.getController();
        
        Book clickedBook = booksTableView.getSelectionModel().getSelectedItem();
        
        if (event.getSource() == btnUpdate) {

            if(books.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty board error!");
                alert.setContentText("Unable to update the information in the table because the table is empty !");
                alert.showAndWait();
            } else {
                updateBook.setTextfiledID(String.valueOf(clickedBook.getId()));
                updateBook.setTextfiledPrice(String.valueOf(clickedBook.getPrice()));
                updateBook.setTextfiledYear(String.valueOf(clickedBook.getYear()));
                updateBook.setTextfiledStock(String.valueOf(clickedBook.getStock()));
                updateBook.setTextfiledAuthor(clickedBook.getAuthor());
                updateBook.setTextfiledCategory(clickedBook.getCategory());
                updateBook.setTextfiledPublisher(clickedBook.getPublisher());
                updateBook.setTextfiledTitle(clickedBook.getTitle());
        
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(updateBookDialogPane);
                dialog.setTitle("Update book");
                dialog.showAndWait();
        
                Optional<ButtonType> clickedButton = dialog.showAndWait();
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
                            
                            booksTableView.setItems(currentTableData);
                            booksTableView.refresh();
                            break;
                        }
                    }
        
                }
            }
        }

        if (event.getSource() == btnDelete) { 
            booksTableView.getItems().removeAll(clickedBook);
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
