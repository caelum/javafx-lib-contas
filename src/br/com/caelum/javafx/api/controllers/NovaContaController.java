package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.CLASSE_CONTA;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.MANIPULADOR_DE_CONTAS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PACOTE_MODELO;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.TELA_INICIAL_FXML;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.Field;

import br.com.caelum.javafx.api.annotations.EhAtributoDaConta;
import br.com.caelum.javafx.api.modelo.ContaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class NovaContaController extends Controller {

	private static final String CONTA_EXISTENTE = "Já existe uma conta com esse número e agência.";

	@FXML
	private ToggleGroup tipo;

	@FXML
	@EhAtributoDaConta
	private TextField titular;

	@FXML
	@EhAtributoDaConta
	private TextField numero;

	@FXML
	@EhAtributoDaConta
	private TextField agencia;

	@FXML
	void criaConta(ActionEvent event) {
		invocaMetodo("criaConta");
		Object conta = buscaConta();
		if (ContaDao.adiciona(conta)) {
			JavaFXUtil.trocaDeTela(TELA_INICIAL_FXML, event);
		} else {
			mostraAlerta(CONTA_EXISTENTE);
		}
	}

	@Override
	protected String getNomeDoManipulador() {
		return MANIPULADOR_DE_CONTAS;
	}

	private Object buscaConta() {
		try {
			Field[] fields = getManipulador().getClass().getDeclaredFields();
			for (Field field : fields) {
				Class<?> classeConta = Class.forName(PACOTE_MODELO + CLASSE_CONTA);
				if (field.getType().isAssignableFrom(classeConta)) {
					field.setAccessible(true);
					Object conta = field.get(getManipulador());
					return conta;
				}
			}
			mostraAlerta("Não foi encontrado o atributo do tipo " + CLASSE_CONTA + " na classe "
					+ getNomeDoManipulador() + " Verifique se o atributo foi criado corretamente.");
		} catch (ClassNotFoundException e) {
			mostraAlerta("Não foi encontrada a classe " + CLASSE_CONTA + " no pacote " + PACOTE_MODELO
					+ " Verifique se o pacote e o nome da classe estão corretos.");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			mostraAlerta(PROBLEMAS_INTERNOS);
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Não foi possível encontrar a conta.");
	}
}
