package com.demoqa.pages.interactions;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DragAndDropPage extends BasePage {
    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }
@FindBy(id = "draggable")
    WebElement dragMe;
    @FindBy(id = "droppable")
    WebElement dropHere;
    public DragAndDropPage dragMe() {
        scrollWithJS(0,400,500);
        actions.dragAndDrop(dragMe,dropHere).perform();

    return this;
    }

    public DragAndDropPage verifyDropped(String text) {
        Assertions.assertTrue(shouldHaveText(dropHere,text,5));
        return this;
    }

    public DragAndDropPage dragMeBy() {
        scrollWithJS(0,400,400);
        int xOffset1 = dragMe.getLocation().getX();
        int yOffset1 = dragMe.getLocation().getY();
        System.out.println("xOffset1: " + xOffset1+ " yOffset1: " + yOffset1);
        int xOffset = dropHere.getLocation().getX();
        int yOffset = dropHere.getLocation().getY();
        System.out.println("xOffset: " + xOffset+ " yOffset: " + yOffset);
        xOffset= xOffset-xOffset1;
        yOffset= yOffset-yOffset1;
        actions.dragAndDropBy(dragMe,xOffset,yOffset).perform();
        return this;
    }
}
