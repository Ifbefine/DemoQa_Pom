package com.demoqa.core;

import com.demoqa.utils.MyListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.time.Duration;

public class ApplicationManager {

    private String browser;
    protected WebDriver driver;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public WebDriver start() {
        // 1. Инициализация драйвера в зависимости от браузера
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            // Safari обычно не требует WebDriverManager на macOS
            driver = new SafariDriver();
        } else {
            // Если браузер указан неверно, выбрасываем ошибку, а не возвращаем null
            throw new RuntimeException("Некорректный тип браузера: " + browser);
        }

        // 2. Настройка слушателя (Листенера)
        // Важно: создаем декоратор только если драйвер успешно создался
        WebDriverListener listener = new MyListener(driver);
        driver = new EventFiringDecorator<>(listener).decorate(driver);

        // 3. Общие настройки браузера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 20 секунд часто многовато
        driver.get("https://demoqa.com");

        return driver;
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }
}