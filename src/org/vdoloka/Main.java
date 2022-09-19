package org.vdoloka;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int LIFT_PASSENGER_CAPACITY = 5;
        final int MIN_FOOR_COUNT = 5;
        final int MAX_FLOOR_COUNT = 20;
        final int STEPS_COUNT = 10;
        final int MAX_PASSENGERS_PER_FLOOR = 10;
        Random random = new Random();
        Building building = new BuildingImpl(MIN_FOOR_COUNT + (random.nextInt(MAX_FLOOR_COUNT - MIN_FOOR_COUNT + 1)),
                random.nextInt(MAX_PASSENGERS_PER_FLOOR) + 1);
        Lift lift = new LiftImpl(LIFT_PASSENGER_CAPACITY);
        TransportService transportService = new TransportService(lift, building);
        transportService.run(STEPS_COUNT);
    }
}