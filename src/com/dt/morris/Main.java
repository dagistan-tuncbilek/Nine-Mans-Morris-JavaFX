package com.dt.morris;
	
import com.dt.morris.gui.SingletonBoard;
import com.dt.morris.gui.SingletonMenuBar;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
	        BorderPane borderPane = new BorderPane();
	        borderPane.setTop(SingletonMenuBar.getMenu());
	        borderPane.setCenter(SingletonBoard.getBoard());
	        stage.setTitle("Nine Man's Morris");
	        Scene scene = new Scene(borderPane, 1280, 720);
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
