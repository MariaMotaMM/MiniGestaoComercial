package exception;

public class MovimentacoesNaoRealizadaNoEstoque extends RuntimeException {
		
	public MovimentacoesNaoRealizadaNoEstoque(String mensagem) {
		super(mensagem);
	}
}
