package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DistributorItem extends Item {
    float unitCost;

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
                    int sku = (int) Float.parseFloat(data.get(i));
                    setSku(Integer.toString(sku));
                case 2:
                    float cost = Float.parseFloat(data.get(i));
                    setUnitCost(cost);
                default:
            }
        }
    }

    public String toString() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("sku", getSku());
        data.put("cost", getUnitCost());
        return data.toString();
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }
}
