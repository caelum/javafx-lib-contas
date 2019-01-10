package br.com.caelum.javafx.api.main;

import br.com.caelum.javafx.api.controllers.JavaFXUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SistemaBancario extends Application {

	public static void mostraTela(boolean temTributavel) {
		JavaFXUtil.temTributavel = temTributavel;
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane base = FXMLLoader.load(getClass().getClassLoader().getResource(JavaFXUtil.TELA_INICIAL_FXML));
		Scene scene = new Scene(base);
		stage.setScene(scene);
		stage.show();
	}
}
