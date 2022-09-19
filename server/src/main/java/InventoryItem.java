import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryItem extends Item {
    private String stock;
    private String capacity;

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
                    setStock(data.get(i));
                    break;
                case 2:
                    setCapacity(data.get(i));
                    break;
                case 3:
                    int sku = (int) Double.parseDouble(data.get(i));
                    setSku(Integer.toString(sku));
                default:
            }
        }
    }

    public String toString() {
        Map<String, String> data = new HashMap<>();
        data.put("name", getName());
        data.put("stock", getStock());
        data.put("capacity", getCapacity());
        data.put("sku", getSku());
        return data.toString();
    }

    public String getStock() {
        return stock;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public boolean isLowStock(int trigger) {
        double stock = Double.parseDouble(getStock());
        double capacity = Double.parseDouble(getCapacity());
        return (stock / capacity) * 100 < trigger;
    }
}
