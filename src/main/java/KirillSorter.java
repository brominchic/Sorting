import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KirillSorter implements Sorter {


    @Override
    public <T> List<List<T>> getArrays(List<T> baseList) {

        List<List<T>> result = new ArrayList<>();

        Queue<T> elements = new LinkedList<>(baseList);

        while (!elements.isEmpty()) {
            var current = elements.remove();
            List<List<T>> tmp = new ArrayList<>(result);

            result.add(List.of(current));
            for (List<T> existing : tmp) {

                List<T> newCombination = new ArrayList<>(existing);
                newCombination.add(current);

                result.add(newCombination);
            }

        }

        return result;
    }
}
