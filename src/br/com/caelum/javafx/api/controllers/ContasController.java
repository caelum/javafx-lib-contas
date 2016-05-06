package br.com.caelum.javafx.api.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import br.com.caelum.javafx.api.util.Campos;
import br.com.caelum.javafx.api.util.Evento;

public class ContasController {
	
	private static final String PACOTE = "br.com.caelum.contas.";
	private static final String MANIPULADOR_DE_CONTAS = "ManipuladorDeContas";

	@FXML
	private TextField valor;
	
	@FXML
	private TextField numero;

	@FXML
	private TextField agencia;

	@FXML
	private TextField titular;
	
	@FXML
	public void initialize(){
		Campos.registraCampos(this);
	}
	
	public void criaConta(){
		
	}
	
	public void saca(ActionEvent event){
		invocaMetodo("saca");
	}
	
	public void deposita(ActionEvent event){
		invocaMetodo("deposita");
	}

	public void transfere(ActionEvent event){
		invocaMetodo("transfere");
	}

	private void invocaMetodo(String nomeDoMetodo) {
		try {
			Class<?> classe = Class.forName(PACOTE + MANIPULADOR_DE_CONTAS);
			Object manipuladorDeContas = classe.newInstance();
			Method metodo = classe.getMethod(nomeDoMetodo, Evento.class);
			Evento evento = new Evento();
			metodo.invoke(manipuladorDeContas, evento);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Não foi encontrada a classe " + MANIPULADOR_DE_CONTAS + " no pacote " + PACOTE + " Verifique se o pacote e o nome da classe estão corretos.");
		} catch (InstantiationException e) {
			throw new RuntimeException("Não foi possível chamar a classe " + MANIPULADOR_DE_CONTAS + ". Verifique se a sua classe possui um construtor sem argumentos.");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Não foi possível chamar a classe " + MANIPULADOR_DE_CONTAS + ". Verifique se a sua classe possui um construtor sem argumentos público.");
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Não foi encontrado o método " + nomeDoMetodo + " com o parâmetro do tipo Evento dentro da classe " + MANIPULADOR_DE_CONTAS + ". Verifique se o método foi criado corretamente.");
		} catch (SecurityException e) {
			throw new RuntimeException("Não foi possível chamar o método " + nomeDoMetodo + " com o parâmetro do tipo Evento dentro da classe " + MANIPULADOR_DE_CONTAS + ". Verifique se o método é público.");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Oops, problemas internos. Chame o instrutor. '-'");
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
