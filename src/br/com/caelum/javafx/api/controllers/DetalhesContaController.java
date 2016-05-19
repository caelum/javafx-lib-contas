package br.com.caelum.javafx.api.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class DetalhesContaController {
	
    @FXML
    private TitledPane tipo;

    @FXML
    private Label titular;

    @FXML
    private Label numero;

    @FXML
    private Label agencia;

    @FXML
    private Label saldo;

    @FXML
    private TextField valorOperacao;

    @FXML
    private TextField valorTransferencia;

    @FXML
    private ComboBox<?> listaContas;

    @FXML
    void deposita(ActionEvent event) {

    }

    @FXML
    void saca(ActionEvent event) {

    }

    @FXML
    void transfere(ActionEvent event) {

    }
}
