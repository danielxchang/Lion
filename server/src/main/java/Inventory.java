import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {
    Map<String, T> items = new HashMap<>();

    public Map<String, T> getItems() {
        return items;
    }

    public String toString() {
        return items.toString();
    }
}
