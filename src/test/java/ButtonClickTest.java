import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ButtonClickTest {
    @Test (testName = "ButtonClickTest")
    public void testButtonClick() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/elements");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@id='item-4']/span[text()=\"Buttons\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"mt-4\"]/button[text()='Click Me']\n")).click();
        Thread.sleep(2000);
        String fetchedText = driver.findElement(By.xpath("//p[@id='dynamicClickMessage']")).getText();
        Assert.assertEquals(fetchedText, "You have done a dynamic click");
        System.out.println("The fetched text is: " + fetchedText);
        driver.quit();
    }
}

