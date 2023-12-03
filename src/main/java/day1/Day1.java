package day1;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day1_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        return data.stream().mapToLong(Day1::getCalibration).sum();
    }

    private static Long getCalibration(String str) {
        return Long.valueOf(getFirstDigit(str) + getLastDigit(str));
    }

    private static String getLastDigit(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (Character.isDigit(str.charAt(i))) {
                return String.valueOf(str.charAt(i));
            }
        }
        return null;
    }

    private static String getFirstDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return String.valueOf(str.charAt(i));
            }
        }
        return null;
    }

    public static long getPart2Result(List<String> data) {
        return data.stream().mapToLong(Day1::getCalibrationWithLetters).sum();
    }

    private static long getCalibrationWithLetters(String str) {
        return Long.valueOf(getFirstDigitWithLetters(str) + getLastDigitWithLetters(str));
    }

    private static String getFirstDigitWithLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return String.valueOf(str.charAt(i));
            } else {
                Number number = Number.ifContains(str.substring(0, i + 1));
                if (number != null) {
                    return number.getTag();
                }
            }
        }
        return null;
    }

    private static String getLastDigitWithLetters(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (Character.isDigit(str.charAt(i))) {
                return String.valueOf(str.charAt(i));
            } else {
                Number number = Number.ifContains(str.substring(i));
                if (number != null) {
                    return number.getTag();
                }
            }
        }
        return null;
    }

}
