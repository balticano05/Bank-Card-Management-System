package com.effective.project.bank.card.management.system.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CardNumberGenerator {

    public static String generateCardNumber() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append("4");
        for (int i = 0; i < 14; i++) {
            sb.append(random.nextInt(10));
        }

        String partialNumber = sb.toString();

        int checkDigit = calculateCheckDigit(partialNumber);
        String fullNumber = partialNumber + checkDigit;

        return formatNumber(fullNumber);
    }

    private static int calculateCheckDigit(String partialNumber) {

        String tempNumber = partialNumber + "0";

        int[] digits = new int[tempNumber.length()];

        for (int i = 0; i < tempNumber.length(); i++) {
            digits[i] = Character.getNumericValue(tempNumber.charAt(i));
        }

        int sum = 0;
        boolean doubleDigit = true;

        for (int i = digits.length - 2; i >= 0; i--) {
            int digit = digits[i];

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return (10 - (sum % 10)) % 10;
    }

    private static String formatNumber(String number) {

        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {

            if (i > 0 && i % 4 == 0) {

                formatted.append(" ");
            }

            formatted.append(number.charAt(i));
        }

        return formatted.toString();
    }

}