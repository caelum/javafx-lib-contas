package br.com.caelum.javafx.api.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

public class ListaContasController {

	

	public ListaContasController(TableView<?> listaContas) {

	}

	public void criaConta(ActionEvent event) {
		JavaFXUtil.trocaDeTela(JavaFXUtil.NOVA_CONTA_FXML, event);
	}

	public void salvarDados(ActionEvent event) {
		
	}

}
