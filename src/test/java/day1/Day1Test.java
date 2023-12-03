package day1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day1_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day1.getPart1Result(data);

        assertEquals(142, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day1_input-2.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day1.getPart2Result(data);

        assertEquals(281, result);
    }
}