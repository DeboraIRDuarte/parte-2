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
public class Autor {
    
    private String nome;
    private int codigo;
    private int frequencia;
    
    
    public Autor(String linha){
        SeparaCampos(linha);
         this.frequencia = 0;
    }
    
    void SeparaCampos(String str){
        int n = str.length();
        String info = "";
        int anda = 0;
        int cont =0;
        String[] campos = new String[2];
        
         try{
             
            while (anda < n-1 && cont < 2){ 
                if (str.charAt(anda)== '"'){
                    anda++;
                while (anda < n && str.charAt(anda) != '"'){
                    info = info + str.charAt(anda);
                    anda++;
                }
                campos[cont] = info;
                cont++;
                info = "";
                }
                anda++;
            }
            
             if (campos[0] == null) {this.codigo = 0;} else{
             this.codigo = Integer.parseInt(campos[0]);} 
             if (campos[1] == null) {this.nome ="Autor desconhecido";}else{
             this.nome = campos[1];}
             
         } catch (StringIndexOutOfBoundsException ex){
            System.out.println(str);
            System.out.println(str.length());
            System.out.println(anda);
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void setNome(String nome){
       this.nome = nome; 
    }
    
    public int getCodigo(){
        return this.codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the frequencia
     */
    public int getFrequencia() {
        return frequencia;
    }

    /**
     */
    public void addFrequencia() {
        this.frequencia = this.frequencia +1;
    }
    
    
}
