package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Order;
import model.OrderDAO;
import model.OrderList;

public class AdminController implements Initializable{
	
	public AdminController(){}
	
	@FXML
	private TableView<OrderList> orderList2;
	@FXML
	private TableColumn<OrderList, String> orderID;
	@FXML
	private TableColumn<OrderList, String> userID;
	@FXML
	private TableColumn<OrderList, String> menuName;
	@FXML
	private TableColumn<OrderList, String> price;
	@FXML
	private TableColumn<OrderList, String> count;
	
	
	private Main main;
	OrderDAO orderDAO = new OrderDAO();
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	private void manageAction() {
		
	}
	@FXML
	private void salesAction() {
		
	}
	@FXML
	private void cancelAction()	{
		 int selectedIndex = orderList2.getSelectionModel().getSelectedIndex();
		 if(selectedIndex >= 0) {
			 String order_id = (orderList2.getItems().get(selectedIndex).getOrderID());
			 orderDAO.cancelOrder(order_id);
			 orderList2.getItems().remove(selectedIndex);
		 }
	}
	@FXML
	private void completeAction() {
		 int selectedIndex = orderList2.getSelectionModel().getSelectedIndex();
	     if(selectedIndex >= 0) {
	    	 String order_id = (orderList2.getItems().get(selectedIndex).getOrderID());
	    	 System.out.println(order_id);
	    	 orderDAO.changeOIsComplete(order_id);
	    	 orderDAO.calculateUserSale(order_id);
	    	 orderList2.getItems().remove(selectedIndex);
	    	 System.out.println(main.getOrderList().size());
	     }
	         
	}
	@FXML
	private void sendAction() {
		
	}
	@FXML
	private void lookUpAction() { // 조회버튼을 눌렀을때 새로운 주문 확인할 수 있는 메소드 
		orderList2.getItems().clear();	
		ObservableList<OrderList> tmpList = orderDAO.getOrderList();
		for(int i = 0; i< tmpList.size();i++) {
			main.orderList.add(tmpList.get(i));
			}
		orderList2.setItems(main.getOrderList());
	}

	public void setOrderListView(AdminController admincontroller) {  //adminview에 들어왔을때 주문목록 출력되도록 하는 함수
		ObservableList<OrderList> tmpList = orderDAO.getOrderList();
		for(int i = 0; i< tmpList.size();i++) {
			main.orderList.add(tmpList.get(i));
			}
		orderList2.setItems(main.getOrderList());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.orderID.setCellValueFactory(cellData -> cellData.getValue().getOrderIDProperty());
		this.userID.setCellValueFactory(cellData -> cellData.getValue().getUserIDProperty());
		this.menuName.setCellValueFactory(cellData -> cellData.getValue().getMenuNameProperty());
		this.count.setCellValueFactory(cellData -> cellData.getValue().getCountProperty());
		this.price.setCellValueFactory(cellData-> cellData.getValue().getPriceProperty());
		//
	}
}
