package Classes;

/**
* Classe para objetos do tipo Autor, onde serão contidos valores e métodos para o mesmo.
* @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
* @version 1.0
 */
public class Autor {
    
    private String nome;
    private int codigo;
    private int frequencia;
    
    /**
    * Construtor da classe Autor.
    * @param linha String que representa uma linha inteira lida do arquivo dataset.
    */
    public Autor(String linha){
        SeparaCampos(linha);
         this.frequencia = 0;
    }
    
    /**
     * Recebe uma String como parâmetro , identifica os campos nessa String e os separa, campo por campo.
     * @param str String que representa uma linha inteira lida do arquivo dataset.  
     */
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
    
    /**
     * Altera o atributo nome da classe.
     * @param nome String que é o nome a ser inserido.
     */
    public void setNome(String nome){
       this.nome = nome; 
    }
    
    /**
     * @return Inteiro contendo código do Autor
     */
    public int getCodigo(){
        return this.codigo;
    }

    /**
     * @return String contendo o nome do Autor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return Inteiro que é a quantidade de livros a venda escritos pelo Autor.
     */
    public int getFrequencia() {
        return frequencia;
    }

    /**
     * Acrescenta em mais um a quantidade de livros a venda escritos pelo Autor.
     */
    public void addFrequencia() {
        this.frequencia = this.frequencia +1;
    }
    
    
}
