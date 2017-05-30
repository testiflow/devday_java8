// $Id$
package ch.iflow.devday2017.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ch.iflow.devday2017.functions.Car;

public class StreamsMain {

    public static void main(String[] args) {
        List<Car> cars = Car.makeCars();
        int[] numbersArray = {4, 9, 10, 3, 17, 0, 3, 2, 8, 15, 9, 11, 1, 3, 20};
        
        // implement exercise 1 here...
        List<Car> cars2 = cars.stream().sorted(Comparator.comparing(Car::getYear)).limit(3).collect(Collectors.toList());
        System.out.println("1. Übung - Preis-Summe der 3 ältesten Autos: " + cars2.stream().mapToInt(Car::getPrice).sum());
        
        // implement exercise 2 here...
        int sumsOfNumbersSmallerThan10 = Arrays.stream(numbersArray).filter(num -> num < 10).sum();
        System.out.println("2. Übung - Summe aller Zahlen kleiner 10: " + sumsOfNumbersSmallerThan10);

        // implement exercise 3 here...
        List<Car> selectedCars = cars.stream().filter(car -> !car.getManufacturer().equals("Toyota")).collect(Collectors.toList());
        selectedCars = selectedCars.stream().sorted(Comparator.comparing(Car::getYear)).limit(selectedCars.size() - 3).collect(Collectors.toList());
        selectedCars = selectedCars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
        
        Car.printCars("3. Übung - Autos sortiert nach Preis, ohne die 3 neuesten und ohne Toyotas: ", selectedCars);
    }
}
