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
public class TabelaAutores{
    
    private int m;
    private final Autor[] hash;
    
    public TabelaAutores(int m){
         this.m = m;
         this.hash = new Autor[m];
         
         for (int j=0; j<m; j++){
             hash[j] = null;
         }
    }
    
    public int getM(){
        return this.m;
    }
    
    public Autor[] getHash(){
       return this.hash; 
    }
    
    private int funcao(int codigo,int i){
         int chave = codigo;
         long hk1 = chave%m;
         long hk2 = 1+chave%(m-1);
         return (int) ((hk1 + i*hk2)%m);
     }
    
    public void insere (Autor autor){
         int i=0;
         int posicao = funcao(autor.getCodigo(),i);
         while (hash[posicao]!= null && i<m){
             i++;
             posicao = funcao(autor.getCodigo(),i);
         }
         
         hash[posicao] = autor;
    }
    
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
