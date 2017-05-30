// $Id$
package ch.iflow.devday2017.functions;

import java.util.List;

public class FunctionsMain {
    
    public static void main(String[] args) {
        List<Car> cars = Car.makeCars();
        
        // implement exercise 1 here...
        
        Car.printCars("1. Übung - günstige rote Audis", cars);
        
        // implement exercise 2 here...
        
        Car.printCars("2. Übung - Sortierung nach Preis", cars);
        
        // implement exercise 3 here...
        
        Car.printCars("3. Übung - Sortierung nach Hersteller und Modell", cars);
    }
}
