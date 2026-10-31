package model;

import java.util.ArrayList;
import java.util.List;

import enums.TipoEntrada;
import enums.TipoMovimentacao;
import enums.TipoSaida;
import exception.EstoqueInsuficienteException;
import exception.MovimentacaoDeSaidaException;
import exception.MovimentacoesNaoRealizadaNoEstoque;
import exception.MovimentacaoEntradaException;

public class Estoque {
	private Produto produto;
	private int quantidadeAtual;
	private int quantidadeMinima;
	private List <Movimentacao> movimentacoes = new ArrayList<>();
	
	public Produto getProduto() {
		return produto;
	}
	
	public Estoque setProduto(Produto produto) {
		if(produto == null) {
			throw new NullPointerException("O produto não pode ser nulo!");
		}
		
		this.produto = produto;
		produto.setEstoque(this);
		return this;
	}
	
	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}
	
	public Estoque setQuantidadeAtual(int quantidadeAtual) {
		if(quantidadeAtual<=0) {
			throw new IllegalArgumentException("A quantidade de estoque não pode ser nula");
		}
		this.quantidadeAtual = quantidadeAtual;
		return this;
	}
	
	
	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}
	
	public Estoque setQuantidadeMinima(int quantidadeMinima) {
		if(quantidadeMinima<=0) {
			throw new IllegalArgumentException("O valor nao pode ser menor ou igual a zero");
		}
		this.quantidadeMinima = quantidadeMinima;
		return this;
	}
	
	public void reduzirEstoque (TipoSaida tipoSaida, int quantidade, String descricao) {
		if(quantidade<=0) {
			throw new IllegalArgumentException("O valor da quantidade não pode ser zero ou menor que zero");
		}
		
		if(quantidade>getQuantidadeAtual()) {
			throw new EstoqueInsuficienteException("Estoque insuficiente");
		}
		
		setQuantidadeAtual(getQuantidadeAtual()-quantidade);
		alertaEstoqueMinimoAtingido();
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setProduto(produto);
		
		if(tipoSaida==TipoSaida.VENDAS) {
			throw new IllegalArgumentException("O tipo saida não pode ser venda");
		}
		movimentacao.movimentacaoSaida(tipoSaida, descricao, quantidade);
		movimentacoes.add(movimentacao);
		
		
	}
	
	public void aumentarEstoque (TipoEntrada tipoEntrada, String descricao,int quantidade) {
		if(quantidade<=0) {
			throw new IllegalArgumentException("O valor da quantidade não pode ser zero ou menor que zero");
		}
		
		setQuantidadeAtual(getQuantidadeAtual()+quantidade);
		
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setProduto(produto);
		movimentacao.movimentacaoEntrada(tipoEntrada, descricao, quantidade);
		movimentacoes.add(movimentacao);
		
	}
	
	public String alertaEstoqueMinimoAtingido() {
		
		if (getQuantidadeMinima() >= getQuantidadeAtual()) {
			return "O produto chegou ao estoque mínimo!";
			
		}
		return "";
	}
	
	public String relatorioMovimentacao() {
		if (movimentacoes.size()==0) {
			 throw new MovimentacoesNaoRealizadaNoEstoque ("Estoque sem movimentações");
		}
		
		StringBuilder string = new StringBuilder();
		string.append("RELÁTORIO DE MOVIMENTACÃO\n");
		string.append("===============================\n");
		for(Movimentacao movimentacao: movimentacoes) {
		    string.append(movimentacao.toString()+"\n");
		    string.append("-----------------------------------------------\n");
		    
		    
		}
		string.append("===============================\n");
		
		return string.toString();
	}
	
	public String relatorioMovimentacaoSaida () {
		
		boolean semRegistroSaida = true;
		
		for(Movimentacao  movimentacao: movimentacoes ) {
			if(movimentacao.getTipoMovimentacao()==TipoMovimentacao.SAIDA) {
				semRegistroSaida= false;
			}
		}
		if (semRegistroSaida) {
			throw new MovimentacaoDeSaidaException("Nenhuma movimentação de saida!");
		}
		
		StringBuilder string = new StringBuilder();
		string.append("RELÁTORIO DE MOVIMENTACÃO DE SAÍDA\n");
		string.append("===============================\n");
		for(Movimentacao movimentacao: movimentacoes) {
			
			if(movimentacao.getTipoMovimentacao()==TipoMovimentacao.ENTRADA) {			
		    string.append(movimentacao.toString()+"\n");
		    string.append("-----------------------------------------------\n");
			}
		    
		}
		string.append("===============================\n");
		
		return string.toString();
	}
	
	public String relatorioMovimentacaoEntrada() {

		boolean semRegistroSaida = true;
		
		for(Movimentacao  movimentacao: movimentacoes ) {
			if(movimentacao.getTipoMovimentacao()==TipoMovimentacao.ENTRADA) {
				semRegistroSaida= false;
			}
		}
		if (semRegistroSaida) {
			throw new MovimentacaoEntradaException("Nenhuma movimentação de entrada!");
		}
		
		
		StringBuilder string = new StringBuilder();
		string.append("RELÁTORIO DE MOVIMENTACÃO DE ENTRADA\n");
		string.append("===============================\n");
		for(Movimentacao movimentacao: movimentacoes) {
			
			if(movimentacao.getTipoMovimentacao()==TipoMovimentacao.ENTRADA) {			
		    string.append(movimentacao.toString()+"\n");
		    string.append("-----------------------------------------------\n");
			}
		    
		}
		string.append("===============================\n");
		
		return string.toString();
	}
}
