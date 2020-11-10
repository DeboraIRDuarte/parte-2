package trabalho02;

import Classes.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Classe principal do projeto Trabalho01, criado como parte das exigências da disciplina DCC012 - Estruturas de Dados II.
 * Essa classe tem como objetivo fazer uma análise de desempenho entre os Algoritmos de Comparação QuickSort e Heapsort 
 * com uso de dados do Book Depository Dataset. 
 * @author Débora Duarte, Fabrício Guidine e Walkíria Garcia
 * @version 1.0
 * 
*/
public class Trabalho02 {
    
    private static final int NUM_DE_REGISTROS = 50000;
    
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
    /**
     * LerEntradas é responsavel por ler e retornar o conteúdo do arquivo 
     * 'entrada.txt', que define 5 tamanhos diferentes para o vetor ser executado.
     * @return entradas do tipo Array de Inteiros.
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
    
    public static ArrayList<Autor> LeAutores(){
        try {
            FileInputStream entrada = new FileInputStream("authors.csv");
            InputStreamReader entradaFormatada = new InputStreamReader(entrada);
            BufferedReader entradaString = new BufferedReader(entradaFormatada);
            
            ArrayList<Autor> autores = new ArrayList<>();
            String linha = entradaString.readLine();
            
            while(linha != null){
            linha = entradaString.readLine();
            if (linha !=null){
            Autor at = new Autor(linha);
            autores.add(at);
            }
            
            }
        entrada.close();
        return autores;
            
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não foi encontrado!");
        } catch (IOException ex) {
            Logger.getLogger(Trabalho02.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    /**
     * Método responsavel por carregar de forma aleatória um número tam de registros de forma desordenada.
     * @param registros array de objetos do tipo Registro onde os dados vão ser armazenados.
     * @param tam do tipo Inteiro que representa o tamanho do vetor de Registros.
     * @see Registro
    */
    public static void LeRegistros (Registro registros[], int tam){
        
        try {
        
            RandomAccessFile raf = new RandomAccessFile("dataset_simp_sem_descricao.csv", "r");
            
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
            }
            int i=0,j=0;
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
         raf.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    /**
      * Chama a função da classe Quicksort,que ordena o vetor. 
      * 
     * @param vet
     * @param tam
      */
    public static void fazOrdenacaoQuickSort(Autor[] vet, int tam){
        
        QuickSort qSort = new QuickSort();
        qSort.quickSorting(vet, 0, vet.length);
        
    }
    
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
    
    public static void hashAutores(TabelaAutores hashA, ArrayList<Autor> autores){
       
        for (int i=0; i<autores.size();i++){
            hashA.insere(autores.get(i));
        }
    }
    
    

    
    public static void determinaMaisVendidos(TabelaAutores hashA,Autor [] mv){
        HeapSort hsort = new HeapSort();

        for (int i=0; i<mv.length;i++){
            if (hashA.getHash()[i] != null){
            mv[i] = hashA.getHash()[i];
            }
        }
        
        hsort.sort(mv);
        System.out.println("Termina Ordenação Aqui\n");
        
        
    }
    
    public static void AutoresMaisVendidos(){
        int n = lerEntrada();
        
        
        ArrayList<Autor> autores = LeAutores();
        System.out.println("Termina Leitura Autores Aqui");
        TabelaAutores hashAutores = new TabelaAutores(retornaprimo(autores.size()));
        hashAutores(hashAutores,autores);
        System.out.println("Termina Hash Autores Aqui");
             
        TabelaRegistros hashRegis = new TabelaRegistros(retornaprimo(NUM_DE_REGISTROS));
        hashRegistros(hashRegis,hashAutores);
        System.out.println("Termina Hash Registros Aqui");
        
        
        Autor [] maisvendidos = new Autor[hashAutores.getM()];
        determinaMaisVendidos(hashAutores,maisvendidos);
        
        
        ArquivoSaidaParte2 arqp2 = new ArquivoSaidaParte2(n);
        for (int i = maisvendidos.length-1; i >= maisvendidos.length - (n+1); i--){
            if (maisvendidos[i] != null){
            arqp2.addSaida(maisvendidos[i].getNome() + "  |  " + maisvendidos[i].getFrequencia());
            }
        }
        arqp2.gravaSaidas();
        
        
    }
    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        
        AutoresMaisVendidos();        
        
        
    }
    
}
