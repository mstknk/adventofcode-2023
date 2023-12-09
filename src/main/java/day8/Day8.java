package day8;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/main/resources/day8_input.txt");
        List<String> data = Files.readAllLines(path);
        System.out.println(getPart1Result(data));
        System.out.println(getPart22Result(data));

    }

    public static BigInteger findLCM(BigInteger[] numbers) {
        BigInteger lcm = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            lcm = lcm.multiply(numbers[i]).divide(lcm.gcd(numbers[i]));
        }
        return lcm;
    }

    public static int getPart1Result(List<String> data) {

        String instructions = data.get(0);
        List<Node> nodes = new ArrayList<>();
        for (int i = 2; i < data.size(); i++) {
            String str = data.get(i);
            Node node = extracted(str);
            nodes.add(node);
        }
        for (Node node : nodes) {
            Node leftNode = findNode(nodes, node.getLeft());
            Node rightNode = findNode(nodes, node.getRight());
            node.setLeftNode(leftNode);
            node.setRightNode(rightNode);

        }
        boolean found = false;
        int index = 0;
        Node currentNode = findNode(nodes, "AAA");
        int step = 0;
        while (!found) {
            if (index == instructions.length()) {
                index = 0;
            }
            char currentIns = instructions.charAt(index);
            if (currentIns == 'L') {
                currentNode = currentNode.getLeftNode();
            } else {
                currentNode = currentNode.getRightNode();
            }
            step++;
            index++;
            if (currentNode.getCurrent().contentEquals("ZZZ")) {
                break;
            }
        }
        return step;

    }

    public static BigInteger getPart22Result(List<String> data) {

        String instructions = data.get(0);
        List<Node> nodes = new ArrayList<>();
        for (int i = 2; i < data.size(); i++) {
            String str = data.get(i);
            Node node = extracted(str);
            nodes.add(node);
        }
        for (Node node : nodes) {
            Node leftNode = findNode(nodes, node.getLeft());
            Node rightNode = findNode(nodes, node.getRight());
            node.setLeftNode(leftNode);
            node.setRightNode(rightNode);

        }
        boolean found = false;
        int index = 0;
        List<Node> nodelist = findStartingNodesWithEndsA(nodes);
        List<BigInteger> numbers = new ArrayList<>();
        for (Node currentNode : nodelist) {
            int step = 0;
            found = false;
            while (!found) {
                if (index == instructions.length()) {
                    index = 0;
                }
                char currentIns = instructions.charAt(index);
                if (currentIns == 'L') {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode = currentNode.getRightNode();
                }
                step++;
                index++;
                if (currentNode.getCurrent().endsWith("Z")) {
                    break;
                }

            }
            numbers.add(BigInteger.valueOf(step));
        }
        return findLCM(numbers.toArray(new BigInteger[numbers.size()]));

    }

    public static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return findGCD(b, a % b);
        }
    }

    private static Node findNode(List<Node> nodes, String node) {
        return nodes.stream().filter(e -> e.getCurrent().contentEquals(node)).findFirst().get();
    }

    private static Node extracted(String str) {
        String current = str.substring(0, str.indexOf("=") - 1);
        String leftRight = str.substring(str.indexOf("(") + 1, str.lastIndexOf(")"));
        String[] lR = leftRight.split(",");
        return new Node(current, lR[0].trim(), lR[1].trim());
    }

    private static List<Node> findStartingNodesWithEndsA(List<Node> nodes) {
        return nodes.stream().filter(e -> e.getCurrent().endsWith("A")).collect(Collectors.toList());
    }
}