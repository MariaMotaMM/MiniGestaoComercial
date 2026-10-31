package model;

public class CartaoCredito extends Pagamento {
	
	@Override
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		
		if (quantidadeParcelas<=0 && quantidadeParcelas>12) {
			throw new IllegalArgumentException("A quantidades de  parcelas deve está entre 1 e 12");
		}
		
		super.setQuantidadeParcelas(quantidadeParcelas);
	}
	
	@Override
	public String getTipoPagamento() {
		// TODO Auto-generated method stub
		return "Cartão de Credito";
	}
}
