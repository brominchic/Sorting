package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws CloneNotSupportedException {
        ArrayList list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        SetSorter setSorter= new SetSorter();
        System.out.println(setSorter.getArrays(list));

    }
}
