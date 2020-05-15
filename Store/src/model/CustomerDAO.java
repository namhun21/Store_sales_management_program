package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDAO {
	private Connection conn;
	private ResultSet rs;
	
	public CustomerDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String driver = "oracle.jdbc.driver.OracleDriver";
			String ID= "namhoon"; //개인이 만든 db아이디와 비번 입력해야함
			String pw = "12345";
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL,ID,pw);
			System.out.println("접속 성공");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertCustomer(Customer cus) {
		String SQL = "INSERT INTO CUSTOMER VALUES(?,?,?,?)";
		try {
			Customer customer = cus;
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getID());
			pstmt.setString(3, customer.getPw());
			pstmt.setString(4, customer.getPhone());
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public boolean isExistId(String id) {
		String SQL = "SELECT ID FROM CUSTOMER WHERE ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			String str = "";
			rs = pstmt.executeQuery();
			while(rs.next()) 
				str = rs.getString(1);
			if(str.equals(id))
				return true;
			else 
				return false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public ObservableList<Customer> getCustomerList(){
		String SQL = "SELECT * FROM CUSTOMER = ?";
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Customer customer = new Customer(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3));
				customerList.add(customer);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}
}
