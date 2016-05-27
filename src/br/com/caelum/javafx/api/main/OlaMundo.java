package br.com.caelum.javafx.api.main;

import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import br.com.caelum.javafx.api.controllers.JavaFXUtil;

public class OlaMundo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		JavaFXUtil.mostraAlerta("Olha aí a janelinha marota =D", "Bem vindo", AlertType.INFORMATION);
	}
}
