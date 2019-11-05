package lab3;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchTest {

    private static Util util;
    private static ChromeDriver chromeDriver;
    private static FirefoxDriver firefoxDriver;

    @BeforeClass
    public static void setUp() throws InterruptedException {
        util = new Util();

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("incognito");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        chromeDriver = new ChromeDriver(capabilities);
//        chromeDriver = new ChromeDriver();

//        FirefoxOptions opts = new FirefoxOptions();
//        opts.addArguments("-private");
//        firefoxDriver = new FirefoxDriver(opts);
        firefoxDriver = new FirefoxDriver();

//        util.prepare(chromeDriver);
        util.prepare(firefoxDriver);
//        util.doLogin(chromeDriver, "testAcc556@yandex.ru", "testAcc556");
        util.doLogin(firefoxDriver, "testAcc558@yandex.ru", "testAcc558");
    }

    @AfterClass
    public static void tearDown() {
//        chromeDriver.quit();
        firefoxDriver.quit();
    }

    @Test
    public void validSearch_TestFirefox() {
        firefoxDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input")).click();
        firefoxDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input"))
                .sendKeys("Android");
        firefoxDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/button")).click();
        String searchResults = firefoxDriver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/div/div/div/div/span")).getText();
        Assert.assertTrue(searchResults.contains("Results for \"Android\""));
    }

    @Test
    public void invalidSearch_TestFirefox() throws Exception{
        String randomRequest = util.getRandomString(20);
        firefoxDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input")).click();
        firefoxDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input"))
                .sendKeys(randomRequest);
        firefoxDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/button")).click();
        String searchResults = firefoxDriver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/" +
                "div/div/div/h3")).getText();
        System.out.println(searchResults);
        Assert.assertTrue(searchResults.contains("No Services"));
    }

    @Test
    public void validSearch_TestChrome() {
        chromeDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input")).click();
        chromeDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input"))
                .sendKeys("Android");
        chromeDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/button")).click();
        String searchResults = chromeDriver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/div/div/div/div/span")).getText();
        Assert.assertTrue(searchResults.contains("Results for \"Android\""));
    }

    @Test
    public void invalidSearch_TestChrome() throws Exception{
        String randomRequest = util.getRandomString(20);
        chromeDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input")).click();
        chromeDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/input"))
                .sendKeys(randomRequest);
        chromeDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/div/div/form/button")).click();
        String searchResults = chromeDriver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/" +
                "div/div/div/h3")).getText();
        System.out.println(searchResults);
        Assert.assertTrue(searchResults.contains("No Services"));
    }
}
