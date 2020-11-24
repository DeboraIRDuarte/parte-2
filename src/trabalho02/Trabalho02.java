package trabalho02;

import Classes.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Classe principal do projeto Trabalho02, criado como parte das exigências da disciplina DCC012 - Estruturas de Dados II.
 * Essa classe tem como objetivo determinar os N autores mais citados em uma fração aleatória do book-depository-dataset.
 * @author Débora Duarte, Fabrício Guidine e Walkíria Garcia
 * @version 1.0
*/
public class Trabalho02 {
    
    private static final int NUM_DE_REGISTROS = 100000;
    
    public static boolean eprimo(int n){
        for (int j = 2; j < n; j++) {
        if (n % j == 0)
            return false;   
    }
    return true;
    }
    
    public static int retornaprimo(int num){
        
        while(eprimo(num) == false){
            num++;
        }
        return num;
    }
    
    public static boolean epot(double v){
       
        double resto;
        while (v != 1){
           resto = v%2;
           if (resto != 0){
               return false;
           }
           v = v/2;
        }
        return true;
    }
    
    public static int retornapot(int valor){
        while (epot(valor) == false){
            valor++;
        }        
        return valor;
    }
    /**
     * Lê e retorna o número de autores que deverão ser exibidos ao término da execução.
     * @return entrada do tipo Inteiro
     */
    public static int lerEntrada(){
        int entrada;
        String valorInformado = JOptionPane.showInputDialog("Informe valor de N:");
        if (valorInformado == null){
            System.exit(0);
        }
        entrada = Integer.parseInt(valorInformado);     
        return entrada;
    } 
    
