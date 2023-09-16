import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KirillSorterTest {

    Sorter sorter = new KirillSorter();

    @Test
    void test3() {
        List<List<Integer>> expected = List.of(
                List.of(1),
                List.of(2),
                List.of(3),
                List.of(1, 2),
                List.of(1, 3),
                List.of(2, 3),
                List.of(1, 2, 3)
        );

        var result = sorter.getArrays(List.of(1, 2, 3));

        System.out.println(result);
        assertEquals(expected.size(), result.size());

        for (List<Integer> integers : expected) {
            assertTrue(result.contains(integers), "expected should contain " + integers);
        }

    }

    @Test
    void test4() {
        List<List<Integer>> expected = List.of(
                List.of(1),
                List.of(2),
                List.of(3),
                List.of(4),
                List.of(1, 2),
                List.of(1, 3),
                List.of(1, 4),
                List.of(2, 3),
                List.of(2, 4),
                List.of(3, 4),
                List.of(1, 2, 3),
                List.of(1, 2, 4),
                List.of(1, 3, 4),
                List.of(2, 3, 4),
                List.of(1, 2, 3, 4)
        );

        var result = sorter.getArrays(List.of(1, 2, 3, 4));

        System.out.println(result);
        assertEquals(expected.size(), result.size());

        for (List<Integer> integers : expected) {
            assertTrue(result.contains(integers), "expected should contain " + integers);
        }

    }

}
