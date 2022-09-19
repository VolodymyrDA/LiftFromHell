package org.vdoloka;

import java.util.List;
import java.util.Random;

public class TransportService {
    private final Lift lift;
    private final Building building;
    private final Random random = new Random();

    public TransportService(Lift lift, Building building) {
        this.lift = lift;
        this.building = building;
        fillBuildingRandomPassengers(building.getPassengerPerFloorCount());
    }

    void run(int stepsCount) {
        List<Passenger> passengersFromLift;
        for (int i = 1; i <= stepsCount; i++) {
            showInformation(i);
            passengersFromLift = lift.unloadPassengers();
            if (lift.isEmpty() && !building.FloorIsEmpty(lift.getCurrentFloor())) {
                lift.setDirection(building.selectLiftDirection(lift.getCurrentFloor()));
            }
            loadPassengersToLift();
            if (!passengersFromLift.isEmpty()) {
                regeneratePassengersOnFloor(passengersFromLift);
            }
            lift.move();
        }
    }

    void loadPassengersToLift() {
        List<Passenger> incomePassengers;
        incomePassengers = building.getPassengers(lift.getCurrentFloor(), lift.getDirection(), lift.getFreePassengersCount());
        lift.loadPassengers(incomePassengers);
    }

    void showInformation(int step) {
        System.out.println("\n***** Step " + step + " *****\n");
        for (int i = building.getFloorCount(); i > 0; i--) {
            System.out.print(i + " floor: " + building.getFloorInfo(i));
            if (i == lift.getCurrentFloor()) {
                System.out.printf(" Lift:{" + lift + "}" + lift.getDirection());
            }
            System.out.print("\n");
        }
    }

    Passenger generatePassenger(int currentFloor) {
        Passenger randomPassenger;
        do {
            randomPassenger = new Passenger(currentFloor, random.nextInt(building.getFloorCount()) + 1);
        } while (randomPassenger.getTargetFloor() == currentFloor);
        return randomPassenger;
    }

    Passenger setNewTargetFloor(Passenger passenger) {
        do {
            passenger.setTargetFloor(random.nextInt(building.getFloorCount()) + 1);
        } while (passenger.getTargetFloor() == passenger.getCurrentFloor());
        return passenger;

    }

    void generatePassengersOnFloor(int floor, int count) {
        for (int i = 0; i < count; i++) {
            Passenger incomingPassenger = generatePassenger(floor);
            building.incomePassenger(incomingPassenger);
        }
    }

    void regeneratePassengersOnFloor(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            Passenger incomingPassenger = setNewTargetFloor(passenger);
            building.incomePassenger(incomingPassenger);
        }
    }

    private void fillBuildingRandomPassengers(int count) {
        for (int currentFloor = 1; currentFloor <= building.getFloorCount(); currentFloor++) {
            generatePassengersOnFloor(currentFloor, random.nextInt(count) + 1);
        }
    }
}