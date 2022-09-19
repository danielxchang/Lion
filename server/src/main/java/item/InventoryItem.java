package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryItem extends Item {
    private int stock;
    private int capacity;

    public InventoryItem(ArrayList<String> data) {
        super(data);
    }

    public void setAttributes(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            switch (i) {
                case 0:
                    setName(data.get(i));
                    break;
                case 1:
                    int stock = (int) Float.parseFloat(data.get(i));
                    setStock(stock);
                    break;
                case 2:
                    int capacity = (int) Float.parseFloat(data.get(i));
                    setCapacity(capacity);
                    break;
                case 3:
                    int sku = (int) Float.parseFloat(data.get(i));
                    setSku(Integer.toString(sku));
                default:
            }
        }
    }

    public String toString() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("stock", getStock());
        data.put("capacity", getCapacity());
        data.put("sku", getSku());
        return data.toString();
    }

    public int getStock() {
        return stock;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isLowStock(int trigger) {
        float stock = getStock();
        int capacity = getCapacity();
        return (stock / capacity) * 100 < trigger;
    }
}
