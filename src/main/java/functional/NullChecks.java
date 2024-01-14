package functional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NullChecks {

    public static void main(String[] args) {
        Map<String, Car> owners = new HashMap<>();
        owners.put("Sheila", Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"));
        owners.put("Librarian", Car.withGasColorPassengers(6, "Octarine", "Rincewind", "Ridcully"));
        owners.put("Ogg", Car.withGasColorPassengersAndTrunk(6, "Black", "WeatherMax", "Magrat"));
        String owner = "Sheila";
        Car c = owners.get(owner);
        if(c != null){
            List<String> trunkItems = c.getTrunkContents();
            if(trunkItems != null){
                System.out.println(owner + " has " + trunkItems + " in the car");
            }
        }

        System.out.println("--------------------------");
        Optional<Map<String, Car>> ownersOpt = Optional.of(owners);

        ownersOpt
                .map(m -> m.get(owner))
                .map(x -> x.getTrunkContentsOpt()
                        .map(l -> l.toString())
                        .orElse("nothing"))
                .map(x -> owner + " has " + x + " in the car")
                .ifPresent(m -> System.out.println(m));

    }
}
