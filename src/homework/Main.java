package homework;


/**
 * Прочитать слова из файла.
 * Отсортировать в алфавитном порядке.
 * Посчитать сколько раз каждое слово встречается в файле. Вывести статистику на консоль
 * <p>
 * Найти слово с максимальным количеством повторений.
 * Вывести на консоль это слово и сколько раз оно встречается в файле
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Реализация через TreeMap");
        TreeMap<String, Integer> wordsCount = readTxtFileAndWordsCount();
        printWordWithMaxCount(wordsCount);

        System.out.println("Реализация через свой класс");
        ArrayList<WordCount> listWords = new ArrayList<>();
        initWordList(listWords);
        countSortPrintListWord(listWords);
        printWordWithMaxCount(listWords);


    }


    /**
     * метод считает сколько одинаковых слов, удаляет дубликаты, и сортирует по алфавиту
     * @param listWords
     */
    private static void countSortPrintListWord(ArrayList<WordCount> listWords) {
        for (int i = 0; i < listWords.size()-1; i++) {
            int count = listWords.get(i).getInteger();
            for (int j = i + 1; j < listWords.size(); j++) {
                if (listWords.get(i).getString().equals(listWords.get(j).getString())) {
                    listWords.remove(j);
                    count++;
                    listWords.get(i).setInteger(count);
                    j--;
                }
            }
        }
        Collections.sort(listWords, (o1, o2) -> o1.getString().compareTo(o2.getString()));
        for (WordCount listWord : listWords) {
            System.out.print(listWord.toString());
        }
    }

    /**
     * Метод, читает файл и заполняет ArrayList нашим классом
     * @param listWords
     */
    private static void initWordList(ArrayList<WordCount> listWords) {
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            while (scanner.hasNext()) {
                String word = scanner.useDelimiter("\\s+").next();
                listWords.add(new WordCount(word,1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Поиск и вывод на экран, слова с максимальным колличеством повторений
     * @param listWords
     */
    private static void printWordWithMaxCount(ArrayList<WordCount> listWords) {
        int maxCount = 0;
        String word = null;
        for (WordCount listWord : listWords) {
            if (listWord.getInteger() > maxCount) {
                maxCount = listWord.getInteger();
                word = listWord.getString();
            }
        }
        System.out.println(word + " - " + maxCount);
    }

    /**
     * Метод читает слова из файла, считает количество повторений, записывает их в TreeMap и выводит на экран
     * @return
     */
    private static TreeMap<String, Integer> readTxtFileAndWordsCount() {
        TreeMap<String, Integer> wordsCount = new TreeMap<>();
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            while (scanner.hasNext()) {
                String word = scanner.useDelimiter("\\s+").next();
                Integer count = wordsCount.get(word);
                if (count == null) {
                    count = 0;
                }
                wordsCount.put(word, ++count);
            }
            System.out.println(wordsCount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wordsCount;
    }


    /**
     * Вывод слова с максимальным колличеством повторений из TreeMap/
     * @param wordsCount
     */
    private static void printWordWithMaxCount(TreeMap<String, Integer> wordsCount) {
        int maxValue = 0;
        String result = null;
        for (Map.Entry<String, Integer> stringIntegerEntry : wordsCount.entrySet()) {
            if (stringIntegerEntry.getValue() > maxValue) {
                maxValue = stringIntegerEntry.getValue();
                result = stringIntegerEntry.getKey();
            }
        }
        System.out.println(result + " - " + maxValue);
    }
}
