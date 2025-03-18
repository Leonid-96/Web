import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardApplicationNegativeTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void testLatinName() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Ivanov Aleksei");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void testEmptyName() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void testPhone10() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Алексей");
        form.$("[data-test-id=phone] input").setValue("+7982462250");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void testPhone12() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Алексей");
        form.$("[data-test-id=phone] input").setValue("+798246225055");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void testPhoneWithoutPlus() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Алексей");
        form.$("[data-test-id=phone] input").setValue("79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void testEmptyPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Алексей");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void testPhoneWithoutCheckbox() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Алексей");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$(byClassName("button")).click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").isDisplayed();
    }
}