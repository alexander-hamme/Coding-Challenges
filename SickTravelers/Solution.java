/**
 * @author Alex Hamme
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This solution, while somewhat extensive, was designed with
 * the goal of keeping the operations at linear time complexity O(n),
 * which this solution does. 
 * 
 * Total time complexity of each travel timestep is O(n + n + n) = O(n). 
 */
public class Solution {

    private static final int MAX_STEPS = 365;

    private ArrayList<Traveler> travelers = new ArrayList<>();

    public enum CityStatus {  // unrelated to the INFECTED enum field below
        IS_INFECTED, NOT_INFECTED
    }

    // map city name to current city status. Values get reset at the start of each new day of travel.
    private HashMap<String, CityStatus> cities_infected = new HashMap<>();

    private enum Health {

        HEALTHY("HEALTHY"),
        RECOVERING("RECOVERING"),
        SICK("SICK"),
        INFECTED("");     // extra in-between state, only exists in between calls to Traveler.checkIfInfected() and Traveler.updateHealth()

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
        }
        
        public boolean isHealthy() {
            return health == Health.HEALTHY;
        }

        public void checkIfInfected() {
            if (health == Health.HEALTHY) {
                boolean got_infected = cities_infected.get(city_names[curr_city]) == CityStatus.IS_INFECTED;
                health = (got_infected) ? Health.INFECTED : Health.HEALTHY;
            }
        }

        /**
         * Updates health to next state. Note that by the time this function is executed,
         * checkIfInfected() has already been called.
         */
        public void moveNext() {  // O(1) operation overall

            updateHealth();

            curr_city = (curr_city + 1) % numb_cities;

            if (this.isInfectious()) {      // set city status to infected
                cities_infected.put(city_names[curr_city], CityStatus.IS_INFECTED);    // O(1) operation
            }
        }

        public void updateHealth() {  // O(1)

            switch (health) {

                case HEALTHY:
                    break;

                case INFECTED:                 // special in-between state, exists only between calls to
                    health = Health.SICK;      // checkIfInfected() and updateHealth().
                    break;                     // Traveler CANNOT infect others while in this state.

                case SICK:
                    health = Health.RECOVERING;
                    break;
                    
                case RECOVERING:
                    health = Health.HEALTHY;
                    break;
            }
        }
    }

    private boolean allAreHealthy() {         // O(n)
        for (Traveler traveler : travelers) {
            if (!traveler.isHealthy()) {
                return false;
            }
        }
        return true;
    }
    
    private void printTravelers() {
        travelers.forEach(traveler -> System.out.print(
                String.format("%-17s", "(" + traveler.currCity() + ")"))
        );

        System.out.println();
        travelers.forEach(traveler -> System.out.print(
                String.format("%-17s", traveler.health))
        );
        System.out.println("\n");
    }

    /**
     * Called once to construct initial HashMap and add instances of Traveler class
     * to the `travelers` ArrayList
     * @param input_strings String array, unchanged from initial input array
     */
    private void setupValues(String[] input_strings) {

        for (String str : input_strings) {

            String[] fields = str.split(" ");

            String[] cities = new String[fields.length - 2];
            System.arraycopy(fields, 2, cities, 0, cities.length);

            Traveler traveler = new Traveler(fields[0], Health.valueOf(fields[1]), cities);

            if (traveler.isInfectious()) {
                cities_infected.put(                 // overwrite previous possible status with INFECTED
                        traveler.currCity(), CityStatus.IS_INFECTED
                );
            }

            for (String city : cities) {
                cities_infected.putIfAbsent(city, CityStatus.NOT_INFECTED);  // don't overwrite previous possible statuses
            }                                                                // with NOT_INFECTED

            travelers.add(traveler);
        }
    }

    private int solveTrip(String[] input_strings) {

        boolean PRINT_OUTPUT = true;

        setupValues(input_strings);

        Set<String> all_cities = cities_infected.keySet();

        int step = 0;

        if (PRINT_OUTPUT) {
            travelers.forEach(traveler -> System.out.print(
                String.format(" %-15s", traveler.getName()))
            );
            System.out.println();
        }

        while (step < MAX_STEPS) {      // each iteration is just O(n + n + n) = O(n)

            // not included in time complexity calculation
            if (PRINT_OUTPUT) { printTravelers(); }

            // for each traveler, checks if currently at a location that is infected.
            // this is O(n) because it's just a single HashMap access O(1) for each traveler
            travelers.forEach(Traveler::checkIfInfected);           // O(n)

            // Check if all travelers are healthy
            if (this.allAreHealthy()) { break; }                    // O(n)

            // reset all city values before next step
            all_cities.forEach(city -> cities_infected.put(city, CityStatus.NOT_INFECTED));

            travelers.forEach(Traveler::moveNext);                  // O(n)

            step++;
        }
        return step;
    }
    
    // this function is static for compatibility with the JUnit Test Cases on HackerRank
    public static int solve(String[] inputLines) {
        return new Solution().solveTrip(inputLines);
    }

    public static void main(String[] args) {
        
        String[] travelers = new String[]{   // 8 steps
                "David HEALTHY London Tokyo Berlin Tokyo",
                "Lisa RECOVERING Tokyo Berlin London Seattle Berlin",
                "Frank SICK Seattle London Tokyo Berlin",
                "Mary SICK Berlin Berlin London Seattle"
        };
        System.out.println("\n\nSteps required: " + solve(travelers));
    }
}


