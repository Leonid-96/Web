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

public class CardApplicationTest {
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
    void test1() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Алексей");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void test2() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов-Ван Алексей");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void test3() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов-ван Алексей");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void test4() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Ван Алексей");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void test5() {
        open("http://localhost:9999/");
        SelenideElement form = $(byClassName("form"));
        form.$("[data-test-id=name] input").setValue("Иванов Ван-Алексей");
        form.$("[data-test-id=phone] input").setValue("+79824622505");
        form.$("[data-test-id=agreement]").click();
        form.$(byClassName("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}