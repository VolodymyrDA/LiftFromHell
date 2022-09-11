package org.vdoloka;

import java.util.List;

public interface Lift {

    int getFreePassengersCount();

    void move();

    boolean isEmpty();

    boolean isFull();

    int getCurrentFloor();

    void loadPassengers(List<Passenger> passengers);

    List<Passenger> unloadPassengers();

    Direction getDirection();

    void setDirection(Direction direction);
}