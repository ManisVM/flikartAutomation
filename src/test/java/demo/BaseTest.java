package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public static ChromeDriver driver;
    @BeforeSuite
    public void launchBrowser(){
        System.out.println("Launching Browser");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterSuite
    public void closeBrowser(){
        System.out.println("Closing Browser");
        driver.quit();
    }





}
