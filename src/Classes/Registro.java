package Classes;

import java.util.ArrayList;

/**
* Classe para objetos do tipo Registro, onde serão contidos valores e métodos para o mesmo.
* @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
* @version 1.0
 */
public class Registro {
    
    private ArrayList<String> authors = new ArrayList<>();
    private int bestsellers_rank;
    private ArrayList<String> categories = new ArrayList<>();
    private String edition;
    private String id;
    private String isbn10;
    private String isbn13;
    private float rating_avg;
    private int rating_count;
    private String title;
    
     /**
     * Construtor vazio da classe Registro.
     */
    public Registro(){};
    
    /**
    * Recebe uma String como parâmetro , identifica os campos nessa String e os separa, campo por campo.
    * @param str String que representa uma linha inteira lida do arquivo dataset. 
    * @param campos Array de Strings com 10 posições, cada uma referente a um atributo da classe Registro.
    */
    void PrimeiraSeparacao(String str, String[] campos){
        
        int n = str.length();
        String info = "";
        
        int anda = 0;
        int percorre_Campos = 0;
        try{
        while (anda < n-1 && percorre_Campos < 10){
            if (str.charAt(anda)== '"'){
                anda++;
            while (anda < n && str.charAt(anda) != '"'){
                info = info + str.charAt(anda);
                anda++;
            }
            campos[percorre_Campos] = info;
            info = "";
            percorre_Campos++;

            }
            anda++;
        }
        } catch (StringIndexOutOfBoundsException ex){
            System.out.println(str);
            System.out.println(str.length());
            System.out.println(anda);
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Lida com campos que precisam de uma segunda Separação.
     * @param nInfo String a ser separada.
     * @param listInfo ArrayList de String que representa um atributo da classe Registro.
     */
    void stringToList(String nInfo, ArrayList<String> listInfo){
        
        int n = nInfo.length();
        char[] c = new char[n+1];
        String info = "";

        for (int i=1; i<n;i++){
        while(nInfo.charAt(i)!=',' && nInfo.charAt(i)!=']' && nInfo.charAt(i)!=' '){
        info = info + nInfo.charAt(i);
        i++;
        }
        listInfo.add(info);
        info = "";
        }
    
 
    }
        
    
    /**
    * Construtor da classe Registro.
    * @param ninfo String que representa uma linha inteira lida do arquivo dataset.
    */
    public Registro(String ninfo){
        if (ninfo != null){
        String[] campos = new String[10];
        
        PrimeiraSeparacao(ninfo,campos);
        
        setAuthors(campos[0]);
        setBestsellers_rank(campos[1]);
        setCategories(campos[2]);
        setEdition(campos[3]);
        setId(campos[4]);
        setIsbn10(campos[5]);
        setIsbn13(campos[6]);
        setRating_avg(campos[7]);
        setRating_count(campos[8]);
        setTitle(campos[9]);
        } else {
            System.out.println("Info do registro é nula");
        }
    }

    /**
    * Função que retorna o campo autor.
    * @return authors do tipo ArrayList de String.
    */
    public ArrayList<String> getAuthors() {
        return authors;
    }

    /**
    * Função que retorna o campo Bestsellers.
    * @return bestsellers_rank do tipo Inteiro.
    */
    public int getBestsellers_rank() {
        return bestsellers_rank;
    }

    /**
     * Função que retorna o campo categorias.
     * @return categories tipo ArrayList de String.
    */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
   * Função que retorna o campo editora.
   * @return edition do tipo String.
   */
    public String getEdition() {
        return edition;
    }

    /**
   * Função que retorna o campo id.
   * @return id do tipo String.
   */
    public String getId() {
        return id;
    }

    /**
    * Função que retorna o campo ISBN-10.
    * @return isbn10 do tipo String.
   */
    public String getIsbn10() {
        return isbn10;
    }

    /**
    * Função que retorna o campo ISBN-13.
    * @return isbn13 do tipo String.
    */
    public String getIsbn13() {
        return isbn13;
    }

    /**
    * Função que retorna o campo avaliação média de 0 a 5.
    * @return rating_avg do tipo float.
    */
    public float getRating_avg() {
        return rating_avg;
    }

    /**
    * Função que retorna o campo Número de avaliações.
    * @return rating_count do tipo int.
    */
    public int getRating_count() {
        return rating_count;
    }

    /**
    * Função que retorna o campo título.
    * @return title do tipo String.
    */
    public String getTitle() {
        return title;
    }

   /**
    * Atribui ao campo author o valor recebido como parâmetro.
    * Atribui aos campos autor que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
    * @param newAuthor do tipo String que recebe o campo autor.
    */
    private void setAuthors(String newAuthor) {
        if (newAuthor==null){
           this.authors.add("0");
        } else 
        stringToList(newAuthor,this.authors);
    }

    /**
     * Atribui ao campo bestsellers_rank o valor recebido como parâmetro.
     * Atribui aos campos bestsellers_rank que estiverem vazios o valor de 0, apenas para tratar possíveis exceções.
     * @param bestsellers_rank  do tipo String que recebe o campo bestsellers.
     */
    private void setBestsellers_rank(String bestsellers_rank) {
        if (bestsellers_rank==null|| bestsellers_rank.length()<1){
            this.bestsellers_rank = 0;
        }
        else{
            String info = "";
            for(int b=0; b < bestsellers_rank.length() ; b++){
                if (bestsellers_rank.charAt(b)!= ',' && bestsellers_rank.charAt(b)!= ' '){
                  info = info+bestsellers_rank.charAt(b);
                }
            } 
        this.bestsellers_rank = Integer.parseInt(info);
        }
    }

    /**
     * Atribui ao campo categories o valor recebido como parâmetro.
     * Atribui aos campos Categoria que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
     * @param categories do tipo String que recebe o campo categoria.
    */
    private void setCategories(String newCategorie) {
        if (newCategorie==null || newCategorie.length() <1){
           this.categories.add("-1");
        } else
        stringToList(newCategorie,this.categories);
    }

     /**
      * Atribui ao campo edition o valor recebido como parâmetro.
      * Atribui aos campos Editora que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
      * @param edition do tipo String que recebe o campo editora.
     */
    private void setEdition(String edition) {
        if (edition==null){
            this.edition = "-1";
        }
        this.edition = edition;
    }

    /**
     * Atribui ao campo id o valor recebido como parâmetro.
     * Atribui aos campos id que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
     * @param id do String que recebe o campo id.
    */
    private void setId(String id) {
        if (id==null || id.length()<1){
            this.id="-1";
        }
        this.id = id;
    }

    /**
     * Atribui ao campo isbn10 o valor recebido como parâmetro.
     * Função que atribiu aos campos idbn-10 que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
     * @param isbn10 do String que recebe o campo isbn-10.
    */
    private void setIsbn10(String isbn10) {
        if (isbn10==null || isbn10.length()<1){
            this.isbn10="-1";
        }
        this.isbn10 = isbn10;
    }

    /**
     * Atribui ao campo isbn13 o valor recebido como parâmetro.
     * Atribui aos campos isbn13 que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
     * @param isbn13 do tipo String que recebe o campo isbn13.
    */
    private void setIsbn13(String isbn13) {
        if (isbn13==null || isbn13.length()<1){
            this.isbn13="-1";
        }
        this.isbn13 = isbn13;
    }

    /**
     * Atribui ao campo rating_avg o valor recebido como parâmetro.
     * Atribui aos campos avaliação media que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
     * @param rating_avg do tipo String que recebe o campo avaliação média.
    */
    private void setRating_avg(String rating_avg) {
        if (rating_avg==null || rating_avg.length()<1){
            this.rating_avg = 0;
        }
        else{
        this.rating_avg = Float.parseFloat(rating_avg);
        }
    }

    /**
     * Atribui ao campo rating_count o valor recebido como parâmetro.
     * Atribui aos campos número de avaliações que estiverem vazios o valor de -1, apenas para tratar possíveis exceções.
     * @param rating_count do tipo String que recebe o campo número de avaliações.
    */
    private void setRating_count(String rating_count) {
        if (rating_count== null || rating_count.length()<1){
            this.rating_count = 0;
        }
        else{
        this.rating_count = Integer.valueOf(rating_count);
        }
    }

    /**
     * Atribui ao campo title o valor recebido como parâmetro.
     * Atribui aos campos titulo que estiverem vazios o valor de titulo, apenas para tratar possíveis exceções.
     * @param title do String que recebe o campo titulo.
    */
    private void setTitle(String title) {
        if (title==null){
            this.title = "-1";
        }else
        this.title = title;
    }
    
    
}

