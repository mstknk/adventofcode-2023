package day7;

import day6.Day6;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day7_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day7.getPart1Result(data);

        assertEquals(6440, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day7_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day7.getPart2Result(data);

        assertEquals(5905, result);
    }
}