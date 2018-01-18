// $Id$
package ch.iflow.devday201801;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.iflow.devday2017.functions.Car;
import ch.iflow.devday2017.functions.Car.Color;

public class CollectingData {

    public static void main(String[] args) {
        List<Car> cars = Car.makeCars();
        
        // grouping by some value - creates a map as result
        Map<Color, List<Car>> carsByColor = cars.stream().collect(Collectors.groupingBy(Car::getColor));
        System.out.println(carsByColor);
        
        // Collectors.toList -> simply creating a new list
        List<Car> whiteCars = cars.stream().filter(car -> car.getColor() == Color.WHITE).collect(Collectors.toList());
        System.out.println("\n\n" + whiteCars);
        
        // Creating an integer sum
        int priceOfAllCars = cars.stream().collect(Collectors.summingInt(Car::getPrice));
        System.out.println("\nprice of all cars: " + priceOfAllCars);
        
        // Creating statistics
        IntSummaryStatistics priceStatistics = cars.stream().collect(Collectors.summarizingInt(Car::getPrice));
        System.out.println("\nprice statistics: " + priceStatistics);
        
        // Collectors.joining -> join Strings
        String manufactorers = cars.stream().map(Car::getManufacturer).distinct().collect(Collectors.joining(", "));
        System.out.println("\nmanufacturers: " + manufactorers);
        
        // Collectors.reducing -> reduce stream to one value
        int totalPrice = cars.stream().collect(Collectors.reducing(0, Car::getPrice, Integer::sum));
        System.out.println("\nprice of all cars (2): " + totalPrice);
        // other ways for getting the same result
        Optional<Integer> priceSum = cars.stream().map(Car::getPrice).reduce(Integer::sum);
        totalPrice = cars.stream().mapToInt(Car::getPrice).sum();
        
        // grouping by with own lambda expression
        Map<String, List<Car>> priceCategories = cars.stream().collect(Collectors.groupingBy(car -> { 
                int price = car.getPrice();
                if(price < 10000) {
                    return "< 10'000 Fr.";
                }
                else if(price <= 20000) {
                    return "10'000 - 20'000 Fr.";
                }
                else {
                    return "> 20'000 Fr.";
                }
            }));
        System.out.println("\nprice categories: " + priceCategories);
        
        // multi level grouping
        Map<String, Map<Color, List<Car>>> colorPriceCategories = cars.stream().collect(Collectors.groupingBy(car -> { 
            int price = car.getPrice();
            if(price < 10000) {
                return "< 10'000 Fr.";
            }
            else if(price <= 20000) {
                return "10'000 - 20'000 Fr.";
            }
            else {
                return "> 20'000 Fr.";
            }
        }, Collectors.groupingBy(Car::getColor)));
        System.out.println("\ncolor price categories: " + colorPriceCategories);
        
        // collecting data in subgroups
        Map<Color, Long> numberOfCarsPerColor = cars.stream().collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));
        System.out.println("\nnumber of cars by color: " + numberOfCarsPerColor);
        
        Map<Color, Double> avgPricePerColor = cars.stream().collect(Collectors.groupingBy(Car::getColor, Collectors.averagingInt(Car::getPrice)));
        System.out.println("\naverage price per color: " + avgPricePerColor);
        
        // partitioning - with a predicate (a function returning a boolean value)
        Map<Boolean, List<Car>> carsPerTransmission = cars.stream().collect(Collectors.partitioningBy(Car::isAutomatic));
        System.out.println("\ncars per transmission type: " + carsPerTransmission);
    }

}
