package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.forms.PracticeFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PracticeFormTest extends TestBase {
    PracticeFormPage practiceForm;
    @BeforeEach
    public void precondition()
    {
        new HomePage(driver).getforms();
        new SidePanel(driver).getPracticeForm();
        practiceForm=new PracticeFormPage(driver);
    }
    @Test
    public void createAccountPositiveTest(){
        practiceForm.enterPersonalData("Jamal","Musiala","jamal@gm.com","1234567890")
             .selectGender("Male")
             .typeOfDate("16 Aug 1987")
                .addSubject(new String[]{"Maths","English"})
               .selectHobby(new String[]{"Sports","Music"})
             .uploadFile("C:/Users/Ifbefine/Downloads/arbuz.jpg")
               .enterState("NCR")
             .enterCity("Delhi")
               .submit()
                .verifySuccessRegistration("Thanks for submitting the form")
                ;

    } @Test
    public void createAccountNegativeTest(){
        practiceForm.enterPersonalData("Jamal","Musiala","jamal@gm.com","1234567890")
             .selectGender("Male")
             .selectDate("August", "1987","16")
                .addSubject(new String[]{"Maths","English"})
               .selectHobby(new String[]{"Sports","Music"})
             .uploadFile("C:/Users/Ifbefine/Downloads/arbuz.jpg")
               .enterState("NCR")
             .enterCity("Delhi")
               .submit()

                ;

    }@Test
    public void createAccountNegativeInvalidPhoneTest(){
        practiceForm.enterPersonalData("Jamal","Musiala","jamal@gm.com","1234")
             .selectGender("Male")
             .selectDate("August", "1987","16")
                .addSubject(new String[]{"Maths","English"})
               .selectHobby(new String[]{"Sports","Music"})
             .uploadFile("C:/Users/Ifbefine/Downloads/arbuz.jpg")
               .enterState("NCR")
             .enterCity("Delhi")
               .submit()
                .verifyFormTitle()

                ;

    }
}
