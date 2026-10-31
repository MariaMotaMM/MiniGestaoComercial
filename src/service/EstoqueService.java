package service;

import enums.TipoSaida;
import enums.TipoEntrada;
import model.Produto;

public class EstoqueService {

	private Produto produto;
	private int quantidade;
	private TipoSaida tipoSaida;
	private TipoEntrada tipoEntrada;
	private String descricao;
	
	
	public EstoqueService setDescricao(String descricao) {
		if (descricao==null) {
			
			throw new NullPointerException("O valor da descricao não pode ser nulo");
			
		}
		
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("O valor não pode ser vazio");
		}
		
		this.descricao=descricao;
		return this;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	
	
	public EstoqueService setProduto(Produto produto) {
		
		if (produto == null) {
			throw new NullPointerException("O valor do produto não pode ser nulo");
		}
		
		this.produto= produto;
		return this;
		
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	
	public EstoqueService setQuantidade(int quantidade) {
		if(quantidade <=0) {
			throw new IllegalArgumentException("O valor da quantidade não pode ser zero ou negativo");
		}
		
		this.quantidade = quantidade;
		
		return this;
	}
	
	
	public int getQuantidade() {
		return quantidade;
	}
	
	
	public EstoqueService setTipoSaida(TipoSaida tipoSaida) {
		
		if (tipoSaida == null) {
			throw new NullPointerException("O valor não pode ser nulo");
		}
		
		this.tipoSaida= tipoSaida;
	
		return this;
	}
	
	public TipoSaida getTipoSaida() {
		return tipoSaida;
	}
	
	public EstoqueService setTipoEntrada(TipoEntrada tipoEntrada) {
		
		if (tipoEntrada == null) {
			throw new NullPointerException("O valor não pode ser nulo");
		}
		
		this.tipoEntrada= tipoEntrada;
	
		return this;
	}
	
	public TipoEntrada getTipoEntrada() {
		return tipoEntrada;
	}
	
	
	
	
	public void adicionarEstoque() {
		getProduto().getEstoque().aumentarEstoque(getTipoEntrada(), getDescricao(), getQuantidade());
	}
	
	
	public void retirarEstoque() {
		getProduto().getEstoque().reduzirEstoque(getTipoSaida(), getQuantidade(), getDescricao());
	}
}
