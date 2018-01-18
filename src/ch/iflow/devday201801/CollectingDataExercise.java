// $Id$
package ch.iflow.devday201801;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.iflow.devday2017.functions.Car;
import ch.iflow.devday2017.functions.Car.Color;

public class CollectingDataExercise {

    public static void main(String[] args) {
        List<Car> cars = Car.makeCars();
        
        // Bsp 1 - Anzahl Autos pro Hersteller
        Map<String, Long> carsPerManufacturer = null;
        System.out.println(carsPerManufacturer);
        
        // Bsp 2 - Summe der Preise pro Farbe
        Map<Color, Integer> sumPerColor = null;
        System.out.println(sumPerColor);
    }
}
