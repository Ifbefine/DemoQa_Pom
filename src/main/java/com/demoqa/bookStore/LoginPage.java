package com.demoqa.bookStore;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "userName-value")
    private WebElement userNameValue;

    public LoginPage enterUserData(String name, String password) {
        typeWithJS(userNameInput, name, 0, 300);
        type(passwordInput, password);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        click(loginButton);
        return this;                    // ← теперь возвращает LoginPage
    }

    public void verifyUserName(String expected) {   // ← теперь void, без return
        String actual = userNameValue.getText().trim();
        Assertions.assertEquals(expected, actual,
                "Имя не совпадает. Ожидалось: " + expected + ", увидели: " + actual);
    }
}