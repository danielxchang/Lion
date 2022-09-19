package restock;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestockRequest {
    @SerializedName("items")
    ArrayList<RestockItem> items;

    float totalCost;

    public ArrayList<RestockItem> getItems() {
        return items;
    }

    public void calculateTotalRestock() {
        for (RestockItem item : items) {
            totalCost += item.calculateRestockCost();
        }
    }
}
