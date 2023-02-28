public class task_4 {

    private static final int BOARD_SIZE = 8;
    private static int[] queens = new int[ BOARD_SIZE ];

    public static void main( String[] args ) {
        System.out.println( "\n### Программа реализует алгоритм пирамидальной сортировки (HeapSort)." );
        placeQueens( 0 );
    }


    private static void placeQueens( int row ) {
        if ( row == BOARD_SIZE ) {
            printSolution();
        } else {
            for ( int i = 0; i < BOARD_SIZE; i++ ) {
                queens[ row ] = i;
                if ( isValid( row ) ) {
                    placeQueens( row + 1 );
                }
            }
        }
    }


    private static boolean isValid( int row ) {
        for ( int i = 0; i < row; i++ ) {
            if ( queens[ i ] == queens[ row ] ||
                 queens[ i ]  - queens[ row ] == row - i ||
                 queens[ i ]  - queens[ row ] == i - row ) {
                return false;
            }
        }
        return true;
    }


    private static void printSolution() {
        for ( int i = 0; i < BOARD_SIZE; i++ ) {
            for ( int j = 0; j < BOARD_SIZE; j++ ) {
                if ( queens[ i ] == j ) {
                    System.out.print( "Q " );
                } else {
                    System.out.print( ". " );
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}