package br.com.caelum.javafx.api.util;

import java.lang.reflect.Field;
import java.util.Map;

import br.com.caelum.javafx.api.controllers.ContasController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Campos {
	
	private static Map<String, TextField> todosOsCampos;

	public static void registraCampos(ContasController controller){
		Field[] atributos = controller.getClass().getFields();
		for (Field atributo : atributos) {
			if(atributo.getClass().isAssignableFrom(TextField.class) && atributo.isAnnotationPresent(FXML.class)){
				try {
					atributo.setAccessible(true);
					todosOsCampos.put(atributo.getName(), (TextField) atributo.get(controller));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException();
				}
			}
		}
	}
	
	public static TextField buscaCampo(String campo) {
		TextField encontrado = todosOsCampos.get(campo);
		if(encontrado == null){
			throw new RuntimeException("Não foi encontrado o campo com o nome " + campo + ". Verifique se o nome está correto.");
		}
		return encontrado;
	}
}
