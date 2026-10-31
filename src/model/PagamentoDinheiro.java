package model;

public class PagamentoDinheiro extends Pagamento{

	@Override
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		// TODO Auto-generated method stub
		super.setQuantidadeParcelas(1);
	}
	
	@Override
	public String getTipoPagamento() {
		// TODO Auto-generated method stub
		return "Dinheiro";
	}
}
