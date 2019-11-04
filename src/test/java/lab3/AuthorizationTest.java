package lab3;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AuthorizationTest {
    private Util util;
    private ChromeDriver chromeDriver;
    private FirefoxDriver firefoxDriver;

    @Before
    public void setUp() throws Exception {
        util = new Util();
        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("-private");
        firefoxDriver = new FirefoxDriver(opts);

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("incognito");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        chromeDriver = new ChromeDriver(capabilities);

        util.prepare(firefoxDriver);
//        util.prepare(chromeDriver);
    }

    @After
    public void tearDown() {
        firefoxDriver.quit();
//        chromeDriver.quit();
    }

    private void doLogin(WebDriver driver, String login, String password) throws InterruptedException {
        util.tryClick(driver, By.xpath("(//a[contains(text(),'Sign In')])[7]"));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='user_session[login]']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='user_session[password]']")).sendKeys(password);
        driver.findElement(By.className("chk-img")).click();
        Thread.sleep(500);
        util.tryClick(driver, By.xpath("//input[@name='commit']"));
        Thread.sleep(1000);
    }

    private void doLogOut(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.className("fit-avatar-image")).click();
        Thread.sleep(500);
        driver.findElement(By.className("js-log-out")).click();
        Thread.sleep(1000);
    }

    @Test
    public void successfulEmailLogin() throws Exception {
        doLogin(firefoxDriver, util.getCorrectEmail(), util.getCorrectPassword());
        firefoxDriver.findElement(By.xpath("//span[@class='fit-avatar-image']"));
        Thread.sleep(500);

//        doLogin(chromeDriver, util.getCorrectEmail(), util.getCorrectPassword());
//        chromeDriver.findElement(By.xpath("//span[@class='fit-avatar-image']"));
        Thread.sleep(10000);
    }

    @Test
    public void successfulNameLogin() throws Exception {
        doLogin(firefoxDriver, util.getCorrectName(), util.getCorrectPassword());
        firefoxDriver.findElement(By.xpath("//span[@class='fit-avatar-image']"));
        Thread.sleep(500);

//        doLogin(chromeDriver, util.getCorrectName(), util.getCorrectPassword());
//        chromeDriver.findElement(By.xpath("//span[@class='fit-avatar-image']"));
        Thread.sleep(10000);
    }


    @Test
    public void wrongPasswordLogin() throws InterruptedException {
        doLogin(firefoxDriver, util.getCorrectEmail(), "wrong_pass");
        firefoxDriver.findElement(By.xpath("//p[contains(.,'Wrong username or password, please try again.')]"));
        Thread.sleep(500);

//        doLogin(chromeDriver, util.getCorrectEmail(), "wrong_pass");
//        chromeDriver.findElement(By.xpath("//p[contains(.,'Wrong username or password, please try again.')]"));
        Thread.sleep(10000);
    }


    @Test
    public void wrongLoginLogin() throws InterruptedException {
        doLogin(firefoxDriver, "wrong_login", util.getCorrectPassword());
        firefoxDriver.findElement(By.xpath("//p[contains(.,'Wrong username or password, please try again.')]"));
        Thread.sleep(500);

//        doLogin(chromeDriver, "wrong_login",  util.getCorrectPassword());
//        chromeDriver.findElement(By.xpath("//p[contains(.,'Wrong username or password, please try again.')]"));
        Thread.sleep(10000);
    }

    @Test
    public void logOut() throws Exception {
        doLogin(firefoxDriver, util.getCorrectEmail(), util.getCorrectPassword());
        doLogOut(firefoxDriver);
        Assert.assertEquals("https://www.fiverr.com/", firefoxDriver.getCurrentUrl());
        Thread.sleep(500);

//        doLogin(chromeDriver, util.getCorrectEmail(), util.getCorrectPassword());
//        doLogOut(chromeDriver);
//        Thread.sleep(500);
//        Assert.assertEquals(firefoxDriver.getCurrentUrl(), "https://www.fiverr.com");
        Thread.sleep(10000);
    }
}