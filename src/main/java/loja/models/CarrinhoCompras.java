package loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public void adicionar(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public void remover(Integer produtoId, TipoPreco tipo) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		
		itens.remove(new CarrinhoItem(produto, tipo));
	}

	/*
	 * retorna a quantidade de itens no carrinho: se não tiver chave significa que o
	 * carrinho esta vazio;
	 */
	public int getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}

	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}

		return total;
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoCompras other = (CarrinhoCompras) obj;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		return true;
	}
}
