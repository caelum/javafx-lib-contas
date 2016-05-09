package br.com.caelum.javafx.api.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import br.com.caelum.javafx.api.controllers.ContasController;

public class Campos {
	
	private static Map<String, Label> todosOsCampos;

	public static void registraCampos(ContasController controller){
		Field[] atributos = controller.getClass().getFields();
		for (Field atributo : atributos) {
			if(atributo.getClass().isAssignableFrom(Label.class) && atributo.isAnnotationPresent(FXML.class)){
				try {
					atributo.setAccessible(true);
					todosOsCampos.put(atributo.getName(), (Label) atributo.get(controller));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException("Oops, problemas internos. Chame o instrutor. '-' \nException: " + e.getMessage());
				}
			}
		}
	}
	
	public static Label buscaCampo(String campo) {
		Label encontrado = todosOsCampos.get(campo);
		if(encontrado == null){
			throw new RuntimeException("Não foi encontrado o campo com o nome " + campo + ". Verifique se o nome está correto.");
		}
		return encontrado;
	}

	public static Set<String> getNomeDosCampos(){
		return todosOsCampos.keySet();
	}
}
