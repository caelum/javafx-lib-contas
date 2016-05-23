package br.com.caelum.javafx.api.controllers;

import br.com.caelum.javafx.api.modelo.ContaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

public class ListaContasController extends Controller {

	private TableView<Object> listaContas;

	public ListaContasController(TableView<Object> listaContas) {
		this.listaContas = listaContas;
		ObservableList<Object> dados = FXCollections.observableArrayList(ContaDao.getContas());
		listaContas.setItems(dados);
	}

	public void criaConta(ActionEvent event) {
		JavaFXUtil.trocaDeTela(JavaFXUtil.NOVA_CONTA_FXML, event);
	}

	public void salvarDados(ActionEvent event) {

	}

	@Override
	protected String getNomeDoManipulador() {
		return JavaFXUtil.MANIPULADOR_DE_CONTAS;
	}

}
