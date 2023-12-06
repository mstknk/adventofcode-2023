package day6;

import day5.Day5;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day6_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day6.getPart1Result(data);

        assertEquals(288, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day6_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day6.getPart2Result(data);

        assertEquals(71503, result);
    }
}