package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import com.demoqa.pages.widgets.ToolTipsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class WidgetsTests extends TestBase {

    SidePanel sidePanel;
    SelectPage select;

    @BeforeEach
    public void precondition(){
        sidePanel=new SidePanel(driver);
        select= new SelectPage(driver);
        new HomePage(driver).getWidgets();
    }

    @Test
    public void oldStyleSelectMenuTest(){
        sidePanel.getSelectMenu();
        select.oldStyleSelect("Yellow")
                .verifyColor();
    }
    @Test
    public void multiSelectTest(){
        sidePanel.getSelectMenu();
        select.muliSelect(new String[]{"Green","Red"})
                .verifyMultiSelect(new String[]{"Green","Red"});

    }
    @Test
    public void standardMultiSelectTest(){
        sidePanel.getSelectMenu();
        select.verifyMultiSelectedCar("opel","rgba(25, 103, 210, 1)");

    }

    @Test
    public void hoverMouseOnMenuTest(){
        sidePanel.getMenu();
        new MenuPage(driver).hoverMouseOnSubMenu()
                .verifySubMenu();

    }

    @Test//исправить тест
    public void sliderTest(){
        sidePanel.getSlider();
        new SliderPage(driver).moveSlider()
                .verifySliderValue("88");
        }
        @Test
        @Tag("smoky")
    public void tooltipsTest(){
        sidePanel.getToolTips();
        new ToolTipsPage(driver).hoverMouseOnToolTips()
                .verifyToolTips("buttonToolTip");
        }
    }

