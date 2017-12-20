package loja.models;

import java.math.BigDecimal;

public class DadosPagamento {

	private BigDecimal value;

	public DadosPagamento() {
		// TODO Auto-generated constructor stub
	}

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
}
