package model;

import java.math.BigDecimal;

public class ItemPedido {
	
	private Produto produto;
	private int quantidade;
	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		
		if(produto==null) {
			throw new NullPointerException("O valor do produto do itemPedido não pode ser nulo");
		}
		
		this.produto = produto;
		
	}
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		
		if (quantidade<=0) {
			throw new IllegalArgumentException("O valor da quantidade não pode ser zero ou menor que zero");
		}
		
		this.quantidade = quantidade;
	
	}
	
	public BigDecimal calcularSubTotal() {
		
		BigDecimal qtd = BigDecimal.valueOf(getQuantidade());
		BigDecimal subTotal= getProduto().getPreco().multiply(qtd);
		
		return subTotal;
	}
	
	
}
