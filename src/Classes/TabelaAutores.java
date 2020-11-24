package Classes;

/**
* Classe para objetos do tipo TabelaAutores, onde serão contidos valores e métodos para o mesmo.
* Tabela Hash onde são armazenados objetos do tipo Autor.
* @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
* @version 1.0
* @see Autor
 */
public class TabelaAutores{
    
    private final int m;
    private final Autor[] hash;
    
   /**
      * Construtor da classe.
      * @param m do tipo Inteiro que representa o tamanho da tabela.
      */
    public TabelaAutores(int m){
         this.m = m;
         this.hash = new Autor[m];
         
         for (int j=0; j<m; j++){
             hash[j] = null;
         }
    }
    
     /** 
     * @return Retorna o tamanho da tabela como um Inteiro.
     */
    public int getM(){
        return this.m;
    }
    
    /**
     * Retorna o vetor onde os objetos estão armazenados.
     * @return um Array de objetos do tipo Autor.
     * @see Autor
     */
    public Autor[] getHash(){
       return this.hash; 
    }
        
     /**
      * Função Hash que determina a posição ocupada por uma chave.
      * @param codigo do tipo Inteiro que é a chave a ser armazenada/encontrada.
      * @param i di tipo Inteiro, que é acrescido de um em um em caso de colisão até
      * que uma posição vazia seja encontrada.
      * @return um Inteiro que a posição onde a chave vai ser armazenada/encontrada.
      */
    private int funcao(int codigo,int i){
         int chave = codigo;
         long hk1 = chave%m;
         long hk2 = 1+chave%(m-1);
         return (int) ((hk1 + i*hk2)%m);
     }
    
    /**
      * Insere uma nova chave vai ser armazenada.
      * @param autor do tipo Autor que é o objeto a ser inserido.
      * @see Autor
      */
    public void insere (Autor autor){
         int i=0;
         int posicao = funcao(autor.getCodigo(),i);
         while (hash[posicao]!= null && i<m){
             i++;
             posicao = funcao(autor.getCodigo(),i);
         }
         
         hash[posicao] = autor;
    }
    
    /**
     * Busca uma chave armazenada na tabela.
     * @param codigo do tipo Inteiro que é a chave a ser buscada.
     * @return o Autor que é a chave encontrada. Caso a função não encontre o objeto buscado o retorno será null. 
     * @see Autor
     */
    public Autor busca(int codigo){
         int i=0;
         int posicao = funcao(codigo,i);
         while (i<m){
             if (hash[posicao].getCodigo() == codigo){
                 return hash[posicao];
             }
             else{
                i++;
                posicao = funcao(codigo,i);
             }
         }
         return null;
     }
}
