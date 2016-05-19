package br.com.caelum.javafx.api.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

public class ListaSegurosController {

	public ListaSegurosController(TableView<?> listaSeguros) {
		// TODO Auto-generated constructor stub
	}

	public void criaSeguro(ActionEvent event) {
		JavaFXUtil.trocaDeTela(JavaFXUtil.NOVO_SEGURO_FXML, event);
		
	}

}
