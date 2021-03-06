package entidade;

/**
 * Classe responsável por auxiliar no controle de qual vendedor vendeu menos.
 * 
 * @author Anderson Matte
 * 
 */
public class EntidadeAuxiliarVendedor {
	
	private String nome;
	
	private Long quantidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
