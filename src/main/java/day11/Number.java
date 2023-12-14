package day11;

public class Number {
    private int number;
    private Long x;
    private Long y;

    public Number(int number, Long x, Long y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
