package br.com.caelum.javafx.api.modelo;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.INTERFACE_TRIBUTAVEL;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PACOTE_MODELO;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDao {

	public static List<Object> getProdutos() {

		List<Object> tributaveis = ContaDao.getContas().stream().filter(conta -> {
			try {
				Class<?> classe = Class.forName(PACOTE_MODELO + INTERFACE_TRIBUTAVEL);
				return classe.isAssignableFrom(conta.getClass());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Não foi encontrada a interface " + INTERFACE_TRIBUTAVEL + " no pacote " + PACOTE_MODELO);
			}
		}).collect(Collectors.toList());
		tributaveis.addAll(SeguroDao.getSeguros());

		return tributaveis;
	}

}
