package br.com.caelum.javafx.api.modelo;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.MANIPULADOR_DE_CONTAS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PACOTE_BASE;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ContaDao {

	private static List<Object> contas;

	static {
		String nomeDoMetodo = "carregaDados";
		try {
			Class<?> classe = Class.forName(PACOTE_BASE + MANIPULADOR_DE_CONTAS);
			Object manipulador = classe.newInstance();
			Method carregaDados = classe.getMethod(nomeDoMetodo);
			contas = (List<Object>) carregaDados.invoke(manipulador);
		} catch (NoSuchMethodException e) {
			contas = new ArrayList<>();
		} catch (SecurityException | IllegalAccessException e) {
			mostraAlerta("Não foi possível chamar o método " + nomeDoMetodo + " dentro da classe "
					+ MANIPULADOR_DE_CONTAS + ". Verifique se o método é público.");
		} catch (ClassNotFoundException | InstantiationException | IllegalArgumentException e) {
			mostraAlerta(PROBLEMAS_INTERNOS);
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			mostraAlerta(e.getTargetException().getMessage());
		}
	}

	public static boolean adiciona(Object conta) {
		if (!contas.contains(conta)) {
			contas.add(conta);
			return true;
		}
		return false;
	}

	public static List<Object> getContas() {
		return contas;
	}
}
