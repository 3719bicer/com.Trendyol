package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import javax.swing.text.Element;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Page_Trendyol {

    public Page_Trendyol() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    /*********************************LOCATES*********************************/
    @FindBy (xpath = "//p[contains(text(),'Giriş Yap')]")
    public WebElement ddmGirisYap;

    @FindBy (xpath = "//div[@class='login-button']")
    public WebElement buttonGirisYap;

    @FindBy (xpath = "//input[@id='login-email']")
    public WebElement fieldsEmail;

    @FindBy (xpath = "//input[@id='login-password-input']")
    public WebElement fieldsPassword;

    @FindBy (xpath = "//button[@type='submit']//span[contains(text(),'Giriş Yap')]")
    public WebElement buttonLogin;

    @FindBy(xpath = "//input[@placeholder='Aradığınız ürün, kategori veya markayı yazınız']")
    public WebElement fieldSearchBox;

    @FindBy(xpath = "//i[@class='cyrzo7gC']")
    public WebElement buttonSearch;

    @FindBy(xpath = "//div[contains(text(),'Samsung')]")
    public WebElement checkBoxSamsung;

    @FindBy(xpath = "//input[@placeholder='Kategori ara']")
    public WebElement fieldKategoriAra;

    @FindBy(xpath = "(//a[@class='fltr-item-wrppr'])[1]")
    public WebElement checkBoxTelefon;

    @FindBy(xpath = "//div[@class='overlay']")
    public WebElement popupYildizliUrunler;
    @FindBy(xpath = "//div[contains(text(),'Bir Yıldızlı Ürün')]")
    public WebElement checkBoxYildizliUrunler;

    @FindBy(xpath = "//div[@class='srch-ttl-cntnr-wrppr']")
    public WebElement textResultSamsung;

    @FindAll({@FindBy(xpath = "(//*[@class=''])//div[1]//div[1]//span[2]")})
    public List<WebElement> linkTitleUrunler;
    //yuksek puanli urunler listesi

    @FindBy(xpath = "//h1[@class='pr-new-br']")
    public WebElement textUrun2Sayfa;

    @FindBy(xpath = "//button[@class='fv']")
    public WebElement buttonLikeUrun;
    //5.urun like buton

    @FindBy(xpath = "//div[@class='prdct-desc-cntnr']")
    public WebElement textUrunFavorilerim;
    @FindBy(xpath = "//div[@class='ufvrt-btn-wrppr']")
    public WebElement buttonFavorilerimdenCikar;

    @FindBy(xpath = "//div[@class='link']")
    public WebElement linkTextFavorilerim;

    @FindBy(xpath = "//div[@class='basket-button   ']")
    public WebElement buttonSepeteEkle;

    @FindBy(xpath = "//span[@class='basket-text color-white']")
    public WebElement popUpSepeteEklendi;

    @FindBy(xpath = "//p[normalize-space()='Sepetim']")
    public WebElement buttonGoBasket;

    @FindBy(xpath = "//i[@class='i-trash']")
    public WebElement buttonUrunSil;

    @FindBy(xpath = "//div[@class='pb-header']")
    public WebElement textUrunSepet;

    @FindBy(xpath = "//p[contains(text(),'Hesabım')]")
    public WebElement ddmHesabim;

    @FindBy(xpath = "//p[contains(text(),'Çıkış Yap')]")
    public WebElement buttonLogout;


    /**********************************METHODS*********************************/

    public void loginMethod() throws IOException {
        Page_Trendyol pageTrendyol=new Page_Trendyol();
        ReusableMethods.hover(pageTrendyol.ddmGirisYap);
        pageTrendyol.buttonGirisYap.click();
        ReusableMethods.waitFor(1);

        pageTrendyol.fieldsEmail.sendKeys(ReusableMethods.readExcell("value",3,1));
        pageTrendyol.fieldsPassword.sendKeys(ReusableMethods.readExcell("value",4,1));
        pageTrendyol.buttonLogin.click();
    }

    public void navigate2PageMethods() {
        String ikinciSayfaHandleDegeri = Driver.getDriver().getWindowHandle();

        Set<String> windowHandlesSeti = Driver.getDriver().getWindowHandles();
        String ikinciSayfaWinHandleDegeri = "";
        for (String eachHandleDegeri : windowHandlesSeti
        ) {
            String ilkSayfaWinHandDegeri = Driver.getDriver().getWindowHandle();
            if (!eachHandleDegeri.equals(ilkSayfaWinHandDegeri)) {
                ikinciSayfaWinHandleDegeri = eachHandleDegeri;
            }
        }
        Driver.getDriver().switchTo().window(ikinciSayfaWinHandleDegeri);
    }

    public void logoutMethod() {
        Page_Trendyol pageTrendyol=new Page_Trendyol();
        ReusableMethods.hover(pageTrendyol.ddmHesabim);
        pageTrendyol.buttonLogout.click();
    }
}

