// $Id$
package ch.iflow.devday2017;

import static ch.iflow.devday2017.functions.Car.printCars;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.iflow.devday2017.functions.Car;
import ch.iflow.devday2017.functions.CarFilter;
import ch.iflow.devday2017.functions.CarManufacturerColorFilter;

public class Presentation {

    public static void main(String[] args) {
        functionsDemo();
        System.out.println("\n");
        streamsDemo();
    }

    public static void functionsDemo() {
        List<Car> cars = Car.makeCars();
        
        // simple filtering
        printCars("1. simple filtering", filterBlackCars(cars));
        printCars("2. still simple filtering", filterBlackOpels(cars));
        printCars("3. simple filtering with parameters", filterCars(cars, Car.Color.RED, "Audi"));
        
        // filtering with filter interface
        printCars("4. filter interface implementation", filterCarsWithInterface(cars, new CarManufacturerColorFilter("Audi", Car.Color.RED)));
        
        // filtering with filter interface - anonymous class
        printCars("5. filter with anonymous class",
                filterCarsWithInterface(cars, new CarFilter() {
                    @Override
                    public boolean test(Car car) {
                        return car.getColor() == Car.Color.RED && car.getManufacturer().equals("Audi");
                    }
                }));
        
        // filtering with lambdas
        Predicate<Car> redAudisPredicate = (Car car) -> car.getColor() == Car.Color.RED && car.getManufacturer().equals("Audi");
        printCars("6. filter with lambda from variable", filterCars(cars, redAudisPredicate));
        
        printCars("7. filter with lambda 2", filterCars(cars, (Car car) -> car.getColor() == Car.Color.RED && car.getManufacturer().equals("Audi")));
        
        printCars("8. filter with lambda, type inference", filterCars(cars, car -> car.getColor() == Car.Color.WHITE && car.getPrice() > 10000));
        
        Predicate<Car> blackOpelsPredicate = car -> car.getColor() == Car.Color.BLACK && car.getManufacturer().equals("Opel");
        printCars("9. filter with combination of lambdas", filterCars(cars, redAudisPredicate.or(blackOpelsPredicate)));
        
        // sorting with lambdas
        cars.sort((Car car1, Car car2) -> car1.getManufacturer().compareTo(car2.getManufacturer()));
        printCars("10. sorted by manufacturer", cars);
        
        cars.sort(Comparator.comparing((Car car) -> car.getYear()));
        printCars("11. sorted by year with comparing", cars);
        
        cars.sort(Comparator.comparing(Car::getYear));
        printCars("12. sorted by year with comparing (method reference)", cars);
    }
    
    /**
     * Step 1: find black cars and return them in a new list.
     */
    public static List<Car> filterBlackCars(List<Car> cars) {
        List<Car> result = new ArrayList<>();
        
        for(Car car : cars) {
            if(car.getColor() == Car.Color.BLACK) {
                result.add(car);
            }
        }
        
        return result;
    }

    /**
     * Step 2: find black Opel cars and return them in a new list.
     */
    public static List<Car> filterBlackOpels(List<Car> cars) {
        List<Car> result = new ArrayList<>();
        
        for(Car car : cars) {
            if(car.getColor() == Car.Color.BLACK
                    && car.getManufacturer().equals("Opel")) {
                result.add(car);
            }
        }
        
        return result;
    }

    /**
     * Step 3: find cars which match given parameters.
     */
    public static List<Car> filterCars(List<Car> cars, Car.Color color, String manufacturer) {
        List<Car> result = new ArrayList<>();
        
        for(Car car : cars) {
            if(car.getColor() == color
                    && car.getManufacturer().equals(manufacturer)) {
                result.add(car);
            }
        }
        
        return result;
    }
    
    /**
     * Step 4: accept custom implemented filters via interface
     */
    public static List<Car> filterCarsWithInterface(List<Car> cars, CarFilter filter) {
        List<Car> result = new ArrayList<>();
        
        for(Car car : cars) {
            if(filter.test(car)) {
                result.add(car);
            }
        }
        
        return result;
    }

