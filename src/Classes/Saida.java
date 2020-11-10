package Classes;

 /**
  * Classe para objetos do tipo Saída, onde serão contidos valores e métodos para o mesmo.
  * @author Débora Duarte
  * @version 1.0
 */
public class Saida {
    
    private String algoritmo;
    private String nRegistros;
    private String nComparacoes;
    private String nTrocas;
    private String tempo;
    
    /**
     * Construtor vazio da classe Saida.
     */
    public Saida(){
        
    }
    
    /**
     * Construtor da classe Saida.
     * @param algoritmo do tipo String.
     * @param nRegistros do tipo String.
     * @param nComparacoes do tipo Inteiro.
     * @param nTrocas do tipo Inteiro.
     * @param tempo do tipo Long.
     */
    public Saida (String algoritmo, String nRegistros, int nComparacoes, int nTrocas, long tempo){
        
        this.algoritmo = algoritmo;
        this.nRegistros = nRegistros;
        this.nComparacoes = Integer.toString(nComparacoes);
        this.nTrocas = Integer.toString(nTrocas);
        this.tempo = Long.toString(tempo);        
        
    }

    /**
     * Método que retorna o nome do algoritmo de ordenação usado ( QuickSort ou HeapSort ).
     * @return algoritmo do tipo String.
    */
    public String getAlgoritmo() {
        return algoritmo;
    }

    /**
     * Método que retorna o total de Registros.
     * @return nRegistros do tipo String.
    */
    public String getnRegistros() {
        return nRegistros;
    }

    /**
     * Método que retorna a quantidade de comparações realizadas.
     * @return nComparacoes do tipo String.
    */
    public String getnComparacoes() {
        return nComparacoes;
    }

    /**
     * Método que retorna a quantidade de trocas. 
     * @return nTrocas do tipo String.
    */
    public String getnTrocas() {
        return nTrocas;
    }

    /**
     * Método que retorna o tempo que demorou para fazer a ordenação.
     * @return tempo do tipo String.
    */
    public String getTempo() {
        return tempo;
    }
}
