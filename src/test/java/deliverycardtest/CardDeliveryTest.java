package deliverycardtest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import datagenerator.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    DataGenerator client;
    @BeforeEach
    public void setup() {
        //Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        client = DataGenerator.generateClient(3, "ru");
    }

    @Test
    void changeDeliveryDateTest() {
       String newDate = DataGenerator.generateDate(5);
        $("[data-test-id='city'] input").setValue(client.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(client.getData());
        $("[data-test-id='name'] input").setValue(client.getName());
        $("[data-test-id='phone'] input").setValue(client.getPhone());
        $("[data-test-id='agreement']").click();
        $(byXpath(".//*[contains(@class,'button button_view_extra')]")).click();
        $(byXpath(".//*[@class='notification__content']")).shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + client.getData()));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(newDate);
        $(byXpath(".//*[contains(@class,'button button_view_extra')]")).click();
        $(byXpath(".//*[text()='Перепланировать']")).click();
        $(byXpath(".//*[@class='notification__content']")).shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + newDate));
    }
}