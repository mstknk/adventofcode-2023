package day2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day2_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        Long sum = 0L;
        for (String game : data) {
            String id = game.substring(game.indexOf("Game") + 5, game.indexOf(":"));
            String gameNormalised = game.substring(game.indexOf(":") + 1, game.length());
            String[] gameBlock = gameNormalised.split(";");
            if (isGamePossible(gameBlock)) {
                sum += Long.valueOf(id);
            }
        }
        return sum;

    }

    private static boolean isGamePossible(String[] gameBlock) {
        for (String strTemp : gameBlock) {
            String[] cubes = strTemp.split(",");
            for (String cube : cubes) {
                if (!checkCube(cube.trim())) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkCube(String cube) {
        Integer cubeNumber = Integer.valueOf(cube.substring(0, cube.indexOf(" ")));
        if (cube.contains("green") && cubeNumber < 14) {
            return true;
        } else if (cube.contains("red") && cubeNumber < 13) {
            return true;
        } else if (cube.contains("blue") && cubeNumber < 15) {
            return true;
        }
        return false;
    }

    public static Long getPart2Result(List<String> data) {
        Long sum = 0L;
        for (String game : data) {
            String gameNormalised = game.substring(game.indexOf(":") + 1, game.length());
            String[] gameBlock = gameNormalised.split(";");
            sum += multipliedFewestNumberOfCubesOfEeachColor(gameBlock);
        }
        return sum;
    }

    private static Long multipliedFewestNumberOfCubesOfEeachColor(String[] gameBlock) {
        long green = 0;
        long red = 0;
        long blue = 0;

        for (String strTemp : gameBlock) {
            String[] cubes = strTemp.split(",");
            for (String cube : cubes) {
                cube = cube.trim();
                Integer cubeNumber = Integer.valueOf(cube.substring(0, cube.indexOf(" ")));
                if (cube.contains("green")) {
                    green = cubeNumber > green ? cubeNumber : green;
                } else if (cube.contains("red")) {
                    red = cubeNumber > red ? cubeNumber : red;
                } else if (cube.contains("blue")) {
                    blue = cubeNumber > blue ? cubeNumber : blue;
                }
            }
        }
        return blue * red * green;
    }

}
