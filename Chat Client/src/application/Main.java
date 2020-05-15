package application;
	
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;


public class Main extends Application {
	Socket socket;
	TextArea textArea;
	
	//클라이언트 프로그램 동작 메소드
	public void startClient(String IP, int port) {
		
		Thread thread = new Thread() {
			public void run() {
				try {
					socket = new Socket(IP,port);
					receive();
				}
				catch(Exception e) {
					if(!socket.isClosed()) {
						stopClient();
						System.out.println("[서버 접속 실패]");
						Platform.exit();
					}
				}
			}
		};
		thread.start();
	}
	
	//클라이언트 종료 메소드
	public void stopClient() {
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//서버로부터 메세지를 전달받는 메소드
	public void receive() {
		while(true) {
			try {
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[512];
				int length = in.read(buffer);
				if(length == -1 ) throw new IOException();
				String message = new String(buffer,0,length,"UTF-8");
				Platform.runLater(()->{
					textArea.appendText(message);
				});
			}
			catch(Exception e) {
				stopClient();
				break;
			}
		}
	}
	
	//서버로 메세지를 전송
	public void send(String message) {
		Thread thread = new Thread() {
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				}
				catch(Exception e) {
					stopClient();
				}
			}
		};
		thread.start();
	}
	
	//실제로 프로그램을 동작시키는 메소드
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane(); //다른 요소들을 담을수 있는
		root.setPadding(new Insets(5));
		
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		
		TextField username = new TextField();
		username.setPrefWidth(150);
		username.setPromptText("닉네임을 입력하세요.");
		HBox.setHgrow(username, Priority.ALWAYS);
		
		TextField IPText = new TextField("127.0.0.1");
		TextField portText = new TextField("9876");
		portText.setPrefWidth(80);
		
		hbox.getChildren().addAll(username, IPText, portText);
		root.setTop(hbox);
		
		textArea = new TextArea();
		textArea.setEditable(false);
		root.setCenter(textArea);
		
		TextField input = new TextField(); 
		input.setPrefWidth(Double.MAX_VALUE);
		input.setDisable(false);
		
		input.setOnAction(event->{
			send(username.getText() + ":" + input.getText() + "\n");
			input.setText("");
			input.requestFocus();
		});
		
		Button sendButton = new Button("보내기");
		sendButton.setDisable(true);
		
		sendButton.setOnAction(event->{
			send(username.getText() + ": "+ input.getText()+"\n");
			input.setText("");
			input.requestFocus();
		});
		
		Button connectionButton = new Button("접속하기");
		connectionButton.setOnAction(event->{  //버튼 눌렀을 때 이벤트 처리
			if(connectionButton.getText().equals("접속하기")) {
				int port = 9876;
				try {
					port = Integer.parseInt(portText.getText());
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				startClient(IPText.getText(),port);
				Platform.runLater(()->{
					textArea.appendText("[ 채팅방 접속]\n");
				});
				connectionButton.setText("종료하기");
				input.setDisable(false);
				sendButton.setDisable(false);
				input.requestFocus();
			}
			else {
				stopClient();
				Platform.runLater(()->{
					textArea.appendText("[ 채팅방 퇴장 ]\n");
				});
				connectionButton.setText("접속하기");
				input.setDisable(true);
				sendButton.setDisable(true);
			}
		});
		
		BorderPane pane = new BorderPane();
		pane.setLeft(connectionButton);
		pane.setCenter(input);
		pane.setRight(sendButton);
		
		root.setBottom(pane);
		Scene scene = new Scene(root,400,400);
		primaryStage.setTitle(" [채팅 클라이언트] ");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(event-> stopClient()); //사용자가 닫기 버튼 눌렀을때
		primaryStage.show();
		
		connectionButton.requestFocus();
	}
	
	//프로그램 진입점
	public static void main(String[] args) {
		launch(args);
	}
}
