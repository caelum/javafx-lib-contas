package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PACOTE_BASE;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.TELA_INICIAL_FXML;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import br.com.caelum.javafx.api.util.Campos;
import br.com.caelum.javafx.api.util.Evento;

public abstract class Controller {

	private Object manipulador;
	protected Campos campos = new Campos();
	protected Object[] objects;

	@FXML
	public void initialize() {
		campos.registraCampos(this);
		criaManipulador();
	}

	protected abstract String getNomeDoManipulador();

	public Object getManipulador() {
		return manipulador;
	}

	protected void criaManipulador() {
		String nomeDaClasse = getNomeDoManipulador();
		try {
			Class<?> classe = Class.forName(PACOTE_BASE + nomeDaClasse);
			this.manipulador = classe.newInstance();
		} catch (ClassNotFoundException e) {
			mostraAlerta("Não foi encontrada a classe " + nomeDaClasse + " no pacote " + PACOTE_BASE
					+ " Verifique se o pacote e o nome da classe estão corretos.");
		} catch (InstantiationException e) {
			mostraAlerta("Não foi possível chamar a classe " + nomeDaClasse + ". Verifique se a sua classe possui um construtor sem argumentos.");
		} catch (IllegalAccessException e) {
			mostraAlerta("Não foi possível chamar a classe " + nomeDaClasse
					+ ". Verifique se a sua classe possui um construtor sem argumentos público.");
		}
	}

	protected void invocaMetodo(String nomeDoMetodo) {
		try {
			Method metodo = manipulador.getClass().getMethod(nomeDoMetodo, Evento.class);
			Evento evento = new Evento(campos);
			metodo.invoke(manipulador, evento);
		} catch (NoSuchMethodException e) {
			mostraAlerta("Não foi encontrado o método " + nomeDoMetodo + " com o parâmetro do tipo Evento dentro da classe "
					+ manipulador.getClass().getSimpleName() + ". Verifique se o método foi criado corretamente.");
		} catch (SecurityException | IllegalAccessException e) {
			mostraAlerta("Não foi possível chamar o método " + nomeDoMetodo + " com o parâmetro do tipo Evento dentro da classe "
					+ manipulador.getClass().getSimpleName() + ". Verifique se o método é público.");
		} catch (IllegalArgumentException e) {
			mostraAlerta(PROBLEMAS_INTERNOS);
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			mostraAlerta(e.getTargetException().getMessage());
			throw new RuntimeException(e);
		}
	}

	public void populaDados(Object[] objects) {
		this.objects = objects;
	}

	@FXML
	void voltar(ActionEvent event) {
		JavaFXUtil.trocaDeTela(TELA_INICIAL_FXML, event);
	}

}
