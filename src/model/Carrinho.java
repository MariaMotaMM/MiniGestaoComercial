package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Carrinho {
	
	private List <ItemPedido> itensPedido = new ArrayList<>(); 

	
	

	public  void adicionarItem(Produto produto, int quantidade) {
	
		if (produto == null) {
			throw new NullPointerException("Valor do produto não pode ser nulo!");
		}
		
		if(quantidade <=0) {
			throw new IllegalArgumentException("O valor da quantidade não pode ser zero ou menor que zero no carrinho");
		}
		
		for (ItemPedido itemPedido: itensPedido) {
			if (itemPedido.getProduto() == produto) {
				itemPedido.setQuantidade(itemPedido.getQuantidade() +quantidade);
				return ;
			}
		}
		
		ItemPedido item= new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		
		itensPedido.add(item);
		
		
	}
	
	public void removerItem (Produto produto) {
		boolean produtoPedido= false;
		if(produto== null) {
			
			throw new NullPointerException("O valor do produto não pode ser nulo!");
		}
		
		for (int i=0; i<itensPedido.size();i++) {
			 if(itensPedido.get(i).getProduto()==produto) {
				 itensPedido.remove(i);
				 produtoPedido=true;
			 }
		}
		
		if(!produtoPedido) {
			throw new IllegalArgumentException("O produto não está cadastrado nesse item");
		}
	}
	
	
	public BigDecimal calcularValorTotal() {
		
		BigDecimal valorTotal = new BigDecimal ("0");
		for (ItemPedido item :itensPedido) {
			valorTotal= valorTotal.add(item.calcularSubTotal());
		}
		
		return valorTotal;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}
	
	
}
