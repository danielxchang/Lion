package inventory;

import item.DistributorItem;

public class DistributorInventory extends Inventory<DistributorItem> {
    private String name;
    public DistributorInventory(String distributorName) {
        setName(distributorName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToInventory(DistributorItem item) {
        this.items.put(item.getSku(), item);
    }

    public boolean hasItem(String sku) {
        DistributorItem item = getItem(sku);
        return item != null;
    }

    public Float getUnitCost(String sku) {
        DistributorItem item = getItem(sku);
        return item.getUnitCost();
    }
}
