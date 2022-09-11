package org.vdoloka;

import java.util.*;

public class BuildingImpl implements Building {
    private final int floorCount;
    private final int passengerPerFloorCount;
    private final TreeMap<Integer, Queue<Passenger>> passengersFloorUp;
    private final TreeMap<Integer, Queue<Passenger>> passengersFloorDown;

    public BuildingImpl(int floorCount, int passengerPerFloorCount) {
        this.floorCount = floorCount;
        this.passengerPerFloorCount = passengerPerFloorCount;
        passengersFloorUp = new TreeMap<>();
        passengersFloorDown = new TreeMap<>();
        for (int i = 1; i <= floorCount; i++) {
            passengersFloorUp.put(i, new LinkedList<>());
            passengersFloorDown.put(i, new LinkedList<>());
        }
    }

    @Override
    public int getFloorCount() {
        return floorCount;
    }

    @Override
    public int getPassengerPerFloorCount() {
        return passengerPerFloorCount;
    }

    @Override
    public String getFloorInfo(int floor) {
        return "^" + passengersFloorUp.get(floor) + "v" + passengersFloorDown.get(floor);
    }

    @Override
    public boolean FloorIsEmpty(int floor) {
        return (passengersFloorUp.get(floor).isEmpty() && passengersFloorDown.get(floor).isEmpty());
    }

    @Override
    public Direction selectLiftDirection(int floor) {
        if (passengersFloorUp.get(floor).size() > passengersFloorDown.get(floor).size()) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }

    @Override
    public void incomePassenger(Passenger passenger) {
        if (passenger.direction == Direction.UP) {
            passengersFloorUp.get(passenger.currentFloor).add(passenger);
        } else {
            passengersFloorDown.get(passenger.getCurrentFloor()).add(passenger);
        }
    }

    @Override
    public List<Passenger> getPassengers(int floor, Direction direction, int count) {
        List<Passenger> boardingPassengers = new LinkedList<>();
        if (direction == Direction.UP) {
            if (passengersFloorUp.get(floor).isEmpty()) {
                return Collections.emptyList();
            }
            int counter = Math.min(count, passengersFloorUp.get(floor).size());
            for (int i = 1; i <= counter; i++) {
                boardingPassengers.add(passengersFloorUp.get(floor).poll());
            }
        } else {
            if (passengersFloorUp.get(floor).isEmpty()) {
                return Collections.emptyList();
            }
            int counter = Math.min(count, passengersFloorDown.get(floor).size());
            for (int i = 1; i <= counter; i++) {
                boardingPassengers.add(passengersFloorDown.get(floor).poll());
            }
        }
        return boardingPassengers;
    }
}