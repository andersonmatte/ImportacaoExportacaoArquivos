package entidade;

import java.math.BigDecimal;

/**
 * Classe que representa as vendas.
 * 
 * @author Anderson Matte
 * 
 */
public class Venda {

	private Long idVenda;
	
	private Long idItem;

	private Long qtdItem;
	
	private BigDecimal precoItem;
	
	private String nomeVendedor;
	
	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Long getIdItem() {
		return idItem;
	}
	
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}
	
	public Long getQtdItem() {
		return qtdItem;
	}
	
	public void setQtdItem(Long qtdItem) {
		this.qtdItem = qtdItem;
	}

	public BigDecimal getPrecoItem() {
		return precoItem;
	}

	public void setPrecoItem(BigDecimal precoItem) {
		this.precoItem = precoItem;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
}