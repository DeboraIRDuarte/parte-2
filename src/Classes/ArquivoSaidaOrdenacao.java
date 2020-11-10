package Classes;

import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.FormatterClosedException;
import java.lang.SecurityException;
import java.io.FileNotFoundException;

/**
 *Classe para objetos do tipo ArquivoSaida, onde serão contidos valores e métodos para o mesmo.
 * @author DéboraDuarte
 * @version 1.0
*/
public class ArquivoSaidaOrdenacao {
    Saida[] saidas;
    int i;
    int tam;
    private Formatter arquivo;
    
    /**
     * Cria um arquivo de nome saída.txt.
    */
    private void abrir() {
        try {
            arquivo = new Formatter("saida.txt");
        }
        catch( SecurityException semPermissao) {
            System.err.println(" Sem permissao para escrever no arquivo ");
            System.exit(1); //exit(0) é sucesso, outro número significa que terminou com problemas
        }
        catch( FileNotFoundException arquivoInexistente ) {
            System.err.println(" Arquivo inexistente ou arquivo não pode ser criado");
            System.exit(1);
        }
    } 
    
    /**
     * Construtor para a classe ArquivoSaida.
     * @param tam do tipo Inteiro que representa o total saídas.
    */
    public ArquivoSaidaOrdenacao(int tam){
        this.tam = tam;
        int i=0;
        saidas = new Saida[tam];
        
    }
    
    /**
     * Adiciona uma nova saída para posterior escrita no arquivo.
     * @param nSaida do tipo Saida sendo a sáida a ser adicionada.
     * @see Saida
    */
    public void addSaida(Saida nSaida){
        saidas[i] = nSaida;
        i++;
    }
    
    /**
     * Função que chama a função abrir da mesma classe, formata o arquivo para saída e faz a escrita. 
    */
    public void gravaSaidas(){
        abrir();
        try {
            System.out.println("grava saida");
            arquivo.format("Indice,Algoritmo,Número de Registros,Comparacoes,Trocas,Tempo\n");
            for (int j=0; j<tam; j++){
            arquivo.format("%d, %s, %s, %s, %s,%s\n" ,j+1,saidas[j].getAlgoritmo(),saidas[j].getnRegistros(),saidas[j].getnComparacoes(),saidas[j].getnTrocas(),saidas[j].getTempo());
            }
            arquivo.close();
        }
        catch(FormatterClosedException formatoDesconhecido) {
            System.err.println("Erro ao escrever");
            return;
        }
        catch(NoSuchElementException excecaoElemento){
            System.err.println("Entrada invalida. Por exemplo, era pra ser uma string, mas foi um inteiro");
        }
    }
    
    public void fechar(){
        arquivo.close();
    }
    
}
