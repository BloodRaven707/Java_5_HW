import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;


public class task_2_1_for_full_name {
    public static void main( String[] args ) {
        System.out.println( "\n### Программа находит выведет повторяющиеся имена с количеством повторений, сортирует по убыванию популярности. Для сортировки использует TreeMap." );
        // Как всегд ДЗ = Х.З. // Имя = Иван ?? Иван Юрин, будет "Имя" = "Иван Юрин".split( " " )[0];

        // Проблемка, не хочет выводить все имена... не понимаю как это вылечить в Java
        String[] fullNames = { "Иван Иванов", "Светлана Петрова", "Кристина Белова", "Анна Мусина",
            "Анна Крутова", "Иван Юрин", "Петр Лыков", "Павел Чернов", "Петр Чернышов",
            "Мария Федорова", "Марина Светлова", "Мария Савина", "Мария Рыкова", "Марина Лугова",
            "Анна Владимирова", "Иван Мечников", "Петр Петин", "Иван Ежов", "Иван Иванов" };

        HashMap<String, Integer> countMap = new HashMap<>();
        for ( String name:  fullNames ) {
            if ( countMap.containsKey( name ) ) {
                countMap.put( name, countMap.get( name ) + 1 );
            } else {
                countMap.put( name, 1 );
            }
            // Integer entry = countMap.get( name );
            // System.out.println( name + " - " + entry );
        }

        // Сортируем через кастомный класс
        TreeMap<String, Integer> sortedMap = new TreeMap<>( new ValueComparator( countMap ) );
        sortedMap.putAll( countMap );

        System.out.println( "\nПовторяющиеся имена:" );

        boolean flag = false;
        for ( HashMap.Entry<String, Integer> entry : sortedMap.entrySet() ) {
            if ( entry.getValue() > 1 ) {
                System.out.println( entry.getKey() + " - " + entry.getValue() );
                flag = true;
            }
        }
        if ( !flag ) {
            System.out.println( "Отсутствуют..." );
        }
        System.out.println( "" );
    }


    // Класс для сравнения значений в HashMap
    static class ValueComparator implements Comparator<String> {
        HashMap<String, Integer> map;

        public ValueComparator( HashMap<String, Integer> map ) {
            this.map = map;
        }

        public int compare( String el1, String el2 ) {
            if ( map.get( el1 ) >= map.get( el2 ) ) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}