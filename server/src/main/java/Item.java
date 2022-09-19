import java.util.ArrayList;

public class Item {
    protected String name;
    protected String sku;

    public Item(ArrayList<String> data) {
        setAttributes(data);
    }

    public void setAttributes(ArrayList<String> data) {}

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
