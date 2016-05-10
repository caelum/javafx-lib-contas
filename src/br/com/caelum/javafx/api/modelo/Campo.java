package br.com.caelum.javafx.api.modelo;

import javafx.scene.control.TextField;

public class Campo {

	private boolean ehAtributoDaConta;
	private TextField valor;
	private String nome;

	public Campo(boolean ehAtributoDaConta, TextField valor, String nome) {
		this.ehAtributoDaConta = ehAtributoDaConta;
		this.valor = valor;
		this.nome = nome;
	}

	public boolean ehAtributoDaConta() {
		return ehAtributoDaConta;
	}

	public TextField getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

}
