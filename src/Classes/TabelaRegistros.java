/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author izabe
 */
public class TabelaRegistros  {
     private int m;   
     private final Registro[] hash;   
        
     public TabelaRegistros(int m){
         this.m = m;
         this.hash = new Registro[m];
         
         for (int j=0; j<m; j++){
             hash[j] = null;
         }
     }
     
     public int getM(){
        return this.m;
    }
     
     private int funcao(Registro chave,int i){
         long vchave = Long.parseLong(chave.getId());
         int x = (int) (vchave/1000);
         long hk1 = x%m;
         long hk2 = 1+x%(m-1);
         return (int) ((hk1 + i*hk2)%m);
     }
     
     public void insere (Registro chave){
         int i=0;
         int posicao = funcao(chave,i);
         while (hash[posicao]!= null && i<m){
             i++;
             posicao = funcao(chave,i);
         }
         
         hash[posicao] = chave;
         }
    
    
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
