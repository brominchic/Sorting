package org.example;

import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList getArrays(ArrayList baseList) throws CloneNotSupportedException {
        HashMap valuesOfBaseList = new HashMap();
        for (int i = 0; i < baseList.size(); i++) {
            valuesOfBaseList.put(baseList.get(i), i);
        }
        ArrayList<ArrayList<ArrayList>> finalList = new ArrayList<ArrayList<ArrayList>>();
        if (baseList.size() <= 3) {
            return getFirstThreeArrays(baseList);
        }
        ArrayList<ArrayList<ArrayList>> firstThree = getFirstThreeArrays(baseList);
        for (int i = 0; i < 3; i++) {
            finalList.add(i, firstThree.get(i));
        }
        ArrayList<ArrayList<ArrayList>> combinations = getAllCombinations((baseList));
        for (int i = 3; i < baseList.size(); i++) {
            finalList.add(i,new ArrayList<ArrayList>());
            for (int j = 0; j < baseList.size() - i; j++) {
                for (int k = 0; k < combinations.get(i-1).size(); k++) {
                    int intFirstChar = (int) valuesOfBaseList.get(baseList.get(j));
                    int intLastChar = (int) valuesOfBaseList.get(combinations.get(i-1).get(k).get(0));
                    if (intFirstChar < intLastChar) {
                        ArrayList newCombination = new ArrayList<>();
                        newCombination=combinations.get(i-1).get(k);
                        ArrayList<ArrayList> newCombinations = getArrayOfUniqueArrays(newCombination);
                        for (int l = 0; l < newCombinations.size(); l++) {
                            newCombinations.get(l).add(0,baseList.get(j));
                            finalList.get(i).add(newCombinations.get(l));
                        }
                    }
                }
            }
        }
        return finalList;
    }


    private ArrayList getFirstThreeArrays(ArrayList baseList) {
        ArrayList<ArrayList<ArrayList>> lists = new ArrayList<ArrayList<ArrayList>>();
        for (int i = 0; i < baseList.size(); i++) {
            lists.add(new ArrayList<ArrayList>());
        }
        for (int i = 0; i < baseList.size(); i++) {
            lists.get(0).add(new ArrayList<ArrayList>());
            lists.get(0).get(i).add(baseList.get(i));
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < baseList.size(); i++) {
            hashMap.put(baseList.get(i), i);
        }
        int size = 0;
        if (hashMap.size() >= 3) {
            size = 3;
        } else {
            size = hashMap.size();
        }
        for (int n = 0; n < size - 1; n++) {
            int num = 0;
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < lists.get(n).size(); j++) {
                    int intFirstChar = (int) hashMap.get(lists.get(0).get(i).get(0));
                    int intLastChar = (int) hashMap.get(lists.get(n).get(j).get(0));
                    if (intFirstChar < intLastChar) {
                        lists.get(n + 1).add(new ArrayList<>());
                        lists.get(n + 1).get(num).add(lists.get(0).get(i).get(0));
                        for (int k = 0; k < lists.get(n).get(j).size(); k++) {
                            lists.get(n + 1).get(num).add(lists.get(n).get(j).get(k));
                        }
                        num++;
                    }
                }
            }
        }
        return lists;
    }

    public ArrayList getAllCombinations(ArrayList baseList) {
        ArrayList<ArrayList<ArrayList>> lists = new ArrayList<ArrayList<ArrayList>>();
        for (int i = 0; i < baseList.size(); i++) {
            lists.add(new ArrayList<ArrayList>());
        }
        for (int i = 0; i < baseList.size(); i++) {
            lists.get(0).add(new ArrayList<ArrayList>());
            lists.get(0).get(i).add(baseList.get(i));
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < baseList.size(); i++) {
            hashMap.put(baseList.get(i), i);
        }

        for (int n = 0; n < baseList.size() - 1; n++) {
            int num = 0;
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < lists.get(n).size(); j++) {
                    int intFirstChar = (int) hashMap.get(lists.get(0).get(i).get(0));
                    int intLastChar = (int) hashMap.get(lists.get(n).get(j).get(0));
                    if (intFirstChar < intLastChar) {
                        lists.get(n + 1).add(new ArrayList<>());
                        lists.get(n + 1).get(num).add(lists.get(0).get(i).get(0));
                        for (int k = 0; k < lists.get(n).get(j).size(); k++) {
                            lists.get(n + 1).get(num).add(lists.get(n).get(j).get(k));
                        }
                        num++;
                    }
                }
            }
        }
        return lists;
    }

    private ArrayList<ArrayList> getArrayOfUniqueArrays(ArrayList baseList) throws CloneNotSupportedException {
        ArrayList<ArrayList> lists = new ArrayList<ArrayList>();
        int size = getFactorial(baseList.size());
        for (int i = 0; i < baseList.size(); i++) {
            lists.add(new ArrayList<ArrayList>());
            for (int j = 0; j < size / getFactorial(i); j++) {
                lists.get(i).add(new ArrayList<>());
            }
        }
        for (int i = 0; i < baseList.size(); i++) {
            writeToArray(baseList, 0, 0, 0, i, lists);
        }
        for (int i = 0; i < baseList.size(); i++) {
            sort(baseList, i, lists);
        }
        return lists.get(0);
    }

    private void sort(ArrayList baseList, int numOfString, ArrayList<ArrayList> lists) {
        HashMap<Object, Integer> NumbersOfObjects = new HashMap();
        for (int i = 0; i < baseList.size(); i++) {
            NumbersOfObjects.put(baseList.get(i), i);
        }
        ArrayList<ArrayList> arrayList = lists.get(lists.size() - 1 - numOfString);
        int size = arrayList.size();
        for (int i = size - 1; i >= 0; i--) {
            ArrayList combination = arrayList.get(i);
            int intFirstChar = (int) NumbersOfObjects.get(combination.get(0));
            int intLastChar = (int) NumbersOfObjects.get(combination.get(numOfString));
            if (intFirstChar > intLastChar) {
                lists.get(lists.size() - 1 - numOfString).remove(i);
            }
        }
    }

    private void writeToArray(ArrayList baseList, int numOfPositionInArray, int SizeOfSet, int LastSizeOfSet, int numOfString, ArrayList<ArrayList> lists) throws CloneNotSupportedException {
        writeToPosition(baseList, numOfPositionInArray, SizeOfSet, numOfString, lists);
        int size = baseList.size();
        for (int i = 0; i < baseList.size(); i++) {
            ArrayList newList = (ArrayList) baseList.clone();
            newList.remove(i);
            if (newList.size() > numOfString) {
                writeToArray(newList, numOfPositionInArray + 1, getFactorial(size - 1) * (LastSizeOfSet + i), (LastSizeOfSet + i) * (size - 1), numOfString, lists);
            }
        }
    }

    private void writeToPosition(ArrayList list, int numOfPositionInArray, int sizeOfSet, int numOfString, ArrayList<ArrayList> lists) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int factorial = getFactorial(size - 1);
            for (int j = 0; j < factorial; j++) {
                ArrayList<ArrayList> arrayList = lists.get(numOfString);
                int place = (i * factorial + j + sizeOfSet);
                if (place % getFactorial(numOfString) == 0) {
                    arrayList.get(place / getFactorial(numOfString)).add(numOfPositionInArray, list.get(i));
                }
            }
        }
    }
}

