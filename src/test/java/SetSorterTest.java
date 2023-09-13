import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetSorterTest {
    SetSorter setSorter = new SetSorter();

    @Test
    void correctnessTest() throws CloneNotSupportedException {
        LinkedList list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List testList = setSorter.getArrays(list);
        ArrayList<ArrayList> arrayList = new ArrayList(7);
        arrayList.add(new ArrayList<>());
        arrayList.get(0).add(1);
        arrayList.add(new ArrayList<>());
        arrayList.get(1).add(2);
        arrayList.add(new ArrayList<>());
        arrayList.get(2).add(3);
        arrayList.add(new ArrayList<>());
        arrayList.get(3).add(1);
        arrayList.get(3).add(2);
        arrayList.add(new ArrayList<>());
        arrayList.get(4).add(1);
        arrayList.get(4).add(3);
        arrayList.add(new ArrayList<>());
        arrayList.get(5).add(2);
        arrayList.get(5).add(3);
        arrayList.add(new ArrayList<>());
        arrayList.get(6).add(1);
        arrayList.get(6).add(2);
        arrayList.get(6).add(3);
        LinkedList<ArrayList> correctList = new LinkedList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            correctList.add(arrayList.get(i));
        }
        assertTrue(testList.equals(correctList));
    }

    @Test
    void testForAllList() throws CloneNotSupportedException {
        LinkedList linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        assertTrue(setSorter.getArrays(linkedList).equals(setSorter.getArrays(arrayList)));
    }
}
