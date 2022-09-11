package org.vdoloka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiftImpl implements Lift {
    private final int MAX_PASSENGERS = 5;
    private final List<Passenger> passengers = new ArrayList<>();
    private int currentFloor;
    private final int maxFloor;

    private Direction direction;

    public LiftImpl(int maxFloor) {
        this.maxFloor = maxFloor;
        currentFloor = 1;
        direction = Direction.UP;
    }

    @Override
    public int getFreePassengersCount() {
        return MAX_PASSENGERS - passengers.size();
    }

    @Override
    public void move() {
        if (currentFloor == 1) {
            direction = Direction.UP;
        } else if (currentFloor == maxFloor) direction = Direction.DOWN;
        if (isFull()) {
            currentFloor = findClosestPassengerFloor();
        } else {
            currentFloor = currentFloor + direction.getDirection();
        }
    }

    @Override
    public boolean isFull() {
        return passengers.size() == MAX_PASSENGERS - 1;
    }

    @Override
    public boolean isEmpty() {
        return passengers.isEmpty();
    }

    @Override
    public void loadPassengers(List<Passenger> passengers) {
        this.passengers.addAll(passengers);
    }

    @Override
    public List<Passenger> unloadPassengers() {
        if (passengers.isEmpty()) {
            return Collections.emptyList();
        }
        List<Passenger> passengersOut = new ArrayList<>();
        for (Passenger passenger : passengers)
            if (passenger.getTargetFloor() == currentFloor) {
                passengersOut.add(passenger);
            }
        passengers.removeAll(passengersOut);
        return passengersOut;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return passengers.toString();
    }

    private int findClosestPassengerFloor() {
        int result;
        if (direction == Direction.UP) {
            int min = maxFloor + 1;
            for (Passenger passenger : passengers) {
                if (passenger.getTargetFloor() < min) {
                    min = passenger.getTargetFloor();
                }
            }
            result = min;
        } else {
            int max = 0;
            for (Passenger passenger : passengers) {
                if (passenger.getTargetFloor() > max) {
                    max = passenger.getTargetFloor();
                }
            }
            result = max;
        }
        return result;
    }
}