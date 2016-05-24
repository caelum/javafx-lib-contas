package br.com.caelum.javafx.api.util;

import javafx.scene.control.RadioButton;

public class Evento {

	private Campos campos;

	public Evento(Campos campos) {
		this.campos = campos;
	}

	public int getInt(String campo) {
		try {
			return Integer.parseInt(campos.buscaTextoDoCampo(campo));
		} catch (NumberFormatException e) {
			throw new RuntimeException("O campo procurado não possui valor do tipo int. Verifique o tipo e chame o método adequado.");
		}
	}

	public double getDouble(String campo) {
		try {
			return Double.parseDouble(campos.buscaTextoDoCampo(campo));
		} catch (NumberFormatException e) {
			throw new RuntimeException("O campo procurado não possui valor do tipo double. Verifique o tipo e chame o método adequado.");
		}
	}

	public String getString(String campo) {
		return campos.buscaTextoDoCampo(campo);
	}

	public String getSelecionado(String campo) {
		RadioButton radio = (RadioButton) campos.buscaSelecionado(campo).getSelectedToggle();
		return radio.getText();
	}
}
