package day11;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11_2 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day11_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart2Result(data, 1L));
        System.out.println(getPart2Result(data, 1000000L - 1L));

    }

    public static Long getPart2Result(List<String> data, Long space) {
        List<String> newList = new ArrayList<>();
        for (String s : data) {
            newList.add(s);

        }
        int n = 1;
        List<Number> numbers = new ArrayList<>();
        for (int i = 0; i < newList.size(); i++) {
            for (int j = 0; j < newList.get(0).length(); j++) {
                //System.out.print(newList.get(i).charAt(j));
                if (newList.get(i).charAt(j) == '#') {
                    numbers.add(new Number(n, Long.valueOf(j), Long.valueOf(i)));
                    n++;
                }
            }
        }

        int y = 0;
        Long columnFind = 0L;
        for (String s : data) {
            if (!s.contains("#")) {
                for (Number number : numbers) {
                    if (number.getY() > y + columnFind) {
                        number.setY(number.getY() + space);
                    }
                }
                columnFind = columnFind + space;
            }
            y++;
        }
        Long rawFound = 0L;
        for (int i = 0; i < newList.get(0).length(); i++) {
            boolean found = false;

            for (int j = 0; j < newList.size(); j++) {
                if (newList.get(j).charAt(i) == '#') {
                    found = true;
                    break;
                }
            }
            if (!found) {
                for (Number number : numbers) {
                    if (number.getX() > i + rawFound) {
                        number.setX(number.getX() + space);
                    }
                }
                rawFound = rawFound + space;
            }
        }


        for (Number number : numbers) {
            //System.out.println(number.getNumber() + " " + number.getX() + " " + number.getY());
        }
        Long total = 0L;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                total += calculateStep(numbers.get(i), numbers.get(j));
                //System.out.println(numbers.get(i).getNumber() + ", " + numbers.get(j).getNumber() + " : " + mm);
            }
        }
        // System.out.println(mm);
        return total;

    }

    private static Long calculateStep(Number number, Number number1) {
        if (number.getY() == number1.getY()) {
            return number1.getX() - number.getX();
        }
        if (number.getX() == number1.getX()) {
            return number1.getY() - number.getY();
        }
        return Math.abs(number1.getX() - number.getX()) + Math.abs(number1.getY() - number.getY());
    }

}
