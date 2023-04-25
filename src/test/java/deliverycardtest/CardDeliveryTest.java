package deliverycardtest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static datagenerator.DataGenerator.ClientInfo.clientInfo;
import static datagenerator.DataGenerator.generateDate;

public class CardDeliveryTest {

    @BeforeEach
    public void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
   }

    @Test
    void changeDeliveryDateTest() {
        var client = clientInfo(3, "ru");
        String date = generateDate(3);
       String newDate = generateDate(5);
        $("[data-test-id='city'] input").setValue(client.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue(client.getName());
        $("[data-test-id='phone'] input").setValue(client.getPhone());
        $("[data-test-id='agreement']").click();
        $(byXpath(".//*[contains(@class,'button button_view_extra')]")).click();
        $(byXpath(".//*[@class='notification__content']")).shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + date));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(newDate);
        $(byXpath(".//*[contains(@class,'button button_view_extra')]")).click();
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $(byXpath(".//*[text()='Перепланировать']")).click();
        $(byXpath(".//*[@class='notification__content']")).shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + newDate));
    }
}