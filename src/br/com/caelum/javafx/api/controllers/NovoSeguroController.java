package br.com.caelum.javafx.api.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NovoSeguroController {

    @FXML
    private TextField titular;

    @FXML
    private TextField numeroApolice;

    @FXML
    private TextField valor;
    
    @FXML
    void novoSeguro(ActionEvent event) {
    	JavaFXUtil.trocaDeTela(JavaFXUtil.TELA_INICIAL_FXML, event);
    }
}
