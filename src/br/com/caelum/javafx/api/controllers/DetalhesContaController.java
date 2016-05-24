package br.com.caelum.javafx.api.controllers;

import br.com.caelum.javafx.api.modelo.ContaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class DetalhesContaController extends Controller {

	@FXML
	private TitledPane tipo;

	@FXML
	private Label titular;

	@FXML
	private Label numero;

	@FXML
	private Label agencia;

	@FXML
	private Label saldo;

	@FXML
	private TextField valorOperacao;

	@FXML
	private TextField valorTransferencia;

	@FXML
	private ComboBox<Object> listaContas;

	@Override
	public void initialize() {
		super.initialize();
		ObservableList<Object> dados = FXCollections.observableArrayList(ContaDao.getContas());
		listaContas.setItems(dados);
	}

	@FXML
	void deposita(ActionEvent event) {

	}

	@FXML
	void saca(ActionEvent event) {

	}

	@FXML
	void transfere(ActionEvent event) {

	}

	@Override
	protected String getNomeDoManipulador() {
		return JavaFXUtil.MANIPULADOR_DE_CONTAS;
	}
}
