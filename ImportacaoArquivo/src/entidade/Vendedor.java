package entidade;

import java.math.BigDecimal;

/**
 * Classe que representa os vendedores.
 * 
 * @author Anderson Matte
 * 
 */
public class Vendedor {
	
	private String cpf;
	
	private String nome;
	
	private BigDecimal salario;

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}	
	
}
