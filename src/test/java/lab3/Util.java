package lab3;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class Util {
    private String baseUrl;

    String getBaseUrl() {
        return baseUrl;
    }

    String getCorrectLogin() {
        return "pukoh@mail.ru";
    }

    String getCorrectPassword() {
        return "8FBN7j3Jh,fmk3Q";
    }

    Util() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        baseUrl = "https://www.fiverr.com//";
    }

    void prepare(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(getBaseUrl());
    }

    void tryClick(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        element.sendKeys(Keys.ENTER);
    }

    void waitPresent(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    String getRandomString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}