package br.com.caelum.javafx.api.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class NovaContaController extends Controller {

	@FXML
	private ToggleGroup tipo;

	@FXML
	private TextField titular;

	@FXML
	private TextField numero;

	@FXML
	private TextField agencia;

	@FXML
	void criaConta(ActionEvent event) {
		invocaMetodo("criaConta");
		JavaFXUtil.trocaDeTela(JavaFXUtil.TELA_INICIAL_FXML, event);
	}

	@Override
	protected String getNomeDoManipulador() {
		return JavaFXUtil.MANIPULADOR_DE_CONTAS;
	}
}
