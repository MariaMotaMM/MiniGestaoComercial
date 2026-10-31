package model;

/*
 * Formatar saida da data e do horario, e utilizar futuramente como busca  que doi realizado
 * 
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import enums.TipoEntrada;
import enums.TipoMovimentacao;
import enums.TipoSaida;


public class Movimentacao {
	
	private static int valorSaida;
	private int idSaida;
	private static int valorEntrada;
	private int idEntrada;
	private Produto produto;
	private Estoque estoque;
	private TipoMovimentacao tipoMovimentacao;
	private TipoSaida tipoSaida;
	private TipoEntrada tipoEntrada;
	private String descricao;
	private static int codigoMovimentacao;
	private int idMovimentacao;
	private LocalDate data;
	private LocalTime horario;
	
	public Movimentacao() {
		codigoMovimentacao++;
		this.idMovimentacao = codigoMovimentacao;
	}
	
	
	
	public int getIdMovimentacao() {
		return idMovimentacao;
	}



	public Movimentacao setProduto(Produto produto) {
		if (produto == null) {
			throw new NullPointerException("O valor do produto na movimentacao nao pode ser nulo");
		}
		if (produto.getEstoque()==null) {
			throw new NullPointerException("O valor do estoque do produto  na movimentacao nao pode ser nulo");
		}
		
		this.produto= produto;
		this.estoque= produto.getEstoque();
		return this;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public Estoque getEstoque() {
		return estoque;
	}
	
	public void  movimentacaoSaida(TipoSaida tipoSaida, String descricao, int quantidade) {
		
	this.tipoMovimentacao = TipoMovimentacao.SAIDA;
	 
	 if(descricao==null) {
		 throw new NullPointerException("O valor da descricao da movimentacao nao pode ser nula");
	 }
	 
	 if (descricao.isBlank()) {
		 throw new IllegalArgumentException("O valor da descricao não pode ser vazia");
	 }
	 
	 if(tipoSaida == null) {
		 throw new NullPointerException("O valor da saida não pode ser nulo");
	 }
	 
	 this.tipoSaida=tipoSaida;
	 
	 this.descricao=descricao;
	 
	 valorSaida++;
	 this.idSaida=valorSaida;
	 
	 
	}
	
	public TipoSaida getTipoSaida() {
		return tipoSaida;
	}
	
	public String descricao() {
		return descricao;
	}
	
	public int getIdSaida() {
		return idSaida;
	}
	
	public void  movimentacaoEntrada(TipoEntrada tipoEntrada, String descricao, int quantidade) {
		
	this.tipoMovimentacao = TipoMovimentacao.ENTRADA;
	 
	 if(descricao==null) {
		 throw new NullPointerException("O valor da descricao da movimentacao nao pode ser nula");
	 }
	 
	 if (descricao.isBlank()) {
		 throw new IllegalArgumentException("O valor da descricao não pode ser vazia");
	 }
	 
	 if(tipoSaida == null) {
		 throw new NullPointerException("O valor da saida não pode ser nulo");
	 }
	 
	 this.tipoEntrada=tipoEntrada;
	 
	 this.descricao=descricao;
	 
	 valorEntrada++;
	 this.idEntrada=valorEntrada;
	 

	}
	
	
	public int getIdEntrada() {
		return idEntrada;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public TipoEntrada getTipoEntrada() {
		return tipoEntrada;
	}

	public String getDescricao() {
		return descricao;
	} 
	
	public LocalDate getData() {
		return data;
	}
	
	public LocalTime getHorario() {
		return horario;
	}

	public String dataFormatada() {
		DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return getData().format(formatado);
		
	}
	
	public String horaFormatada() {
		DateTimeFormatter formatado = DateTimeFormatter.ofPattern("HH:mm:ss");
		return getHorario().format(formatado);
	}
	@Override
	public String toString() {
		
	
		StringBuilder sb = new StringBuilder();
		if(getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
			sb.append("ID movimentação: "+getIdMovimentacao());
			sb.append("ID entrada: "+getIdEntrada());
			sb.append("Tipo movimentação: "+getTipoEntrada());
			sb.append("\n ID produto: "+produto.getId());
			sb.append("\n Nome Produto: "+produto.getNomeProduto());
			sb.append("Tipo entrada: "+getTipoEntrada().getDescricao());
			sb.append("Data: "+getData());
			sb.append("Horario: "+getHorario());
			
			return sb.toString();
			
		}
			
		if (getTipoMovimentacao()== TipoMovimentacao.SAIDA) {
			sb.append("ID movimentação: "+getIdMovimentacao());
			sb.append("ID: "+getIdSaida());
			sb.append("Tipo movimentação: "+getTipoSaida());
			sb.append("\n ID produto: "+produto.getId());
			sb.append("\n Nome Produto: "+produto.getNomeProduto());
			sb.append("Tipo entrada: "+getTipoSaida().getDescricao());
			sb.append("Data: "+getData());
			sb.append("Horario: "+getHorario());
			
			return sb.toString();
		}	
		
		return "";
	}
	
	
}
