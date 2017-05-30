// $Id$
package ch.iflow.devday2017.functions;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple class representing a car.
 */
public class Car {
    private String manufacturer;
    private String model;
    private int price;
    private Color color;
    private int year;
    
    /**
     * Defines possible car colors.
     */
    public static enum Color {
        WHITE("white"),
        YELLOW("yellow"),
        RED("red"),
        BLUE("blue"),
        BLACK("black");
        
        private String text;
        
        Color(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    
    public Car(String manufacturer, String model, Color color, int year, int price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.color = color;
        this.year = year;
    }

    /**
     * Creates a list with some cars in it
     */
    public static List<Car> makeCars() {
        List<Car> cars = new ArrayList<>();
        
        cars.add(new Car("Audi", "A3", Car.Color.RED, 2011, 18900));
        cars.add(new Car("Volkswagen", "Golf IV", Car.Color.BLACK, 2004, 7500));
        cars.add(new Car("Volkswagen", "Golf V", Car.Color.WHITE, 2005, 8000));
        cars.add(new Car("Audi", "A4", Car.Color.BLUE, 2016, 39000));
        cars.add(new Car("Toyota", "Yaris", Car.Color.WHITE, 2011, 10400));
        cars.add(new Car("Opel", "Corsa", Car.Color.BLACK, 2008, 7000));
        cars.add(new Car("Opel", "Corsa", Car.Color.RED, 2007, 6000));
        cars.add(new Car("Toyota", "Prius", Car.Color.RED, 2008, 8650));
        cars.add(new Car("Audi", "80", Car.Color.RED, 1995, 3500));
        
        return cars;
    }

    /**
     * Print cars as list with header (only if "mute" is off)
     */
    public static void printCars(String header, List<Car> cars) {
        System.out.println("\n" + header);
        for(Car car : cars) {
            System.out.println(car);
        }
    }
    
    @Override
    public String toString() {
        return color + " " + model + " built by " + manufacturer + " in " + year + ", price: " + price;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    
    public String getModel() {
        return model;
    }
    
    public Car.Color getColor() {
        return color;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getYear() {
        return year;
    }
}
