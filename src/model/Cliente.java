package model;

import exception.CPFInvalidoException;
import exception.EmailInvalidoException;

public class Cliente {
	
	private String nomeCompleto;
	private String CPF;
	private Endereco endereco;
	private String telefoneContato; 
	private String numeroResidencia;
	private String complementoEndereco;
	private boolean NumWhatsApp;
	private String email;
	
	public Cliente setEndereco(Endereco endereco) {
		if(endereco==null) {
			throw new NullPointerException("O valor não pode ser nulo");
		}
		this.endereco=endereco;
		return this;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public Cliente setNomeCompleto (String nomeCompleto) {
	
		if(nomeCompleto== null) {
			throw new NullPointerException("O valor de nome completo é nulo");
		}
		
		if (nomeCompleto.isBlank()) {
			
			throw new IllegalArgumentException("Preencha o nome do cliente");
		}
		
		this.nomeCompleto= nomeCompleto;
		return this;
	}
	
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public String getCpf() {
		return CPF;
	}
	
	
	public Cliente setCPF(String cpf) {
		if(cpf== null) {
			throw new NullPointerException("O valor do CPF não pode ser nulo");
		}
		if(cpf.isBlank()) {
			throw new IllegalArgumentException("Preencha o cpf apenas com numeros!");
		}
		
		if(cpf.length()!=11) {
			throw new CPFInvalidoException("Digite apenas os numeros. Precisa de 11 números");
		}
		
		for(char numCpf: cpf.toCharArray()) {
			
			if (!Character.isDigit(numCpf)) {
				throw new IllegalArgumentException("Digite apenas o numero do cpf!");
			}
		}
		
		
		
		String numerosCom9Digitos=cpf.substring(0, 9);
		int somaDos9Digitos=0;
		int numeroMultiplicador =10;
		
		for (char num: numerosCom9Digitos.toCharArray() ) {
			Integer valorNumero= Integer.valueOf(num);
			somaDos9Digitos += valorNumero*numeroMultiplicador;
			numeroMultiplicador--;
		}
		
		int restoDoCalculoDo9Digitos = somaDos9Digitos%11;
		
		int decimoNumero = restoDoCalculoDo9Digitos>1? 11- restoDoCalculoDo9Digitos:0; 
		Integer decimoNumeroInt = Integer.valueOf(cpf.charAt(9));
		
		if (decimoNumeroInt!=decimoNumero) {
			throw new CPFInvalidoException("O CPF é invalido. Digite novamente");
			
		}
		
		String numerosCom10Digitos=cpf.substring(0, 10);
		int somaDos10Digitos=0;
		int numeroMultiplicadores10Digitos =11;
		
		for (char num: numerosCom10Digitos.toCharArray() ) {
			Integer valorNumero= Integer.valueOf(num);
			somaDos10Digitos += valorNumero*numeroMultiplicadores10Digitos;
			numeroMultiplicadores10Digitos--;
		}
		
		int restoDoCalculoDo10Digitos = somaDos10Digitos%11;
		
		int decimoPrimeiroNumero = restoDoCalculoDo10Digitos>1?11-restoDoCalculoDo10Digitos:0; 
		Integer decimoPrimeiroNumeroInt = Integer.valueOf(cpf.charAt(10));
		
		if (decimoPrimeiroNumero !=decimoPrimeiroNumeroInt) {
			throw new CPFInvalidoException("O CPF é invalido. Digite novamente");
		}
		
		this.CPF=cpf;
		
		return this;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public Cliente setNumeroResidencia(String numeroResidencia) {
		if(numeroResidencia == null) {
			throw new NullPointerException("Numero da Residencia nula");
		}
		if (numeroResidencia.isBlank()) {
			throw new IllegalArgumentException("Preencha o numero da residencia");
		}
		
		this.numeroResidencia = numeroResidencia;
		return this;
	}

	

	public Cliente setComplementoEndereco(String complementoEndereco) {
		if(complementoEndereco == null) {
			throw new NullPointerException("Numero da Residencia nula");
		}
		if (complementoEndereco.isBlank()) {
			throw new IllegalArgumentException("Preencha o numero da residencia");
		}
		
		this.complementoEndereco = complementoEndereco;
		
		return this;
	}
	
	public Cliente setTelefoneContato (String telefoneContato) {
		if(telefoneContato==null) {
			throw new NullPointerException("O valor de telefone não pode ser nulo");
		}
		
		if (telefoneContato.isBlank()) {
			throw new IllegalArgumentException("Preencha o telefone de contato");
		}
		
		for(char numTelefone: telefoneContato.toCharArray()) {
			
			if (!Character.isDigit(numTelefone)) {
				throw new IllegalArgumentException("Digite apenas o numero do cpf!");
			}
		}
		
		
		this.telefoneContato = telefoneContato;
		return this;
	}

	public boolean getNumWhatsApp() {
		return NumWhatsApp;
	}

	public Cliente setNumWhatsApp(boolean isNumWhatsApp) {
		
		this.NumWhatsApp = isNumWhatsApp;
		
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Cliente setEmail(String email) {
		
		if (email==null) {
			throw new NullPointerException("O email não pode ser nulo");
		}
		boolean tipoEmail= email.contains("@yahoo") || email.contains("@hotmail") || email.contains("@outlook") || email.contains("@gmail");
		boolean contemCom = email.contains(".com");
	
		if (email.isBlank()) {
			throw new IllegalArgumentException("Preencha o email! ");
		}
		
		if (tipoEmail && contemCom) {
			throw new EmailInvalidoException("O email é invalido");
		}
		
		this.email = email;
		return this;
	}
	
	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public String getCPF() {
		return CPF;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: "+getNomeCompleto());
		sb.append("\nCPF:" +getCpf());
		sb.append("\nTelefone: "+getTelefoneContato());
		sb.append("\nEmail :"+getEmail());
		sb.append("\nNumero pe WhatsApp: "+getNumWhatsApp());
		sb.append("\nEndereco"+getEndereco());
		sb.append("\nNumero Residencia: "+getNumeroResidencia());
		sb.append("\n Complemento Residencial: "+getComplementoEndereco());
		
		return sb.toString();
	}
	
	
}
