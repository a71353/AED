package aed.sorting;
import java.util.Random;

public class SmartMergeSort extends Sort {

    private static final int MAX_INTERVAL = 64;

    //creates a random generator with a specific seed
    //this is useful for testing methods that are supposed to generate random elements
    //because we can always repeat the same tests by using the same seed
    private static final Random pseudoRandom = new Random(3729);


    //sort an array of elements (using InsertionSort) from low to high (including)
    //assuming that the first n elements are already sorted between themselves
    //this variation implies that we can start with a bigger hand immediately
    //this method is very useful for the SmartMergeSort in order to extend a natural
    //ascending run with additional elements to make a run with minimum size
    public static <T extends Comparable<T>> void insertionSortWithInitialSortedHand(T[] a, int low, int n, int high)
    {
        assert(low < high);
        assert(n > 0);
        assert(low + n <= high);
        if(!isSorted(a)) {//se nao estiver ordenado insertionsort
            for (int i = low + n; i <= high; i++) {//primeira mão é n
                for (int j = i; j > low; j--) {
                    if (less(a[j], a[j - 1])) {
                        exchange(a, j, j - 1);
                    } else break;
                }
            }
        }
        //TODO: implement
    }

    public static <T extends Comparable<T>> Run getNaturalRun(T[] a, int low, int high)
    {
        assert(low < high);
        int n = a.length;
        int result = 1;
        for(int i = low; i < high; i++)
        {
            if(less(a[i], a[i+1]) || a[i].equals(a[i+1])){//se a[i] for maior ou igual a a[i+1] result++
                result++;
            }
            else{
                break;
            }
        }
        return new Run(low, result);//meter no formato da run
        //TODO: implement
    }


    public static <T extends Comparable<T>> Run getNaturalOrMakeAscendingRun(T[] a, int low, int high)//runtime error
    {
        assert(low < high);
        int n = a.length;
        Run result1 = null;
        int result = 1;
        if(a[low + 1].compareTo(a[low]) >= 0)
        {
           result1 = getNaturalRun(a, low, high);
        }
        else
        {
            for(int i = low; i < high; i++){
                if(a[i].compareTo(a[i+1]) < 0)
                {
                    result++;
                }
                else{
                    break;
                }
            }
            int result2 = result;
            for(int i = low; i < result; i++){
                a[i] = a[result2--];
            }
            result1 = new Run(low, result);
        }
        return result1;
    }

    public static <T extends Comparable<T>> Run getNextRunWithMinimumSize(T[] a, int low, int high, int minRunSize)
    {
        assert(low < high);
        assert(minRunSize > 0);

        //TODO: implement
        return null;
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, Run leftRun, Run rightRun)
    {
        assert(rightRun.start == leftRun.start + leftRun.length);
        int low = leftRun.start;//começa no left
        int mid = leftRun.start + leftRun.length - 1;//mid é o começo + o tamanho
        int high = rightRun.start + rightRun.length - 1;//começo da right run + o tamanho
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

    public static <T extends Comparable<T>> void mergeCollapse(MergeStack stack, T[] a, T[] aux)
    {
        //TODO: implement
    }


    public static <T extends Comparable<T>> void sort(T[] a)
    {

        //TODO: implement

        //Pro Tip: this is how we can create an aux array of Comparables
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
    }


    public static Integer[] generateLargeNaturalRunsExample(Random randomGenerator, int n)
    {
        //todo: implement
        return null;
    }


    public static void main(String[] args)
    {
        //TODO: implement tests
    }
}
