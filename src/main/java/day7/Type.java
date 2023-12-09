package day7;

public enum Type {
    FIVEOFKIND(1),
    FOUROFKIND(2),
    FULLHOUSE(3),
    THREEOFKIND(4),
    TWOPAIR(5),
    ONEPAIR(6),
    HIGHCARD(7);


    private int priorty;

    Type(int priorty) {
        this.priorty = priorty;
    }

    public int getPriorty() {
        return priorty;
    }

    public boolean isLessStronger(Type type) {
        return this.priorty < type.priorty;
    }
    public boolean isSame(Type type) {
        return this.priorty == type.priorty;
    }
}
