package br.com.caelum.javafx.api.modelo;

import javafx.scene.control.Control;

public class Campo {

	private boolean ehAtributoDaConta;
	private Control valor;
	private String nome;

	public Campo(boolean ehAtributoDaConta, Control valor, String nome) {
		this.ehAtributoDaConta = ehAtributoDaConta;
		this.valor = valor;
		this.nome = nome;
	}

	public boolean ehAtributoDaConta() {
		return ehAtributoDaConta;
	}

	public Control getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

}
