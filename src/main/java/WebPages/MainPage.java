package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class MainPage {
    private WebDriver driver;
    private final static String URL = "https://qa-scooter.praktikum-services.ru/";

    // Локатор кнопки для принятия куки:
    private final By ACCEPT_COOKIES_BUTTON = By.id("rcc-confirm-button");

    // Локатор раздела вопросов и ответов:
    private final By QAA_SECTION = By.className("Home_SubHeader__zwi_E");
    // Для построение локаторов вопросов и ответов с индексами:
    private String questionLocatorBase = "accordion__heading-";
    private String answerLocatorBase = ".//div[@id='accordion__panel-";
    private String answerLocatorSuffix = "']/p";

    // Локатор кнопки "Заказать" вверху:
    private final By UPPER_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Локатор кнопки "Заказать" внизу
    private final By LOW_ORDER_BUTTON = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    // Метод кликает по кнопке аксепта куки, если эта кнопка отображается на странице:
    public void clickAcceptCookiesButton() {
        if (driver.findElement(ACCEPT_COOKIES_BUTTON).isDisplayed()) {
            driver.findElement(ACCEPT_COOKIES_BUTTON).click();
        }
    }

    // Метод прокрутки страницы до раздела вопросов и ответов:
    public void scrollToQAASection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(QAA_SECTION));
    }

    // Метод кликает на вопрос с заданным индексом:
    public void clickQuestion(int index) {
        String questionLocator = questionLocatorBase + index;
        driver.findElement(By.id(questionLocator)).click();
    }

    // Метод возвращает текст вопроса с заданным индексом:
    public String getQuestionText(int index) {
        String questionLocator = questionLocatorBase + index;
        return driver.findElement(By.id(questionLocator)).getText();
    }

    // Метод возвращает текст ответа с заданаыыми индексом:
    public String getAnswerText(int index) {
        String answerLocator = answerLocatorBase + index + answerLocatorSuffix;
        return driver.findElement(By.xpath(answerLocator)).getText();
    }

    // Метод кликает на кнопку "Заказать" вверху страницы:
    public void clickOrderUpperButton() {
        driver.findElement(UPPER_ORDER_BUTTON).click();
    }

    // Метод кликает на кнопку "Заказать" в теле страницы:
    public void clickOrderLowButton() {
        driver.findElement(LOW_ORDER_BUTTON).click();
    }
}