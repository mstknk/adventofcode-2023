package day9;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day9_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day9.getPart1Result(data);

        assertEquals(114, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day9_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day9.getPart2Result(data);

        assertEquals(2, result);
    }
}