package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.NOVO_SEGURO_FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import br.com.caelum.javafx.api.modelo.SeguroDao;

public class ListaSegurosController {

	public ListaSegurosController(TableView<Object> listaSeguros) {
		ObservableList<Object> dados = FXCollections.observableArrayList(SeguroDao.getSeguros());
		listaSeguros.setItems(dados);
	}

	public void criaSeguro(ActionEvent event) {
		JavaFXUtil.trocaDeTela(NOVO_SEGURO_FXML, event);
	}

}