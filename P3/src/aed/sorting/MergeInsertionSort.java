package aed.sorting;

import aed.utils.TemporalAnalysisUtils;

import java.util.Random;

public class MergeInsertionSort extends Sort {

    private static final int MAX_INTERVAL = 64;

    //creates a random generator with a specific seed
    //this is useful for testing methods that are supposed to generate random elements
    //because we can always repeat the same tests by using the same seed
    private static final Random pseudoRandom = new Random(4582);

    //sort an array of elements (using MergeSort Bottom Up)
    //this method uses extra memory of O(n) to perform the sort
    public static <T extends Comparable<T>> void traditionalBottomUpSort(T[] a)//merge de subarrays
    {
        int n = a.length;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
        for(int groupSize = 1; groupSize < n; groupSize *= 2)
        {
            for(int low = 0; low < n - groupSize; low += 2*groupSize)
            {
                merge(a, aux, low, low+groupSize-1, Math.min(low+2*groupSize-1,n-1));
            }
        }

    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int low, int mid, int high) {//compara o primeiro com o do meio +1
        int left = low;
        int right = mid + 1;
        for (int i = low; i <= high; i++) {
            aux[i] = a[i];
        }
        for (int i = low; i <= high; i++) {
            if (left > mid) {a[i] = aux[right++];}
            else if (right > high) {a[i] = aux[left++];}
            else if (less(aux[right], aux[left])) {a[i] = aux[right++];}
            else {a[i] = aux[left++];}
        }
    }

    //sort an array of elements (using InsertionSort) from low to high (including)
    public static <T extends Comparable<T>> void insertionSort(T[] a, int low, int high){//começa com a mão e compara ao numero a seguir no deck
        if(!isSorted(a)) {
            for (int i = low + 1; i <= high; i++) {//low +1 pq o mais baixo é 0
                for (int j = i; j > low; j--) {
                    if (less(a[j], a[j - 1])) {
                        exchange(a, j, j - 1);
                    } else break;
                }
            }
        }
    }


    public static int determineRunSize(int n){
        int k = n;
        int y = 0;
        while (k >= 64){
            k /= 2;             //dividir ate ficar entre 32 e 64
            if (k % 2 != 0) {  //se algum k nao for divisivel por 2 retorna k+1
                y = 1;          // se todos forem divisiveis retorna k
            }
        }
        return y == 1? k+1: k;
    }

    public static <T extends Comparable<T>> void sort(T[] a){
        int n = a.length;
        if(!isSorted(a)){
        if( n < 64) {//se tamanho for menor que 64 fazer insertion sort
            insertionSort(a, 0, n-1);
            return;
        }
        else{
            int Rsize = determineRunSize(n);
            for(int i = 0; i < n; i+=Rsize){
                if(i + Rsize < n) {                     //Se não for o ultimo subconjunto insertion sort até ao ultimo elemento desse subconjunto
                    insertionSort(a, i, i + Rsize - 1);
                }
                else{                                   //para quando é retornado k+1 do return size(não é divisivel por 2) i+Rsize vai ser maior que n
                    insertionSort(a, i, n-1);
                }
                }
        }
        //Pro Tip: this is how we can create an aux array of Comparables
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
        for(int groupSize = 1; groupSize < n; groupSize *= 2)//traditional bottomup sort nos grupos ja ordenados
        {
            for(int low = 0; low < n - groupSize; low += 2*groupSize)
            {
                merge(a, aux, low, low+groupSize-1, Math.min(low+2*groupSize-1,n-1));
            }
        }
        }
    }



    public static Integer[] generateRandomExample(int n)
    {
        return generateRandomExample(pseudoRandom,n);
    }

    public static Integer[] generateRandomExample(Random randomGenerator, int n)
    {
        Integer []a = new Integer[n];
        for(int i = 0; i <n ; i++){
            a[i] = randomGenerator.nextInt(n);
        }
        return a;
    }

    public static Integer[] generateMostlySortedExample(int n)
    {
        return generateMostlySortedExample(pseudoRandom,n);
    }


    public static Integer[] generateMostlySortedExample(Random randomGenerator, int n)
    {
        Integer []a = new Integer[n];
        for(int i = 0; i <n ; i++) {
            if(i%10 == 0) {
                a[i] = randomGenerator.nextInt(n);
            }
            else{
                a[i] = i;
            }
        }
        return a;
    }

    public static Integer[] generateAlmostSortedExample(int n)
    {
        return generateAlmostSortedExample(pseudoRandom,n);
    }

    public static Integer[] generateAlmostSortedExample(Random randomGenerator, int n)
    {
        Integer []a = new Integer[n];
        for(int i = 0; i <n ; i++) {
            if(i < n*0.01) {
                a[i] = randomGenerator.nextInt(n);
            }
            else{
                a[i] = i;
            }
        }
        return a;
    }

    public static Integer[] generateAscendingExample(Random randomGenerator, int n)
    {
        Integer []a = new Integer[n];
        for(int i = 0; i <n ; i++) {
        a[i] = i;
        }
        return a;
    }

    public static Integer[] generateDescendingExample(Random randomGenerator, int n)
    {
        Integer []a = new Integer[n];
        int sn = n;
        for(int i = 0; i <n ; i++) {
                a[i] = sn--;
        }
        return a;
    }

    public static void main(String[] args)
    {
        //TODO: implement tests
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv","Merge/Random",MergeInsertionSort::generateRandomExample,MergeInsertionSort::sort,100,15,30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv","Merge/Mostly",MergeInsertionSort::generateMostlySortedExample,MergeInsertionSort::sort,100,15,30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv","Merge/Almost",MergeInsertionSort::generateAlmostSortedExample,MergeInsertionSort::sort,100,15,30);
    }

    //this method might be usefull for testing
    private static void printArray(Object[] a, int low, int high)
    {
        if(a == null || a.length == 0)
        {
            System.out.println("Array: []");
            return;
        }

        if(low > 0)
        {
            System.out.print("Array: [...");
        }
        else
        {
            System.out.print("Array: [");
        }

        for(int i = low; i <= high; i++)
        {
            System.out.print(a[i]+",");
        }
        if(high < a.length-1)
        {
            System.out.print(a[a.length-1]+"...]");
        }
        else
        {
            System.out.print(a[a.length-1]+"]");
        }

        System.out.println();
    }
}
