package blob.server.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Table<F, S, T> {

    private final ConcurrentHashMap<F ,ConcurrentHashMap<S ,T>> data = new ConcurrentHashMap();

    public T getValue(F row, S column) {
        return this.data.computeIfAbsent(row, (key) -> (new ConcurrentHashMap<>())).computeIfAbsent(column, (val) -> (null));
    }

    public void setValue(F row, S column, T value) {
        this.data.computeIfAbsent(row, (key) -> (new ConcurrentHashMap<>())).put(column, value);
    }

}
