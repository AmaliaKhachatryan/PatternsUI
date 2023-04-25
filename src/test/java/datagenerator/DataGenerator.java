package datagenerator;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Value
public class DataGenerator {

    private DataGenerator() {

    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var cities = new String[]{
                "Москва", "Томск", "Уфа", "Тверь", "Тула", "Казань", "Самара", "Хабаровск", "Омск", "Орел"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class ClientInfo {
        private ClientInfo() {

        }

        public static DataClient clientInfo(int days, String locale) {
            return new DataClient(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class DataClient {
        String city;
        String name;
        String phone;
    }
}

