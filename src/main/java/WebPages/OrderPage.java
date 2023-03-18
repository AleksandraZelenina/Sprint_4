package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPage {

    // Локатор для поля с именем:
    private final By NAME_FIELD = By.xpath(".//input[@placeholder = '* Имя']");
    // Локатор для поля с фамилией:
    private final By SURNAME_FIELD = By.xpath(".//input[@placeholder = '* Фамилия']");
    // Локатор для поля с адресом:
    private final By ADDRESS_FIELD = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    // Локатор для поля с метро:
    private final By METRO_STATION_FIELD = By.xpath(".//input[@placeholder = '* Станция метро']");
    // Локатор для поля с телефоном:
    private final By TEL_NUMBER_FIELD = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");

    // Локатор для первой кнопки "Далее":
    private final By FIRST_STEP_NEXT_BUTTON = By.xpath(".//button[text() = 'Далее']");

    // Локатор для Даты:
    private final By DATE_OF_RENT_START_INPUT = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");

    // Локатор для Поля "Срок Аренды":
    private final By RENT_DURATION_DROP_DOWN_LIST = By.xpath(".//div[text() = '* Срок аренды']");

    // Выпадающий список - варианты для продолжительности аренды:
    private final By RENT_DURATION_OPTIONS = By.xpath(".//div[@class = 'Dropdown-option']");

    // Необязательное. Локаторы для Поля "Цвет":
    private final By COLOR_BLACK_CHECKBOX = By.xpath(".//input[@id = 'black']");
    private final By COLOR_GRAY_CHECHBOX = By.xpath(".//input[@id = 'gray']");

    // Необязательное. Поле ввода коментария для курьера:
    private final By COMMENT_FIELD = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");

    // Локатор кнопки "Заказать" :
    private final By ORDER_BUTTON = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор кнопки подтверждения заказа "Да":
    private final By ORDER_CONFIRMATION_BUTTON = By.xpath(".//button[text() = 'Да']");

    // Локатор раздела с сообщением подтвержения "Заказ оформлен":
    private final By ORDER_SUCCESS_MESSAGE = By.className("Order_ModalHeader__3FDaJ");
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы заполняют данные формы заказа:
    public void inputName(String text) {
        driver.findElement(NAME_FIELD).sendKeys(text);
    }
    public void inputSurname(String text) {
        driver.findElement(SURNAME_FIELD).sendKeys(text);
    }
    public void inputAddress(String text) {
        driver.findElement(ADDRESS_FIELD).sendKeys(text);
    }
    public void inputMetro(String text) {
        driver.findElement(METRO_STATION_FIELD).click();
        driver.findElement(METRO_STATION_FIELD).sendKeys(text, Keys.ARROW_DOWN, Keys.ENTER);
    }
    public void inputPhone(String text) {
        driver.findElement(TEL_NUMBER_FIELD).sendKeys(text);
    }
    
    // Метод кликает на кнопку "Далее" на шаге заполнения "Для кого самокат":
    public void clickNextStepButton() {
        driver.findElement(FIRST_STEP_NEXT_BUTTON).click();
        }
    
    public void setStartDateInput(String text) {
        driver.findElement(DATE_OF_RENT_START_INPUT).click();
        driver.findElement(DATE_OF_RENT_START_INPUT).sendKeys(text, Keys.ENTER);
    }
    public void setDurationDropDownListInput(int day) {
        driver.findElement(RENT_DURATION_DROP_DOWN_LIST).click();
        driver.findElements(RENT_DURATION_OPTIONS).get(day).click();
    }
    public void setColorCheckbox(String color) {
        if ("black".equals(color)) {
            driver.findElement(COLOR_BLACK_CHECKBOX).click();
        } else if ("grey".equals(color))  {
            driver.findElement(COLOR_GRAY_CHECHBOX).click();
        }
    }
    public void setCommentsInput(String comments) {
        driver.findElement(COMMENT_FIELD).sendKeys(comments);
    }
    
    // Метод кликает на кнопку "Заказать" под данными о заказе на шаге "Про аренду":
    public void clickOrderFinalButton() {
        driver.findElement(ORDER_BUTTON).click();
    }
    // Метод кликает на кнопку "Да" подтверждения заказа:
    public void clickOrderConfirmationButton() {
        driver.findElement(ORDER_CONFIRMATION_BUTTON).click();
    }
    // Метод проверяет, что отобразилось сообщение "Заказ оформлен":
    public boolean isOrderSuccessMessageDisplayed() {
        return driver.findElement(ORDER_SUCCESS_MESSAGE).isDisplayed();
    }

}
