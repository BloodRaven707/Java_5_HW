import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;


public class task_2_2 {
    public static void main( String[] args ) {
        System.out.println( "\n### Программа находит выведет повторяющиеся имена с количеством повторений, сортирует по убыванию популярности. Для сортировки использует TreeMap." );
        // Как всегд ДЗ = Х.З. // Имя = Иван ?? Иван Юрин, будет "Имя" = "Иван Юрин".split( " " )[0];

        String[] fullNames = { "Иван Иванов", "Светлана Петрова", "Кристина Белова", "Анна Мусина",
            "Анна Крутова", "Иван Юрин", "Петр Лыков", "Павел Чернов", "Петр Чернышов",
            "Мария Федорова", "Марина Светлова", "Мария Савина", "Мария Рыкова", "Марина Лугова",
            "Анна Владимирова", "Иван Мечников", "Петр Петин", "Иван Ежов" };

        System.out.println( "\nИсходный список:\n" + Arrays.toString( fullNames ) );

        HashMap<String, Integer> countMap = new HashMap<>();
        String name;
        for ( String fullName : fullNames ) {
            name = fullName.split( " " )[ 0 ];
            //System.out.println( "+" + name + "+" );
            if ( countMap.containsKey( name ) ) {
                countMap.put( name, countMap.get( name ) + 1 );
            } else {
                countMap.put( name, 1 );
            }
            //Integer entry = countMap.get( name );
            //System.out.println( name + " - " + entry );
        }

        // Сортируем через кастомный класс
        TreeMap<String, Integer> sortedMap = new TreeMap<>( new ValueComparator( countMap ) ) {};
        sortedMap.putAll( countMap );

        System.out.println( "\nНе сортированные повторяющиеся:" );
        for ( HashMap.Entry<String, Integer> entry : countMap.entrySet() ) {
            if ( entry.getValue() > 1 ) {
                System.out.println( entry.getKey() + " - " + entry.getValue() );
            }
        }

        System.out.println( "\nСортированнае повторяющиеся:" );
        for ( HashMap.Entry<String, Integer> entry : sortedMap.entrySet() ) {
            if ( entry.getValue() > 1 ) {
                System.out.println( entry.getKey() + " - " + entry.getValue() );
            }
        }
        System.out.println();
    }


    // Класс для сравнения значений в HashMap
    static class ValueComparator implements Comparator<String> {
        HashMap<String, Integer> map;

        public ValueComparator( HashMap<String, Integer> map ) {
            this.map = map;
        }

        // Функции сравнения...
        public int compare( String el1, String el2 ) {
            if ( map.get( el1 ) >= map.get( el2 ) ) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}