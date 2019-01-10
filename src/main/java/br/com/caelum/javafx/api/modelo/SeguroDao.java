package br.com.caelum.javafx.api.modelo;

import java.util.ArrayList;
import java.util.List;

public class SeguroDao {

	private static List<Object> seguros = new ArrayList<>();

	public static void adiciona(Object conta) {
		seguros.add(conta);
	}

	public static List<Object> getSeguros() {
		return seguros;
	}

}