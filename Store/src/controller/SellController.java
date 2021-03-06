package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import model.SDAO;
import model.SDTO;
import javafx.scene.control.TableColumn;

public class SellController implements Initializable {
   @FXML
   private DatePicker Cal; //날짜 선택
   @FXML
   private TableView<SDTO> Caltable; // 메뉴별 총액 테이블
   @FXML
   private TableColumn<SDTO, String> list; // 메뉴
   @FXML
   private TableColumn<SDTO, String> sum;// 메뉴별 총액

   @FXML
   private TableView<SDTO> Caltable1; // 날짜별 총액
   @FXML
   private TableColumn<SDTO, String> list1;// 날짜
   @FXML
   private TableColumn<SDTO, String> sum1; // 날짜별 총액

   @FXML
   private Button search;
   
   @FXML
   private Label label; // 선택날짜
   @FXML
   private SDAO sdao;
   private ObservableList<SDTO> sdaoo;

   private Stage dialogStage;
   private String date;
   private String a;
   private Main main;
   public void setMain(Main main) {
	   this.main = main;
   }
   public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
   public void initialize(URL location, ResourceBundle resources) {
      sdao = new SDAO();
      Cal.setValue(LocalDate.now());
      date = Cal.getValue().format(DateTimeFormatter.ofPattern("yy/MM/dd"));
      label.setText(date);
   }

   @FXML
   public void search(ActionEvent event) {
      a = Cal.getValue().format(DateTimeFormatter.ofPattern("yy/MM/dd"));
      label.setText(a);
      date_view(label.getText());
      menu_view(label.getText());
   }

   private void date_view(String date) {
      try {
         ObservableList<SDTO> tmp_date = SDAO.search_date(date);
         System.out.println(tmp_date);
         sum1.setCellValueFactory(cellData -> cellData.getValue().getSum());
         list1.setCellValueFactory(cellData -> cellData.getValue().getlist());
         Caltable1.setItems(tmp_date);
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Data를 테이블에 가져올 수 없습니다.");
      }
   }

   private void menu_view(String date) {
      try {
         ObservableList<SDTO> tmp_menu = SDAO.search_menu(date);
         System.out.println(tmp_menu);
         sdaoo = FXCollections.observableArrayList();
         list.setCellValueFactory(cellData -> cellData.getValue().getlist());
         sum.setCellValueFactory(cellData -> cellData.getValue().getSum());
         Caltable.setItems(tmp_menu);
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Data를 테이블에 가져올 수 없습니다.");
      }
   }
   
}