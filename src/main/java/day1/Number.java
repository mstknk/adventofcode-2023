package day1;

public enum Number {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9");
    private String tag;

    private Number(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public static Number ifContains(String line) {
        for (Number enumValue : values()) {
            if (line.contains(enumValue.toString().toLowerCase())) {
                return enumValue;
            }
        }
        return null;
    }

}
