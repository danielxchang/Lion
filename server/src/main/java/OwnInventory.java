public class OwnInventory extends Inventory<InventoryItem> {
    int lowStockTrigger = 25;

    public void addToInventory(InventoryItem item) {
        this.items.put(item.getSku(), item);
    }
    public int getLowStockTrigger() {
        return lowStockTrigger;
    }

    public void setLowStockTrigger(int lowStockTrigger) {
        this.lowStockTrigger = lowStockTrigger;
    }
}
