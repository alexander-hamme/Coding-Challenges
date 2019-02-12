
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


public class Solution {

    private final int max_steps = 365;

    private ArrayList<Traveler> travelers = new ArrayList<>();


    public enum CityStatus {
        INFECTED, NOT_INFECTED
    }


    // map city name to boolean --> True if it there is a sick or recovering person there,
    // False if only healthy people are currently there
    private HashMap<String, CityStatus> cities_infected = new HashMap<>();


    public enum Health {

        HEALTHY("HEALTHY"),
        RECOVERING("RECOVERING"),
        SICK("SICK"),
        INFECTED("");                       // extra state

        final String message;

        Health(String h) {
            this.message = h;
        }
    }


    public class Traveler {

        private int curr_city = 0;
        private int numb_cities;
        private String[] city_names;

        private String name;
        private Health health;

        public String getName() {
            return name;
        }

        public String currCity() {
            return city_names[curr_city];
        }

        public boolean isInfectious() {
            return health == Health.SICK || health == Health.RECOVERING;
        }

        public Traveler(String name, Health h, String[] cities) {
            this.name = name;
            this.city_names = cities;
            this.numb_cities = cities.length;
            this.health = h;

            System.out.println(String.format("Traveler: %s, %s, %s", name, health, Arrays.toString(city_names)));
        }

        public void printStatus() {
            System.out.println(
                    String.format("%s is in %s and is %s", name, city_names[curr_city], health)
            );
        }

        public void checkInfected() {
            if (health == Health.HEALTHY) {
                boolean got_infected = cities_infected.get(city_names[curr_city]) == CityStatus.INFECTED;
                health = (got_infected) ? Health.INFECTED : Health.HEALTHY;
            }
        }

        public void moveNext() {

            updateHealth();

            curr_city = (curr_city + 1) % numb_cities;

            if (this.isInfectious()) {
                cities_infected.put(city_names[curr_city], CityStatus.INFECTED);    // O(1) operation
            }

        }

        public void updateHealth() {

            switch (health) {

                case HEALTHY:
                    break;

                case RECOVERING:
                    health = Health.HEALTHY;
                    break;

                case SICK:
                    health = Health.RECOVERING;
                    break;

                case INFECTED:                 // special in-between state. Can NOT infect others in this state.
                    health = Health.SICK;
                    break;
            }
        }

        public boolean isHealthy() {
            return health == Health.HEALTHY;
        }
    }


    private void setupValues(String[] input_strings) {

        for (String str : input_strings) {

            String[] fields = str.split(" ");

            String[] cities = new String[fields.length - 2];
            System.arraycopy(fields, 2, cities, 0, cities.length);

            Traveler traveler = new Traveler(fields[0], Health.valueOf(fields[1]), cities);

            if (traveler.isInfectious()) {
                cities_infected.put(                 // overwrite previous possible status
                        traveler.currCity(), CityStatus.INFECTED
                );
            }

            for (int i=1; i<cities.length; i++) {
                cities_infected.putIfAbsent(cities[i], CityStatus.NOT_INFECTED);  // don't overwrite previous possible status
            }

            travelers.add(traveler);
        }
    }

    private int solve(String[] input_strings) {

        setupValues(input_strings);

        Set<String> all_cities = cities_infected.keySet();

        int step = 0;

        travelers.forEach(traveler -> System.out.print(
                String.format(" %-14s", traveler.getName()))
        );
        System.out.println();

        while (step < max_steps) {

            //iterate through travelers, *updating travelers health* and also keeping track if there are any sick


//            travelers.forEach(traveler -> System.out.print("(" + traveler.currCity() + ")\t"));
            travelers.forEach(traveler -> System.out.print(
                    String.format("%-15s", "(" + traveler.currCity() + ")"))
            );

            System.out.println();
            travelers.forEach(traveler -> System.out.print(
                    String.format("%-15s", traveler.health))
            );

            travelers.forEach(Traveler::checkInfected);


            boolean all_healthy = true;

            for (Traveler traveler : travelers) {
                if (!traveler.isHealthy()) {
                    all_healthy = false;
                }
            }

            if (all_healthy) {
                break;
            }

            System.out.println();


//            System.out.println("\nAfter check for infection");
//            travelers.forEach(Traveler::printStatus);


            // reset all city values before next step
            all_cities.forEach(city -> cities_infected.put(city, CityStatus.NOT_INFECTED));

            /* These two together represent the next timestep */

            //travelers.forEach(Traveler::updateHealth);

//            System.out.println("\nAfter update health");
//            travelers.forEach(Traveler::printStatus);


            travelers.forEach(Traveler::moveNext);


            //travelers.forEach(Traveler::checkInfected);





            step++;

            System.out.println();

        }


        return step;
    }


    public static void main(String[] args) {
        String[] travelers = {
                "John HEALTHY Seattle London Seattle Berlin",
                "Lily RECOVERING Seattle Berlin",
                "Joanna SICK Berlin Berlin London Tokyo",
                "Tim RECOVERING Berlin London London Seattle"
        };


        travelers = new String[]{
                "John SICK Tokyo Berlin London Seattle Berlin",
                "Lily RECOVERING Seattle London Tokyo Berlin",
                "Joanna SICK Seattle Tokyo Berlin Tokyo",
                "Tim HEALTHY Berlin London London Seattle"
        };


        travelers = new String[]{
                "John RECOVERING Tokyo Berlin London Seattle Berlin",
                "Lily SICK Seattle London Tokyo Berlin",
                "Joanna RECOVERING London Tokyo Berlin Tokyo",
                "Tim SICK Berlin Berlin London Seattle"
        };

        System.out.println("\n\nSteps required: " + new Solution().solve(travelers));
    }
}


