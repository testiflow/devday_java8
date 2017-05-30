// $Id$
package ch.iflow.devday2017.functions;

/**
 * Simple filter implementing our self-defined interface CarFilter
 */
public class CarManufacturerColorFilter implements CarFilter {
    private String manufacturer;
    private Car.Color color;
    
    public CarManufacturerColorFilter(String manufacturer, Car.Color color) {
        this.manufacturer = manufacturer;
        this.color = color;
    }
    
    @Override
    public boolean test(Car car) {
        return car.getColor() == color
                && car.getManufacturer().equals(manufacturer);
    }
}
