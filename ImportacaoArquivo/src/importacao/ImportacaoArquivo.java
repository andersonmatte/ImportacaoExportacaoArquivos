package importacao;

import tratamento.TratamentoArquivos;

/**
 * Classe que inicializa o programa.
 * 
 * @author Anderson Matte
 *
 */
public class ImportacaoArquivo {

	public static void main(String[] args) {
		//Cria uma nova instância da TratamentoArquivos
		TratamentoArquivos novoTratamento = new TratamentoArquivos();
		novoTratamento.capturaArquivos();
	}
	
}
