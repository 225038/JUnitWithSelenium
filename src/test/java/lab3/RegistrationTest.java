package lab3;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class RegistrationTest {
    private Util util;
    //    private ChromeDriver chromeDriver;
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

    private void enterEmailForRegistry(WebDriver driver, String email) throws InterruptedException {
        util.tryClick(driver, By.xpath("(//a[contains(text(),'Join')])[4]"));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='user[email]']")).sendKeys(email);
        util.tryClick(driver, By.xpath("//button[@type='submit']"));
        Thread.sleep(1000);
    }

    private void enterLoginPassForRegistry(WebDriver driver, String name, String password) throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='user[username]']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='user[password]']")).sendKeys(password);
        util.tryClick(driver, By.xpath("//button[@type='submit']"));
        Thread.sleep(1000);
    }

    @Test
    public void successfulRegister() throws InterruptedException {
        String randomString = util.getRandomString(6);
        enterEmailForRegistry(firefoxDriver, randomString + "@mail.ru");
        enterLoginPassForRegistry(firefoxDriver, randomString, util.getCorrectPassword());
//        enterEmailForRegistry(chromeDriver, randomString + "@mail.ru", randomString, util.getCorrectPassword());
//        enterLoginPassForRegistry(chromeDriver, randomString + "@mail.ru", randomString, util.getCorrectPassword());
        Thread.sleep(10000);
    }

    @Test
    public void failedRegister() throws InterruptedException {
        String shortString = util.getRandomString(4);
        String randomString = util.getRandomString(8);
        String existingString = util.getCorrectName();
        String errEmail;

        // Пустой email
        enterEmailForRegistry(firefoxDriver, "");
        errEmail = firefoxDriver.findElement(By.xpath("(//p[@class='msg-error'])")).getText();
        Assert.assertEquals("Looks like this email is incomplete.", errEmail);
        Thread.sleep(500);
//       // Пустой email
//        enterEmailForRegistry(chromeDriver, "");
//        errEmail = chromeDriver.findElement(By.xpath("(//p[@class='msg-error'])")).getText();
//        Assert.assertEquals("Looks like this email is incomplete.", errEmail);
//        Thread.sleep(500);

        // Некорректный email
        enterEmailForRegistry(firefoxDriver, shortString + "@11.11");
        errEmail = firefoxDriver.findElement(By.xpath("(//p[@class='msg-error'])")).getText();
        Assert.assertEquals("This email's format is not valid. Please try another one.", errEmail);
        Thread.sleep(500);
//        // Некорректный email
//        enterEmailForRegistry(chromeDriver,shortString + "@11.11");
//        errEmail = chromeDriver.findElement(By.xpath("(//p[@class='msg-error'])")).getText();
//        Assert.assertEquals("This email's format is not valid. Please try another one.", errEmail);
//        Thread.sleep(500);

        // Верный email, верные, но короткие логин + пароль
        enterEmailForRegistry(firefoxDriver, shortString + "@mail.ru");
        enterLoginPassForRegistry(firefoxDriver, shortString, shortString + "A1");
        firefoxDriver.findElement(By.xpath("//p[contains(.,'That’s too short. A great username must include at least 6 characters.')]"));
        firefoxDriver.findElement(By.xpath("//p[contains(.,'Password must be min. 8 characters. Combine numbers, upper and lowercase letters.')]"));
        Thread.sleep(500);
//        // Верный email, верные, но короткие логин + пароль
//        enterEmailForRegistry(chromeDriver,shortString + "@mail.ru");
//        enterLoginPassForRegistry(chromeDriver, shortString,shortString + "A1");
//        chromeDriver.findElement(By.xpath("//p[contains(.,'That’s too short. A great username must include at least 6 characters.')]"));
//        chromeDriver.findElement(By.xpath("//p[contains(.,'Password must be min. 8 characters. Combine numbers, upper and lowercase letters.')]"));
//        Thread.sleep(500);

        // Верный email, существующий логин, некорректный пароль прваильной длины (нет цифры и заглавной буквы)
        enterEmailForRegistry(firefoxDriver, shortString + "@mail.ru");
        enterLoginPassForRegistry(firefoxDriver, existingString, randomString);
        firefoxDriver.findElement(By.xpath("//p[contains(.,'Looks like this username is already taken. Please pick a new one.')]"));
        firefoxDriver.findElement(By.xpath("//p[contains(.,'Password must be min. 8 characters. Combine numbers, upper and lowercase letters.')]"));
        Thread.sleep(500);
//        // Верный email, существующий логин, некорректный пароль прваильной длины (нет цифры и заглавной буквы)
//        enterEmailForRegistry(chromeDriver,shortString + "@mail.ru");
//        enterLoginPassForRegistry(chromeDriver, existingString, randomString);
//        chromeDriver.findElement(By.xpath("//p[contains(.,'Looks like this username is already taken. Please pick a new one.')]"));
//        chromeDriver.findElement(By.xpath("//p[contains(.,'Password must be min. 8 characters. Combine numbers, upper and lowercase letters.')]"));
//        Thread.sleep(500);

        // Верный email, пустые строки логина и пароля
        enterEmailForRegistry(firefoxDriver, shortString + "@mail.ru");
        enterLoginPassForRegistry(firefoxDriver, "", "");
        firefoxDriver.findElement(By.xpath("//p[contains(.,'That’s too short. A great username must include at least 6 characters.')]"));
        firefoxDriver.findElement(By.xpath("//p[contains(.,'Password must be min. 8 characters. Combine numbers, upper and lowercase letters.')]"));
        Thread.sleep(500);
//        // Верный email, пустые строки логина и пароля
//        enterEmailForRegistry(chromeDriver,shortString + "@mail.ru");
//        enterLoginPassForRegistry(chromeDriver,"", "");
//        chromeDriver.findElement(By.xpath("//p[contains(.,'That’s too short. A great username must include at least 6 characters.')]"));
//        chromeDriver.findElement(By.xpath("//p[contains(.,'Password must be min. 8 characters. Combine numbers, upper and lowercase letters.')]"));
//        Thread.sleep(500);
        Thread.sleep(10000);
    }
}
