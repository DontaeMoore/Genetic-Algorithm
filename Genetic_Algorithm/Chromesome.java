package salespersongenetic2;

import java.util.Random;

public class Chromesome {

    int[] route = {1, 2, 3, 4, 5};  //each chromesome has a route and a distance
    int distance;

    int[][] prices = new int[][]{
        {0, 14, 4, 10, 20},
        {14, 0, 7, 8, 7},
        {4, 5, 0, 7, 16},
        {11, 7, 9, 0, 2},
        {18, 7, 17, 4, 0}

    }; //prices we will use to calculate fitness

    public int[] getRoute() {
        return route;
    }

    public void setRoute(int[] route) {
        this.route = route;
    }

    public void routeRandomize() {

        //randomizes the route stored in a chromesome
        Random rgen = new Random();

        for (int i = 0; i < route.length; i++) {
            int randomPosition = rgen.nextInt(route.length);
            int temp = route[i];
            route[i] = route[randomPosition];
            route[randomPosition] = temp;

        }
    }

    public void printRoute() {

        //prints the route stored in a chromesome
        for (int i = 0; i < route.length; i++) {
            System.out.print(route[i] + " ");
        }
        System.out.println("Distance " + distance);

    }

    public int calculateDistance() {

        //calcualtes distance for a route in a chromesome then returns it
        int x = 0;
        int y = 0;
        for (int i = 0; i < 4; i++) {
            x = route[i];
            y = route[i + 1];

            distance = distance + prices[x - 1][y - 1];

        }
        return distance;

    }

    public int getDistance() {
        return distance;
    }

    public float getNormedDistance() {

        //taking reciprical here so lower distances have higher fitness values
        return 1 / (float) distance;
    }

}
