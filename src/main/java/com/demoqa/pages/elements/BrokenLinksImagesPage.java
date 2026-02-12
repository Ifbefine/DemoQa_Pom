package com.demoqa.pages.elements;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.util.Iterator;

public class BrokenLinksImagesPage extends BasePage {
    public BrokenLinksImagesPage(WebDriver driver) {
        super(driver);
    }
@FindBy(css="a")
    List<WebElement> allLinks;
    public BrokenLinksImagesPage getAllLinks() {
        System.out.println("Total Links : " + allLinks.size());
        String url="";
        Iterator<WebElement>iterator=allLinks.iterator();
        while (iterator.hasNext()){
            url=url+iterator.next().getText();
            System.out.println(url);
            System.out.println("************************************");
        }
        return this;
    }

    public BrokenLinksImagesPage checkBrokenLinks() {
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement element = allLinks.get(i);
            String url = element.getDomAttribute("href");
            verifyLinks(url);

        }
        return this;
    }
@FindBy(css="img")
List<WebElement> images;
    public BrokenLinksImagesPage checkBrokenImages() {
        System.out.println("Total images on the page = " +images.size());
        for (int i = 0; i < images.size(); i++) {
            WebElement image = images.get(i);
            String url = image.getAttribute("src");
            verifyLinks(url);

            try {
                boolean imageDisplayed= (boolean)
                        js.executeScript("return (typeof arguments[0].naturalWidth!=undifined && " +
                                        "arguments[0].naturalWidth>0);"
                        ,image);
                if (imageDisplayed){
                    System.out.println("DISPAY - OK");
                }else {
                    System.out.println("DISPAY - NOT OK");
                    System.out.println("*****************************************");
                }
            } catch (Exception e) {
               // System.out.println("ERROR occurred");
            }

        }
        return this;
    }
}
