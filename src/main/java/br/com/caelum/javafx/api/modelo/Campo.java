package br.com.caelum.javafx.api.modelo;

public class Campo {

	private boolean ehAtributoDaConta;
	private Object valor;
	private String nome;

	public Campo(boolean ehAtributoDaConta, Object valor, String nome) {
		this.ehAtributoDaConta = ehAtributoDaConta;
		this.valor = valor;
		this.nome = nome;
	}

	public boolean ehAtributoDaConta() {
		return ehAtributoDaConta;
	}

	public Object getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

}
