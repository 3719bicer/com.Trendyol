package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class TestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        /*WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();*/
    }

    @AfterMethod
    public void teardown(){
        Driver.getDriver().close();
    }

    public void wait(int saniye){
        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void fotografCek(String eurozoneResmi){
        TakesScreenshot tss= (TakesScreenshot) driver;
        File resimDosyasi= new File("target/screnShots/tumSayfaResmi.jpeg");
        File geciciResim=tss.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(geciciResim,resimDosyasi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
