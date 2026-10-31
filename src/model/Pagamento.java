package model;

public abstract class Pagamento {
	
	private int quantidadeParcelas;
	

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		
		if(quantidadeParcelas<=0) {
			throw new IllegalArgumentException("O valor das parcelas não pode ser nulo");
		}
		
		this.quantidadeParcelas = quantidadeParcelas;
	}

	
	public abstract String getTipoPagamento();
}
