package br.com.caelum.javafx.api.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Evento {

	private Campos campos;

	public Evento(Campos campos) {
		this.campos = campos;
	}

	public int getInt(String campo) {
		try {
			return Integer.parseInt(getString(campo));
		} catch (NumberFormatException e) {
			throw new RuntimeException("O campo procurado não possui valor do tipo int. Verifique o tipo e chame o método adequado.");
		}
	}

	public double getDouble(String campo) {
		try {
			return Double.parseDouble(getString(campo));
		} catch (NumberFormatException e) {
			throw new RuntimeException("O campo procurado não possui valor do tipo double. Verifique o tipo e chame o método adequado.");
		}
	}

	public String getString(String campo) {
		TextField texto = campos.buscaCampo(campo);
		return texto.getText();
	}

	public String getSelecionadoNoRadio(String campo) {
		ToggleGroup toggle = campos.buscaCampo(campo);
		RadioButton radio = (RadioButton) toggle.getSelectedToggle();
		return radio.getText();
	}
	
	public Object getSelecionadoNoCombo(String campo){
		ComboBox<?> combo = campos.buscaCampo(campo);
		return combo.getSelectionModel().getSelectedItem();
	}
}
