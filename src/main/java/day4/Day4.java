package day4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day4_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        Long sum = 0L;
        for (String str : data) {
            str = str.substring(str.indexOf(":") + 1);
            String[] s = str.split("\\|");

            List<Integer> winningNumbers = convertNumbers(s[0]);
            List<Integer> numbers = convertNumbers(s[1]);
            sum += getPoints(winningNumbers, numbers);
        }
        return sum;

    }

    private static Long getPoints(List<Integer> winningNumbers, List<Integer> numbers) {
        Long points = 0L;
        for (Integer wi : winningNumbers) {
            if (numbers.contains(wi)) {
                points = points == 0L ? 1 : points * 2;
            }
        }
        return points > 0L ? points : 0;
    }

    private static List<Integer> convertNumbers(String s) {
        String[] numberStr = s.trim().split(" ");
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < numberStr.length; i++) {
            String s1 = numberStr[i];
            if (!s1.isBlank()) {
                numbers.add(Integer.valueOf(s1));
            }
        }
        return numbers;
    }

    public static long getPart2Result(List<String> lines) {
        final int[] instances = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            instances[i] = 1;
        }

        for (int i = 0; i < lines.size(); i++) {
            String str = lines.get(i);
            str = str.substring(str.indexOf(":") + 1);
            String[] s = str.split("\\|");

            List<Integer> winningNumbers = convertNumbers(s[0]);
            List<Integer> numbers = convertNumbers(s[1]);
            int count = 0;
            for (Integer myNumber : numbers) {
                for (Integer winningNumber : winningNumbers) {
                    if (myNumber.equals(winningNumber)) {
                        ++count;
                        break;
                    }
                }
            }
            for (int j = 0; j < count; j++) {
                int cardToCopy = i + j + 1;
                if (cardToCopy < instances.length) {
                    instances[cardToCopy] += instances[i];
                }
            }
        }

        int result = 0;
        for (int instance : instances) {
            result += instance;
        }

        return result;
    }
}