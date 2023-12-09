package day7;

public class CardComparator implements java.util.Comparator<Card> {

    @Override
    public int compare(Card a, Card b) {
        if (b.getType() == a.getType()) {
            return checkIsSmaller(b, a) ? 1 : -1;
        }

        return Integer.valueOf(b.getType().getPriorty()).compareTo(a.getType().getPriorty());
    }

    private static boolean checkIsSmaller(Card card1, Card card2) {
        String card1Str = card1.getCard();
        String card2Str = card2.getCard();
        for (int i = 0; i < card1Str.length(); i++) {

            if (getCardValue(card1Str.charAt(i)) < getCardValue(card2Str.charAt(i))) {
                return true;
            } else if (getCardValue(card1Str.charAt(i)) != getCardValue(card2Str.charAt(i))) {
                return false;
            }

        }
        return false;
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
}
