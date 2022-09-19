package restock;

public class RestockItem {
    String sku;
    int quantity;
    float lowestUnitCost;

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public Float getLowestUnitCost() {
        return lowestUnitCost;
    }

    public void setLowestUnitCost(Float unitCost) {
        if (lowestUnitCost == 0.0f) {
            this.lowestUnitCost = unitCost;
        } else {
            this.lowestUnitCost = Math.min(unitCost, lowestUnitCost);
        }
    }

    public float calculateRestockCost() {
        return getLowestUnitCost() * getQuantity();
    }
}
