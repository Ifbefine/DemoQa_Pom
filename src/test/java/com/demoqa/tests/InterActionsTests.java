package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.interactions.DragAndDropPage;
import org.junit.jupiter.api.*;

public class InterActionsTests extends TestBase {
    SidePanel sidePanel;
    DragAndDropPage dragAndDrop;

    @BeforeEach
    public void precondition(){
        sidePanel=new SidePanel(driver);
        dragAndDrop=new DragAndDropPage(driver);
        new HomePage(driver).getInteractions();
    }
    @Test
    public void dragMeTest(){
        sidePanel.getDropple();
        dragAndDrop.dragMe()
                .verifyDropped("Dropped!");
    }
    @Test
    public void draMeByTest(){
        sidePanel.getDropple();
        dragAndDrop.dragMeBy().verifyDropped("Dropped!");
    }
    @RepeatedTest(value = 3,name = "{displayName}{currentRepetition}/{totalRepetitions}")
    @DisplayName("Verify -> drag and drop element by coordinates/ Try to get error ")
   @Tag("smoky")
    public void draMeTest(){
        sidePanel.getDropple();
        dragAndDrop.dragMeBy().verifyDropped("Dropped!");
    }

}
