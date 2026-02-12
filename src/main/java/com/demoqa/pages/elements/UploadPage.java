package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class UploadPage extends BasePage {

    Robot robot;

    public UploadPage(WebDriver driver) {
        super(driver);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "uploadFile")
    WebElement uploadFile;

    public UploadPage performKeyEvent(String absolutePath) {
        scrollWithJS(0, 300,400);
        clickWithRectangle(uploadFile); // или просто uploadFile.click();
        pause(2000); // Важно! Ждем, пока окно Windows реально откроется

        // 1. Копируем путь в буфер обмена
        StringSelection stringSelection = new StringSelection(absolutePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // 2. Робот нажимает Ctrl + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        pause(1000);

        // 3. Нажимаем Enter, чтобы подтвердить выбор
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        return this;
    }

    @FindBy(id = "uploadedFilePath")
    WebElement uploadedFilePath;


    public UploadPage verifyFilePath(String path) {
        Assertions.assertTrue(isContainsText(path,uploadedFilePath));
        return this;
    }

    public UploadPage performMouseEvent() {
        scrollWithJS(0,100,400);
        clickWithRectangle(uploadFile);
 pause(2000);
//        Point location = MouseInfo.getPointerInfo().getLocation();
//        int x = (int) location.getX();
//        int y = (int) location.getY();
        //System.out.println("x: " + x + " y: " + y);
        pause(2000);
        robot.mouseMove(345,371);
        pause(2000);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
pause(2000);
robot.keyPress(KeyEvent.VK_ENTER);
        return this;
    }
}