package br.com.caelum.javafx.api.controllers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import br.com.caelum.javafx.api.annotations.EhAtributoDaConta;
import br.com.caelum.javafx.api.util.Campos;
import br.com.caelum.javafx.api.util.Evento;

import com.sun.xml.internal.ws.util.StringUtils;

public class ContasController {
	
	private static final String CLASSE_CONTA = "br.com.caelum.contas.modelo.Conta";
	private static final String PACOTE_BASE = "br.com.caelum.contas.";
	private static final String PACOTE_MODELO = PACOTE_BASE + "modelo.";
	private static final String MANIPULADOR_DE_CONTAS = "ManipuladorDeContas";

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

	private Object manipuladorDeContas;
	
	@FXML
	public void initialize(){
		Campos.registraCampos(this);
		criaManipuladorDeContas();
	}
	
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

	private void criaManipuladorDeContas() {
		try {
			Class<?> classe = Class.forName(PACOTE_BASE + MANIPULADOR_DE_CONTAS);
			this.manipuladorDeContas = classe.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Não foi encontrada a classe " + MANIPULADOR_DE_CONTAS + " no pacote " + PACOTE_BASE + " Verifique se o pacote e o nome da classe estão corretos.");
		} catch (InstantiationException e) {
			throw new RuntimeException("Não foi possível chamar a classe " + MANIPULADOR_DE_CONTAS + ". Verifique se a sua classe possui um construtor sem argumentos.");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Não foi possível chamar a classe " + MANIPULADOR_DE_CONTAS + ". Verifique se a sua classe possui um construtor sem argumentos público.");
		}
	}

	private void invocaMetodo(String nomeDoMetodo) {
		try {
			Method metodo = this.manipuladorDeContas.getClass().getMethod(nomeDoMetodo, Evento.class);
			Evento evento = new Evento();
			metodo.invoke(manipuladorDeContas, evento);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Não foi encontrado o método " + nomeDoMetodo + " com o parâmetro do tipo Evento dentro da classe " + MANIPULADOR_DE_CONTAS + ". Verifique se o método foi criado corretamente.");
		} catch (SecurityException | IllegalAccessException e) {
			throw new RuntimeException("Não foi possível chamar o método " + nomeDoMetodo + " com o parâmetro do tipo Evento dentro da classe " + MANIPULADOR_DE_CONTAS + ". Verifique se o método é público.");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Oops, problemas internos. Chame o instrutor. '-' \nException: " + e.getMessage());
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private void executaAcao(String acao){
		invocaMetodo(acao);
		atualizaConta();
	}

	private void atualizaConta() {
		Field[] fields = this.manipuladorDeContas.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Class<?> conta = Class.forName(CLASSE_CONTA);
				if(field.getType().isAssignableFrom(conta)) {
					field.setAccessible(true);
					populaTela(field.get(this.manipuladorDeContas));
					break;
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Não foi encontrada a classe " + CLASSE_CONTA + " no pacote " + PACOTE_MODELO + " Verifique se o pacote e o nome da classe estão corretos.");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException("Oops, problemas internos. Chame o instrutor. '-' \nException: " + e.getMessage());
			}
		}
	}

	private void populaTela(Object conta) {
		for(String nome : Campos.getNomeDosCampos()) {
			String nomeDoMetodo = "get" + StringUtils.capitalize(nome);
			try {
				Method getter = conta.getClass().getMethod(nomeDoMetodo);
				Object retorno = getter.invoke(conta);
				Campos.buscaCampo(nome).setText(retorno.toString());
			} catch (NoSuchMethodException e) {
				throw new RuntimeException("Não foi encontrado o método " + nomeDoMetodo + " dentro da classe " + CLASSE_CONTA + ". Verifique se o método foi criado corretamente.");
			} catch (SecurityException | IllegalAccessException e) {
				throw new RuntimeException("Não foi possível chamar o método " + nomeDoMetodo + " dentro da classe " + CLASSE_CONTA + ". Verifique se o método é público.");
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Oops, problemas internos. Chame o instrutor. '-' \nException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
