package demo;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TestCases extends BaseTest{    

    
    @Test(enabled = false)
    public void testCase01() throws InterruptedException{
        System.out.println("Washing Machine rating test case");
        driver.get("https://www.flipkart.com/");
        Wrapper wrapper = new Wrapper();
        wrapper.writeText(driver, By.xpath("//input[@placeholder='Search for Products, Brands and More']"), "Washing Machine");
        driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).submit();
        wrapper.clickElement(driver, By.xpath("//div[text()='Popularity']"));
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
        wrapper.getTheRatingText(driver, By.xpath("//span[contains (@id,'productRating_')]/child::div"), 4.0);
    }

    //a[@rel='noopener noreferrer']//span[contains(text(),'off')]

    //a[@rel='noopener noreferrer']//div[contains(text(),'Apple')]

    //*[contains(text(), 'Ratings') and not (./*) and not (contains(text(),'Customer'))]/../../../../div[1]

    @Test(enabled = false)
    public void testCase02() throws InterruptedException{
        System.out.println("Search iphone with 17%");
        driver.get("https://www.flipkart.com/");    
        Wrapper wrapper = new Wrapper();
        wrapper.writeText(driver, By.xpath("//input[@placeholder='Search for Products, Brands and More']"), "iphone");
        driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).submit();
        ArrayList<String> flow2= wrapper.getTheTitleOnDiscount(driver, 17, By.xpath("//a[@rel='noopener noreferrer']//div[@class='UkUFwK']/span[contains(text(),'off')]"), By.xpath("//*[contains(text(), 'Ratings') and not (./*) and not (contains(text(),'Customer'))]/../../../../div[1]"));
        Thread.sleep((new Random().nextInt(3)+2) *1000);
        for (String reslutfromFlow : flow2) {
            System.out.println(reslutfromFlow);
            
        }
   
    }

   

    //a[@rel='noopener noreferrer']//child::div/following::span[@class="Wphh3N"] rating 
    //a[@class='wjcEIp'] 

    @Test
    public void testCase03  () throws InterruptedException{
        System.out.println("Search coffee mug with highest rating");
        driver.get("https://www.flipkart.com/");    
        Wrapper wrapper = new Wrapper();
        wrapper.writeText(driver, By.xpath("//input[@placeholder='Search for Products, Brands and More']"), "Coffee Mug");
        driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).submit();
        Thread.sleep((new Random().nextInt(3)+2)*1000);
        WebElement checkBox=  driver.findElement(By.xpath("(//input[@type='checkbox']//parent::label//child::div[@class='XqNaEv'])[1]"));
        checkBox.click();
        Thread.sleep((new Random().nextInt(3)+2)*1000);
        ArrayList<String> flow3=wrapper.getTheTopReviewItem(driver, By.xpath("//a[@class='wjcEIp']"), By.xpath("//a[@rel='noopener noreferrer']//child::div/following::span[@class='Wphh3N']"));
        for (String ele : flow3) {
            System.out.println(ele);
            
        }

   
    }




}