    /**
     * Faz a leitura do arquivo authors.csv relacionando os nomes dos autores com seu código no 
     * arquivo book-depository-dataset.
     * @return um ArrayList do tipo Autor.
     * @see Autor
     */
    public static ArrayList<Autor> LeAutores(){
        try {
            ArrayList<Autor> autores;
            try (FileInputStream entrada = new FileInputStream("authors.csv")) {
                InputStreamReader entradaFormatada = new InputStreamReader(entrada);
                BufferedReader entradaString = new BufferedReader(entradaFormatada);
                autores = new ArrayList<>();
                String linha = entradaString.readLine();
                while(linha != null){
                    linha = entradaString.readLine();
                    if (linha !=null){
                        Autor at = new Autor(linha);
                        autores.add(at);
                    }
                    
                }
            }
        return autores;
            
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não foi encontrado!");
        } catch (IOException ex) {
            Logger.getLogger(Trabalho02.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    /**
     * Método responsavel por carregar de forma aleatória um número de registros de forma desordenada.
     * @param registros array de objetos do tipo Registro onde os dados vão ser armazenados.
     * @param tam do tipo Inteiro que representa o tamanho do vetor de Registros.
     * @see Registro
    */
    public static void LeRegistros (Registro registros[], int tam){
        
        try {
        
            try (RandomAccessFile raf = new RandomAccessFile("dataset_simp_sem_descricao.csv", "r")) {
                long posicao = ThreadLocalRandom.current().nextLong(raf.length());
                raf.seek(posicao);
                String line = "";
                String[] linhas = new String[tam+tam/2];
                raf.readLine();
                for (int i=0; i<tam+tam/2;i++){
                    if (raf.getFilePointer()==raf.length()){
                        raf.seek(0);
                    }
                    
                    linhas[i] = raf.readLine();
                }   int i=0,j=0;
                while (i<tam && j<tam+tam/2){
                    line = linhas[j];
                    int last = line.length()-1;
                    if (line.charAt(last)!='"'){
                        String line2 = linhas[j+1];
                        j++;
                        String info = line+line2;
                        if (info!=null){
                            Registro reg = new Registro(info);
                            registros[i] = reg;
                            i++;
                            j++;
                        }
                    }else{
                        Registro reg = new Registro(line);
                        registros[i] = reg;
                        i++;
                        j++;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    /**
      * Chama a função da classe Quicksort,que ordena o vetor. 
      * @param vet do tipo Autor que é o vetor a ser ordenado.
      * @param tam do tipo Inteiro que é o tamanho do vetor.
      * @see Autor
      */
    public static void fazOrdenacaoQuickSort(Autor[] vet, int tam){
        
        QuickSort qSort = new QuickSort();
        qSort.quickSorting(vet, 0, vet.length);
        
    }
    
    /**
     * Verifica se o código dos autores em um Registro é igual a 0.
     * O código 0 siginifica que o autor não foi informado ou é desconhecido.
     * @param r objeto do tipo Registro
     * @return b onjeto do tipo boolean. b é igual a true se código de um dos autores é igual a 0.
     * @see Registro
     */
    public static boolean verificaAutores(Registro r){
        boolean b = false;
        for (int i=0; i< r.getAuthors().size();i++){
            if (r.getAuthors().get(i).compareTo("0")==0){
                b = true;
                System.out.println(r.getAuthors().get(i));
            }
        }
        return b;
    }
    
    /**
     * Armazena os Registros lidos em uma tabela hash e já procura os autores de um registro
     * na tabela de autores e determina a quantidade de vezes em que um autor aparece nos registros.
     * @param hashR tabela hash que armazena objetos do tipo Registro.
     * @param hashA tabela hash que armazena objetos do tipo Autor.
     * @see TabelaRegistros
     * @see TabelaAutores
     */
    public static void hashRegistros(TabelaRegistros hashR,TabelaAutores hashA){
        
        
        Registro[] registros = new Registro[NUM_DE_REGISTROS];
        Esperando esp = new Esperando();
        esp.setVisible(true);
        LeRegistros (registros, registros.length);
        esp.dispose();
        System.out.println("Termina Leitura Registros Aqui");
        int i = 0;
        
        Progressa fp = new Progressa();
        fp.setProgressa(i, registros.length);
        fp.setVisible(true);
        
        while (i<registros.length){
            
            if (registros[i] != null && registros[i].getId() != null && registros[i].getId().compareTo("-1") != 0){                
                    if ( verificaAutores(registros[i]) != true){
                         hashR.insere(registros[i]);
                         for (int b=0; b< registros[i].getAuthors().size(); b++){
                             try{
                                 if (registros[i].getAuthors().get(b).length() != 0){
                                    hashA.busca(Integer.parseInt(registros[i].getAuthors().get(b))).addFrequencia();
                                 }
                             }
                             catch (NullPointerException ex){ }
                         }
                    }
            }            
            i++;
            fp.setTexto(i);
        }
        fp.dispose();
        
    }
    
    /**
     * Aramzena os autores lidos do arquivo authors.txt em uma tabela hash.
     * @param hashA tabela onde Autores vãos ser armazenados.
     * @param autores ArrayList de objetos do tipo Autor contendo os autores que serão armazenados na tabela.
     * @see Autor
     * @see TabelaAutores
     */
    public static void hashAutores(TabelaAutores hashA, ArrayList<Autor> autores){
       
        for (int i=0; i<autores.size();i++){
            hashA.insere(autores.get(i));
        }
    }  
    
    /**
     * Transfere os autores da tabela hash, já com as frequências determinadas, para um vetor que será ordenado
     * de acordo com a frequência de cada autor.
     * @param hashA tabela hash contendo os Autores a serem ordenados.
     * @param mv vetor de objetos do tipo Autor que vai ser ordenado.
     * @see TabelaAutores
     * @see Autor
     */
    public static void determinaMaisVendidos(TabelaAutores hashA,Autor [] mv){
        HeapSort hsort = new HeapSort();
        
        int cn = 0;

        for (int i=0; i<mv.length;i++){
            if (hashA.getHash()[i] != null){
            mv[i] = hashA.getHash()[i];
            }
            else {
                cn++;
            }
        }
        
        hsort.sort(mv);
        System.out.println( hashA.getHash().length + "\t" + cn);
        System.out.println("Termina Ordenação Aqui\n");   
    }
    
    /**
     * Cria os objetos e chama as funções necessárias para determinar os N autores mais citados.
     * No final da execução cria um arquivo contendo os N autores mais citados.
     */
    public static void AutoresMaisVendidos(){
        int n = lerEntrada();

        ArrayList<Autor> autores = LeAutores();
        System.out.println("Termina Leitura Autores Aqui");
        TabelaAutores hashAutores = new TabelaAutores(retornapot(autores.size())-1);
        hashAutores(hashAutores,autores);
        System.out.println("Termina Hash Autores Aqui");
             
        TabelaRegistros hashRegis = new TabelaRegistros(retornapot(NUM_DE_REGISTROS)-1);
        hashRegistros(hashRegis,hashAutores);
        System.out.println("Termina Hash Registros Aqui");
        
        System.out.println(hashAutores.getHash()[0].getNome());

        Autor [] maisvendidos = new Autor[hashAutores.getM()];
        determinaMaisVendidos(hashAutores,maisvendidos);
        

        ArquivoSaidaParte2 arqp2 = new ArquivoSaidaParte2(n);
        int i = maisvendidos.length-1; 
        int cont = 0;
        while (cont < n){
            if (maisvendidos[i] != null){
            System.out.println( i + "\t" + maisvendidos[i].getNome() + "  |  " + maisvendidos[i].getFrequencia());    
            arqp2.addSaida(maisvendidos[i].getNome() + "  |  " + maisvendidos[i].getFrequencia());
           
            cont++;
        }
            i--;

        }
        arqp2.gravaSaidas();   
    }
    
    /**
     * Função main da clase Trabalho02.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        
        AutoresMaisVendidos();        
        
        
    }
    
}
