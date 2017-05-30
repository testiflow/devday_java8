// $Id$
package ch.iflow.devday2017.functions;

import java.util.List;

public class FunctionsMain {
    
    public static void main(String[] args) {
        List<Car> cars = Car.makeCars();
        
        // implement exercise 1 here...
        
        Car.printCars("1. �bung - g�nstige rote Audis", cars);
        
        // implement exercise 2 here...
        
        Car.printCars("2. �bung - Sortierung nach Preis", cars);
        
        // implement exercise 3 here...
        
        Car.printCars("3. �bung - Sortierung nach Hersteller und Modell", cars);
    }
}
