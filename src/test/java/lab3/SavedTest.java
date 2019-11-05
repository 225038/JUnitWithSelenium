package lab3;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SavedTest {

    private static Util util;
    private static ChromeDriver chromeDriver;
    private static FirefoxDriver firefoxDriver;

    @BeforeClass
    public static void setUp() throws InterruptedException {
        util = new Util();

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        chromeDriver = new ChromeDriver(capabilities);
//        chromeDriver = new ChromeDriver();

        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("-private");
        firefoxDriver = new FirefoxDriver(opts);
//        firefoxDriver = new FirefoxDriver();

        util.prepare(chromeDriver);
        util.prepare(firefoxDriver);
        util.doLogin(chromeDriver, "testAcc556@yandex.ru", "testAcc556");
        util.doLogin(firefoxDriver, "testAcc557@yandex.ru", "testAcc557");
    }

    @AfterClass
    public static void tearDown() {
        chromeDriver.quit();
        firefoxDriver.quit();
    }

    private void addToSaved(WebDriver driver) throws Exception{
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/a")).click();
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/section[2]/div/div/article/div/div/div/div/div/footer/div/a/i"))
                .click();
    }

    private void removeFromSaved(WebDriver driver) {
        driver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/nav/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/section/div/nav/div[2]/a/span/img")).click();
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/section/div[3]/div/article/div/div" +
                "/div/div/div/div/a/h3")).click();
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/div[2]/div/div/nav/aside/div/div/a/span")).click();
    }

    private int getGigsCount(WebDriver driver) {
        driver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/nav/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/section/div/nav/div[2]/a/span/img")).
                click();
        String text = driver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/section/div/div/div/" +
                "header/h1/strong/small")).getText();
        String[] output = text.split(" ");
        return Integer.parseInt(output[0].replace("(",""));
    }

    @Test
    public void addToSaved_TestFirefox() throws Exception{
        int gigsBeforeAdding = getGigsCount(firefoxDriver);
        addToSaved(firefoxDriver);
        firefoxDriver.findElement(By.xpath("//div[@id='Header-component']/header/div/div/nav/ul/li[4]/a")).click();
        firefoxDriver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/section/div/nav/div[2]/a/span/img")).
                click();
        int gigsAfterAdding = getGigsCount(firefoxDriver);
        Assert.assertEquals(gigsBeforeAdding + 1, gigsAfterAdding);
    }

    @Test
    public void addToSaved_TestChrome() throws Exception {
        int gigsBeforeAdding = getGigsCount(chromeDriver);
        addToSaved(chromeDriver);
        util.tryClick(chromeDriver, By.xpath("//div[@id='Header-component']/header/div/div/nav/ul/li[4]/a"));
        chromeDriver.findElement(By.xpath("//div[@id='main-wrapper']/div[2]/section/div/nav/div[2]/a/span/img")).
                click();
        int gigsAfterAdding = getGigsCount(chromeDriver);
        Assert.assertEquals(gigsBeforeAdding + 1, gigsAfterAdding);
    }

    @Test
    public void removeFromSaved_TestFirefox() throws Exception {
        addToSaved(firefoxDriver);
        int gigsCountBeforeRemove = getGigsCount(firefoxDriver);
        removeFromSaved(firefoxDriver);
        Thread.sleep(1000);
        int gigsCountAfterRemove = getGigsCount(firefoxDriver);
        Assert.assertEquals(gigsCountBeforeRemove - 1, gigsCountAfterRemove);

    }

    @Test
    public void removeFromSaved_testChrome() throws Exception {
        addToSaved(chromeDriver);
        int gigsCountBeforeRemove = getGigsCount(chromeDriver);
        removeFromSaved(chromeDriver);
        Thread.sleep(1000);
        int gigsCountAfterRemove = getGigsCount(chromeDriver);
        Assert.assertEquals(gigsCountBeforeRemove - 1, gigsCountAfterRemove);
    }
}
