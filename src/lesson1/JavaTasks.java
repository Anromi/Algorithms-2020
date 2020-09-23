package lesson1;

import kotlin.NotImplementedError;
import kotlin.text.Regex;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        ArrayList<String[]> listLine = new ArrayList<>(); // список из списков времени и (am/pm)
        ArrayList<ArrayList<String>> listPair = new ArrayList<>(); // время и секунды

        FileReader reader = new FileReader(inputName); // читаем файл
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine()) {
            ArrayList<String> list = new ArrayList<>();
            String lineString = scanner.nextLine();
            if (!lineString.matches(String.valueOf(new Regex("[0-2][0-9]:[0-5][0-9]:[0-5][0-9] (PM|AM)"))))
                throw  new IllegalArgumentException();
            String[] lineList = lineString.replace(" ", ":").split(":"); // [01,15,19,PM]
            int secAMorPM;
            if (lineList[3].equals("AM")) {
                int new12;
                if (!lineList[0].equals("12")) {
                    new12 = Integer.parseInt(lineList[0]);
                } else new12 = 0;
                secAMorPM = new12 * 60 * 60 + Integer.parseInt(lineList[1]) * 60 + Integer.parseInt(lineList[2]); // из AM в обычные сек

            } else {
                int newNumbers;
                if (!lineList[0].equals("12")) {
                    newNumbers = Integer.parseInt(lineList[0]) + 12;
                } else newNumbers = 12;
                secAMorPM = newNumbers * 60 * 60 + Integer.parseInt(lineList[1]) * 60 + Integer.parseInt(lineList[2]); // из PM в обычные сек
            }
            list.add(lineString);
            list.add(Integer.toString(secAMorPM));
            listPair.add(list);
        }

        FileWriter writer = new FileWriter(outputName);

        for (int i = 0; i < listPair.size(); i++) {
            String time = listPair.get(i).get(0);
            int minSecI = Integer.parseInt(listPair.get(i).get(1));
            int minIndex = i;
            for (int j = i + 1; j < listPair.size(); j++) {
                int minSecJ = Integer.parseInt(listPair.get(j).get(1));
                if (minSecJ < minSecI) {
                    minSecI = minSecJ;
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                String sec = listPair.get(i).get(1);
                String str =  listPair.get(i).get(0);
                listPair.get(i).set(1 , listPair.get(minIndex).get(1));
                listPair.get(i).set(0 , listPair.get(minIndex).get(0));
                listPair.get(minIndex).set(1 , sec);
                listPair.get(minIndex).set(0 , str);
            }
        }

        for (int i = 0; i < listPair.size(); i++) {
            writer.write(listPair.get(i).get(0) + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
