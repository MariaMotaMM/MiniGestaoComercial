package exception;

public class PagamentoReprovadoException extends RuntimeException{

	public PagamentoReprovadoException(String descricao) {
		super(descricao);
	}
}
