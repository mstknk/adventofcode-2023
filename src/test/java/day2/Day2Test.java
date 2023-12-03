package day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day2_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day2.getPart1Result(data);

        assertEquals(8, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day2_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day2.getPart2Result(data);

        assertEquals(2286, result);
    }
}