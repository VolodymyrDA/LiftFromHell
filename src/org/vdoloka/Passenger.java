package org.vdoloka;

public class Passenger {
    int targetFloor;
    int currentFloor;
    Direction direction;

    public Passenger(int currentFlor, int targetFloor) {
        this.targetFloor = targetFloor;
        this.currentFloor = currentFlor;
        if (targetFloor > currentFloor) {
            direction = Direction.UP;
        }
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public String toString() {
        return String.valueOf(targetFloor);
    }
}