package br.com.caelum.javafx.api.controllers;

import br.com.caelum.javafx.api.modelo.ProdutoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

public class ListaProdutosController {

	public ListaProdutosController(TableView<Object> listaProdutos) {
		ObservableList<Object> dados = FXCollections.observableArrayList(ProdutoDao.getProdutos());
		listaProdutos.setItems(dados);
	}

	public void calculaImpostos(ActionEvent event) {
		
	}

}
