package day7;

import java.util.Map;

public class Card {
    private String card;
    private Map<String, Long> sortedCard;
    private Long bid;
    private Type type;

    public Card(String card, Map<String, Long> sortedCard, Long bid, Type type) {
        this.card = card;
        this.sortedCard = sortedCard;
        this.bid = bid;
        this.type = type;
    }

    public void setSortedCard(Map<String, Long> sortedCard) {
        this.sortedCard = sortedCard;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }
}
