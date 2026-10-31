package service;

import enums.StatusEnvio;
import enums.StatusPagamento;
import enums.TipoSaida;
import exception.PagamentoAprovadoException;
import exception.PagamentoCanceladoReprovadoException;
import exception.PagamentoReprovadoException;
import model.Carrinho;
import model.Pagamento;
import exception.PedidoNaoPodeSerCanceladoException;
import exception.NaoPodeMudarEnvioException;
import model.ItemPedido;
import model.Cliente;

public class VendasService {
	
	private static int codigoVenda;
	private Carrinho carrinho;
	private Pagamento pagamento;
	private StatusEnvio statusEnvio;
	private StatusPagamento statusPagamento;
	private Cliente cliente;
	
	
	
	
	public void setCliente (Cliente cliente) {
		
		if (cliente ==null) {
			throw new NullPointerException("O valor do cliente é nulo");
		}
		
		this.cliente=cliente;
		
	}
	
	public Cliente getCliente() {
		return cliente;
	}


	public void adicionarCarrinho (Carrinho carrinho) {
		
		if(carrinho == null) {
			throw new NullPointerException("O valor do carrinho é nulo");
		}
		
		this.carrinho = carrinho;
		
	}
	
	public void iniciarVenda(Pagamento pagamento, int parcela) {
		
		if (pagamento==null) {
			throw new NullPointerException("O valor de vagamento nao pode ser nulo");
		}
		
		if (pagamento.getTipoPagamento().equals("Cartão de Credito")) {
			pagamento.setQuantidadeParcelas(parcela);
		}
		
		this.statusPagamento = StatusPagamento.PAGAMENTO_EM_ANALISE;
		this.statusEnvio=StatusEnvio.PENDENTE;
		
	}
	
	public void aprovarPagamento() {
		if (statusPagamento == StatusPagamento.PAGAMENTO_REPROVADO) {
			throw new PagamentoReprovadoException("Pagamento foi reprovado");
		}
		
		
		if (statusPagamento==StatusPagamento.PAGAMENTO_EM_ANALISE) {
			this.statusPagamento = StatusPagamento.PAGAMENTO_APROVADO;
		}
		
	}
	
	public void reprovarPagamento() {
		
		if (statusPagamento == StatusPagamento.PAGAMENTO_APROVADO) {
			throw new PagamentoAprovadoException("Pagamento ja foi aprovado");
		}
		
		if (statusPagamento==StatusPagamento.PAGAMENTO_EM_ANALISE) {
			this.statusPagamento = StatusPagamento.PAGAMENTO_REPROVADO;
			this.statusEnvio = StatusEnvio.CANCELADO;
		}
	}
	
	
	public void mudarEstadoEnvio() {
		if(statusEnvio ==StatusEnvio.CANCELADO) {
			throw new NaoPodeMudarEnvioException("O pedido ja foi cancelado");
		}
		
		else if(statusEnvio == StatusEnvio.PENDENTE) {
			this.statusEnvio=StatusEnvio.PROCESSANDO;
			
		}
		
		else if(statusEnvio == StatusEnvio.PROCESSANDO) {
			this.statusEnvio= StatusEnvio.ENVIADO;
			return;
		}
		
		else if(statusEnvio == StatusEnvio.ENVIADO) {
			this.statusEnvio= StatusEnvio.ENTREGUE;
			
		}

	}
	
	public void CancelarPedido() {
		if (statusEnvio == StatusEnvio.ENTREGUE) {
			throw new PedidoNaoPodeSerCanceladoException("O pagamento já foi entregue e não pode ser cancelado");
		}
		
		this.statusEnvio = StatusEnvio.CANCELADO;
		
	}
	
	
	public void finalizarPedido() {
		
		if(statusPagamento ==StatusPagamento.PAGAMENTO_REPROVADO || statusEnvio==StatusEnvio.CANCELADO) {
			throw new PagamentoCanceladoReprovadoException("Pedido cncelado");
		}
		
		for(ItemPedido itens: carrinho.getItensPedido()) {
			itens.getProduto().getEstoque().reduzirEstoque(TipoSaida.VENDAS,itens.getQuantidade(), TipoSaida.VENDAS.getDescricao());
		}
	}
	

}
