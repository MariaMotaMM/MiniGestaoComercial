package enums;

public enum TipoMovimentacao {
	
	ENTRADA("Entrada produto"),
	SAIDA("Saida produto");
	
	
	private String descricao;
	
	TipoMovimentacao(String descricao){
		
		this.descricao=descricao;

		}
	
	public String getDescricao() {
		return descricao;
	}
}
