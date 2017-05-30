// $Id$
package ch.iflow.devday2017.functions;

import java.util.Comparator;
import java.util.List;

import ch.iflow.devday2017.Presentation;

public class FunctionsMain {
    
    public static void main(String[] args) {
        List<Car> cars = Car.makeCars();
        
        // implement exercise 1 here...
        List<Car> cars1 = Presentation.filterCars(cars, car -> car.getColor().equals(Car.Color.RED) && car.getPrice() < 10000);
        Car.printCars("1. Übung - günstige rote Audis", cars1);
        
        // implement exercise 2 here...
        cars.sort(Comparator.comparing(Car::getPrice).reversed()); 
        Car.printCars("2. Übung - Sortierung nach Preis", cars);
        
        // implement exercise 3 here...
        cars.sort((Car car1, Car car2) -> {
        	int result = car1.getManufacturer().compareTo(car2.getManufacturer());
        	return result != 0 ? result : car1.getModel().compareTo(car2.getModel());
        });
        Car.printCars("3. Übung - Sortierung nach Hersteller und Modell", cars);
        
        // implement exercise 3.1 here...
        cars.sort(Comparator.comparing(Car::getManufacturer).thenComparing(Car::getModel).reversed());
        Car.printCars("3.1 Übung - Sortierung nach Hersteller und Modell", cars);
    }
}
