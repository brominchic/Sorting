import java.util.List;

public interface Sorter {
    <T> List<List<T>> getArrays(List<T> baseList);
}
