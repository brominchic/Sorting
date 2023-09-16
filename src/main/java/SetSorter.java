import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SetSorter implements Sorter {

    public SetSorter() {
    }

    private static int getFactorial(int firstInt) {
        int result = 1;
        for (int i = 1; i <= firstInt; i++) {
            result = result * i;
        }
        return result;

    }

    @Override
    public <T> List<List<T>> getArrays(List<T> baseList) {
        HashMap<T, Integer> valuesOfBaseList = new HashMap<>(); // создаем мапу хрананящую числовое значение для каждого элемента из baseList
        for (int i = 0; i < baseList.size(); i++) {
            valuesOfBaseList.put(baseList.get(i), i);
        }
        List<List<List<T>>> list = new ArrayList<>();//создаем массив массивов массивов чтобы хранить там все уникальные значения, абстракцию тут запихнуть не получилось
        List<List<List<T>>> firstThree = getFirstThreeArrays(baseList, valuesOfBaseList);// c помощью метода получаем комбинации с одним, двумя и тремя обьектами
        for (int i = 0; i < 3; i++) {
            list.add(i, firstThree.get(i));
        }
        List<List<List<T>>> combinations = getAllUniqueCombinations(baseList, valuesOfBaseList);
        for (int i = 3; i < baseList.size(); i++) {
            list.add(i, new ArrayList<>());// добавляем массив для хранения дргуих массивов из i+1 элементов
            for (int j = 0; j < baseList.size() - i; j++) {
                for (int k = 0; k < combinations.get(i - 1).size(); k++) {
                    int intFirstChar = valuesOfBaseList.get(baseList.get(j));// получаем числовое значение для элемента под номером j из изначального массива
                    int intLastChar = valuesOfBaseList.get(combinations.get(i - 1).get(k).get(0));//получаем числовое значение для первого элемента из массива комбинаций длинной i
                    if (intFirstChar < intLastChar) {// сравниваем, если числовое значени элемента из изначального массива меньше первого элемента из комбинации, то с помощью метода получаем все массивы из n чисел, что при добавления справа еще одного все массивы окажутся уникальными, и добавляем к ним элемент изначального массива под номером j
                        List<T> newCombination = combinations.get(i - 1).get(k);
                        List<List<T>> newCombinations = getAllArrays(newCombination, valuesOfBaseList);
                        for (List<T> combination : newCombinations) {
                            combination.add(0, baseList.get(j));
                            list.get(i).add(combination);
                        }
                    }
                }
            }
        }
        LinkedList<List<T>> finalList = new LinkedList<>();//добавляем все в один массив
        for (List<List<T>> lists : list) {
            finalList.addAll(lists);
        }
        return finalList;
    }

    private <T> List<List<List<T>>> getFirstThreeArrays(List<T> baseList, HashMap<T, Integer> valuesOfBaseList) {// Этот метод по сути getAllUniqueCombinations, но для длинны от 1 до 3 обьектов
        List<List<List<T>>> firstThreeArrays = new ArrayList<>();
        for (int i = 0; i < baseList.size(); i++) {
            firstThreeArrays.add(new ArrayList<>());
        }
        for (int i = 0; i < baseList.size(); i++) {
            firstThreeArrays.get(0).add(new ArrayList<>());
            firstThreeArrays.get(0).get(i).add(baseList.get(i));
        }
        int size = Math.min(valuesOfBaseList.size(), 3);
        for (int n = 0; n < size - 1; n++) {
            int num = 0;
            for (int i = 0; i < firstThreeArrays.size(); i++) {
                for (int j = 0; j < firstThreeArrays.get(n).size(); j++) {
                    int intFirstChar = valuesOfBaseList.get(firstThreeArrays.get(0).get(i).get(0));
                    int intLastChar = valuesOfBaseList.get(firstThreeArrays.get(n).get(j).get(0));
                    if (intFirstChar < intLastChar) {
                        firstThreeArrays.get(n + 1).add(new ArrayList<>());
                        firstThreeArrays.get(n + 1).get(num).add(firstThreeArrays.get(0).get(i).get(0));
                        for (int k = 0; k < firstThreeArrays.get(n).get(j).size(); k++) {
                            firstThreeArrays.get(n + 1).get(num).add(firstThreeArrays.get(n).get(j).get(k));
                        }
                        num++;
                    }
                }
            }
        }
        return firstThreeArrays;
    }

    private <T> List<List<List<T>>> getAllUniqueCombinations(List<T> baseList, HashMap<T, Integer> valuesOfBaseList) {
        //возвращает все варианты комбинаций по n элементов, где n от 1 до длинны начального массива
        List<List<List<T>>> allUniqueCombinations = new ArrayList<>();
        for (int i = 0; i < baseList.size(); i++) {
            allUniqueCombinations.add(new ArrayList<>());
        }
        for (int i = 0; i < baseList.size(); i++) {
            allUniqueCombinations.get(0).add(new ArrayList<>());
            allUniqueCombinations.get(0).get(i).add(baseList.get(i));
        }
        for (int n = 0; n < baseList.size() - 1; n++) {
            int num = 0;
            for (int i = 0; i < allUniqueCombinations.size(); i++) {
                for (int j = 0; j < allUniqueCombinations.get(n).size(); j++) {
                    int intFirstChar = valuesOfBaseList.get(allUniqueCombinations.get(0).get(i).get(0));
                    int intLastChar = valuesOfBaseList.get(allUniqueCombinations.get(n).get(j).get(0));
                    if (intFirstChar < intLastChar) {
                        allUniqueCombinations.get(n + 1).add(new ArrayList<>());
                        allUniqueCombinations.get(n + 1).get(num).add(allUniqueCombinations.get(0).get(i).get(0));
                        for (int k = 0; k < allUniqueCombinations.get(n).get(j).size(); k++) {
                            allUniqueCombinations.get(n + 1).get(num).add(allUniqueCombinations.get(n).get(j).get(k));
                        }
                        num++;
                    }
                }
            }
        }
        System.out.println(allUniqueCombinations);
        return allUniqueCombinations;
    }

    private <T> List<List<T>> getAllArrays(List<T> baseList, HashMap<T, Integer> valuesOfBaseList) {
        // метод который вызывает рекурсивный метод для поиска и метод сортировки, возвращает такие массивы из n чисел, что при добавления справа еще одного все массивы окажутся уникальными
        List<List<List<T>>> allArrays = new ArrayList<>();
        int size = getFactorial(baseList.size());
        for (int i = 0; i < baseList.size(); i++) {
            allArrays.add(new ArrayList<>());
            for (int j = 0; j < size / getFactorial(i); j++) {
                allArrays.get(i).add(new ArrayList<>());
            }
        }
        for (int i = 0; i < baseList.size(); i++) {
            writeToArray(baseList, 0, 0, 0, i, allArrays);
        }
        for (int i = 0; i < baseList.size(); i++) {
            sort(i, allArrays, valuesOfBaseList);
        }
        return allArrays.get(0);
    }

    private <T> void sort(int numOfString, List<List<List<T>>> allArrays, HashMap<T, Integer> valuesOfBaseList) {
        // метод сортировки отвечающий за то, чтобы не было массивов которые справа налево повторяют другие массивы слева направо
        var arrayList = allArrays.get(allArrays.size() - 1 - numOfString);
        int size = arrayList.size();
        for (int i = size - 1; i >= 0; i--) {
            var combination = arrayList.get(i);
            int intFirstChar = valuesOfBaseList.get(combination.get(0));
            int intLastChar = valuesOfBaseList.get(combination.get(numOfString));
            if (intFirstChar > intLastChar) {
                allArrays.get(allArrays.size() - 1 - numOfString).remove(i);
            }
        }
    }

    @SuppressWarnings("SuspiciousListRemoveInLoop")
    private <T> void writeToArray(List<T> baseList, int numOfPositionInArray, int SizeOfSet, int LastSizeOfSet, int numOfString, List<List<List<T>>> allArrays) {
        // рекурсивный метод который в паре с writeToPosition выдает все комбинации массивов из каких то обьектов
        writeToPosition(baseList, numOfPositionInArray, SizeOfSet, numOfString, allArrays);
        int size = baseList.size();
        for (int i = 0; i < baseList.size(); i++) {
            var newList = new ArrayList<>(baseList);
            newList.remove(i);
            if (newList.size() > numOfString) {
                writeToArray(newList, numOfPositionInArray + 1, getFactorial(size - 1) * (LastSizeOfSet + i), (LastSizeOfSet + i) * (size - 1), numOfString, allArrays);
            }
        }
    }

    private <T> void writeToPosition(List<T> baseList, int numOfPositionInArray, int sizeOfSet, int numOfString, List<List<List<T>>> allArrays) {
        int size = baseList.size();
        for (int i = 0; i < size; i++) {
            int factorial = getFactorial(size - 1);
            for (int j = 0; j < factorial; j++) {
                List<List<T>> arrayList = allArrays.get(numOfString);
                int place = (i * factorial + j + sizeOfSet);
                if (place % getFactorial(numOfString) == 0) {
                    arrayList.get(place / getFactorial(numOfString)).add(numOfPositionInArray, baseList.get(i));
                }
            }
        }
    }
}

