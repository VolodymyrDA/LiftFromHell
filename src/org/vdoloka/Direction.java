package org.vdoloka;

public enum Direction {
    UP(1), DOWN(-1);

    private final int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        if (this == Direction.UP) return "^";
        else return "v";
    }
}