package com.lexinda.yiscroll;

import com.lexinda.yiscroll.common.YiScroll;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class YiLanucher extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = new AnchorPane();
		YiScroll ys = new YiScroll("jfx-yiscroll文字滚动组件",Font.font(null, FontWeight.BOLD, 32),Color.RED);
		ys.setLayoutY(150.0);
		ys.setLayoutX(0);
		ys.setPrefWidth(500);
		ys.setMaxWidth(500);
		ys.setScrollType(1);
		ys.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		root.getChildren().add(ys);
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("jfx-yiscroll");
		primaryStage.show();
		ys.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}