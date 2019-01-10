package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.MANIPULADOR_DE_TRIBUTAVEIS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.caelum.javafx.api.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import br.com.caelum.javafx.api.modelo.ProdutoDao;

public class ListaProdutosController extends Controller {

	@FXML
	private TableView<Object> listaTributaveis;

	public ListaProdutosController(TableView<Object> listaProdutos) {
		this.listaTributaveis = listaProdutos;
		super.initialize();
		ObservableList<Object> dados = FXCollections.observableArrayList(ProdutoDao.getProdutos());
		this.listaTributaveis.setItems(dados);
	}

	public void calculaImpostos(ActionEvent event) {
		invocaMetodo("calculaImpostos");
		double valor = buscaValorTotalDeImpostos();
		JavaFXUtil.mostraAlerta("Valor total de impostos = " + valor, "Relatório de Tributáveis", AlertType.INFORMATION);
	}

	private double buscaValorTotalDeImpostos() {
		Field[] fields = getManipulador().getClass().getDeclaredFields();
		for (Field field : fields) {
			String nomeDoMetodo = "get" + StringUtils.capitalize(field.getName());
			try {
				if (field.getType().isAssignableFrom(double.class)) {
					return (double) getManipulador().getClass().getMethod(nomeDoMetodo).invoke(getManipulador());
				}
			} catch (NoSuchMethodException e) {
				mostraAlerta("Não foi encontrado o método " + nomeDoMetodo + " dentro da classe " + getNomeDoManipulador()
						+ ". Verifique se o método foi criado corretamente.");
			} catch (SecurityException | IllegalAccessException e) {
				mostraAlerta("Não foi possível chamar o método " + nomeDoMetodo + " dentro da classe " + getNomeDoManipulador()
						+ ". Verifique se o método é público.");
			} catch (IllegalArgumentException e) {
				mostraAlerta(PROBLEMAS_INTERNOS);
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				mostraAlerta(e.getTargetException().getMessage());
			}
		}
		throw new RuntimeException("Não foi possível encontrar o método");
	}

	@Override
	protected String getNomeDoManipulador() {
		return MANIPULADOR_DE_TRIBUTAVEIS;
	}

}
