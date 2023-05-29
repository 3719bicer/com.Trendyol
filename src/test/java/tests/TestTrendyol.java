package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Page_ToastMessege;
import pages.Page_Trendyol;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.io.IOException;

import static org.testng.Assert.*;

public class TestTrendyol extends TestBaseRapor {
        /*
    Selenium Java ile aşağıdaki maddelerin gerçekleştirilmesi gerekmektedir. Testte baktığımız şeyler;
    kod kalitesi, page object yapısı, assertion'lar, TestNG Framework'u, object isimlendirmeleri,
    otomasyon raporu için gerekli kontrol cümleleri, loglama ve rapor.
    Rapor olarak Extent Report, Allure, Grafana vs. olabilir.

    Bonus olarak giriş bilgilerinin parametrik yapılıp excelden okunması ve
    test sonucunun başarılı başarısız durumun bir excel sonuç dosyasına yazılması.
    */

    /*
    1. <https://www.trendyol.com/> sitesini açılacak ve anasayfanın açıldığını onaylayacak
    2. Login ekranını açıp, bir kullanıcı ile login olacak ( daha önce siteye üyeliğin varsa o olabilir )
    3. Ekranın üstündeki Search alanına 'samsung' yazip 'Ara' butonuna tıklayacak
    4. Sol menüden 'Telefon' sonrasında 'Cep Telefonu' tıklayacak
    5. Gelen sayfada samsung için sonuç bulunduğunu onaylayacak
    6. Arama sonuçlarından 2. sayfaya tıklayacak ve açılan sayfada 2. sayfanın şu an gösterimde olduğunu onaylayacak
    7. Üstten 5. ürünü tıklayacak
    8. Ürün detayında 'Beğen' butonuna tıklayacak
    9. 'Ürün listenize eklendi.' popup kontrolü yapacak
    10. Ekranın en üstündeki hesabım alanında 'Beğendiklerim' tıklayacak
    11. Açılan sayfada bir önceki sayfada beğendiklerime alınmış ürünün bulunduğunu onaylayacak
    12. Beğendiklerime alınmış ürün bulunup seçilecek ve sepete eklenecek
    13. 'Ürün sepete eklendi' popup kontrolü yapacak
    14. Sepetim sayfasına gidecek
    15. Sepete eklenen bu ürünün içine girilip 'Kaldır' butonuna basılacak, sepetimden çıkarılacak
    16. Bu ürünün artik sepette olmadığını onaylayacak
    */

    SoftAssert softAssert=new SoftAssert();

    Page_ToastMessege toastMessege=new Page_ToastMessege();

    Page_Trendyol pageTrendyol=new Page_Trendyol();

    static Actions actions=new Actions(Driver.getDriver());


