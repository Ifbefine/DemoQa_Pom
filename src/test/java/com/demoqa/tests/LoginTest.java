package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.bookStore.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginTest extends TestBase {

    private LoginPage loginPage;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectBookStore();    // используем существующий метод
        loginPage = new SidePanel(driver).selectLogin();  // сохраняем объект
    }

    @Test
    @Tag("smoky")
    public void loginPositiveTest() {
        loginPage.enterUserData("ifbefine", "Test123456789@")
                .clickOnLoginButton()
                .verifyUserName("ifbefine");
    }


    @Test
    @Tag("parameters")
    public void loginPositiveTestWithParameters() {
        String userName = System.getProperty("userName");
        String password = System.getProperty("password");
        loginPage.enterUserData(userName,password)
                .clickOnLoginButton()
                .verifyUserName("ifbefine");
    }
}