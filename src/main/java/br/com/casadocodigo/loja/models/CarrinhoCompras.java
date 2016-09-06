package br.com.casadocodigo.loja.models;

import java.util.LinkedHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras {

	private LinkedHashMap<CarrinhoItem, Integer> itens;

	public CarrinhoCompras() {
		itens = new LinkedHashMap<CarrinhoItem, Integer>();
	}

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	private int getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}

		return itens.get(item);
	}
	
	public Integer getQuantidade() {		
		return itens.values()
				.stream()
				.reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}

}