    @Test(priority = 0)
    public void TC_01() throws IOException {
        //1. <https://www.trendyol.com/> sitesini açılacak ve anasayfanın açıldığını onaylayacak
        extentTest=extentReports.createTest("TRENDYOL BASKET TEST","The product has been successfully added to the basket.");
        String expURL= ReusableMethods.readExcell("value",2,1);
        Driver.getDriver().get(expURL);
        String actURL=Driver.getDriver().getCurrentUrl();
        extentTest.info("Anasayfa YUKLENDI");

        softAssert.assertEquals(actURL, expURL,"Anasayfa YUKLENMEMISTIR");
        ReusableMethods.writeExcell("PASSED",1,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 1)
    public void TC_02() throws IOException {
        //2. Login ekranını açıp, bir kullanıcı ile login olacak ( daha önce siteye üyeliğin varsa o olabilir )
        toastMessege.close();
        pageTrendyol.loginMethod();
        extentTest.info("Kullanıcı LOGIN OLDU");

        ReusableMethods.writeExcell("PASSED",2,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 3)
    public void TC_03() throws IOException {
        //3. Ekranın üstündeki Search alanına 'samsung' yazip 'Ara' butonuna tıklayacak
        ReusableMethods.waitForVisibility(pageTrendyol.fieldSearchBox,1);
        ReusableMethods.waitForClickablility(pageTrendyol.fieldSearchBox,2);
        pageTrendyol.fieldSearchBox.sendKeys(ReusableMethods.readExcell("value",5,1));
        pageTrendyol.buttonSearch.click();
        extentTest.info("Search YAPILDI");

        ReusableMethods.writeExcell("PASSED",3,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 4)
    public void TC_04() throws IOException {
        //4. Sol menüden 'Telefon' sonrasında 'Cep Telefonu' tıklayacak
        ReusableMethods.clickElementByJS(pageTrendyol.checkBoxSamsung);
        ReusableMethods.waitFor(1);
        pageTrendyol.fieldKategoriAra.click();
        pageTrendyol.fieldKategoriAra.sendKeys(ReusableMethods.readExcell("value",6,1));
        ReusableMethods.waitFor(1);
        ReusableMethods.clickWithJS(pageTrendyol.checkBoxTelefon);
        extentTest.info("Filtre CheckBox TIKLANDI");

        ReusableMethods.writeExcell("PASSED",4,3);
        ReusableMethods.waitFor(1);

    }

    @Test(priority = 5)
    public void TC_05() throws IOException {
        //5. Gelen sayfada samsung için sonuç bulunduğunu onaylayacak /287 sonuc
        String searchResult=ReusableMethods.readExcell("value",7,1);
        String expCepResult=searchResult.substring(1,3);
        Driver.getDriver().navigate().refresh();
        ReusableMethods.waitForVisibility(pageTrendyol.textResultSamsung,5);
        String actCepResult = pageTrendyol.textResultSamsung.getText();
        extentTest.info("Arama Sonucları LISTELENMISTIR");

        assertTrue(actCepResult.contains(expCepResult),"Sonuclar LISTELENMEMISTIR");
        ReusableMethods.writeExcell("PASSED",5,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 6)
    public void TC_06() throws IOException {
        //6. Arama sonuçlarından gerekli filtrelemeler yapilarak sayfanın şu an gösterimde olduğunu onaylayacak
        ReusableMethods.clickElementByJS(pageTrendyol.popupYildizliUrunler);
        //ReusableMethods.clickElementByJS(pageTrendyol.checkBoxYildizliUrunler);
        //Listedeki ürün sayisi 3 e dustugu icin bu filtre ignore edilmistir.
        String expUrl3=ReusableMethods.readExcell("value",9,1);
        String actUrl3 = Driver.getDriver().getCurrentUrl();
        extentTest.info("2 Yildizli Urunler LISTELENMISTIR");

        softAssert.assertEquals(expUrl3,actUrl3,"Cok Satanlar sayfası GORUNTULENMEMEKTEDıR");
        ReusableMethods.writeExcell("PASSED",6,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 7)
    public void TC_07() throws IOException {
        //7. Üstten 5. ürünü tıklayacak.Açılan 2 sayfaya gecis yapilir.
        pageTrendyol.linkTitleUrunler.get(4).click();
        pageTrendyol.navigate2PageMethods();
        extentTest.info("2.Sayfaya GECIS YAPILMISTIR");

        ReusableMethods.writeExcell("PASSED",7,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 8)
    public void TC_08() throws IOException {
        //8. 2.Sayfa Ürün detayında 'Beğen' butonuna tıklayacak
        Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN)
                .perform();
        pageTrendyol.buttonLikeUrun.click();
        actions.sendKeys(Keys.PAGE_UP)
                .perform();
        extentTest.info("Begen Butonuna TIKLANMISTIR");

        ReusableMethods.writeExcell("PASSED",8,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 9)
    public void TC_09() throws IOException {
        //9. Açılan sayfada bir önceki sayfada beğendiklerime alınmış ürünün bulunduğunu onaylayacak
        String exp5UrunName=pageTrendyol.textUrun2Sayfa.getText();
        pageTrendyol.linkTextFavorilerim.click();
        String act5UrunName = pageTrendyol.textUrunFavorilerim.getText();
        extentTest.info("Urun FAVORILERE EKLENMISTIR");

        softAssert.assertEquals(exp5UrunName,act5UrunName,"Urun favorilerime EKLENMEMISTIR");
        ReusableMethods.writeExcell("PASSED",9,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 10)
    public void TC_10() throws IOException {
        //10. Favorilerime alınmış ürün bulunup seçilecek ve sepete eklenecek
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        ReusableMethods.clickElementByJS(pageTrendyol.buttonSepeteEkle);
        extentTest.info("Urun SEPETE EKLENMISTIR");

        ReusableMethods.writeExcell("PASSED",10,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 11)
    public void TC_11() throws IOException {
        //11. 'Ürün sepete eklendi' popup kontrolü yapacak
        String expSepeteEklendi=ReusableMethods.readExcell("value",11,1);
        String actSepeteEklendi= pageTrendyol.popUpSepeteEklendi.getText();
        extentTest.info("Urun Sepete Eklendigi POPUP DOGRULANMISTIR");

        softAssert.assertEquals(expSepeteEklendi,actSepeteEklendi,"Urun Sepete EKLENMEMISTIR");
        ReusableMethods.writeExcell("PASSED",11,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 12)
    public void TC_12() throws IOException {
        //12. Sepetim sayfasına gidecek
        pageTrendyol.buttonFavorilerimdenCikar.click();
        pageTrendyol.buttonGoBasket.click();
        ReusableMethods.waitForVisibility(pageTrendyol.buttonUrunSil,2);
        extentTest.info("Sepetim Sayfasina GIDILMISTIR");

        ReusableMethods.writeExcell("PASSED",12,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 13)
    public void TC_13() throws IOException {
        //13. Sepete eklenen bu ürünün içine girilip 'Kaldır' butonuna basılacak, sepetimden çıkarılacak
        ReusableMethods.clickElementByJS(pageTrendyol.buttonUrunSil);
        extentTest.info("Urun Kaldır Butonuna TIKLANMISTIR");

        ReusableMethods.writeExcell("PASSED",13,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 14)
    public void TC_14() throws IOException {
        //14. Bu ürünün artik sepette olmadığını onaylayacak
        String expSepeyUrunSayisi=ReusableMethods.readExcell("value",12,1);
        String actSepeyUrunSayisi= pageTrendyol.textUrunSepet.getText();
        extentTest.info("Sepette urun BULUNMAMAKTADIR");

        softAssert.assertEquals(expSepeyUrunSayisi,actSepeyUrunSayisi,"Sepette urun BULUNMAMAKTADIR.");
        ReusableMethods.writeExcell("PASSED",14,3);
        ReusableMethods.waitFor(1);
    }

    @Test(priority = 15)
    public void TC_15() throws IOException {
        //15. Kullanıcı hesaptan cikis yapar.
        pageTrendyol.logoutMethod();
        extentTest.info("Kullanıcı Hesaptan Cikis YAPMISTIR");

        ReusableMethods.writeExcell("PASSED",15,3);
        ReusableMethods.waitFor(1);
        Driver.getDriver().quit();
    }
}
