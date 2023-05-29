package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;

public class Page_ToastMessege {

    public Page_ToastMessege() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement buttonCloseToast;

    @FindBy(xpath = "//div[@title='Kapat']")
    public WebElement labelModalPopup;

    public void close() {
        try {
            buttonCloseToast = ReusableMethods.waitForVisibility(buttonCloseToast, 10);
            if(labelModalPopup.isDisplayed()) {
                ReusableMethods.waitForClickablility(labelModalPopup,3);
                labelModalPopup.click();
                hideCookies();
            }
            buttonCloseToast.click();
        } catch (RuntimeException e) {
            System.out.println("buttonCloseToast element is not visible\n");
            e.printStackTrace();
        }
    }

    public void hideCookies() {
        JavascriptExecutor driver = (JavascriptExecutor) Driver.getDriver();
        driver.executeScript("arguments[0].style.visibility='hidden'", labelModalPopup);

    }

}
