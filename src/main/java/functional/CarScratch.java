package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

interface Strange {
    boolean stuff(Car c);
}

class PassengerCountOrder implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getPassengers().size() - o2.getPassengers().size();
    }
}


public class CarScratch {

    public static <E> ToIntFunction<E> compareWithThis(E target, Comparator<E> comp){
        return x -> comp.compare(target, x);
    }

    public static <E> Predicate<E> comparesGreater(ToIntFunction<E> comp){
        return x -> comp.applyAsInt(x) < 0;
    }

    // Pure functions
    // Optional




    public static <E> void showAll(List<E> lc){
//        for(Car c : lc){
//            System.out.println(c);
//        }
        lc.stream().forEach(System.out::println);
        System.out.println("-------------------------------------");
    }

//    public static List<Car> getColoredCars(Iterable<Car> in, String color){
//        List<Car> output = new ArrayList<>();
//        for(Car c : in){
//            if(c.getColor().equals(color)){
//                output.add(c);
//            }
//        }
//        return output;
//    }

        public static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> crit){
        List<E> output = new ArrayList<>();
        for(E c : in){
            if(crit.test(c)){
                output.add(c);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila", "Tim"),
                Car.withGasColorPassengers(3, "Blue", "Cade", "Peter", "Kim", "Peter"),
                Car.withGasColorPassengers(9, "Green", "Bobby", "Jim", "Taylor", "Sam"),
                Car.withGasColorPassengers(7, "Black", "Brian", "Guy"),
                Car.withGasColorPassengers(6, "Red", "Chen", "Hank", "Dan", "Mike", "Ed")
        );
        //showAll(cars);
//        showAll(getByCriterion(cars, new Car.RedCarCriterion()));
//        showAll(getByCriterion(cars, Car.RED_CAR_CRITERION));
        //showAll(getByCriterion(cars, Car.getRedCarCriterion()));
        //showAll(getByCriterion(cars, Car.GasLevelCarCriterion(4)));

//        showAll(cars);
//        cars.sort(Car.getGasComparator());
//        showAll(cars);

        //showAll(getByCriterion(cars, c -> c.getPassengers().size() == 2));
        //showAll(getByCriterion(cars, Car.getFourPassengerCriterion()));

        //CarCriterion x = c -> c.getColor().equals("Red");
        //boolean b = ((Strange)( c -> c.getColor().equals("Red"))).stuff(Car.withGasColorPassengers(0, "Red"));
        //boolean b = ((Criterion)( c -> c.getColor().equals("Red"))).stuff(Car.withGasColorPassengers(0, "Red"));

        //cars.sort(new PassengerCountOrder());
        //showAll(cars);
        //showAll(getColoredCars(cars, "Black"));
        //showAll(cars.stream().filter(car -> car.getColor().equals("Red")).collect(Collectors.toList()));
        //showAll(cars.stream().filter(car -> car.getGasLevel() >= 6).collect(Collectors.toList()));
        //showAll(cars.stream().filter(car -> car.getPassengers().size() >= 5).collect(Collectors.toList()));

        //List<String> colors = Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Black", "limeGreen");
//        showAll(getByCriterion(colors, st -> st.length() > 4));
//        showAll(getByCriterion(colors, st -> Character.isUpperCase(st.charAt(0))));
//
//        LocalDate today = LocalDate.now();
//        List<LocalDate> dates = Arrays.asList(today.minusDays(1), today, today.plusDays(1), today.plusDays(7) );
//        showAll(getByCriterion(dates, ld -> ld.isAfter(today)));

//        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(7)));
//        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(4)));
//        showAll(getByCriterion(cars, Car.getColorCriterion("Red", "Black")));

//        Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);
//        showAll(getByCriterion(cars, level7));
//
//        Predicate<Car> notLevel7 = level7.negate();
//        showAll(getByCriterion(cars, notLevel7));
//
//        Predicate<Car> isRed = Car.getColorCriterion("Red");
//        Predicate<Car> fourPassengers = Car.getFourPassengerCriterion();
//
//        Predicate<Car> redFourPassengers = isRed.and(fourPassengers);
//
//        showAll(getByCriterion(cars, redFourPassengers));
//
//        Predicate<Car> isBlack = Car.getColorCriterion("Black");
//        Predicate<Car> blackFourPassengers = isBlack.or(fourPassengers);
//
//        showAll(getByCriterion(cars, blackFourPassengers));

        Car bert = Car.withGasColorPassengers(5, "Blue");

        ToIntFunction<Car> compareWithBert = compareWithThis(bert, Car.getGasComparator());
//        for (Car c : cars){
//            System.out.println("comparing " + c + " with bert gives " + compareWithBert.applyAsInt(c));
//        }

        showAll(getByCriterion(cars, comparesGreater(compareWithBert)));

    }
}
