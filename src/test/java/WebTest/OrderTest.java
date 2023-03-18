package WebTest;

import WebPages.MainPage;
import WebPages.OrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest extends BasicTest {
    private final String ORDER_BUTTON;
private final String NAME;
private final String SURNAME;
private final String ADDRESS;
private final String METRO;
private final String PHONE_NUMBER;
private final String START_DATE;
private final int ORDER_DAYS;
private final String COLOR;
private final String COMMENT;

    public OrderTest(String ORDER_BUTTON, String NAME, String SURNAME, String ADDRESS, String METRO, String PHONE_NUMBER, String START_DATE, int ORDER_DAYS, String COLOR, String COMMENT) {
        this.ORDER_BUTTON = ORDER_BUTTON;
        this.NAME = NAME;
        this.SURNAME = SURNAME;
        this.ADDRESS = ADDRESS;
        this.METRO = METRO;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.START_DATE = START_DATE;
        this.ORDER_DAYS = ORDER_DAYS;
        this.COLOR = COLOR;
        this.COMMENT = COMMENT;
    }

    @Parameterized.Parameters
    //Набор тестовых данных
    public static Object[][] checkOrder() {
        return new Object[][]{
                // Заказ через верхнюю кнопку. Все поля получают допустимые значения.
                {"UPPER_ORDER_BUTTON", "Александр", "Пушкин", "Мойка,12", "Маяковская", "89211111111", "18.03.2023", 1, "black", "У Лукоморья дуб зеленый"},
                // Заказ через нижнюю кнопку. Значения заданы только для обязательных полей.
                {"LOW_ORDER_BUTTON", "Сергей", "Есенин", "Санкт-Петербург", "Чеховская", "89215555555", "27.03.2023", 2, "", ""},
        };
    }
        @Test
        public void checkOrderTest() {
            MainPage mainPage = new MainPage(driver);
            mainPage.open();
            mainPage.clickAcceptCookiesButton();

            // Клик по указанной в параметре кнопке "Заказать":
            if ("UPPER_ORDER_BUTTON".equals(ORDER_BUTTON)) {
                mainPage.clickOrderUpperButton();
            } else if ("LOW_ORDER_BUTTON".equals(ORDER_BUTTON)) {
                //mainPage.scrollToQAASection();
                mainPage.clickOrderLowButton();
            }

            OrderPage orderPage = new OrderPage(driver);

            //заполняем поля заказа на шаге "Для кого самокат":
            orderPage.inputName(NAME);
            orderPage.inputSurname(SURNAME);
            orderPage.inputAddress(ADDRESS);
            orderPage.inputMetro(METRO);
            orderPage.inputPhone(PHONE_NUMBER);

            //клик кнопки "Далее"
            orderPage.clickNextStepButton();

            //заполняем поля заказа на шаге "Про аренду":
            orderPage.setStartDateInput(START_DATE);
            orderPage.setDurationDropDownListInput(ORDER_DAYS);
            orderPage.setColorCheckbox(COLOR);
            orderPage.setCommentsInput(COMMENT);

            //клик по кнопкам "Заказать" и подтверждения заказа "Да":
            orderPage.clickOrderFinalButton();
            orderPage.clickOrderConfirmationButton();


            //Проверка, что появилось окно подтверждения создания заказа "Заказ оформлен":
            Assert.assertTrue("Ожидаем появления подтверждения \"Заказ оформлен\".",orderPage.isOrderSuccessMessageDisplayed());
        }
    }
