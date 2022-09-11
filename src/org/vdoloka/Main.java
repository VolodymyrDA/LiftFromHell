package org.vdoloka;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int LIFT_PASSENGER_CAPACITY = 5;
        int MIN_FOOR_COUNT = 5;
        int MAX_FLOOR_COUNT = 20;
        int STEPS_COUNT = 10;
        int MAX_PASSENGERS_PER_FLOOR = 10;
        Random random = new Random();
        Building building = new BuildingImpl((random.nextInt(MAX_FLOOR_COUNT - MIN_FOOR_COUNT + 1) + MIN_FOOR_COUNT),
                random.nextInt(MAX_PASSENGERS_PER_FLOOR )+1);
        Lift lift = new LiftImpl(LIFT_PASSENGER_CAPACITY);
        TransportService transportService = new TransportService(lift, building);
        transportService.run(STEPS_COUNT);
    }
}