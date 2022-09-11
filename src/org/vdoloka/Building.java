package org.vdoloka;

import java.util.List;

public interface Building {
    boolean FloorIsEmpty(int floor);

    Direction selectLiftDirection(int floor);

    void incomePassenger(Passenger passenger);

    List<Passenger> getPassengers(int floor, Direction direction, int count);

    int getPassengerPerFloorCount();

    int getFloorCount();

    String getFloorInfo(int floor);
}
