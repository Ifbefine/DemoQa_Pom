package com.demoqa.pages.forms;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PracticeFormPage  extends BasePage {
    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }
@FindBy(id = "firstName")
WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;
    public PracticeFormPage enterPersonalData(String name, String surname, String email, String phone) {
        typeWithJS(firstName,name,0,300);
type(lastName,surname);
type(userEmail,email);
typeWithJS(userNumber,phone,0,300);
return this;
    }
@FindBy(css = "[for='gender-radio-1']")
WebElement male;
    @FindBy(css = "[for='gender-radio-1" +
            "2']")
WebElement female;
    @FindBy(css = "[for='gender-radio-1" +
            "3']")
WebElement other;

    public PracticeFormPage selectGender(String gender) {
        if (gender.equals("Male")) {
            click(male);
        }else if (gender.equals("Female")) {
            click(female);
        }
        else {
            click(other);
        }

        return this;
    }
@FindBy(id = "dateOfBirthInput")
WebElement dateOfBirthInput;
    public PracticeFormPage typeOfDate(String date) {
        click(dateOfBirthInput);

        String os = System.getProperty("os.name");
        System.out.println("My os " + os);

        if(os.startsWith("Mac")) {
            dateOfBirthInput.sendKeys(Keys.COMMAND, "a");
        }else {
            dateOfBirthInput.sendKeys(Keys.CONTROL,"a");


        }
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        return this;
    }
@FindBy(id = "subjectsInput")
WebElement subjectsInput;
    public PracticeFormPage addSubject(String[] subjects) {
        for(int i=0;i<subjects.length;i++) {
            if(subjects!=null) {
                type(subjectsInput,subjects[i]);
                subjectsInput.sendKeys(Keys.ENTER);
            }
        }
        return this;
    }
@FindBy(css = "[for='hobbies-checkbox-1']")
WebElement sports;
    @FindBy(css = "[for='hobbies-checkbox-2']")
    WebElement reading;
    @FindBy(css = "[for='hobbies-checkbox-3']")
    WebElement music;
    public PracticeFormPage selectHobby(String[] hobbies) {
        for (int i = 0; i < hobbies.length; i++) {
            if(hobbies[i].equals("Sports")) {
                click(sports);
            }
            if (hobbies[i].equals("Reading")) {
                click(reading);

            }
            if (hobbies[i].equals("Music")) {
                click(music);
            }
        }
        return this;
    }
@FindBy(id = "uploadPicture")
WebElement picture;
    public PracticeFormPage uploadFile(String path) {
        picture.sendKeys(path);
        return this;
    }
@FindBy(id = "react-select-3-input")
WebElement stateInput;
    public PracticeFormPage enterState(String state) {
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        return this;

    }
@FindBy(id = "react-select-4-input")
WebElement cityInput;
    public PracticeFormPage enterCity(String city) {
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        return this;

    }
@FindBy(id = "submit")
WebElement submit;
    public PracticeFormPage submit() {
        click(submit);
        return this;
    }
@FindBy(id = "example-modal-sizes-title-lg")
WebElement modalTitle;
    public void verifySuccessRegistration(String title) {
        Assertions.assertTrue(shouldHaveText(modalTitle,title,10));
    }
@FindBy(css = ".react-datepicker__month-select")
WebElement selectMonth;
    @FindBy(css = ".react-datepicker__year-select")
    WebElement selectYear;
    public PracticeFormPage selectDate(String month, String year, String date) {
        click(dateOfBirthInput);
        new Select(selectMonth).selectByVisibleText(month);
        new Select(selectYear).selectByVisibleText(year);

driver.findElement(By.xpath("//*[.='" + date + "']")).click();
        return this;
    }
@FindBy(css = "h5")
WebElement formTitle;
    public PracticeFormPage verifyFormTitle() {
        Assertions.assertTrue(isElementVisible(formTitle));
        return this;
    }
}
