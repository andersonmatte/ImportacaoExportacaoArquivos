package tratamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entidade.Cliente;
import entidade.EntidadeAuxiliarVendedor;
import entidade.Venda;
import entidade.Vendedor;

/**
 * Classe responsável pelo tratamento dos arquivo.
 * 
 * @author Anderson Matte
 * 
 */
public class TratamentoArquivos {
	
	private List<Vendedor> listaVendedor = new ArrayList<Vendedor>();
	private List<Cliente> listaCliente = new ArrayList<Cliente>();
	private List<Venda> listaVenda = new ArrayList<Venda>();
	
	/**
	 * Método que faz a captura dos arquivos no diretório e faz todo o trabalho de
	 * triagem nos arquivos existentes
	 * 
	 */
	public void capturaArquivos() {
		//Diretório  "/dados/in/"
		File folder = new File("/home/dados/in");
		//Lista Arquivos dentro do diretório
		File[] listOfFiles = folder.listFiles();
		
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		String line = null;
		
		String extensao = null;   
		
		if (listOfFiles != null) {
			
			//Laço que percorre os arquivos do diretório
			for (File file : listOfFiles) {
				
				extensao = file.getName().substring(file.getName().lastIndexOf('.') + 1);
				
				//Valida extenssão permitida
				if (extensao.equals("dat")) {
					
					//verifica se arquivo e não pasta
					if (file.isFile()) {
						try {
							fileReader = new FileReader(file);
							
							bufferedReader = new BufferedReader(fileReader);
							
							Long contador = 0l;
							
							//Laço que trabalha as linha do arquivo
							while((line = bufferedReader.readLine()) != null) {
								
								//Quebra a linha do arquivo 
								String[] linhaQuebrada = line.split(";");
								contador++;
								
								if (contador > 0) {
									if (linhaQuebrada[0] != null && !linhaQuebrada[0].isEmpty()) {
										//Controle de Vendedor 001, Cliente 002 e Venda 003
										if (linhaQuebrada[0].equals("001")) {
											Vendedor novoVendedor = new Vendedor();
											novoVendedor.setCpf(linhaQuebrada[1] != null ? linhaQuebrada[1] : null);
											novoVendedor.setNome(linhaQuebrada[2] != null ? linhaQuebrada[2] : null);
											if (linhaQuebrada[3] != null) {
												novoVendedor.setSalario(new BigDecimal(linhaQuebrada[3]));											
											}
											this.listaVendedor.add(novoVendedor);
										} else if (linhaQuebrada[0].equals("002")) {
											Cliente novoCliente = new Cliente();
											novoCliente.setCnpj(linhaQuebrada[1] != null ? linhaQuebrada[1] : null);
											novoCliente.setNome(linhaQuebrada[2] != null ? linhaQuebrada[2] : null);
											novoCliente.setRamoAtividade(linhaQuebrada[3] != null ? linhaQuebrada[3] : null);
											this.listaCliente.add(novoCliente);
										} else if (linhaQuebrada[0].equals("003")) {
											Venda novaVenda = new Venda();
											novaVenda.setIdVenda(linhaQuebrada[1] != null ? Long.parseLong(linhaQuebrada[1]) : null);
											novaVenda.setIdItem(linhaQuebrada[2] != null ? Long.parseLong(linhaQuebrada[2]) : null);
											novaVenda.setQtdItem(linhaQuebrada[3] != null ? Long.parseLong(linhaQuebrada[3]) : null);
											if (linhaQuebrada[4] != null) {
												novaVenda.setPrecoItem(new BigDecimal(linhaQuebrada[4]));											
											}
											novaVenda.setNomeVendedor(linhaQuebrada[5] != null ? linhaQuebrada[5] : null);
											this.listaVenda.add(novaVenda);
										}
									}
								}
								
							}
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}					
						
					}
				}					
				
			}
			
			// Chama o método validador das regras de negócio
			this.validaRegrasNegocio();
			
		}
		
	}
	
