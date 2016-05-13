package br.com.caelum.javafx.api.main;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class OlaMundo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Alert alerta = new Alert(AlertType.INFORMATION, "Olha aí a janelinha marota =D", ButtonType.OK);
		alerta.setHeaderText("Bem vindo");
		alerta.showAndWait();
	}
}
