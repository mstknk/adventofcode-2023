package day3;

import day2.Day2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day3_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day3.getPart1Result(data);

        assertEquals(4361, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day3_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day3.getPart2Result(data);

        assertEquals(467835, result);
    }
}