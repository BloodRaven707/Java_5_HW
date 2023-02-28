import java.util.Arrays;

public class task_3 {
    public static void main( String args[] ) {
        System.out.println( "\n### Программа реализует алгоритм пирамидальной сортировки (HeapSort)." );

        // Не стал делать генератор...
        int arr[] = { 5, 12, 0, 11, 9, 12, 13, 5, 6, 7, 1, 7 };
        heapSort( arr );
        System.out.println( "\nОтсортированный массив: " + String.join( ",", Arrays.toString( arr ) ) + "\n" );
    }


    public static void heapSort( int arr[] ) {
        int n = arr.length;

        for ( int i = n / 2 - 1; i >= 0; i-- )
            heapify( arr, n, i );

        for ( int i = n - 1; i > 0; i-- ) {
            int temp = arr[ 0 ];
            arr[ 0 ] = arr[ i ];
            arr[ i ] = temp;
            heapify( arr, i, 0 );
        }
    }


    // Функция перегруппировки массива в поддерево
    public static void heapify( int arr[], int n, int i ) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if ( l < n && arr[ l ] > arr[ largest ] )
            largest = l;

        if ( r < n && arr[ r ] > arr[ largest ] )
            largest = r;

        if ( largest != i ) {
            int swap = arr[ i ];
            arr[ i ] = arr[ largest ];
            arr[ largest ] = swap;

            heapify( arr, n, largest );
        }
    }
}