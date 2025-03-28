package userInput;

import entity.*;
import io.bretty.console.table.*;

import java.util.List;

public class TablePrinting {
    static final int minTableWidth = 70;

    static public void printMessages(List<String> messages) {
        if(messages == null || messages.isEmpty()) return;
        messages = messages.stream().map(x -> " " + x + " ").toList();
        var longestMessageLength = messages.stream().mapToInt(String::length).max().orElse(0);
        var lineLength = Math.max(longestMessageLength, minTableWidth);
        var tableHeader = "┌" + ("—".repeat(lineLength)) + "┐";
        var tableBottom = "└" + "—".repeat(lineLength) + "┘";
        var tableHeaderBottom = "|" + "—".repeat(lineLength) + "|";

        System.out.println(tableHeader);
        printLine(messages.get(0), lineLength);
        if (messages.size() > 1) {
            System.out.println(tableHeaderBottom);
            for (int i = 1; i < messages.size(); i++) {
                printLine(messages.get(i), lineLength);
            }
        }

        System.out.println(tableBottom);
    }

    private static void printLine(String message, int longestMessageLength) {
        var spaces = " ".repeat(longestMessageLength - message.length());
        System.out.println("|" + message + spaces + "|");
    }

    static public void printTable(List<? extends Item> collection) {
        if (collection != null && !collection.isEmpty()) {
            Item firstItem = collection.get(0);

            Table table = null;
            if (firstItem instanceof Barrel){
                table = getBarrelsTable(collection);
            }
            if (firstItem instanceof Animal){
                table = getAnimalsTable(collection);
            }
            if (firstItem instanceof Person){
                table = getPersonsTable(collection);
            }

            if (table != null){
                var lineLength = table.toString().length() / (collection.size() + 1);
                var tableHeader = "┌" + ("—".repeat(lineLength - 2)) + "┐";
                var tableBottom = "└" + "—".repeat(lineLength - 2) + "┘";
                System.out.println(tableHeader);
                System.out.println(table);
                System.out.println(tableBottom);
            }
            else {
                System.out.println("Something went wrong during table printing! The item type does not match.");
            }
        } else {
            System.out.println("Couldn't print the collection because it was null or empty.");
        }
    }

    private static Table getPersonsTable(List<? extends Item> collection) {
        var lastNameHeader = "Last Name (III)";
        var ageHeader = "Age (II)";
        var genderHeader = "Gender (I)";

        var persons = collection.stream().map(b -> (Person) b).toList();

        ColumnFormatter<Number> ageFormatter = ColumnFormatter.number(Alignment.LEFT, ageHeader.length(), Precision.ZERO);
        ColumnFormatter<String> lastNameFormatter = ColumnFormatter.text(Alignment.LEFT, lastNameHeader.length());
        ColumnFormatter<String> genderFormatter = ColumnFormatter.text(Alignment.LEFT, genderHeader.length());

        Integer[] ages = persons.stream().map(Person::getAge).toArray(Integer[]::new);
        String[] lastNames = persons.stream().map(Person::getLastName).toArray(String[]::new);
        String[] genders = persons.stream().map(person -> person.getGender().toString())
                .toArray(String[]::new);

        var builder = new Table.Builder(lastNameHeader, lastNames, lastNameFormatter);
        builder.addColumn(ageHeader, ages, ageFormatter);
        builder.addColumn(genderHeader, genders, genderFormatter);
        return builder.build();
    }

    private static Table getAnimalsTable(List<? extends Item> collection) {
        var idHeader = "Animal ID (IV)";
        var speciesHeader = "Species (I)";
        var eyeColorHeader = "Eye Color (II)";
        var furHeader = "Has Fur (III)";

        var animals = collection.stream().map(b -> (Animal) b).toList();

        var columnLength = idHeader.length();
        ColumnFormatter<String> stringFormatter = ColumnFormatter.text(Alignment.LEFT, columnLength);

        String[] ids = animals.stream().map(animal -> ((Object) animal.getId()).toString()).toArray(String[]::new);
        String[] eyeColors = animals.stream().map(person -> person.getEyeColor().toString())
                .toArray(String[]::new);
        String[] species = animals.stream().map(Animal::getAnimalSpecies).toArray(String[]::new);
        String[] haveFur = animals.stream().map(animal -> ((Object) animal.isHasFur()).toString()).toArray(String[]::new);



        var builder = new Table.Builder(idHeader, ids, stringFormatter);
        builder.addColumn(speciesHeader, species, stringFormatter);
        builder.addColumn(eyeColorHeader, eyeColors, stringFormatter);
        builder.addColumn(furHeader, haveFur, stringFormatter);
        return builder.build();
    }

    private static Table getBarrelsTable(List<? extends Item> collection) {
        var volumeHeader = "Volume (I)";
        var storedHeader = "Stored Contents (II)";
        var bodyHeader = "Body Material (III)";

        var barrels = collection.stream().map(b -> (Barrel) b).toList();

        ColumnFormatter<Number> volumeFormatter = ColumnFormatter.number(Alignment.LEFT, volumeHeader.length(), Precision.TWO);
        ColumnFormatter<String> stringFormatter = ColumnFormatter.text(Alignment.LEFT, storedHeader.length());

        Double[] volumes = barrels.stream().map(Barrel::getVolume).toArray(Double[]::new);
        String[] contents = barrels.stream().map(Barrel::getStoredContents).toArray(String[]::new);
        String[] materials = barrels.stream().map(barrel -> barrel.getBodyMaterial().toString())
                .toArray(String[]::new);

        var builder = new Table.Builder(volumeHeader, volumes, volumeFormatter);
        builder.addColumn(storedHeader, contents, stringFormatter);
        builder.addColumn(bodyHeader, materials, stringFormatter);
        return builder.build();
    }
}
