package Data.Services;

import java.util.Map;
import java.util.Scanner;

public class DataGenerationServiceBuilder {
    private long seedNumber;
    private String locale = "ru";
    private static final Map<String, String> LOCALE_MAP = Map.of(
            "by", "be_BY",
            "en", "en-US",
            "pl", "pl",
            "ru", "ru_RU",
            "tr", "tr"
    );

    private static final Map<String, String> SHOW_LOCALES = Map.of(
            "by", "for Belarusian",
            "en", "for US English",
            "pl", "for Polish",
            "ru", "for Russian",
            "tr", "for Turkish"
    );

    public void setSeedNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Seed number into the console and press Enter.\n" +
                "If you want to get a random Seed number, press Enter.");
        System.out.print("Enter your seed number here: ");
        String input = scanner.nextLine();
        try {
            if (!input.isEmpty()) {
                seedNumber = Long.parseLong(input);
            } else {
                seedNumber = System.currentTimeMillis();
            }
        } catch (NumberFormatException e) {
            System.out.println("It was an incorrect number. The Seed number will be generate automatically.");
        }

        System.out.println("Your Seed number is " + seedNumber +
                ". You can get the same data by entering this Seed number next time. \n");
    }

    public void setLocale() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter two characters to select a language (location). " +
                "The default location is \"ru\". List of all locations:");
        SHOW_LOCALES.forEach((key, value) ->
                System.out.printf("Use the %s code %s%n", key, value));
        System.out.print("\nEnter your location here: ");
        String input = scanner.nextLine();
        if (LOCALE_MAP.containsKey(input.toLowerCase())) {
            locale = input;
        }
    }

    public DataGenerationService getService() {
        return new DataGenerationService(seedNumber, locale);
    }
}
