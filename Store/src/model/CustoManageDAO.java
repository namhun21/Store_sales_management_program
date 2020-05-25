package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustoManageDAO {
	static Connection conn = null;
	static Statement stat = null;
	static ResultSet rs = null;

	static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private String ID = "namhoon";
	private String PW = "12345";

	public CustoManageDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("connect");
		} catch (ClassNotFoundException e) {
			System.out.println("�ε� ����");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<CumaDTO> Cus_value() {
		String sql = "select UNAME, user_id, total_sales from user_info where uname not like 'admin%'";
		ObservableList<CumaDTO> call_info = FXCollections.observableArrayList();
		System.out.println("����");
		try {
			conn = DriverManager.getConnection(URL, ID, PW);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			String total;
			System.out.println("�ֱ�");
			while (rs.next()) {
				System.out.println(rs.getString("Uname") + " " + rs.getString("user_id") + " " + rs.getString("total_sales"));
//				total = Integer.toString(rs.getInt("total_sales"));
				total = rs.getString("total_sales");
				System.out.println(total);
				CumaDTO cuma = new CumaDTO(rs.getString("Uname"), rs.getString("user_id"), rs.getString("total_sales"));
				call_info.add(cuma);
			}
		} catch (Exception e) {
			System.out.println("����");
		} finally {
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return call_info;
	}

}