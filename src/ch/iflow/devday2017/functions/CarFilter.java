// $Id$
package ch.iflow.devday2017.functions;

@FunctionalInterface
public interface CarFilter {
    /**
     * Tests a car
     * @return  <code>true</code> if the car matches the filter
     */
    public boolean test(Car car);
}
