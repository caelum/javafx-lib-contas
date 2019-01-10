package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.CLASSE_CONTA;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PACOTE_MODELO;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.com.caelum.javafx.api.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import br.com.caelum.javafx.api.annotations.EhAtributoDaConta;
import br.com.caelum.javafx.api.modelo.ContaDao;
import br.com.caelum.javafx.api.util.Evento;

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
	private ComboBox<Object> destino;

	private Object conta;

	@Override
	public void populaDados(Object[] objects) {
		super.populaDados(objects);
		ObservableList<Object> dados = FXCollections.observableArrayList(ContaDao.getContas());
		this.conta = objects[0];
		dados.remove(this.conta);
		destino.setItems(dados);
		ordenaLista();
		populaTela();
		atualizaConta();
	}

	private void ordenaLista() {
		String nomeDoMetodo = "ordenaLista";
		try {
			Method metodo = getManipulador().getClass().getMethod(nomeDoMetodo, Evento.class);
			Evento evento = new Evento(campos);
			metodo.invoke(getManipulador(), evento);
		} catch (NoSuchMethodException e) {
			// Ignora pois o aluno só vai ter este método quando fizer o exercício de Collections
		} catch (SecurityException | IllegalAccessException e) {
			mostraAlerta("Não foi possível chamar o método " + nomeDoMetodo + " com o parâmetro do tipo Evento dentro da classe "
					+ getManipulador().getClass().getSimpleName() + ". Verifique se o método é público.");
		} catch (IllegalArgumentException e) {
			mostraAlerta(PROBLEMAS_INTERNOS);
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			mostraAlerta(e.getTargetException().getMessage());
			throw new RuntimeException(e);
		}
	}

	@FXML
	void deposita(ActionEvent event) {
		executaAcao("deposita");
	}

	@FXML
	void saca(ActionEvent event) {
		executaAcao("saca");
	}

	@FXML
	void transfere(ActionEvent event) {
		executaAcao("transfere");
	}

	@Override
	protected String getNomeDoManipulador() {
		return JavaFXUtil.MANIPULADOR_DE_CONTAS;
	}

	private void executaAcao(String acao){
		invocaMetodo(acao);
		populaTela();
	}

	private void populaTela() {
		for(String nome : campos.getNomeDosCampos()) {
			String nomeDoMetodo = "get" + StringUtils.capitalize(nome);
			try {
				Method getter = conta.getClass().getMethod(nomeDoMetodo);
				Object retorno = getter.invoke(conta);
				Object campo = campos.buscaCampo(nome);
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

	private void atualizaConta() {
		Field[] fields = getManipulador().getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Class<?> classeConta = Class.forName(PACOTE_MODELO + CLASSE_CONTA);
				if(field.getType().isAssignableFrom(classeConta)) {
					field.setAccessible(true);
					field.set(getManipulador(), conta);
					break;
				}
			} catch (ClassNotFoundException e) {
				mostraAlerta("Não foi encontrada a classe " + CLASSE_CONTA + " no pacote " + PACOTE_MODELO + " Verifique se o pacote e o nome da classe estão corretos.");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				mostraAlerta(PROBLEMAS_INTERNOS);
				throw new RuntimeException(e);
			}
		}
	}
}
