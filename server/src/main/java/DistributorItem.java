import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DistributorItem extends Item {
    String unitCost;

    public DistributorItem(ArrayList<String> data) {
        super(data);
    }

    public void setAttributes(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            switch (i) {
                case 0:
                    setName(data.get(i));
                    break;
                case 1:
                    int sku = (int) Double.parseDouble(data.get(i));
                    setSku(Integer.toString(sku));
                case 2:
                    setUnitCost(data.get(i));
                default:
            }
        }
    }

    public String toString() {
        Map<String, String> data = new HashMap<>();
        data.put("name", getName());
        data.put("sku", getSku());
        data.put("cost", getUnitCost());
        return data.toString();
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }
}
