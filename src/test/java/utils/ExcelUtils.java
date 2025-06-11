package utils;

import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {

    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Map<String, String> rowData = new HashMap<>();
                Row row = sheet.getRow(i);

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = row.getCell(j).getStringCellValue();
                    rowData.put(key, value);
                }

                testData.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return testData;
    }
}