    /**
     * Step 5: accept Java 8 functional interface predicate
     */
    public static List<Car> filterCars(List<Car> cars, Predicate<Car> filter) {
        List<Car> result = new ArrayList<>();
        
        for(Car car : cars) {
            if(filter.test(car)) {
                result.add(car);
            }
        }
        
        return result;
    }

    /**
     * Examples from dev day Java 8 presentation regarding the new {@link Stream} API of
     * Java 8.
     */
    public static void streamsDemo() {
        List<Car> cars = Car.makeCars();
        
        List<Car> cheapCars = cars.stream().filter(car -> car.getPrice() < 10000).collect(toList());
        printCars("1. filter", cheapCars);
        
        System.out.println();
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
            .filter(i -> i % 2 == 0)
            .distinct()
            .forEach(System.out::println);
        
        System.out.println();
        numbers.stream()
            .limit(3)
            .forEach(System.out::println);
        
        List<Car> twoMostExpensiveCars = cars.stream()
            .sorted(Comparator.comparing(Car::getPrice).reversed())
            .limit(2)
            .collect(toList());
        printCars("2. two most expensive cars", twoMostExpensiveCars);

        List<String> manufacturerNames = cars.stream()
            .map(Car::getManufacturer)
            .distinct()
            .sorted()
            .collect(toList());
        
        System.out.println("\nHersteller:");
        System.out.println(manufacturerNames);
        
        boolean areThereYellowCars = cars.stream().anyMatch(car -> car.getColor() == Car.Color.YELLOW);
        boolean areAllCarsRed = cars.stream().allMatch(car -> car.getColor() == Car.Color.RED);
        boolean areThreeOldestCarsCheaperThan10000 = cars.stream()
            .sorted(Comparator.comparing(Car::getYear))
            .limit(3)
            .noneMatch(car -> car.getPrice() >= 10000);

        System.out.println("\nareThereYellowCars: " + areThereYellowCars);
        System.out.println("\nareAllCarsRed: " + areAllCarsRed);
        System.out.println("\nareThreeOldestCarsCheaperThan10000: " + areThreeOldestCarsCheaperThan10000);
        
        Optional<Car> cheapestRedCar = cars.stream()
            .filter(car -> car.getColor() == Car.Color.RED)
            .sorted(Comparator.comparing(Car::getPrice))
            .findFirst();

        System.out.println();
        cheapestRedCar.ifPresent(System.out::println);
        
        
        int priceOfAllRedCars = cars.stream()
            .filter(car -> car.getColor() == Car.Color.RED)
            .map(car -> car.getPrice())
            .reduce(0, Integer::sum);

        System.out.println("\nprice (sum) of all red cars: " + priceOfAllRedCars);
        

        int priceOfMostExpensiveRedCar = cars.stream()
                .filter(car -> car.getColor() == Car.Color.RED)
                .map(car -> car.getPrice())
                .reduce(0, (price1, price2) -> price1 > price2 ? price1 : price2);

        System.out.println("\nprice of most expensive red car: " + priceOfMostExpensiveRedCar);
        
        OptionalDouble averageRedCarPrice = cars.stream()
                .filter(car -> car.getColor() == Car.Color.RED)
                .mapToInt(Car::getPrice)
                .average();
        
        if(averageRedCarPrice.isPresent()) {
            System.out.println("\naverage red car price: " + averageRedCarPrice.getAsDouble());
        }
        
        String letters = Stream.of("A", "B", "C").reduce("", (a, b) -> a + b);
        
        int[] numbersArray = {4, 9, 10, 5, 3, 17};
        int sum = Arrays.stream(numbersArray).sum();

        Stream.iterate(0, n -> n + 2)
            .limit(10)
            .forEach(System.out::println);
        
        Stream.generate(Math::random)
            .limit(5)
            .forEach(System.out::println);
        

        Map<String, Long> redCarsByManufacturer = cars.stream()
                .filter(car -> car.getColor() == Car.Color.RED)
                .collect(Collectors.groupingBy(Car::getManufacturer, Collectors.counting()));
            
        System.out.println("\nred cars by manufacturer: " + redCarsByManufacturer);
    }
}
