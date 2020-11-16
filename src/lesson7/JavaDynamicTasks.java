package lesson7;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    // N - длина first строки
    // M - длина second строки
    // Трудоёмкость: O(N * M)
    // Ресурсоёмкость: O(N * M)
    public static String longestCommonSubSequence(String first, String second) {
        if (first.equals("") || second.equals("")) return "";

        int[][] matrix = new int[first.length() + 1][second.length() + 1];
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = first.length() - 1; 0 <= i; i--) {
            for (int j = second.length() - 1; 0 <= j; j--) {
                if (first.charAt(i) == second.charAt(j))
                    matrix[i][j] = matrix[i + 1][j + 1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i][j + 1], matrix[i + 1][j]);
            }
        }

        int i = 0;
        int j = 0;
        while (i < first.length() && j < second.length()) {
            if (first.charAt(i) == second.charAt(j)) {
                stringBuilder.append(second.charAt(j));
                i++;
                j++;
            }
            else if (matrix[i][j + 1] <= matrix[i + 1][j]) i++;
            else j++;
        }

        return stringBuilder.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    // Трудоёмкость: O( N^2 )
    // Ресурсоёмкость: O(N)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int size = list.size();
        if (size < 1) return list;
        int[] prev = new int[size];
        int[] d = new int[size];

        for (int i = 0; i < size; i++) {
            d[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i) && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int pos = 0;
        int length = 0;
        for (int i = 0; i < size; i++) {
            if (d[i] > length) {
                pos = i;
                length = d[i];
            }
        }

        List<Integer> result = new ArrayList<>();
        while (pos != -1) {
            result.add(0, list.get(pos));
            pos = prev[pos];
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
