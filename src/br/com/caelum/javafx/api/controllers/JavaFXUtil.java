package br.com.caelum.javafx.api.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class JavaFXUtil {

	public static final String PROBLEMAS_INTERNOS = "Oops, problemas internos. Chame o instrutor. '-'";
	public static final String DEU_PAU_EXCEPTION = "Deu pau '-'";
	public static final String CLASSE_CONTA = "Conta";
	public static final String CLASSE_SEGURO_DE_VIDA = "SeguroDeVida";
	public static final String PACOTE_BASE = "br.com.caelum.contas.";
	public static final String PACOTE_MODELO = PACOTE_BASE + "modelo.";
	public static final String MANIPULADOR_DE_CONTAS = "ManipuladorDeContas";
	public static final String NOVA_CONTA_FXML = "NovaConta.fxml";
	public static final String TELA_INICIAL_FXML = "Inicial.fxml";
	public static final String NOVO_SEGURO_FXML = "NovoSeguro.fxml";
	public static final String DETALHES_CONTA_FXML = "DetalhesConta.fxml";
	public static final String MANIPULADOR_DE_SEGUROS = "ManipuladorDeSeguroDeVida";
	public static final String MANIPULADOR_DE_TRIBUTAVEIS = "ManipuladorDeTributaveis";
	public static final String INTERFACE_TRIBUTAVEL = "Tributavel";

	public static boolean temTributavel = false;

	public static void mostraAlerta(String mensagem) {
		mostraAlerta(mensagem, DEU_PAU_EXCEPTION, AlertType.ERROR);
	}

	public static void mostraAlerta(String mensagem, String titulo, AlertType tipoDoAlerta) {
		Alert alerta = new Alert(tipoDoAlerta ,mensagem, ButtonType.OK);
		alerta.setHeaderText(titulo);
		alerta.setResizable(true);
		alerta.showAndWait();
	}

	static void trocaDeTela(String nomeFxml, ActionEvent event) {
		buscaTela(nomeFxml, event);
	}

	static void trocaDeTela(String nomeFxml, ActionEvent event, Object... objects) {
		Controller controller = buscaTela(nomeFxml, event);
		controller.populaDados(objects);

	}

	private static <T> T buscaTela(String nomeFxml, ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(JavaFXUtil.class.getClassLoader().getResource(nomeFxml));
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			return loader.getController();
		} catch (IOException e) {
			mostraAlerta(PROBLEMAS_INTERNOS);
			throw new RuntimeException(e);
		}
	}

}
