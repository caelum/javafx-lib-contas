package br.com.caelum.javafx.api.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class JavaFXUtil {
	
	public static final String PROBLEMAS_INTERNOS = "Oops, problemas internos. Chame o instrutor. '-'";
	public static final String DEU_PAU_EXCEPTION = "Deu pau '-'";
	public static final String CLASSE_CONTA = "br.com.caelum.contas.modelo.Conta";
	public static final String PACOTE_BASE = "br.com.caelum.contas.";
	public static final String PACOTE_MODELO = PACOTE_BASE + "modelo.";
	public static final String MANIPULADOR_DE_CONTAS = "ManipuladorDeContas";

	public static void mostraAlerta(String mensagem){
		Alert alerta = new Alert(AlertType.ERROR, mensagem, ButtonType.OK);
		alerta.setHeaderText(DEU_PAU_EXCEPTION);
		alerta.showAndWait();
	}
}
