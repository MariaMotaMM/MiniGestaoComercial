package model;

import java.math.BigDecimal;

import enums.UnidadeMedida;

public class Produto {
	
	private int id;
	private  static int valor;
	private String nomeProduto;
	private String descricao;
	private String marca;
	private BigDecimal preco;
	private UnidadeMedida unidadeMedida;
	private Estoque estoque;
	
	
	
	public Produto () {
		valor++;
		id=valor;
	}
	
	public Produto setNomeProduto(String nomeProduto) {
		
		if (nomeProduto== null) {
			throw new NullPointerException("O valor do nome do produto não pode ser nulo");
		}
		
		if(nomeProduto.isBlank()) {
			throw new IllegalArgumentException("Preencha o nome do produto! ");
		}
		
		this.nomeProduto= nomeProduto;
		return this;
		
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto setDescricao(String descricao) {
		
		if(descricao==null) {
			throw new NullPointerException("O valor de descricao é nula");
		}
		
		if(descricao.isBlank()) {
			throw new IllegalArgumentException("Preencha a descricao do produto!");
		}
		
		this.descricao = descricao;
		return this;
	}

	public String getMarca() {
		return marca;
	}

	public Produto setMarca(String marca) {
		
		if (marca==null) {
		   throw new NullPointerException("O valor da marca é nulo");	
		}
		
		if (marca.isBlank()) {
			throw new IllegalArgumentException("Preencha a marca!");
		}
		
		this.marca = marca;
		return this;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Produto setPreco(BigDecimal preco) {
		
		if (preco == null) {
			throw new NullPointerException("O valor do preço é null!");
		}
		
		if(preco.compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("O valor do preço nao pode ser igual ou menor que zero");
		}
		
		this.preco = preco;
		return this;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public Produto setUnidadeMedida(UnidadeMedida unidadeMedida) {
		if(unidadeMedida==null) {
			throw new NullPointerException("A unidade de medida não pode ser nulo");
		}
		this.unidadeMedida = unidadeMedida;
		return this;
	}

	public int getId() {
		return id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public void alterarPrecoVenda(BigDecimal novoPreco) {
		
		if(novoPreco == null) {
			throw new NullPointerException("O valor não pode ser nulo");
		}
		
		if(novoPreco.equals(getPreco())){
			throw new IllegalArgumentException("Digite um valor diferente");
		}
		
		if(novoPreco.compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("O valor não pode ser menor ou igual a zero ");
		}
		
		setPreco(novoPreco);
	}
	
	
	public Estoque getEstoque() {
		return estoque;
	}

	public Produto setEstoque(Estoque estoque) {
		if(estoque==null) {
			throw new NullPointerException("O valor não pode ser nulo");
		}
		this.estoque=estoque;
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Codigo Produto: "+getId());
		sb.append("\nNome Produto: "+getNomeProduto());
		sb.append("\nDescricao: "+getDescricao());
		sb.append("\nMarca: "+getMarca());
		sb.append("\nPreco: "+getPreco());
		sb.append("\nUnidade Medida: "+getUnidadeMedida());
		
		return sb.toString();
		
	}
	
}
