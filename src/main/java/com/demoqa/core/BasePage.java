package com.demoqa.core;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    public static JavascriptExecutor js;
    public static SoftAssertions softly;
    public static Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
        softly = new SoftAssertions();
        actions = new Actions(driver);
    }

    public void scrollWithJS(int x, int y, int millis) {
        pause(millis);
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public void clickWithJS(WebElement element, int x, int y) {
        scrollWithJS(x,y, 1000);
        click(element);
    }

    public void typeWithJS(WebElement element,String text, int x, int y) {
        scrollWithJS(x,y, 1000);
        type(element,text);
    }

    public void click(WebElement element) {
        getWait(5).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text!=null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public boolean isAlertPresent(int time) {
        Alert alert = getWait(time)
                .until(ExpectedConditions.alertIsPresent());
        if (alert==null) {
            return false;
        }else {
            driver.switchTo().alert().accept();
            return true;
        }
    }

    public WebDriverWait getWait(int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    public boolean isContainsText(String text, WebElement element) {
        return element.getText().contains(text);
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        return getWait(time).until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public boolean isContainsCssValue(String color, WebElement selectedCar, String value) {
        return selectedCar.getCssValue(value).contains(color);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            e.getMessage();
            return false;
        }
    }

    public void waitOfElementVisibility(WebElement element, int time) {
        getWait(time).until(ExpectedConditions.visibilityOf(element));
    }

    public String getValue(WebElement element, String value) {
        return element.getDomAttribute(value);
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyLinks(String url) {
        try {
            URL linkUrl = new URL(url);
            //create URL connection and get response code
            HttpURLConnection connection = (HttpURLConnection) linkUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            int statusCode = connection.getResponseCode();
            if (statusCode >= 400) {
                // System.out.println(url + " --> " + connection.getResponseMessage() + " is a BROKEN link");
                softly.fail(url + " --> " + connection.getResponseMessage() + " is a BROKEN link");
            } else {
                //   System.out.println(url + " --> " + connection.getResponseMessage());
                softly.assertThat(statusCode).isLessThan(400);
            }
        }catch (Exception e) {
            //  System.out.println(url + " --> " + " ERROR occurred");
            softly.fail(url + " --> " + " ERROR occurred");
        }
    }

    public void  clickWithRectangle(WebElement element) {
        Rectangle rectangle = element.getRect();

        int xOffset = rectangle.getWidth() /8;
        int yOffset = rectangle.getHeight() / 4;

        actions.moveToElement(element).perform();
        actions.moveByOffset(-xOffset,-yOffset).click().perform();
    }

}