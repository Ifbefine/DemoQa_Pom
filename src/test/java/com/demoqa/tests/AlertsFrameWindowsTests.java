package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.IframesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AlertsFrameWindowsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;
    IframesPage iframes;

    @BeforeEach
    public void precondition(){
        new HomePage(driver).getAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
        iframes = new IframesPage(driver);
    }
    @Test
    public void waitAlertTest(){
        sidePanel.getAlerts();
        alerts.verifyAlertwithTimer();
    }

    @Test
    public void alertWithSelectResult(){
        sidePanel.getAlerts();
        alerts.clickOnResult("Cancel")
                .verifyResult("Cancel");
    }

    @Test
    public void SendMessageAlert(){
        sidePanel.getAlerts();
        alerts.clickOnPromptButton()
                .sendMessageToAlert("Hello world")
                .verifyMeassage("Hello world");
    }
@Test
    public void newTabTest(){
        sidePanel.getBrowserWindows();
        new WindowsPage(driver).clickNewTabButton()
                .switchToNewTab(1)
                .verifyNewTabTitle("This is a sample page");
}
@Test
    public void iframeByIdTest(){
        sidePanel.getFrames();
        iframes.switchToIframeById()
                .veifyIframeByTitle("This is a sample page")
                .switchToMainPage()
                .verifyMainPageByTitle("Frames");


}
    @Test
    @Tag("smoky")
    public void nestedIframesTest()  {
        sidePanel.getNestedFrames();
        iframes.verifyNestedIframes();
    }

}
