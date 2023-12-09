package day7;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day7_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        //System.out.println(getPart2Result(data));

    }

    public static Long getPart1Result(List<String> data) {
        Long sum = 0L;
        List<Card> cards = new ArrayList<>();
        for (String str : data) {
            String[] sp = str.split(" ");
            Map<String, Long> result = Arrays.stream(sp[0].split(""))
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet()
                    .stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            Type type = getType(result);
            Card card = new Card(sp[0], result, Long.valueOf(sp[1]), type);
            cards.add(card);
        }
        Collections.sort(cards, new CardComparator());
        int rank = 1;
        for (int i = 0; i < cards.size(); i++) {
            sum += rank * cards.get(i).getBid();
            rank++;
        }
        return sum;

    }

    private static long getCardValue(char c) {
        if (c == 'T') {
            return 10;
        }
        if (c == 'J') {
            return 11;
        }
        if (c == 'Q') {
            return 12;
        }
        if (c == 'K') {
            return 13;
        }
        if (c == 'A') {
            return 14;
        }
        return Character.getNumericValue(c);
    }

    private static Type getTypeWithJoker(Map<String, Long> map, String cardStr) {
        Optional<Map.Entry<String, Long>> joker = ((LinkedHashMap) map).keySet().stream().filter(e -> e.equals("J")).findFirst();
        Map.Entry<String, Long> firstEntry = map.entrySet().stream().findFirst().get();
        if (joker.isPresent() && map.get("J").longValue() < 5) {
            String key = firstEntry.getKey();
            if (!key.contains("J") && map.size() < 5) {
                map.put(key, firstEntry.getValue() + map.get("J").longValue());
                map.remove("J");
            } else {
                String higher = getHiger(cardStr);
                map.put(higher, map.get(higher).longValue() + map.get("J").longValue());
                map.remove("J");
            }
        }


        if (map.size() == 1) {
            return Type.FIVEOFKIND;
        }
        if (map.size() == 5) {
            return Type.HIGHCARD;
        }
        if (map.size() == 4) {
            return Type.ONEPAIR;
        }
        Long first = null;
        try {
            String asd = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
            first = map.get(asd).longValue();
        } catch (Exception e) {
            System.out.println("s");
        }

        if (map.size() == 2 && first == 3) {
            return Type.FULLHOUSE;
        }
        if (map.size() == 2 && first == 4) {
            return Type.FOUROFKIND;
        }

        if (map.size() == 3 && first == 3) {
            return Type.THREEOFKIND;
        }
        if (map.size() == 3 && first == 2) {
            return Type.TWOPAIR;
        }


        return null;
    }

    private static String getHiger(String cardStr) {
        cardStr = cardStr.replace("J", "");
        long higher = getCardValue(cardStr.charAt(0));
        int index = 0;
        for (int i = 1; i < cardStr.length(); i++) {
            if (higher < getCardValue(cardStr.charAt(i))) {
                higher = getCardValue(cardStr.charAt(i));
                index = i;
            }

        }
        return String.valueOf(cardStr.charAt(index));
    }

    private static Type getType(Map<String, Long> map) {
        if (map.size() == 1) {
            return Type.FIVEOFKIND;
        }
        if (map.size() == 5) {
            return Type.HIGHCARD;
        }
        if (map.size() == 4) {
            return Type.ONEPAIR;
        }
        Long first = map.entrySet().stream().findFirst().get().getValue();
        if (map.size() == 2 && first == 3) {
            return Type.FULLHOUSE;
        }
        if (map.size() == 2 && first == 4) {
            return Type.FOUROFKIND;
        }

        if (map.size() == 3 && first == 3) {
            return Type.THREEOFKIND;
        }
        if (map.size() == 3 && first == 2) {
            return Type.TWOPAIR;
        }


        return null;
    }


    public static long getPart2Result(List<String> lines) {
        List<Card> cards = new ArrayList<>();
        for (String str : lines) {
            String[] sp = str.split(" ");
            String cardStr = sp[0];
            Map<String, Long> result = Arrays.stream(cardStr.split(""))
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet()
                    .stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

                Type type = getTypeWithJoker(result, cardStr);
                Card card = new Card(cardStr, result, Long.valueOf(sp[1]), type);
                cards.add(card);

        }

        Collections.sort(cards, new CardComparator2());
        Long sum = 0L;
        int rank = 1;
        for (Card card : cards) {
            System.out.println(card.getCard() + " " +card.getType());
            sum += rank * card.getBid();
            rank++;
        }

        return sum;
    }
}