package by.mvashkewi4.wt.entity;

public enum Sex {
    MAN(1),
    WOMAN(2);

    private final int id;

    Sex(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
