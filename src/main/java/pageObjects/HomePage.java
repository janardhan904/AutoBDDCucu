package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void perform_Search(String search) {
        driver.findElement(By.id("search_query_top")).sendKeys(search);
        driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
    }

    public void navigateTo_HomePage() {
        driver.get("http://automationpractice.com/index.php");
    }
}
