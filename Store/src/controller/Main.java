package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Customer;



public class Main extends Application{
	private Stage primaryStage;
	private AnchorPane MainFrameView;
	//private static ObservableList<Customer> customerList = FXCollections.observableArrayList();
	
	public Main() {

	}
	
//	public ObservableList<Customer> getCustomerList() {
//		return customerList;
//	}
	@Override 
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test");
		setMainFrameView();
	}
	public void setMainFrameView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/MainFrame.fxml"));
			MainFrameView = (AnchorPane) loader.load();
			 
			Scene scene = new Scene(MainFrameView);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			MainFrameController controller = loader.getController();
			controller.setMain(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCustomerInfo(Customer customer) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/Signup.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("ȸ������");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			SignupController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCustomer(customer);
			
			dialogStage.showAndWait();
			//return controller.getReturnValue();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//return 0;
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}

	
}