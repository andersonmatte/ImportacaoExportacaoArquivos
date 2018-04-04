package entidade;

/**
 * Classe que representa os clientes.
 * 
 * @author Anderson Matte
 * 
 */
public class Cliente {
	
	private String cnpj;
	
	private String nome;
	
	private String ramoAtividade;
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}
	
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	
}
