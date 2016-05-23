package br.com.caelum.javafx.api.modelo;

import java.util.ArrayList;
import java.util.List;

public class ContaDao {

	private static List<Object> contas = new ArrayList<>();

	public static void adiciona(Object conta) {
		contas.add(conta);
	}
	
	public static List<Object> getContas() {
		return contas;
	}
}
