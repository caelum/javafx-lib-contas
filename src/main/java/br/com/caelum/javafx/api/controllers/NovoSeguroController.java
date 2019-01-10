package br.com.caelum.javafx.api.controllers;

import static br.com.caelum.javafx.api.controllers.JavaFXUtil.CLASSE_SEGURO_DE_VIDA;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PACOTE_MODELO;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.PROBLEMAS_INTERNOS;
import static br.com.caelum.javafx.api.controllers.JavaFXUtil.mostraAlerta;

import java.lang.reflect.Field;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import br.com.caelum.javafx.api.modelo.SeguroDao;

public class NovoSeguroController extends Controller{

    @FXML
    private TextField titular;

    @FXML
    private TextField numeroApolice;

    @FXML
    private TextField valor;
    
    @FXML
    void novoSeguro(ActionEvent event) {
    	invocaMetodo("criaSeguro");
    	Object seguro = buscaSeguro();
    	SeguroDao.adiciona(seguro);
    	JavaFXUtil.trocaDeTela(JavaFXUtil.TELA_INICIAL_FXML, event);
    }

	@Override
	protected String getNomeDoManipulador() {
		return JavaFXUtil.MANIPULADOR_DE_SEGUROS;
	}

	private Object buscaSeguro() {
		try {
			Field[] fields = getManipulador().getClass().getDeclaredFields();
			for (Field field : fields) {
					Class<?> classeSeguroDeVida = Class.forName(PACOTE_MODELO + CLASSE_SEGURO_DE_VIDA);
					if(field.getType().isAssignableFrom(classeSeguroDeVida)) {
						field.setAccessible(true);
						Object conta = field.get(getManipulador());
						return conta;
					}
			}
			mostraAlerta("Não foi encontrado o atributo do tipo " + CLASSE_SEGURO_DE_VIDA + " na classe " + getNomeDoManipulador() + " Verifique se o atributo foi criado corretamente.");
		} catch (ClassNotFoundException e) {
			mostraAlerta("Não foi encontrada a classe " + CLASSE_SEGURO_DE_VIDA + " no pacote " + PACOTE_MODELO + " Verifique se o pacote e o nome da classe estão corretos.");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			mostraAlerta(PROBLEMAS_INTERNOS);
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Não foi possível encontrar a conta.");
	}

}
