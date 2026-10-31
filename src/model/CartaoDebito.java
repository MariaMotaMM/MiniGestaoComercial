package model;

public class CartaoDebito extends Pagamento {

	@Override
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		// TODO Auto-generated method stub
		super.setQuantidadeParcelas(1);
	}
	
	@Override
	public String getTipoPagamento() {
		// TODO Auto-generated method stub
		return "Cartão de Débito";
	}
}
