package functional;

import java.util.*;
import java.util.function.Predicate;

public class Car {
    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents){
        this.color = color;
        this.gasLevel = gasLevel;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

    // static factory instead of public constructor
    public static Car withGasColorPassengers( int gas, String color, String... passengers){ // variable length list
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));// use immutable
        return new Car(gas, color, p, null);
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers){
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p,  Arrays.asList("jack", "wrench", "spare wheel"));
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public  Car addGas(int g){
        return new Car(gasLevel + g, color, passengers, trunkContents);
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    public Optional<List<String>> getTrunkContentsOpt() {
        return Optional.ofNullable(trunkContents);
    }

    @Override
    public String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                (trunkContents != null ? ", trunkContents=" + trunkContents : " no trunk") +
                '}';
    }


    // Anonymous inner class
    private static final Predicate<Car> RED_CAR_PREDICATE = c -> c.color.equals("Red"); // Expression Lambda
    // weak approach
    //private static final RedCarCriterion RED_CAR_CRITERION = new RedCarCriterion();

    // factory pattern
    public static Predicate<Car> getRedCarCriterion(){
        //return new RedCarCriterion(); // new object
        return RED_CAR_PREDICATE; //same object singleton pattern
    }

//     static class RedCarCriterion implements CarCriterion {
//        @Override
//        public boolean test(Car c) {
//            return c.color.equals("Red");
//        }
//    }

    public static Predicate<Car> getGasLevelCarCriterion(final int threshold){
        return c -> c.gasLevel >= threshold;
    }
// public static Criterion<Car> getGasLevelCarCriterion(int threshold){
//        return new Criterion<Car>() {
//            @Override
//            public boolean test(Car c) {
//                return c.gasLevel >= threshold;
//            }
//        };
// }

//    public static Criterion<Car> getGasLevelCarCriterion(int threshold){
//        return new GasLevelCriterion(threshold);
//    }
    // Command Pattern GOF
    // nested classes grouping and ownership
//    private static class GasLevelCriterion implements Criterion<Car> {
//        private final int threshold;
//        public GasLevelCriterion(int threshold){
//            this.threshold = threshold;
//        }
//        @Override
//        public boolean test(Car c) {
//            return c.gasLevel >= threshold;
//        }
//    }

    public static Comparator<Car> getGasComparator(){
        return gasComparator;
    }

    private static final Comparator<Car> gasComparator = Comparator.comparingInt(o -> o.gasLevel);

    //private static final Comparator<Car> gasComparator = (o1, o2) -> o1.gasLevel - o2.gasLevel;

//    private static final Comparator<Car> gasComparator = new CarGasComparator();
//    private static class CarGasComparator implements Comparator<Car> {
//
//        @Override
//        public int compare(Car o1, Car o2) {
//            return o1.gasLevel - o2.gasLevel;
//        }
//    }

    // Java is a strongly typed language

    public static Predicate<Car> getFourPassengerCriterion(){
        return c -> c.passengers.size() == 4;
    }

    public static Predicate<Car> getColorCriterion(String... colors){
        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));
        //System.out.println(colorSet);
        return c -> colorSet.contains(c.color);
    }

}
