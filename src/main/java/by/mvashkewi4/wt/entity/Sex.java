package by.mvashkewi4.wt.entity;

public enum Sex {
    Man(1),
    Woman(2);

    private final int id;

    Sex(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
