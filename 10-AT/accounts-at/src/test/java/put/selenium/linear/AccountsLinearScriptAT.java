package put.selenium.linear;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;

import static org.junit.Assert.*;


public class AccountsLinearScriptAT {

    private WebDriver driver;

    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
            new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.driver = new ChromeDriver();
        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
        driver.get("http://localhost:8080/accounts/controller");
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("ja");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("repeat_password")).click();
        driver.findElement(By.name("repeat_password")).clear();
        driver.findElement(By.name("repeat_password")).sendKeys("123");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("Mateusz Test");
        driver.findElement(By.name("addressData")).click();
        driver.findElement(By.name("addressData")).clear();
        driver.findElement(By.name("addressData")).sendKeys("Poznan");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.xpath("//body")).click();
        assertEquals("Login", driver.findElement(By.xpath("//div[@id='contentSingle']/h3")).getText());
        assertEquals("username:", driver.findElement(By.xpath("//div[@id='contentSingle']/form/table/tbody/tr/td")).getText());
        assertEquals("password:", driver.findElement(By.xpath("//div[@id='contentSingle']/form/table/tbody/tr[3]/td")).getText());
    }


}