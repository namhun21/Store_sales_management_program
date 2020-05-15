package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Customer;

public class OrderController implements Initializable{
	@FXML
	private ImageView img_01;
	@FXML
	private ImageView img_02;
	@FXML
	private ImageView img_03;
	@FXML
	private ImageView img_04;
	@FXML
	private ImageView img_05;
	@FXML
	private ImageView img_06;
	@FXML
	private ImageView img_07;
	@FXML
	private ImageView img_08;
	@FXML
	private ImageView img_09;
	@FXML
	private Button coffeeImg, latteImg, fruitImg;
	
	ArrayList<ImageView> imgViewlist = new ArrayList<ImageView>();
	
	private Stage OrderStage;
	private Customer customer;
	private int returnValue = 0;
	private Main main;
	
	
	@FXML
	public void changeCategory(ActionEvent event) {
		if (event.getSource().equals(coffeeImg)) {
			String menuSeq = "����������.jpg �Ƹ޸�ī��.jpg ī���.jpg ī���ī.jpg īǪġ��.jpg ī��Ḷ���ƶ�.jpg ȭ��Ʈī���ī.jpg �ٴҶ��.jpg ����ī��.jpg";
			StringTokenizer st = new StringTokenizer(menuSeq);
			changeMenu(st);
		} else if (event.getSource().equals(latteImg)) {
			String menuSeq = "���������.jpg ��������.jpg �����.jpg ��������.jpg ������.jpg ȭ��Ʈ���ڶ�.jpg �ٳ�����.jpg ���۹�Ʈ.jpg ���߻���.jpg";
			StringTokenizer st = new StringTokenizer(menuSeq);
			changeMenu(st);
		} else if (event.getSource().equals(fruitImg)) {
			String menuSeq = "ü�����̵�.jpg �����̵�.jpg ���ڿ��̵�.jpg �ڸ����̵�.jpg �����ƾ��̽�Ƽ.jpg ���⽺����.jpg Ű���ֽ�.jpg �����ֽ�.jpg �����ֽ�.jpg";
			StringTokenizer st = new StringTokenizer(menuSeq);
			changeMenu(st);
		}
	}
	public void changeMenu(StringTokenizer st) {
		int i = 0;
		while (st.hasMoreTokens()) {
			String path = String.format("C:\\Users\\Lenovo\\Desktop\\javafxproject\\Store\\src\\image\\%s", st.nextToken());
			File file = new File(path);
			imgViewlist.get(i++).setImage(new Image(file.toURI().toString()));
		}
	}
	public void setMain(Main main) {
		this.main = main;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imgViewlist.add(img_01);
		imgViewlist.add(img_02);
		imgViewlist.add(img_03);
		imgViewlist.add(img_04);
		imgViewlist.add(img_05);
		imgViewlist.add(img_06);
		imgViewlist.add(img_07);
		imgViewlist.add(img_08);
		imgViewlist.add(img_09);
		
	}
	
}
