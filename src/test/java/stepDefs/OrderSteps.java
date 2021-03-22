package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePage;
import pageObjects.ProductPage;

import java.util.concurrent.TimeUnit;

public class OrderSteps {
    WebDriver driver;
    WebDriverWait wait;
    String orderRef;
    HomePage homePage;
    ProductPage productPage;

    @Given("^user is on HomePage$")
    public void user_is_on_home_page() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        homePage.navigateTo_HomePage();
    }

    @When("^user search for \"([^\"]*)\"$")
    public void he_search_for(String string) {
        wait=new WebDriverWait(driver, 20);
        homePage.perform_Search(string);
    }

    @When("Choose to buy the second item")
    public void choose_to_buy_the_second_item() {
        productPage = new ProductPage(driver);
        productPage.MoveToEle();
    }

    @When("Adds the item to cart")
    public void moves_to_checkout_from_minicart() {
        productPage.AddtoCart();
    }

    @When("^Proceeds to checkout and Sign in to website using \\\"(.*)\\\" and \\\"(.*)\\\"$")
    public void enter_personal_details_on_checkoutpage(String usname, String pwd) {
        driver.findElement(By.xpath("//span[text()=\"Proceed to checkout\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(usname);
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys(pwd);
        driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();

    }
    @When("Proceeds to checkout and Check terms of Service")
    public void select_same_delivery_address() {
        driver.findElement(By.xpath("//span[text()=\"Proceed to checkout\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();

    }
    @When("select payment method and places the order")
    public void select_payment_method() {
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
        orderRef = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div")).getText();
        System.out.println(orderRef);
    }
    @When("verify the order in order history")
    public void verify_order() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        driver.findElement(By.xpath("//span[text()='Order history and details']")).click();
        Assert.assertTrue(orderRef.contains(driver.findElement(By.xpath("//*[@id=\"order-list\"]/tbody/tr[1]/td[1]/a")).getText()));
        driver.close();
    }

    @Given("user logged in to the website using \\\"(.*)\\\" and \\\"(.*)\\\"$")
    public void user_already_logged_in_to_the_website(String usname, String pwd) {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(usname);
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys(pwd);
        driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();

    }
    @When("navigates to personal info page")
    public void navigates_to_personal_info_page() {
        driver.findElement(By.xpath("//span[text()='My personal information']")).click();
    }
    @When("updates name")
    public void updates_name() {
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "UpdatedName");
        driver.findElement(By.xpath("//*[@id=\"old_passwd\"]")).sendKeys("janardhan@123");
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/form/fieldset/div[11]/button")).click();

    }
}
