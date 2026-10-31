package model;

import exception.CEPInvalidoException;

public class Endereco {
	
	private String CEP;
	private String ruaAvenida;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	
	
	public String getCEP() {
		return CEP;
	}
	
	public Endereco setCEP(String cEP) {
		if(cEP==null) {
			throw new NullPointerException("Valor de CEP não pode ser nulo");
		}
		
		if(cEP.isBlank()) {
			throw new IllegalArgumentException("O campo deve ser preenchido!");
		}
		
		for (char numerosCep: cEP.toCharArray()) {
			if (!Character.isDigit(numerosCep)) {
				throw new CEPInvalidoException("Digite apenas numeros");
			}
			
		}
		
		if(cEP.length()!=8) {
			throw new CEPInvalidoException("Dados do cpf invalido. Digite apenas os numeros do cpf ");
		}
		
		CEP = cEP;
		return this;
	}
	
	public String getRuaAvenida() {
		return ruaAvenida;
	}
	
	
	public Endereco setRuaAvenida(String ruaAvenida) {
	
		if (ruaAvenida==null) {
			throw new NullPointerException("O valor da ruaAvenida não pode ser nulo");
		}
		
		if (ruaAvenida.isBlank()) {
			throw new IllegalArgumentException("Digite o nome da rua");
		}
		
		this.ruaAvenida = ruaAvenida;
		return this;
	
	}
	
	
	public String getBairro() {
		return bairro;
	}
	
	public Endereco setBairro(String bairro) {
		if (bairro==null) {
			throw new NullPointerException("O valor da bairro não pode ser nulo");
		}
		
		if (bairro.isBlank()) {
			throw new IllegalArgumentException("Digite o nome do bairro");
		}
		
		this.bairro = bairro;
		return this;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public Endereco setCidade(String cidade) {
		
		if (cidade==null) {
			throw new NullPointerException("O valor da cidade não pode ser nulo");
		}
		
		if (cidade.isBlank()) {
			throw new IllegalArgumentException("Digite o nome da cidade");
		}
		
		this.cidade = cidade;
		return this;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Endereco setEstado(String estado) {
		if (estado==null) {
			throw new NullPointerException("O valor do estado não pode ser nulo");
		}
		
		if (estado.isBlank()) {
			throw new IllegalArgumentException("Digite o nome do estado");
		}
		
		this.estado = estado;
		return this;
	}
	
	public String getPais() {
		return pais;
	}
	
	public Endereco setPais(String pais) {
		if (pais==null) {
			throw new NullPointerException("O valor da ruaAvenida não pode ser nulo");
		}
		
		if (pais.isBlank()) {
			throw new IllegalArgumentException("Digite o nome da rua");
		}
		
		this.pais = pais;
		return this;
	}
	
	public String toString() {
		return "CEP: "+getCEP()+
				"\n Rua/Avenida: "+getRuaAvenida()+
				"\nBairro: "+getBairro()+
				"\nCidade :"+getCidade()+
				"\nEstado: "+getEstado()+
				"\nPais: "+getPais();
	}
	
	
}
