package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerDAO;

public class CustomerDataController {
	@FXML
	private TextField nameField;
	@FXML 
	private TextField idField;
	@FXML
	private TextField pwField;
	@FXML
	private TextField phoneField;
	private Stage dialogStage;
	private Customer customer;
	
	private int returnValue = 0;
	private Main main;
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	private void initialize() {
		
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
		nameField.setText(customer.getName());
		idField.setText(customer.getID());
		pwField.setText(customer.getPw());
		phoneField.setText(customer.getPhone());
	}
	public int getReturnValue() {
		return returnValue;
	}
	@FXML
	private void completeAction() {
		if(valid()) {
			customer.setName(nameField.getText());
			customer.setName(nameField.getText());
			customer.setName(nameField.getText());
			customer.setName(nameField.getText());
			
			returnValue = 1;
			CustomerDAO customerDAO = new CustomerDAO();
			int result = customerDAO.insertCustomer(customer);
			if(result == 1) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(dialogStage);
				alert.setTitle("Complete Sign up!");
				alert.setHeaderText(nameField.getText()+ "님 "+ "환영합니다");
				alert.setContentText("오늘도 좋은 하루 되세요");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("오류 메세지");
				alert.setHeaderText("오류가 발생했습니다");
				alert.setContentText("데이터베이스 오류가 발생했습니다.");
				alert.showAndWait();
			}
			
			dialogStage.close();
		}
	}
	@FXML
	private void checkAction() {
		
	}
	@FXML
	private void cancelAction() {
		dialogStage.close();
	}
	private boolean valid() {
		String errorMessage = "";
		if(nameField.getText() == null || nameField.getText().equals(""))
			errorMessage += "이름을 입력하세요 \n";
		
		if(idField.getText() == null || idField.getText().equals(""))
			errorMessage += "id를 입력하세요 \n";
		
		if(pwField.getText() == null || pwField.getText().equals(""))
			errorMessage += "pw를 입력하세요 \n";
		
		if(phoneField.getText() == null || phoneField.getText().equals(""))
			errorMessage += "전화번호를 입력하세요 \n";
		
		if(errorMessage.equals(""))
			return true;
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("오류 메세지");
			alert.setHeaderText("값을 제대로 입력해 주세요");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	
	}

}
