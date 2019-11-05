package lab3;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

class Util {
    private String baseUrl;
    private String email = "testAcc555@yandex.ru";
    private String name = "testAcc555";

    String getBaseUrl() {
        return baseUrl;
    }

    String getCorrectEmail() {
        return email;
    }

    String getCorrectName() {
        return name;
    }

    String getCorrectPassword() {
        return "Test1234";
    }

    Util() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        baseUrl = "https://www.fiverr.com/";
    }

    void prepare(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(getBaseUrl());
    }

    void tryClick(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        element.sendKeys(Keys.ENTER);
//        element.sendKeys(MouseButton.PRIMARY);
    }

    void waitPresent(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    void doLogin(WebDriver driver, String login, String password) throws InterruptedException {
//        driver.findElement(By.xpath("(//a[contains(text(),'Sign In')])[7]")).click();
        driver.findElement(By.xpath("//div[@id='main-wrapper-header']/header/div/nav/ul/li[3]/a")).click();
//        tryClick(driver, By.xpath("//div[@id='main-wrapper-header']/header/div/nav/ul/li[3]/a"));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='user_session[login]']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='user_session[password]']")).sendKeys(password);
        driver.findElement(By.className("chk-img")).click();
        Thread.sleep(500);
        tryClick(driver, By.xpath("//input[@name='commit']"));
        Thread.sleep(1000);
    }

    String getRandomString(int length) {
        String symbols = "qwertytestrandomhelloworld";
        StringBuilder randString = new StringBuilder();
        for (int i = 0; i < length; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
        return randString.toString();
    }
}