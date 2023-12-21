package main.java.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.base.BaseTest.log;

public class TableVerifier {
        public static void verifyColumnSorting(WebElement header, List<WebElement> tableRows, int columnIndex) throws InterruptedException {
            Thread.sleep(500);
            header.click();

            List<String> columnData = new ArrayList<>();
            for (WebElement row : tableRows) {
                List<WebElement> rowData = row.findElements(By.xpath(".//div[@role='gridcell' and contains(@class, 'rt-td')][" + columnIndex + "]"));
                if (rowData.size() > 0 && !rowData.get(0).getText().isEmpty()) {
                    columnData.add(rowData.get(0).getText().trim());
                }
            }
            columnData.removeIf(String::isEmpty);

            List<String> sortedColumnData;

            boolean isNumeric = columnData.stream().allMatch(s -> s.matches("\\d+"));

            if (isNumeric) {
                List<Integer> numericData = columnData.stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                List<Integer> sortedNumericData = new ArrayList<>(numericData);
                Collections.sort(sortedNumericData);
                sortedColumnData = sortedNumericData.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList());
            } else {
                sortedColumnData = new ArrayList<>(columnData);
                Collections.sort(sortedColumnData);
            }

            log.info("Sorted 'Column " + columnIndex + "' Data: " + sortedColumnData);

            boolean isColumnSorted = columnData.equals(sortedColumnData);
            Assert.assertTrue(isColumnSorted, "Column " + columnIndex + " is not sorted correctly.");
        }
    }
