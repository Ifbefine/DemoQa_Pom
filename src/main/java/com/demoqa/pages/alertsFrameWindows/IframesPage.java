package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class IframesPage extends BasePage {
    public IframesPage(WebDriver driver) {
        super(driver);
    }
@FindBy(id = "frame1")
    WebElement frame1;
    @FindBy(css = "h1")
    WebElement title;
    @FindBy(css = "iframe")
    List<WebElement> iframes;
    public IframesPage switchToIframeById() {
        System.out.println(iframes.size());
driver.switchTo().frame(frame1);
        return this;
    }

    public IframesPage veifyIframeByTitle(String text) {
        Assertions.assertTrue(isContainsText(text,title));
        return this;
    }

    public IframesPage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }
@FindBy(css = ".text-center")
WebElement mainTitle;
    public IframesPage verifyMainPageByTitle(String text) {
        Assertions.assertTrue(isContainsText(text,mainTitle));
        return this;
    }
    @FindBy(css = "body")
    WebElement body;
    public IframesPage verifyNestedIframes() {
        //switch to parent iframe by id
        driver.switchTo().frame(frame1);
        //assert by test and by total numbers of iframes
        softly.assertThat(isContainsText("Parent frame",body));
        softly.assertThat(iframes.size()).isEqualTo(1);
        //switch to child iframe by index
        driver.switchTo().frame(0);
        //asset by text
        softly.assertThat(isContainsText("Child Iframe",body));
        //return to parent iframe
        driver.switchTo().parentFrame();
        //assert by text
        softly.assertThat(isContainsText("Parent frame",body));
        softly.assertAll();
        return this;
    }
}
