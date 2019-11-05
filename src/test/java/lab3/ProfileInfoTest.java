package lab3;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProfileInfoTest {

    private Util util;
    private ChromeDriver chromeDriver;
    private FirefoxDriver firefoxDriver;

    @Before
    public void setUp() throws Exception {
        util = new Util();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        chromeDriver = new ChromeDriver(capabilities);
//        FirefoxOptions opts = new FirefoxOptions();
//        opts.addArguments("-private");
//        firefoxDriver = new FirefoxDriver();
//        chromeDriver = new ChromeDriver();

//        util.prepare(firefoxDriver);
        util.prepare(chromeDriver);
//        util.doLogin(firefoxDriver, "testAcc558@yandex.ru", "testAcc558");
        util.doLogin(chromeDriver, "testAcc558@yandex.ru", "testAcc558");
    }

    @After
    public void tearDown() {
//        firefoxDriver.quit();
        chromeDriver.quit();
    }

    private void openProfile(WebDriver driver) throws InterruptedException {
        driver.findElement(By.className("fit-avatar-image")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/nav/ul/li[5]/div/aside/ul/li/a")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[3]/div[2]/section[2]/div[2]/div[2]/a")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/div/div/div/a")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/div/div/div[2]/a")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/div/div/div[2]/a/button")).click();
        Thread.sleep(300);
        firefoxDriver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/div/div/div/a")).click();

    }

    private void fillingWithAllEmpty(WebDriver driver) throws Exception{
        Thread.sleep(1000);
        openProfile(driver);
        Thread.sleep(300);
        driver.findElement(By.xpath("//div[2]/div/div/div[2]/button")).click();
        Thread.sleep(300);
    }

    @Test
    public void allFieldsAreEmpty_TestFirefox() throws Exception {
        fillingWithAllEmpty(firefoxDriver);
//        WebElement cookie = firefoxDriver.findElement(By.xpath("//div[@id='react-cookie-approval']/div/a"));
//        if (cookie.getSize().getWidth() != 0) cookie.click();
        Assert.assertEquals("https://www.fiverr.com/seller_onboarding/personal_info", firefoxDriver.getCurrentUrl());
    }

    @Test
    public void allFieldsAreEmpty_TestChrome() throws Exception {
//        if (chromeDriver.findElement(By.xpath("//div[@id='react-cookie-approval']/div/a")) != null) {
//            chromeDriver.findElement(By.xpath("//div[@id='react-cookie-approval']/div/a")).click();
//        }
        fillingWithAllEmpty(chromeDriver);
        Assert.assertEquals("https://www.fiverr.com/seller_onboarding/personal_info", chromeDriver.getCurrentUrl());
    }
}
