package functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVersion {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Black", "limeGreen");

        Stream<String> upperCase = strings.stream().filter(s -> Character.isUpperCase(s.charAt(0)));

        strings.forEach(s -> System.out.println("> " + s));
        System.out.println("-------------------------------");
        upperCase.forEach(s -> System.out.println("> " + s));

        System.out.println("-------------------------------");
        strings.forEach(s -> System.out.println("> " + s));

        System.out.println("-------------------------------");
        strings.stream()
                .filter(x -> Character.isUpperCase(x.charAt(0)))
                .map(x -> x.toUpperCase())
                .forEach(x -> System.out.println(x));

        System.out.println("-------------------------------");
        strings.stream()
                .filter(x -> Character.isLowerCase(x.charAt(0)))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        List<Car> carList =
                Arrays.asList(
                        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila", "Tim"),
                        Car.withGasColorPassengers(3, "Blue", "Cade", "Peter", "Kim", "Peter"),
                        Car.withGasColorPassengers(9, "Green", "Bobby", "Jim", "Taylor", "Sam"),
                        Car.withGasColorPassengers(7, "Black", "Brian", "Guy"),
                        Car.withGasColorPassengers(6, "Red", "Chen", "Hank", "Dan", "Mike", "Ed")
                );

        carList.stream()
                .filter(c -> c.getGasLevel() > 6)
                .map(c -> c.getPassengers().get(0) + " is driving a " + c.getColor() + " car with lots of fuel")
                .forEach(c -> System.out.println("> " + c));

        System.out.println("-------------------------------");
//        carList
//                .map(c -> Car.withGasColorPassengers(c.getGasLevel() + 4, c.getColor(), c.getPassengers().toArray(new String[]{})))
//                .forEach(c -> System.out.println(">> " + c));
        carList
                .stream()
                .map(c -> c.addGas(4))
                .forEach(c -> System.out.println(">> " + c));

        System.out.println("-------------------------------");
        carList
                .forEach(c -> System.out.println(">> " + c));

        System.out.println("-------------------------------");
        carList.stream()
                .filter( c -> c.getPassengers().size() > 4)
                .flatMap(c -> c.getPassengers().stream())
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));

        System.out.println("-------------------------------");
        carList
                .stream()
                .flatMap(c -> c.getPassengers().stream()
                        .map(p -> p + " is riding in " + c.getColor() + " car"))
                .forEach(s -> System.out.println(s));



    }
}
