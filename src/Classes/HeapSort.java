package Classes;

  /**
   * Classe para objetos do tipo HeapSort, onde serão contidos valores e métodos para o mesmo.
   * @author Débora Duarte e Fabrício Guidine
   * @version 1.0
   * @see Autor
  */
public class HeapSort 
{ 
    /**
      * Constrói a Heap. 
      * Troca o item na posição 1 do Array com o item da última posição .
      * Use o procedimento Heapify para reconstruir a Heap para os itens A[1], A[2],..., A[n − 1], sendo n = tamanho do vetor.
      * @param vet Array de objetos do tipo Registro a ser ordenado.
      */
    public void sort(Autor vet[]) 
    { 
        int n = vet.length; 
  
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(vet, n, i); 
  
        for (int i=n-1; i>0; i--) 
        { 
            Autor aux = vet[0]; 
            vet[0] = vet[i]; 
            vet[i] = aux; 
  
            heapify(vet, i, 0); 
        } 
    } 
  
    /**
     * Verifica se a propriedade de heap máximo não é violada em um determinado nó da árvore. 
     * @param vet Array de objetos do tipo Autor a ser ordenado 
     * @param n Inteiro que representa o tamanho do vetor
     * @param i Inteiro que representa a posição do nó pai
     * @see Autor
     */
    void heapify(Autor vet[], int n, int i) 
    { 
        int maior = i; 
        int e = 2*i + 1;  
        int d = 2*i + 2; 
        
        try{

        if (e < n && vet[e].getFrequencia() > vet[maior].getFrequencia()) 
            maior = e; 
  
        if (d < n && vet[d].getFrequencia() > vet[maior].getFrequencia()) 
            maior = d; 
  
        if (maior != i) 
        { 
            Autor aux = vet[i]; 
            vet[i] = vet[maior]; 
            vet[maior] = aux; 
  
            heapify(vet, n, maior); 
        } 
        } catch (NullPointerException ex){}
    }
    
    
}
