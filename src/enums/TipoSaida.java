package enums;

public enum TipoSaida {
	
	VENDAS("Venda"),
	AVARIAS("Avarias"),
	FURTO ("Furto"),
	OUTROS("Outros");
	
	private String descricao;
	
	TipoSaida(String descricao){
		
		this.descricao=descricao;

		}
	
	public String getDescricao() {
		return descricao;
	}

}
