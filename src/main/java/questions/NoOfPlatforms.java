package questions;

import java.util.Arrays;

/**
 * Calculates the minimum number of railway platforms required so that no train has to wait.
 */
public class NoOfPlatforms {

    public static void main(String[] args) {
        int[][] simpleTrains = {
                {900, 930},  // Train A stays from 09:00 to 09:30
                {915, 1000}, // Train B arrives while A is still there
                {940, 1200}  // Train C arrives after B leaves, so still 2 platforms needed
        };

        System.out.println("Simple example platforms = " + findMinPlatforms(simpleTrains));

        int[][] trains = {
                {900, 910},
                {940, 1200},
                {950, 1120},
                {1100, 1130},
                {1500, 1900},
                {1800, 2000}
        };

        System.out.println("Minimum Platforms Required = " + findMinPlatforms(trains));
    }

    private static int findMinPlatforms(int[][] trains) {
        int n = trains.length;

        int[] arrivals = new int[n];
        int[] departures = new int[n];

        for (int i = 0; i < n; i++) {
            arrivals[i] = trains[i][0];
            departures[i] = trains[i][1];
        }

        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int arrivalIndex = 0;
        int departureIndex = 0;
        int platformsInUse = 0;
        int maxPlatforms = 0;

        while (arrivalIndex < n && departureIndex < n) {
            if (arrivals[arrivalIndex] <= departures[departureIndex]) {
                platformsInUse++;
                maxPlatforms = Math.max(maxPlatforms, platformsInUse);
                arrivalIndex++;
            } else {
                platformsInUse--;
                departureIndex++;
            }
        }

        return maxPlatforms;
    }
}
