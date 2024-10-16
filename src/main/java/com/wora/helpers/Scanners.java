package com.wora.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class Scanners {
    private final static Scanner scanner = new Scanner(System.in);

    public static int scanInt(String prompt) {
        System.out.println(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Invalid value. Please enter a valid integer.");
            return scanInt(prompt);
        }
    }

    public static double scanDouble(String prompt) {
        System.out.println(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid value. Please try again.");
            return scanDouble(prompt);
        }
    }

    public static String scanString(String prompt) {
        System.out.println(prompt);
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            System.out.println("invalid value, please try again.");
            return scanString(prompt);
        }
    }

    public static boolean scanBoolean(String prompt) {
        System.out.println(prompt);
        final String input = scanner.nextLine().trim().toLowerCase();

        return switch (input) {
            case "true", "y", "1", "yes", "oui" -> true;
            case "false", "n", "0", "non", "no" -> false;
            default -> {
                System.out.println("Invalid input: please enter 'yes'/'no', 'y'/'n', 'true'/'false', '1'/'0', or 'oui'/'non'.");
                yield scanBoolean(prompt);
            }
        };
    }
    public static UUID scanUUID(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine().trim();

        while (!isValidUUID(input)) {
            System.out.println("Invalid UUID format. Please enter a valid UUID.");
            input = scanner.nextLine().trim();
        }
        return UUID.fromString(input);
    }

    private static boolean isValidUUID(String input) {
        try {
            UUID.fromString(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static LocalDate scanDate(String prompt, String format) {
        System.out.println(prompt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String input = scanner.nextLine().trim();

        while (!isValidDate(input, format) || isPastDate(LocalDate.parse(input, formatter).atStartOfDay())) {
            if (!isValidDate(input, format)) {
                System.out.println("Invalid date format. Please enter a valid date in the format " + format);
            } else if (isPastDate(LocalDate.parse(input, formatter).atStartOfDay())) {
                System.out.println("The date cannot be earlier than today. Please enter a future or today’s date.");
            }
            input = scanner.nextLine().trim();
        }

        return LocalDate.parse(input, formatter);
    }
    private static boolean isPastDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    public static <T extends Enum<T>> T scanEnum(String prompt, Class<T> enumType) {
        System.out.println(prompt );
        String input = scanner.nextLine().trim();

        try {
            return Enum.valueOf(enumType, input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid value. Please enter a valid option from the enum.");
            return scanEnum(prompt, enumType);
        }
    }

    //UPDATES SCANNERS
    public static int updateInt(String prompt, int existingValue) {
        System.out.println(prompt + " (Current value: " + existingValue + ") or press Enter to keep the current value:");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return existingValue;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Keeping the existing value.");
            return existingValue;
        }
    }

    public static double updateDouble(String prompt, double existingValue) {
        System.out.println(prompt + " (Current value: " + existingValue + ") or press Enter to keep the current value:");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return existingValue;
        }
        try {
            return Double.parseDouble(input);
        } catch (RuntimeException e) {
            System.out.println("Invalid input. Keeping the existing value.");
            return existingValue;
        }
    }

    public static String updateString(String prompt, String existingValue) {
        System.out.println(prompt + " (Current value: " + existingValue + ") or press Enter to keep the current value:");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? existingValue : input;
    }

    public static boolean updateBoolean(String prompt, boolean existingValue) {
        System.out.println(prompt + " (Current value: " + (existingValue ? "Yes" : "No") + ") or press Enter to keep the current value:");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return existingValue;
        }
        return switch (input.toLowerCase()) {
            case "true", "y", "1", "yes", "oui" -> true;
            case "false", "n", "0", "non", "no" -> false;
            default -> {
                System.out.println("Invalid input. Keeping the existing value.");
                yield existingValue;
            }
        };
    }

    public static <T extends Enum<T>> T updateEnum(String prompt, T existingValue, Class<T> enumType) {
        System.out.println(prompt + " (Current value: " + existingValue + ") or press Enter to keep the current value:");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return existingValue;
        }
        try {
            return Enum.valueOf(enumType, input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid value. Keeping the current value.");
            return existingValue;
        }
    }


    public static UUID updateUUID(String prompt, UUID existingValue) {
        System.out.println(prompt + " (Current value: " + existingValue + ") or press Enter to keep the current value:");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return existingValue;
        }
        while (!isValidUUID(input)) {
            System.out.println("Invalid UUID format. Please enter a valid UUID or press Enter to keep the current value.");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return existingValue;
            }
        }
        return UUID.fromString(input);
    }

    public static LocalDateTime updateDate(String prompt, String format, LocalDateTime existingValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        System.out.println(prompt + " (Current value: " + existingValue.format(formatter) + ") or press Enter to keep the current value:");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return existingValue;
        }
        while (!isValidDate(input, format) || isPastDate(LocalDateTime.parse(input, formatter))) {
            if (!isValidDate(input, format)) {
                System.out.println("Invalid date format. Please enter a valid date in the format " + format);
            } else if (isPastDate(LocalDateTime.parse(input, formatter))) {
                System.out.println("The date cannot be earlier than today. Please enter a future or today’s date.");
            }
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return existingValue;
            }
        }
        return LocalDateTime.parse(input, formatter);
    }



    private static boolean isValidDate(String date, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isPastDate(LocalDateTime date) {
        return date.isBefore(LocalDateTime.now());
    }

}