package day6;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day6 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day6_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        Long sum = 1L;
        List<Long> times = new ArrayList<>();
        List<Long> distances = new ArrayList<>();
        String time = data.get(0).replace("Time:", "").trim();
        String distance = data.get(1).replace("Distance:", "").trim();
        Stream.of(time.split("   ")).forEach(e -> times.add(Long.valueOf(e.trim())));
        Stream.of(distance.split("   ")).forEach(e -> distances.add(Long.valueOf(e.trim())));
        for (int t = 0; t < times.size(); t++) {
            Long count = 0L;
            for (int i = 0; i < times.get(t) + 1; i++) {
                Long x = calculateWinning(times.get(t), i);
                if (x > distances.get(t)) {
                    count++;
                }
            }
            sum = sum * count;

        }
        return sum;

    }

    private static Long calculateWinning(Long time, int index) {
        if (index == 0) {
            return 0L;
        }
        if (index == 1) {
            return time - 1;
        }

        Long millimeters = Long.valueOf(index);
        return (time - millimeters) * millimeters;
    }

    public static long getPart2Result(List<String> lines) {
        List<String> data = new ArrayList<>();
        for (String str : lines) {
            data.add(str.replace(" ", ""));
        }

        return getPart1Result(data);
    }
}