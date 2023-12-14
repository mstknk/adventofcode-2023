package day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day11_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day11_2.getPart2Result(data, 1L);

        assertEquals(374, result);
    }


    @Test
    void assertThatPart2Result10() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day11_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day11_2.getPart2Result(data, 9L);

        assertEquals(1030, result);
    }


    @Test
    void assertThatPart2Result100() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day11_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day11_2.getPart2Result(data, 99L);

        assertEquals(8410, result);
    }
}