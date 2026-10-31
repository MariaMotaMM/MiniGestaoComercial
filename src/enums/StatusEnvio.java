package enums;

public enum StatusEnvio {
	
	PENDENTE("Pendente"),
    PROCESSANDO("Em Processamento"),
    ENVIADO("Enviado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;

    
    StatusEnvio(String descricao) {
        this.descricao = descricao;
    }

    
    public String getDescricao() {
        return descricao;
    }
	
	
}
