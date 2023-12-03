package day3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day3_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        Long sum = 0L;
        for (int i = 0; i < data.size(); i++) {
            sum += notPartNumbers(data, i);
        }
        return sum;

    }


    public static Long getPart2Result(List<String> data) {
        Long sum = 0L;
        for (int i = 0; i < data.size(); i++) {
            String s = data.get(i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '*') {
                    sum += getTwoPart(data, i, j);
                }
            }
        }
        return sum;
    }

    private static Long getTwoPart(List<String> data, int i, int j) {
        List<Number> current = getCurrentPosition(data.get(i), j);
        List<Number> upper = getUpperPosition(data.get(i - 1), j);
        List<Number> down = getDownPosition(data.get(i + 1), j);
        List<Number> numbers = Stream.of(current, upper, down)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        if (numbers.size() == 2) {
            return numbers.stream().mapToLong(x -> Long.valueOf(x.getNumber())).reduce(1, Math::multiplyExact);
        }

        return 0L;
    }

    private static List<Number> getDownPosition(String data, int j) {
        List<Number> down = convertNumber(data);
        List<Number> numbers = new ArrayList<>();
        for (Number number : down) {
            if (number.getFirstIndex() <= j && number.getLastIndex() >= j) {
                numbers.add(number);
            }
            if (number.getFirstIndex() == j + 1) {
                numbers.add(number);
            }
            if (number.getLastIndex() == j - 1) {
                numbers.add(number);
            }
        }
        return numbers;
    }

    private static List<Number> getUpperPosition(String data, int j) {
        List<Number> upper = convertNumber(data);
        List<Number> numbers = new ArrayList<>();
        for (Number number : upper) {
            if (number.getFirstIndex() <= j && number.getLastIndex() >= j) {
                numbers.add(number);
            }
            if (number.getFirstIndex() == j + 1) {
                numbers.add(number);
            }
            if (number.getLastIndex() == j - 1) {
                numbers.add(number);
            }
        }
        return numbers;
    }

    private static List<Number> getCurrentPosition(String data, int j) {
        List<Number> current = convertNumber(data);
        List<Number> numbers = new ArrayList<>();
        for (Number number : current) {
            if (number.getLastIndex() == j - 1) {
                numbers.add(number);
            }
            if (number.getFirstIndex() == j + 1) {
                numbers.add(number);
            }
        }
        return numbers;
    }

    private static Long notPartNumbers(List<String> data, int i) {
        List<Number> numbers = convertNumber(data.get(i));
        long sum = 0;
        for (Number number : numbers) {
            if (isPart(data, number, i)) {
                sum += Long.valueOf(number.getNumber());
            }
        }

        return sum;
    }

    private static boolean isPart(List<String> data, Number number, int i) {
        if (checkLeft(data, number, i)) {
            return true;
        }
        if (checkRight(data, number, i)) {
            return true;
        }

        return false;
    }

    private static boolean checkLeft(List<String> data, Number number, int i) {
        // left up diagonal
        if (i > 0 && number.getFirstIndex() > 0 && checkSpecial(data.get(i - 1).charAt(number.getFirstIndex() - 1))) {
            return true;
        }

        // left bottom
        if (data.size() > i + 1 && checkSpecial(data.get(i + 1).charAt(number.getFirstIndex()))) {
            return true;
        }

        // left up
        if (i > 0 && number.getFirstIndex() > 0 && checkSpecial(data.get(i - 1).charAt(number.getFirstIndex()))) {
            return true;
        }
        // left bottom diagonal
        if (data.size() > i + 1 && number.getFirstIndex() > 0 && checkSpecial(data.get(i + 1).charAt(number.getFirstIndex() - 1))) {
            return true;
        }

        // left next
        if (number.getFirstIndex() > 0 && checkSpecial(data.get(i).charAt(number.getFirstIndex() - 1))) {
            return true;
        }
        return false;
    }

    private static boolean checkRight(List<String> data, Number number, int i) {

        // right up diagonal
        if (i > 0 && data.get(i).length() > number.getLastIndex() + 1 && checkSpecial(data.get(i - 1).charAt(number.getLastIndex() + 1))) {
            return true;
        }


        // right bottom
        if (data.size() > i + 1 && checkSpecial(data.get(i + 1).charAt(number.getLastIndex()))) {
            return true;
        }

        // right up
        if (i > 0 && checkSpecial(data.get(i - 1).charAt(number.getLastIndex()))) {
            return true;
        }
        // right bottom diagonal
        if (data.size() > i + 1 && data.get(i).length() > number.getLastIndex() + 1 && checkSpecial(data.get(i + 1).charAt(number.getLastIndex() + 1))) {
            return true;
        }

        // right next
        if (data.get(i).length() > number.getLastIndex() + 1 && checkSpecial(data.get(i).charAt(number.getLastIndex() + 1))) {
            return true;
        }

        // left up diagonal
        if (i > 0 && checkSpecial(data.get(i - 1).charAt(number.getLastIndex() - 1))) {
            return true;
        }

        // left bottom diagonal
        if (data.size() > i + 1 && number.getLastIndex() > 0 && checkSpecial(data.get(i + 1).charAt(number.getLastIndex() - 1))) {
            return true;
        }
        return false;
    }

    private static boolean checkSpecial(char charAt) {
        return !Character.isDigit(charAt) && charAt != '.';
    }

    private static List<Number> convertNumber(String s) {
        List<Number> numberList = new ArrayList<>();
        int i = 0;
        int first = 0;
        String number = "";
        boolean found = false;
        while (s.length() > i) {

            if (Character.isDigit(s.charAt(i))) {
                if (!found) {
                    first = i;
                }
                number += String.valueOf(s.charAt(i));
                found = true;
            } else if (!number.isBlank()) {
                Number number1 = new Number(number, first, i - 1);
                numberList.add(number1);
                number = "";
                found = false;
            }
            i++;
        }
        if (found) {
            Number number1 = new Number(number, first, i - 1);
            numberList.add(number1);
        }
        return numberList;
    }


    public static class Number {
        private String number;
        private int firstIndex;
        private int lastIndex;

        public Number(String number, int firstIndex, int lastIndex) {
            this.number = number;
            this.firstIndex = firstIndex;
            this.lastIndex = lastIndex;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getFirstIndex() {
            return firstIndex;
        }

        public void setFirstIndex(int firstIndex) {
            this.firstIndex = firstIndex;
        }

        public int getLastIndex() {
            return lastIndex;
        }

        public void setLastIndex(int lastIndex) {
            this.lastIndex = lastIndex;
        }
    }
}
