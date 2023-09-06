import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class FillingTheFormTest {

    Map<String, String> originalDataSet = new HashMap<>()
    {
        {
            put("firstName", "Adam");
            put("lastName", "Johns");
            put("emailAddress", "adam.johns@test.com");
            put("age", "25");
            put("salary", "25000");
            put("department", "Test Department");
        }
    };

    Map<String, String> updatedDataSet = new HashMap<>()
    {
        {
            put("firstName", "Mary");
            put("lastName", "Smith");
            put("emailAddress", "mary.smith@test.com");
            put("age", "31");
            put("salary", "150000");
            put("department", "Test Update Department");
        }
    };


    @Test (testName = "FillingTheFormTest")
    public void testFillingTheForm() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");

        //Opening The Form For Adding New Entry

        driver.findElement(By.xpath("//button[@id='addNewRecordButton']")).click();

        // Finding Web Elements On The Form

        WebElement firstName = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-6 col-sm-6\"]//input[@id='firstName']"));
        WebElement lastName = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-6 col-sm-6\"]//input[@id='lastName']"));
        WebElement emailAddress = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-9 col-sm-12\"]//input[@id='userEmail']"));
        WebElement age = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-2 col-sm-12\"]//input[@id='age']"));
        WebElement salary = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-9 col-sm-12\"]//input[@id='salary']"));
        WebElement department = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-6 col-sm-6\"]//input[@id='department']"));

        //Setting Values On The Form

        firstName.clear();
        firstName.sendKeys(originalDataSet.get("firstName"));

        lastName.clear();
        lastName.sendKeys(originalDataSet.get("lastName"));

        emailAddress.clear();
        emailAddress.sendKeys(originalDataSet.get("emailAddress"));

        age.clear();
        age.sendKeys(originalDataSet.get("age"));

        salary.clear();
        salary.sendKeys(originalDataSet.get("salary"));

        department.clear();
        department.sendKeys(originalDataSet.get("department"));

        //Submitting The Data Entered

        driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//button[@id='submit']")).submit();

        Thread.sleep(2000);

        //Assertion Part

        List<String> expectedDataInTheGrid = Arrays.asList(
                originalDataSet.get("firstName"),
                originalDataSet.get("lastName"),
                originalDataSet.get("age"),
                originalDataSet.get("emailAddress"),
                originalDataSet.get("salary"),
                originalDataSet.get("department")
        );
        WebElement firstNameInTheGrid = driver.findElement(By.xpath(String.format("//div[text()=\"%s\"]", originalDataSet.get("firstName"))));
        List<WebElement> dataInTheGrid = driver.findElements(By.xpath(String.format("//div[text()=\"%s\"]/following-sibling::*", originalDataSet.get("firstName"))));
        List<String> actualDataInTheGrid = new ArrayList<>();
        actualDataInTheGrid.add(firstNameInTheGrid.getText());

        for (WebElement webElement : dataInTheGrid) {
            actualDataInTheGrid.add(webElement.getText());
        }
        actualDataInTheGrid.remove(actualDataInTheGrid.size() - 1);

        System.out.println("*** Original Data In The Grid ***");

        for (String s : actualDataInTheGrid) {
            System.out.println(s);
        }

        Assert.assertEquals(actualDataInTheGrid, expectedDataInTheGrid, "There is an error in the user data");

        //Calling The Edit Mode For The Data Entered and Updating The Data

        driver.findElement(By.xpath("//div[@class=\"rt-table\"]/div[@class=\"rt-tbody\"]/div[@class=\"rt-tr-group\"]//div[@class=\"rt-td\"]/div[@class=\"action-buttons\"]/span[@id='edit-record-4']")).click();

        WebElement firstNameUPD = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-6 col-sm-6\"]//input[@id='firstName']"));
        WebElement lastNameUPD = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-6 col-sm-6\"]//input[@id='lastName']"));
        WebElement emailAddressUPD = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-9 col-sm-12\"]//input[@id='userEmail']"));
        WebElement ageUPD = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-2 col-sm-12\"]//input[@id='age']"));
        WebElement salaryUPD = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-9 col-sm-12\"]//input[@id='salary']"));
        WebElement departmentUPD = driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//div[@class=\"col-md-6 col-sm-6\"]//input[@id='department']"));

        firstNameUPD.clear();
        firstNameUPD.sendKeys(updatedDataSet.get("firstName"));

        lastNameUPD.clear();
        lastNameUPD.sendKeys(updatedDataSet.get("lastName"));

        emailAddressUPD.clear();
        emailAddressUPD.sendKeys(updatedDataSet.get("emailAddress"));

        ageUPD.clear();
        ageUPD.sendKeys(updatedDataSet.get("age"));

        salaryUPD.clear();
        salaryUPD.sendKeys(updatedDataSet.get("salary"));

        departmentUPD.clear();
        departmentUPD.sendKeys(updatedDataSet.get("department"));

        //Submitting The Data Entered

        driver.findElement(By.xpath("//div[@class=\"fade modal show\"]//div[@class=\"modal-content\"]/div[@class=\"modal-body\"]/form//button[@id='submit']")).submit();

        Thread.sleep(2000);

        //Assertion Part

        List<String> expectedDataInTheGridUPD = Arrays.asList( updatedDataSet.get("firstName"),
                updatedDataSet.get("lastName"),
                updatedDataSet.get("age"),
                updatedDataSet.get("emailAddress"),
                updatedDataSet.get("salary"),
                updatedDataSet.get("department")
        );

        WebElement firstNameInTheGridUPD = driver.findElement(By.xpath(String.format("//div[text()=\"%s\"]", updatedDataSet.get("firstName"))));
        List<WebElement> dataInTheGridUPD = driver.findElements(By.xpath(String.format("//div[text()=\"%s\"]/following-sibling::*", updatedDataSet.get("firstName"))));

        List<String> actualDataInTheGridUPD = new ArrayList<>();
        actualDataInTheGridUPD.add(firstNameInTheGridUPD.getText());

        for (WebElement webElement : dataInTheGridUPD) {
            actualDataInTheGridUPD.add(webElement.getText());
        }
        actualDataInTheGridUPD.remove(actualDataInTheGridUPD.size() - 1);

        System.out.println("*** Updated Data In The Grid ***");

        for (String s : actualDataInTheGridUPD) {
            System.out.println(s);
        }

        Assert.assertEquals(actualDataInTheGridUPD, expectedDataInTheGridUPD, "There is an error in the user data");

        driver.quit();
    }
}