import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//tempmailaddress.com
//motaz.khrystian@plutocow.com
//qqbcu6B?
//change this as needed

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
        WebDriver Driver = new ChromeDriver();
        WebDriverWait Waiter;

        String Email; //randomly generated email address
        String Password; //I'd encrypt this if it wasn't a throwaway account/email.
        String OfferedPrice;
        String BookTitle;

        boolean SubtotalTextMatches;
        boolean AddedToCartTextExists;

        Driver.get("http://www.amazon.com");
        Driver.findElement(By.className("nav-a")).click();

        Email = "motaz.khrystian@plutocow.com";
        Password = "SuperSecurePassword";

        Waiter = new WebDriverWait(Driver,5);

        Waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        Driver.findElement(By.id("ap_email")).sendKeys(Email);
        Driver.findElement(By.id("ap_password")).sendKeys(Password);
        Driver.findElement(By.id("signInSubmit")).click();

        BookTitle = "Selenium Programming";

        Waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        Driver.findElement(By.id("twotabsearchtextbox")).sendKeys(BookTitle);
        Driver.findElement(By.className("nav-input")).click();

        Waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className("s-result-item")));
        Driver.findElement(By.className("aok-relative")).click();

        Waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
        OfferedPrice = Driver.findElement(By.className("offer-price")).getText();
        Driver.findElement(By.id("add-to-cart-button")).click();

        String CurrentSubtotalText = Driver.findElement(By.id("hlb-subcart")).findElement(By.className("hlb-price")).getText();

        AddedToCartTextExists = Driver.findElement(By.id("huc-v2-order-row-container")).findElement(By.id("huc-v2-order-row-confirm-text"))
                .findElement(By.className("a-text-bold")).getText() == "Added to Cart";
        SubtotalTextMatches = CurrentSubtotalText == OfferedPrice;

        Waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("huc-v2-order-row-container")));
        if(AddedToCartTextExists && SubtotalTextMatches ) {
            Driver.findElement(By.id("nav-item-signout")).click();
            Waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("a-form-label")));
        }
        else
            System.out.println("Something Bad Happened: " + CurrentSubtotalText + " " + OfferedPrice);
        Driver.quit();
    }
}
