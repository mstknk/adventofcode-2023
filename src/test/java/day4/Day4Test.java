package day4;

import day3.Day3;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day4_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day4.getPart1Result(data);

        assertEquals(13, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day4_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day4.getPart2Result(data);

        assertEquals(30, result);
    }
}