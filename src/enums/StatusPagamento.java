package enums;

public enum StatusPagamento {
	
	PAGAMENTO_EM_ANALISE ("Pagamento em Analise"),
	PAGAMENTO_APROVADO("Pagamento Aprovado"),
	PAGAMENTO_REPROVADO ("Pagamento Reprovado");
	
	private String descricao;
	
	StatusPagamento(String descricao){
	
		this.descricao=descricao;
	
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
}
