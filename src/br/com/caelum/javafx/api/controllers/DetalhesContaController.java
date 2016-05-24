package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.CLASSE_CONTA;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sun.xml.internal.ws.util.StringUtils;

import br.com.caelum.javafx.api.annotations.EhAtributoDaConta;
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
	@EhAtributoDaConta
	private TitledPane tipo;

	@FXML
	@EhAtributoDaConta
	private Label titular;

	@FXML
	@EhAtributoDaConta
	private Label numero;

	@FXML
	@EhAtributoDaConta
	private Label agencia;

	@FXML
	@EhAtributoDaConta
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

	@Override
	public void populaDados(Object[] objects) {
		super.populaDados(objects);
		populaTela(objects[0]);
	}

	private void populaTela(Object conta) {
		for(String nome : campos.getNomeDosCampos()) {
			String nomeDoMetodo = "get" + StringUtils.capitalize(nome);
			try {
				Method getter = conta.getClass().getMethod(nomeDoMetodo);
				Object retorno = getter.invoke(conta);
				Object campo = campos.buscaCampoParaPopular(nome);
				campo.getClass().getMethod("setText", String.class).invoke(campo, retorno.toString());
			} catch (NoSuchMethodException e) {
				mostraAlerta("Não foi encontrado o método " + nomeDoMetodo + " dentro da classe " + CLASSE_CONTA + ". Verifique se o método foi criado corretamente.");
			} catch (SecurityException | IllegalAccessException e) {
				mostraAlerta("Não foi possível chamar o método " + nomeDoMetodo + " dentro da classe " + CLASSE_CONTA + ". Verifique se o método é público.");
			} catch (IllegalArgumentException e) {
				mostraAlerta(PROBLEMAS_INTERNOS);
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				mostraAlerta(e.getTargetException().getMessage());
			}
		}
	}
}
