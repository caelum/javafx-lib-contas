package br.com.caelum.javafx.api.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TelaInicialController {

	@FXML
	private TableView<Object> listaContas;

	@FXML
	private TableView<Object> listaSeguros;

	@FXML
	private TableView<Object> listaProdutos;

	private ListaContasController contasController;
	private ListaSegurosController segurosController;
	private ListaProdutosController produtosController;

	@FXML
	public void initialize() {
		this.contasController = new ListaContasController(listaContas);
		this.segurosController = new ListaSegurosController(listaSeguros);
		if (JavaFXUtil.temTributavel) {
			this.produtosController = new ListaProdutosController(listaProdutos);
		}
	}

	@FXML
	void calculaImpostos(ActionEvent event) {
		if (JavaFXUtil.temTributavel) {
			this.produtosController.calculaImpostos(event);
		}
	}

	@FXML
	void criaConta(ActionEvent event) {
		this.contasController.criaConta(event);
	}

	@FXML
	void criaSeguro(ActionEvent event) {
		this.segurosController.criaSeguro(event);
	}

	@FXML
	void salvaDados(ActionEvent event) {
		this.contasController.salvaDados(event);
	}
}
