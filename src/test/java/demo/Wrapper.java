package demo;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Wrapper extends BaseTest {

    public void writeText(ChromeDriver driver, By eleLocator, String passText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(eleLocator));
        WebElement element = driver.findElement(eleLocator);
        element.sendKeys(passText);
    }

    public void clickElement(ChromeDriver driver, By eleLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(eleLocator));
            WebElement element = driver.findElement(eleLocator);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTheRatingText(ChromeDriver driver, By eleLocator, double expectedRating) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(eleLocator));
        List<WebElement> elements = driver.findElements(eleLocator);
        int ratingUnder4 = 0;
        for (WebElement eachEle : elements) {
            double rating = Double.parseDouble(eachEle.getText());
            if (rating <= expectedRating) {
                ratingUnder4++;
            }
        }
        System.out.println("Printing the total item rating less then " + expectedRating + ": " + ratingUnder4);
        return ratingUnder4;
    }

    public ArrayList<String> getTheTitleOnDiscount(ChromeDriver driver, double expDiscount, By discountElement,
            By titleElement) {
        System.out.println("Getting the title based on discount flow");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleElement));
        List<WebElement> title = driver.findElements(titleElement);
        List<WebElement> discount = driver.findElements(discountElement);
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < title.size(); i++) {
            String titleString = title.get(i).getText();
            String discountString = discount.get(i).getText();
            discountString = removeSubString(discountString, "% off");
            float discountInFloat = 0;
            try {

                discountInFloat = Float.parseFloat(discountString);

                if (discountInFloat > expDiscount) {
                    result.add(titleString + " discount of the product " + discountInFloat);
                }

            } catch (NumberFormatException e) {
                // TODO: handle exception
                System.out.println("Invalid rating format for " + titleString);
            }
        }
        return result;

    }

    public String removeSubString(String originalString, String stringToRemove) {
        if (originalString.contains(stringToRemove)) {

            return originalString.replace(stringToRemove, "").trim();
        }
        return originalString;
    }

    public void ScrollToTheElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public ArrayList<String> getTheTopReviewItem(ChromeDriver driver, By titles, By reviews) {

        System.out.println("Getting the title and image of top 5 reviews");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(titles));
        List<WebElement> titleElements = driver.findElements(titles);
        List<WebElement> reviewElements = driver.findElements(reviews);

        // Maps to hold titles and their corresponding review counts
        Map<String, Double> itemsMap = new HashMap<>();
        for (int i = 0; i < titleElements.size(); i++) {
            String titleString = titleElements.get(i).getText();
            String reviewString = reviewElements.get(i).getText().replaceAll("[^0-9.]", ""); // Remove non-digit
                                                                                             // characters

            try {
                double reviewCount = Double.parseDouble(reviewString);
                itemsMap.put(titleString, reviewCount);
            } catch (NumberFormatException e) {
                System.err.println("Skipping element due to exception: " + e.getMessage());
            }
        }

        // Extract entries from the map and sort them
        List<Map.Entry<String, Double>> itemList = new ArrayList<>(itemsMap.entrySet());
        Collections.sort(itemList, (entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        // Prepare the result list
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map.Entry<String, Double> item = itemList.get(i);
            result.add(item.getKey() + " - Review count: " + item.getValue());
        }

        return result;
    }
}
