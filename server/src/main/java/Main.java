import com.google.gson.Gson;
import inventory.DistributorInventory;
import inventory.OwnInventory;
import item.InventoryItem;
import restock.RestockItem;
import restock.RestockRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static <string> void main(String[] args) throws IOException {
        Gson gson = new Gson();
        WorkbookReader wbReader = new WorkbookReader();

        //This is required to allow GET and POST requests with the header 'content-type'
        options("/*",
                (request, response) -> {
                        response.header("Access-Control-Allow-Headers",
                                "content-type");

                        response.header("Access-Control-Allow-Methods",
                                "GET, POST");


                    return "OK";
                });

        //This is required to allow the React app to communicate with this API
        before((request, response) -> response.header("Access-Control-Allow-Origin", "http://localhost:3000"));

        //TODO: Return JSON containing the candies for which the stock is less than 25% of it's capacity
        get("/low-stock", (request, response) -> {
            response.type("application/json");

            // Retrieve inventory data from workbook
            ArrayList<InventoryItem> inventoryData = wbReader.getInventoryData("Inventory");
            OwnInventory inventory = new OwnInventory();
            for (InventoryItem item: inventoryData) {
                inventory.addToInventory(item);
            }

            // Add all low stock items below 25% mark to ArrayList
            ArrayList<InventoryItem> lowStockItems = new ArrayList<>();
            for (InventoryItem item: inventory.getItems().values()) {
                if (item.isLowStock(inventory.getLowStockTrigger())) {
                    lowStockItems.add(item);
                }
            }

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("items", lowStockItems);
            return gson.toJson(responseData);
        });

        //TODO: Return JSON containing the total cost of restocking candy
        post("/restock-cost", (request, response) -> {
            response.type("application/json");
            RestockRequest data = gson.fromJson(request.body(), RestockRequest.class);

            // Get distributor data from workbook
            ArrayList<DistributorInventory> distributors = wbReader.getDistributorData("Distributors");
            ArrayList<RestockItem> restockItems = data.getItems();

            // check each distributor for restock items
            for (DistributorInventory distributor: distributors) {
                // update restock item's lowestUnitCost property if distributor has in stock
                for (RestockItem item: restockItems) {
                    String sku = item.getSku();
                    if (distributor.hasItem(sku)) {
                        float unitCost = distributor.getUnitCost(sku);
                        item.setLowestUnitCost(unitCost);
                    }
                }
            }

            // calculate total restock cost and store total as property on data restock.RestockRequest object
            data.calculateTotalRestock();
            return gson.toJson(data);
        });

    }
}
