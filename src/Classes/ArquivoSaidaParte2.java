package Classes;

import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.FormatterClosedException;
import java.io.FileNotFoundException;

/**
 * Classe para objetos do tipo ArquivoSaida, onde serão contidos valores e métodos para o mesmo.
 * @author DéboraDuarte
 * @version 1.0
*/
public class ArquivoSaidaParte2 {
    String[] saidas;
    int i;
    int tam;
    private Formatter arquivo;
    
    /**
     * Cria um arquivo de nome saídapt2.txt.
    */
    private void abrir() {
        try {
            arquivo = new Formatter("saidapt2.txt");
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
    public ArquivoSaidaParte2(int tam){
        this.tam = tam;
        this.i = 0;
        this.saidas = new String[tam];
        
    }
    
    /**
     * Adiciona uma nova saída para posterior escrita no arquivo.
     * @param nSaida do tipo String sendo a sáida a ser adicionada.
    */
    public void addSaida(String nSaida){
        saidas[i] = nSaida;
        i++;
    }
    
    /**
     * Função que chama a função abrir da mesma classe, formata o arquivo para saída e faz a escrita. 
    */
    public void gravaSaidas(){
        abrir();
        try {
            System.out.println("GRAVA SAIDA");
            arquivo.format("Autor | Frequência\n");
            for (int j=0; j<tam; j++){
            arquivo.format("%s\n" ,saidas[j]);
            }
            arquivo.close();
        }
        catch(FormatterClosedException formatoDesconhecido) {
            System.err.println("Erro ao escrever");
        }
        catch(NoSuchElementException excecaoElemento){
            System.err.println("Entrada invalida.");
        }
    }
    
    public void fechar(){
        arquivo.close();
    }
    
}
