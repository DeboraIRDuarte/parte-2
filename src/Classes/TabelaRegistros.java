package Classes;

/**
* Classe para objetos do tipo TabelaRegistro, onde serão contidos valores e métodos para o mesmo.
* Tabela Hash onde são armazenados objetos do tipo Registro.
* @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
* @version 1.0
* @see Registro
 */
public class TabelaRegistros  {
     private final int m;   
     private final Registro[] hash;   
     
     /**
      * Construtor da classe.
      * @param m do tipo Inteiro que representa o tamanho da tabela.
      */
     public TabelaRegistros(int m){
         this.m = m;
         this.hash = new Registro[m];
         
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
      * Função Hash que determina a posição ocupada por uma chave.
      * @param chave do tipo Registro que é o objeto a ser armazenado/encontrado.
      * @param i di tipo Inteiro, que é acrescido de um em um em caso de colisão até
      * que uma posição vazia seja encontrada.
      * @return um Inteiro que a posição onde a chave vai ser armazenada/encontrada.
      * @see Registro
      */
     private int funcao(Registro chave,int i){
         long vchave = Long.parseLong(chave.getId());
         int x = (int) (vchave/1000);
         long hk1 = x%m;
         long hk2 = 1+x%(m-1);
         return (int) ((hk1 + i*hk2)%m);
     }
     
     /**
      * Insere uma nova chave vai ser armazenada.
      * @param chave do tipo registro que é a chave a ser inserida.
      * @see Registro
      */
     public void insere (Registro chave){
         int i=0;
         int posicao = funcao(chave,i);
         while (hash[posicao]!= null && i<m){
             i++;
             posicao = funcao(chave,i);
         }
         
         hash[posicao] = chave;
         }
    
    /**
     * Busca uma chave armazenada na tabela.
     * @param chave do tipo Registro que é a chave a ser buscada.
     * @return o Registro que é a chave encontrada. Caso a função não encontre o objeto buscado o retorno será null. 
     */
     public Registro busca(Registro chave){
         int i=0;
         int posicao = funcao(chave,i);
         while (hash[posicao] != chave && i<m){
             i++;
             posicao = funcao(chave,i);
         }
         return hash[posicao];
     }

     
     
}
