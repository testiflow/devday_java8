// $Id$
package ch.iflow.devday2017.streams;

import java.util.Collections;
import java.util.List;

import ch.iflow.devday2017.functions.Car;

public class StreamsMain {

    public static void main(String[] args) {
        List<Car> cars = Car.makeCars();
        int[] numbersArray = {4, 9, 10, 3, 17, 0, 3, 2, 8, 15, 9, 11, 1, 3, 20};
        
        // implement exercise 1 here...
        int priceSumOfThreeOldestCars = 0;
        
        System.out.println("1. Übung - Preis-Summe der 3 ältesten Autos: " + priceSumOfThreeOldestCars);
        
        // implement exercise 2 here...
        int sumsOfNumbersSmallerThan10 = 0;
        
        System.out.println("2. Übung - Summe aller Zahlen kleiner 10: " + sumsOfNumbersSmallerThan10);

        // implement exercise 3 here...
        List<Car> selectedCars = Collections.emptyList();
        
        Car.printCars("3. Übung - Autos sortiert nach Preis, ohne die 3 neuesten und ohne Toyotas: ", selectedCars);
    }
}
