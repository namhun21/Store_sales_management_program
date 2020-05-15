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
				alert.setHeaderText(nameField.getText()+ "�� "+ "ȯ���մϴ�");
				alert.setContentText("���õ� ���� �Ϸ� �Ǽ���");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("���� �޼���");
				alert.setHeaderText("������ �߻��߽��ϴ�");
				alert.setContentText("�����ͺ��̽� ������ �߻��߽��ϴ�.");
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
			errorMessage += "�̸��� �Է��ϼ��� \n";
		
		if(idField.getText() == null || idField.getText().equals(""))
			errorMessage += "id�� �Է��ϼ��� \n";
		
		if(pwField.getText() == null || pwField.getText().equals(""))
			errorMessage += "pw�� �Է��ϼ��� \n";
		
		if(phoneField.getText() == null || phoneField.getText().equals(""))
			errorMessage += "��ȭ��ȣ�� �Է��ϼ��� \n";
		
		if(errorMessage.equals(""))
			return true;
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("���� �޼���");
			alert.setHeaderText("���� ����� �Է��� �ּ���");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	
	}

}