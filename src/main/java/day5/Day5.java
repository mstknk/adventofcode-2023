package day5;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day5 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day5_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        List<Long> seeds = new ArrayList<>();
        String seed = data.get(0).replace("seeds:", "").trim();
        Stream.of(seed.split(" ")).forEach(e -> seeds.add(Long.valueOf(e)));
        Long minLocation = Long.MAX_VALUE;
        List<Seed> soils = getSeeds(data, "seed-to-soil");
        List<Seed> fertilizers = getSeeds(data, "soil-to-fertilizer");
        List<Seed> waters = getSeeds(data, "fertilizer-to-water");
        List<Seed> lights = getSeeds(data, "water-to-light");
        List<Seed> temperatures = getSeeds(data, "light-to-temperature");
        List<Seed> humiditys = getSeeds(data, "temperature-to-humidity");
        List<Seed> locations = getSeeds(data, "humidity-to-location");


        for (int i = 0; i < seeds.size(); i++) {
            Long soil = getSeedNumber(soils, seeds.get(i));
            Long fertilizer = getSeedNumber(fertilizers, soil);
            Long water = getSeedNumber(waters, fertilizer);
            Long light = getSeedNumber(lights, water);
            Long temperature = getSeedNumber(temperatures, light);
            Long humidity = getSeedNumber(humiditys, temperature);
            Long location = getSeedNumber(locations, humidity);
            System.out.println(seeds.get(0) + " > " + soil + " > " + fertilizer + " > " + water + " > " + light + " > " + temperature + " > " + humidity + " > " + location);
            if (minLocation > location) {
                minLocation = location;
            }

        }
        return minLocation;
    }


    private static List<Seed> getSeeds(List<String> data, String mapName) {
        List<Seed> seeds = new ArrayList<>();
        boolean found = false;
        for (String line : data) {
            if (found && line.isBlank()) {
                break;
            }
            if (found) {
                String[] sd = line.split(" ");
                Long dest = Long.valueOf(sd[0]);
                Long source = Long.valueOf(sd[1]);
                Long aLong = Long.valueOf(sd[2]);
                Seed seed = new Seed(dest, source, aLong);
                seeds.add(seed);
            }
            if (line.startsWith(mapName)) {
                found = true;
            }

        }
        return seeds;
    }


    private static Long getSeedNumber(List<Seed> seeds, Long number) {
        for (Seed seed : seeds) {
            Long dest = seed.getDest();
            Long source = seed.getSource();
            Long aLong = seed.getaLong();
            if (number >= source && (source + aLong - 1 >= number)) {
                    long s = (number - source + dest);
                    if (s > 0) {
                        return s;
                    }

            }

        }
        return number;
    }


    public static long getPart2Result(List<String> data) {
        List<Long> seeds = new ArrayList<>();
        String seed = data.get(0).replace("seeds:", "").trim();
        Stream.of(seed.split(" ")).forEach(e -> seeds.add(Long.valueOf(e)));
        Long minLocation = Long.MAX_VALUE;
        List<Seed> soils = getSeeds(data, "seed-to-soil");
        List<Seed> fertilizers = getSeeds(data, "soil-to-fertilizer");
        List<Seed> waters = getSeeds(data, "fertilizer-to-water");
        List<Seed> lights = getSeeds(data, "water-to-light");
        List<Seed> temperatures = getSeeds(data, "light-to-temperature");
        List<Seed> humiditys = getSeeds(data, "temperature-to-humidity");
        List<Seed> locations = getSeeds(data, "humidity-to-location");


        for (int i = 0; i < seeds.size(); i++) {
            System.out.println(seeds.get(i) + " " + seeds.get(i) + seeds.get(i + 1));
            for (Long j = seeds.get(i); j < seeds.get(i) + seeds.get(i + 1); j++) {
                Long soil = getSeedNumber(soils, j);
                Long fertilizer = getSeedNumber(fertilizers, soil);
                Long water = getSeedNumber(waters, fertilizer);
                Long light = getSeedNumber(lights, water);
                Long temperature = getSeedNumber(temperatures, light);
                Long humidity = getSeedNumber(humiditys, temperature);
                Long location = getSeedNumber(locations, humidity);
                //     System.out.println(seedLong + " > " + soil + " > " + fertilizer + " > " + water + " > " + light + " > " + temperature + " > " + humidity + " > " + location);
                if (minLocation > location) {
                    minLocation = location;
                }
            }
            i++;

        }
        return minLocation;
    }
}