	/** Método que monta o arquivo com base nas regras de negócio */
	public void validaRegrasNegocio() {

		int qtdCliente = 0;
		int qtdVendedor = 0;
		Long idMaiorVenda = 0l;
		String nomeVendedor = "";
		
		//Quantidade de Clientes
		if (this.listaCliente != null && !this.listaCliente.isEmpty()) {
			qtdCliente = this.listaCliente.size();
		}
		//Quantidade de Vendedores
		if (this.listaVendedor != null && !this.listaVendedor.isEmpty()) {
			qtdVendedor = this.listaVendedor.size();
		}
		//ID da Venda de valor mais alto
		idMaiorVenda = this.validaValorMaisAlto();
		
		//Nome do Vendedor que menos vendeu
		nomeVendedor = this.validaMenorVenda();
		
		//Montagem do arquivo
		String arquivo = qtdCliente+";"+qtdVendedor+";"+idMaiorVenda+";"+idMaiorVenda+";"+nomeVendedor;
		
		//Chama o método gerador do arquivo
		this.geraArquivo(arquivo);

	}
	
	/** Busca na lista de vendas o ID da Venda de valor mais alto */
	public Long validaValorMaisAlto() {
		Long id = 0l;
		BigDecimal maiorValor = new BigDecimal(0);
		BigDecimal menorValor = new BigDecimal(0);
		
		if (this.listaVenda != null && !this.listaVenda.isEmpty()) {
			for (Venda venda : this.listaVenda) {
				if (venda != null && venda.getPrecoItem() != null) {
					if (venda.getPrecoItem().doubleValue() > maiorValor.doubleValue()) {
						maiorValor = venda.getPrecoItem();
						id = venda.getIdVenda() != null ? venda.getIdVenda() : 0l;
					}
					if (menorValor.doubleValue() < venda.getPrecoItem().doubleValue()) {
						menorValor = venda.getPrecoItem();						
					}
				}
			}
		}

		return id;
	}
	
	/** Busca o nome do vendedor que menos vendeu */
	public String validaMenorVenda() {
		String nomeVendedor = "";
		
		List<String> listaNomeVendedor = new ArrayList<String>();
		List<EntidadeAuxiliarVendedor> listaEntidadeAuxiliarVendedor = new ArrayList<EntidadeAuxiliarVendedor>();
		
		//Busca o nome dos vendedores na lista de vendas
		if (this.listaVenda != null && !this.listaVenda.isEmpty()) {
			for (Venda venda : this.listaVenda) {
				if (venda!= null && venda.getNomeVendedor() != null) {
					listaNomeVendedor.add(venda.getNomeVendedor());
				}
			}
		}
		
		//monta a frequencia de cada vendedor
		Set<String> distinct = new HashSet<>(listaNomeVendedor);
		for (String nome : distinct) {
			Collections.frequency(listaNomeVendedor, nome);
			EntidadeAuxiliarVendedor vendedor = new EntidadeAuxiliarVendedor(); 
			vendedor.setNome(nome);
			vendedor.setQuantidade((long) Collections.frequency(listaNomeVendedor, nome));
			listaEntidadeAuxiliarVendedor.add(vendedor);
		}

		//Percorre a lista da entidade auxiliar para descobrir o menor vendedor
		Long quantidadeAnteriror = null;
		for (EntidadeAuxiliarVendedor menorVendedor : listaEntidadeAuxiliarVendedor) {
			
			if (menorVendedor != null && menorVendedor.getQuantidade() != null) {				
				if (quantidadeAnteriror == null) {
					quantidadeAnteriror = menorVendedor.getQuantidade();
					nomeVendedor = menorVendedor.getNome();
				}				
				if (menorVendedor.getQuantidade() < quantidadeAnteriror) {
					nomeVendedor = menorVendedor.getNome();					
				}
			}
			
		}
	        
		return nomeVendedor;
	}
	
	/**
	 * Método responsável pela geração do arquivo
	 * 
	 * @param arquivo
	 * 
	 */
	public void geraArquivo(String arquivo) {
		FileWriter writeFile = null;
		try {
			writeFile = new FileWriter("/home/dados/out/arquivo.dat.proc");
			writeFile.write(arquivo);
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}