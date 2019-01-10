package br.com.caelum.javafx.api.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaDeContas extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane base = FXMLLoader.load(getClass().getClassLoader().getResource("Base.fxml"));
		Scene scene = new Scene(base);
		stage.setScene(scene);
		stage.show();
	}

}
