import inventory.DistributorInventory;
import item.DistributorItem;
import item.InventoryItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WorkbookReader {
    public WorkbookReader() {}

    private Workbook readExcel(String fileName) throws IOException {
        File file = new File("resources/" + fileName + ".xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        return new XSSFWorkbook(fileInputStream);
    }

    public ArrayList<InventoryItem> getInventoryData(String fileName) throws IOException {
        Workbook workbook = readExcel(fileName);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<InventoryItem> data = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            ArrayList<String> record = new ArrayList<>();
            for (Cell cell : row) {
                record.add(String.valueOf(cell));
            }
            InventoryItem item = new InventoryItem(record);
            data.add(item);
        }
        return data;
    }

    public ArrayList<DistributorInventory> getDistributorData(String fileName) throws IOException {
        ArrayList<DistributorInventory> distributors = new ArrayList<>();
        Workbook workbook = readExcel(fileName);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String distributorName = sheet.getSheetName();
            DistributorInventory distributorInventory = new DistributorInventory(distributorName);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                ArrayList<String> record = new ArrayList<>();
                for (Cell cell : row) {
                    record.add(String.valueOf(cell));
                }
                DistributorItem item = new DistributorItem(record);
                if (item.getSku() != null ) {
                    distributorInventory.addToInventory(item);
                }
            }
            distributors.add(distributorInventory);
        }
        return distributors;
    }

}
