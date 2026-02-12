package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.BrokenLinksImagesPage;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.pages.elements.UploadPage;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonsPage buttons;
    TextBoxPage textBox;
    BrokenLinksImagesPage brokenLinksImages;
    UploadPage upload;

    @BeforeEach
    public void precondition(){
        sidePanel=new SidePanel(driver);
        buttons=new ButtonsPage(driver);
        new HomePage(driver).getElements();
        textBox=new TextBoxPage(driver);
        brokenLinksImages=new BrokenLinksImagesPage(driver);
        upload=new UploadPage(driver);
    }

    @Test
    @Tag("smoky")
    public void doubleClickTest(){
        sidePanel.clickButtons();//это типо getButtons
        buttons.doubleClick();
        buttons.verifyDoubleClick("double click");
    }

    @Test
    public void rightClick(){
        sidePanel.clickButtons();//это типо getButtons
        buttons.rightClick();
        buttons.verifyRightClick("right click");
    }
    @Test
    public void copyPasteTest(){
        sidePanel.getTextBox();
        textBox.copyPast("Friedrich Strasse").clickOnSubmitButton().verifyAddress();
    }
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    public void textBoxWithParameterTest(String name, String email, String address){
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void textBoxWithCsvFileTest(String name, String email, String address){
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
    }
    @Test
    public void javascriptExecutorTest(){
        sidePanel.getTextBox();
        textBox.enterPersonalDataWithJs("Jamal Musiala","Aboba@gm.com")
                .clickOnSubmitWithJs().getInnerText()
                .verifyUrl()
                .refreshWithJs()
                .navigateWithJS("https://ilcarro.web.app")
                .verifyFaveiconTitle()
        ;
    }
    @Test
    public void getAllLinksTest(){
        sidePanel.getBrokenLinksImages();
        brokenLinksImages.getAllLinks();
    }
    @Test
    public void checkBrokenLinksTest() {
        sidePanel.getBrokenLinksImages();
        brokenLinksImages.checkBrokenLinks();
    }

    @Test
    public void checkBrokenImages() {
        sidePanel.getBrokenLinksImages();
        brokenLinksImages.checkBrokenImages();
    }

    @Test
    public void performKeyEventsWithRobotTest() {
        sidePanel.getUpload();

        // Пишем путь как обычную строку.
        // Java сама поймет \\ как один слэш.
        String path = "C:\\QA2\\D1.txt";

        upload.performKeyEvent(path)
                .verifyFilePath("D1.txt"); // Проверяем только имя файла
    }

    @Test
    public void performMouseEventWithRobotTest() {
        sidePanel.getUpload();
        upload.performMouseEvent()
                .verifyFilePath("D1.txt")
        ;
    }
    @Test
    public void performMouseEventTest(){
        sidePanel.getUpload();
        upload.performMouseEvent()
                ;
    }
}
