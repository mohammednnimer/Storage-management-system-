package db_testing;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
public class Driver extends Application {
    private Button btInsert, btUpdate, btDelete, btSelect;
    private TextField tfEmployeeId, tfName, tfEmail, tfPhone, tfage;
    private TableView<customer> tableView;
    private TableColumn<customer, Integer> tcEmployeeId, tcPhone, tcDepartmentId;
    private TableColumn<customer, String> tcName, tcEmail, tcage;
    private Connection  con;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private GridPane gridPane;

    public Driver() throws SQLException {
    	 try {
    	        Class.forName("com.mysql.jdbc.Driver");
    	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8", "root", "1234");
    	        initializeUI();
    	    } catch (Exception e) {
    	        // Handle exceptions
    	        e.printStackTrace();
    	    }
    }

    private void initializeUI() {
        btInsert = new Button("Insert");
        btUpdate = new Button("Update");
        btDelete = new Button("Delete");
        btSelect = new Button("Select");

        tfEmployeeId = new TextField();
        tfName = new TextField();
        tfEmail = new TextField();
        tfPhone = new TextField();
        tfage = new TextField();

        tableView = new TableView<>();
        setupTableView();
        setupButtonActions();
        
    }
    
    
    
    
    
    
    
    private void setupTableView() {
        tcEmployeeId = new TableColumn<>("Customer ID");
        tcName = new TableColumn<>("Name");
        tcEmail = new TableColumn<>("CustomerAddress");
        tcPhone = new TableColumn<>("CustomerPhone");
        tcage = new TableColumn<>("age");
        tcEmployeeId.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        tcage.setCellValueFactory(new PropertyValueFactory<>("age"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
        tableView.getColumns().addAll(tcEmployeeId, tcName, tcEmail, tcPhone, tcage);
    }

    private void setupButtonActions() {
        btInsert.setOnAction(e -> insertRecord());
        btUpdate.setOnAction(e -> updateRecord());
        btDelete.setOnAction(e -> deleteRecord());
        btSelect.setOnAction(e -> loadRecords());
    }
    
    
    
    
    

    private void insertRecord() {
        if (validateInput()) {
        	        try {
        	            String sql = "INSERT INTO customer (CustomerID, CustomerName, CustomerAddress, CustomerPhone, age) VALUES (?, ?, ?, ?, ?)";
        	            preparedStatement = con.prepareStatement(sql);
        	            preparedStatement.setInt(1, Integer.parseInt(tfEmployeeId.getText()));
        	            preparedStatement.setString(2, tfName.getText());
        	            preparedStatement.setString(3, tfEmail.getText());
        	            preparedStatement.setString(4, tfPhone.getText());
        	            preparedStatement.setInt(5, Integer.parseInt(tfage.getText()));     
        	            preparedStatement.executeUpdate();
        	            loadRecords();
        	            clearFields();
        	        } catch (SQLException ex) {
        	            showAlert("Error inserting record", ex.getMessage());
        	        }
        	    }
    }

    private void updateRecord() {
        if (validateInput()) {
            try {
                String sql = "UPDATE customer SET CustomerName=?, CustomerAddress=?, CustomerPhone=?, age=?WHERE CustomerID=?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, tfName.getText());
                preparedStatement.setString(2, tfEmail.getText());
                preparedStatement.setInt(3, Integer.parseInt(tfPhone.getText()));
                preparedStatement.setInt(4,Integer.parseInt(tfage.getText()));
                preparedStatement.setInt(5, Integer.parseInt(tfEmployeeId.getText()));
                preparedStatement.executeUpdate();
                loadRecords();
                clearFields();
            } catch (SQLException ex) {
                showAlert("Error updating record", ex.getMessage());
            }
        }
    }

    private void deleteRecord() {
        if (!tfEmployeeId.getText().isEmpty()) {
            try {
                String sql = "DELETE FROM customer WHERE CustomerID = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(tfEmployeeId.getText()));
                preparedStatement.executeUpdate();
                loadRecords();
                clearFields();
                
            } catch (SQLException ex) {
                showAlert("Error deleting record", ex.getMessage());
            }
        } else {
            showAlert("Validation Error", "custmer ID field cannot be empty for delete operation.");
            }
    }

    private void loadRecords() {
    	  try {
    	        if (con == null) {
    	            showAlert("Database Connection Error", "No database connection.");
    	            return;
    	        }
    	        statement = con.createStatement();
    	        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
    	        tableView.getItems().clear();
    	        while (resultSet.next()) {
    	            customer c1 = new customer(
    	                    resultSet.getInt("CustomerID"),
    	                    resultSet.getString("CustomerName"),
    	                    resultSet.getString("CustomerAddress"),
    	                    resultSet.getString("CustomerPhone"),
    	                    resultSet.getInt("age")
    	                    
    	            );
    	          
    	            tableView.getItems().add(c1);
    	        }
    	        
    	        statement.close();
    	        resultSet.close();
    	    } catch (SQLException e) {
    	        showAlert("Error loading records", e.getMessage());
    	    }
    }

    private boolean validateInput() {
        if (tfName.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPhone.getText().isEmpty() ||
                tfage.getText().isEmpty() || tfEmployeeId.getText().isEmpty()) {
            showAlert("Validation Error", "All fields must be filled out.");
            return false;
        }
        return true;
    }

    private void clearFields() {
        tfEmployeeId.clear();
        tfName.clear();
        tfEmail.clear();
        tfPhone.clear();
        tfage.clear();
      
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) {
    	
    	
    	
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(new Label("Employee ID:"), 0, 0);
        gridPane.add(tfEmployeeId, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(tfName, 1, 1);
        gridPane.add(new Label("Email:"), 0, 2);
        gridPane.add(tfEmail, 1, 2);
        gridPane.add(new Label("Phone:"), 0, 3);
        gridPane.add(tfPhone, 1, 3);
        gridPane.add(new Label("age:"), 0, 4);
        gridPane.add(tfage, 1, 4);
      
      
        gridPane.add(btInsert, 0, 6);
        gridPane.add(btUpdate, 1, 6);
        gridPane.add(btDelete, 0, 7);
        gridPane.add(btSelect, 1, 7);
        gridPane.add(tableView, 0, 8, 2, 1);

        Scene scene = new Scene(gridPane, 400, 550);
        stage.setTitle("Database Operations");
        stage.setScene(scene);
        stage.show();
        	  loadRecords();
        
      
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        if (con != null) con.close();
    }
}

//System.out.print(false);
//
//Class.forName("com.mysql.jdbc.Driver");
//Connection con = (Connection) DriverManager
//		.getConnection("jdbc:mysql://localhost:3306/ch5?characterEncoding=utf8", "root", "1234");
//
//Statement stmt = con.createStatement();//بتحول ال سترينج ل ستايتمنت 
//
//String query = "select * from boats";
//   
//ResultSet rs = stmt.executeQuery(query);//بنفذ الكويري
//
//while (rs.next()) {//. next بجيب سطر سطر زي الفايل
//	System.out.println("Name : " + rs.getString("bname") + "--> " + "Color : " + rs.getString("color"));
//}
//stmt.close();
//rs.close();
//con.close();








//
//mainPane.getChildren().clear();
//
//ImageView pro1 = new ImageView("order.png");
//pro1.setFitWidth(200);
//pro1.setFitHeight(200);
//pro1.setLayoutX(200);
//pro1.setLayoutY(15);
//mainPane.getChildren().addAll(path, l1, product, supplier, details, Giving, Sitting, pro1);
//n = 0;
//i = 1;
//Label Product = new Label("Product :" + i);
//Product.setFont(new Font(40));
//Product.setLayoutX(750);
//Product.setLayoutY(50);
//Label infor = new Label("information about the product");
//infor.setFont(new Font(25));
//
//Label id = new Label("ID the product :      " + prduct.get(n).getProductId());
//Label name = new Label("Name of the product : " + prduct.get(n).getProductName());
//Label Category = new Label("Category  the product :" + prduct.get(n).getCategory());
//Label price = new Label("Price the product :   " + prduct.get(n).getPrice());
//Label maincompany = new Label("maincompany product :" + prduct.get(n).getMaincompany());
//name.setFont(new Font(20));
//id.setFont(new Font(20));
//Category.setFont(new Font(20));
//price.setFont(new Font(20));
//maincompany.setFont(new Font(20));
//
//VBox v1 = new VBox();
//v1.setSpacing(20);
//v1.getChildren().addAll(infor, id, name, Category, price, maincompany);
//
//v1.setLayoutX(1100);
//v1.setLayoutY(50);
//
//ImageView ne = new ImageView("next-button.png");
//ne.setFitWidth(30);
//ne.setFitHeight(30);
//Button next = new Button();
//next.setGraphic(ne);
//next.setLayoutX(1450);
//next.setLayoutY(390);
//Button prev = new Button();
//mainPane.getChildren().clear(); ne1 = new ImageView("next-button.png");
//ne1.setFitWidth(30);
//ne1.setFitHeight(30);
//prev.setGraphic(ne1);
//prev.setRotate(180);
//prev.setLayoutX(205);
//prev.setLayoutY(390);
//TableView<order_detail> table = new TableView<>();
//TableColumn<order_detail, Integer> OrderDetailID = new TableColumn<>("OrderDetailID");
//OrderDetailID.setCellValueFactory(new PropertyValueFactory<>("OrderDetailID"));
//OrderDetailID.setPrefWidth(150);
//TableColumn<order_detail, Integer> OrderID = new TableColumn<>("OrderID");
//OrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
//OrderID.setPrefWidth(150);
//TableColumn<order_detail, Integer> ProductID = new TableColumn<>("ProductID");
//ProductID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
//ProductID.setPrefWidth(150);
//TableColumn<order_detail, Integer> OrderedQuantity = new TableColumn<>("OrderedQuantity");
//OrderedQuantity.setCellValueFactory(new PropertyValueFactory<>("OrderedQuantity"));
//OrderedQuantity.setPrefWidth(150);
//TableColumn<order_detail, Double> pric = new TableColumn<>("price");
//pric.setCellValueFactory(new PropertyValueFactory<>("price"));
//pric.setPrefWidth(150);
//table.getColumns().addAll(OrderDetailID, OrderID, ProductID, OrderedQuantity, pric);
//table.setPrefWidth(750);
//table.setLayoutX(500);
//table.setLayoutY(550);
//found(table, prduct.get(n).getProductId());
//table.setOnMouseClicked(event -> {
//	if (event.getClickCount() == 2) {
//		selectedOrderDetail = table.getSelectionModel().getSelectedItem();
//		if (selectedOrderDetail != null) {
//		
//
//		}
//	}
//});
//
//Button insert = new Button("Insert");
//Button delet = new Button("Delete");
//insert.setStyle("-fx-background-color: #3366FF;");
//insert.setLayoutX(10);
//insert.setLayoutY(650);
//insert.setPrefHeight(46);
//insert.setPrefWidth(190);
//
//insert.setOnMouseEntered(e1 -> insert.setStyle("-fx-background-color: lightgray;"));
//insert.setOnMouseExited(e1 -> insert.setStyle("-fx-background-color: #3366FF;"));
//insert.setFont(new Font("Comic Sans MS", 15));
//
//delet.setStyle("-fx-background-color: #3366FF;");
//delet.setLayoutX(10);
//delet.setLayoutY(700);
//delet.setPrefHeight(46);
//delet.setPrefWidth(190);
//
//delet.setOnMouseEntered(e1 -> delet.setStyle("-fx-background-color: lightgray;"));
//delet.setOnMouseExited(e1 -> delet.setStyle("-fx-background-color: #3366FF;"));
//delet.setFont(new Font("Comic Sans MS", 15));
//
//mainPane.getChildren().addAll(v1, Product, table, insert, delet);
//
//insert.setOnAction(er1 -> {
//
//	Label nameof = new Label("Insert ID of costumer:");
//	TextField tid = new TextField();
//
//	Label qun = new Label("Insert Quntity :");
//	ComboBox<Integer> tqun = new ComboBox<>();
//	for (int i = 1; i < 50; i++) {
//		tqun.getItems().add(i);
//	}
//	tqun.setValue(1);
//	Button b1 = new Button("Done");
//
//	GridPane gp = new GridPane();
//	gp.add(nameof, 0, 0);
//
//	gp.add(tid, 1, 0);
//	gp.add(qun, 0, 1);
//	gp.add(tqun, 1, 1);
//	gp.setHgap(20);
//	gp.setVgap(20);
//	VBox v2 = new VBox();
//	v2.getChildren().addAll(gp, b1);
//	v2.setSpacing(20);
//	v2.setLayoutX(210);
//	v2.setLayoutY(600);
//	mainPane.getChildren().add(v2);
//	v2.setAlignment(Pos.CENTER);
//
//	b1.setOnAction(er2 -> {
//
//		try {
//			Integer.parseInt(tid.getText());
//
//		} catch (Exception e1) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("choose any Order Product");
//			alert.setHeaderText("insert number");
//			alert.show();
//			return;
//		}
//
//		if (tid.getText() != "") {
//
//			try {
//				String sql;
//				if (cheackCustmer(tid.getText())) {
//
//					sql= "INSERT INTO customer (CustomerID, CustomerName, CustomerAddress, CustomerPhone, age) VALUES (?, ?, ?, ?, ?)";
//
//					preparedStatement = con.prepareStatement(sql);
//					preparedStatement.setInt(1, Integer.parseInt(tid.getText()));
//					preparedStatement.setString(2, null);
//					preparedStatement.setString(3, null);
//					preparedStatement.setString(4, null);
//					preparedStatement.setInt(5, 0);
//					preparedStatement.executeUpdate();
//					
//				}
//					
//					
//
//					int i = 0;
//					boolean r = true;
//					for (; r; ) {
//						i++;
//						r = false;
//
//						for (int j = 0; j < order.size(); j++) {
//							System.out.println(order.get(j).order_Id +"    "+i);
//							if (order.get(j).order_Id == i) {
//								r = true;
//								break;
//							}
//						}
//
//					}
//
//					System.out.println(i);
//					
//					sql = "INSERT INTO order_Request (OrderID, OrderDate, CustomerID, ShippingAddress, PaymentMethod) VALUES (?, ?, ?, ?, ?)";
//
//					preparedStatement = con.prepareStatement(sql);
//					preparedStatement.setInt(1, i);
//					preparedStatement.setDate(2, null);
//					preparedStatement.setInt(3, Integer.parseInt(tid.getText()));
//					preparedStatement.setString(4, null);
//					preparedStatement.setString(5, null);
//					preparedStatement.executeUpdate();
//				
//					int or = i;
//				
//					i = 0;
//					r = true;
//					for (; r; ) {
//						i++;
//						r = false;
//
//						for (int j = 0; j < prduct_Dettels.size(); j++) {
//
//							if (prduct_Dettels.get(j).getOrderDetailID() == i) {
//								r = true;
//							}
//						}
//
//					}
//
//					sql = "INSERT INTO order_detail (OrderDetailID, OrderID, ProductID, OrderedQuantity, Price) VALUES (?, ?, ?, ?, ?)";
//
//					preparedStatement = con.prepareStatement(sql);
//					preparedStatement.setInt(1, i);
//					preparedStatement.setInt(2, or);
//					preparedStatement.setInt(3, prduct.get(n).getProductId());
//					preparedStatement.setInt(4, tqun.getValue());
//					preparedStatement.setDouble(5, tqun.getValue() * prduct.get(n).getPrice());
//					preparedStatement.executeUpdate();
//					abload();
//					found(table, prduct.get(n).getProductId());
//					mainPane.getChildren().clear();
//					mainPane.getChildren().addAll(path, l1, product, supplier, details, Giving, Sitting,pro1);
//					mainPane.getChildren().addAll(v1, Product, table, insert, delet);
//					mainPane.getChildren().addAll(next, prev);
//
//					return;
//
//				
//			} catch (ClassNotFoundException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			} catch (SQLException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//			 Alert alert = new Alert(AlertType.ERROR);
//		        alert.setTitle("choose any Order Product");
//		        alert.setHeaderText("please enter valid data");
//		       alert.show();
//
//		}
//		 Alert alert = new Alert(AlertType.ERROR);
//	        alert.setTitle("choose any Order Product");
//	        alert.setHeaderText("please enter valid data");
//	       alert.show();
//
//	});
//
//});
//
//delet.setOnAction(t -> {
//	if (selectedOrderDetail != null) {
//
//		String sql = "DELETE FROM order_detail WHERE OrderDetailID = ?";
//		try {
//			preparedStatement = con.prepareStatement(sql);
//
//			preparedStatement.setInt(1, selectedOrderDetail.getOrderDetailID());
//			preparedStatement.executeUpdate();
//			abload();
//			found(table, prduct.get(n).getProductId());
//		} catch (ClassNotFoundException e2) {
//			e2.printStackTrace();
//		} catch (SQLException e2) {
//			e2.printStackTrace();
//		}
//	} else {
//		Alert alert = new Alert(AlertType.ERROR);
//		alert.setTitle("choose any Order Product");
//		alert.setHeaderText("choose any Order Product");
//		alert.show();
//
//	}
//
//});
//
//next.setOnAction(er1 -> {
//	n++;
//	i++;
//	if (n < prduct.size()) {
//		table.getItems().clear();
//		Product.setText("Product :" + i);
//		id.setText("ID the product :      " + prduct.get(n).getProductId());
//		name.setText("Name of the product : " + prduct.get(n).getProductName());
//		Category.setText("Category  the product :" + prduct.get(n).getCategory());
//		price.setText("Price the product :   " + prduct.get(n).getPrice());
//		maincompany.setText("maincompany product :" + prduct.get(n).getMaincompany());
//		found(table, prduct.get(n).getProductId());
//
//	} else {
//		n--;
//		i--;
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Last Product Information");
//		alert.setHeaderText("Last Product Information");
////		alert.show();
////	}
////
////});
////
////prev.setOnAction(er1 -> {
////	n--;
////	i--;
////	if (n >= 0) {
////		table.getItems().clear();
////		Product.setText("Product :" + i);
////		id.setText("ID the product :      " + prduct.get(n).getProductId());
////		name.setText("Name of the product : " + prduct.get(n).getProductName());
////		Category.setText("Category  the product :" + prduct.get(n).getCategory());
////		price.setText("Price the product :   " + prduct.get(n).getPrice());
////		maincompany.setText("maincompany product :" + prduct.get(n).getMaincompany());
////		found(table, prduct.get(n).getProductId());
////
////	} else {
////		n++;
////		i++;
////		Alert alert = new Alert(AlertType.INFORMATION);
////		alert.setTitle("Last Product Information");
////		alert.setHeaderText("first Product Information");
////		alert.show();
////
////	}
////
////});
////
////mainPane.getChildren().addAll(next, prev);
////product.setStyle("-fx-background-color: lightgray;");
////product.setOnMouseExited(e1 -> product.setStyle("-fx-background-color: lightgray;"));
////supplier.setStyle("-fx-background-color: #3366FF;");
////Sitting.setStyle("-fx-background-color: #3366FF;");
////Giving.setStyle("-fx-background-color: #3366FF;");
////details.setStyle("-fx-background-color: #3366FF;");
//
//});
//supplier.setOnAction(er -> {
//
//supplier.setStyle("-fx-background-color: lightgray;");
//supplier.setOnMouseExited(e1 -> supplier.setStyle("-fx-background-color: lightgray;"));
//product.setStyle("-fx-background-color: #3366FF;");
//Sitting.setStyle("-fx-background-color: #3366FF;");
//Giving.setStyle("-fx-background-color: #3366FF;");
//details.setStyle("-fx-background-color: #3366FF;");
//
//});
//
//Sitting.setOnAction(er -> {
//
//Sitting.setStyle("-fx-background-color: lightgray;");
//Sitting.setOnMouseExited(e1 -> Sitting.setStyle("-fx-background-color: lightgray;"));
//product.setStyle("-fx-background-color: #3366FF;");
//supplier.setStyle("-fx-background-color: #3366FF;");
//Giving.setStyle("-fx-background-color: #3366FF;");
//details.setStyle("-fx-background-color: #3366FF;");
//
//});
//
//Giving.setOnAction(er -> {
//mainPane.getChildren().clear();
//mainPane.getChildren().addAll(path, l1, product, supplier, details, Giving, Sitting);
//
//Giving.setStyle("-fx-background-color: lightgray;");
//Giving.setOnMouseExited(e1 -> Giving.setStyle("-fx-background-color: lightgray;"));
//product.setStyle("-fx-background-color: #3366FF;");
//supplier.setStyle("-fx-background-color: #3366FF;");
//Sitting.setStyle("-fx-background-color: #3366FF;");
//details.setStyle("-fx-background-color: #3366FF;");
//Label give = new Label(
//		" Giving the authority results in this employee being \n able to access, access, and manipulate all stored information.\n Please be careful when granting the authority. Thank you.");
//give.setFont(new Font(30));
//give.setLayoutX(200);
//give.setLayoutY(20);
//mainPane.getChildren().add(give);
//Label name = new Label("Please enter the Name:");
//name.setFont(new Font(25));
//TextField tname = new TextField();
//tname.setPromptText("The Name");
//tname.setPrefWidth(200);
//tname.setPrefHeight(40);
//
//Label basswd = new Label("Please enter the Password:");
//
//basswd.setFont(new Font(25));
//javafx.scene.control.TextField tbasswd = new javafx.scene.control.TextField();
//tbasswd.setPrefWidth(200);
//tbasswd.setPrefHeight(40);
//tbasswd.setPromptText("The Password");
//
//Label gbasswd = new Label("Enter the Password again:");
//gbasswd.setFont(new Font(25));
//javafx.scene.control.TextField gtname = new javafx.scene.control.TextField();
//gtname.setPrefWidth(200);
//gtname.setPrefHeight(40);
//gtname.setPromptText("The Password");
//
//GridPane gp = new GridPane();
//gp.add(name, 0, 0);
//gp.add(tname, 1, 0);
//gp.add(basswd, 0, 1);
//gp.add(tbasswd, 1, 1);
//gp.add(gbasswd, 0, 2);
//gp.add(gtname, 1, 2);
//gp.setAlignment(Pos.CENTER);
//gp.setHgap(20);
//gp.setVgap(30);
//gp.setLayoutX(600);
//gp.setLayoutY(350);
//mainPane.getChildren().add(gp);
//
//Button done = new Button("Done");
//done.setLayoutX(800);
//done.setLayoutY(550);
//mainPane.getChildren().add(done);
//
//});
//
//details.setOnAction(er -> {
//details.setStyle("-fx-background-color: lightgray;");
//details.setOnMouseExited(e1 -> details.setStyle("-fx-background-color: lightgray;"));
//product.setStyle("-fx-background-color: #3366FF;");
//supplier.setStyle("-fx-background-color: #3366FF;");
//Sitting.setStyle("-fx-background-color: #3366FF;");
//Giving.setStyle("-fx-background-color: #3366FF;");
//
//});
//
//// Set up the stage
//scene = new Scene(mainPane, 1500, 790);
//primaryStage.setScene(scene);
//primaryStage.setTitle("SMS");
//primaryStage.show();
//
//// Initialize the details list
//detailsList = new ArrayList<>();
//detailsList.add("Details for item 1");
//detailsList.add("Details for item 2");
//detailsList.add("Details for item 3");
//// Create the main pane with blue background
//
//// Create the product details pane with white background
//Pane productDetailsPane = new Pane();
//productDetailsPane.setLayoutX(217);
//productDetailsPane.setLayoutY(-1);
//productDetailsPane.setPrefSize(846, 715);
//
//// Create the menu pane
//Pane menuPane = new Pane();
//menuPane.setLayoutY(-1);
//menuPane.setPrefSize(217, 715);
//menuPane.setStyle("-fx-background-color: blue");
//
//// Create buttons for the menu pane
//Button supplierButton = new Button("Supplier");
//supplierButton.setLayoutX(11);
//supplierButton.setLayoutY(197);
//supplierButton.setPrefSize(195, 49);
//supplierButton.setFont(Font.font("System Bold", 20));
//
//Button settingButton = new Button("Setting");
//settingButton.setLayoutX(41);
//settingButton.setLayoutY(622);
//settingButton.setPrefSize(134, 36);
//settingButton.setFont(Font.font("System Bold", 16));
//
//Button detailsButton = new Button("Details");
//detailsButton.setLayoutX(11);
//detailsButton.setLayoutY(114);
//detailsButton.setPrefSize(195, 49);
//detailsButton.setFont(Font.font("System Bold", 20));
//
//// Create the Text for the click name
//Text clickName = new Text("");
//clickName.setLayoutX(90);
//clickName.setLayoutY(43);
//clickName.setWrappingWidth(660);
//clickName.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
//clickName.setFont(Font.font("System Bold", 27));
//
//// Create the Button for the Menu button
//Button MenuButton = new Button("Menu");
//MenuButton.setLayoutX(5);
//MenuButton.setLayoutY(15);
//MenuButton.setPrefSize(190, 50);
//MenuButton.setStyle("-fx-background-color: rgb(131, 153, 255);");
//MenuButton.setFont(Font.font("System Bold", 20));
//
//// Add buttons to menu pane
//menuPane.getChildren().addAll(supplierButton, settingButton, detailsButton);
//
//});
//
//Label nameLabel = new Label("Storage Management System");
//nameLabel.setLayoutX(262);
//nameLabel.setLayoutY(27);
//nameLabel.setPrefHeight(44);
//nameLabel.setPrefWidth(305);
//nameLabel.setFont(new Font("Andalus", 26));
//
//Label colorOnlyLabel3 = new Label();
//colorOnlyLabel3.setLayoutX(221);
//colorOnlyLabel3.setLayoutY(403);
//colorOnlyLabel3.setPrefHeight(17);
//colorOnlyLabel3.setPrefWidth(98);
//colorOnlyLabel3.setStyle("-fx-background-color: white;");
//Label colorOnlyLabel2 = new Label();
//colorOnlyLabel2.setLayoutX(163);
//colorOnlyLabel2.setLayoutY(277);
//colorOnlyLabel2.setPrefHeight(17);
//colorOnlyLabel2.setPrefWidth(98);
//colorOnlyLabel2.setStyle("-fx-background-color: white;");
//root.getChildren().addAll(backgroundImageView, colorOnlyLabel, logoSMSImageView, loginLabel, usernameLabel,
//passwordLabel, usernameTextField, passwordTextField, loginButton, nameLabel, colorOnlyLabel2,
//colorOnlyLabel3);
