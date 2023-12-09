package day9;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day9_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        Long sum = 0L;

        for (String str : data) {
            String[] asd = str.split(" ");
            List<Long> list = Arrays.asList(asd).stream().map(Long::valueOf).collect(Collectors.toList());
            sum += getNumber(list);

        }

        return sum;

    }

    private static Long getNumber(List<Long> list) {
        boolean isZero = false;
        List<Long> lasts = new ArrayList<>();
        lasts.add(list.get(list.size() - 1));

        do {
            List<Long> newList = new ArrayList<>();
            for (int i = 0; i < list.size() - 1; i++) {
                newList.add(list.get(i + 1) - list.get(i));
            }
            lasts.add(newList.get(newList.size() - 1));
            if (list.stream().allMatch(e -> e == 0)) {
                isZero = true;
            }
            list = new ArrayList<>(newList);

        } while (!isZero);

        return lasts.stream().collect(Collectors.summarizingLong(Number::longValue)).getSum();
    }

    private static Long getNumber2(List<Long> list) {
        boolean isZero = false;
        List<Long> first = new ArrayList<>();
        first.add(list.get(0));
        //printList(list);

        do {
            List<Long> newList = new ArrayList<>();
            for (int i = 0; i < list.size() - 1; i++) {
                newList.add(list.get(i + 1) - list.get(i));
            }
            first.add(newList.get(0));
            if (list.stream().allMatch(e -> e == 0)) {
                isZero = true;
            }
            //printList(newList);
            list = new ArrayList<>(newList);

        } while (!isZero);

        return extrapolatedValues(first);
    }

    private static Long extrapolatedValues(List<Long> first) {
        first.remove(first.size() - 1);
        Long num = first.get(first.size() - 2) - first.get(first.size() - 1);
        for (int i = first.size() - 2; i > 0; i--) {
            num = first.get(i - 1) - num;
            //System.out.println(num);
        }
        return num;
    }

    private static void printList(List<Long> list) {
        for (Long s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static long getPart2Result(List<String> data) {
        Long sum = 0L;

        for (String str : data) {
            String[] asd = str.split(" ");
            List<Long> list = Arrays.asList(asd).stream().map(Long::valueOf).collect(Collectors.toList());
            sum += getNumber2(list);

        }

        return sum;
    }
}