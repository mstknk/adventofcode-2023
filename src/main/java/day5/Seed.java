package day5;

public class Seed {
    Long dest;
    Long source;
    Long aLong;

    public Seed(Long dest, Long source, Long aLong) {
        this.dest = dest;
        this.source = source;
        this.aLong = aLong;
    }

    public Long getDest() {
        return dest;
    }

    public void setDest(Long dest) {
        this.dest = dest;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }
}
