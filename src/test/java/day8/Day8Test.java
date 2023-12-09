package day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {
    @Test
    void assertThatPart1Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day8_input.txt");
        List<String> data = Files.readAllLines(path);
        long result = Day8.getPart1Result(data);

        assertEquals(6, result);
    }
    @Test
    void assertThatPart2Result() throws IOException {
        Path path = FileSystems.getDefault().getPath("src/test/resources/day8_input_2.txt");
        List<String> data = Files.readAllLines(path);
        BigInteger result = Day8.getPart22Result(data);

        assertEquals(6, result);
    }
}