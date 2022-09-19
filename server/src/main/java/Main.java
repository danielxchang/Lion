import java.util.ArrayList;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
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
            ArrayList<InventoryItem> inventoryData = wbReader.getInventoryData("Inventory");
            OwnInventory inventory = new OwnInventory();
            for (InventoryItem item: inventoryData) {
                inventory.addToInventory(item);
            }

            for (InventoryItem item: inventory.getItems().values()) {
                if (item.isLowStock(inventory.getLowStockTrigger())) {
                    // TODO: Replace print code with code returning JSON
                    System.out.println(item);
                }
            }
            return null;
        });

        //TODO: Return JSON containing the total cost of restocking candy
        post("/restock-cost", (request, response) -> {
            ArrayList<DistributorInventory> distributors= wbReader.getDistributorData("Distributors");
            // TODO: Implement code to process restock request data and calculate restock cost
            System.out.println(distributors);
            return null;
        });

    }
}
