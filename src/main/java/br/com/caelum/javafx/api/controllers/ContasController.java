 package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.CLASSE_CONTA;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PACOTE_MODELO;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.com.caelum.javafx.api.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import br.com.caelum.javafx.api.annotations.EhAtributoDaConta;

public class ContasController extends Controller {
	
	@FXML
	private TextField valor;

	@FXML
	@EhAtributoDaConta
	private TextField saldo;
	
	@FXML
	@EhAtributoDaConta
	private TextField numero;

	@FXML
	@EhAtributoDaConta
	private TextField agencia;

	@FXML
	@EhAtributoDaConta
	private TextField titular;

	@FXML
	public void criaConta(){
		executaAcao("criaConta");
	}

	@FXML
	public void saca(ActionEvent event){
		executaAcao("saca");
	}
	
	@FXML
	public void deposita(ActionEvent event){
		executaAcao("deposita");
	}

	private void executaAcao(String acao){
		invocaMetodo(acao);
		atualizaConta();
	}

	private void atualizaConta() {
		Field[] fields = getManipulador().getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Class<?> conta = Class.forName(PACOTE_MODELO + CLASSE_CONTA);
				if(field.getType().isAssignableFrom(conta)) {
					field.setAccessible(true);
					populaTela(field.get(getManipulador()));
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

	private void populaTela(Object conta) {
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

	@Override
	protected String getNomeDoManipulador() {
		return JavaFXUtil.MANIPULADOR_DE_CONTAS;
	}
}
