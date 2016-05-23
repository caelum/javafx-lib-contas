package br.com.caelum.javafx.api.controllers;

import br.com.caelum.javafx.api.annotations.EhAtributoDaConta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class NovaContaController extends Controller {

	@FXML
	private ToggleGroup tipo;

	@FXML
	@EhAtributoDaConta
	private TextField titular;

	@FXML
	@EhAtributoDaConta
	private TextField numero;

	@FXML
	@EhAtributoDaConta
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
