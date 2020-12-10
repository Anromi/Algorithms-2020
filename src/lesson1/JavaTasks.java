package lesson1;

import kotlin.NotImplementedError;
import kotlin.text.Regex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Math.abs;

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
    // трудоёмкость: O(N * log N)
    // ресурсоёмкость: O(N)
    static public void sortTimes(String inputName, String outputName) throws IOException {
        List<Integer> listTime = new ArrayList<>();

        FileReader reader = new FileReader(inputName);
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine()) {
            String lineString = scanner.nextLine();
            if (!lineString.matches(String.valueOf(new Regex("(0[1-9]|1[0-2]):[0-5][0-9]:[0-5][0-9] (PM|AM)"))))
                throw  new IllegalArgumentException();
            String[] lineList = lineString.replace(" ", ":").split(":");
            int secAMorPM;
            if (lineList[3].equals("AM")) {
                int new12;
                if (!lineList[0].equals("12")) {
                    new12 = Integer.parseInt(lineList[0]);
                } else new12 = 0;
                secAMorPM = new12 * 60 * 60 + Integer.parseInt(lineList[1]) * 60 + Integer.parseInt(lineList[2]);

            } else {
                int newNumbers;
                if (!lineList[0].equals("12")) {
                    newNumbers = Integer.parseInt(lineList[0]) + 12;
                } else newNumbers = 12;
                secAMorPM = newNumbers * 60 * 60 + Integer.parseInt(lineList[1]) * 60 + Integer.parseInt(lineList[2]);
            }
            listTime.add(secAMorPM);
        }

        int[] listNewTime = listTime.stream().mapToInt(i->i).toArray();
        Sorts.quickSort(listNewTime);

        FileWriter writer = new FileWriter(outputName);

        for (int i = 0; i < listNewTime.length; i++) {
            int element = listNewTime[i];
            String format = String.format("%02d:%02d:%02d", 12, (element % 3600) / 60, element % 60);
            if (element < 43200) {
                if (element / 3600 == 0)
                    writer.write(format + " AM" + System.getProperty("line.separator"));
                else
                    writer.write(String.format("%02d:%02d:%02d", element / 3600,
                            (element % 3600) / 60, element % 60) + " AM" + System.getProperty("line.separator"));
            }
            if (element >= 43200) {
                if (element / 3600 == 12)
                    writer.write(format + " PM" + System.getProperty("line.separator"));
                else
                    writer.write(String.format("%02d:%02d:%02d", (element / 3600) - 12,
                            (element % 3600) / 60, element % 60) + " PM" + System.getProperty("line.separator"));
            }
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
    // трудоёмкость: O(N)
    // ресурсоёмкость: O(1)
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        int maxTemp = 7731;
        int minTemp = 2730;
        int[] ar = new int[maxTemp];
        try {
            FileReader fileReader = new FileReader(inputName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            while (str != null) {
                int temp = (int) (Double.parseDouble(str) * 10 + minTemp);
                str = bufferedReader.readLine();
                ar[temp]++;
            }
            FileWriter fileWriter = new FileWriter(outputName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < ar.length; i++) {
                while (ar[i] > 0) {
                    fileWriter.write((i - minTemp) / 10.0 + System.lineSeparator());
                    ar[i]--;
                }
            }
            fileReader.close();
            fileWriter.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw (new IllegalArgumentException("Значение температуры вышло за диапазон допустимых значений."));
        } catch (NumberFormatException e) {
            throw (new IllegalArgumentException("Значение имеет неравильный формат формат"));
        }
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
