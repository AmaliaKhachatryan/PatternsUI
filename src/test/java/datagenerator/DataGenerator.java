package datagenerator;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Value
public class DataGenerator {
    String city;
    String data;
    String name;
    String phone;

    public static DataGenerator generateClient(int days, String locale) {
        return new DataGenerator(generateCity(), generateDate(days), generateName(locale), generatePhone(locale));
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
}

