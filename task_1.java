import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class task_1 {
    public static void main( String[] args ) {
        System.out.println( "\n### Программа реализует структуру и функционал телефонной книги с помощью HashMap, учитывает, что 1 человек может иметь несколько телефонов." );
        // Но не учитывает что номер телефона может принадлежать нескольким людям...
        // Хотя есть возможность заменить телефон, указанному контакту

        Map<String, List<String>> phoneBook = new HashMap<>();
        Scanner iScanner = new Scanner( System.in, "UTF-8" ); // Боль Java - это в т.ч. кодировки... в 19 так все и не вылечено...
        String name;

        boolean exit = false;
        while ( !exit ) {
            System.out.println( "\n1. Добавить контакт" );
            System.out.println( "2. Вывести все контакты" );
            System.out.println( "3. Изменить номер" );

            System.out.println( "5. Удалить контакт" );

            System.out.println( "7. Найти телефоны по имени" );
            System.out.println( "8. Найти имя по телефону" );

            System.out.println( "0. Выйти" );

            String input = iScanner.nextLine();
            switch ( input ) {
                case "1":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    System.out.print( "Введите номер телефона: " );
                    String phoneNumber = getPhoneNumberFromUser( iScanner );
                    addContact( phoneBook, name, phoneNumber );
                    break;
                case "2":
                    printAllContacts( phoneBook );
                    break;
                case "3":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    System.out.print( "Введите старый номер телефона: " );
                    String oldPhoneNumber = getPhoneNumberFromUser( iScanner );
                    System.out.print( "Введите новый номер телефона: " );
                    String newPhoneNumber = getPhoneNumberFromUser( iScanner );
                    updatePhoneNumber( phoneBook, name, oldPhoneNumber, newPhoneNumber );
                    break;
                case "5":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    removeContact( phoneBook, name );
                    break;
                case "7":
                    System.out.print( "\nВведите имя контакта: " );
                    name = iScanner.nextLine();
                    findPhoneNumbersByContact ( phoneBook, name );
                    break;
                case "8":
                    System.out.print( "\nВведите номер телефона: " );
                    String phoneToFind = getPhoneNumberFromUser( iScanner );
                    findContactByPhoneNumber( phoneBook, phoneToFind );
                    break;
                case "0":
                    System.out.print( "\nДля подтверждения введите 'Y': " );
                    String answer = iScanner.nextLine().toLowerCase();
                    if ( answer.equals( "y" ) ) {
                        exit = true;
                    }
                    break;
                default:
                    System.out.println( "\nНеверный ввод\n" );
            }
        }
        iScanner.close();
    }

    // Запрос номера
    public static String getPhoneNumberFromUser( Scanner iScanner ) {
        String phoneNumber = iScanner.nextLine();
        while ( !isNumeric( phoneNumber ) ) {
            System.out.println( "Номер телефона должен состоять только из цифр. Пожалуйста, введите номер заново:" );
            phoneNumber = iScanner.nextLine();
        }
        return phoneNumber;
    }

    // Проверка номера
    public static boolean isNumeric( String str ) {
        if ( str == null || str.length() == 0 ) {
            return false;
        }
        for ( char c : str.toCharArray() ) {
            if ( !Character.isDigit( c ) ) {
                return false;
            }
        }
        return true;
    }

    // 1. Добавить контакт
    public static void addContact( Map<String, List<String>> phoneBook, String name, String phoneNumber ) {
        if ( !phoneBook.containsKey( name ) ) {
            phoneBook.put( name, new ArrayList<>());
        }
        phoneBook.get( name ).add( phoneNumber );
        System.out.println("\nНомер телефона " + phoneNumber + " добавлен контакту " + name + "" );
    }

    // 2. Вывод всех контактов
    public static void printAllContacts( Map<String, List<String>> phoneBook ) {
        if ( phoneBook.isEmpty() ) {
            System.out.println( "\nТелефонная книга пуста" );
        } else {
            System.out.println( "\nСписок контактов:" );
            for ( Map.Entry<String, List<String>> entry : phoneBook.entrySet() ) {
                System.out.println( entry.getKey() + ": " + entry.getValue() );
            }
            // System.out.println();
        }
    }

    // ---. Изменить номер - Не правильно
    public static void findAndupdatePhoneNumber( Map<String, List<String>> phoneBook, String name, String oldPhoneNumber, String newPhoneNumber ) {
        if ( !phoneBook.containsKey( name ) && phoneBook.get( name ).contains( oldPhoneNumber ) ) {
            phoneBook.get( name ).set( phoneBook.get( name ).indexOf( oldPhoneNumber ), newPhoneNumber );
            System.out.println("Номер телефона " + oldPhoneNumber + " контакта " + name + " заменен на " + newPhoneNumber );
        } else {
            phoneBook.get( name ).add( newPhoneNumber );
            System.out.println("Номер телефона " + newPhoneNumber + " добавлен контакту " + name );
        }
    }

    // 3. Нормальная замена
    public static void updatePhoneNumber( Map<String, List<String>> phoneBook, String name, String oldPhoneNumber, String newPhoneNumber ) {
        List<String> phoneNumbers = phoneBook.get( name );
        if ( phoneNumbers == null ) {
            System.out.println( "Контакт " + name + " не найден в телефонной книге" );
            return;
        }
        int index = phoneNumbers.indexOf( oldPhoneNumber );
        if ( index == -1 ) {
            System.out.println("Номер телефона " + oldPhoneNumber + " для контакта " + name + " не найден в телефонной книге" );
            return;
        }
        phoneNumbers.set( index, newPhoneNumber );
        System.out.println( "Номер телефона " + oldPhoneNumber + " для контакта " + name + " успешно изменен на " + newPhoneNumber );
    }

    // 5. Удалить контакт
    public static void removeContact( Map<String, List<String>> phoneBook, String name ) {
        List<String> phoneNumbers = phoneBook.remove( name );
        if ( phoneNumbers != null ) {
            System.out.println( "Контакт " + name + " успешно удален из телефонной книги" );
        } else {
            System.out.println( "Контакт " + name + " не найден в телефонной книге" );
        }
    }

   // 7. Поиск по имени контакта
    public static void findPhoneNumbersByContact( Map<String, List<String>> phoneBook, String nameToFind ) {
        List<String> phoneNumbersFound = phoneBook.get( nameToFind );
        if ( phoneNumbersFound != null ) {
            System.out.println( "Номера телефона " + nameToFind + ": " );
            for ( String phoneNumberFound : phoneNumbersFound ) {
                System.out.println( phoneNumberFound );
            }
        } else {
            System.out.println( "Контакт "+ nameToFind +" не найден в телефонной книге" );
        }
    }

    // 8. Поиск по номеру телеона
    public static void findContactByPhoneNumber( Map<String, List<String>> phoneBook, String phoneNumber ) {
        boolean contactFound = false;
        for ( Map.Entry<String, List<String>> entry : phoneBook.entrySet() ) {
            String name = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            if ( phoneNumbers.contains( phoneNumber ) ) {
                System.out.println( "Номер телефона " + phoneNumber + " принадлежит контакту " + name );
                contactFound = true;
            }
        }
        if ( !contactFound ) {
            System.out.println( "Контакт с номером телефона " + phoneNumber + " не найден в телефонной книге" );
        }
    }
}
