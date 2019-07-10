package homework;

public class WordCount {
    private String string;

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    private Integer integer;

    public WordCount(String string, Integer integer) {
        this.string = string;
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public Integer getInteger() {
        return integer;
    }

    @Override
    public String toString() {
        return string + " - " + integer + "; ";
    }
}
