package enums;

public enum TipoEntrada {
	
	DEVOLUCAO("Devolução"),
	ABASTECIMENTO ("Abastecimento");
	
	private String descricao;
	TipoEntrada(String descricao){
		this.descricao= descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
