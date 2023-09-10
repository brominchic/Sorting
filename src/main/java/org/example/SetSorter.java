package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetSorter {

    public SetSorter() {
    }

    private static int getFactorial(int firstInt) {
        int result = 1;
        for (int i = 1; i <= firstInt; i++) {
            result = result * i;
        }
        return result;

    }

    // где тесты?
    // где комменты?
    // почему приватный метод выше чем публичный
    // исправил тебе сигнатуру метода чтобы ты понимал как должно быть
    // работу надо вести в отдельной ветке и открывать мр чтобы можно было комментить
    // папку .idea надо заносить в гитигнор, оно у каждого свое генерится
    public <T> List<List<T>> getArrays(List<T> baseList) throws CloneNotSupportedException {
        HashMap valuesOfBaseList = new HashMap();
        for (int i = 0; i < baseList.size(); i++) {
            valuesOfBaseList.put(baseList.get(i), i);
        }
        // старайся работать в первую очередь с абстракциями, интерфейсами, т.п List а не с реализациями(ArrayList)
        ArrayList<ArrayList<ArrayList>> list = new ArrayList<ArrayList<ArrayList>>();
        // мне кажется этот код излишним, твоя рекурсия в целом сможет эти кейсы покрывать
        if (baseList.size() <= 3) {
            ArrayList<ArrayList<ArrayList>> firstThreeArrays = getFirstThreeArrays(baseList, valuesOfBaseList);
            ArrayList<ArrayList> threeList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < firstThreeArrays.get(i).size(); j++) {
                    threeList.add(firstThreeArrays.get(i).get(j));
                }
            }
            return threeList;
        }
        ArrayList<ArrayList<ArrayList>> firstThree = getFirstThreeArrays(baseList, valuesOfBaseList);
        for (int i = 0; i < 3; i++) {
            list.add(i, firstThree.get(i));
        }
        ArrayList<ArrayList<ArrayList>> combinations = getAllUniqueCombinations(baseList, valuesOfBaseList);
        for (int i = 3; i < baseList.size(); i++) {
            list.add(i, new ArrayList<ArrayList>());
            for (int j = 0; j < baseList.size() - i; j++) {
                for (int k = 0; k < combinations.get(i - 1).size(); k++) {
                    // ты тут так работаешь с элементами как будто они всегда int. задание поставлено что элементы могут быть любыми объектами
                    int intFirstChar = (int) valuesOfBaseList.get(baseList.get(j));
                    int intLastChar = (int) valuesOfBaseList.get(combinations.get(i - 1).get(k).get(0));
                    if (intFirstChar < intLastChar) {
                        ArrayList newCombination = new ArrayList<>();
                        newCombination = combinations.get(i - 1).get(k);
                        ArrayList<ArrayList> newCombinations = getAllArrays(newCombination, valuesOfBaseList);
                        for (int l = 0; l < newCombinations.size(); l++) {
                            newCombinations.get(l).add(0, baseList.get(j));
                            list.get(i).add(newCombinations.get(l));
                        }
                    }
                }
            }
        }
        ArrayList<ArrayList> finalList = new ArrayList<ArrayList>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                finalList.add(list.get(i).get(j));
            }
        }
        return finalList;
    }

    // формат поломался, почаще нажимай ctrl-alt-l. перед коммитом можно галочку поставить reformat code
    private ArrayList getFirstThreeArrays(ArrayList baseList, HashMap valuesOfBaseList) {
        ArrayList<ArrayList<ArrayList>> firstThreeArrays = new ArrayList<ArrayList<ArrayList>>();
        for (int i = 0; i < baseList.size(); i++) {
            firstThreeArrays.add(new ArrayList<ArrayList>());
        }
        for (int i = 0; i < baseList.size(); i++) {
            firstThreeArrays.get(0).add(new ArrayList<ArrayList>());
            firstThreeArrays.get(0).get(i).add(baseList.get(i));
        }
        int size = 0;
        if (valuesOfBaseList.size() >= 3) {
            size = 3;
        } else {
            size = valuesOfBaseList.size();
        }
        for (int n = 0; n < size - 1; n++) {
            int num = 0;
            for (int i = 0; i < firstThreeArrays.size(); i++) {
                for (int j = 0; j < firstThreeArrays.get(n).size(); j++) {
                    int intFirstChar = (int) valuesOfBaseList.get(firstThreeArrays.get(0).get(i).get(0));
                    int intLastChar = (int) valuesOfBaseList.get(firstThreeArrays.get(n).get(j).get(0));
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

    public ArrayList getAllUniqueCombinations(ArrayList baseList, HashMap valuesOfBaseList) {
        ArrayList<ArrayList<ArrayList>> allUniqueCombinations = new ArrayList<ArrayList<ArrayList>>();
        for (int i = 0; i < baseList.size(); i++) {
            allUniqueCombinations.add(new ArrayList<ArrayList>());
        }
        for (int i = 0; i < baseList.size(); i++) {
            allUniqueCombinations.get(0).add(new ArrayList<ArrayList>());
            allUniqueCombinations.get(0).get(i).add(baseList.get(i));
        }
        for (int n = 0; n < baseList.size() - 1; n++) {
            int num = 0;
            for (int i = 0; i < allUniqueCombinations.size(); i++) {
                for (int j = 0; j < allUniqueCombinations.get(n).size(); j++) {
                    int intFirstChar = (int) valuesOfBaseList.get(allUniqueCombinations.get(0).get(i).get(0));
                    int intLastChar = (int) valuesOfBaseList.get(allUniqueCombinations.get(n).get(j).get(0));
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
        return allUniqueCombinations;
    }

    private ArrayList<ArrayList> getAllArrays(ArrayList baseList, HashMap valuesOfBaseList) throws CloneNotSupportedException {
        ArrayList<ArrayList> allArrays = new ArrayList<ArrayList>();
        int size = getFactorial(baseList.size());
        for (int i = 0; i < baseList.size(); i++) {
            allArrays.add(new ArrayList<ArrayList>());
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

    private void sort(int numOfString, ArrayList<ArrayList> allArrays, HashMap valuesOfBaseList) {
        ArrayList<ArrayList> arrayList = allArrays.get(allArrays.size() - 1 - numOfString);
        int size = arrayList.size();
        for (int i = size - 1; i >= 0; i--) {
            ArrayList combination = arrayList.get(i);
            int intFirstChar = (int) valuesOfBaseList.get(combination.get(0));
            int intLastChar = (int) valuesOfBaseList.get(combination.get(numOfString));
            if (intFirstChar > intLastChar) {
                allArrays.get(allArrays.size() - 1 - numOfString).remove(i);
            }
        }
    }

    private void writeToArray(ArrayList baseList, int numOfPositionInArray, int SizeOfSet, int LastSizeOfSet, int numOfString, ArrayList<ArrayList> allArrays) throws CloneNotSupportedException {
        writeToPosition(baseList, numOfPositionInArray, SizeOfSet, numOfString, allArrays);
        System.out.println(allArrays);
        int size = baseList.size();
        for (int i = 0; i < baseList.size(); i++) {
            ArrayList newList = (ArrayList) baseList.clone();
            newList.remove(i);
            if (newList.size() > numOfString) {
                writeToArray(newList, numOfPositionInArray + 1, getFactorial(size - 1) * (LastSizeOfSet + i), (LastSizeOfSet + i) * (size - 1), numOfString, allArrays);
            }
        }
    }

    private void writeToPosition(ArrayList baseList, int numOfPositionInArray, int sizeOfSet, int numOfString, ArrayList<ArrayList> allArrays) {
        int size = baseList.size();
        for (int i = 0; i < size; i++) {
            int factorial = getFactorial(size - 1);
            for (int j = 0; j < factorial; j++) {
                ArrayList<ArrayList> arrayList = allArrays.get(numOfString);
                int place = (i * factorial + j + sizeOfSet);
                if (place % getFactorial(numOfString) == 0) {
                    arrayList.get(place / getFactorial(numOfString)).add(numOfPositionInArray, baseList.get(i));
                }
            }
        }
    }
}

