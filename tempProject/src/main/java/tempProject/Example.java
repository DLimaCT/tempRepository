package tempProject;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.chrome.ChromeDriver;

public class Example {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", new File(Example.class.getClassLoader().getResource("Drivers/chromedriver").getFile()).getAbsolutePath());
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.computest.nl/nl/werken-bij-computest/direct-solliciteren/";

        driver.get(baseUrl);

        WebElement elementToTest = driver.findElement(By.xpath("/html/body/main/div/div/article/div[2]/form/div[1]/input"));

        Actions actions = new Actions(driver);

        //-----
        //MIN/MAX LENGTH TEST

        int maxLength = Integer.valueOf(elementToTest.getAttribute("maxlength"));
        System.out.println("Max Length: " + maxLength);

        elementToTest.clear();
        elementToTest.sendKeys(getAlphaNumericString(maxLength + 1) + Keys.ENTER);

        int actualLength = elementToTest.getAttribute("value").length();
        System.out.println("Actual Length: " + actualLength);
        System.out.println("\n");

        if(actualLength > maxLength){
            System.out.println("Actual Length is bigger than Max Length");
            System.out.println("Max Length Test Failed.");
        } else {
            System.out.println("Actual Length is smaller than or equal to Max Length");
            System.out.println("Max Length Test Passed.");
        }

        elementToTest.clear();

        driver.quit();

    }
    static String getAlphaNumericString(int n)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "abcdefghijklmnopqrstuvxyz"
                                    + "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());

             sb.append(AlphaNumericString
                          .charAt(index));
        }

        return sb.toString();

    }
}