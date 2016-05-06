package br.com.caelum.javafx.api.util;


public class Evento {

	public int getInt(String campo) {
		try {			
			return Integer.parseInt(Campos.buscaCampo(campo).getText());
		} catch (NumberFormatException e){
			throw new RuntimeException("O campo procurado não possui valor do tipo int. Verifique o tipo e chame o método adequado.");
		}
	}
	
	public double getDouble(String campo){
		try{
			return Double.parseDouble(Campos.buscaCampo(campo).getText());
		} catch (NumberFormatException e){
			throw new RuntimeException("O campo procurado não possui valor do tipo double. Verifique o tipo e chame o método adequado.");
		}
	}
	
	public String getString(String campo){
		return Campos.buscaCampo(campo).getText();
	}

}
